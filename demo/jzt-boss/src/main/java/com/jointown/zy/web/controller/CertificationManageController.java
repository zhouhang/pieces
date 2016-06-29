package com.jointown.zy.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jointown.zy.common.constant.MessageConstant;
import com.jointown.zy.common.dto.CompanyCertifyDto;
import com.jointown.zy.common.dto.MemberCertifySearchDto;
import com.jointown.zy.common.dto.PersonCertifyDto;
import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.enums.CompanyCertifyCheckRemarkEnum;
import com.jointown.zy.common.enums.PersonCertifyCheckRemarkEnum;
import com.jointown.zy.common.exception.ErrorMessage;
import com.jointown.zy.common.model.CertifyImg;
import com.jointown.zy.common.model.CompanyCertify;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcPersonCertify;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.model.UcUserCertify;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.CertifyImgService;
import com.jointown.zy.common.service.CompanyCertifyService;
import com.jointown.zy.common.service.IMemberCertifyService;
import com.jointown.zy.common.service.IMemberManageService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.BeanToMapUtil;
import com.jointown.zy.common.util.ImageHelper;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.util.image.UploadUtils;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.common.wms.WmsApiCommon;

/**
 * 实名认证controller
 * @author ldp
 * date 2015年1月6日
 * Verison 0.0.1
 */
@Controller
@RequestMapping(value="/getCertificationManage")
public class CertificationManageController extends UserBaseController {

	private static final Logger log = LoggerFactory.getLogger(CertificationManageController.class);
	
