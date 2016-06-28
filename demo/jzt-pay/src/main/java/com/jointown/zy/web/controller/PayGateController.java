package com.jointown.zy.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.dto.PayReqDto;
import com.jointown.zy.common.enums.AmtTypeEnum;
import com.jointown.zy.common.enums.PayFlowLogStatusEnum;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.pay.PayErrorResult;
import com.jointown.zy.common.pay.PayManager;
import com.jointown.zy.common.pay.PayReqDataCheck;
import com.jointown.zy.common.pay.PayUtil;
import com.jointown.zy.common.pay.ucs.service.UcsService;
import com.jointown.zy.common.service.PayOrderService;
import com.jointown.zy.common.util.EncryptUtil;

/**
 * 支付网关controller
 * @author ldp
 * date:2015-2-5
 */
@Controller(value="payGateController")
public class PayGateController extends UserBaseController {

	private static final Logger log = LoggerFactory.getLogger(PayGateController.class);
	@Autowired
	private PayManager payManager;
	@Autowired
	private UcsService ucsService;
	@Autowired
	private PayOrderService payOrderService;
	/**
	 * 支付管理
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/gateway")
	public String payManage(@ModelAttribute PayReqDto payReqDto,
							HttpServletRequest request,ModelMap modelMap){
		log.info("req data is:" + payReqDto);
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("pay req Encoding Exception:",e);
		}
		PayErrorResult per = null;
		per = PayReqDataCheck.reqDataCheck(payReqDto);
		if (null != per) {
			modelMap.addAttribute("payErrorMsg", per);
			return "payError";
		}
		payManager.payFlowLogAdd(payReqDto.getOrderId(), Integer.parseInt(payReqDto.getSourceSys()), "00", PayFlowLogStatusEnum.SUCCESS.getStatus(), "订单发送支付请求成功!");
		//处理订单名称特殊符号在url请求中产生的异常[java.lang.IllegalArgumentException: !hex:25]
		try {
			modelMap.addAttribute("encryptOrderTitle", PayUtil.getDesEncryptData(payReqDto.getOrderTitle()));
		} catch (Exception e) {
			log.error("encryptOrderTitle error:",e);
			modelMap.addAttribute("payErrorMsg", "订单信息加密错误！");
			return "payError";
		}
		modelMap.addAttribute("payInfo", payReqDto);
		modelMap.addAttribute("amtTypes", AmtTypeEnum.toMap());
		
		int amtType = Integer.valueOf(payReqDto.getAmtType());
		if(AmtTypeEnum.PAY_BALANCE_PAYMENT.getCode() == amtType){
			//查询支付成功的支付流水
			PayOrder payOrder = payOrderService.getPaySuccessOrderByOrderId(payReqDto.getOrderId());
			modelMap.addAttribute("payOrder",payOrder);
		}
		//珍药包请求参数处理 
		Map<String,String> maps = ucsService.payOrder(payReqDto);
		modelMap.addAttribute("data",maps.get("data"));
		modelMap.addAttribute("signdata",maps.get("signdata"));
		modelMap.addAttribute("paymentNo", maps.get("paymentNo"));
		return "paygate";
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/testPay")
	public String testPay(HttpServletRequest request,ModelMap model){
		model.addAttribute("orderNo", PayUtil.getSeqence());
		return "testPay";
	}
	
	/**
	 * test
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/test")
	public String test(@ModelAttribute PayReqDto payReqDto,HttpServletRequest request,ModelMap map){
		StringBuilder orderInfo = new StringBuilder();
		orderInfo.append("orderId=").append(payReqDto.getOrderId());
		orderInfo.append("&");
		orderInfo.append("amount=").append(payReqDto.getAmount());
		orderInfo.append("&");
		orderInfo.append("amtType=").append(payReqDto.getAmtType());
		orderInfo.append("&");
		orderInfo.append("userId=").append(payReqDto.getUserId());
		orderInfo.append("&");
		orderInfo.append("recieveId=").append(payReqDto.getRecieveId());
		orderInfo.append("&");
		orderInfo.append("sourceSys=").append(payReqDto.getSourceSys());
		orderInfo.append("&");
		orderInfo.append("orderTitle=").append(payReqDto.getOrderTitle());
		String orderKey = EncryptUtil.getMD5(orderInfo.toString() + BankConfigConstant.TX_PAY_MD5KEY, "UTF-8");
		/*String testInfo  = orderInfo.toString();
		String orderTitle = payReqDto.getOrderTitle();*/
		map.put("orderId", payReqDto.getOrderId());
		map.put("amount", payReqDto.getAmount());
		map.put("amtType", payReqDto.getAmtType());
		map.put("userId", payReqDto.getUserId());
		map.put("recieveId", payReqDto.getRecieveId());
		map.put("sourceSys", payReqDto.getSourceSys());
		map.put("orderTitle", payReqDto.getOrderTitle());
		map.put("signData", orderKey);
		
		//签名防止数据篡改
		/*String orderKey = EncryptUtil.getMD5(orderInfo + "&orderTitle=" + orderTitle + BankConfigConstant.TX_PAY_MD5KEY, "UTF-8");
		String params = testInfo + "&orderTitle=" + java.net.URLEncoder.encode(orderTitle) + "&signData=" + orderKey;*/
		//return "redirect:/gateway?"+params;
		return "redirect:/gateway";
		//return "redirect:https://pay.54315.com:8090/gateway";
	}
}
