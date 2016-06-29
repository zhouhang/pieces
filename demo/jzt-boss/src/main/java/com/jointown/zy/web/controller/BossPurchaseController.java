/**
 * Copyright © 2014-2015 珍药材版权所有
 */
package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.jointown.zy.common.dto.BossPurchaseAuditQueryDto;
import com.jointown.zy.common.dto.BossPurchaseManageQueryDto;
import com.jointown.zy.common.enums.BusiPurchaseStatusEnum;
import com.jointown.zy.common.model.BusiQuote;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BossPurchaseService;
//import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.vo.BusiPurchaseVo;

/**
 * @ClassName: BossPurchaseController
 * @Description: 后台-采购操作Controller
 * @Author: 赵航
 * @Date: 2015年10月13日
 * @Version: 1.0
 */
@Controller
public class BossPurchaseController extends UserBaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(BossPurchaseController.class);

	@Autowired
	private BossPurchaseService bossPurchaseService;
	
	/**
	 * @Description: 审核采购信息列表
	 * @Author: 赵航
	 * @Date: 2015年10月13日
	 * @param query
	 * @param model
	 * @return
	 */
	@RequestMapping("purchaseAudit")
	public String getAuditPurchasePage(@ModelAttribute("query") BossPurchaseAuditQueryDto query, ModelMap model){
		logger.info("BossPurchaseController.getAuditPurchasePage");
		try {
			if(query == null){
				query = new BossPurchaseAuditQueryDto();
			}
			query.setPageSize(10);
			Page<BusiPurchaseVo> page = bossPurchaseService.selectPurchaseAuditPage(query);
			model.addAttribute("page", page);
			Map<String, String> stautsMap = new LinkedHashMap<String, String>();
			stautsMap.put(BusiPurchaseStatusEnum.AUDIT_WAITING.getCode(), BusiPurchaseStatusEnum.AUDIT_WAITING.getCodeName());
			stautsMap.put(BusiPurchaseStatusEnum.AUDIT_FAILURE.getCode(), BusiPurchaseStatusEnum.AUDIT_FAILURE.getCodeName());
			model.addAttribute("stautsMap", stautsMap);
			//model.addAttribute("defaultTradersName", SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.name"));
			return "public/purchaseAuditList";
		} catch (Exception e) {
			logger.error("BossPurchaseController.getAuditPurchasePage error is " + e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}
	
	/**
	 * @Description: 审核采购信息列表页的查看详情
	 * @Author: 赵航
	 * @Date: 2015年10月13日
	 * @param purchaseId
	 * @param model
	 * @return
	 */
	@RequestMapping("purchaseAudit/detaiInfo")
	public String getAuditPurchaseDetai(@RequestParam(value="purchaseId",required=true) String purchaseId, ModelMap model){
		logger.info("BossPurchaseController.getAuditPurchaseDetai");
		try {
			BusiPurchaseVo purchase = bossPurchaseService.selectPurchaseDetail(purchaseId);
			model.addAttribute("purchase", purchase);
			return "public/purchaseAuditDetail";
		} catch (Exception e) {
			logger.error("BossPurchaseController.getAuditPurchaseDetai error is " + e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}
	
	/**
	 * @Description: 后台-采购信息审核通过
	 * @Author: 赵航
	 * @Date: 2015年10月13日
	 * @param purchaseId
	 * @return
	 */
	@RequestMapping("purchaseAudit/passed")
	public @ResponseBody String auditPurchasePassed(@RequestParam(value="purchaseId", required=true) String purchaseId,
			@RequestParam(value="purchaseCode", required=true) String purchaseCode){
		logger.info("BossPurchaseController.auditPurchasePassed");
		JsonObject json = new JsonObject();
		try {
			bossPurchaseService.changePurchaseAuditStatus(purchaseId, purchaseCode, BusiPurchaseStatusEnum.OFFER_WAITING.getCode(), null);
			json.addProperty("state", "success");
			json.addProperty("info", "审核通过操作成功");
		} catch (Exception e) {
			logger.error("BossPurchaseController.auditPurchasePassed, error is " + e.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("info", "审核通过操作失败");
		}
		return json.toString();
	}
	
	/**
	 * @Description: 后台-采购信息审核不通过
	 * @Author: 赵航
	 * @Date: 2015年10月13日
	 * @param purchaseId
	 * @param rejectReason
	 * @return
	 */
	@RequestMapping("purchaseAudit/unpassed")
	public @ResponseBody String auditPurchaseUnpassed(@RequestParam(value="purchaseId", required=true) String purchaseId,
			@RequestParam(value="purchaseCode", required=true) String purchaseCode,
			@RequestParam(value="rejectReason", required=true) String rejectReason){
		logger.info("BossPurchaseController.auditPurchaseUnpassed");
		JsonObject json = new JsonObject();
		try {
			bossPurchaseService.changePurchaseAuditStatus(purchaseId, purchaseCode, BusiPurchaseStatusEnum.AUDIT_FAILURE.getCode(), rejectReason);
			json.addProperty("state", "success");
			json.addProperty("info", "审核不通过操作成功");
		} catch (Exception e) {
			logger.error("BossPurchaseController.auditPurchaseUnpassed, error is " + e.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("info", "审核不通过操作失败");
		}
		return json.toString();
	}
	
	/**
	 * @Description: 后台-采购信息管理列表
	 * @Author: 赵航
	 * @Date: 2015年10月14日
	 * @param query 查询条件
	 * @param model
	 * @return
	 */
	@RequestMapping("purchaseManage")
	public String getManagePurchasePage(@ModelAttribute("query") BossPurchaseManageQueryDto query, ModelMap model){
		logger.info("BossPurchaseController.getManagePurchasePage");
		try {
			if(query == null){
				query = new BossPurchaseManageQueryDto();
			}
			query.setPageSize(10);
			Page<BusiPurchaseVo> page = bossPurchaseService.selectPurchaseManagePage(query);
			model.addAttribute("page", page);
			Map<String, String> stautsMap = new LinkedHashMap<String, String>();
			stautsMap.put(BusiPurchaseStatusEnum.OFFER_WAITING.getCode(), BusiPurchaseStatusEnum.OFFER_WAITING.getCodeName());
			stautsMap.put(BusiPurchaseStatusEnum.NEGOTIATING.getCode(), BusiPurchaseStatusEnum.NEGOTIATING.getCodeName());
			stautsMap.put(BusiPurchaseStatusEnum.DEAL_SUCCESS.getCode(), BusiPurchaseStatusEnum.DEAL_SUCCESS.getCodeName());
			stautsMap.put(BusiPurchaseStatusEnum.FINISHED.getCode(), BusiPurchaseStatusEnum.FINISHED.getCodeName());
			model.addAttribute("stautsMap", stautsMap);
			//model.addAttribute("defaultTradersName", SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.name"));
			return "public/purchaseManageList";
		} catch (Exception e) {
			logger.error("BossPurchaseController.getManagePurchasePage error is " + e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}
	
	/**
	 * @Description: 采购信息管理列表页的查看详情
	 * @Author: 赵航
	 * @Date: 2015年10月14日
	 * @param purchaseId
	 * @param model
	 * @return
	 */
	@RequestMapping("purchaseManage/detaiInfo")
	public String getManagePurchaseDetai(@RequestParam(value="purchaseId",required=true) String purchaseId, ModelMap model){
		logger.info("BossPurchaseController.getManagePurchaseDetai");
		try {
			BusiPurchaseVo purchase = bossPurchaseService.selectPurchaseDetail(purchaseId);
			model.addAttribute("purchase", purchase);
			return "public/purchaseManageDetail";
		} catch (Exception e) {
			logger.error("BossPurchaseController.getManagePurchaseDetai error is " + e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}
	
	/**
	 * @Description: 采购信息管理列表页的查看详情的报价列表
	 * @Author: 赵航
	 * @Date: 2015年10月14日
	 * @param purchaseId 采购ID
	 * @param pageNo 页码
	 * @return
	 */
	@RequestMapping("purchaseManage/quoteList")
	@ResponseBody
	public Map<String, Object> getManagePurchaseQuoteList(@RequestParam(value="purchaseId",required=true) String purchaseId,
			@RequestParam("pageNo") String pageNo) {
		logger.info("BossPurchaseController.getManagePurchaseQuoteList");
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			Integer iPageNo = StringUtils.isNotBlank(pageNo) ? Integer.valueOf(pageNo) : 1;
			Page<BusiQuote> page = bossPurchaseService.selectPurchaseQuotePage(purchaseId, iPageNo);
			resMap.put("page", page);
		} catch (Exception e) {
			logger.error("BossPurchaseController.getManagePurchaseQuoteList error is " + e.getMessage());
			resMap.put("page", null);
		}
		return resMap;
	}
	
	/**
	 * @Description: 后台-采购信息管理-标记为已交易
	 * @Author: 赵航
	 * @Date: 2015年10月15日
	 * @param purchaseId
	 * @param quoteId
	 * @return
	 */
	@RequestMapping("purchaseManage/dealsuccess")
	public @ResponseBody String purchaseDealSuccess(@RequestParam(value="purchaseId", required=true) String purchaseId,
			@RequestParam(value="quoteId", required=true) String quoteId){
		logger.info("BossPurchaseController.purchaseDealSuccess");
		JsonObject json = new JsonObject();
		try {
			bossPurchaseService.purchaseDealSuccess(purchaseId, quoteId);
			json.addProperty("state", "success");
			json.addProperty("info", "标记已交易操作成功");
		} catch (Exception e) {
			logger.error("BossPurchaseController.purchaseDealSuccess, error is " + e.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("info", "标记已交易操作失败");
		}
		return json.toString();
	}
}
