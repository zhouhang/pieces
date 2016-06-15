package com.jointown.zy.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jointown.zy.common.dto.WxOpinionDto;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.exception.WxErrorException;
import com.jointown.zy.common.service.WxOpinionService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.MobileCodeUtil;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.util.image.UploadUtils;

/**
 * 意见反馈
 *
 * @author aizhengdong
 *
 * @data 2015年7月13日
 */
@Controller(value = "wxOpinionController")
public class WxOpinionController extends WxUserBaseController {
	@Autowired
	private WxOpinionService wxOpinionService;

	@Autowired
	private SftpConfigInfo sftpConfigInfo;

	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@RequestMapping("feedback")
	public String feedback(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "helper";
	}

	/**
	 * 添加意见
	 *
	 * @param wxOpinionDto
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月16日
	 */
	@RequestMapping(value = "/addOpinion", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addOpinion(
			@ModelAttribute WxOpinionDto wxOpinionDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String tempPicUrl = wxOpinionDto.getPicUrl();
			if (tempPicUrl != null && !tempPicUrl.equals("")) {
				String picUrl = uploadWxOpinionPic(tempPicUrl);
				if (picUrl.equals("")) {
					map.put("ok", false);
					map.put("msg", "提交失败！");
					return map;
				} else {
					wxOpinionDto.setPicUrl(picUrl);
				}
			}

			int ok = wxOpinionService.addWxOpinion(wxOpinionDto);
			if (ok > 0) {
				map.put("msg", "感谢您的宝贵意见，小珍稍后给您回复！");
				map.put("ok", true);

				// 成功后发送邮件
				String subject = WxConstant.EMAIL_SUBJECT_OPINION;
				String toEmail = GetEmailContext.getWxEmail();
				String emailMsg = WxConstant
						.getOpinionEmailContent(wxOpinionDto.getMobile());
				threadPoolTaskExecutor.execute(new EmailTaskSend(subject,
						toEmail, emailMsg));
			} else {
				map.put("ok", false);
				map.put("msg", "提交失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("ok", false);
			String msg = e.getMessage();
			if (e instanceof WxErrorException) {
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			} else {
				map.put("msg", "提交失败！");
			}
		}

		return map;
	}

	/**
	 * 获取手机验证码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/feedback/getMobileCode", method = RequestMethod.POST)
	@ResponseBody
	public String getMobileCode(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		String mobile = request.getParameter("mobile");
		return wxOpinionService.getMobileCode(request, mobile);
	}

	/**
	 * 检查手机验证码
	 *
	 * @param request
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月16日
	 */
	@RequestMapping(value = "/checkMobileCode")
	@ResponseBody
	public Map<String, Object> checkMobileCode(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		String inputMobileCode = request.getParameter("param");
		@SuppressWarnings("unchecked")
		Map<String, Object> mobileCodeMap = (Map<String, Object>) request
				.getSession().getAttribute(MobileCodeUtil.MOBILE_CODE);
		if (MobileCodeUtil.verityMobileCode(mobileCodeMap,
						inputMobileCode).equals("success")) {
			map.put("ok", true);
			map.put("status", "y");
			return map;
		}

		map.put("ok", false);
		map.put("status", "n");
		map.put("info", "验证码错误！");

		return map;
	}

	/**
	 * 上传图片到资源服务器临时目录下
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/feedback/uploadPic", method = RequestMethod.POST)
	public void uploadPic(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file") MultipartFile[] files) throws IOException {
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> picPaths = new ArrayList<String>();
		SFTPUtil sftp = null;
		ChannelSftp channel = null;
		Session session = null;

		try {
			// 初始化
			sftp = SFTPUtil.getSingleton();
			session = sftp.openSession(sftpConfigInfo.getSftpIpWx(),
					sftpConfigInfo.getSftpUserName(),
					sftpConfigInfo.getSftpPassword(),
					Integer.parseInt(sftpConfigInfo.getSftpPort()));
			channel = sftp.getChannelSftp(session, "sftp");

			for (MultipartFile file : files) {
				String dateDir = TimeUtil.getTimeShowByTimePartten(new Date(),
						"yyyyMM");
				String fileName = UploadUtils.generateFilename("jpg");
				boolean isImage = UploadUtils.isImage(file.getInputStream());
				if (isImage) {
					// 上传原图到资源服务器临时目录下
					sftp.upload(channel, fileName, file.getInputStream(),
							dateDir, sftpConfigInfo.getSftpDataDir(),
							sftpConfigInfo.getSftpImagesDir(),
							sftpConfigInfo.getSftpTempProjectDir());

					// 图片文件和路径
					fileName = fileName.substring(0, fileName.lastIndexOf("."))
							+ fileName.substring(fileName.lastIndexOf("."));
					String filePath = sftpConfigInfo.getSftpImagesDir() + "/"
							+ sftpConfigInfo.getSftpTempProjectDir() + "/"
							+ dateDir + "/" + fileName;
					picPaths.add(filePath);
				}
			}

			map.put("ok", true);
			map.put("obj", picPaths);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = e.getMessage();
			map.put("ok", false);
			if (e instanceof WxErrorException) {
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			} else {
				map.put("msg", "图片上传失败！");
			}
		} finally {
			// 关闭管道连接
			try {
				if (sftp != null) {
					sftp.closeChannel(channel, session);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String res = gson.toJson(map);
		response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().println(res);

	}

	/**
	 * 上传图片到资源服务器图片目录
	 *
	 * @param sftp
	 * @param session
	 * @param filePath
	 * @return
	 * @throws Exception
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月17日
	 */
	public String uploadWxOpinionPic(String tempPicUrl) throws Exception {
		List<String> picUrlList = new ArrayList<String>();
		StringBuffer stringBuffer = new StringBuffer();
		if (tempPicUrl.contains(",")) {
			String[] array = tempPicUrl.split(",");
			if (array.length > 2) {
				throw new WxErrorException(
						ErrorRepository.WX_UPLOAD_IMG_NUM_ERROR);
			}

			for (int i = 0; i < array.length; i++) {
				picUrlList.add(array[i]);
			}
		} else {
			picUrlList.add(tempPicUrl);
		}

		// 上传图片到资源服务器
		SFTPUtil sftp = null;
		Session session = null;
		try {
			sftp = SFTPUtil.getSingleton();
			session = sftp.openSession(sftpConfigInfo.getSftpIpWx(),
					sftpConfigInfo.getSftpUserName(),
					sftpConfigInfo.getSftpPassword(),
					Integer.parseInt(sftpConfigInfo.getSftpPort()));

			for (String picUrl : picUrlList) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("imgpath", picUrl);
				map.put("dataDir", sftpConfigInfo.getSftpDataDir());
				map.put("imagesDir", sftpConfigInfo.getSftpImagesDir());
				map.put("projectDir", sftpConfigInfo.getSftpProjectDirWx());
				map.put("tempProjectDir",
						sftpConfigInfo.getSftpTempProjectDir());
				sftp.moveImg(session, map);
				String fileName = picUrl.substring(picUrl.lastIndexOf("/") + 1);
				String b = picUrl.substring(0, picUrl.lastIndexOf("/"));
				String dateDir = b.substring(b.lastIndexOf("/") + 1);
				String fileNewPath = sftpConfigInfo.getSftpImagesDir() + "/"
						+ sftpConfigInfo.getSftpProjectDirWx() + "/" + dateDir
						+ "/" + fileName;

				if (!stringBuffer.toString().equals("")) {
					stringBuffer.append(",");
				}

				stringBuffer.append(fileNewPath);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stringBuffer.toString();
	}
}
