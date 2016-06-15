package com.jointown.zy.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jointown.zy.common.dto.WxSupplyDto;
import com.jointown.zy.common.dto.WxSupplyPicDto;
import com.jointown.zy.common.dto.WxSupplySearchDto;
import com.jointown.zy.common.enums.InfoManagerColorEnum;
import com.jointown.zy.common.enums.InfoWarehousStateEnum;
import com.jointown.zy.common.enums.WxSupplyResourceEnum;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.exception.WxErrorException;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.EastGongqiu;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxSupply;
import com.jointown.zy.common.model.WxSupplyPic;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BreedService;
import com.jointown.zy.common.service.BusiWarehouseApplyService;
import com.jointown.zy.common.service.EastGongqiuService;
import com.jointown.zy.common.service.WxSupplyService;
import com.jointown.zy.common.util.ImageHelper;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.util.image.UploadUtils;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.DictInfoVo;
import com.jointown.zy.common.vo.WxSupplyVo;

/**
 * 微信供应信息审核管理
 *
 * @author aizhengdong
 *
 * @data 2015年3月17日
 */
@Controller(value="wxSupplyController")
@RequestMapping(value="bossWxSupply")
public class WxSupplyController {
	
	private final static Logger logger = LoggerFactory.getLogger(WxSupplyController.class);
	
	@Autowired
	private BreedService breedService;
	
	@Autowired
	private EastGongqiuService eastGongqiuService;
	
	@Autowired
	private WxSupplyService wxSupplyService;
	
	@Autowired
	private BusiWarehouseApplyService warehouseApplyService;
	
