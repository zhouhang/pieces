package com.jointown.zy.web.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jointown.zy.common.enums.PayErrorCode;
import com.jointown.zy.common.enums.PayFlowLogStatusEnum;
import com.jointown.zy.common.enums.PayStatusEnum;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.pay.IPayValidateService;
import com.jointown.zy.common.pay.PayErrorResult;
import com.jointown.zy.common.pay.PayManager;
import com.jointown.zy.common.pay.PayUtil;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.PayOrderService;
import com.jointown.zy.common.service.PayResultService;
import com.jointown.zy.common.util.BigDecimalUtil;
import com.jointown.zy.common.util.TimeUtil;
import com.unionpay.acp.sdk.LogUtil;

/**
 * B2CController
 * @author zhouji
 * @date 2015年4月7日 上午10:04:04
 */
@Controller(value="UnionPayB2CController")
@RequestMapping("/b2c")
public class UnionPayB2CController extends UserBaseController  {
	private static final Logger log = LoggerFactory.getLogger(UnionPayValidateController.class);
	@Autowired
	@Qualifier("unionPayValidate")
	private IPayValidateService payValidateService;
	@Autowired
	private PayOrderService payOrderService;
	@Autowired
	private PayManager payManager;
	@Autowired
	private PayResultService payResultService;
	
