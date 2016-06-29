package com.jointown.zy.web.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jointown.zy.common.dto.PayReqDto;
import com.jointown.zy.common.enums.PayErrorCode;
import com.jointown.zy.common.enums.PayFlowLogStatusEnum;
import com.jointown.zy.common.enums.PayStatusEnum;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.pay.IPayInstructionService;
import com.jointown.zy.common.pay.PayErrorResult;
import com.jointown.zy.common.pay.PayManager;
import com.jointown.zy.common.pay.PayReqDataCheck;
import com.jointown.zy.common.pay.PayUtil;
import com.jointown.zy.common.service.PayOrderService;
import com.jointown.zy.common.util.BigDecimalUtil;
import com.jointown.zy.common.util.IPUtil;

/**
 * 支付Controller
 * @author ldp
 * date 2015.2.10
 */
@Controller(value="payController")
public class PayController extends UserBaseController {
	
	private static final Logger log = LoggerFactory.getLogger(PayController.class);
	
	@Autowired
	@Qualifier("unionPay")
	private IPayInstructionService unionPayB2BInstruction;
	@Autowired
	@Qualifier("unionPayB2C")
	private IPayInstructionService unionPayB2CInstruction;
	@Autowired
	private PayOrderService payOrderService;
	@Autowired
	private PayManager payManager;
	/**
	 * 调银行(银联)指令进行支付
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/pay")
	public String pay(@ModelAttribute PayReqDto payReqDto, HttpServletRequest request,ModelMap modelMap){
		log.info("pay data is:" + payReqDto);
		
		String code = request.getParameter("code");
		PayErrorResult per = null;
		if (StringUtils.isBlank(code)) {
			log.info("req data[bankCode] is null");
			payManager.payFlowLogAdd(payReqDto.getOrderId(), Integer.parseInt(payReqDto.getSourceSys()), PayErrorCode.PAY_BANKCODE_IS_NOT_NULL.getCode(), PayFlowLogStatusEnum.FAIL.getStatus(), PayErrorCode.PAY_BANKCODE_IS_NOT_NULL.getCodeDes());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_BANKCODE_IS_NOT_NULL.getCode());
			per.setCodeDes(PayErrorCode.PAY_BANKCODE_IS_NOT_NULL.getCodeDes());
			modelMap.addAttribute("payErrorMsg", per);
			return "payError";
		}
		
		String bankCode = StringUtils.split(code,"-")[1];
		//再次校验
		per = PayReqDataCheck.reqDataCheck(payReqDto);
		if (null != per) {
			modelMap.addAttribute("payErrorMsg", per);
			return "payError";
		}
		PayOrder payOrder = new PayOrder();
		payOrder.setOrderId(payReqDto.getOrderId());
		payOrder.setAmount(new BigDecimal(payReqDto.getAmount()));
		payOrder.setBankCode(bankCode);
		payOrder.setCreateTime(new Date());
		payOrder.setPayChannel(Integer.parseInt(StringUtils.split(code,"-")[0]));//银联B2B
		payOrder.setCurrencyCode("CNY");
		payOrder.setSourceSys(payReqDto.getSourceSys());
		payOrder.setUserId(Integer.parseInt(payReqDto.getUserId()));
		payOrder.setRecieveId(Integer.parseInt(payReqDto.getRecieveId()));
		payOrder.setClientIp(IPUtil.getIP(request));
		payOrder.setAmtType(Integer.parseInt(payReqDto.getAmtType()));
		payOrder.setStatus(PayStatusEnum.UNPAY.getStatus());
		payOrder.setOrderTitle(payReqDto.getOrderTitle());
		payOrder.setFlowId(new BigDecimal(PayUtil.getSeqence()));
		
		//生成支付指令
		String form = null;
		if (1 == payOrder.getPayChannel().intValue()) {//生成银联B2B支付表单
			payOrder.setHandingFee(new BigDecimal("10"));//B2B收取手续费
			form = unionPayB2BInstruction.pay(payOrder);
		}else if(2 == payOrder.getPayChannel().intValue()){//B2C
			payOrder.setHandingFee(BigDecimalUtil.multiply(payOrder.getAmount(), new BigDecimal("0.003")));//B2C收取手续费
			form = unionPayB2CInstruction.pay(payOrder);
		}
		//生成支付流水
		int flag = payOrderService.insertPayOrder(payOrder);
		if (flag < 1) {
			log.info("pay flow create error");
			payManager.payFlowLogAdd(payReqDto.getOrderId(), Integer.parseInt(payReqDto.getSourceSys()), PayErrorCode.PAY_FLOW_EXCEPTION.getCode(), PayFlowLogStatusEnum.FAIL.getStatus(), PayErrorCode.PAY_FLOW_EXCEPTION.getCodeDes());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_FLOW_EXCEPTION.getCode());
			per.setCodeDes(PayErrorCode.PAY_FLOW_EXCEPTION.getCodeDes());
			modelMap.addAttribute("payErrorMsg", per);
			return "payError";
		}
		payManager.payFlowLogAdd(payReqDto.getOrderId(), Integer.parseInt(payReqDto.getSourceSys()), "00", PayFlowLogStatusEnum.SUCCESS.getStatus(), "向银联提交交易请求成功!");
		modelMap.addAttribute("form", form);
		return "pay";
	}
	
	
}
