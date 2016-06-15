package com.jointown.zy.web.controller;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.dto.BusiPurchaseDto;
import com.jointown.zy.common.dto.BusiPurchaseSearchDto;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.BusiPurchaseStatusEnum;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BusiPurchase;
import com.jointown.zy.common.model.BusiQuote;
import com.jointown.zy.common.model.DictInfo;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BreedService;
import com.jointown.zy.common.service.BusiListingService;
import com.jointown.zy.common.service.BusiPurchaseService;
import com.jointown.zy.common.service.BusiQuoteService;
import com.jointown.zy.common.service.DictInfoService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.FileUtil;
import com.jointown.zy.common.vo.BusiPurchaseVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * 
 * @ClassName: BusiPurchaseController
 * @Description: 珍药采购Controller
 * @Author: fanyuna
 * @Date: 2015年10月12日
 * @Version: 1.0
 */
@Controller
@RequestMapping(value="/purchase")
public class BusiPurchaseController {

	private static final Logger log = LoggerFactory.getLogger(BusiPurchaseController.class);
	
	@Resource
	private UcUserService ucUserService;
	@Resource
	private BreedService breedService;
	@Resource 
	private DictInfoService dictInfoService;
	@Resource
	private BusiListingService busiListingService;
	@Resource
	private BusiPurchaseService  busiPurchaseService;
	@Resource
	private BusiQuoteService busiQuoteService;
	
