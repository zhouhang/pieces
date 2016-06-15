/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jointown.zy.common.dto.BossOrderDepositDto;
import com.jointown.zy.common.dto.BossOrderDepositQueryDto;
import com.jointown.zy.common.enums.BusiOrderDepositStateEnum;
import com.jointown.zy.common.enums.BusiOrderDepositTypeEnum;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BossOrderService;
import com.jointown.zy.common.service.BusiOrderDepositService;
import com.jointown.zy.common.util.GetBaseInfo;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.vo.BossOrderDepositVo;
import com.jointown.zy.common.vo.BossOrderInfoVo;
import com.jointown.zy.common.vo.RefuseRemitFlowVo;

/**
 * @ClassName: BossOrderDepositController
 * @Description: 订单划账处理
 * @Author: 赵航
 * @Date: 2015年5月18日
 * @Version: 1.0
 */
@Controller
public class BossOrderDepositController {
	
	private static final Logger logger = LoggerFactory.getLogger(BossOrderDepositController.class);
	
	@Autowired
	private BusiOrderDepositService busiOrderDepositService;
	
	@Autowired
	private BossOrderService bossOrderService;
	
	/**
	 * @Description: 后台-退款处理列表
	 * @Author: 赵航
	 * @Date: 2015年5月18日
	 * @param query
	 * @param model
	 * @return String
	 */
	@RequestMapping("depositFailed")
	public String getOrderDepositListByFailed(@ModelAttribute("query") BossOrderDepositQueryDto query, ModelMap model){
		logger.info("BossOrderDepositController.getOrderDepositListByFailed");
		try {
			if(query == null){
				query = new BossOrderDepositQueryDto();
			}
			query.setDepositType(BusiOrderDepositTypeEnum.ORDER_REFUND_DEPOSIT.getCode());//订单申退划账
			query.setPageSize(10);//一页显示10条
			Page<BossOrderDepositVo> page = busiOrderDepositService.selectDepositList(query);
			model.addAttribute("page", page);
			model.addAttribute("stateMap", BusiOrderDepositStateEnum.toMap());
			return "public/order_return";
		} catch (Exception e) {
			logger.error("BossOrderDepositController.getOrderDepositListByFailed, error is " + e.getMessage());
			return "error";
		}
	}
	
