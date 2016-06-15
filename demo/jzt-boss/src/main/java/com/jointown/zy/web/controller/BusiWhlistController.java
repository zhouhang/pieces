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

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.lang.StringUtils;
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
import com.jointown.zy.common.dto.BusiWhlistDto;
import com.jointown.zy.common.dto.BusiWhlistSearchDto;
import com.jointown.zy.common.enums.BusiWhlistStateEnum;
import com.jointown.zy.common.model.BusiQualitypic;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiQualityPicService;
import com.jointown.zy.common.service.BusiWareHouseService;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.ExcelUtil;
import com.jointown.zy.common.util.ImageHelper;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.util.image.UploadUtils;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.BreedVo;
import com.jointown.zy.common.vo.BusiWareHouseVo;
import com.jointown.zy.common.vo.BusiWhlistVo;
import com.jointown.zy.common.vo.CategorysVo;
import com.jointown.zy.common.vo.DictInfoVo;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * 仓单管理Controller
 * @author wangjunhu
 * 2014-12-19
 */
@Controller(value="busiWhlistController")
public class BusiWhlistController {
	
	private final static Logger logger = LoggerFactory.getLogger(BusiWhlistController.class);
	
	@Autowired
	private BusiWhlistService busiWhlistService;
	
	@Autowired
	private BusiWareHouseService busiWareHouseService;
	
	@Autowired
	private BusiQualityPicService busiQualityPicService;
	
	@Autowired
	private UcUserService ucUserService;
	
	@Autowired
	private SftpConfigInfo sftpConfigInfo;
	