	@Autowired
	private SftpConfigInfo sftpConfigInfo ;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String getWxSupplyManager(@RequestParam(value="pageNo",required=false,defaultValue="1") String pageNo, @ModelAttribute WxSupplySearchDto wxSupplySearchDto, ModelMap model) throws Exception {
		Page<WxSupplyVo> page = new Page<WxSupplyVo>();
		if(!pageNo.isEmpty()){
			page.setPageNo(Integer.parseInt(pageNo));
		}
		//查询条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("wxSupplySearchDto", wxSupplySearchDto);
		page.setParams(params);
		//查询结果
		List<WxSupplyVo> wxSupplys = wxSupplyService.findWxSupplysByCondition(page);
		page.setResults(wxSupplys);
		//单位
		List<DictInfoVo> dicts = wxSupplyService.findWxSupplyDicts();
		//信息状态
		Map<String,String> statusMap = InfoWarehousStateEnum.toMap();
		//信息来源
		Map<String,String> sypplyResourcesMap = WxSupplyResourceEnum.toMap();
		//added by biran 20150722 将供求关系-供应信息管理和微信-供应信息审核分开
		sypplyResourcesMap.remove("1");
		//查询地区-省市
		AreaVo record = new AreaVo();
		record.setType("1");
		List<AreaVo> areas = warehouseApplyService.findAreasByCondition(record);
				
		model.put("page", page);
		model.put("dicts", dicts);
		model.put("statusMap", statusMap);
		model.put("sypplyResourcesMap", sypplyResourcesMap);
		model.put("areas", areas);
		
		model.put("colorMap", InfoManagerColorEnum.toMap()); // 状态颜色列表
		
		return "/public/wxSupplyManage";
	}
	
	
	/**
	 * 
	 * @Description: 查询供应信息，伪查询
	 * @Author: wangjunhu
	 * @Date: 2015年6月17日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getWxSupply", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getWxSupply() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ok", true);
		return map;
	}
	
	/**
	 * 
	 * @Description: 供应信息有效
	 * @Author: wangjunhu
	 * @Date: 2015年6月16日
	 * @param supplyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkWxSupplyValid",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> checkWxSupplyValid(@ModelAttribute WxSupplyDto wxSupplyDto) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证权限
			BossUserVo user = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			Long supplyId = wxSupplyDto.getSupplyId();
			Short sypplyResource = wxSupplyDto.getSypplyResource();
			if(supplyId==null || sypplyResource==null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			short sypplyResourceWx = Short.parseShort(WxSupplyResourceEnum.WX.getCode());
			short sypplyResourceEast = Short.parseShort(WxSupplyResourceEnum.EAST.getCode());
			if(sypplyResourceWx==sypplyResource.shortValue()){
				//验证信息是否存在
				WxSupply wxSupply = new WxSupply();
				wxSupply.setSupplyId(supplyId);
				WxSupply record = wxSupplyService.findWxSupplyById(wxSupply);
				if(record==null){
					throw new WxErrorException(ErrorRepository.WX_NO_RECORD);
				}
				//更新信息
				Date date = new Date();
				wxSupply.setApproveTime(date);
				Integer userId = user.getId();
				wxSupply.setApprover(userId.longValue());
				Short statusValid = (short) InfoWarehousStateEnum.VALID.getStatus();
				wxSupply.setStatus(statusValid);
				String remarks = wxSupplyDto.getRemarks();
				if(remarks!=null){
					wxSupplyDto.setRemarks(remarks);
				}
				
				wxSupplyService.updateWxSupplyById(wxSupply);
				
				map.put("ok", true);
				map.put("msg", "处理成功！");
			}else if(sypplyResourceEast==sypplyResource.shortValue()){
				//验证信息是否存在
				BigDecimal gqid = new BigDecimal(supplyId);
				EastGongqiu record = eastGongqiuService.findEastGongqiuById(gqid);
				String gqflg = record.getGqflg();
				if(record==null || !"1".equals(gqflg)){
					throw new WxErrorException(ErrorRepository.WX_NO_RECORD);
				}
				//更新信息
				EastGongqiu eastGongqiu = new EastGongqiu();
				eastGongqiu.setGqid(gqid);
				Date date = new Date();
				eastGongqiu.setApproveTime(date);
				Integer userId = user.getId();
				eastGongqiu.setApprover(userId.longValue());
				Short statusValid = (short) InfoWarehousStateEnum.VALID.getStatus();
				eastGongqiu.setStatus(statusValid);
				
				eastGongqiuService.updateEastGongqiu(eastGongqiu);
				
				map.put("ok", true);
				map.put("msg", "处理成功！");
			}else{
				map.put("ok", false);
				map.put("msg", "信息来源异常！");
			}
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "处理失败！");
			}
			logger.error("WxSupplyController.checkWxSupplyValid："+msg);
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 供应信息无效
	 * @Author: wangjunhu
	 * @Date: 2015年6月16日
	 * @param supplyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkWxSupplyInvalid",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> checkWxSupplyInvalid(@ModelAttribute WxSupplyDto wxSupplyDto) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证权限
			BossUserVo user = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			Long supplyId = wxSupplyDto.getSupplyId();
			Short sypplyResource = wxSupplyDto.getSypplyResource();
			if(supplyId==null || sypplyResource==null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			short sypplyResourceWx = Short.parseShort(WxSupplyResourceEnum.WX.getCode());
			short sypplyResourceEast = Short.parseShort(WxSupplyResourceEnum.EAST.getCode());
			if(sypplyResourceWx==sypplyResource.shortValue()){
				//验证信息是否存在
				WxSupply wxSupply = new WxSupply();
				wxSupply.setSupplyId(supplyId);
				WxSupply record = wxSupplyService.findWxSupplyById(wxSupply);
				if(record==null){
					throw new WxErrorException(ErrorRepository.WX_NO_RECORD);
				}
				//更新信息
				Date date = new Date();
				wxSupply.setApproveTime(date);
				Integer userId = user.getId();
				wxSupply.setApprover(userId.longValue());
				Short statusInvalid = (short) InfoWarehousStateEnum.INVALID.getStatus();
				wxSupply.setStatus(statusInvalid);
				String remarks = wxSupplyDto.getRemarks();
				if(remarks!=null){
					wxSupplyDto.setRemarks(remarks);
				}
				
				wxSupplyService.updateWxSupplyById(wxSupply);
				
				map.put("ok", true);
				map.put("msg", "处理成功！");
			}else if(sypplyResourceEast==sypplyResource.shortValue()){
				//验证信息是否存在
				BigDecimal gqid = new BigDecimal(supplyId);
				EastGongqiu record = eastGongqiuService.findEastGongqiuById(gqid);
				String gqflg = record.getGqflg();
				if(record==null || !"1".equals(gqflg)){
					throw new WxErrorException(ErrorRepository.WX_NO_RECORD);
				}
				//更新信息
				EastGongqiu eastGongqiu = new EastGongqiu();
				eastGongqiu.setGqid(gqid);
				Date date = new Date();
				eastGongqiu.setApproveTime(date);
				Integer userId = user.getId();
				eastGongqiu.setApprover(userId.longValue());
				Short statusInvalid = (short) InfoWarehousStateEnum.INVALID.getStatus();
				eastGongqiu.setStatus(statusInvalid);
				
				eastGongqiuService.updateEastGongqiu(eastGongqiu);
				
				map.put("ok", true);
				map.put("msg", "处理成功！");
			}else{
				map.put("ok", false);
				map.put("msg", "信息来源异常！");
			}
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "处理失败！");
			}
			logger.error("WxSupplyController.checkWxSupplyInvalid："+msg);
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 新增微信供应信息
	 * @Author: wangjunhu
	 * @Date: 2015年4月12日
	 * @param wxSupplyDto
	 * @param wxSupplyPicDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addWxSupply",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addWxSupply(@ModelAttribute WxSupplyDto wxSupplyDto, @ModelAttribute WxSupplyPicDto wxSupplyPicDto) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		SFTPUtil sftp = null;
		Session session = null;
		try{
			//验证权限
			BossUserVo user = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			String breedName = wxSupplyDto.getBreedName();
			if(breedName==null || breedName.isEmpty()){
				throw new WxErrorException(ErrorRepository.WX_NO_BREED);
			}
			Breed breed = breedService.findBreedByBreedName(breedName);
			if(breed == null){
				throw new WxErrorException(ErrorRepository.WX_NO_BREED);
			}
			//验证图片数量
			List<WxSupplyPic> wxSupplyPics = wxSupplyPicDto.getWxSupplyPics();
			int wxSupplyPicsSize = wxSupplyPics.size();
			if(wxSupplyPicsSize < 1 || wxSupplyPicsSize > 6){
				throw new WxErrorException(ErrorRepository.WX_UPLOAD_IMG_NUM_ERROR);
			}
			//上传图片到资源服务器
			sftp = SFTPUtil.getSingleton();
			session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
			for (int i = 0; i < wxSupplyPicsSize; i++) {
				WxSupplyPic wxSupplyPic = wxSupplyPics.get(i);
				//上传缩略图
				wxSupplyPic.setType((short)(i*2));
				String resizeWxSupplyPicTempPath = wxSupplyPic.getPicPath();
				String resizeWxSupplyPicPath = uploadWxSupplyPic(sftp,session,resizeWxSupplyPicTempPath);
				wxSupplyPic.setPicPath(resizeWxSupplyPicPath);
				//上传原图
				WxSupplyPic orgignWxSupplyPic = new WxSupplyPic();
				orgignWxSupplyPic.setType((short)(i*2+1));
				String orgignWxSupplyPicTempPath = resizeWxSupplyPicTempPath.substring(0, resizeWxSupplyPicTempPath.lastIndexOf("_")) + resizeWxSupplyPicTempPath.substring(resizeWxSupplyPicTempPath.lastIndexOf("."));
				String orgignWxSupplyPicPath = uploadWxSupplyPic(sftp,session,orgignWxSupplyPicTempPath);
				orgignWxSupplyPic.setPicPath(orgignWxSupplyPicPath);
				wxSupplyPics.add(orgignWxSupplyPic);
			}
			//新增信息
			Long breedId = breed.getBreedId();
			wxSupplyDto.setBreedId(breedId);
			Date date = new Date();
			wxSupplyDto.setApproveTime(date);
			Integer userId = user.getId();
			wxSupplyDto.setApprover(userId.longValue());
			Short statusValid = (short) InfoWarehousStateEnum.VALID.getStatus();
			wxSupplyDto.setStatus(statusValid);
			Short sypplyResourceBack = Short.parseShort(WxSupplyResourceEnum.BACK.getCode());
			wxSupplyDto.setSypplyResource(sypplyResourceBack);
			
			wxSupplyService.addWxSupply(wxSupplyDto, wxSupplyPicDto);
			
			map.put("ok", true);
			map.put("msg", "添加成功！");
		}catch(Exception e){
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "添加失败！");
			}
			logger.error("WxSupplyController.addWxSupply："+msg);
		}finally{
			if (session != null) {
				if (session.isConnected()) {
					session.disconnect();
				}
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 验证品种名称是否存在
	 * @Author: wangjunhu
	 * @Date: 2015年6月16日
	 * @param breedName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkBreedName",method=RequestMethod.POST)
	public Map<String,Object> checkBreedName(@RequestParam(value="param",defaultValue="",required=true) String breedName) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//验证参数
			if(breedName==null || breedName.isEmpty()){
				throw new Exception("参数错误！");
			}
			Breed breed = breedService.findBreedByBreedName(breedName);
			if(breed != null){
				map.put("ok", true);
				map.put("status", "y");
			}else{
				map.put("ok", false);
				map.put("status", "n");
				map.put("info", "请先在品种管理中添加该品种！");
			}
		} catch (Exception e) {
			map.put("ok", false);
			map.put("status", "n");
			map.put("info", "请填写正确的品种名称！");
			logger.error("WxSupplyController.checkBreedName："+e.getMessage());
		}
		
		return map;
	}
	
	/**
	 * 
	 * @Description: 品种名称联想
	 * @Author: wangjunhu
	 * @Date: 2015年6月16日
	 * @param breedName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getBreedNames",method=RequestMethod.GET)
	public Map<String,Object> getBreedNames(@RequestParam(value="query",defaultValue="",required=true) String breedName) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Breed> breeds = breedService.findBreedsByName(breedName);
		List<Map<String,Object>> suggestions = new ArrayList<Map<String,Object>>();
		for (Breed breed : breeds) {
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("value", breed.getBreedName());
			m.put("data", breed.getBreedId());
			suggestions.add(m);
		}
		map.put("query", breedName);
		map.put("suggestions", suggestions);
		return map;
	}
	
	/**
	 * 查询地区
	 * @param firstCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getAreasByCode",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getAreasByCode(@RequestParam("code") String code) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证参数
			if(code==null || code.isEmpty()){
				throw new Exception("参数错误！");
			}
			//查询地区-市区
			AreaVo record = new AreaVo();
			record.setFirstcode(code);
			List<AreaVo> areas = warehouseApplyService.findAreasByCondition(record);
			if(areas.size()>0){
				map.put("ok", true);
				map.put("obj", areas);
			}else{
				map.put("ok", false);
			}
		} catch (Exception e) {
			map.put("ok", false);
			logger.error("WxSupplyController.getAreasByCode："+e.getMessage());
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 上传图片到资源服务器临时目录
	 * @Author: wangjunhu
	 * @Date: 2015年4月12日
	 * @param request
	 * @param response
	 * @param file
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
	public void uploadPic(HttpServletRequest request,HttpServletResponse response,@RequestParam("file") MultipartFile[] files) throws IOException {
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> picPaths = new ArrayList<String>();
		SFTPUtil sftp = null;
		Session session = null;
		ChannelSftp channel = null;
		try {
			BossUserVo user = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			sftp = SFTPUtil.getSingleton();
			session = sftp.openSession(sftpConfigInfo.getSftpIpWx(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
			channel = sftp.getChannelSftp(session,"sftp");
			for (MultipartFile file : files) {
				String dateDir = TimeUtil.getTimeShowByTimePartten(new Date(),"yyyyMM");
				String fileName = UploadUtils.generateFilename("jpg");
				//验证文件是否为图片
				boolean isImage = UploadUtils.isImage(file.getInputStream());
				if(isImage){
					//上传原图到资源服务器临时目录下
					sftp.upload(channel, fileName, file.getInputStream(), dateDir,sftpConfigInfo.getSftpDataDir(),sftpConfigInfo.getSftpImagesDir(),sftpConfigInfo.getSftpTempProjectDir());
					//判断图片尺寸大小
					//BufferedImage bi = ImageIO.read(file.getInputStream());
					//System.out.println(bi.getWidth()+"/"+bi.getHeight());
					int sftpMiddleWidth = sftpConfigInfo.getSftpMiddleWidth();
					//if(bi.getWidth()>sftpXBigWidth){
						//上传缩略图到资源服务器临时目录下
						String scaleFileName = fileName.substring(0,fileName.lastIndexOf("."))+"_"+sftpMiddleWidth+".jpg";
						OutputStream thumbOutstream = channel.put(scaleFileName);
						ImageHelper.scaleImage(file.getInputStream(), thumbOutstream, sftpMiddleWidth, sftpMiddleWidth);
						fileName = fileName.substring(0, fileName.lastIndexOf("."))+"_"+sftpMiddleWidth+fileName.substring(fileName.lastIndexOf("."));
					//}
					String filePath = sftpConfigInfo.getSftpImagesDir()+"/"+sftpConfigInfo.getSftpTempProjectDir()+"/"+dateDir+"/"+fileName;
					picPaths.add(filePath);
				}
			}
			map.put("ok", true);
			map.put("obj", picPaths);
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "图片上传失败！");
			}
			logger.error("WxSupplyController.uploadPic："+msg);
		} finally {
			//关闭管道连接
	        try {
	        	if(sftp!=null){
	        		sftp.closeChannel(channel,session);
	        	}
			} catch (Exception e) {
				String msg = e.getMessage();
				logger.error("WxSupplyController.uploadPic："+msg);
			}
		}
		String res = gson.toJson(map);
		response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().println(res);
	}
	
	/**
	 * 
	 * @Description: 上传图片到资源服务器图片目录
	 * @Author: wangjunhu
	 * @Date: 2015年4月12日
	 * @param sftp
	 * @param session
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public String uploadWxSupplyPic(SFTPUtil sftp,Session session,String filePath) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("imgpath", filePath);
		map.put("dataDir", sftpConfigInfo.getSftpDataDir());
		map.put("imagesDir", sftpConfigInfo.getSftpImagesDir());
		map.put("projectDir", sftpConfigInfo.getSftpProjectDirWx());
		map.put("tempProjectDir", sftpConfigInfo.getSftpTempProjectDir());
		sftp.moveImg(session, map);
		String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
		String b = filePath.substring(0,filePath.lastIndexOf("/"));
		String dateDir = b.substring(b.lastIndexOf("/")+1);
		String fileNewPath = sftpConfigInfo.getSftpImagesDir() +"/"+ sftpConfigInfo.getSftpProjectDirWx()+"/"+dateDir+"/"+fileName;
		return fileNewPath;
	}
}