	/**
	 * 
	 * @Description: 跳转至发布采购页
	 * @Author: fanyuna
	 * @Date: 2015年10月13日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/pub")
	public String toPubPurchase(HttpServletRequest request,ModelMap model,@RequestParam(value="purchaseId", required = false)String purchaseId){
		//登录人信息
		UcUserVo user =getUserInfo(request);
		//专属交易员信息
		Map<String,String> salesmanInfo = busiPurchaseService.getSalesmanOfPurchase(user.getUserId());
		//采购单位与联系人 默认值
		String orgName = user.getCompanyName();
		if(user.getCertifyStatus().intValue()!=0)   //实名认证
			orgName=ucUserService.getCertifyNameByUserId(user.getUserId());
		
		//采购品种数量单位 
		List<Map<Object,Object>> unitMapList = dictInfoService.getLimitDictList("weight_unit");
		
		if(StringUtils.isNotBlank(purchaseId)){
			try {
				BusiPurchase  purchase = busiPurchaseService.getPurchaseDetailById(purchaseId);
				model.addAttribute("purchase", purchase);
			} catch (Exception e) {
				log.error("重新提交审核不通过的采购单时，跳转至修改页面前，查询采购信息异常，异常信息："+e.getMessage(), e);
				return "error";
			}
		}
		
		model.addAttribute("salesmanInfo", salesmanInfo);
		model.addAttribute("orgName", orgName);
		model.addAttribute("contactTel", user.getMobile());
		model.addAttribute("unitMapList", unitMapList);
		
		return "purchase/singlePurchase";
	}
	
	/**
	 * 
	 * @Description: 跳转至批量发布页面
	 * @Author: fanyuna
	 * @Date: 2015年10月15日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/batchPub")
	public String toBatchPubPurchase(HttpServletRequest request,ModelMap model){
		//登录人信息
			UcUserVo user =getUserInfo(request);
		//专属交易员信息
			Map<String,String> salesmanInfo = busiPurchaseService.getSalesmanOfPurchase(user.getUserId());
			
			model.addAttribute("salesmanInfo", salesmanInfo);
			return "purchase/batchPurchase";
	}
	
	/**
	 * 
	 * @Description: 单条发布采购页面  根据关键字搜索品种
	 * @Author: fanyuna
	 * @Date: 2015年10月14日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getBreedByKeyword")
	@ResponseBody
	public List<Breed> getBreedByKeyword(@RequestParam(value="keyWord",defaultValue="")String keyWord,HttpServletRequest request){
//		String keyWord = request.getParameter("keyWord");
		List<Breed> breedList = null;
		if(StringUtils.isNotBlank(keyWord))
		  breedList = breedService.selectBreedByKeyword(keyWord);
		return breedList==null?new ArrayList<Breed>():breedList;
	}
	
	/**
	 * 
	 * @Description: 单条发布
	 * @Author: fanyuna
	 * @Date: 2015年10月12日
	 * @param busiPurchaseDto
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/singleSave",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addPurchase(@ModelAttribute BusiPurchaseDto busiPurchaseDto,HttpServletRequest request){
		log.info("BusiPurchaseController.singleSave controller");
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			//验证是否登陆
			UcUserVo user =getUserInfo(request);
			if(user==null){
				throw new Exception("请登录再操作！");
			}
			//检验参数
			String validMsg = busiPurchaseDto.validate();
			if (!"success".equals(validMsg)) {
				throw new Exception(validMsg);
			}
			//获取批次号
			String purchaseCode = busiPurchaseService.createPurchaseCode(user.getUserId());
			int num = busiPurchaseService.savePurchase(busiPurchaseDto, user,purchaseCode);
			if(num>0){
			//修改
			  if(StringUtils.isNotBlank(busiPurchaseDto.getPurchaseId())){
				  purchaseCode = busiPurchaseDto.getPurchaseCode();
				  map.put("url","/purchase/manager");
			  }else{
				//查同品种的4个最低价格的挂牌
				Map<String,Object> conMap = new HashMap<String,Object>();
					conMap.put("breedId", busiPurchaseDto.getBreedId());
					conMap.put("listingflag", BusiListingFlagEnum.LISTING.getCode());
					conMap.put("num", 4);
					conMap.put("picindex", 1);
				List<Map<String, Object>> listingInfos = busiListingService.selectListingByBreed(conMap);
				map.put("listingInfos", listingInfos);
				map.put("url","/purchase/pub");
			  }
				map.put("ok", true);
				map.put("msg", "采购单已提交审核，采购批次号为："+purchaseCode+"。您可以在用户中心查看审核状态。");
				
				
			}else{
				map.put("ok", false);
				map.put("msg", "发布采购失败！");
			}
			
		}catch(Exception e){
			if(StringUtils.isNotBlank(busiPurchaseDto.getPurchaseId())){
				log.error("重新提交采购单失败："+e.getMessage(), e);
				map.put("msg", "重新提交采购单失败！");
				
			}else{
				log.error("发布采购失败："+e.getMessage(), e);
				map.put("msg", "发布采购失败！");
			}
			
			map.put("ok", false);
			
			
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 下载批量采购模板
	 * @Author: fanyuna
	 * @Date: 2015年10月13日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/download")
	@ResponseBody
	public String  downloadExcelModel(HttpServletRequest request,HttpServletResponse response){
		try{
			String path = request.getSession().getServletContext().getRealPath("/");
			String fileName="批量采购模版.xlsx";
			log.info("项目的全路径："+path);
//			String filePath = path+"\\purchase\\"+fileName;
			String filePath = path+File.separator+"purchase"+File.separator+fileName;
			/*//windows
			if("\\".equals(File.separator)){
				filePath = path.replaceAll("/", "\\")+"\\purchase\\"+fileName;
			}
			//linux
			if("/".equals(File.separator)){
				filePath = path+"purchase/"+fileName;
			}*/
			log.info("批量采购模板路径："+filePath);
			String userAgent = request.getHeader("User-Agent");
			//针对IE或者以IE为内核的浏览器：
			if (userAgent.contains("MSIE")||userAgent.contains("Trident")) {
			fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			} else {
			//非IE浏览器的处理：
			fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
			}
			boolean boo = FileUtil.downLoadFile(filePath, response, fileName, "xls");
			if(!boo)
				return "fail";
		}catch(Exception e){
			log.error("下载批量采购模板出错："+e.getMessage(),e);
			return "fail";
		}
		return "success";
		
	}
	
	/**
	 * @Description: 批量采购 
	 * @Author: fanyuna
	 * @Date: 2015年10月16日
	 * @param file
	 * @param request
	 * @param response 改成返回json字符串
	 * @return
	 */
	@RequestMapping(value="/batchSave",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String uploadExcel(@RequestParam("excelFile") MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		  try {
			  
			//验证是否登陆
			UcUserVo user =getUserInfo(request);
			if(user==null){
				throw new Exception("请登录再操作！");
					}
			
			InputStream fis =  file.getInputStream();
			
			//判断文件类型是否为Excel
			/*FileType fileType = FileTypeJudge.getType(fis);
			if(!(fileType!=null && (fileType.equals(FileType.XLS_DOC) || fileType.equals(FileType.XLSX_DOCX)))){
				 log.error("批量发布采购时，上传文件类型不正确！");
				 map.put("ok", false);
				 map.put("msg", "请选择正确的模版文件！");
				 return map;
			}*/
			org.apache.poi.ss.usermodel.Workbook wb =  WorkbookFactory.create(fis); 
			org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
			
			int rows = busiPurchaseService.getExcelRows(wb,sheet);
			if(rows==0){
				fis.close();
				log.error("批量发布采购时，文件行数不正确，行数为："+rows);
				map.put("ok", false);
				map.put("msg", "本次采购的品种数量为0，请确认选择的文件是否正确。");
				JSONObject jsonObject = new JSONObject(map);
				return jsonObject.toString();
			}
			if(rows>100){
				fis.close();
				log.error("批量发布采购时，文件行数不正确，行数为："+rows);
				map.put("ok", false);
				map.put("msg", "批量采购上限为100条，请修改模版后再次提交！");
				JSONObject jsonObject = new JSONObject(map);
				return jsonObject.toString();
			}
			
			//获取批次号
			String purchaseCode = busiPurchaseService.createPurchaseCode(user.getUserId());
			
			
			boolean boo = busiPurchaseService.batchSavePurchase(user, purchaseCode,wb,sheet);
			if(boo){
				
				map.put("ok", true);
				map.put("msg", "采购单已提交审核，采购批次号为："+purchaseCode+"，您可以在用户中心查看审核状态。");
				map.put("url","/purchase/batchPub");
				JSONObject jsonObject = new JSONObject(map);
				return jsonObject.toString();
			}else{
				map.put("ok", false);
				map.put("msg", "批量发布采购失败！");
				JSONObject jsonObject = new JSONObject(map);
				return jsonObject.toString();
			}
			
		  } catch (Exception e) {
			  log.error("批量发布采购失败："+e.getMessage(), e);
			 map.put("ok", false);
			 map.put("msg", "批量发布采购失败！");
			 JSONObject jsonObject = new JSONObject(map);
				return jsonObject.toString();
		}
//		  return map;
		
	}
	
	/**
	 * 
	 * @Description: 用户中心 我的采购  采购管理列表
	 * @Author: fanyuna
	 * @Date: 2015年10月21日
	 * @param request
	 * @param pageNo
	 * @param busiPurchaseSearchDto
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/manager")
	public String myPurchasemanager(HttpServletRequest request,@RequestParam(value="pageNo", required = false) String pageNo, @ModelAttribute BusiPurchaseSearchDto busiPurchaseSearchDto, ModelMap model){
		log.info("BusiPurchaseController.manager controller");
	try {
			  
			//验证是否登陆
			UcUserVo user =getUserInfo(request);
			if(user==null){
				throw new Exception("请登录再操作！");
					}
		//专属交易员信息
			Map<String,String> salesmanInfo = busiPurchaseService.getSalesmanOfPurchase(user.getUserId());
		
		//采购状态
			Map<String,String> statusMap = BusiPurchaseStatusEnum.toMap();
			
		Page<BusiPurchaseVo> page = new Page<BusiPurchaseVo>();
		//当前页
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}
		page.setPageSize(ConfigConstant.USER_CENTER_PAGE_SIZE);
		
		Map<String, Object> params = new HashMap<String, Object>();
			params.put("busiPurchaseSearchDto", busiPurchaseSearchDto);
			params.put("purchaser", user.getUserName());
			
		page.setParams(params);
		
		List<BusiPurchaseVo> myPurchaseList = busiPurchaseService.selectMyPurchaseListBy(page);
		page.setResults(myPurchaseList);
		
		model.put("salesmanInfo", salesmanInfo);
		model.put("statusMap", statusMap);
		model.put("page", page);
		
		return "purchase/myPurchaseList";
		
		}catch(Exception e){
			log.error("用户中心，获取我的采购列表信息异常，异常信息："+e.getMessage(),e);
			return "error";
			
		}
	}
	
	/**
	 * 
	 * @Description: 用户中心，根据ID获取采购详情
	 * @Author: fanyuna
	 * @Date: 2015年10月21日
	 * @param request
	 * @param purchaseId
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail")
	public String getPurchaseDetailById(HttpServletRequest request,@RequestParam(value="purchaseId", required = true) String purchaseId, ModelMap model){
		try {
			//验证是否登陆
			UcUserVo user =getUserInfo(request);
			if(user==null){
				throw new Exception("请登录再操作！");
					}
		//专属交易员信息
			Map<String,String> salesmanInfo = busiPurchaseService.getSalesmanOfPurchase(user.getUserId());
			
			BusiPurchase purchase = busiPurchaseService.getPurchaseDetailById(purchaseId);
			if(StringUtils.isNotBlank(purchase.getWunitCode())){
				List<DictInfo> dictInfoList = dictInfoService.selectDictByCode(purchase.getWunitCode());
				if(dictInfoList!= null && dictInfoList.size()>0){
					model.put("qualityUnit",dictInfoList.get(0).getDictValue()); //重量单位值
				}
			}
			
			model.put("salesmanInfo", salesmanInfo);
			model.put("purchase", purchase);
			return "purchase/purchaseDetail";
			
		} catch (Exception e) {
			log.error("用户中心，根据ID获取采购详情异常，异常信息："+e.getMessage(),e);
			return "error";
		}
	}
	
	/**
	 * 
	 * @Description: 根据采购ID获取其报价列表  分页查询
	 * @Author: fanyuna
	 * @Date: 2015年10月22日
	 * @param request
	 * @param purchaseId
	 * @return
	 */
	@RequestMapping(value="/getPurchaseQuoteBy")
	@ResponseBody
	public Map<String, Object> getPurchaseQuoteBy(HttpServletRequest request,@RequestParam(value="purchaseId", required = true) String purchaseId){
		log.info("BusiPurchaseController.getPurchaseQuoteBy controller");
		Page<BusiQuote> page = new Page<BusiQuote>();
		Map<String, Object> map = new HashMap<String, Object>();
			map.put("purchaseId", purchaseId);
			map.put("order", "status");
		page.setPageSize(5); // 每页5条
		page.setPageNo(StringUtils.isNotBlank(request.getParameter("pageNo")) ? Integer
				.parseInt(request.getParameter("pageNo")) : 1);
		page.setParams(map);
		List<BusiQuote> purchaseQuoteList = busiQuoteService.selectQuotePageByPurchaseId(page);
		page.setResults(purchaseQuoteList);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("page", page);
		return resMap;
	}
	
	public UcUserVo getUserInfo(HttpServletRequest... requests){
    	//header登录名称使用
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		//left菜单选中使用
		if(userinfo!=null && (!ArrayUtils.isEmpty(requests))){
			userinfo.setUrl(requests[0].getRequestURI());
		}
		return userinfo;
    }
}
