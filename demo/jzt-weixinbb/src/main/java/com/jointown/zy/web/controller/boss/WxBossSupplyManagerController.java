package com.jointown.zy.web.controller.boss;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.jointown.zy.common.enums.InfoWarehousSourceEnum;
import com.jointown.zy.common.enums.InfoWarehousStateEnum;
import com.jointown.zy.common.enums.WxInfoResourceEnum;
import com.jointown.zy.common.enums.WxSupplyResourceEnum;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.exception.WxErrorException;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxSupply;
import com.jointown.zy.common.model.WxSupplyPic;
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
import com.jointown.zy.web.controller.WxUserBaseController;

/**
 * 微信后台管理：供应信息
 *
 * @author aizhengdong
 *
 * @date 2015年7月22日
 */
@Controller
@RequestMapping(value = "/Boss/wxBossSupply")
public class WxBossSupplyManagerController extends WxUserBaseController {

	@Autowired
	private WxSupplyService wxSupplyService;

	@Autowired
	private BusiWarehouseApplyService warehouseApplyService;

	@Autowired
	private EastGongqiuService eastGongqiuService;
	
	@Autowired
	private BreedService breedService;
	
	@Autowired
	private SftpConfigInfo sftpConfigInfo;
	
	/**
	 * 供求信息管理页面，初始化显示首屏供应信息
	 * 
	 * @author aizhengdong
	 * @date 2015年7月22日
	 */
	@RequestMapping(value = "")
	public String wxBossSupplyManager(@ModelAttribute WxSupplySearchDto wxSupplySearchDto, HttpServletRequest request, ModelMap model) {
		// 验证登录
		BossUserVo bossUser = getWxBossUserInfo();
		if (bossUser == null) {
			return "redirect:/WxBoss/wxSystem";
		}
					
		String pageNo = request.getParameter("pageNo");
		
		//查询结果
		List<WxSupplyVo> wxSupplys = querySupply(wxSupplySearchDto, pageNo);
		
		//信息状态
		Map<String,String> statusMap = InfoWarehousStateEnum.toMap();
		
		//信息来源
		Map<String,String> sypplyResourcesMap = WxSupplyResourceEnum.toMap();
		Map<String,String> demandResourcesMap = WxInfoResourceEnum.toMap();
		Map<String,String> warehousResourcesMap = InfoWarehousSourceEnum.toMap();
		
		//查询地区-省市
		AreaVo record = new AreaVo();
		record.setType("1");
		List<AreaVo> areas = warehouseApplyService.findAreasByCondition(record);
		
		// 价格的数量单位
		List<DictInfoVo> dicts = wxSupplyService.findWxSupplyDicts();
				
		model.put("supplyList", wxSupplys);
		model.put("statusMap", statusMap);
		model.put("supplyResourcesMap", sypplyResourcesMap);
		model.put("demandResourcesMap", demandResourcesMap);
		model.put("warehousResourcesMap", warehousResourcesMap);
		model.put("areas", areas);
		model.put("dicts", dicts);
		
		return "/boss/admin-sell";
	}