	/**
	 * 仓单管理
	 * @param pageNo
	 * @param busiWhlistSearchDto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiWhlistManage")
	public String getBusiWhlistManage(@ModelAttribute BusiWhlistSearchDto busiWhlistSearchDto, ModelMap model) throws Exception {
		Page<BusiWhlistVo> page = new Page<BusiWhlistVo>();
		page.setPageNo(busiWhlistSearchDto.getPageNo());
		//仓库列表
		List<BusiWareHouseVo> busiWareHousesByTree = busiWareHouseService.findBusiWareHousesByTree();
		//仓单状态列表
		Map<String,String> wlStateMap = BusiWhlistStateEnum.toMap();
		//搜索条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("busiWhlistSearchDto", busiWhlistSearchDto);
		page.setParams(params);
		//仓单列表
		List<BusiWhlistVo> busiWhlists = busiWhlistService.findBusiWhlistsByCondition(page);
		page.setResults(busiWhlists);
		
		model.put("page", page);
		model.put("busiWareHouses", busiWareHousesByTree);
		model.put("wlStateMap", wlStateMap);
		
		return "/public/store_management";
	}
	
	/**
	 * 
	 * @Description: 根据仓单id查询仓单详情
	 * @Author: 宋威
	 * @Date: 2015年6月16日
	 * @param wlid
	 * @param model
	 * @return String 返回仓单详情界面
	 * @throws Exception
	 */
	@RequestMapping(value="busiWhlistManage/detail",method=RequestMethod.GET)
	public String whListDetail(@RequestParam(value="wlid", required = false) String wlid,ModelMap model) throws Exception {
		logger.info("BusiWhlistController.whListDetail controller");
		try{
			BusiWhlistVo vo = busiWhlistService.findBusiWhlistById(wlid,null);
			if(vo == null){
				model.addAttribute("error", "仓单[" + wlid + "]不存在！");
				return "error";
			}
			model.addAttribute("i320", sftpConfigInfo.getSftpXBigWidth());
			model.addAttribute("i640", sftpConfigInfo.getSftpXXBigWidth());
			model.addAttribute("whlistvo", vo);
			model.addAttribute("whlistStateMap", BusiWhlistStateEnum.toMap());
			/**********************匹配菜单*******************/
			//header登录名称使用
			BossUserVo userinfo = (BossUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
			model.addAttribute("userinfo", userinfo);
			/**********************匹配菜单*******************/
			return "/public/busiWhlistDetail";
		}catch (Exception e) {
			logger.error("BusiListingController.addListing error is " + e.getMessage());
			return "error";
		}	
	}
	
	
	/**
	 * 新增仓单
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addBusiWhlist",method=RequestMethod.GET)
	public String addBusiWhlist(ModelMap model){	
		//新增仓单ID
		BusiWhlistVo busiWhlist = busiWhlistService.findWlIdBySeqBusiWhlist();
		//类目列表
		List<CategorysVo> categorysByTree = busiWhlistService.findCategorysByTree();
		//品种列表
		if(!categorysByTree.isEmpty()){
			Long categoryId = categorysByTree.get(0).getId();
			List<BreedVo> breedTreesByCategorysId = busiWhlistService.findBreedTreesByCategorysId(categoryId);
			if(!breedTreesByCategorysId.isEmpty()){
				BreedVo breed = breedTreesByCategorysId.get(0);
				//计量单位
				Long breedId = breed.getBreedId();
				DictInfoVo dictInfoByBreedId = busiWhlistService.findDictInfoByBreedId(breedId);
				//等级规格列表
				String[] breedStandardLevels = breed.getBreedStandardLevel().split(",");
				model.put("breeds", breedTreesByCategorysId);
				model.put("dictInfo", dictInfoByBreedId);
				model.put("breedStandardLevels", breedStandardLevels);
			}
		}
		//仓库列表
		List<BusiWareHouseVo> busiWareHousesByTree = busiWareHouseService.findBusiWareHousesByTree();
		//仓单状态列表
		Map<String,String> wlStateMap = BusiWhlistStateEnum.toMap();
		//区域列表
		Map<String,List<AreaVo>> areasMap = new HashMap<String,List<AreaVo>>();
		List<AreaVo> areas0 = busiWhlistService.findAreasByFirstCode("0");
		if(!areas0.isEmpty()){
			AreaVo area0 = areas0.get(0);
			String type0 = area0.getType();
			areasMap.put(type0, areas0);
			String firstCode0 = area0.getCode();
			List<AreaVo> areas1 = busiWhlistService.findAreasByFirstCode(firstCode0);
			if(!areas1.isEmpty()){
				AreaVo area1 = areas1.get(0);
				String type1 = area1.getType();
				areasMap.put(type1, areas1);
				String firstCode1 = area1.getCode();
				List<AreaVo> areas2 = busiWhlistService.findAreasByFirstCode(firstCode1);
				if(!areas2.isEmpty()){
					AreaVo area2 = areas2.get(0);
					String type2 = area2.getType();
					areasMap.put(type2, areas2);
					String firstCode2 = area2.getCode();
					List<AreaVo> areas3 = busiWhlistService.findAreasByFirstCode(firstCode2);
					if(!areas3.isEmpty()){
						AreaVo area3 = areas3.get(0);
						String type3 = area3.getType();
						areasMap.put(type3, areas3);
					}
				}
			}
		}
		model.put("busiWhlist", busiWhlist);
		model.put("categorys", categorysByTree);
		model.put("busiWareHouses", busiWareHousesByTree);
		model.put("wlStateMap", wlStateMap);
		model.put("areasMap", areasMap);
		
		return "/public/add_warrant";
	}
	
	/**
	 * 修改仓单
	 * @param busiWhlistDto
	 * @param model
	 * @return
	 */
	@RequestMapping(value="busiWhlistManage/alterBusiWhlist",method=RequestMethod.GET)
	public String alterBusiWhlist(@ModelAttribute BusiWhlistDto busiWhlistDto, ModelMap model){
		//仓单详情
		String wlId = busiWhlistDto.getWlid();
		BusiWhlistVo busiWhlist = busiWhlistService.findBusiWhlistById(wlId,null);
		//类目列表
		List<CategorysVo> categorysByTree = busiWhlistService.findCategorysByTree();
		model.put("categorys", categorysByTree);
		
		//品种列表
		Long categoryId = busiWhlist.getCategoryid();
		if(categoryId != null){
			List<BreedVo> breedTreesByCategorysId = busiWhlistService.findBreedTreesByCategorysId(categoryId);
			model.put("breeds", breedTreesByCategorysId);
			
			Long breedId = busiWhlist.getBreedid();
			if(breedId != null){
				BreedVo breed = busiWhlistService.findBreedById(breedId);
				//计量单位
				DictInfoVo dictInfoByBreedId = busiWhlistService.findDictInfoByBreedId(breedId);
				model.put("dictInfo", dictInfoByBreedId);
				
				if(breed!=null){
					//等级规格列表
					String[] breedStandardLevels = {}; 
					String breedStandardLevel = breed.getBreedStandardLevel();
					if(breedStandardLevel!=null){
						breedStandardLevels = breedStandardLevel.split(",");
						model.put("breedStandardLevels", breedStandardLevels);
					}
				}
			}
		}
		//仓库列表
		List<BusiWareHouseVo> busiWareHousesByTree = busiWareHouseService.findBusiWareHousesByTree();
		//仓单状态列表
		Map<String,String> wlStateMap = BusiWhlistStateEnum.toMap();
		//区域列表
		Map<String,List<AreaVo>> areasMap = new HashMap<String,List<AreaVo>>();
		String areaId = busiWhlist.getAreaid();
		if(areaId!=null&&!areaId.isEmpty()){
			AreaVo area3 = busiWhlistService.findAreaByCode(areaId);
			String type3 = area3.getType();
			String firstCode3 = area3.getFirstcode();
			List<AreaVo> areas3 = busiWhlistService.findAreasByFirstCode(firstCode3);
			if(!areas3.isEmpty()){
				areasMap.put(type3, areas3);
				AreaVo area2 = busiWhlistService.findAreaByCode(firstCode3);
				String code2 = area2.getCode();
				String type2 = area2.getType();
				String firstCode2 = area2.getFirstcode();
				List<AreaVo> areas2 = busiWhlistService.findAreasByFirstCode(firstCode2);
				if(!areas2.isEmpty()){
					busiWhlist.setAreaid2(code2);
					areasMap.put(type2, areas2);
					AreaVo area1 = busiWhlistService.findAreaByCode(firstCode2);
					String code1 = area1.getCode();
					String type1 = area1.getType();
					String firstCode1 = area1.getFirstcode();
					List<AreaVo> areas1 = busiWhlistService.findAreasByFirstCode(firstCode1);
					if(!areas1.isEmpty()){
						busiWhlist.setAreaid1(code1);
						areasMap.put(type1, areas1);
						AreaVo area0 = busiWhlistService.findAreaByCode(firstCode1);
						String code0 = area0.getCode();
						String type0 = area0.getType();
						String firstCode0 = area0.getFirstcode();
						List<AreaVo> areas0 = busiWhlistService.findAreasByFirstCode(firstCode0);
						if(!areas0.isEmpty()){
							busiWhlist.setAreaid0(code0);
							areasMap.put(type0, areas0);
						}
					}
				}
			}
		}else{
			List<AreaVo> areas0 = busiWhlistService.findAreasByFirstCode("0");
			if(!areas0.isEmpty()){
				AreaVo area0 = areas0.get(0);
				String type0 = area0.getType();
				areasMap.put(type0, areas0);
			}
		}
		//仓单图片列表
		List<BusiQualitypic> piclists = busiWhlist.getPiclist();
		Map<String,String> piclistMap = new HashMap<String,String>();
		for (BusiQualitypic piclist : piclists) {
			String picIndex = piclist.getPicindex().toString();
			String path = piclist.getPath();
			path = path.substring(0,path.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpMiddleWidth()+path.substring(path.lastIndexOf("."));
			piclistMap.put(picIndex, path);
		}
		//质检报告图片
		BusiQualitypic busiQualitypic = busiQualityPicService.findZJPicByWLID(wlId);
		if(busiQualitypic!=null){
			String zjPic = busiQualitypic.getPath();
			model.put("zjPic", zjPic);
		}
		model.put("busiWhlist", busiWhlist);
		model.put("busiWareHouses", busiWareHousesByTree);
		model.put("wlStateMap", wlStateMap);
		model.put("areasMap", areasMap);
		model.put("piclistMap", piclistMap);
		
		return "/public/alter_warrant";
	}
	
	/**
	 * 提交新增仓单表单
	 * @param busiWhlistDto
	 * @param busiQualityInfoDto
	 * @param busiQualityPicDto
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value="addBusiWhlist/addBusiWhlistFormAction",method=RequestMethod.POST)
//	@ResponseBody
//	public String addBusiWhlistFormAction(@ModelAttribute BusiWhlistDto busiWhlistDto, @ModelAttribute BusiQualityInfoDto busiQualityInfoDto, @ModelAttribute BusiQualityPicDto busiQualityPicDto) throws Exception {
//		Map<String,Object> map = new HashMap<String,Object>();
//		Gson gson = new Gson();
//		SFTPUtil sftp = SFTPUtil.getSingleton();
//		Session session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
//		try{
//			busiWhlistService.addBusiWhlist(sftp,session,busiWhlistDto, busiQualityInfoDto, busiQualityPicDto);
//			map.put("status", true);
//			map.put("info", "保存成功！");
//			map.put("redirect", "/busiWhlistManage");
//		}catch(Exception e){
//			map.put("status", false);
//			map.put("info", "保存失败！");
//		}finally{
//			if (session != null) {
//				if (session.isConnected()) {
//					session.disconnect();
//				} else{
//					logger.info("session is closed already");
//				}
//			}  //操作完毕关闭管道连接
//		}
//		return gson.toJson(map);
//	}
	
	/**
	 * 提交修改仓单表单
	 * @param busiWhlistDto
	 * @param busiQualityInfoDto
	 * @param busiQualityPicDto
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value="busiWhlistManage/alterBusiWhlistFormAction",method=RequestMethod.POST)
//	@ResponseBody
//	public String alterBusiWhlistFormAction(@ModelAttribute BusiWhlistDto busiWhlistDto, @ModelAttribute BusiQualityInfoDto busiQualityInfoDto, @ModelAttribute BusiQualityPicDto busiQualityPicDto) throws Exception {
//		Map<String,Object> map = new HashMap<String,Object>();
//		Gson gson = new Gson();
//		SFTPUtil sftp = SFTPUtil.getSingleton();
//		Session session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
//		try{
//			busiWhlistService.alterBusiWhlist(sftp,session,busiWhlistDto, busiQualityInfoDto, busiQualityPicDto);
//			map.put("status", true);
//			map.put("info", "修改成功！");
//			map.put("redirect", "/busiWhlistManage");
//		}catch(Exception e){
//			map.put("status", false);
//			map.put("info", "修改失败！");
//		}finally{
//			if (session != null) {
//				if (session.isConnected()) {
//					session.disconnect();
//				} else{
//					logger.info("session is closed already");
//				}
//			}  //操作完毕关闭管道连接
//		}
//		return gson.toJson(map);
//	}
	
	/**
	 * 类目级联品种、计量单位、等级规格
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiWhlistManage/getBreedTreesByCategorysId",method=RequestMethod.GET)
	@ResponseBody
	public String getBreedTreesByCategorysId(@ModelAttribute("categoryId") Long categoryId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		List<BreedVo> breeds = busiWhlistService.findBreedTreesByCategorysId(categoryId);
		if(!breeds.isEmpty()){
			BreedVo breed = breeds.get(0);
			//计量单位
			DictInfoVo dictInfo = busiWhlistService.findDictInfoByBreedId(breed.getBreedId());
			//等级规格列表
			String[] breedStandardLevels = breed.getBreedStandardLevel().split(",");
			map.put("breeds", breeds);
			map.put("dictInfo", dictInfo);
			map.put("breedStandardLevels", breedStandardLevels);
		}
		Gson gson = new Gson();
		String jsonObject = gson.toJson(map);
		return jsonObject;
	}
	
	/**
	 * 品种级联计量单位、等级规格
	 * @param breedId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiWhlistManage/getDictInfoByBreedId",method=RequestMethod.GET)
	@ResponseBody
	public String getDictInfoByBreedId(@ModelAttribute("breedId") Long breedId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		BreedVo breed = busiWhlistService.findBreedById(breedId);
		//计量单位
		DictInfoVo dictInfo = busiWhlistService.findDictInfoByBreedId(breedId);
		//等级规格列表
		String[] breedStandardLevels = breed.getBreedStandardLevel().split(",");
		map.put("dictInfo", dictInfo);
		map.put("breedStandardLevels", breedStandardLevels);
		Gson gson = new Gson();
		String jsonObject = gson.toJson(map);
		return jsonObject;
	}
	
	/**
	 * 区域级联
	 * @param firstCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiWhlistManage/getAreasByCode",method=RequestMethod.GET)
	@ResponseBody
	public String getAreasByCode(@ModelAttribute("code") String code) throws Exception {
		Map<String,List<AreaVo>> areasMap = new HashMap<String,List<AreaVo>>();
		AreaVo area = busiWhlistService.findAreaByCode(code);
		String type = area.getType();
		if(type!=null&&!type.isEmpty()){
			Integer ty = Integer.parseInt(type);
			switch (ty) {
			case 0:
				String firstCode0 = area.getCode();
				List<AreaVo> areas1 = busiWhlistService.findAreasByFirstCode(firstCode0);
				if(!areas1.isEmpty()){
					AreaVo area1 = areas1.get(0);
					String type1 = area1.getType();
					areasMap.put(type1, areas1);
					String firstCode1 = area1.getCode();
					List<AreaVo> areas2 = busiWhlistService.findAreasByFirstCode(firstCode1);
					if(!areas2.isEmpty()){
						AreaVo area2 = areas2.get(0);
						String type2 = area2.getType();
						areasMap.put(type2, areas2);
						String firstCode2 = area2.getCode();
						List<AreaVo> areas3 = busiWhlistService.findAreasByFirstCode(firstCode2);
						if(!areas3.isEmpty()){
							AreaVo area3 = areas3.get(0);
							String type3 = area3.getType();
							areasMap.put(type3, areas3);
						}
					}
				}
				break;
			case 1:
				String firstCode1 = area.getCode();
				List<AreaVo> areas2 = busiWhlistService.findAreasByFirstCode(firstCode1);
				if(!areas2.isEmpty()){
					AreaVo area2 = areas2.get(0);
					String type2 = area2.getType();
					areasMap.put(type2, areas2);
					String firstCode2 = area2.getCode();
					List<AreaVo> areas3 = busiWhlistService.findAreasByFirstCode(firstCode2);
					if(!areas3.isEmpty()){
						AreaVo area3 = areas3.get(0);
						String type3 = area3.getType();
						areasMap.put(type3, areas3);
					}
				}
				break;
			case 2:
				String firstCode2 = area.getCode();
				List<AreaVo> areas3 = busiWhlistService.findAreasByFirstCode(firstCode2);
				if(!areas3.isEmpty()){
					AreaVo area3 = areas3.get(0);
					String type3 = area3.getType();
					areasMap.put(type3, areas3);
				}
				break;	
			default:
				break;
			}
		}
		Gson gson = new Gson();
		String jsonObject = gson.toJson(areasMap);
		return jsonObject;
	}
	
	/**
	 * 验证仓单ID是否重复
	 * @param wlid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiWhlistManage/getBusiWhlistByWlId",method=RequestMethod.GET)
	@ResponseBody
	public String getBusiWhlistByWlId(@ModelAttribute("wlid") String wlid) throws Exception {
		BusiWhlistVo busiWhlist = busiWhlistService.findBusiWhlistByWlId(wlid);
		MessageVo message = new MessageVo();
		if(busiWhlist==null){
			message.setOk(true);
		}else{
			message.setOk(false);
		}
		Gson gson = new Gson();
		String jsonObject = gson.toJson(message);
		return jsonObject;
	}
	
	/**
	 * 验证用户是否存在
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiWhlistManage/getUcUserByAccount",method=RequestMethod.GET)
	@ResponseBody
	public String getUcUserByAccount(@ModelAttribute("account") String account) throws Exception {
		UcUser ucUser = ucUserService.findUcUserByUserName(account);
		MessageVo message = new MessageVo();
		if(ucUser==null){
			message.setOk(false);
		}else{
			message.setOk(true);
			message.setObj(ucUser.getUserId());
		}
		Gson gson = new Gson();
		String jsonObject = gson.toJson(message);
		return jsonObject;
	}

	/**
	 * 上传图片
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "busiWhlistManage/uploadpic", method = RequestMethod.POST)
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
	
	/**
	 * @Description: 导出仓单excel表格
	 * @Author: 赵航
	 * @Date: 2015年8月27日
	 * @param busiWhlistSearchDto
	 * @param response
	 */
	@RequestMapping(value = "busiWhlistManage/exportWhlistExcel")
	public void exportWhlistExcel(@ModelAttribute BusiWhlistSearchDto busiWhlistSearchDto, HttpServletResponse response) {
		Page<BusiWhlistVo> page = new Page<BusiWhlistVo>();
		page.setPageNo(1);
		page.setPageSize(Integer.MAX_VALUE - 1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("busiWhlistSearchDto", busiWhlistSearchDto);
		page.setParams(params);
		List<BusiWhlistVo> results = busiWhlistService.findBusiWhlistsByCondition(page);
		if(CollectionUtils.isNotEmpty(results)){
			this.exportExcel(response,results);
		}else{
			this.exportExcel(response, new ArrayList<BusiWhlistVo>());
		}
	}
	
	private HttpServletResponse exportExcel(HttpServletResponse response,List<BusiWhlistVo> results) {
		try {
			
			ExcelUtil.setResponseProperties(response, ExcelUtil.createExcelName("WHLIST"));
			
			OutputStream os = response.getOutputStream();
			WritableWorkbook wbook = Workbook.createWorkbook(os);
			
			WritableCellFormat wcfTitle = ExcelUtil.getTitleFormat();
			WritableCellFormat wcfContentCenter = ExcelUtil.getContentFormat();
			
			WritableSheet wsheet = wbook.createSheet("仓单信息", 0); // sheet名称
			//设置单元格宽度
			wsheet.setColumnView(0, 25);
			wsheet.setColumnView(1, 25);
			wsheet.setColumnView(2, 25);
			wsheet.setColumnView(3, 25);
			wsheet.setColumnView(4, 25);
			wsheet.setColumnView(5, 30);
			wsheet.setColumnView(6, 25);
			wsheet.setColumnView(7, 25);
			
			// 设置单元格标题内容
			wsheet.addCell(new Label(0, 0, "仓单编号", wcfTitle));	
			wsheet.addCell(new Label(1, 0, "品种", wcfTitle));
			wsheet.addCell(new Label(2, 0, "货主", wcfTitle));
			wsheet.addCell(new Label(3, 0, "产地", wcfTitle));
			wsheet.addCell(new Label(4, 0, "等级/规格", wcfTitle));
			wsheet.addCell(new Label(5, 0, "已挂牌数量/总重量", wcfTitle));
			wsheet.addCell(new Label(6, 0, "所在仓库", wcfTitle));
			wsheet.addCell(new Label(7, 0, "入库时间", wcfTitle));
			int row = 1;//行
			int cell;//列
			// 生成报表内容
			if(results.size()<0)
				return null;
			
			for(BusiWhlistVo reslult : results){
				cell = 0;
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(reslult.getWlid()) ? "" :
					reslult.getWlid(), wcfContentCenter));//仓单编号
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(reslult.getBreedname()) ? "" :
					reslult.getBreedname(), wcfContentCenter));//品种
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(reslult.getUsername()) ? "" :
					reslult.getUsername(), wcfContentCenter));//货主
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(reslult.getOrigin()) ? "" :
					reslult.getOrigin(), wcfContentCenter));//产地
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(reslult.getGrade()) ? "" :
					reslult.getGrade(), wcfContentCenter));//等级/规格
				String dict = StringUtils.isBlank(reslult.getDictvalue()) ? "" :reslult.getDictvalue();
				String str = reslult.getWltotal().subtract(reslult.getWlsurplus()).toString() + dict;
				String total = reslult.getWltotal().toString() + dict;
				wsheet.addCell(new Label(cell++, row, str + "/" + total, wcfContentCenter));//已挂牌数量/总重量
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(reslult.getWarehousename()) ? "" :
					reslult.getWarehousename(), wcfContentCenter));//所在仓库
				wsheet.addCell(new Label(cell++, row, reslult.getWlrkdate() == null ? "" :
					TimeUtil.getYMDHMS(reslult.getWlrkdate()), wcfContentCenter));//入库时间
				row++;
			}
			wbook.write(); // 写入文件
			wbook.close();
			os.close();
			response.flushBuffer();
		} catch (Exception ex) {
			logger.error("exportExcel:", ex);
		}
		return response;
	}
}
