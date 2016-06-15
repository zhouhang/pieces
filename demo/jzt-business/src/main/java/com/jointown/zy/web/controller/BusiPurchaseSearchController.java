/**
 * Copyright © 2014-2015 珍药材版权所有
 */
package com.jointown.zy.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.jointown.zy.common.dto.PurchaseSearchDto;
import com.jointown.zy.common.enums.BusiPurchaseStatusEnum;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BusiPurchaseService;
import com.jointown.zy.common.service.CategorysService;
import com.jointown.zy.common.service.IndexService;
import com.jointown.zy.common.service.PurchaseSolrSearchService;
import com.jointown.zy.common.service.SortListService;
import com.jointown.zy.common.solr.SolrPurchaseVo;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.vo.BusiPurchaseVo;

/**
 * @ClassName: BusiPurchaseSearchController
 * @Description: 采购信息浏览
 * @Author: 赵航
 * @Date: 2015年10月22日
 * @Version: 1.0
 */
@Controller
@RequestMapping("purchaseSearch")
public class BusiPurchaseSearchController extends UserBaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(BusiPurchaseSearchController.class);
	
	@Autowired
	private PurchaseSolrSearchService purchaseSolrSearchService;
	
	@Autowired
	private BusiPurchaseService busiPurchaseService;
	
	@Autowired 
	private CategorysService categorysService;
	
	@Autowired 
	private IndexService indexService;
	
	@Autowired
	private SortListService sortListService;
	
	/**
	 * @Description: 采购信息浏览-列表部分
	 * @Author: 赵航
	 * @Date: 2015年10月22日
	 * @param query
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String searchPurchaseList(@ModelAttribute("query") PurchaseSearchDto query, ModelMap model,HttpServletRequest request){
		logger.info("BusiPurchaseSearchController.searchPurchaseList");
		try {
			if(query == null){
				query = new PurchaseSearchDto();
			}
			query.setPageSize(10);
			Page<SolrPurchaseVo> page = purchaseSolrSearchService.searchSolrPurchasePage(query);
			model.addAttribute("page", page);
			model.addAttribute("stautsMap", BusiPurchaseStatusEnum.toMap());
			List<String> breedNames = purchaseSolrSearchService.searchHotPurchaseBreed(13);
			model.addAttribute("breedNames", breedNames);
			model.addAttribute("defaultTradersName", SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.name"));
			model.addAttribute("defaultTradersPhone", SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.phone"));
			
			String tunnage = indexService.getWarrantsTunnage();
			model.put("tunnage", tunnage);
			List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
			model.put("categorylist", categorylist);
			List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
			model.put("sortList", sortList);
			model.put("url", WebUtils.getPathWithinApplication((HttpServletRequest)request));
			return "purchase/purchaseSearchList";
		} catch (Exception e) {
			logger.error("BusiPurchaseSearchController.searchPurchaseList error is " + e.getMessage());
			//model.addAttribute("error", e.getMessage());
			return "error";
		}
	}
	
	/**
	 * @Description: 采购信息浏览-最近成交部分
	 * @Author: 赵航
	 * @Date: 2015年10月22日
	 * @return
	 */
	@RequestMapping("recentlyPurchases")
	public @ResponseBody String getRecentlyPurchaseList(){
		logger.info("BusiPurchaseSearchController.getRecentlyPurchaseList");
		JsonObject json = new JsonObject();
		try {
			List<BusiPurchaseVo> list = busiPurchaseService.selectRecentlyPurchaseList(50);
			json.addProperty("state", "success");
			json.addProperty("info", GsonFactory.toJson(list));
		} catch (Exception e) {
			logger.error("BusiPurchaseSearchController.getRecentlyPurchaseList, error is " + e.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("info", "获取数据异常");
		}
		return json.toString();
	}

}
