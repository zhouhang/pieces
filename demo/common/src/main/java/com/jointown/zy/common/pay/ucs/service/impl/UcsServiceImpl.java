package com.jointown.zy.common.pay.ucs.service.impl;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import ucs.creditpay.entities.notices.UcsNoticeRequest;
import ucs.creditpay.entities.notices.UcsNoticeRequest1002;
import ucs.creditpay.entities.requests.BatchRemitItem;
import ucs.creditpay.entities.requests.Request1000;
import ucs.creditpay.entities.requests.Request1001;
import ucs.creditpay.entities.requests.Request1009;
import ucs.creditpay.entities.responses.Response1007;
import ucs.creditpay.entities.responses.Response1009;
import ucs.creditpay.security.SignatureFactory;
import ucs.creditpay.utils.Base64Utils;
import ucs.creditpay.utils.GsonUtils;

import com.google.gson.JsonObject;
import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.dto.PayReqDto;
import com.jointown.zy.common.enums.PayStatusEnum;
import com.jointown.zy.common.enums.RefundRemitStatusEnum;
import com.jointown.zy.common.enums.UcsCodeEnum;
import com.jointown.zy.common.model.CompanyCertify;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.model.RemitFlow;
import com.jointown.zy.common.model.RemitFlowLog;
import com.jointown.zy.common.model.RemitResult;
import com.jointown.zy.common.model.UcPersonCertify;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.pay.PayUtil;
import com.jointown.zy.common.pay.ucs.service.RemitAccountService;
import com.jointown.zy.common.pay.ucs.service.UcsService;
import com.jointown.zy.common.pay.ucs.util.UcsBase;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.CompanyCertifyService;
import com.jointown.zy.common.service.IMemberCertifyService;
import com.jointown.zy.common.service.PayOrderService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.UcUserVo;
@Component
public class UcsServiceImpl extends UcsBase implements UcsService {
	
