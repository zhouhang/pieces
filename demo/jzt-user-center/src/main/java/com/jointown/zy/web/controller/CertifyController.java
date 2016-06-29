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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jointown.zy.common.constant.MessageConstant;
import com.jointown.zy.common.dto.CompanyCertifyDto;
import com.jointown.zy.common.dto.PersonCertifyDto;
import com.jointown.zy.common.enums.CompanyCertifyCheckRemarkEnum;
import com.jointown.zy.common.enums.PersonCertifyCheckRemarkEnum;
import com.jointown.zy.common.mail.SimpleMailSender;
import com.jointown.zy.common.model.CertifyImg;
import com.jointown.zy.common.model.CompanyCertify;
import com.jointown.zy.common.model.UcPersonCertify;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.CertifyImgService;
import com.jointown.zy.common.service.CompanyCertifyService;
import com.jointown.zy.common.service.IMemberCertifyService;
import com.jointown.zy.common.service.IMemberHomeService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.ImageHelper;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.util.StringTransferSymbolUtil;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.util.image.UploadUtils;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * 认证信息操作
 * 
 * @author zhouji
 *
 *         2015年1月5日
 */
@Controller(value = "certifyController")
public class CertifyController extends UserBaseController {
	private static final Logger log = LoggerFactory.getLogger(CertifyController.class);
	
