package com.jointown.zy.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.dto.HomePageAdDto;
import com.jointown.zy.common.enums.HomepageAdEnum;
import com.jointown.zy.common.model.HomePageAd;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.HomePageAdService;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.util.image.UploadUtils;
import com.jointown.zy.common.vo.BossUserVo;
/**
* 平台广告管理Controller
* @author Mr.song
* 2015-3-20
*/
@Controller(value="platformAdManageController")
@RequestMapping(value="/platformad")
public class PlatformAdManageController {
	private final static Logger logger = LoggerFactory.getLogger(PlatformAdManageController.class);
	@Autowired
	private HomePageAdService homePageAdService;
	@Autowired
	private SftpConfigInfo sftpConfigInfo ;
	
	/**
	 * 删除广告
	 * @param wxActivity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAd",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> deleteAd(@RequestParam(value="id", required = false) int id) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		int ok = homePageAdService.abandonHomePageAdByAdid(id);
		if(ok>0){
			map.put("status", true);
			map.put("info", "删除成功！");
		}else{
			map.put("status", false);
			map.put("info", "删除失败！");
		}
		return map;
	}
	
	/**
	 * 跳转界面获取广告信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getAdUpdate",method=RequestMethod.GET)
	@ResponseBody
	public HomePageAd getAdUpdate(@RequestParam(value="id", required = false) int id){
		logger.info("PlatformAdManageController.getAdUpdate");
		HomePageAd vo = new HomePageAd();
		try{
			vo = homePageAdService.getHomePageAdById(id);
		}catch(Exception s){
			logger.info("PlatformAdManageController.getWxActivityManager. error:【{}】",s);
		}
		return vo;
	}
	
	/**
	 * 提供广告接口
	 * @param model
	 * @return
	 */
	@RequestMapping(value="getAds",method=RequestMethod.GET)
	@ResponseBody
	public HashMap<String,List<HomePageAd>> getAds(){
		HashMap<String,List<HomePageAd>> map = null;
		try{	 
			map = homePageAdService.selectAdByCategory();
		}catch(Exception s){
			logger.info("PlatformAdManageController.getAds. error:【{}】",s);
		}
		return map;
	}
	