	private static final Logger logger = LoggerFactory.getLogger(UcsServiceImpl.class);
	@Autowired
	private PayOrderService payOrderService;
	@Autowired
	private CompanyCertifyService companyCertifyService;
	@Autowired
	private IMemberCertifyService ucUserCertifyService;
	@Autowired
	private UcUserService ucUserService;
	@Autowired
	private RemitAccountService remitAccountService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	public Map<String,String> payOrder(PayReqDto payReqDto) {
		Map<String,String> params = new HashMap<String,String>();
		Request1001 payOrder = new Request1001();
		//获取当前用户session信息
		Subject subject = SecurityUtils.getSubject();
		UcUserVo ucUserVo = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		try {
			payOrder.setMallId(BankConfigConstant.UCS_MALLID);
			payOrder.setOptType(BankConfigConstant.TradeType.TRADE_TYPE_1001);
			payOrder.setFontURL(BankConfigConstant.UCS_PAYORDER_FONTURL);
			//业务参数
			payOrder.setOrderNo(payReqDto.getOrderId());
			payOrder.setPaymentNo(PayUtil.getSeqence());
			payOrder.setItemName(payReqDto.getOrderTitle());
			payOrder.setOrderAmount(payReqDto.getAmount());
			payOrder.setDeliveryAmount(payReqDto.getAmount());
			payOrder.setOrderDate(TimeUtil.formatDatetime(new Date(), "yyyyMMddHHmmss"));
			//个人信息参数
			payOrder.setUSRNBR(String.valueOf(ucUserVo.getUserId()));
			payOrder.setUSRName(ucUserVo.getUserName());
			payOrder.setUSRMobile(ucUserVo.getMobile());
			
			if (BankConfigConstant.UcsUserCertify.PRIVATE_CERTIFY_STATUS == ucUserVo.getCertifyStatus())
				 payOrder.setUSRType(BankConfigConstant.UCS_PRIVATE_USRTYPE);
			else
				 payOrder.setUSRType(BankConfigConstant.UCS_COMPANY_USRTYPE);
			
			//企业参数
			if(BankConfigConstant.UcsUserCertify.COMPANY_CERTIFY_STATUS == ucUserVo.getCertifyStatus()){
				Integer userId = Integer.valueOf(String.valueOf(ucUserVo.getUserId()));
				CompanyCertify companyInfo = companyCertifyService.findCompanyCertifyByUserId(userId);
				if(null!=companyInfo){
					//企业名称
					payOrder.setCompanyName(companyInfo.getCompanyName());
					//营业执照号码
					payOrder.setLicenseNo(companyInfo.getLicenceCode());
					//营业执照开始时间
					payOrder.setLicenseSDate(TimeUtil.formatDatetime(companyInfo.getLicenceStartdate(), "yyyyMMdd"));
					//营业执照结束时间
					payOrder.setLicenseEDate(TimeUtil.formatDatetime(companyInfo.getLicenceEnddate(), "yyyyMMdd"));
					//组织结构代码
					payOrder.setCertificateCode(companyInfo.getOrgCode());
				}
			}
			payOrder.serialize();
			params.put("data", payOrder.getRequestData());
			params.put("signdata", payOrder.getRequestSigndata());
			params.put("paymentNo", payOrder.getPaymentNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	/**
	 * 生成支付流水
	 * @param payReqDto
	 */
	public int addPayFlow(PayReqDto payReqDto,String paymentNo,String ip){
		logger.info("[珍药宝]开始记录支付流水 。。。");
		int flag = 0;
		if(null!=payReqDto){
			PayOrder payOrder = new PayOrder();
			payOrder.setOrderId(payReqDto.getOrderId());//订单号
			payOrder.setAmount(new BigDecimal(payReqDto.getAmount()));//金额
			payOrder.setBankCode("UCS");//机构
			payOrder.setCreateTime(new Date());
			payOrder.setPayChannel(BankConfigConstant.PayChannel.CMB);//支付频道
			payOrder.setCurrencyCode("CNY");//币种
			payOrder.setSourceSys(payReqDto.getSourceSys());//系统来源
			payOrder.setUserId(Integer.parseInt(payReqDto.getUserId()));//付款人
			payOrder.setRecieveId(Integer.parseInt(payReqDto.getRecieveId()));//收款人
			payOrder.setClientIp(ip);//用户ip
			payOrder.setAmtType(Integer.parseInt(payReqDto.getAmtType()));//金额类型
			payOrder.setStatus(PayStatusEnum.UNPAY.getStatus());//支付状态
			payOrder.setOrderTitle(payReqDto.getOrderTitle());//商品名
			payOrder.setFlowId(new BigDecimal(paymentNo));//支付流水号
			flag = payOrderService.insertPayOrder(payOrder);
			logger.info("[珍药宝]记录支付流水完成 。。。");
			return flag;
		}else{
			return flag ;
		}
	}
	
	/**
	 * 根据回调之后返回的状态,对数据做相应的处理
	 */
	public void payCallBack(String data , String signdata) throws Exception{
		UcsNoticeRequest noticeRequest = new UcsNoticeRequest(data,signdata);
		logger.debug("[data]         = " + data);
		logger.debug("[signdata]         = " + signdata);
		
		UcsNoticeRequest1002 noticeRequest1002 =new UcsNoticeRequest1002(noticeRequest.getJsonResult());
		logger.debug("[OptType]         = [1002]");
		logger.debug("[CState]         = " + noticeRequest1002.getCState());
		logger.debug("[PayTime]         = " + noticeRequest1002.getPayTime());
	}

	@Override
	public String payLogin(String type) throws Exception {
		if(type == null || "".equals(type))
			type = "0";
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		CompanyCertify companyCertify  = companyCertifyService.findCompanyCertifyByUserId(userinfo.getUserId().intValue());
		UcPersonCertify ucPersonCertify =ucUserCertifyService.getCertifyUcUserInfoByUserId(userinfo.getUserId().toString());
		Request1000 reqLogin = new Request1000();
		reqLogin.setMallId(BankConfigConstant.UCS_MALLID);
		reqLogin.setOptType(BankConfigConstant.TradeType.TRADE_TYPE_1000);
		reqLogin.setUSRNBR(userinfo.getUserId().toString());
		reqLogin.setUSRName(userinfo.getUserName());
		reqLogin.setUSRMobile(userinfo.getMobile());
		reqLogin.setPIndex(type);
		if(companyCertify!=null){
			reqLogin.setUSRType(BankConfigConstant.UCS_COMPANY_USRTYPE);
			reqLogin.setCompanyName(companyCertify.getCompanyName());
			reqLogin.setLicenseNo(companyCertify.getLicenceCode());
			SimpleDateFormat datetype = new SimpleDateFormat("yyyyMMdd");
			reqLogin.setLicenseSDate(datetype.format(companyCertify.getLicenceStartdate()));
			reqLogin.setLicenseEDate(datetype.format(companyCertify.getLicenceEnddate()));
			reqLogin.setCertificateCode(companyCertify.getOrgCode());
		}else{
			reqLogin.setUSRType(BankConfigConstant.UCS_PRIVATE_USRTYPE);
			if(ucPersonCertify!=null){
				//插入身份证,姓名信息
				reqLogin.setCertNo(ucPersonCertify.getIdCard());
				reqLogin.setRealName(ucPersonCertify.getName());
			}
		}
		
		
		String jsonResult = GsonUtils.toJson(reqLogin);
		String data = Base64Utils.encode(jsonResult.getBytes(Charset
				.forName("UTF-8")));
		String signdata = SignatureFactory.getSigner().signature(
				jsonResult);
		String html = PayUtil.submitForm("payLoingForm",BankConfigConstant.UCS_PAYLOGIN_ACTION,data,signdata);
		return html;
	}
	
	
	/**
	 * 记录划账流水
	 * @param remitAccountInfo
	 * @param remitNo
	 * @param payChannel
	 * @throws Exception
	 */
	public Integer insertRemitFlow(RemitFlow remitFlow,String remitNo,Integer payChannel) throws Exception{
		//插入对应的数据
		remitFlow.setFlowId(Long.parseLong(remitNo));
		remitFlow.setRemitChannel(payChannel);
		remitFlow.setCurrencyCode("CNY");
		String amount_info = remitFlow.getPlatformId()+":"+remitFlow.getPlatformAmount()+";"+remitFlow.getSellerId()+":"+remitFlow.getSellerAmount()+";"+remitFlow.getBuyerId()+":"+remitFlow.getBuyerAmount()+";";
		remitFlow.setAmountInfo(amount_info);
		remitFlow.setStatus(0);
		Date createTime = new Date();
		remitFlow.setCreateTime(createTime);
		remitFlow.setUpdateTime(createTime);
		//如果划账渠道为0(招行)默认插入珍药宝信息
		if(payChannel==0){
			//判断卖家划账金额
			if(remitFlow.getSellerAmount().compareTo(new BigDecimal(0))>0){
				UcUser seller = ucUserService.findUcUserById(remitFlow.getSellerId().intValue());
				remitFlow.setSellerBank("珍药材");
				remitFlow.setSellerAccount(seller.getUserName());
			}
			
			//判断买家划账金额
			if(remitFlow.getBuyerAmount().compareTo(new BigDecimal(0))>0){
				UcUser buyer = ucUserService.findUcUserById(remitFlow.getBuyerId().intValue());
				remitFlow.setBuyerBank("珍药材");
				remitFlow.setBuyerAccount(buyer.getUserName());;
			}
		}
		int status = remitAccountService.addRemitFlow(remitFlow);
		return status;
	}
	/**
	 * 修改划账流水(资金划账接口调用request1007)
	 * @param response1007
	 * @param remitNo
	 * @throws Exception
	 */
	public void updateRemitFlow(Response1007 response1007,String remitNo) throws Exception{
		RemitFlow remitFlow = remitAccountService.getRemitFlowById(remitNo);
		Date time = new Date();
		if(response1007.getCode()==100){
			remitFlow.setRespcode(Integer.toString(response1007.getCode()));
			remitFlow.setRespstatus(Integer.parseInt(response1007.getCState()));
			remitFlow.setRespmsg(response1007.getCMsg());
			if("1".equals(response1007.getCState())){
				remitFlow.setStatus(1);
				remitFlow.setRemitTime(time);
			}
			remitFlow.setUpdateTime(time);
		}else{
			remitFlow.setRespcode(Integer.toString(response1007.getCode()));
			remitFlow.setRespmsg(response1007.getMessage());
			remitFlow.setUpdateTime(time);
		}
		remitAccountService.updateRemitFlow(remitFlow);
	}
	/**
	 * 修改划账流水(批量划账接口调用request1009)
	 * @param response1009
	 * @param remitNo
	 * @throws Exception
	 */
	public void updateRemitFlow(Response1009 response1009,RemitFlow remitFlow) throws Exception{
		Date time = new Date();
		//回调成功
		if(Integer.valueOf(UcsCodeEnum.CODE_100.getCode()) == response1009.getCode()){
			remitFlow.setRespcode(Integer.toString(response1009.getCode()));
			remitFlow.setRespstatus(Integer.parseInt(response1009.getCState()));
			remitFlow.setRespmsg(response1009.getCMsg());
			//0 划账失败  1划账成功
			if("1".equals(response1009.getCState())){
				remitFlow.setStatus(RefundRemitStatusEnum.REMIT_SUCCESS.getStatus());
				remitFlow.setRemitTime(time);
			}
			remitFlow.setUpdateTime(time);
		}else{
			remitFlow.setStatus(RefundRemitStatusEnum.REMIT_FAILED.getStatus());
			remitFlow.setRespcode(Integer.toString(response1009.getCode()));
			remitFlow.setRespmsg(response1009.getMessage());
			remitFlow.setUpdateTime(time);
		}
		remitAccountService.updateRemitFlow(remitFlow);
	}
	/**
	 * 记录划账日志(批量划账接口调用request1009)
	 * @param remitAccountInfo
	 * @param response1009
	 * @param note
	 * @throws Exception
	 */
	public void insertRemitFlowLog(RemitFlow remitFlow,Response1009 response1009,String note) throws Exception{
		RemitFlowLog remitFlowLog = new RemitFlowLog();
		Date time = new Date();
		remitFlowLog.setFlowId(remitFlow.getFlowId());
		remitFlowLog.setRemitType(remitFlow.getRemitType());
		remitFlowLog.setSourcesys(remitFlow.getSourcesys());
		remitFlowLog.setOrderId(remitFlow.getOrderId());
		if(response1009!=null){
			remitFlowLog.setReturnCode(Integer.toString(response1009.getCode()));
			if(response1009.getCode()==100){
				//获取json判断json数据插入信息
				if("1".equals(response1009.getCState())){
					remitFlowLog.setNote("划账流水号:"+response1009.getRemitNo()+"划账金额:"+"划账状态:成功");
				}else{
					remitFlowLog.setNote("划账流水号:"+response1009.getRemitNo()+"划账金额:"+"划账状态:失败");
				}
				remitFlowLog.setStatus(Integer.parseInt(response1009.getCState()));
			}else{
				remitFlowLog.setNote("请求失败");
			}
		}else{
			remitFlowLog.setNote(note);
		}
		remitFlowLog.setCreateTime(time);
		remitAccountService.addRemitFlowLog(remitFlowLog);
	}
	/**
	 * 记录划账结果(批量划账接口调用request1009)
	 * @param remitAccountInfo
	 * @param response1009
	 * @throws Exception
	 */
	public void insertRemitResult(RemitFlow remitFlow,Response1009 response1009) throws Exception{
		RemitResult remitResult = new RemitResult();
		Date time = new Date();
		logger.info("优迈返回时间:"+response1009.getRemitTime());
		Date remitTime = TimeUtil.payDateTimeFormat(response1009.getRemitTime());
		logger.info("格式化时间:"+remitTime);
		remitResult.setSourcesys(remitFlow.getSourcesys());
		remitResult.setRemitType(remitFlow.getRemitType());
		remitResult.setOrderid(remitFlow.getOrderId());
		if(response1009.getCode()==100){
			remitResult.setFlowId(Long.parseLong(response1009.getRemitNo()));
			remitResult.setOprateStatus(Integer.parseInt(response1009.getCState()));
			remitResult.setRemitTime(remitTime);
		}
		remitResult.setCreateTime(time);
		remitResult.setStatus(0);
		logger.info("结果对象时间:"+remitResult.getRemitTime());
		remitAccountService.addRemitResult(remitResult);
	}
	
	/*--线下，银联支付方式订单划账
	 * 参数： remitAccountInfo 划账信息
	 * 	   PayChannel 支付渠道（划账渠道）
	 * ----*/
	public String  offline(RemitFlow remitFlow) throws Exception{
		//插入划账流水日志表
		insertOfflineRemitFlowLog(remitFlow.getSourcesys(),
					remitFlow.getRemitType(),
					remitFlow.getOrderId(),
				  "100",
				  "接收交易系统划账请求，支付系统采用线下划账。",
				  1);
		insertOfflineRemitResult(remitFlow);

		//更新划账流水表
		updateOfflineRemitFlow(remitFlow);
		//插入日志表
		insertOfflineRemitFlowLog(remitFlow.getSourcesys(),
								  remitFlow.getRemitType(),
								  remitFlow.getOrderId(),
								  "100",
								  "完成划账，返回交易系统",
								  1);
	
		return remitFlow.getFlowId().toString();
	}
	
	
	/*--线下，银联支付方式订单划账 ， 插入流水信息
	 * * 参数： remitAccountInfo 划账信息
	 * 		 remitFlowId 划账流水号
	 * 	     PayChannel 支付渠道（划账渠道）
	 * ----*/
	public void insertOfflineRemitFlow(RemitFlow remitFlow) throws Exception{
		remitFlow.setCurrencyCode("CNY");
		String amount_info = remitFlow.getPlatformId()+":"+remitFlow.getPlatformAmount()+";"+remitFlow.getSellerId()+":"+remitFlow.getSellerAmount()+";"+remitFlow.getBuyerId()+":"+remitFlow.getBuyerAmount()+";";
		remitFlow.setAmountInfo(amount_info);
		remitFlow.setStatus(0);
		Date createTime = new Date();
		remitFlow.setCreateTime(createTime);
		remitFlow.setUpdateTime(createTime);
		remitAccountService.addRemitFlow(remitFlow);
	}
	
	/*--线下，银联支付方式订单划账 ， 更新流水信息
	 * * 参数： remitFlowId 划账流水号
	 * ----*/
	
	public void updateOfflineRemitFlow(RemitFlow remitFlow) throws Exception{
		Date time = new Date();
		remitFlow.setStatus(remitFlow.getStatus());
		if(remitFlow.getStatus()==1){
			remitFlow.setRespcode("100");
			remitFlow.setRespstatus(1);
		}
		remitFlow.setUpdateTime(time);
		remitAccountService.updateRemitFlow(remitFlow);
	}
	
	/*--线下，银联支付方式订单划账 ， 插入划账日志
	 * * 参数： source_sys 来源系统
	 * 		 remit_type 划账类型
	 * 		 orderId 订单号
	 * 		 ReturnCode 返回码
	 * 		 Note 备注
	 * 	     Status  状态
	 * 		 
	 * ----*/
	public void insertOfflineRemitFlowLog(int source_sys, int remit_type,String orderId,String ReturnCode,String Note,int Status)
	throws Exception{
		RemitFlowLog remitFlowLog = new RemitFlowLog();
		Date time = new Date();
		remitFlowLog.setSourcesys(source_sys);
		remitFlowLog.setOrderId(orderId);
		remitFlowLog.setReturnCode(ReturnCode);
		remitFlowLog.setNote(Note);
		remitFlowLog.setStatus(Status);
		remitFlowLog.setCreateTime(time);
		remitAccountService.addRemitFlowLog(remitFlowLog);
	}
	
	/*--线下，银联支付方式订单划账 ， 插入划账结果
	 * * 参数： source_sys 来源系统
	 * 		 remit_type 划账类型
	 * 		 orderId 订单号
	 * 		 remitFlowId 划账流水号
	 * 	     user_id  分润用户ID
	 * 		 amount  分润金额
	 * 		 
	 * ----*/
	public void insertOfflineRemitResult(RemitFlow remitFlow)
	throws Exception{
		RemitResult remitResult = new RemitResult();
		Date time = new Date();
		remitResult.setSourcesys(remitFlow.getSourcesys());
		remitResult.setRemitType(remitFlow.getRemitType());
		remitResult.setOrderid(remitFlow.getOrderId());
		remitResult.setFlowId(remitFlow.getFlowId());
		remitResult.setCreateTime(time);
		remitResult.setOprateStatus(remitFlow.getStatus());
		remitResult.setStatus(0);
		remitResult.setRemitTime(remitFlow.getRemitTime());
		
		remitAccountService.addRemitResult(remitResult);
	}

	@Override
	@ResponseBody
	public String batchRemitAccount(RemitFlow remitFlow)
			throws Exception {
		insertRemitFlowLog(remitFlow,null,"财务发起处理请求!");
		//0:失败 1:成功
		String msg="";
		JsonObject params = new JsonObject();
		/**
		 * 根据退款管理的退款的状态操作(退款/拒绝)
		 * 来执行下面的业务,若是珍药宝 ,则需要执行回调业务
		 */
		if(remitFlow.getStatus()==1){
			//如果是招行的支付订单,才会去调用招行接口,其他订单直接结束
			if(remitFlow.getRemitChannel()==0){
				//组成报文
				Request1009 request = new Request1009();
				request.setMallId(BankConfigConstant.UCS_MALLID);
				request.setOptType(Integer.parseInt(BankConfigConstant.UCS_REMITACCOUNTS_OPTTYPE));
				request.setBatchRemitNo(remitFlow.getFlowId().toString());
				
				List<BatchRemitItem> list = new ArrayList<BatchRemitItem>();
				
				//组成卖家划账报文(卖家分账金额不为0时)
				if(remitFlow.getSellerAmount().compareTo(new BigDecimal(0))>0){
					BatchRemitItem seller = new BatchRemitItem();
					seller.setOrderNo(remitFlow.getOrderId());
					seller.setPaymentNo("");
					seller.setAmount(remitFlow.getSellerAmount().toString());
					UcUser sUser = ucUserService.findUcUserById(remitFlow.getSellerId().intValue());
					seller.setUSRNBR(sUser.getUserId().toString());
					seller.setUSRName(sUser.getUserName());
					seller.setUSRMobile(sUser.getMobile());
					CompanyCertify companyCertify  = companyCertifyService.findCompanyCertifyByUserId(remitFlow.getSellerId().intValue());
					if(companyCertify!=null){
						seller.setUSRType(Integer.parseInt(BankConfigConstant.UCS_COMPANY_USRTYPE));
						seller.setCompanyName(companyCertify.getCompanyName());
						seller.setLicenseNo(companyCertify.getLicenceCode());
						SimpleDateFormat datetype = new SimpleDateFormat("yyyyMMdd");
						seller.setLicenseSDate(datetype.format(companyCertify.getLicenceStartdate()));
						seller.setLicenseEDate(datetype.format(companyCertify.getLicenceEnddate()));
						seller.setCertificateCode(companyCertify.getOrgCode());
					}else{
						seller.setUSRType(Integer.parseInt(BankConfigConstant.UCS_PRIVATE_USRTYPE));
					}
					list.add(seller);
				}
				
				//组成平台划账报文(平台分账金额不为0时)
				if(remitFlow.getPlatformAmount().compareTo(new BigDecimal(0))>0){
					BatchRemitItem platform = new BatchRemitItem();
					platform.setOrderNo(remitFlow.getOrderId());
					platform.setPaymentNo("");
					platform.setAmount(remitFlow.getPlatformAmount().toString());
					UcUser pUser = ucUserService.findUcUserById(remitFlow.getPlatformId().intValue());
					platform.setUSRNBR(pUser.getUserId().toString());
					platform.setUSRName(pUser.getUserName());
					platform.setUSRMobile(pUser.getMobile());
					CompanyCertify pCompanyCertify  = companyCertifyService.findCompanyCertifyByUserId(remitFlow.getPlatformId().intValue());
					if(pCompanyCertify!=null){
						platform.setUSRType(Integer.parseInt(BankConfigConstant.UCS_COMPANY_USRTYPE));
						platform.setCompanyName(pCompanyCertify.getCompanyName());
						platform.setLicenseNo(pCompanyCertify.getLicenceCode());
						SimpleDateFormat datetype = new SimpleDateFormat("yyyyMMdd");
						platform.setLicenseSDate(datetype.format(pCompanyCertify.getLicenceStartdate()));
						platform.setLicenseEDate(datetype.format(pCompanyCertify.getLicenceEnddate()));
						platform.setCertificateCode(pCompanyCertify.getOrgCode());
					}else{
						platform.setUSRType(Integer.parseInt(BankConfigConstant.UCS_PRIVATE_USRTYPE));
					}
					list.add(platform);
				}
				
				//组成买家划账报文(买家分账金额不为0时)
				if(remitFlow.getBuyerAmount().compareTo(new BigDecimal(0))>0){
					BatchRemitItem buyer = new BatchRemitItem();
					buyer.setOrderNo(remitFlow.getOrderId());
					buyer.setPaymentNo("");
					buyer.setAmount(remitFlow.getBuyerAmount().toString());
					UcUser bUser = ucUserService.findUcUserById(remitFlow.getBuyerId().intValue());
					buyer.setUSRNBR(bUser.getUserId().toString());
					buyer.setUSRName(bUser.getUserName());
					buyer.setUSRMobile(bUser.getMobile());
					CompanyCertify bCompanyCertify  = companyCertifyService.findCompanyCertifyByUserId(remitFlow.getBuyerId().intValue());
					if(bCompanyCertify!=null){
						buyer.setUSRType(Integer.parseInt(BankConfigConstant.UCS_COMPANY_USRTYPE));
						buyer.setCompanyName(bCompanyCertify.getCompanyName());
						buyer.setLicenseNo(bCompanyCertify.getLicenceCode());
						SimpleDateFormat datetype = new SimpleDateFormat("yyyyMMdd");
						buyer.setLicenseSDate(datetype.format(bCompanyCertify.getLicenceStartdate()));
						buyer.setLicenseEDate(datetype.format(bCompanyCertify.getLicenceEnddate()));
						buyer.setCertificateCode(bCompanyCertify.getOrgCode());
					}else{
						buyer.setUSRType(Integer.parseInt(BankConfigConstant.UCS_PRIVATE_USRTYPE));
					}
					list.add(buyer);
				}
				request.setItems(list);
				//插入记录
				//insertRemitFlow(remitAccountInfo,remitNo,PayChannel);
				insertRemitFlowLog(remitFlow,null,"财务同意划账,调用珍药宝批量划账接口!");
				//调用接口接受返回参数
				Response1009 response = request.Send(BankConfigConstant.UCS_REMITACCOUNT_UCSCREDITPAY_SERVICEURL); 
				insertRemitFlowLog(remitFlow,null,"珍药宝批量划账接口返回结果!");
				logger.info("调用状态:"+response.getCode()+"返回状态:"+response.getCState()+"返回信息:"+response.getMessage());
				logger.info("修改划账流水表");
				//修改划账流水记录信息
				updateRemitFlow(response,remitFlow);
				logger.info("插入划账流水日志表");
				insertRemitFlowLog(remitFlow,response,null);
				if(response.getCode()==100&&response.getCState().equals("1")){
					logger.info("插入划账结果表");
					insertRemitResult(remitFlow,response);
					insertRemitFlowLog(remitFlow,null,"处理成功,插入结果信息!");
					params.addProperty("code", "1");
				}else{
					params.addProperty("code", "0");
					params.addProperty("msg", response.getCode());
				}
			}else{
				//线下划账，不调用招行接口，直接生成划账信息
				offline(remitFlow);
				params.addProperty("code", "1");
				insertRemitFlowLog(remitFlow,null,"财务线下处理!");
			}
		}else{
			insertOfflineRemitResult(remitFlow);
			updateOfflineRemitFlow(remitFlow);
			params.addProperty("code", "1");
			insertRemitFlowLog(remitFlow,null,"财务拒绝划账!");
		}
		msg = params.toString();
		return msg;
	}

	@Override
	public String addRemitFlow(RemitFlow remitFlow)
			throws Exception {
		insertRemitFlowLog(remitFlow,null,"交易系统提交审批请求!");
		String msg = "";
		//插入划账流水表
		String remitNo = PayUtil.getSeqence();
		List<PayOrder> payOrderList = payOrderService.getPayOrderByOrderId(remitFlow.getOrderId());
		JsonObject params = new JsonObject();
		if(payOrderList.size()>0){
			//获取支付渠道信息
			int PayChannel=payOrderList.get(0).getPayChannel();
			int status = insertRemitFlow(remitFlow,remitNo,PayChannel);
			//插入成功返回成功信息给交易
			if(status==1){
				//插入日志表
				remitFlow.setFlowId(Long.parseLong(remitNo));
				insertRemitFlowLog(remitFlow,null,"交易系统审批完成,提交支付系统处理,开始插入划账流水!");
				params.addProperty("code", "1");
				params.addProperty("msg", "SUCCESS");
				params.addProperty("remitno", remitNo);
		        msg = params.toString();
		        insertRemitFlowLog(remitFlow,null,"划账流水插入成功,等待财务操作!");
			}else{
				params.addProperty("code", "0");
				params.addProperty("msg", "FAILURE");
				msg = params.toString();
				insertRemitFlowLog(remitFlow,null,"划账流水插入失败,请重新操作!");
			}
		}else{
			params.addProperty("code", "0");
			params.addProperty("msg", "该订单支付流水有问题,请核实!");
			msg = params.toString();
			insertRemitFlowLog(remitFlow,null,"划账流水插入失败,该订单支付流水有问题,请核实后重新操作!");
		}
		return msg;
	}
}
