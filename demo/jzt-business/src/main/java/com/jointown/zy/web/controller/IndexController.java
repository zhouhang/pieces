package com.jointown.zy.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jointown.zy.common.service.IndexService;
import com.jointown.zy.common.service.SortListService;


@RequestMapping("/index")
@Controller(value = "indexController")
public class IndexController  extends UserBaseController{
	
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	@Autowired 
	private IndexService indexService;
	@Autowired
	private SortListService sortListService;
	
	@RequestMapping("/getProcurement")
	public String getProcurement(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		String tunnage = indexService.getWarrantsTunnage();
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		modelMap.put("sortList", sortList);
		modelMap.put("tunnage", tunnage);
		modelMap.put("categorylist", categorylist);
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		modelMap.put("url", url);
		return "/homepage/procurement";
	}  
	@RequestMapping("/getWarehousing")
	public String getWarehousing(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		String tunnage = indexService.getWarrantsTunnage();
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		modelMap.put("sortList", sortList);
		modelMap.put("tunnage", tunnage);
		modelMap.put("categorylist", categorylist);
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		modelMap.put("url", url);
		return "/homepage/warehousing";
	}  
	@RequestMapping("/getStockService")
	public String getStockService(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		String tunnage = indexService.getWarrantsTunnage();
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		modelMap.put("sortList", sortList);
		modelMap.put("tunnage", tunnage);
		modelMap.put("categorylist", categorylist);
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		modelMap.put("url", url);
		return "/homepage/stock-service";
	}  
	@RequestMapping("/getBankingService")
	public String getBankingService(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		String tunnage = indexService.getWarrantsTunnage();
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		modelMap.put("sortList", sortList);
		modelMap.put("tunnage", tunnage);
		modelMap.put("categorylist", categorylist);
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		modelMap.put("url", url);
		return "/homepage/banking-service";
	}  
	@RequestMapping("/getInPurchase")
	public String getInPurchase(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		//added by biran 20150617 head的导航栏使用
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		modelMap.put("url", url);
		return "/homepage/in-purchase";
	} 
	@RequestMapping("/getInPledge")
	public String getInPledge(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		//added by biran 20150617 head的导航栏使用
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		modelMap.put("url", url);
		return "/homepage/in-pledge";
	} 
	@RequestMapping("/getInStock")
	public String getInStock(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		//added by biran 20150617 head的导航栏使用
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		modelMap.put("url", url);
		return "/homepage/in-stock";
	} 
	@RequestMapping("/getServicePlatform")
	public String getServicePlatform(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		String tunnage = indexService.getWarrantsTunnage();
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		modelMap.put("sortList", sortList);
		modelMap.put("tunnage", tunnage);
		modelMap.put("categorylist", categorylist);
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		modelMap.put("url", url);
		return "/homepage/service-platform";
	} 
	@RequestMapping("/getQualitySystem")
	public String getQualitySystem(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		String tunnage = indexService.getWarrantsTunnage();
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		modelMap.put("sortList", sortList);
		modelMap.put("tunnage", tunnage);
		modelMap.put("categorylist", categorylist);
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		modelMap.put("url", url);
		return "/homepage/quality-system";
	} 
	@RequestMapping("/getSpecialFruit")
	public String getSpecialFruit(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		String tunnage = indexService.getWarrantsTunnage();
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		modelMap.put("sortList", sortList);
		modelMap.put("tunnage", tunnage);
		modelMap.put("categorylist", categorylist);
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		modelMap.put("url", url);
		return "/homepage/specialFruit";
	}  
	
}
