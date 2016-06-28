package com.jointown.zy.web.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.util.ImageHelper;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.util.image.UploadUtils;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * 图片上传
 */
@Controller
@RequestMapping(value="/upload")
public class UploadController extends UserBaseController {

	public static Log log = LogFactory.getLog(UploadController.class);
	
	@Autowired
	private SftpConfigInfo sftpConfigInfo ;
	
	/**
	 * 
	 * @Description: 上传图片(ckeditor)
	 * @Author: wangjunhu
	 * @Date: 2015年6月5日
	 * @param request
	 * @param response
	 * @param file
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
	public void uploadPic(HttpServletRequest request,HttpServletResponse response,@RequestParam("upload") MultipartFile file) throws IOException {
		log.info("UploadController.uploadPic：controller");
		SFTPUtil sftp = null;	//sftp初始化
		Session session = null;	//session声明
		ChannelSftp channel = null;	//sftp管道声明
		try {
			String callback = request.getParameter ("CKEditorFuncNum");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			//验证是否登陆
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
			if(user==null){
				out.println ("<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+callback+", '', '请登录再操作！');</script>");
				throw new Exception("请登录再操作！");
			}
			//验证文件是否为图片
			boolean isImage = UploadUtils.isImage(file.getInputStream());
			if(!isImage){
				out.println ("<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+callback+", '', '图片格式不正确！');</script>");
				throw new Exception("图片格式不正确！");
			}
			//验证图片大小是否超出限制
			long fileSize = file.getSize();
			long maxFileSize = 5000*1024;	//图片大小限制5M
			if(fileSize > maxFileSize){
				out.println ("<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+callback+", '', '图片不能大于5M！');</script>");
				throw new Exception("图片不能大于5M！");
			}
			//上传图片到资源服务器临时目录下
			sftp = SFTPUtil.getSingleton();
			session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
			channel = sftp.getChannelSftp(session,"sftp");  //获取管道符
			
			String dateDir = TimeUtil.getTimeShowByTimePartten(new Date(),"yyyyMM");
			String fileName = UploadUtils.generateFilename("jpg");
			//验证图片尺寸大小
			BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
			//System.out.println(bufferedImage.getWidth()+"/"+bufferedImage.getHeight());
			int imageHeight = bufferedImage.getHeight();
			int imageWidth = bufferedImage.getWidth();
			int maxImageSize = 2048;  //图片高宽尺寸限制2048
			if(imageHeight > maxImageSize || imageWidth > maxImageSize){
				//上传缩略图到资源服务器临时目录下
				InputStream fileInputStream = ImageHelper.scaleImage(file.getInputStream(), maxImageSize, maxImageSize);
				sftp.upload(channel, fileName, fileInputStream, dateDir,sftpConfigInfo.getSftpDataDir(),sftpConfigInfo.getSftpImagesDir(),sftpConfigInfo.getSftpTempProjectDir());
			}else{
				//上传原始图片到资源服务器临时目录下
				sftp.upload(channel, fileName, file.getInputStream(), dateDir,sftpConfigInfo.getSftpDataDir(),sftpConfigInfo.getSftpImagesDir(),sftpConfigInfo.getSftpTempProjectDir());
			}
			String filePath = sftpConfigInfo.getSftpTempPath()+dateDir+"/"+fileName;
			out.println ("<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+callback+", '"+filePath+"', '');</script>");
		} catch (Exception e) {
			log.error("UploadController.uploadPic："+e.getMessage());
		}finally{
			//关闭管道连接
	        try {
	        	if(sftp!=null){
	        		sftp.closeChannel(channel,session);
	        	}
			} catch (Exception e) {
				log.error("UploadController.uploadPic："+e.getMessage());
			}
		}
	}
}