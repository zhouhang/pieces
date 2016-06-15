package com.jointown.zy.web.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ucs.creditpay.entities.notices.UcsNoticeRequest;
import ucs.creditpay.entities.notices.UcsNoticeRequest1002;
import ucs.creditpay.entities.notices.UcsNoticeResponse;
import ucs.creditpay.entities.responses.Response1001;

import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.dto.PayReqDto;
import com.jointown.zy.common.enums.PayErrorCode;
import com.jointown.zy.common.enums.PayFlowLogStatusEnum;
import com.jointown.zy.common.enums.PayStatusEnum;
import com.jointown.zy.common.enums.UcsCodeEnum;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.pay.PayErrorResult;
import com.jointown.zy.common.pay.PayManager;
import com.jointown.zy.common.pay.PayReqDataCheck;
import com.jointown.zy.common.pay.PayUtil;
import com.jointown.zy.common.pay.ucs.service.UcsService;
import com.jointown.zy.common.service.PayOrderService;
import com.jointown.zy.common.service.PayResultService;
import com.jointown.zy.common.util.IPUtil;
import com.jointown.zy.common.util.TimeUtil;

/**
 * 珍药宝支付
 * @ClassName:UcsController
 * @author:Calvin.Wangh
 * @Description:
 * @date:2015-5-15上午10:04:24
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/ucs")
public class UcsController {

	private static final Logger logger = LoggerFactory.getLogger(UcsController.class);
	@Autowired
	private UcsService ucsService;
	@Autowired
	private PayManager payManager;
	@Autowired
	private PayOrderService payOrderService;
	@Autowired
	private PayResultService payResultService;

	
	/**
	 * 记录支付流水 发送签名验签数据 发送请求到珍药宝
	 * @param payReqDto
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/pay")
	public String pay(@ModelAttribute PayReqDto payReqDto,@RequestParam(value="orderTitle",defaultValue="")String orderTitle,  ModelMap model,HttpServletRequest request){
		String data = request.getParameter("data");
		String signdata = request.getParameter("signdata");
		String paymentNo = request.getParameter("paymentNo");
		try {
			//解析加密参数
			payReqDto.setOrderTitle(PayUtil.getDesDecryptData(orderTitle));
		} catch (Exception e) {
			logger.error("ordertitle decrypt error:",e);
		}
		PayErrorResult per = null;
		//验证签名是否为空
		if(StringUtils.isNotBlank(data) && StringUtils.isNotBlank(signdata)){
			//校验
			per = PayReqDataCheck.reqDataCheck(payReqDto);
			if (null != per) {
				model.addAttribute("payErrorMsg", per);
				return "UCSpayError";
			}
			//根据支付流水号查询  防止重复提交
			PayOrder payOrder = payOrderService.getPayOrderByFlowId(paymentNo);
			if(null == payOrder){
				int flag = ucsService.addPayFlow(payReqDto,paymentNo,IPUtil.getIP(request));
				if (flag < 1) {
					logger.info("pay flow create error");
					payManager.payFlowLogAdd(payReqDto.getOrderId(),
							Integer.parseInt(payReqDto.getSourceSys()),
							PayErrorCode.PAY_FLOW_EXCEPTION.getCode(),
							PayFlowLogStatusEnum.FAIL.getStatus(),
							PayErrorCode.PAY_FLOW_EXCEPTION.getCodeDes());
					per = new PayErrorResult();
					per.setCode(PayErrorCode.PAY_FLOW_EXCEPTION.getCode());
					per.setCodeDes(PayErrorCode.PAY_FLOW_EXCEPTION.getCodeDes());
					return "UCSpayError";
				}
			}
			//记录交易发送珍药宝请求日志
		    payManager.payFlowLogAdd(payReqDto.getOrderId(),
		    		Integer.parseInt(payReqDto.getSourceSys()),BankConfigConstant.UcsRespCode.UCS_RESPCODE_100,
		    			PayFlowLogStatusEnum.SUCCESS.getStatus(), "向珍药宝提交交易请求成功!");
		    
			model.addAttribute("data", data);
			model.addAttribute("signdata", signdata);
			//提交请求url
			model.addAttribute("url", BankConfigConstant.UCS_PAYORDER_ACTION);
			return "ucs/ucsPay";
		}else{
			logger.info("签名数据为空!");
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_REQDATA_SIGNDATA_IS_NOT_NULL.getCode());
			per.setCodeDes(PayErrorCode.PAY_REQDATA_SIGNDATA_IS_NOT_NULL.getCodeDes());
			model.addAttribute("payErrorMsg", per);
			return "UCSpayError";
		}
	}

	/**
	 * 前台通知
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/payFontResult", method = { RequestMethod.GET,RequestMethod.POST })
	public String payFontResult(HttpServletRequest req,HttpServletResponse resp, ModelMap model) {
		logger.info("[珍药宝]前台接收报文返回开始");
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("[frontUrl]EncodingException", e1);
		}
		PayOrder payOrder = null;
		PayErrorResult per = null;
		
		String data = req.getParameter("data");
		String signdata = req.getParameter("signdata");
		String amount = "";
		String respCode = "";
		String cState = "";
		String paymentNo = "";
		try {
			if(StringUtils.isNotBlank(data) && StringUtils.isNotBlank(signdata)){
				Response1001 response = new Response1001(data, signdata);
				amount = String.valueOf(response.getAmount());
				respCode = String.valueOf(response.getCode());
				cState = response.getCState();
				paymentNo = response.getPaymentNo();
				if (null == response) {
					per = new PayErrorResult();
					per.setCode(PayErrorCode.PAY_RESULT_IS_NULL.getCode());
					per.setCodeDes(PayErrorCode.PAY_RESULT_IS_NULL.getCodeDes());
					model.addAttribute("payErrorMsg", per);
					return "payError";
				}
				payOrder = payOrderService.getPayOrderByFlowId(paymentNo);
				// 判断流水是否存在
				if (null == payOrder) {
					logger.info("pay flow not exist");
					per = new PayErrorResult();
					per.setCode(PayErrorCode.PAY_FLOW_NOT_EXIST.getCode());
					per.setCodeDes(PayErrorCode.PAY_FLOW_NOT_EXIST.getCodeDes());
					model.addAttribute("payErrorMsg", per);
					return "payError";
				}
				//验证订单是否支付成功，如果已成功则不进行更新操作,直接返回支付成功页面
				if (PayStatusEnum.SUCCESS.getStatus()== payOrder.getStatus()) {
					logger.info("pay flow status success[no longger update pay flow status]-[front]!");
					payManager.payFlowLogAdd(payOrder.getOrderId(),
							Integer.parseInt(payOrder.getSourceSys()),BankConfigConstant.UcsRespCode.UCS_RESPCODE_100,
							PayFlowLogStatusEnum.SUCCESS.getStatus(),PayStatusEnum.SUCCESS.getStatusDesc()+ "-[珍药宝]前台返回：支付成功！支付流水状态已经被更新过!");
					return "redirect:http://uc.54315.com/order/listinfo";
				}
				PayOrder pOrder = new PayOrder();
				//成功受理请求
				if (UcsCodeEnum.CODE_100.getCode().equals(respCode)) {
					logger.info("[珍药宝]支付请求受理成功!");
					//验证支付状态 成功
					if(BankConfigConstant.UcsRespPayState.PAY_SUCCESS.equals(cState)){
						//支付成功
						pOrder.setStatus(PayStatusEnum.SUCCESS.getStatus());
						//支付成功 记录日志
						payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), BankConfigConstant.UcsRespCode.UCS_RESPCODE_100, PayStatusEnum.SUCCESS.getStatus(),PayStatusEnum.SUCCESS.getStatusDesc());
						//向支付结果表中 add消息
						payResultService.addPayResult(payOrder);
						payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), BankConfigConstant.UcsRespCode.UCS_RESPCODE_100, PayStatusEnum.SUCCESS.getStatus(), "[珍药宝]前台返回:支付成功!" + "[add PayResult success]");
					}else if (BankConfigConstant.UcsRespPayState.PAY_FAILED.equals(cState)) {// 失败
						pOrder.setStatus(PayStatusEnum.FAILED.getStatus());
						payManager.payFlowLogAdd(payOrder.getOrderId(),Integer.parseInt(payOrder.getSourceSys()), BankConfigConstant.UcsRespCode.UCS_RESPCODE_100,
								PayFlowLogStatusEnum.FAIL.getStatus(), "[珍药宝]前台返回:"+ PayStatusEnum.FAILED.getStatusDesc());
					} else if (BankConfigConstant.UcsRespPayState.PAY_DISPONSE.equals(cState)) {// 处理中
						pOrder.setStatus(PayStatusEnum.DISPOSE.getStatus());
						payManager.payFlowLogAdd(payOrder.getOrderId(),Integer.parseInt(payOrder.getSourceSys()), BankConfigConstant.UcsRespCode.UCS_RESPCODE_100,
								PayFlowLogStatusEnum.FAIL.getStatus(), "[珍药宝]前台返回:"+ PayStatusEnum.DISPOSE.getStatusDesc()+ "[需调用查询接口查询支付流水结果]");
					}
					// 修改支付流水状态
					pOrder.setFlowId(new BigDecimal(paymentNo));
					pOrder.setAmount(new BigDecimal(amount));
					pOrder.setPayTime(TimeUtil.payDateTimeFormat(response.getPayTime()));
					//pOrder.setQueryId(response.getOrderNo());
					pOrder.setRespCode(BankConfigConstant.UcsRespCode.UCS_RESPCODE_100);
					payOrderService.updatePayOrder(pOrder);
				}else{
					logger.error("[珍药宝]支付前台回调响应吗错误 respCode：" + respCode);
					payManager.payFlowLogAdd(payOrder.getOrderId(),
							Integer.parseInt(payOrder.getSourceSys()), respCode,
							PayFlowLogStatusEnum.FAIL.getStatus(), "[珍药宝]支付前台回调响应吗错误 " + respCode);
				}
			}else{
				logger.info("[珍药宝]支付后台回调签名数据为空");
				per = new PayErrorResult();
				per.setCode(PayErrorCode.PAY_REQDATA_SIGNDATA_IS_NOT_NULL.getCode());
				per.setCodeDes(PayErrorCode.PAY_REQDATA_SIGNDATA_IS_NOT_NULL.getCodeDes());
				model.addAttribute("payErrorMsg", per);
				return "payError";
			}
		} catch (Exception e) {
			logger.error("front notify error is :", e);
			payManager.payFlowLogAdd(payOrder.getOrderId(),
					Integer.parseInt(payOrder.getSourceSys()), BankConfigConstant.UcsRespCode.UCS_RESPCODE_999,
					PayFlowLogStatusEnum.FAIL.getStatus(), "[珍药宝]前台返回:"
							+ "[pay result done exception]" + e.getMessage());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_RESULT_DONE_EXCEPTION.getCode());
			per.setCodeDes(PayErrorCode.PAY_RESULT_DONE_EXCEPTION.getCodeDes());
			model.addAttribute("payErrorMsg", per);
			return "payError";
		}
		return "redirect:http://uc.54315.com/order/listinfo";
	}

	/**
	 * 后台回调通知
	 * 只接受respCode100  否则将发送20次请求 间隔为10s
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/payCallBack", method = { RequestMethod.GET,RequestMethod.POST })
	public void payCallBack(HttpServletRequest request,HttpServletResponse response) {
		logger.info("[珍药宝]接收后台通知开始");
		PayErrorResult per = null;
		PayOrder payOrder = null;
		String data = request.getParameter("data");
		String signdata = request.getParameter("signdata");
		String CState = "";
		String amount = "";
		String paymentNo = "";
		String payTime="";
		String orderNo = "";
		try {
			//验证签名参数是否为空
			if(StringUtils.isNotBlank(data) && StringUtils.isNotBlank(signdata)){
				logger.debug("[data]         = " + data);
				logger.debug("[signdata]         = " + signdata);
				UcsNoticeRequest noticeRequest = new UcsNoticeRequest(data,signdata);
				String optType = String.valueOf(noticeRequest.getOptType());
				//验证操作类型
				if(BankConfigConstant.UCS_PAY_CHANGE_STATUS_OPTTYPE.equals(optType)){
					UcsNoticeRequest1002 noticeRequest1002 =new UcsNoticeRequest1002(noticeRequest.getJsonResult());
					
					CState = noticeRequest1002.getCState();
					amount = noticeRequest1002.getAmount();
					payTime = noticeRequest1002.getPayTime();
					paymentNo = noticeRequest1002.getPaymentNo();
					orderNo = noticeRequest1002.getOrderNo();
					
					//查询支付流水
					payOrder = payOrderService.getPayOrderByFlowId(paymentNo);
					// 判断流水是否存在
					if (null == payOrder) {
						logger.info("[珍药宝支付后台回调]pay flow not exist");
						per = new PayErrorResult();
						per.setCode(PayErrorCode.PAY_FLOW_NOT_EXIST.getCode());
						per.setCodeDes(PayErrorCode.PAY_FLOW_NOT_EXIST.getCodeDes());
						return;
					}
					//验证流水订单状态是否支付成功，如果已成功则不进行更新操作
					if (PayStatusEnum.SUCCESS.getStatus()== payOrder.getStatus()) {
						logger.info("[珍药宝支付后台回调]pay flow status success[no longger update pay flow status]-[front]!");
						payManager.payFlowLogAdd(payOrder.getOrderId(),
								Integer.parseInt(payOrder.getSourceSys()),BankConfigConstant.UcsRespCode.UCS_RESPCODE_100,
								PayFlowLogStatusEnum.SUCCESS.getStatus(),PayStatusEnum.SUCCESS.getStatusDesc()+ "-[珍药宝支付后台回调：支付成功！支付流水状态已经被更新过!]");
						return;
					}
					
					PayOrder pOrder = new PayOrder();
					//验证回调返回的支付状态
					if(BankConfigConstant.UcsRespPayState.PAY_SUCCESS.equals(CState)){
						//支付成功
						pOrder.setStatus(PayStatusEnum.SUCCESS.getStatus());
						payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), BankConfigConstant.UcsRespCode.UCS_RESPCODE_100, PayStatusEnum.SUCCESS.getStatus(),PayStatusEnum.SUCCESS.getStatusDesc());
						//向支付结果表中 add消息
						payResultService.addPayResult(payOrder);
						payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), BankConfigConstant.UcsRespCode.UCS_RESPCODE_100, PayStatusEnum.SUCCESS.getStatus(), "[珍药宝]后台返回:支付成功!" + "[add PayResult success]");
					}else if (BankConfigConstant.UcsRespPayState.PAY_FAILED.equals(CState)){// 失败
						pOrder.setStatus(PayStatusEnum.FAILED.getStatus());
						payManager.payFlowLogAdd(payOrder.getOrderId(),Integer.parseInt(payOrder.getSourceSys()), CState,
								PayFlowLogStatusEnum.FAIL.getStatus(), "[珍药宝]后台返回:"+ PayStatusEnum.FAILED.getStatusDesc());
					}
					// 修改支付流水状态
					pOrder.setFlowId(new BigDecimal(paymentNo));
					pOrder.setAmount(new BigDecimal(amount));
					pOrder.setPayTime(TimeUtil.payDateTimeFormat(payTime));
					//pOrder.setQueryId(orderNo);
					pOrder.setRespCode(BankConfigConstant.UcsRespCode.UCS_RESPCODE_100);
					payOrderService.updatePayOrder(pOrder);
					//发送响应到珍药宝 告知后台回调成功 不在重复发送后台回调请求
					sendUcsNoticeResponse(response, UcsCodeEnum.CODE_100.getCode(), UcsCodeEnum.CODE_100.getCodeDesc());
				}
			}else{
				logger.info("[珍药宝]支付后台回调签名数据为空");
				per = new PayErrorResult();
				per.setCode(PayErrorCode.PAY_REQDATA_SIGNDATA_IS_NOT_NULL.getCode());
				per.setCodeDes(PayErrorCode.PAY_REQDATA_SIGNDATA_IS_NOT_NULL.getCodeDes());
				return ;
			}
		} catch (Exception e) {
			logger.error("[珍药宝]支付后台回调异常 ："+e);
		}
		logger.info("[珍药宝]接收后台通知结束");
	}
	
//	@RequestMapping(value = "/gotoRequest")
//	public String gotoRequest(HttpServletRequest request,HttpServletResponse response) {
//		return "ucs/request";
//	}
//	/**
//	 * 手动接收后台回调通知(测试用,上线注释掉)
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping(value = "/payCallBackByManual", method = { RequestMethod.GET,RequestMethod.POST })
//	public void payCallBackByManual(HttpServletRequest request,HttpServletResponse response) {
//		logger.info("[珍药宝]接收后台通知开始");
//		PayErrorResult per = null;
//		PayOrder payOrder = null;
//		String CState = request.getParameter("CState");
//		String amount = request.getParameter("Amount");
//		String paymentNo = request.getParameter("PaymentNo");
//		Date date = new Date();
//		String orderNo = request.getParameter("OrderNo");
//		try {
//			//查询支付流水
//			payOrder = payOrderService.getPayOrderByFlowId(paymentNo);
//			// 判断流水是否存在
//			if (null == payOrder) {
//				logger.info("[珍药宝支付后台回调]pay flow not exist");
//				per = new PayErrorResult();
//				per.setCode(PayErrorCode.PAY_FLOW_NOT_EXIST.getCode());
//				per.setCodeDes(PayErrorCode.PAY_FLOW_NOT_EXIST.getCodeDes());
//				return;
//			}
//			//验证流水订单状态是否支付成功，如果已成功则不进行更新操作
//			if (PayStatusEnum.SUCCESS.getStatus()== payOrder.getStatus()) {
//				logger.info("[珍药宝支付后台回调]pay flow status success[no longger update pay flow status]-[front]!");
//				payManager.payFlowLogAdd(payOrder.getOrderId(),
//						Integer.parseInt(payOrder.getSourceSys()),BankConfigConstant.UcsRespCode.UCS_RESPCODE_100,
//						PayFlowLogStatusEnum.SUCCESS.getStatus(),PayStatusEnum.SUCCESS.getStatusDesc()+ "-[珍药宝支付后台回调：支付成功！支付流水状态已经被更新过!]");
//				return;
//			}
//			
//			PayOrder pOrder = new PayOrder();
//			//验证回调返回的支付状态
//			//支付成功
//			pOrder.setStatus(PayStatusEnum.SUCCESS.getStatus());
//			payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), BankConfigConstant.UcsRespCode.UCS_RESPCODE_100, PayStatusEnum.SUCCESS.getStatus(),PayStatusEnum.SUCCESS.getStatusDesc());
//			//向支付结果表中 add消息
//			payResultService.addPayResult(payOrder);
//			payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), BankConfigConstant.UcsRespCode.UCS_RESPCODE_100, PayStatusEnum.SUCCESS.getStatus(), "[珍药宝]后台返回:支付成功!" + "[add PayResult success]");
//			// 修改支付流水状态
//			pOrder.setFlowId(new BigDecimal(paymentNo));
//			pOrder.setAmount(new BigDecimal(amount));
//			pOrder.setPayTime(date);
//			//pOrder.setQueryId(orderNo);
//			pOrder.setRespCode(BankConfigConstant.UcsRespCode.UCS_RESPCODE_100);
//			payOrderService.updatePayOrder(pOrder);
//			//发送响应到珍药宝 告知后台回调成功 不在重复发送后台回调请求
//			sendUcsNoticeResponse(response, UcsCodeEnum.CODE_100.getCode(), UcsCodeEnum.CODE_100.getCodeDesc());
//		} catch (Exception e) {
//			logger.error("[珍药宝]支付后台回调异常 ："+e);
//		}
//		logger.info("[珍药宝]接收后台通知结束");
//	}
//	
	/**
	 * 发送响应通知到珍药宝
	 * @param response
	 * @param respCode
	 * @param msg
	 */
	public void sendUcsNoticeResponse(HttpServletResponse response,String respCode,String msg){
		int code = Integer.valueOf(respCode);
		try {
			UcsNoticeResponse noticeResponse = new UcsNoticeResponse(code,msg);
			PrintWriter out = response.getWriter();
	        out.print(noticeResponse.getJsonResult());
	        out.flush();
	        out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	///////////////////////////////////////////生成单号////////////////////////////////////
	/**
	 * 生成订单编号
	 * 
	 * @return
	 */
	public static String getOrderNo() {
		return getMinGuid();
	}

	public static String getFullGuid() {
		return java.util.UUID.randomUUID().toString();
	}

	public static String getMinGuid() {
		return java.util.UUID.randomUUID().toString().replace("-", "").substring(1, 21);
	}

}