	/**
	 * 前台返回
	 * @author zhouji
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/frontUrl")
	public String frontUrl(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		log.info("前台接收报文返回开始");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			log.error("[frontUrl]EncodingException",e1);
		}
		//获取请求参数中所有的信息
		Map<String, String> respParam = getAllRequestParam(request);
		//错误信息
		PayErrorResult per = null;
		if (null == respParam || respParam.size() == 0) {
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_RESULT_IS_NULL.getCode());
			per.setCodeDes(PayErrorCode.PAY_RESULT_IS_NULL.getCodeDes());
			modelMap.addAttribute("payErrorMsg", per);
			return "payError";
		}
		// 打印请求报文
		log.info("front respParam is :" + respParam);
		String rescode = respParam.get("respCode");
		String flowId = respParam.get("orderId");
		String amount = respParam.get("txnAmt");
		//根据流水ID查询流水表
		PayOrder payOrder = payOrderService.getPayOrderByFlowId(flowId);
		try {
			//支付验签
			boolean validateFlag = payValidateService.payValidate(respParam);
			if (validateFlag) {
				//判断流水是否存在
				if (null == payOrder) {
					log.info("pay flow not exist");
					per = new PayErrorResult();
					per.setCode(PayErrorCode.PAY_FLOW_NOT_EXIST.getCode());
					per.setCodeDes(PayErrorCode.PAY_FLOW_NOT_EXIST.getCodeDes());
					modelMap.addAttribute("payErrorMsg", per);
					return "payError";
				}
				//判断支付流水状态是否已更新，如果已更新不进行更新操作,直接返回支付成功页面
				if (1 == payOrder.getStatus()) {
					log.info("pay flow status success[no longger update pay flow status]-[front]!");
					payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), rescode, PayFlowLogStatusEnum.SUCCESS.getStatus(), PayStatusEnum.SUCCESS.getStatusDesc() + "-[前台返回：支付成功！支付流水状态已经被更新过!]");
					return "paySuccess";
				}
				Date payTime = new Date();
				PayOrder pOrder = new PayOrder();
				//支付成功处理
				if ("00".equals(rescode)) {
					log.info("pay success!");
					//判断金额是否一致
					if (!amount.equals(String.valueOf(payOrder.getAmount().multiply(new BigDecimal(100)).intValue()))) {
						pOrder.setStatus(PayStatusEnum.EXCEPTION.getStatus());
						payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), rescode, PayFlowLogStatusEnum.FAIL.getStatus(), "前台返回:银联返回金额与支付流水金额不一致!");
					}else {
						pOrder.setStatus(PayStatusEnum.SUCCESS.getStatus());//支付状态
						payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), rescode, PayFlowLogStatusEnum.SUCCESS.getStatus(), "前台返回:" + PayStatusEnum.SUCCESS.getStatusDesc());
						//向支付结果表中 add消息
						payResultService.addPayResult(payOrder);
						payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), rescode, PayFlowLogStatusEnum.SUCCESS.getStatus(), "前台返回:" + PayStatusEnum.SUCCESS.getStatusDesc() + "[add PayResult success]");
					}
				}else if ("01".equals(rescode)) {//失败
					pOrder.setStatus(PayStatusEnum.FAILED.getStatus());
					payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), rescode, PayFlowLogStatusEnum.FAIL.getStatus(), "前台返回:" + PayStatusEnum.FAILED.getStatusDesc());
				}else if ("03".equals(rescode)||"04".equals(rescode)||"05".equals(rescode)) {//处理中
					pOrder.setStatus(PayStatusEnum.DISPOSE.getStatus());
					payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), rescode, PayFlowLogStatusEnum.FAIL.getStatus(), "前台返回:" + PayStatusEnum.DISPOSE.getStatusDesc() + "[需调用查询接口查询支付流水结果]");
				} 
				//修改支付流水状态
				pOrder.setFlowId(new BigDecimal(flowId));
				pOrder.setAmount(new BigDecimal(amount));
				pOrder.setPayTime(payTime);
				pOrder.setQueryId(respParam.get("queryId"));
				pOrder.setRespCode(respParam.get("respCode"));
				payOrderService.updatePayOrder(pOrder);
			}
			log.info("前台接收报文返回结束");
		} catch (Exception e) {
			log.error("front notify error is :", e);
			payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), rescode, PayFlowLogStatusEnum.FAIL.getStatus(), "前台返回:" + "[pay result done exception]" + e.getMessage());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_RESULT_DONE_EXCEPTION.getCode());
			per.setCodeDes(PayErrorCode.PAY_RESULT_DONE_EXCEPTION.getCodeDes());
			modelMap.addAttribute("payErrorMsg", per);
			return "payError";
		}
		return "paySuccess";
	}
	
	/**
	 * 后台异步返回
	 * @author zhouji
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/backUrl")
	public void backUrl(HttpServletRequest request){
		log.info("接收后台通知开始");
		// 获取请求参数中所有的信息
		Map<String, String> respParam = getAllRequestParam(request);
		if (null == respParam || respParam.size() == 0) {
			log.info("back respParam is:" + PayErrorCode.PAY_RESULT_IS_NULL.getCodeDes());
			return;
		}
		// 打印请求报文
		log.info("back respParam is:"+respParam);
		String respCode = respParam.get("respCode");
		String flowId = respParam.get("orderId");
		String amount = respParam.get("txnAmt");
		PayOrder payOrder = payOrderService.getPayOrderByFlowId(flowId);
		try {
			//验签
			boolean validateFlag = payValidateService.payValidate(respParam);
			if (validateFlag) {
				//判断流水是否存在
				if (null == payOrder) {
					log.info("pay flow not exist");
					return;
				}
				//判断支付流水状态是否已更新，如果已更新不进行更新操作,直接返回
				if (1 == payOrder.getStatus()) {
					log.info("pay flow status success[no longger update pay flow status]-[back]!");
					payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), respCode, PayFlowLogStatusEnum.SUCCESS.getStatus(), PayStatusEnum.SUCCESS.getStatusDesc() + "[后台返回:支付成功！支付流水状态已经被更新过!]");
					return;
				}
				Date payTime = new Date();
				PayOrder pOrder = new PayOrder();
				if ("00".equals(respCode)) {
					log.info("pay success!");//判断金额是否一致
					if (!amount.equals(String.valueOf(payOrder.getAmount().multiply(new BigDecimal(100)).intValue()))) {
						log.info("pay amt and pay flow amt is discord!");
						payOrder.setStatus(PayStatusEnum.EXCEPTION.getStatus());
						payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), respCode, PayFlowLogStatusEnum.FAIL.getStatus(), "后台返回:银联返回金额与支付流水金额不一致!");
					}else{
						//支付成功
						pOrder.setStatus(PayStatusEnum.SUCCESS.getStatus());
						payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), respCode, PayFlowLogStatusEnum.SUCCESS.getStatus(),"后台返回:支付成功!");
						//向支付结果表中 add消息
						payResultService.addPayResult(payOrder);
						payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), respCode, PayStatusEnum.SUCCESS.getStatus(), "后台返回:支付成功!" + "[add PayResult success]");
					}
				}else if ("01".equals(respCode)) {
					pOrder.setStatus(2);//交易失败
					payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), respCode, PayFlowLogStatusEnum.FAIL.getStatus(), "后台返回:支付失败!");
				}else if ("03".equals(respCode)||"04".equals(respCode)||"05".equals(respCode)) {
					pOrder.setStatus(3);//处理中
					payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), respCode, PayFlowLogStatusEnum.FAIL.getStatus(), "后台返回:" + PayStatusEnum.DISPOSE.getStatusDesc() + "[需调用查询接口查询支付流水结果]");
				} 
				//修改支付流水状态
				pOrder.setFlowId(new BigDecimal(flowId));
				pOrder.setAmount(new BigDecimal(amount));
				pOrder.setPayTime(payTime);
				pOrder.setQueryId(respParam.get("queryId"));
				pOrder.setRespCode(respParam.get("respCode"));
				payOrderService.updatePayOrder(pOrder);
			}
			log.info("接收后台通知结束");
		} catch (Exception e) {
			log.error("back notify error is:", e);
			payManager.payFlowLogAdd(payOrder.getOrderId(), Integer.parseInt(payOrder.getSourceSys()), respCode, PayFlowLogStatusEnum.FAIL.getStatus(), "后台返回:" + "[pay result done exception]" + e.getMessage());
		}
	}
	
	/**
	 * 获取请求参数中所有的信息
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(
			final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				// 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				if (res.get(en) == null || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		return res;
	}
}