	@Autowired
	public IMemberCertifyService memberCertifyService;
	@Autowired
	private CompanyCertifyService certifyService;
	@Autowired
	private SftpConfigInfo sftpConfigInfo ;
	@Autowired
	private UcUserService ucUserService;
	@Autowired
	public IMemberHomeService memberHomeService;
	@Autowired
	public CompanyCertifyService companyCertifyService;
	@Autowired
	public CertifyImgService certifyImgService;
	//spring的线程池,用来发送邮件(chengchang add 2015/03/19)
	@Autowired
	public ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private SimpleMailSender simpleMailSender;
	/**
	 * 跳转到认证首页
	 * @author zhouji
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getLegalize")
	public String getLegalize(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap) throws Exception {
    	//从session中取出会员信息（头信息，菜单信息）
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		//header登录名称使用
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		//left菜单选中使用
		String url=request.getRequestURI();
		userinfo.setUrl(url);
		modelMap.addAttribute("userinfo", userinfo);
		//取到用户认证信息的状态
    	UcUser ucuser = ucUserService.findUcUserByUserName(userName);
    	CompanyCertify companyCertify = certifyService.findCompanyCertifyByUserId1(Integer.parseInt(ucuser.getUserId().toString()));
    	UcPersonCertify ucPersonCertify = memberCertifyService.getCertifyUcUserInfoByUserId1(ucuser.getUserId().toString());
    	if(ucPersonCertify!=null){
    		modelMap.put("userStatus", ucPersonCertify.getStatus());
    		modelMap.put("ucPersonCertify", ucPersonCertify);
    	}
    	if(companyCertify!=null){
    		modelMap.put("companyStatus", companyCertify.getStatus());
    		modelMap.put("companyCertify", companyCertify);
    	}
    	modelMap.addAttribute("personCheckRemark", PersonCertifyCheckRemarkEnum.toMap());
    	modelMap.addAttribute("companyCheckRemark", CompanyCertifyCheckRemarkEnum.toMap());
		return "/certify/legalize";
	}
	/**
	 * 跳转到企业认证页面
	 * 
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getLegalizeCompany")
	public String getLegalizeCompany(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap) throws Exception {
		//从session中取出会员信息（头信息，菜单信息）
		//header登录名称使用
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		//left菜单选中使用
		String url=request.getRequestURI();
		userinfo.setUrl(url);
		modelMap.addAttribute("userinfo", userinfo);
		String certifyId = request.getParameter("certifyId");
		if(certifyId!=null){
			CompanyCertify companyCertify = certifyService.findCompanyCertifyByCertifyId(Integer.parseInt(certifyId));
			List<CertifyImg> imglist = certifyImgService.findCertifyImgByCertifyId(Integer.parseInt(certifyId));
			CompanyCertifyDto companyCertifyDto = new CompanyCertifyDto();
			companyCertifyDto.setPicName(imglist.get(0).getPath().substring(imglist.get(0).getPath().lastIndexOf("/")+1));
			companyCertifyDto.setPicPath(imglist.get(0).getPath());
			companyCertifyDto.setPicType(imglist.get(0).getType());
			companyCertifyDto.setPicNameSmall(imglist.get(1).getPath().substring(imglist.get(1).getPath().lastIndexOf("/")+1));
			companyCertifyDto.setPicSmallPath(imglist.get(1).getPath());
			companyCertifyDto.setPicSmallType(imglist.get(1).getType());
			companyCertifyDto.setPicNameBig(imglist.get(2).getPath().substring(imglist.get(2).getPath().lastIndexOf("/")+1));
			companyCertifyDto.setPicBigPath(imglist.get(2).getPath());
			companyCertifyDto.setPicBigType(imglist.get(2).getType());
			if(imglist.size()>3){
				companyCertifyDto.setPicName1(imglist.get(3).getPath().substring(imglist.get(3).getPath().lastIndexOf("/")+1));
				companyCertifyDto.setPicPath1(imglist.get(3).getPath());
				companyCertifyDto.setPicType1(imglist.get(3).getType());
				companyCertifyDto.setPicNameSmall1(imglist.get(4).getPath().substring(imglist.get(4).getPath().lastIndexOf("/")+1));
				companyCertifyDto.setPicSmallPath1(imglist.get(4).getPath());
				companyCertifyDto.setPicSmallType1(imglist.get(4).getType());
				companyCertifyDto.setPicNameBig1(imglist.get(5).getPath().substring(imglist.get(5).getPath().lastIndexOf("/")+1));
				companyCertifyDto.setPicBigPath1(imglist.get(5).getPath());
				companyCertifyDto.setPicBigType1(imglist.get(5).getType());
			}
			modelMap.addAttribute("companyCertify",companyCertify);
			modelMap.addAttribute("companyCertifyDto",companyCertifyDto);
			return "/certify/reLegalizeCompany";
		}else{
			return "/certify/legalizeCompany";
		}
	}

	/**
	 * 跳转到个人认证页面
	 * @author ldp
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/getLegalizePerson")
	public String getPersonCertifyApply(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		//header登录名称使用
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		//left菜单选中使用
		String url=request.getRequestURI();
		userinfo.setUrl(url);
		modelMap.addAttribute("userinfo", userinfo);
		
		String certifyId = request.getParameter("certifyId");
		if (null != certifyId) {
			UcPersonCertify ucPersonCertify = memberCertifyService.getCertifyUcUserInfoByCertifyId(certifyId);
			modelMap.addAttribute("ucPersonCertify", ucPersonCertify);
			//获取图片
			List<CertifyImg> imglist = certifyImgService.findCertifyImgByCertifyId(Integer.parseInt(certifyId));
			if (null != imglist && imglist.size() != 0) {
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
			return "/certify/rePersonCertifyApply";
		}
		return "/certify/personCertifyApply";
	}
	
	/**
	 * 提交企业认证
	 * 
	 * @author zhouji
	 * @param companyCertifyDto
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "addCompanyCertify")
	@ResponseBody
	public String addCompanyCertify(
			@ModelAttribute CompanyCertifyDto companyCertifyDto,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		JsonObject j = new JsonObject();
		Gson gson = new Gson();
		// 验证
		// 插入信息
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		UcUser user = ucUserService.findUcUserByUserName(userName);
		companyCertifyDto.setUserId(user.getUserId());
		/*********added by biran 20150715 会员提交认证前增加校验***********************************/
		int userId=user.getUserId().intValue();
		String errorCode=checkCertyBeforeSubmit(1,String.valueOf(userId));
		if(errorCode!=null&&!errorCode.equals("")){//校验不通过
			j.addProperty("status", errorCode);
			return gson.toJson(j);
		}
		/*********added end***********************************/
		//String v = sftpConfigInfo.getSftpProjectDir();
		String picPath = companyCertifyDto.getPicPath();
		String b = picPath.substring(0,picPath.lastIndexOf("/"));
		String dateDir = b.substring(b.lastIndexOf("/")+1);
		String imgDir = sftpConfigInfo.getSftpPath()+dateDir+"/";
		Session session = null;  //session声明
		SFTPUtil sftp = SFTPUtil.getSingleton();  //sftp初始化
		try {
			//获取上传sftp通道session
			session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
			int flag =certifyService.addCompanyCertify(companyCertifyDto,imgDir);
			if (flag == 1) {
				log.info("添加成功!");
				//mVo.setOk(true);
	 			save(sftp, session,companyCertifyDto);
				j.addProperty("status", "yes");
				/********chengchang 添加认证信息提交成功后，发送邮件通知给客服 start **********/
				//获取发邮件的主题内容
				//String emailMsg=GetEmailContext.getMemberCertifyCompanyText(companyCertifyDto.getCompanyName());
				//taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_MEMBER_CERTIFY_COMPANY, ConfigConstant.EMAIL_TO_KEFU,emailMsg));
				Map <String,Object> map= new HashMap<String,Object >();
				map.put("name", companyCertifyDto.getCompanyName());
	//			simpleMailSender.sendTemplateMail(map, "companyCertifyEmail.ftl", GetEmailContext.EMAIL_MEMBER_CERTIFY_COMPANY);
				simpleMailSender.setMap(map);
				simpleMailSender.setTemplateName("companyCertifyEmail.ftl");
				simpleMailSender.setSubject(GetEmailContext.EMAIL_MEMBER_CERTIFY_COMPANY);
				simpleMailSender.setToEmail(SpringUtil.getConfigProperties().get("kefu.hy.email.address").toString());
				taskExecutor.execute(simpleMailSender);
				log.info("email msg send...");
				/********chengchang 添加认证信息提交成功后，发送邮件通知给客服 end **********/
			}else if(flag==3){
				log.info("添加失败!");
				j.addProperty("status", "double");
			}
		} catch (Exception e) {
			j.addProperty("status", "no");
			e.printStackTrace();
		} finally{
			if (session != null) {
				if (session.isConnected()) {
					session.disconnect();
				} else{
					log.info("session is closed already");
				}
			}  //操作完毕关闭管道连接
			// 加以下语句：页面中文没有乱码
			response.setCharacterEncoding("utf-8");
			String json = gson.toJson(j);
			return json;
		}
	}
	
	/**
	 * 提交个人认证
	 * @author ldp
	 * @param pCertifyApplyDto
	 * @return
	 * @throws MessagingException 
	 */
	@RequestMapping(value="/submitPersonCertify")
	@ResponseBody
	public String addPersonCertify(@ModelAttribute PersonCertifyDto pCertifyApplyDto,ModelMap modelMap,HttpServletResponse response) {
		JsonObject json = new JsonObject();
		Gson gson = new Gson();
		// 加以下语句：页面中文没有乱码
		response.setCharacterEncoding("utf-8");
		String jsonStr = null;
		Subject subject = SecurityUtils.getSubject();
		UcUserVo ucUserVo = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		pCertifyApplyDto.setUserId(String.valueOf(ucUserVo.getUserId()));
		//MessageVo mVo = new MessageVo();
		if ("0".equals(pCertifyApplyDto.getIsPass())) {//如果第一次提交判断图片是否为空
			if ("".equals(pCertifyApplyDto.getPicPath()) || null == pCertifyApplyDto.getPicPath()) {
				json.addProperty("status", "code001");//身份证正面不能为空
				return gson.toJson(json);
			}
			
			if ("".equals(pCertifyApplyDto.getPicPath1()) || null == pCertifyApplyDto.getPicPath1()) {
				json.addProperty("status", "code002");//身份证反面不能为空
				return gson.toJson(json);
			}
		}
		/*********added by biran 20150702 会员提交认证前增加校验***********************************/
		String errorCode=checkCertyBeforeSubmit(0,pCertifyApplyDto.getUserId());
		if(errorCode!=null&&!errorCode.equals("")){//校验不通过
			json.addProperty("status", errorCode);
			return gson.toJson(json);
		}
		/*********added end***********************************/
		pCertifyApplyDto.setUserId(String.valueOf(ucUserVo.getUserId()));
		//图片实际路径处理
		String picPath = pCertifyApplyDto.getPicPath();
		String picPath1 = pCertifyApplyDto.getPicPath1();
		String imgDir = getImgDir(picPath, picPath1);
		/********sftp连接 alter by Mr.song 2015.1.28 15:27 start **********/
		SFTPUtil sftp = SFTPUtil.getSingleton();
		Session session =null;
		if (null != imgDir) {
			try {
				session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
				//保存图片
				savePersonPic(sftp,session,pCertifyApplyDto);
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
		int flag = memberCertifyService.addPersonCertifyInfo(pCertifyApplyDto,imgDir);
		if (flag == 1) {
			log.info("添加成功!");
			//mVo.setOk(true);
			json.addProperty("status", "yes");
			/********chengchang 添加认证信息提交成功后，发送邮件通知给客服 start **********/
			//获取发邮件的主题内容
			//String emailMsg=GetEmailContext.getMemberCertifyPersonText( pCertifyApplyDto.getName());
			//taskExecutor.execute(new EmailTaskSend(, ConfigConstant.EMAIL_TO_KEFU,emailMsg));
			Map <String, Object> map = new HashMap <String,Object >();
			String name=pCertifyApplyDto.getName();
			map.put("name", name);
//			simpleMailSender.sendTemplateMail(map, "personCertifyEmail.ftl", GetEmailContext.EMAIL_MEMBER_CERTIFY_PRESON);
			simpleMailSender.setMap(map);
			simpleMailSender.setTemplateName("personCertifyEmail.ftl");
			simpleMailSender.setSubject(GetEmailContext.EMAIL_MEMBER_CERTIFY_PRESON);
			simpleMailSender.setToEmail(SpringUtil.getConfigProperties().get("kefu.hy.email.address").toString());
			taskExecutor.execute(simpleMailSender);
			log.info("email msg send...");
			/********chengchang 添加认证信息提交成功后，发送邮件通知给客服 end **********/
		}else if(flag == 3){
			json.addProperty("status", "double");
		}
		
		jsonStr = gson.toJson(json);
		return jsonStr;
	}

	
	/**
	 * 查看企业认证
	 * 
	 * @author zhouji
	 * @param companyCertifyDto
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "checkCompanyCertify")
	public String checkCompanyCertify(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		//header登录名称使用
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		//left菜单选中使用
		String url=request.getRequestURI();
		userinfo.setUrl(url);
		model.addAttribute("userinfo", userinfo);
    	Integer certifyId = Integer.parseInt(request.getParameter("certifyId"));
    	CompanyCertify companyCertify = companyCertifyService.findCompanyCertifyByCertifyId(certifyId);
    	//根据状态对企业信息进行隐秘符号处理 by Calvin.Wangh date 2015-08-04
    	if(companyCertify.getStatus()==1){
    		companyCertify.setPresidentName(StringTransferSymbolUtil.hideString(companyCertify.getPresidentName(), StringTransferSymbolUtil.CHINESE_NAME));
        	companyCertify.setLicenceCode(StringTransferSymbolUtil.hideString(companyCertify.getLicenceCode(), StringTransferSymbolUtil.LICENSE_CODE));
    	}
		model.addAttribute("ucComCertify", companyCertify);
		return "/certify/checkLegalizeCompany";
	}

	/**
	 * 查看个人认证
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/checkPersonCertify")
	public String checkPersonCertify(HttpServletRequest request,ModelMap modelMap){
		//header登录名称使用
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		//left菜单选中使用
		String url=request.getRequestURI();
		userinfo.setUrl(url);
		modelMap.addAttribute("userinfo", userinfo);		
		String certifyId = request.getParameter("certifyId");
		UcPersonCertify ucPersonCertify = memberCertifyService.getCertifyUcUserInfoByCertifyId(certifyId);
		//根据审核状态对个人信息进行隐秘符号处理 by Calvin.Wangh date 2015-08-04
		if (ucPersonCertify.getStatus() == 1){
			ucPersonCertify.setIdCard(StringTransferSymbolUtil.hideString(ucPersonCertify.getIdCard(), StringTransferSymbolUtil.IDENTITY_CARD));
		}
		//根据审核状态对个人信息进行隐秘符号处理 by Calvin.Wangh date 2015-08-04
		modelMap.addAttribute("ucPersonCertify", ucPersonCertify);
		List<CertifyImg> imglist = certifyImgService.findCertifyImgByCertifyId(Integer.parseInt(certifyId));
		if (null != imglist && imglist.size() != 0) {
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
		return "/certify/viewPersonCertInfo";
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
			ModelMap model) throws IOException, ServletException  {
		Integer type = Integer.parseInt(request.getParameter("type"));
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

		String dateDir = TimeUtil
				.getTimeShowByTimePartten(new Date(), "yyyyMMdd");
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		UcUser user = ucUserService.findUcUserByUserName(userName);
		String fileName = createFileName(user.getUserId(), type, "jpg");
		SFTPUtil sftp = SFTPUtil.getSingleton();  //sftp初始化
		ChannelSftp channel = null;               //sftp管道声明
		Session session = null;  //session声明
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
			String msg = uploadThumbSmall(channel, small, user.getUserId(), type,dateDir);
			InputStream big = new FileInputStream(watermark);
			msg = msg+uploadThumbBig(channel, big, user.getUserId(), type,dateDir);
			res = "{status:{code:0,desc:''},con:[{filename:'" + fileName
					+ "',type:'" + type + "',path:'"
					+ sftpConfigInfo.getSftpTempPath() +dateDir+"/"+fileName+ "'},"+msg+"]}";
			
			watermark.delete();
		} catch (Exception e) {
			if (e instanceof SizeLimitExceededException) {
				log.error("图片大小超过5M");
			}
			e.printStackTrace();
			log.error("上传图片失败！" + e.getMessage(), e);
			res = "{status:{code:-1,desc:''}}";
		} 
		setNoCache(response);
		writeHtml(request, response, res);
		//关闭管道连接
        try {
			sftp.closeChannel(channel,session);
		} catch (Exception e) {
			e.printStackTrace();
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
	 * 保存图片(将图片从零时文件夹移到正式文件夹)
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
		
		if(companyCertifyDto.getCertifyId()!=null){
			List<CertifyImg> imglist = certifyImgService.findCertifyImgByCertifyId(Integer.parseInt(companyCertifyDto.getCertifyId()));
			System.out.println(imglist.get(0).getPath().equals(picPath));
			if(!imglist.get(0).getPath().equals(picPath)){
				if(!picPath.equals("")){
					saveFile(sftp,session,picPath);
					saveFile(sftp, session,picSmallPath);
					saveFile(sftp, session,picBigPath);
				}
			}
			if(imglist.size()>3){
				if(!picPath1.equals("")){
					if(!imglist.get(3).getPath().equals(picPath1)){
						saveFile(sftp,session,picPath1);
						saveFile(sftp,session,picSmallPath1);
						saveFile(sftp,session,picBigPath1);
					}
				}
			}
		}else{
			saveFile(sftp,session,picPath);
			saveFile(sftp,session,picSmallPath);
			saveFile(sftp,session,picBigPath);
			if(!picPath1.equals("")){
				saveFile(sftp,session,picPath1);
				saveFile(sftp,session,picSmallPath1);
				saveFile(sftp,session,picBigPath1);
			}
		}
	}
	
	/**
	 * 个人认证保存图片(将图片从零时文件夹移到正式文件夹)
	 * @author ldp
	 * @param pCertifyApplyDto
	 * @throws Exception
	 */
	public void savePersonPic(SFTPUtil sftp,Session session,PersonCertifyDto pCertifyApplyDto) throws Exception {
		String picPath =pCertifyApplyDto.getPicPath();
		String picSmallPath =pCertifyApplyDto.getPicSmallPath();
		String picBigPath =pCertifyApplyDto.getPicBigPath();
		
		String picPath1 =pCertifyApplyDto.getPicPath1();
		String picSmallPath1 =pCertifyApplyDto.getPicSmallPath1();
		String picBigPath1 =pCertifyApplyDto.getPicBigPath1();
		
		if (null != picPath && !"".equals(picPath)) {
			saveFile(sftp,session,picPath);
			saveFile(sftp,session,picSmallPath);
			saveFile(sftp,session,picBigPath);
		}
		if(null != picPath1 && !"".equals(picBigPath1)){
			saveFile(sftp,session,picPath1);
			saveFile(sftp,session,picSmallPath1);
			saveFile(sftp,session,picBigPath1);
		}
	}
	
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
	
	@RequestMapping(value="/companyNameIsHaved")
	@ResponseBody
	public String companyNameIsHaved(HttpServletRequest request,HttpServletResponse response){
		//Validform ajaxurl方法带入两个参数:param,name param是input的value值 name是input的name值
		//header登录名称使用
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		if (null != certifyService.findReallyCompanyCertifyByUserId(Integer.parseInt(userinfo.getUserId().toString()))) {
			return "该会员认证信息已存在！";
		};
		return "y";
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
	
	
	/**
	 * 认证提交前检查
	 * @author biran
	 * @param certyType 认证类型 0-个人认证 1-企业认证
	 * @param userId 会员ID
	 *
	 * @return
	 */
	public String checkCertyBeforeSubmit(int certyType,String userId){
		/*
		 * 会员中心，【实名认证】，在用户提交认证时，做以下校验：
			1.  在做个人认证时，检查当前用户是否已经做完了认证（企业或个人），如果做完了认证，不能提交。
			2.  在做企业认证时，检查当前用户是否已经做完了企业认证，如果做完了企业认证，不能提交。
			3.  当前用户是否已经有审核中的认证（个人认证或企业认证），如果有申请中的认证，不能提交。
			* 
		 * */
		String errorCode=null;
		if(userId==null||userId.equals("")){
			return "code000";//系统异常
		}
		if(certyType==0){//个人认证
			// 1. 做个人认证时，检查当前用户是否已经做完了认证（企业或个人），如果做完了认证，不能提交。
			UcUser user=ucUserService.findUcUserById(Integer.valueOf(userId));
			if(user==null){
				return "code000";//系统异常
			}
			int Certifstatus=user.getCertifyStatus();
			if(Certifstatus==1){//已经做了个人认证
				//您的个人认证已提交，请勿再次提交！
				return "code003";
			}else if(Certifstatus==2){//已经做了企业认证
				//您的企业认证已完成，不能提交个人认证！
				return "code004";
			}
			//3.  当前用户是否已经有审核中的认证（个人认证或企业认证），如果有申请中的认证，不能提交。
			CompanyCertify company=certifyService.findCompanyCertifyByUserId(Integer.valueOf(userId));
			UcPersonCertify member=memberCertifyService.getCertifyUcUserInfoByUserId(userId);
			if(company!=null||member!=null){
				//您的认证资料正在审核中，请勿再提交！
				return "code006";
			}
			
		}
		if(certyType==1){//企业认证
			//2.  在做企业认证时，检查当前用户是否已经做完了企业认证，如果做完了企业认证，不能提交。
			UcUser user=ucUserService.findUcUserById(Integer.valueOf(userId));
			if(user==null){
				return "code000";//系统异常
			}
			int Certifstatus=user.getCertifyStatus();
			if(Certifstatus==2){//已经做了企业认证
				//您的企业认证已完成，请勿重复提交！
				return "code005";
			}
			//3.  当前用户是否已经有审核中企业认证，如果有申请中的认证，不能提交。
			CompanyCertify company=certifyService.findCompanyCertifyByUserId(Integer.valueOf(userId));
			if(company!=null){
				//您的认证资料正在审核中，请勿再提交！
				return "code006";
			}
			//added by biran 20150716 如果是个人认证有提交中的，不能再提交。如果是已经通过的，可以升级
			UcPersonCertify member=memberCertifyService.getCertifyUcUserInfoByUserId(userId);
			if(Certifstatus==0 && member!=null){
				//您的认证资料正在审核中，请勿再提交！
				return "code006";
			}
			
		}
		return errorCode;
	}
	

}