	@Autowired
	public IMemberCertifyService memberCertifyService;
	@Autowired
	public CompanyCertifyService companyCertifyService;
	@Autowired
	private SftpConfigInfo sftpConfigInfo ;
	@Autowired
	private CertifyImgService certifyImgService;
	@Autowired
	private IMemberManageService memberManageService;
	@Autowired
	private UcUserService ucUserService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="")
	public String getCertificationManage(@ModelAttribute("memCertify") MemberCertifySearchDto memCertify,ModelMap modelMap){
		modelMap.addAttribute("memCertify", new MemberCertifySearchDto());
		modelMap.addAttribute("page", new Page());
		return "public/certificationManage";
	}
	
	/**
	 * 条件查询认证会员
	 * @param memCertifyDto
	 * @return
	 */
	@RequestMapping(value="/searchUcCertifyCondition")
	public String searchUcCertifyByCondition(@ModelAttribute("memCertify") MemberCertifySearchDto memCertify,ModelMap modelMap){
		
		memCertify.setPageSize(10);
		
		Page<UcUserCertify> page = new Page<UcUserCertify>();
		page.setPageSize(memCertify.getPageSize());
		page.setPageNo(memCertify.getPageNo());
		page.setParams(BeanToMapUtil.getOriginalParameters(memCertify));
		List<UcUserCertify> ucCertifyList = memberCertifyService.getCertifyUcUsersByCondition(page);
		page.setResults(ucCertifyList);
		modelMap.addAttribute("page", page);
		return "public/certificationManage";
	}
	
	
	/**
	 * 根据会员认证Id获取认证会员信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getCertifyUcUserInfo")
	public String getCertifyUcUserInfo(HttpServletRequest request,ModelMap modelMap){
		String param = request.getParameter("param");
		String[] params = param.split("-"); 
		//掳走查询条件
		String submitStartDate = request.getParameter("submitStartDate");
		String submitEndDate = request.getParameter("submitEndDate");
		String userName = request.getParameter("userName");
		String mobileNo = request.getParameter("mobileNo");
		String realName = request.getParameter("realName");
		String certifyType = request.getParameter("certifyType");
		String userType = request.getParameter("userType");
		modelMap.addAttribute("submitStartDate", submitStartDate);
		modelMap.addAttribute("submitEndDate", submitEndDate);
		modelMap.addAttribute("userName", userName);
		modelMap.addAttribute("mobileNo", mobileNo);
		modelMap.addAttribute("realName", realName);
		modelMap.addAttribute("certifyType", certifyType);
		modelMap.addAttribute("userType", userType);
		
		String urlForward = null;
		if ("0".equals(params[1])) {//个人认证页面
			UcPersonCertify ucPCertify = memberCertifyService.getCertifyUcUserInfoByCertifyId(params[0]);
			modelMap.addAttribute("ucPCertify", ucPCertify);
			modelMap.addAttribute("checkRemark", PersonCertifyCheckRemarkEnum.toMap());
			//获取图片
			List<CertifyImg> imglist = certifyImgService.findCertifyImgByCertifyId(Integer.parseInt(params[0]));
			if (null != imglist && imglist.size() != 0 ) {//判断获取图片是否为空
				for (CertifyImg certifyImg : imglist) {
					if (null == certifyImg.getType()) {
						continue;
					}
					if(certifyImg.getType()==2){
						modelMap.addAttribute("picPath",certifyImg.getPath());
					}
					if(certifyImg.getType()==5){
						modelMap.addAttribute("picPath1",certifyImg.getPath());
					}
				}
			}
			urlForward = "public/personCertify";
		}else if ("1".equals(params[1])) {//企业认证页面
			CompanyCertify companyCertify = companyCertifyService.findCompanyCertifyByCertifyId(Integer.parseInt(params[0]));
			modelMap.addAttribute("ucComCertify", companyCertify);
			modelMap.addAttribute("checkRemark", CompanyCertifyCheckRemarkEnum.toMap());
			//获取图片
			List<CertifyImg> imglist = certifyImgService.findCertifyImgByCertifyId(Integer.parseInt(params[0]));
			if (null != imglist && imglist.size() != 0 ) {//判断获取图片是否为空
				for (CertifyImg certifyImg : imglist) {
					if (null == certifyImg.getType()) {
						continue;
					}
					if(certifyImg.getType()==8){
						modelMap.addAttribute("picPath",certifyImg.getPath());
					}
					if(certifyImg.getType()==11){
						modelMap.addAttribute("picPath1",certifyImg.getPath());
					}
				}
			}
			urlForward = "public/enterpriseCertify";
		}
		
		return urlForward;
		
	}
	
	/**
	 * 个人认证
	 * @param pcDto
	 * @return
	 */
	@RequestMapping(value = "/personCertifyIsPass")
	@ResponseBody
	public MessageVo personCertifyIsPass(@ModelAttribute PersonCertifyDto pcDto){
		MessageVo mvo = new MessageVo();
		List<ErrorMessage> errorList = new ArrayList<ErrorMessage>();
		errorList = pcDto.validate();
		if (errorList != null && !errorList.isEmpty()) {
			return mvo.setErrorMessages(errorList);
		}
		/*if ((null == pcDto.getPicPath() || "".equals(pcDto.getPicPath())) && ( null != pcDto.getPicPath1() && !"".equals(pcDto.getPicPath1()))) {
			errorList.add(new ErrorMessage(ErrorRepository.UC_PERSON_CERTIFY_IDCARD_ZM_NOT_NULL));
			return mvo.setErrorMessages(errorList);
		}
		if ((null == pcDto.getPicPath1() || "".equals(pcDto.getPicPath1())) && ( null != pcDto.getPicPath() && !"".equals(pcDto.getPicPath()))) {
			errorList.add(new ErrorMessage(ErrorRepository.UC_PERSON_CERTIFY_IDCARD_FM_NOT_NULL));
			return mvo.setErrorMessages(errorList);
		}*/
		//图片实际路径处理
		String picPath = pcDto.getPicPath();
		String picPath1 = pcDto.getPicPath1();
		String imgDir = null;
		imgDir = getImgDir(picPath, picPath1);
		/********sftp连接 alter by Mr.song 2015.1.28 15:27 start **********/
		SFTPUtil sftp = SFTPUtil.getSingleton();
		Session session =null;
		if (null != imgDir) {
			try {
				session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
				//保存图片
				savePersonPic(sftp,session,pcDto);
				if (session != null) {
					if (session.isConnected()) {
						session.disconnect();
					} else{
						log.info("session is closed already");
					}
				}  //操作完毕关闭管道连接
			}catch (Exception e1) {
				log.error("save pic error:", e1);
			}
		}
		/********sftp连接 alter by Mr.song 2015.1.28 15:27 end **********/
		Subject subject = SecurityUtils.getSubject();
		BossUserVo bossUserVo = (BossUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		int certFlag = memberCertifyService.updatePersonCertify(pcDto,bossUserVo,imgDir);
		if (certFlag == 1) {
			log.info("操作成功!");
			mvo.setOk(true);
			/*********wms接口嵌入开始  author ldp ***********/
			if ("1".equals(pcDto.getIsPass())) {
				UcUser ucUser = memberManageService.findMemberByUserID(pcDto.getUserId());
				//add by fanyuna 2015.08.07 同步至WMS系统的名称为实名认证的名称
				String name = ucUserService.getCertifyNameByUserId(ucUser.getUserId());
				String json = WmsApiCommon.ucUserToJsonStr(ucUser,name);
				RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.UC_USER_ADD, json);
			}
			/*********wms接口嵌入结束***********/
		}
		return mvo;
	}
	
	/**
	 * 认证通过后，会员信息资料保存
	 * @param pcDto
	 * @return
	 */
	@RequestMapping(value="/personCertifyInfoSave")
	@ResponseBody
	public MessageVo personCertifyInfoSave(@ModelAttribute PersonCertifyDto pcDto){
		MessageVo mvo = new MessageVo();
		List<ErrorMessage> errorList = new ArrayList<ErrorMessage>();
		errorList = pcDto.validate();
		if (errorList != null && !errorList.isEmpty()) {
			return mvo.setErrorMessages(errorList);
		}
		/*if ((null == pcDto.getPicPath() || "".equals(pcDto.getPicPath())) && ( null != pcDto.getPicPath1() && !"".equals(pcDto.getPicPath1()))) {
			errorList.add(new ErrorMessage(ErrorRepository.UC_PERSON_CERTIFY_IDCARD_ZM_NOT_NULL));
			return mvo.setErrorMessages(errorList);
		}
		if ((null == pcDto.getPicPath1() || "".equals(pcDto.getPicPath1())) && ( null != pcDto.getPicPath() && !"".equals(pcDto.getPicPath()))) {
			errorList.add(new ErrorMessage(ErrorRepository.UC_PERSON_CERTIFY_IDCARD_FM_NOT_NULL));
			return mvo.setErrorMessages(errorList);
		}*/
		//图片实际路径处理
		String picPath = pcDto.getPicPath();
		String picPath1 = pcDto.getPicPath1();
		String imgDir = getImgDir(picPath, picPath1);
		/********sftp连接 alter by Mr.song 2015.1.28 15:27 start **********/
		SFTPUtil sftp = SFTPUtil.getSingleton();
		Session session =null;
		if (null != imgDir) {
			try {
				session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
				//保存图片
				savePersonPic(sftp,session,pcDto);
				if (session != null) {
					if (session.isConnected()) {
						session.disconnect();
					} else{
						log.info("session is closed already");
					}
				}  //操作完毕关闭管道连接
			}catch (Exception e1) {
				log.error("save pic error:", e1);
			}
		}
		/********sftp连接 alter by Mr.song 2015.1.28 15:27 end **********/
		
		Subject subject = SecurityUtils.getSubject();
		BossUserVo bossUserVo = (BossUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		int saveFlag = memberCertifyService.updatePersonCertifyInfoPassAfter(pcDto,bossUserVo,imgDir);
		if (saveFlag == 1) {
			log.info("保存成功");
			mvo.setOk(true);
			//add by fanyuna 2015.11.11 保存成功后需要用户信息同步至wms （主要是针对认证名称）
			UcUser ucUser = memberManageService.findMemberByUserID(pcDto.getUserId());
			String json = WmsApiCommon.ucUserToJsonStr(ucUser,pcDto.getName());
			RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.UC_USER_UPDATE, json);
		}
		return mvo;
	}
	
	/**
	 * 企业认证
	 * @param comCertifyDao
	 * @return
	 */
	@RequestMapping(value="/companyCertfyIsPass")
	@ResponseBody
	public MessageVo companyCertfyIsPass(@ModelAttribute CompanyCertifyDto comCertifyDto){
		MessageVo mvo = new MessageVo();
		//图片实际路径处理
		String picPath = comCertifyDto.getPicPath();
		String picPath1 = comCertifyDto.getPicPath1();
		String imgDir = null;
		imgDir = getImgDir(picPath, picPath1);
		/********sftp连接 alter by Mr.song 2015.1.28 15:27 start **********/
		SFTPUtil sftp = SFTPUtil.getSingleton();
		Session session =null;
		if (null != imgDir) {
			try {
				session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
				//保存图片
				save(sftp,session,comCertifyDto);
				if (session != null) {
					if (session.isConnected()) {
						session.disconnect();
					} else{
						log.info("session is closed already");
					}
				}  //操作完毕关闭管道连接
			}catch (Exception e1) {
				log.error("save pic error:", e1);
			}
		}
		/********sftp连接 alter by Mr.song 2015.1.28 15:27 end **********/
		//获取审核之前的认证状态，如果已通过个人认证，那么升级成企业认证时，调用户修改接口
		UcUser ucUserBeforeWmsApi = memberManageService.findMemberByUserID(String.valueOf(comCertifyDto.getUserId()));
		
		Subject subject = SecurityUtils.getSubject();
		BossUserVo bossUserVo = (BossUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		int comCertFlag = companyCertifyService.updateCompanyCertify(comCertifyDto, bossUserVo,imgDir);
		if (comCertFlag == 1) {
			log.info("操作成功!");
			mvo.setOk(true);
			/*********wms接口嵌入开始  author ldp ***********/
			if ("1".equals(String.valueOf(comCertifyDto.getStatus()))) {
				UcUser ucUser = memberManageService.findMemberByUserID(String.valueOf(comCertifyDto.getUserId()));
				//add by fanyuna 2015.08.10 同步至WMS系统的名称为实名认证的名称
				String name = ucUserService.getCertifyNameByUserId(ucUser.getUserId());
				String json = WmsApiCommon.ucUserToJsonStr(ucUser,name);
//				String json = WmsApiCommon.ucUserToJsonStr(ucUser);
				
				if ("1".equals(String.valueOf(ucUserBeforeWmsApi.getCertifyStatus()))) {//如果已通过个人认证，那么升级成企业认证时，调用户修改接口
					RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.UC_USER_UPDATE, json);
				}else{
					RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.UC_USER_ADD, json);
				}
			}
		   /*********wms接口嵌入结束*****************************/
		}
		return mvo;
	}
	
	/**
	 * 企业认证通过后，保存资料
	 * @param comCertifyDao
	 * @return
	 */
	@RequestMapping(value="/comCertifyInfoSave")
	@ResponseBody
	public MessageVo comCertifyInfoSave(@ModelAttribute CompanyCertifyDto comCertifyDto){
		MessageVo mvo = new MessageVo();
		//图片实际路径处理
		String picPath = comCertifyDto.getPicPath();
		String picPath1 = comCertifyDto.getPicPath1();
		String imgDir = null;
		imgDir = getImgDir(picPath, picPath1);
		/********sftp连接 alter by Mr.song 2015.1.28 15:27 start **********/
		SFTPUtil sftp = SFTPUtil.getSingleton();
		Session session =null;
		if (null != imgDir) {
			try {
				session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
				//保存图片
				save(sftp,session,comCertifyDto);
				if (session != null) {
					if (session.isConnected()) {
						session.disconnect();
					} else{
						log.info("session is closed already");
					}
				}  //操作完毕关闭管道连接
			}catch (Exception e1) {
				log.error("save pic error:", e1);
			}
		}
		/********sftp连接 alter by Mr.song 2015.1.28 15:27 end **********/
		Subject subject = SecurityUtils.getSubject();
		BossUserVo bossUserVo = (BossUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		int comSaveFlag = companyCertifyService.updateCompanyCertifyInfoAfterPass(comCertifyDto, bossUserVo,imgDir);
		if (comSaveFlag == 1) {
			log.info("操作成功!");
			mvo.setOk(true);
			
			//add by fanyuna 2015.11.11 保存成功后需要用户信息同步至wms （主要是针对认证名称）
			UcUser ucUser = memberManageService.findMemberByUserID(String.valueOf(comCertifyDto.getUserId()));
			String json = WmsApiCommon.ucUserToJsonStr(ucUser,comCertifyDto.getCompanyName());
			RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.UC_USER_UPDATE, json);
		}
		return mvo;
	}
	
	
	
	/**
	 * 上传图片(临时文件夹)
	 * 
	 * @author zhouji
	 * @param companyCertifyDto
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws ServletException 
	 */
	@RequestMapping(value = "uploadImg")
	@ResponseBody
	public void uploadImg(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model){
		Integer type = Integer.parseInt(request.getParameter("type"));
		Long userId = Long.parseLong(request.getParameter("userId"));
		ChannelSftp channel = null;               //sftp管道声明
		Session session = null;  //session声明
		SFTPUtil sftp = SFTPUtil.getSingleton();  //sftp初始化
		try {
			String res = "";
			if (null == file) {
				res = "{status:{code:-1,desc:''}}";
				return;
			}
			//企业图片类型
			if(type==6||type==9){
				if(file.getSize()>4*1024*1024){
					res = "{status:{code:1,desc:''}}";
					writeHtml(request, response, res);
					return;
				}
			}else{
				if(file.getSize()>4*1024*1024){
					res = "{status:{code:1,desc:''}}";
					writeHtml(request, response, res);
					return;
				}
			}		
			// String fileOriginName = file.getOriginalFilename();
			// 文件类型是否正确
			boolean isImage = UploadUtils.isImage(file.getInputStream());
			if (!isImage) {
				res = "{status:{code:-1,desc:''}}";
				return;
			}
			String dateDir = TimeUtil.getTimeShowByTimePartten(new Date(), "yyyyMMdd");
			/*Subject subject = SecurityUtils.getSubject();
			String userName = (String) subject.getPrincipal();
			UcUser user = ucUserService.findUcUserByUserName(userName);*/
			//Integer type = Integer.parseInt(request.getParameter("type"));
			String fileName = createFileName(userId, type, "jpg");
			try {
				session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
				channel = sftp.getChannelSftp(session,"sftp");  //获取管道符

				//上传原图片到资源服务器临时目录下
				sftp.upload(channel, fileName, file.getInputStream(), 
						dateDir,sftpConfigInfo.getSftpDataDir(),
						sftpConfigInfo.getSftpImagesDir(),
						sftpConfigInfo.getSftpTempProjectDir());
	
				//获取图片存放地址,正式,测试环境在服务器上,本地请自己设定盘符(E://temp/)
				String root = MessageConstant.IMG_PATH.getValue();
				//判断图片大小,小于10240kspring组件会将图片转换成ByteArrayInputStream,大于则转换为FileInputStream
				if(file.getSize()>10240){
					FileInputStream in = (FileInputStream) file.getInputStream();
					BufferedInputStream bin = new BufferedInputStream(in);
					BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(root+fileName));
					int i;
					while((i=bin.read())!=-1){
						out.write(i);
					}
					out.flush();
					out.close();
					bin.close();
				}else{
					ByteArrayInputStream bain = (ByteArrayInputStream) file.getInputStream();
					BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(root+fileName));
					int i;
					while((i=bain.read())!=-1){
						out.write(i);
					}
					out.flush();
					out.close();
					bain.close();
				}
				//给图片加上水印
				ImageHelper.markImageByText("图片仅用于珍药材网", root+fileName);
				File watermark = new File(root+fileName);
				InputStream small = new FileInputStream(watermark);
				
				// 上传缩略图
				String msg = uploadThumbSmall(channel, small, userId, type,dateDir);
				InputStream big = new FileInputStream(watermark);
				msg = msg+uploadThumbBig(channel, big, userId, type,dateDir);
	
				res = "{status:{code:0,desc:''},con:[{filename:'" + fileName
						+ "',type:'" + type + "',path:'"
						+ sftpConfigInfo.getSftpTempPath() +dateDir+"/"+fileName+ "'},"+msg+"]}";
				watermark.delete();
			} catch (Exception e) {
				if (e instanceof SizeLimitExceededException) {
					System.out.println("图片大小超过5M");
				}
				e.printStackTrace();
				log.error("上传图片失败！" + e.getMessage(), e);
				res = "{status:{code:-1,desc:''}}";
			}
			setNoCache(response);
			try {
				writeHtml(request, response, res);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			log.error("error is:",e);
		}finally{
			//关闭管道连接
	        try {
				sftp.closeChannel(channel,session);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 上传缩略图
	 * @author zhouji
	 * @param sftpChannel
	 * @param file
	 * @param userId
	 * @param type
	 * @param dateDir
	 * @return
	 * @throws SftpException
	 * @throws IOException
	 */
	public String uploadThumbSmall(ChannelSftp sftpChannel,InputStream is, Long userId,Integer type,String dateDir) throws SftpException, IOException {
		String msg ="";
		int width = sftpConfigInfo.getSftpSmallWidth();
		String x = createFileName(userId, type+1, "jpg");
		OutputStream thumbOutstream = sftpChannel.put(x);
		ImageHelper.scaleImage(is, thumbOutstream,
				width, width);
		msg="{filename:'" + x
			+ "',type:'" + (type+1) + "',path:'"
			+ sftpConfigInfo.getSftpTempPath() +dateDir+"/"+ x +  "'}";
		return msg;
	}
	public String uploadThumbBig(ChannelSftp sftpChannel,InputStream is, Long userId,Integer type,String dateDir) throws SftpException, IOException {
		String msg ="";
		int width1 = sftpConfigInfo.getSftpXXXBigWidth();
		String y = createFileName(userId, type+2, "jpg");
		OutputStream thumbOutstream1 = sftpChannel.put(y);
		ImageHelper.scaleImage(is, thumbOutstream1,
				width1, width1);
		msg = ",{filename:'" + y
				+ "',type:'" + (type+2) + "',path:'"
				+ sftpConfigInfo.getSftpTempPath() +dateDir+"/"+ y +  "'}";
		return msg;
	}
	/**
	 * 保存企业认证图片(将图片从零时文件夹移到正式文件夹)
	 * @author zhouji
	 * @param request
	 * @throws Exception
	 */
	public void save(SFTPUtil sftp,Session session,CompanyCertifyDto companyCertifyDto) throws Exception {
		String picPath =companyCertifyDto.getPicPath();
		String picSmallPath =companyCertifyDto.getPicSmallPath();
		String picBigPath =companyCertifyDto.getPicBigPath();
		
		String picPath1 =companyCertifyDto.getPicPath1();
		String picSmallPath1 =companyCertifyDto.getPicSmallPath1();
		String picBigPath1 =companyCertifyDto.getPicBigPath1();
		
		if(null != picPath && !"".equals(picPath)) {
			saveFile(sftp,session,picPath);
			saveFile(sftp,session,picSmallPath);
			saveFile(sftp,session,picBigPath);
		}
		if(null != picPath1 && !"".equals(picPath1)){
			saveFile(sftp,session,picPath1);
			saveFile(sftp,session,picSmallPath1);
			saveFile(sftp,session,picBigPath1);
		}
	}
	
	/**
	 * 保存个人认证图片
	 * @author ldp
	 * @param personCertifyDto
	 * @throws Exception
	 */
	public void savePersonPic(SFTPUtil sftp,Session session,PersonCertifyDto personCertifyDto) throws Exception {
		String picPath =personCertifyDto.getPicPath();
		String picSmallPath =personCertifyDto.getPicSmallPath();
		String picBigPath =personCertifyDto.getPicBigPath();
		
		String picPath1 =personCertifyDto.getPicPath1();
		String picSmallPath1 =personCertifyDto.getPicSmallPath1();
		String picBigPath1 =personCertifyDto.getPicBigPath1();
		
		if (null != picPath && !"".equals(picPath)) {
			saveFile(sftp,session,picPath);
			saveFile(sftp,session,picSmallPath);
			saveFile(sftp,session,picBigPath);
		}
		if(null != picPath1 && !"".equals(picPath1)){
			saveFile(sftp,session,picPath1);
			saveFile(sftp,session,picSmallPath1);
			saveFile(sftp,session,picBigPath1);
		}
	}
	
	/**
	 * 保存文件
	 * @param picPath
	 * @param picName
	 * @throws Exception
	 */
	public void saveFile(SFTPUtil sftp,Session session,String picPath) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("imgpath", picPath);
		map.put("dataDir", sftpConfigInfo.getSftpDataDir());
		map.put("imagesDir", sftpConfigInfo.getSftpImagesDir());
		map.put("projectDir", sftpConfigInfo.getSftpProjectDir());
		map.put("tempProjectDir", sftpConfigInfo.getSftpTempProjectDir());
		sftp.moveImg(session, map);
	}
	
	/**
	 * 创建文件名
	 * @author zhouji
	 * @param userId
	 * @param type
	 * @param suffix
	 * @return
	 */
	public String createFileName(Long userId, Integer type, String suffix) {
		StringBuffer fileName = new StringBuffer();
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
		fileName.append("c_");
		fileName.append(userId + "_");
		fileName.append(dateStr + "_");
		fileName.append(type + ".");
		fileName.append(suffix);
		return fileName.toString();
	}
	
	/**
	 * 获取实际文件路径
	 * @author ldp
	 * @param picPath
	 * @param picPath1
	 * @return
	 */
	public String getImgDir(String picPath,String picPath1){
		String imgDir = null;
		if ((null != picPath && !"".equals(picPath))) {
			String b = picPath.substring(0,picPath.lastIndexOf("/"));
			String dateDir = b.substring(b.lastIndexOf("/")+1);
			imgDir = sftpConfigInfo.getSftpPath()+dateDir+"/";
			if (null != imgDir) {
				return imgDir;
			}
		}		
		if ((null != picPath1 && !"".equals(picPath1))) {
			String b = picPath1.substring(0,picPath1.lastIndexOf("/"));
			String dateDir = b.substring(b.lastIndexOf("/")+1);
			imgDir = sftpConfigInfo.getSftpPath()+dateDir+"/";
		}
		return imgDir;
	}
	
}