	/**
	 * @Description: 后台-退款处理-查看订单详情
	 * @Author: 赵航
	 * @Date: 2015年5月19日
	 * @param orderId
	 * @return String
	 */
	@RequestMapping("depositFailed/orderinfo")
	public @ResponseBody String selectDepositFailedOrderInfo(@RequestParam(value="orderId", required=true) String orderId){
		logger.info("BossOrderDepositController.selectDepositFailedOrderInfo");
		JsonObject json = new JsonObject();
		try {
			BossOrderInfoVo orderInfo = bossOrderService.selectOrderInfoById(orderId);
			if(orderInfo == null){
				json.addProperty("state", "failure");
				json.addProperty("result", ErrorRepository.NOT_EXIST.getMessage("订单"));
			} else {
				json.addProperty("state", "success");
				json.addProperty("result", GsonFactory.createGson("yyyy-MM-dd HH:mm:ss").toJson(orderInfo));
			}
		} catch (Exception e) {
			logger.error("BossOrderDepositController.selectDepositFailedOrderInfo, error is " + e.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", ErrorRepository.UNKNOW_ERROR.getMessage());
		}
		return json.toString();
	}
	
	/**
	 * @Description: 后台-退款处理-退款
	 * @Author: 赵航
	 * @Date: 2015年5月19日
	 * @param deposit
	 * @param request
	 * @return String
	 */
	@RequestMapping("depositFailed/executeDeposit")
	public @ResponseBody String executeOrderFailedDeposit(@ModelAttribute("deposit") BossOrderDepositDto deposit,
			HttpServletRequest request){
		logger.info("BossOrderDepositController.executeOrderFailedDeposit");
		JsonObject json = new JsonObject();
		if(deposit == null){
			deposit = new BossOrderDepositDto();
		}
		// 对deposit做校验
		if(!depositCheck(deposit, json)){
			return json.toString();
		}
		deposit.setOperattorId(GetBaseInfo.getBossUserId());
		deposit.setOperattorIp(GetBaseInfo.getIp(request));
		try {
			String rs = busiOrderDepositService.todoOrderDeposit(deposit);
			if("success".equals(rs)){
				json.addProperty("status", "y");
				json.addProperty("info", "已成功申请，等待财务处理！");
			} else {
				json.addProperty("status", "n");
				json.addProperty("info", "操作失败");
			}
		} catch (Exception e) {
			logger.error("BossOrderDepositController.executeOrderFailedDeposit, error is " + e.getMessage());
			json.addProperty("status", "n");
			json.addProperty("info", "操作失败");
		}
		return json.toString();
	}

	/**
	 * @Description: 后台-订单分润列表
	 * @Author: 赵航
	 * @Date: 2015年5月18日
	 * @param query
	 * @param model
	 * @return String
	 */
	@RequestMapping("depositFinish")
	public String getOrderDepositListByFinished(@ModelAttribute("query") BossOrderDepositQueryDto query, ModelMap model){
		logger.info("BossOrderDepositController.getOrderDepositListByFinished");
		try {
			if(query == null){
				query = new BossOrderDepositQueryDto();
			}
			query.setDepositType(BusiOrderDepositTypeEnum.ORDER_FINISHED_DEPOSIT.getCode());//订单完成划账
			query.setPageSize(10);//一页显示10条
			Page<BossOrderDepositVo> page = busiOrderDepositService.selectDepositList(query);
			model.addAttribute("page", page);
			model.addAttribute("stateMap", BusiOrderDepositStateEnum.toMap());
			return "public/order_profit";
		} catch (Exception e) {
			logger.error("BossOrderDepositController.getOrderDepositListByFinished, error is " + e.getMessage());
			return "error";
		}
	}
	
	/**
	 * @Description: 后台-订单分润-查看订单详情
	 * @Author: 赵航
	 * @Date: 2015年5月19日
	 * @param orderId
	 * @return String
	 */
	@RequestMapping("depositFinish/orderinfo")
	public @ResponseBody String selectDepositFinishOrderInfo(@RequestParam(value="orderId", required=true) String orderId){
		logger.info("BossOrderDepositController.selectDepositFinishOrderInfo");
		JsonObject json = new JsonObject();
		try {
			BossOrderInfoVo orderInfo = bossOrderService.selectOrderInfoById(orderId);
			if(orderInfo == null){
				json.addProperty("state", "failure");
				json.addProperty("result", ErrorRepository.NOT_EXIST.getMessage("订单"));
			} else {
				json.addProperty("state", "success");
				json.addProperty("result", GsonFactory.createGson("yyyy-MM-dd HH:mm:ss").toJson(orderInfo));
			}
		} catch (Exception e) {
			logger.error("BossOrderDepositController.selectDepositFinishOrderInfo, error is " + e.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", ErrorRepository.UNKNOW_ERROR.getMessage());
		}
		return json.toString();
	}
	
	/**
	 * @Description: 后台-订单分润-划账
	 * @Author: 赵航
	 * @Date: 2015年5月19日
	 * @param deposit
	 * @param request
	 * @return String
	 */
	@RequestMapping("depositFinish/executeDeposit")
	public @ResponseBody String executeOrderFinishDeposit(@ModelAttribute("deposit") BossOrderDepositDto deposit,
			HttpServletRequest request){
		logger.info("BossOrderDepositController.executeOrderFinishDeposit");
		JsonObject json = new JsonObject();
		if(deposit == null){
			deposit = new BossOrderDepositDto();
		}
		// 对deposit做校验
		if(!depositCheck(deposit, json)){
			return json.toString();
		}
		deposit.setOperattorId(GetBaseInfo.getBossUserId());
		deposit.setOperattorIp(GetBaseInfo.getIp(request));
		try {
			String rs = busiOrderDepositService.todoOrderDeposit(deposit);
			if("success".equals(rs)){
				json.addProperty("status", "y");
				json.addProperty("info", "已成功申请，等待财务处理！");
			} else {
				json.addProperty("status", "n");
				json.addProperty("info", "操作失败");
			}
		} catch (Exception e) {
			logger.error("BossOrderDepositController.executeOrderFinishDeposit, error is " + e.getMessage());
			json.addProperty("status", "n");
			json.addProperty("info", "操作失败");
		}
		return json.toString();
	}
	
	/**
	 * @Description: 后台-过期订单处理列表
	 * @Author: 赵航
	 * @Date: 2015年5月19日
	 * @param query
	 * @param model
	 * @return String
	 */
	@RequestMapping("depositOverdue")
	public String getOrderDepositListByOverdue(@ModelAttribute("query") BossOrderDepositQueryDto query, ModelMap model){
		logger.info("BossOrderDepositController.getOrderDepositListByOverdue");
		try {
			if(query == null){
				query = new BossOrderDepositQueryDto();
			}
			query.setDepositType(BusiOrderDepositTypeEnum.ORDER_OVERTIME_DEPOSIT.getCode());//订单完成划账
			query.setPageSize(10);//一页显示10条
			Page<BossOrderDepositVo> page = busiOrderDepositService.selectDepositList(query);
			model.addAttribute("page", page);
			model.addAttribute("stateMap", BusiOrderDepositStateEnum.toMap());
			return "public/order_overdue";
		} catch (Exception e) {
			logger.error("BossOrderDepositController.getOrderDepositListByOverdue, error is " + e.getMessage());
			return "error";
		}
	}
	
	/**
	 * @Description: 后台-过期订单处理-查看订单详情
	 * @Author: 赵航
	 * @Date: 2015年5月19日
	 * @param orderId
	 * @return String
	 */
	@RequestMapping("depositOverdue/orderinfo")
	public @ResponseBody String selectDepositOverdueOrderInfo(@RequestParam(value="orderId", required=true) String orderId){
		logger.info("BossOrderDepositController.selectDepositOverdueOrderInfo");
		JsonObject json = new JsonObject();
		try {
			BossOrderInfoVo orderInfo = bossOrderService.selectOrderInfoById(orderId);
			if(orderInfo == null){
				json.addProperty("state", "failure");
				json.addProperty("result", ErrorRepository.NOT_EXIST.getMessage("订单"));
			} else {
				json.addProperty("state", "success");
				json.addProperty("result", GsonFactory.createGson("yyyy-MM-dd HH:mm:ss").toJson(orderInfo));
			}
		} catch (Exception e) {
			logger.error("BossOrderDepositController.selectDepositOverdueOrderInfo, error is " + e.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", ErrorRepository.UNKNOW_ERROR.getMessage());
		}
		return json.toString();
	}
	
	/**
	 * @Description: 后台-过期订单处理-赔付
	 * @Author: 赵航
	 * @Date: 2015年5月19日
	 * @param deposit
	 * @param request
	 * @return String
	 */
	@RequestMapping("depositOverdue/executeDeposit")
	public @ResponseBody String executeOrderOverdueDeposit(@ModelAttribute("deposit") BossOrderDepositDto deposit,
			HttpServletRequest request){
		logger.info("BossOrderDepositController.executeOrderOverdueDeposit");
		JsonObject json = new JsonObject();
		if(deposit == null){
			deposit = new BossOrderDepositDto();
		}
		//对deposit做校验
		if(!depositCheck(deposit, json)){
			return json.toString();
		}
		deposit.setOperattorId(GetBaseInfo.getBossUserId());
		deposit.setOperattorIp(GetBaseInfo.getIp(request));
		try {
			String rs = busiOrderDepositService.todoOrderDeposit(deposit);
			if("success".equals(rs)){
				json.addProperty("status", "y");
				json.addProperty("info", "已成功申请，等待财务处理！");
			} else {
				json.addProperty("status", "n");
				json.addProperty("info", "操作失败");
			}
		} catch (Exception e) {
			logger.error("BossOrderDepositController.executeOrderOverdueDeposit, error is " + e.getMessage());
			json.addProperty("status", "n");
			json.addProperty("info", "操作失败");
		}
		return json.toString();
	}
	
	/**
	 * 划账信息的校验
	 */
	private boolean depositCheck(BossOrderDepositDto deposit, JsonObject json){
		//买家金额
		if(StringUtils.isEmpty(deposit.getBuyerAmount())){
			json.addProperty("status", "n");
			json.addProperty("info", "请输入买家金额");
			return false;
		} else if(!deposit.getBuyerAmount().matches("^(\\d+)(\\.\\d{1,2})?$")){
			json.addProperty("status", "n");
			json.addProperty("info", "买家格式不正确");
			return false;
		}
		
		//卖家金额
		if(StringUtils.isEmpty(deposit.getSellerAmount())){
			json.addProperty("status", "n");
			json.addProperty("info", "请输入卖家金额");
			return false;
		} else if(!deposit.getSellerAmount().matches("^(\\d+)(\\.\\d{1,2})?$")){
			json.addProperty("status", "n");
			json.addProperty("info", "卖家格式不正确");
			return false;
		}
		
		//平台金额
		if(StringUtils.isEmpty(deposit.getPlatformAmount())){
			json.addProperty("status", "n");
			json.addProperty("info", "请输入平台金额");
			return false;
		} else if(!deposit.getPlatformAmount().matches("^(\\d+)(\\.\\d{1,2})?$")){
			json.addProperty("status", "n");
			json.addProperty("info", "平台格式不正确");
			return false;
		}
		BigDecimal buyerAmount = new BigDecimal(deposit.getBuyerAmount());
		BigDecimal sellerAmount = new BigDecimal(deposit.getSellerAmount());
		BigDecimal platformAmount = new BigDecimal(deposit.getPlatformAmount());
		BigDecimal total = buyerAmount.add(platformAmount).add(sellerAmount);
		BigDecimal depositAmount = new BigDecimal(deposit.getDepositAmount());
		if(total.compareTo(depositAmount)!=0){
			json.addProperty("status", "n");
			json.addProperty("info", "比例之和与总额不一致");
			return false;
		}
		return true;
	}
	
	/**
	 * @Description: 分润拒绝列表
	 * @Author: guoyb
	 * @Date: 2015年7月6日
	 * @param request 
	 * @param response 
	 * @param orderId 
	 * @return 
	 */
	@RequestMapping("/depositFinish/orderDepositRejectList")
	@ResponseBody
	public List<RefuseRemitFlowVo> orderDepositRejectList(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="orderId", required=true) String orderId) {
		logger.info("BossOrderDepositController.orderDepositRejectList");
		
		JsonObject reqJson = new JsonObject();
		//系统标识
		reqJson.addProperty("sourceSys", 0);
		//记录行数
		reqJson.addProperty("orderId", orderId);
		//划账类型
		reqJson.addProperty("remitType",request.getParameter("remitType"));
		
		List<RefuseRemitFlowVo> result = new ArrayList<RefuseRemitFlowVo>();
		try {
			result = busiOrderDepositService.RejectReason(reqJson.toString());
		} catch (Exception e) {
			logger.error("BossOrderDepositController.orderDepositRejectList, error is " + e.getMessage());
		}
		return result;
	}
	
	/**
	 * @Description: 订单过期赔付
	 * @Author: guoyb
	 * @Date: 2015年7月6日
	 * @param request 
	 * @param response 
	 * @param orderId 
	 * @return
	 */
	@RequestMapping("/depositOverdue/orderOverdueDepositRejectList")
	@ResponseBody
	public List<RefuseRemitFlowVo> orderOverdueDepositRejectList(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="orderId", required=true) String orderId) {
		logger.info("BossOrderDepositController.orderOverdueDepositRejectList");
		
		JsonObject reqJson = new JsonObject();
		//系统标识
		reqJson.addProperty("sourceSys", 0);
		//记录行数
		reqJson.addProperty("orderId", orderId);
		//划账类型
		reqJson.addProperty("remitType",request.getParameter("remitType"));
		
		List<RefuseRemitFlowVo> result = new ArrayList<RefuseRemitFlowVo>();
		try {
			result = busiOrderDepositService.RejectReason(reqJson.toString());
		} catch (Exception e) {
			logger.error("BossOrderDepositController.orderOverdueDepositRejectList, error is " + e.getMessage());
		}
		return result;
	}
	
	/**
	 * @Description: 申请退款划账拒绝列表
	 * @Author: guoyb
	 * @Date: 2015年7月6日
	 * @param request 
	 * @param response 
	 * @param orderId 
	 * @return
	 */
	@RequestMapping("/depositFailed/applyDepositRejectList")
	@ResponseBody
	public List<RefuseRemitFlowVo> applyDepositRejectList(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="orderId", required=true) String orderId) {
		logger.info("BossOrderDepositController.applyDepositRejectList");
		
		JsonObject reqJson = new JsonObject();
		//系统标识
		reqJson.addProperty("sourceSys", 0);
		//记录行数
		reqJson.addProperty("orderId", orderId);
		//划账类型
		reqJson.addProperty("remitType",request.getParameter("remitType"));
		
		List<RefuseRemitFlowVo> result = new ArrayList<RefuseRemitFlowVo>();
		try {
			result = busiOrderDepositService.RejectReason(reqJson.toString());
		} catch (Exception e) {
			logger.error("BossOrderDepositController.applyDepositRejectList, error is " + e.getMessage());
		}
		return result;
	}
}
