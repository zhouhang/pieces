/**
 * @author lichenxiao
 * 2015年3月19日 下午15:00:00
 */
package com.jointown.zy.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
import com.jointown.zy.common.constant.WxConstant;
import com.jointown.zy.common.dto.WxSupplyDto;
import com.jointown.zy.common.dto.WxSupplyPicDto;
import com.jointown.zy.common.enums.InfoWarehousStateEnum;
import com.jointown.zy.common.enums.WxSupplyResourceEnum;
import com.jointown.zy.common.enums.WxSupplyStateEnum;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.exception.WxErrorException;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxDemand;
import com.jointown.zy.common.model.WxSupply;
import com.jointown.zy.common.model.WxSupplyPic;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BreedService;
import com.jointown.zy.common.service.WxAdService;
import com.jointown.zy.common.service.WxDemandService;
import com.jointown.zy.common.service.WxSupplyService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.ImageHelper;
import com.jointown.zy.common.util.PropertiesUtils;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.util.image.UploadUtils;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.DictInfoVo;
import com.jointown.zy.common.vo.UcUserVo;
import com.jointown.zy.common.vo.WxAdVo;
import com.jointown.zy.common.vo.WxDemandVo;
import com.jointown.zy.common.vo.WxSupplyBreedVo;
import com.jointown.zy.common.vo.WxSupplyVo;

/**
 * 微信用户发布供应信息
 * 
 * @author lichenxiao 2015年3月19日 下午15:04:00
 */
@Controller
@RequestMapping(value="/wxSupplySend")
public class WxSupplySendController extends WxUserBaseController {

	private final static Logger logger = LoggerFactory.getLogger(WxSupplySendController.class);
	
	@Autowired
	private BreedService breedService;
	
	@Autowired
	private WxSupplyService wxSupplyService;
	
	@Autowired
	private WxDemandService wxDemandService;
	
	@Autowired
	private WxAdService wxAdService;
	
	@Autowired
	public ThreadPoolTaskExecutor threadPoolTaskExecutor;	
	
	@Autowired
	private SftpConfigInfo sftpConfigInfo;
	
	@Autowired
	private MessageConfigManage messageConfigManage;