	/**
	 * ajax查询供应信息
	 *
	 * @author aizhengdong
	 * @date 2015年7月22日
	 */
	@RequestMapping(value = "/getSupply", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getSupply(HttpServletRequest request, @ModelAttribute WxSupplySearchDto wxSupplySearchDto) {
		String pageNo = request.getParameter("pageNo");
		List<WxSupplyVo> wxSupplys = querySupply(wxSupplySearchDto, pageNo);
		
		// 返回结果
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("supplyList", wxSupplys);
		return resultMap;
	}
	
	/**
	 * 查询供应信息数据
	 *
	 * @param wxSupplySearchDto
	 * @param pageNo
	 * @return
	 *
	 * @author aizhengdong
	 * @date 2015年8月13日
	 */
	private List<WxSupplyVo> querySupply(WxSupplySearchDto wxSupplySearchDto, String pageNo){
		if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
		
		//查询条件
		Page<WxSupplyVo> page = new Page<WxSupplyVo>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("wxSupplySearchDto", wxSupplySearchDto);
		page.setParams(params);
		page.setPageNo(Integer.parseInt(pageNo));
		
		//查询结果
		return wxSupplyService.findWxSupplysByCondition(page);
	}
	
	/**
	 * 供应信息详情
	 *
	 * @author aizhengdong
	 * @date 2015年8月4日
	 */
	@RequestMapping(value = "/supplyDetail", method = RequestMethod.GET)
	public String getDemandDetail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			// 验证登录
			BossUserVo bossUser = getWxBossUserInfo();
			if (bossUser == null) {
				return "redirect:/WxBoss/wxSystem";
			}
			
			String supplyId = request.getParameter("supplyId");
			String applyResource = request.getParameter("applyResource");
			
			// 验证参数
			if(StringUtils.isBlank(supplyId) || StringUtils.isBlank(applyResource)){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			
			//查询条件
			Page<WxSupplyVo> page = new Page<WxSupplyVo>();
			Map<String, Object> params = new HashMap<String, Object>();
			WxSupplySearchDto wxSupplySearchDto = new WxSupplySearchDto();
			wxSupplySearchDto.setSupplyId(Long.parseLong(supplyId));
			wxSupplySearchDto.setSypplyResource(Short.parseShort(applyResource));
			params.put("wxSupplySearchDto", wxSupplySearchDto);
			page.setParams(params);
			
			// 查询结果
			List<WxSupplyVo> wxSupplys = wxSupplyService.findWxSupplysByCondition(page);
			
			if(wxSupplys != null && wxSupplys.size() == 1){
				model.put("supply", wxSupplys.get(0));
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return "/boss/a-sellDetail";
	}
	
	/**
	 * 审核供应信息
	 * 
	 * @author aizhengdong
	 * @date 2015年8月4日
	 */
	@RequestMapping(value = "/checkSupply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkSupply(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if (bossUser == null) {
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
						
			String supplyIdStr = request.getParameter("supplyId");
			String applyResourceStr = request.getParameter("applyResource");
			String statusStr = request.getParameter("status");
			String remarks = request.getParameter("remarks");
			
			// 验证参数
			if (StringUtils.isBlank(supplyIdStr) || StringUtils.isBlank(applyResourceStr) || StringUtils.isBlank(statusStr)) {
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}

			long supplyId = Long.parseLong(supplyIdStr);
			short applyResource = Short.parseShort(applyResourceStr);
			short status = Short.parseShort(statusStr);
			
			if(Short.parseShort(WxSupplyResourceEnum.WX.getCode()) == applyResource){
				//验证信息是否存在
				WxSupply wxSupply = new WxSupply();
				wxSupply.setSupplyId(supplyId);
				WxSupply record = wxSupplyService.findWxSupplyById(wxSupply);
				if(record == null){
					throw new WxErrorException(ErrorRepository.WX_NO_RECORD);
				}
				
				//更新信息
				wxSupply.setApproveTime(new Date());
				wxSupply.setApprover(bossUser.getId().longValue());
				wxSupply.setStatus(status);
				wxSupply.setRemarks(remarks);
				
				wxSupplyService.updateWxSupplyById(wxSupply);
				
				map.put("ok", true);
				map.put("msg", "处理成功！");
			}else{
				map.put("ok", false);
				map.put("msg", "信息来源异常！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("ok", false);
			String msg = e.getMessage();
			if (e instanceof WxErrorException) {
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			} else {
				map.put("msg", "处理失败！");
			}
		}

		return map;
	}
	
	/**
	 * 新增微信供应信息
	 * 
	 * @author aizhengdong
	 * @date 2015年8月6日
	 */
	@RequestMapping(value="/addWxSupply",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addWxSupply(@ModelAttribute WxSupplyDto wxSupplyDto, @ModelAttribute WxSupplyPicDto wxSupplyPicDto) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		SFTPUtil sftp = null;
		Session session = null;
		try{
			//验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			//验证参数
			String breedName = wxSupplyDto.getBreedName();
			if(StringUtils.isBlank(breedName)){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			
			// 验证品种名称
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
			session = sftp.openSession(sftpConfigInfo.getSftpIpWx(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
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
			Integer userId = bossUser.getId();
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
	 * 查询地区-市区
	 * 
	 * @author aizhengdong
	 * @date 2015年8月5日
	 */
	@RequestMapping(value="/getAreasByCode",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getAreasByCode(@RequestParam("code") String code) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证参数
			if(StringUtils.isBlank(code)){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			
			AreaVo record = new AreaVo();
			record.setFirstcode(code);
			List<AreaVo> areas = warehouseApplyService.findAreasByCondition(record);
			if(areas != null && areas.size() > 0){
				map.put("ok", true);
				map.put("obj", areas);
			}else{
				map.put("ok", false);
			}
		} catch (Exception e) {
			map.put("ok", false);
		}
		
		return map;
	}
	
	/**
	 * 检查品种是否存在
	 * 
	 * @author aizhengdong
	 * @date 2015年8月5日
	 */
	@ResponseBody
	@RequestMapping(value = "/checkBreedName",method=RequestMethod.POST)
	public Map<String,Object> checkBreedName(@RequestParam(value="param",defaultValue="",required=true) String breedName) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//验证参数
			if(StringUtils.isBlank(breedName)){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			
			Breed breed = breedService.findBreedByBreedName(breedName);
			if(breed != null){
				map.put("breedId", breed.getBreedId());
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
		}
		
		return map;
	}
	
	/**
	 * 品种名称联想
	 * 
	 * @author aizhengdong
	 * @date 2015年8月6日
	 */
	@ResponseBody
	@RequestMapping(value = "/getBreedNames", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getBreedNames(HttpServletRequest request) {
		String breedName = request.getParameter("query");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Breed> breeds = breedService.findBreedsByName(breedName);
		List<String> data = new ArrayList<String>();
		for (Breed breed : breeds) {
			data.add(breed.getBreedName());
		}
		map.put("query", breedName);
		map.put("suggestions", data);
		map.put("data", breeds);
		return map;
	}
	
	/**
	 * 上传图片到资源服务器临时目录
	 * 
	 * @author aizhengdong
	 * @date 2015年8月6日
	 */
	@RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
	public void uploadPic(HttpServletRequest request,HttpServletResponse response,@RequestParam("file") MultipartFile[] files) {
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> picPaths = new ArrayList<String>();
		SFTPUtil sftp = null;
		Session session = null;
		ChannelSftp channel = null;
		
		try {
			// 验证登录
			BossUserVo user = getWxBossUserInfo();
			if(user == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			// 初始化
			sftp = SFTPUtil.getSingleton();
			session = sftp.openSession(sftpConfigInfo.getSftpIpWx(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
			channel = sftp.getChannelSftp(session,"sftp");
			
			for (MultipartFile file : files) {
				String dateDir = TimeUtil.getTimeShowByTimePartten(new Date(),"yyyyMM");
				String fileName = UploadUtils.generateFilename("jpg");
				boolean isImage = UploadUtils.isImage(file.getInputStream());
				if(isImage){
					//上传原图到资源服务器临时目录下
					sftp.upload(channel, fileName, file.getInputStream(), dateDir,sftpConfigInfo.getSftpDataDir(),sftpConfigInfo.getSftpImagesDir(),sftpConfigInfo.getSftpTempProjectDir());
					
					int sftpMiddleWidth = sftpConfigInfo.getSftpMiddleWidth();
					String scaleFileName = fileName.substring(0,fileName.lastIndexOf(".")) + "_" + sftpMiddleWidth+".jpg";
					OutputStream thumbOutstream = channel.put(scaleFileName);
					ImageHelper.scaleImage(file.getInputStream(), thumbOutstream, sftpMiddleWidth, sftpMiddleWidth);
					
					// 图片文件和路径
					fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_" + sftpMiddleWidth + fileName.substring(fileName.lastIndexOf("."));
					String filePath = sftpConfigInfo.getSftpImagesDir()+"/"+sftpConfigInfo.getSftpTempProjectDir() + "/" + dateDir + "/" + fileName;
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
		} finally {
			//关闭管道连接
	        try {
	        	if(sftp!=null){
	        		sftp.closeChannel(channel,session);
	        	}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String res = gson.toJson(map);
		response.setContentType("text/plain;charset=UTF-8");
		
		try {
			response.getWriter().println(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传图片到资源服务器图片目录
	 * 
	 * @author aizhengdong
	 * @date 2015年8月6日
	 */
	public String uploadWxSupplyPic(SFTPUtil sftp,Session session,String filePath) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("imgpath", filePath);
		map.put("dataDir", sftpConfigInfo.getSftpDataDir());
		map.put("imagesDir", sftpConfigInfo.getSftpImagesDir());
		map.put("projectDir", sftpConfigInfo.getSftpProjectDirWx());
		map.put("tempProjectDir", sftpConfigInfo.getSftpTempProjectDir());
		sftp.moveImg(session, map);
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		String b = filePath.substring(0,filePath.lastIndexOf("/"));
		String dateDir = b.substring(b.lastIndexOf("/") + 1);
		String fileNewPath = sftpConfigInfo.getSftpImagesDir() + "/" + sftpConfigInfo.getSftpProjectDirWx() + "/" + dateDir + "/" + fileName;
		return fileNewPath;
	}
	
}