	/**
	 * 跳转广告列表管理界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String getPlatformAdManager(@ModelAttribute("pageNo") String pageNo,HttpServletRequest request,ModelMap model){
		logger.info("PlatformAdManageController.getPlatformAdManager");
		Page<HomePageAd> page = new Page<HomePageAd>();
		if(!pageNo.isEmpty()){
			page.setPageNo(Integer.parseInt(pageNo));
		}
		try{
			String _type = request.getParameter("type");
			String _alt = request.getParameter("alt");
			String type=(_type!=null && !"".equals(_type))? _type:"";
			String alt=(_alt!=null && !"".equals(_alt))? _alt:"";
			//查询条件
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("type",type);
			params.put("alt",alt);
			params.put("category", ConfigConstant.AD_CATEGORY);
			page.setParams(params);
			List<HomePageAd> list = homePageAdService.selectAdByPage(page);
			page.setResults(list);
			model.put("flagMap", HomepageAdEnum.toMap());
			model.put("alt", alt);
			model.put("page", page);
			model.put("type", type);
		}catch(Exception s){
			logger.info("PlatformAdManageController.getWxActivityManager. error:【{}】",s);
		}
		return "/public/platform_ad_manager";
	}
	
	/**
	 * 保存广告信息
	 * @param request
	 * @param response
	 * @param add
	 * @return
	 */
	@RequestMapping(value = "/updateAd", method = RequestMethod.POST)
	public HashMap updateAd(HttpServletRequest request,HttpServletResponse response,@ModelAttribute HomePageAdDto add){
		logger.info("PlatformAdManageController.updateAd");
		
		HomePageAd ad = homePageAdService.getHomePageAdById(add.getAdid());
		ad.setAlt(add.getAlt());
		ad.setUrl(add.getUrl());
		ad.setType(add.getType());
		ad.setCategory("0");
		Subject subject = SecurityUtils.getSubject();
		BossUserVo  user = (BossUserVo ) subject.getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		ad.setCreater(user.getId());
		ad.setUpdater(user.getId());
		ad.setSortno(add.getSortno());
		ad.setStatus((short)0);
		ad.setCreatetime(new Date());
		ad.setUpdatetime(new Date());
		if(add.getPicurl()!=null && !add.getPicurl().equals(ad.getPicurl())){
			SFTPUtil sftp = SFTPUtil.getSingleton();
			Session session =null;
			try {
				session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("imgpath", add.getPicurl());
				map.put("dataDir", sftpConfigInfo.getSftpDataDir());
				map.put("imagesDir", sftpConfigInfo.getSftpImagesDir());
				map.put("projectDir", sftpConfigInfo.getSftpProjectDir());
				map.put("tempProjectDir", sftpConfigInfo.getSftpTempProjectDir());
				//开始迭代移到正式图片目录下
				sftp.moveImg(session, map);
				if (session != null) {
					if (session.isConnected()) {
						session.disconnect();
					} else{
						logger.info("session is closed already");
					}
				}  //操作完毕关闭管道连接
				ad.setPicurl(add.getPicurl().replaceAll(sftpConfigInfo.getSftpTempProjectDir(), sftpConfigInfo.getSftpProjectDir()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//查询条件
		HashMap<String, Object> m = new HashMap<String, Object>();
		m.put("status", "yes");
		m.put("option", "修改");
//		m.put("category", ConfigConstant.AD_CATEGORY);
//		m.put("type", add.getType());
//		m.put("sortno", add.getSortno());
//		List<HomePageAd> list = homePageAdService.selectHomePageAdByType(m);
		//if(list.size()<=0) 
		homePageAdService.updateHomePageAdByAdid(ad);
//		return "redirect:/platformad";
		return m;
	}
	
	/**
	 * 保存广告信息
	 * @param request
	 * @param response
	 * @param add
	 * @return
	 */
	@RequestMapping(value = "/saveAd", method = RequestMethod.POST)
	public HashMap saveAd(HttpServletRequest request,HttpServletResponse response,@ModelAttribute HomePageAdDto add){
		logger.info("PlatformAdManageController.saveAd");
		HomePageAd ad = new HomePageAd();
		ad.setAlt(add.getAlt());
		ad.setUrl(add.getUrl());
		ad.setType(add.getType());
		ad.setSortno(add.getSortno());
		ad.setCategory("0");
		Subject subject = SecurityUtils.getSubject();
		BossUserVo  user = (BossUserVo ) subject.getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		ad.setCreater(user.getId());
		ad.setUpdater(user.getId());
		ad.setStatus((short)0);
		ad.setCreatetime(new Date());
		ad.setUpdatetime(new Date());
		SFTPUtil sftp = SFTPUtil.getSingleton();
		Session session =null;
		HashMap<String, Object> m = new HashMap<String, Object>();
		try {
			session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("imgpath", add.getPicurl());
			map.put("dataDir", sftpConfigInfo.getSftpDataDir());
			map.put("imagesDir", sftpConfigInfo.getSftpImagesDir());
			map.put("projectDir", sftpConfigInfo.getSftpProjectDir());
			map.put("tempProjectDir", sftpConfigInfo.getSftpTempProjectDir());
			//开始迭代移到正式图片目录下
			sftp.moveImg(session, map);
			if (session != null) {
				if (session.isConnected()) {
					session.disconnect();
				} else{
					logger.info("session is closed already");
				}
			}  //操作完毕关闭管道连接
			
			ad.setPicurl(add.getPicurl().replaceAll(sftpConfigInfo.getSftpTempProjectDir(), sftpConfigInfo.getSftpProjectDir()));
			//查询条件
//			m.put("category", ConfigConstant.AD_CATEGORY);
//			m.put("type", add.getType());
//			m.put("sortno", add.getSortno());
			m.put("status","yes");
			m.put("option", "添加");
			int i = homePageAdService.insertHomePageAd(ad);
			logger.info("saveAd success ="+i);
		} catch (NumberFormatException e) {
			logger.info("saveAd error ="+e.getMessage());
		} catch (Exception e) {
			logger.info("saveAd error ="+e.getMessage());
		}
		return m;
	}
	
	/**
	 * 上传图片
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadpic", method = RequestMethod.POST)
	public void uploadPic(HttpServletRequest request,HttpServletResponse response,@RequestParam("upload") MultipartFile file) throws IOException {
		String res = "";
		if(null == file){res = "{status:{code:-1,desc:'请选择图片！'}}";return;}
		boolean isImage = UploadUtils.isImage(file.getInputStream());
		if(!isImage){
			res = "{status:{code:-1,desc:''}}";
			return;
		}
		String dateDir = TimeUtil.getTimeShowByTimePartten(new Date(),"yyyyMM");
		String fileName = UploadUtils.generateFilename("jpg");
		SFTPUtil sftp = SFTPUtil.getSingleton();  //sftp初始化
		ChannelSftp channel = null;               //sftp管道声明
		Session session = null;  //session声明
		try {
			session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
			channel = sftp.getChannelSftp(session,"sftp");  //获取管道符
			
			//上传原图片到资源服务器临时目录下
			sftp.upload(channel, fileName, file.getInputStream(), dateDir,sftpConfigInfo.getSftpDataDir(),sftpConfigInfo.getSftpImagesDir(),sftpConfigInfo.getSftpTempProjectDir());
		    
//			//上传缩略图到资源服务器临时目录下
//			int [] widths={sftpConfigInfo.getSftpMiddleWidth(),sftpConfigInfo.getSftpXBigWidth(),sftpConfigInfo.getSftpXXBigWidth()};
//			for (int width : widths) {
//		       String scaleFileName = fileName.substring(0,fileName.lastIndexOf("."))+"_"+width+".jpg";
//		       OutputStream thumbOutstream = channel.put(scaleFileName);
//		       ImageHelper.scaleImage(file.getInputStream(), thumbOutstream, width, width);
//			}
			
			String newFileName = fileName.substring(0, fileName.lastIndexOf("."))+fileName.substring(fileName.lastIndexOf("."));
			res = "{\"status\":{\"code\":0,\"desc\":\"\"},\"con\":{\"filename\":\"" + newFileName
					+ "\",\"dateDir\":\"" + dateDir + "\",\"path\":\""+sftpConfigInfo.getSftpTempPath()+"\"}}";
		} catch (Exception e) {
			if(e instanceof SizeLimitExceededException){
				res = "{\"status\":{\"code\":-1,\"desc\":\"图片大小超过5M\"}}";
			}
			res = "{\"status\":{\"code\":-1,\"desc\":\"上传图片失败\""+e.getMessage()+"}}";
			logger.debug("UploadController.uploadPic ::::"+res+e.getMessage());
		}finally{
			//关闭管道连接
	        try {
				sftp.closeChannel(channel,session);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		response.getWriter().println(res);
	}
}
