/**
 * @author guoyb
 * 2015年3月19日 上午10:21:48
 */
package com.jointown.zy.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
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
import com.google.gson.JsonObject;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jointown.zy.common.dto.HomePageListingDto;
import com.jointown.zy.common.enums.HomePageListingStatusEnum;
import com.jointown.zy.common.enums.HomepageListingEnum;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiListingService;
import com.jointown.zy.common.service.HomePageListingService;
import com.jointown.zy.common.util.ImageHelper;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.util.image.UploadUtils;
import com.jointown.zy.common.vo.BossUserVo;

/**
 * @author guoyb 2015年3月19日 上午10:21:48
 */
@Controller
@RequestMapping(value="/getPlatformListingManage")
public class PlatFormListingController {
	
	private final static Logger logger = LoggerFactory
			.getLogger(PlatFormListingController.class);

	@Autowired
	private HomePageListingService homePageListingService;
	
	@Autowired
	private SftpConfigInfo sftpConfigInfo ;
	
	@Autowired
	private BusiListingService busiListingService;

	/**
	 * 查询处理主页面
	 * 2015年3月22日 下午10:46:50
	 * @param request
	 * @param response
	 * @param homePageListingDto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "")
	public String platformListing(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute HomePageListingDto homePageListingDto,
			ModelMap model) throws Exception {
		
		Page<HomePageListingDto> page = new Page<HomePageListingDto>();
		
		// 首页listingType
		Map<String, String> types = HomepageListingEnum.toMap();
		types.remove("8");
		
		// 搜索条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("homePageListingDto", homePageListingDto);
		page.setParams(params);
		
		// 首页数据
		// 挂牌列表
		page.setPageNo(homePageListingDto.getPageNo());
		List<HomePageListingDto> BusiListings = homePageListingService
			.selectReviewedListings(page);
		page.setResults(BusiListings);
	
		model.put("types", types);
		model.put("page", page);
		
		return "/public/HomePageListed";
	}
	
	/**
	 * 添加数据
	 * 2015年3月22日 下午10:47:16
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/addPlatformListing")
	@ResponseBody
	public String addPlatformListing(HttpServletRequest request,HttpServletResponse response,
			ModelMap model) throws Exception {
		HomePageListingDto homePageListingDto = this.getHomePageListingDto(request, response);
		homePageListingDto.setCreate_time(new Date());
		//图片
		SFTPUtil sftp = SFTPUtil.getSingleton();
		Session session =null;
		try {
			session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("imgpath", homePageListingDto.getPicurl());
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
			//修改pic路径
			homePageListingDto.setPicurl(homePageListingDto.getPicurl().replaceAll(sftpConfigInfo.getSftpTempProjectDir(), sftpConfigInfo.getSftpProjectDir()));
		} catch (NumberFormatException e) {
			logger.info("saveAd error ="+e.getMessage());
		} catch (Exception e) {
			logger.info("saveAd error ="+e.getMessage());
		}
		this.homePageListingService.insertHomePageListing(homePageListingDto);
		JsonObject j = new JsonObject();
		j.addProperty("status","yes");
		Gson gson = new Gson();
		// 加以下语句：页面中文没有乱码
		response.setCharacterEncoding("utf-8");
		String json = gson.toJson(j);
		return json;
	}
	
	/**
	 * 更新数据
	 * 2015年3月22日 下午10:47:16
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/alterPlatformListing")
	@ResponseBody
	public String alterPlatformListing(HttpServletRequest request,HttpServletResponse response,
			ModelMap model) throws Exception {
		HomePageListingDto homePageListingDto = this.getHomePageListingDto(request, response);
		//图片
		SFTPUtil sftp = SFTPUtil.getSingleton();
		Session session =null;
		try {
			session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("imgpath", homePageListingDto.getPicurl());
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
			//修改pic路径
			homePageListingDto.setPicurl(homePageListingDto.getPicurl().replaceAll(sftpConfigInfo.getSftpTempProjectDir(), sftpConfigInfo.getSftpProjectDir()));
			this.homePageListingService.updateHomePageListing(homePageListingDto);
		} catch (NumberFormatException e) {
			logger.info("saveAd error ="+e.getMessage());
		} catch (Exception e) {
			logger.info("saveAd error ="+e.getMessage());
		}
		JsonObject j = new JsonObject();
		j.addProperty("status","yes");
		Gson gson = new Gson();
		// 加以下语句：页面中文没有乱码
		response.setCharacterEncoding("utf-8");
		String json = gson.toJson(j);
		return json;
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
		SFTPUtil sftp = SFTPUtil.getSingleton();//sftp初始化
		ChannelSftp channel = null;//sftp管道声明
		Session session = null;  //session声明
		try {
			session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
			channel = sftp.getChannelSftp(session,"sftp");  //获取管道符
			
			//上传原图片到资源服务器临时目录下
			sftp.upload(channel, fileName, file.getInputStream(), dateDir,sftpConfigInfo.getSftpDataDir(),sftpConfigInfo.getSftpImagesDir(),sftpConfigInfo.getSftpTempProjectDir());
		    
			//上传缩略图到资源服务器临时目录下
			int [] widths={sftpConfigInfo.getSftpMiddleWidth(),sftpConfigInfo.getSftpXBigWidth(),sftpConfigInfo.getSftpXXBigWidth()};
			for (int width : widths) {
				String scaleFileName = fileName.substring(0,fileName.lastIndexOf("."))+"_"+width+".jpg";
				OutputStream thumbOutstream = channel.put(scaleFileName);
				ImageHelper.scaleImage(file.getInputStream(), thumbOutstream, width, width);
			}
			
			String newFileName = fileName.substring(0, fileName.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXBigWidth()+fileName.substring(fileName.lastIndexOf("."));
			res = "{status:{code:0,desc:''},con:{filename:'" + newFileName
					+ "',dateDir:'" + dateDir + "',path:'"+sftpConfigInfo.getSftpTempPath()+"'}}";
		} catch (Exception e) {
			if(e instanceof SizeLimitExceededException){
				res = "{status:{code:-1,desc:'图片大小超过5M'}}";
			}
			res = "{status:{code:-1,desc:'上传图片失败'"+e.getMessage()+"}}";
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
	
	@RequestMapping(value="/listingidIsHaved")
	@ResponseBody
	public String listingidIsHaved(HttpServletRequest request,HttpServletResponse response){
		String listingid = request.getParameter("param");
		if (null == this.busiListingService.findSingleListingDetail(listingid)){
			return "该挂牌不存在,请重新输入！";
		}
//		if (null != this.homePageListingService.selectHomePageListingByListingid(listingid)) {
//			return "该挂牌已在首页显示,请重新输入！";
//		};
		return "y";
	}
	
	@RequestMapping(value="findPlatformListingById")
	@ResponseBody
	public HomePageListingDto findPlatformListingById(HttpServletRequest request,HttpServletResponse response){
		String listing_Id = request.getParameter("listing_Id");
		HomePageListingDto homePageListingDto = this.homePageListingService.selectHomePageListingByListing_Id(Integer.parseInt(listing_Id));
		return homePageListingDto;
	}
	
	@RequestMapping(value="dropHomePageListing")
	@ResponseBody
	public String dropHomePageListing(HttpServletRequest request,HttpServletResponse response){
		String listing_Id = request.getParameter("listing_Id");
		HomePageListingDto homePageListingDto = new HomePageListingDto();
		homePageListingDto.setListing_Id(Integer.parseInt(listing_Id));
		homePageListingDto.setStatus(Integer.parseInt(HomePageListingStatusEnum.HOMEPAGE_LISTING_TYPE_DELETE.getType()));
		BossUserVo userinfo = (BossUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		homePageListingDto.setUpdate_time(new Date());
		homePageListingDto.setUpdater_id(userinfo.getId());
		
		int number = this.homePageListingService.removeHomePageListingBylisting_Id(homePageListingDto);
		if (number > 0) {
			return "y";
		}else {
			return "删除失败！";
		}
	}
	
	/**
	 * 插入更新用的数据接收。
	 * @param request
	 * @param response
	 * @return
	 */
	private HomePageListingDto getHomePageListingDto(HttpServletRequest request,HttpServletResponse response){
		String listing_Id = request.getParameter("listing_Id");
		String listingid = request.getParameter("listingid");
		String type = request.getParameter("type");
		String picurl = request.getParameter("picurl");
		String alt = request.getParameter("alt");
		String sortno = request.getParameter("sortno");
		
		BossUserVo userinfo = (BossUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		
		HomePageListingDto homePageListingDto = new HomePageListingDto();
		if (listing_Id!=null) {
			homePageListingDto.setListing_Id(Integer.parseInt(listing_Id));
		}
		homePageListingDto.setPicurl(picurl);
		homePageListingDto.setAlt(alt);
		homePageListingDto.setListingid(listingid);
		homePageListingDto.setType(Integer.parseInt(type));
		homePageListingDto.setSortno(Integer.parseInt(sortno));
		homePageListingDto.setCreater_id(userinfo.getId());
		homePageListingDto.setUpdater_id(userinfo.getId());
		homePageListingDto.setUpdate_time(new Date());
		
		return homePageListingDto;
	}
}