	/**
	 * 发布供求信息用户验证跳转
	 * 	
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/supplySendAuthentication", method = RequestMethod.GET)
	public String supplySendAuthentication(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		//验证登陆
		UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
		if (user==null) {
			WxAdVo wxAd = wxAdService.findWxAdsByPostionName("登录页(720*601)");
			model.put("wxAd", wxAd);
			return "/login";
		}
		//单位
		List<DictInfoVo> dicts = wxSupplyService.findWxSupplyDicts();
		//产地
		List<AreaVo> places = wxSupplyService.findWxSupplyAreas();
		//货物所在地
		List<AreaVo> areas = wxSupplyService.findWxSupplyAreas();
		//供应信息
		Page<WxSupply> wxSupplyPage = new Page<WxSupply>();
		wxSupplyPage.setPageNo(1);
		wxSupplyPage.setPageSize(5);
		Map<String,Object> wxSupplyParams = new HashMap<String,Object>();
		WxSupply wxSupply = new WxSupply();
		Long userId = user.getUserId();
		wxSupply.setUserId(userId);
		wxSupplyParams.put("wxSupply", wxSupply);
		wxSupplyPage.setParams(wxSupplyParams);
		List<WxSupplyVo> wxSupplys = wxSupplyService.findWxSupplysByPage(wxSupplyPage);
		Integer wxSupplyTotalRecordNum = wxSupplyPage.getTotalRecord();
		//求购信息
		Page<WxDemand> wxDemandPage = new Page<WxDemand>();
		wxDemandPage.setPageNo(1);
		wxDemandPage.setPageSize(5);
		Map<String,Object> wxDemandParams = new HashMap<String,Object>();
		WxDemand wxDemand = new WxDemand();
		wxDemand.setUserId(userId);
		wxDemandParams.put("wxDemand", wxDemand);
		wxDemandPage.setParams(wxDemandParams);
		List<WxDemandVo> wxDemands = wxDemandService.findWxDemandsByPage(wxDemandPage);
		Integer wxDemandTotalRecordNum = wxDemandPage.getTotalRecord();
		
		model.put("dicts", dicts);
		model.put("places", places);
		model.put("areas", areas);
		model.put("wxSupplys", wxSupplys);
		model.put("wxSupplyTotalRecordNum", wxSupplyTotalRecordNum);
		model.put("wxDemands", wxDemands);
		model.put("wxDemandTotalRecordNum", wxDemandTotalRecordNum);
		
		return "/supply-send";
	}
	
	/**
	 * 
	 * @Description: 查询微信供应信息，ID查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月12日
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getWxSupply", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getWxSupply(@RequestParam(value="supplyId") Long supplyId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			if(supplyId==null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			//验证信息是否存在
			WxSupply wxSupply = new WxSupply();
			wxSupply.setSupplyId(supplyId);
			Long userId = user.getUserId();
			wxSupply.setUserId(userId);
			WxSupplyVo wxSupplyVo = wxSupplyService.findWxSupplyAndPicById(wxSupply);
			if(wxSupplyVo==null){
				throw new WxErrorException(ErrorRepository.WX_NO_RECORD);
			}
			map.put("ok", true);
			map.put("msg", "加载成功！");
			map.put("wxSupply", wxSupplyVo);
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "加载失败！");
			}
			logger.error("WxSupplySendController.getWxSupply："+msg);
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 查询更多的微信供应信息，分页查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月12日
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMoreWxSupplys", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getMoreWxSupplys(@RequestParam(value="pageNo",required=true,defaultValue="1") Integer pageNo) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			if(pageNo==null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			//分页条件查询
			Page<WxSupply> page = new Page<WxSupply>();
			page.setPageNo(pageNo);
			page.setPageSize(5);
			
			Map<String, Object> params = new HashMap<String, Object>();
			WxSupply wxSupply = new WxSupply();
			Long userId = user.getUserId();
			wxSupply.setUserId(userId);
			params.put("wxSupply", wxSupply);
			page.setParams(params);

			List<WxSupplyVo> wxSupplys = wxSupplyService.findWxSupplysByPage(page);
			
			map.put("ok", true);
			map.put("wxSupplys", wxSupplys);
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "加载失败！");
			}
			logger.error("WxSupplySendController.getMoreWxSupplys："+msg);
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
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
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
			Long userId = user.getUserId();
			wxSupplyDto.setUserId(userId);
			String userName = user.getUserName();
			wxSupplyDto.setUserName(userName);
			String userMobile = user.getMobile();
			wxSupplyDto.setUserMobile(userMobile);
			Short statusUndispose = (short) InfoWarehousStateEnum.UNDISPOSE.getStatus();
			wxSupplyDto.setStatus(statusUndispose);
			Short sypplyResourceWx = Short.parseShort(WxSupplyResourceEnum.WX.getCode());
			wxSupplyDto.setSypplyResource(sypplyResourceWx);
			
			wxSupplyService.addWxSupply(wxSupplyDto, wxSupplyPicDto);
			
			map.put("ok", true);
			map.put("msg", "您的发布小珍已收到，稍后小珍审核通过后，您可在“供求信息”栏目中查看，您也可以刷新自己已发布的信息！");
			
			//短信通知运营审核信息
			//Map<String, Object> properties = PropertiesUtils.getProperties("com/jointown/zy/common/properties/config.properties");
			//String mobileNo = (String) properties.get("kefu.sms.moblie");
			String mobileNo = SpringUtil.getConfigProperties("kefu.sms.moblie");
			threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(mobileNo, GetMessageContext.getWxSupplyOrDemandMsg(userName, true)));
			
			//成功后发送邮件
			String subject = WxConstant.EMAIL_SUBJECT_SUPPLY;
			String toEmail = GetEmailContext.getWxEmail();
			String emailMsg = WxConstant.getAddSupplyEmailContent(userName, userMobile);
			threadPoolTaskExecutor.execute(new EmailTaskSend(subject, toEmail, emailMsg));
		}catch(Exception e){
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "发布失败！");
			}
			logger.error("WxSupplySendController.addWxSupply："+msg);
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
	 * @Description: 修改微信供应信息
	 * @Author: wangjunhu
	 * @Date: 2015年5月13日
	 * @param wxSupplyDto
	 * @param wxSupplyPicDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateWxSupply",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateWxSupply(@ModelAttribute WxSupplyDto wxSupplyDto, @ModelAttribute WxSupplyPicDto wxSupplyPicDto) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		SFTPUtil sftp = null;
		Session session = null;
		try{
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			Long supplyId = wxSupplyDto.getSupplyId();
			if(supplyId==null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			//验证信息是否存在
			WxSupply wxSupply = new WxSupply();
			wxSupply.setSupplyId(supplyId);
			Long userId = user.getUserId();
			wxSupply.setUserId(userId);
			WxSupplyVo wxSupplyVo = wxSupplyService.findWxSupplyAndPicById(wxSupply);
			if(wxSupplyVo==null){
				throw new WxErrorException(ErrorRepository.WX_NO_RECORD);
			}
			//验证信息状态是否正确
			short wxSupplyStatus = wxSupplyVo.getStatus();
			short wxSupplyStateWaiting = Short.parseShort(WxSupplyStateEnum.WATING.getCode());
			//待审核
			if(wxSupplyStatus==wxSupplyStateWaiting){
				throw new WxErrorException(ErrorRepository.WX_WAITING_RECORD_NO_UPDATE);
			}
			//验证上传图片编号（取最大值）
			short maxType = 0;
			List<WxSupplyPic> picList = wxSupplyVo.getPicList();
			for (WxSupplyPic wxSupplyPic : picList) {
				short type = wxSupplyPic.getType();
				if(type>maxType){
					maxType=type;
				}
			}
			//下一张图片的序列号
			maxType = (short) (maxType + 1);
			//上传图片到资源服务器
			List<WxSupplyPic> wxSupplyPics = wxSupplyPicDto.getWxSupplyPics();
			int wxSupplyPicsSize = wxSupplyPics.size();
			if(wxSupplyPicsSize > 0){
				sftp = SFTPUtil.getSingleton();
				session = sftp.openSession(sftpConfigInfo.getSftpIpWx(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
				for (int i = 0; i < wxSupplyPicsSize; i++) {
					WxSupplyPic wxSupplyPic = wxSupplyPics.get(i);
					//上传缩略图
					wxSupplyPic.setType((short)(maxType+i*2));
					String resizeWxSupplyPicTempPath = wxSupplyPic.getPicPath();
					String resizeWxSupplyPicPath = uploadWxSupplyPic(sftp,session,resizeWxSupplyPicTempPath);
					wxSupplyPic.setPicPath(resizeWxSupplyPicPath);
					//上传原图
					WxSupplyPic orgignWxSupplyPic = new WxSupplyPic();
					orgignWxSupplyPic.setType((short)(maxType+i*2+1));
					String orgignWxSupplyPicTempPath = resizeWxSupplyPicTempPath.substring(0, resizeWxSupplyPicTempPath.lastIndexOf("_")) + resizeWxSupplyPicTempPath.substring(resizeWxSupplyPicTempPath.lastIndexOf("."));
					String orgignWxSupplyPicPath = uploadWxSupplyPic(sftp,session,orgignWxSupplyPicTempPath);
					orgignWxSupplyPic.setPicPath(orgignWxSupplyPicPath);
					wxSupplyPics.add(orgignWxSupplyPic);
				}
			}
			
			wxSupplyService.updateWxSupply(wxSupplyDto, wxSupplyPicDto);
			
			map.put("ok", true);
			map.put("msg", "更新成功！");
			
			//短信通知运营审核信息
			//Map<String, Object> properties = PropertiesUtils.getProperties("com/jointown/zy/common/properties/config.properties");
			//String mobileNo = (String) properties.get("kefu.sms.moblie");
			String mobileNo = SpringUtil.getConfigProperties("kefu.sms.moblie");
			String userName = user.getUserName();
			threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(mobileNo, GetMessageContext.getWxSupplyOrDemandMsg(userName, true)));
			
			//成功后发送邮件
			String subject = WxConstant.EMAIL_SUBJECT_SUPPLY;
			String toEmail = GetEmailContext.getWxEmail();
			String emailMsg = WxConstant.getUpdateSupplyEmailContent(wxSupplyVo.getBreedName(), userName, user.getMobile());
			threadPoolTaskExecutor.execute(new EmailTaskSend(subject, toEmail, emailMsg));
		}catch(Exception e){
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "更新失败！");
			}
			logger.error("WxSupplySendController.updateWxSupply："+e.getMessage());
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
	 * @Description: 删除微信供应信息图片，ID删除
	 * @Author: wangjunhu
	 * @Date: 2015年5月12日
	 * @param supplyPicId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteWxSupplyPicById",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> deleteWxSupplyPicById(@RequestParam(value="supplyId") Long supplyId,@RequestParam(value="supplyPicIds") String supplyPicIds) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		SFTPUtil sftp = null;
		Session session = null;
		ChannelSftp channel = null;
		try {
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			if(supplyId==null || supplyPicIds==null || supplyPicIds.isEmpty()){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			//验证信息是否存在
			WxSupply wxSupply = new WxSupply();
			wxSupply.setSupplyId(supplyId);
			Long userId = user.getUserId();
			wxSupply.setUserId(userId);
			WxSupplyVo wxSupplyVo = wxSupplyService.findWxSupplyAndPicById(wxSupply);
			if(wxSupplyVo==null){
				throw new WxErrorException(ErrorRepository.WX_NO_RECORD);
			}
			//验证图片数量
			List<WxSupplyPic> wxSupplyPics = wxSupplyVo.getPicList();
			if(wxSupplyPics.size() == 2){
				throw new WxErrorException(ErrorRepository.WX_DELETE_IMG_NUM_ONE);
			}
			//删除图片记录
			wxSupplyService.deleteWxSupplyPicById(supplyPicIds);
			
			map.put("ok", true);
			//删除图片文件
			sftp = SFTPUtil.getSingleton();
			session = sftp.openSession(sftpConfigInfo.getSftpIpWx(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
			channel = sftp.getChannelSftp(session,"sftp");
			String[] supplyPicIdz = supplyPicIds.split("-");
			for (String supplyPicId : supplyPicIdz) {
				for (WxSupplyPic wxSupplyPic : wxSupplyPics) {
					String wxSupplyPicId = wxSupplyPic.getSupplyPicId().toString();
					if(supplyPicId.equals(wxSupplyPicId)){
						String picPath = wxSupplyPic.getPicPath();
						deleteWxSupplyPic(sftp,channel,picPath);
					}
				}
			}
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "删除失败！");
			}
			logger.error("WxSupplySendController.deleteWxSupplyPicById："+msg);
		} finally{
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
	 * @Description: 撤销微信供应信息，ID撤销
	 * @Author: wangjunhu
	 * @Date: 2015年5月13日
	 * @param supplyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteWxSupply",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> deleteWxSupply(@RequestParam(value="supplyId") Long supplyId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			if(supplyId==null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			//验证信息是否存在
			WxSupply wxSupply = new WxSupply();
			wxSupply.setSupplyId(supplyId);
			Long userId = user.getUserId();
			wxSupply.setUserId(userId);
			WxSupplyVo wxSupplyVo = wxSupplyService.findWxSupplyAndPicById(wxSupply);
			if(wxSupplyVo==null){
				throw new WxErrorException(ErrorRepository.WX_NO_RECORD);
			}
			//撤销信息
			short wxSupplyStateDelete = Short.parseShort(WxSupplyStateEnum.DELETE.getCode());
			wxSupply.setStatus(wxSupplyStateDelete);
			
			wxSupplyService.updateWxSupplyById(wxSupply);
			
			map.put("ok", true);
			map.put("msg", "撤销成功！");
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "撤销失败！");
			}
			logger.error("WxSupplySendController.deleteWxSupply："+msg);
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 刷新微信供应信息，ID刷新
	 * @Author: wangjunhu
	 * @Date: 2015年5月13日
	 * @param supplyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/refreshWxSupply",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> refreshWxSupply(@RequestParam(value="supplyId") Long supplyId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			if(supplyId==null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			//验证信息是否存在
			WxSupply wxSupply = new WxSupply();
			wxSupply.setSupplyId(supplyId);
			Long userId = user.getUserId();
			wxSupply.setUserId(userId);
			WxSupplyVo wxSupplyVo = wxSupplyService.findWxSupplyAndPicById(wxSupply);
			if(wxSupplyVo==null){
				throw new WxErrorException(ErrorRepository.WX_NO_RECORD);
			}
			//刷新信息
			wxSupplyService.updateWxSupplyById(wxSupply);
			
			map.put("ok", true);
			map.put("msg", "刷新成功！");
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "刷新失败！");
			}
			logger.error("WxSupplySendController.refreshWxSupply："+msg);
		}
		return map;
	}
	
	/**
	 * 品种名称联想供应信息
	 * @param breedName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getWxSupplyByBreedName",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getWxSupplyByBreedName(@RequestParam("breedName") String breedName) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			if(breedName==null || breedName.isEmpty()){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			WxSupplyBreedVo wxSupply = wxSupplyService.findWxSupplyByBreedName(breedName);
			if(wxSupply==null){
				map.put("ok", false);
			}else{
				map.put("ok", true);
				map.put("obj", wxSupply);
			}
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
//				map.put("msg", "查询失败！");
			}
			logger.error("WxSupplySendController.getWxSupplyByBreedName："+msg);
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
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
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
			logger.error("WxSupplySendController.uploadPic："+msg);
		} finally {
			//关闭管道连接
	        try {
	        	if(sftp!=null){
	        		sftp.closeChannel(channel,session);
	        	}
			} catch (Exception e) {
				String msg = e.getMessage();
				logger.error("WxSupplySendController.uploadPic："+msg);
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
	
	/**
	 * 
	 * @Description: 删除资源服务器图片目录中的图片
	 * @Author: wangjunhu
	 * @Date: 2015年5月28日
	 * @param sftp
	 * @param channel
	 * @param filePath
	 * @throws Exception
	 */
	public void deleteWxSupplyPic(SFTPUtil sftp,ChannelSftp channel,String filePath) throws Exception {
		String[] filePaths = filePath.split("/");
		String deleteFile = filePaths[3];
		String dateDir = filePaths[2];
		sftp.delete(channel, deleteFile, dateDir, sftpConfigInfo.getSftpDataDir(), sftpConfigInfo.getSftpImagesDir(), sftpConfigInfo.getSftpProjectDirWx());
	}
}
