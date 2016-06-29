package com.jointown.zy.common.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.dao.BusiOrderDao;
import com.jointown.zy.common.dao.PayOrderDao;
import com.jointown.zy.common.dao.PayResultDao;
import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.dto.PayReqDto;
import com.jointown.zy.common.dto.PayVoucherAddDto;
import com.jointown.zy.common.enums.PayFlowLogStatusEnum;
import com.jointown.zy.common.enums.PayStatusEnum;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.model.PayResult;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.pay.PayManager;
import com.jointown.zy.common.pay.PayUtil;
import com.jointown.zy.common.pay.PayVoucherUpload;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.PayOrderService;
import com.jointown.zy.common.service.PayVoucherService;
import com.jointown.zy.common.util.BigDecimalUtil;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.common.vo.PayFlowListVo;
import com.jointown.zy.common.vo.UcUserVo;

@Service
public class PayVoucherServiceImpl implements PayVoucherService {

	private static final Logger log = LoggerFactory.getLogger(PayFlowLogServiceImpl.class);
	
	@Autowired
	private PayOrderService payOrderService;
	@Autowired
	private PayVoucherUpload payVoucherUpload;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private BusiOrderDao busiOrderDao;
	@Autowired
	private UcUserDao ucUserDao;
	@Autowired
	private PayOrderDao payOrderDao;
	@Autowired
	private PayManager payManager;
	@Autowired
	private PayResultDao  payResultDao;
	@Autowired
	private MessageConfigManage messageConfigManage;
	
	@Override
	public String payVoucherUpload(InputStream is, PayReqDto payReqDto,String clientIp) {
		boolean result = false;
		try {
			String picPath = payVoucherUpload.uploadPic(is);
			log.info("[线下支付前台]图片凭证 : " + picPath);
			if (StringUtils.isNotBlank(picPath)) {
				this.insertPayflow(payReqDto, picPath, clientIp);
				result = true;
				log.info("[线下支付前台]Insert pay flow Success");
			} else {
				log.error("[线下支付前台]上传回单异常！");
			}
		} catch (Exception e) {
			log.error("[线下支付前台]新增支付流水异常!"+e);
		}
		return result == true ? "true" : "false" ;
	}
	
	/**
	 * 生成支付流水
	 * @param payReqDto
	 * @param picPath
	 * @param clientIp
	 * @return
	 * @throws Exception
	 */
	public int insertPayflow(PayReqDto payReqDto,String picPath,String clientIp) throws Exception{
			int result = 0 ;
			PayOrder payOrder = new PayOrder();
			payOrder.setOrderId(payReqDto.getOrderId());//订单号
			payOrder.setAmount(new BigDecimal(payReqDto.getAmount()));//金额
			payOrder.setCurrencyCode("CNY");//币种
			payOrder.setPayChannel(BankConfigConstant.PayChannel.BANK_TRANSFER);//支付渠道
			payOrder.setBankCode("BVOUCHER");
			payOrder.setSourceSys(payReqDto.getSourceSys());//系统来源
			payOrder.setUserId(Integer.parseInt(payReqDto.getUserId()));//付款人
			payOrder.setRecieveId(Integer.parseInt(payReqDto.getRecieveId()));//收款人
			payOrder.setClientIp(clientIp);//用户ip
			payOrder.setAmtType(Integer.parseInt(payReqDto.getAmtType()));//金额类型
			payOrder.setStatus(PayStatusEnum.UNPAY.getStatus());//支付状态
			payOrder.setOrderTitle(payReqDto.getOrderTitle());//商品名
			payOrder.setFlowId(new BigDecimal(PayUtil.getSeqence()));//支付流水号
			payOrder.setVoucherPic(picPath);
			payOrder.setCreateTime(new Date());
			//payOrder.setPayTime(new Date());
			result = payOrderService.insertPayOrder(payOrder);
			if(result > 0){		
				payManager.payFlowLogAdd(payOrder.getFlowId().toString(), Integer.parseInt(payOrder.getSourceSys()), "00", new Integer(PayFlowLogStatusEnum.SUCCESS.getStatus()), "[线下支付前台]记录流水成功");
			}else {
				payManager.payFlowLogAdd(payOrder.getFlowId().toString(), Integer.parseInt(payOrder.getSourceSys()), "00", new Integer(PayFlowLogStatusEnum.FAIL.getStatus()), "[线下支付前台]记录流水失败");
			}
			return result;
	}
	
	@Override
	public boolean smsAndEmailSend(UcUserVo ucUser,String orderId,String bankCode) {
		boolean flag = false;
		String mobileNo = ucUser.getMobile();
		if(StringUtils.isNotBlank(mobileNo) && StringUtils.isNotBlank(orderId)){
			String sendMsg = GetMessageContext.getPayVoucherAccountMsg(orderId, bankCode);
			try {
				taskExecutor.execute(messageConfigManage.getMessageChannelTask(mobileNo, sendMsg));
				flag = true;
			} catch (Exception e) {
				log.error("[线下支付前台]短信发送失败！"+e);
			}
			return flag;
		}else{
			log.error("[线下支付前台]短信手机号 或 订单号为空!");
			return flag;
		}
	}
	
	@Override
	public int payVoucherAdd(PayVoucherAddDto payVoucherAddDto)throws Exception {
		PayOrder payOrder = new PayOrder();
		payOrder.setAmount(new BigDecimal(payVoucherAddDto.getAmount()));
		payOrder.setSourceSys(payVoucherAddDto.getSourceSys());
		payOrder.setCreateTime(new Date());
		payOrder.setCurrencyCode("CNY");
		payOrder.setFlowId(new BigDecimal(payVoucherAddDto.getPayFlowId()));
		payOrder.setOrderId(payVoucherAddDto.getOrderId());
		payOrder.setPayChannel(Integer.parseInt(payVoucherAddDto.getPayChannel()));
		payOrder.setStatus(Integer.parseInt(String.valueOf(1)));
		payOrder.setVoucherPic(payVoucherAddDto.getPayVoucher());
		payOrder.setAmtType(Integer.parseInt(payVoucherAddDto.getAmtType()));
		payOrder.setBankCode("BVOUCHER");
		System.out.println(TimeUtil.parseDatetime(payVoucherAddDto.getPayDate()));
		payOrder.setPayTime(TimeUtil.parseDatetime(payVoucherAddDto.getPayDate()));
		//购买者-付款人
		UcUser ucUserPayer = ucUserDao.findUcUserByUserName(payVoucherAddDto.getPayerAccount());
		payOrder.setUserId(Integer.parseInt(String.valueOf(ucUserPayer.getUserId())));
		//挂牌者-收款人
		UcUser ucUserPayee = ucUserDao.findUcUserByUserName(payVoucherAddDto.getPayeeAccount());
		payOrder.setRecieveId(Integer.parseInt(String.valueOf(ucUserPayee.getUserId())));
		//确认人-操作人
		BossUserVo bossUserVo = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		payOrder.setConfirmorId(bossUserVo.getId());
		payOrder.setConfirmTime(new Date());
		
		int flag = payOrderDao.insertPayOrder(payOrder);
		if (flag > 0) {
			log.info("pay voucher add is success!");
			//向结果表中插记录
			PayResult payResult = new PayResult();
			payResult.setAmount(payOrder.getAmount());
			payResult.setAmtType(payOrder.getAmtType());
			payResult.setFlowId(payOrder.getFlowId());
			payResult.setSourceSys(Integer.parseInt(payOrder.getSourceSys()));
			payResult.setOrderId(payOrder.getOrderId());
			payResult.setStatus(0);
			payResult.setCreateTime(new Date());
			payResultDao.addPayResult(payResult);
			//记录日志
			payManager.payFlowLogAdd(payOrder.getFlowId().toString(), Integer.parseInt(payOrder.getSourceSys()), "00", new Integer(PayFlowLogStatusEnum.SUCCESS.getStatus()), "[boss后台]线下支付记录添加成功");
		}else {
			//记录日志
			payManager.payFlowLogAdd(payOrder.getFlowId().toString(), Integer.parseInt(payOrder.getSourceSys()), "00", new Integer(PayFlowLogStatusEnum.FAIL.getStatus()), "[boss后台]线下支付记录添加失败");
		}
		return flag;
	}

	@Override
	public PayVoucherAddDto getBusiOrderInfo(String orderId, String amtType) {
		//获取交易订单信息
		BusiOrder busiOrder = busiOrderDao.selectBusiOrderById(orderId);
		if (null == busiOrder) {
			log.info("busiOrder is null!");
			return null;
		}
		//生成支付流水号
		String payFlowId = PayUtil.getSeqence();
		PayVoucherAddDto payVoucherAddDto = new PayVoucherAddDto();
		payVoucherAddDto.setPayFlowId(payFlowId);
		//购买者-付款人
		UcUser ucUserBuyer = ucUserDao.findUcUserById(Integer.parseInt(busiOrder.getBuyer().toString()));
		payVoucherAddDto.setPayerAccount(ucUserBuyer.getUserName());
		payVoucherAddDto.setPayerName(ucUserBuyer.getCompanyName());
		//挂牌者-收款人
		UcUser ucUserPayee = ucUserDao.findUcUserById(Integer.parseInt(busiOrder.getUserid().toString()));
		payVoucherAddDto.setPayeeAccount(ucUserPayee.getUserName());
		payVoucherAddDto.setPayeeName(ucUserPayee.getCompanyName());
		if ("0".equals(amtType)) {//保证金
			payVoucherAddDto.setAmount(busiOrder.getDeposit().toString());
		}else if ("1".equals(amtType)) {//尾款(订单总额-保证金)
			payVoucherAddDto.setAmount(BigDecimalUtil.subtract(busiOrder.getTotalprice(), busiOrder.getDeposit()).toString());
		}else if ("2".equals(amtType)) {//全款
			payVoucherAddDto.setAmount(busiOrder.getTotalprice().toString());
		}
		log.info("getBusiOrderInfo-payVoucher:" + payVoucherAddDto.toString());
		return payVoucherAddDto;
	}

	@Override
	public int payConfirm(String flowId,String payDate,String payStatus)throws Exception {
		//根据流水号获取支付流水信息
		PayOrder pOrder = payOrderDao.getPayOrderByFlowId(flowId);
		//根据订单号修改支付流水
		PayOrder payOrder = new PayOrder();
		payOrder.setFlowId(new BigDecimal(flowId));
		BossUserVo bossUserVo = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		payOrder.setConfirmorId(bossUserVo.getId());
		payOrder.setConfirmTime(new Date());
		payOrder.setStatus(Integer.parseInt(payStatus));
		payOrder.setPayTime(TimeUtil.parseDatetime(payDate));
		
		int flag = payOrderDao.updatePayVoucherByFlowId(payOrder);
		if (flag > 0) {
			//获取交易订单号相同的重复提交的信息,将他们设置为支付失败
			List<PayOrder> payOrderList = payOrderDao.getPayFlowByCondition(pOrder);
			if(payOrderList.size()>0){
				for (PayOrder payFlow : payOrderList) {
					payOrderDao.updatePayFlow(payFlow);
				}
			}
			//向结果表中插记录
			PayResult payResult = new PayResult();
			payResult.setAmount(pOrder.getAmount());
			payResult.setAmtType(pOrder.getAmtType());
			payResult.setFlowId(pOrder.getFlowId());
			payResult.setSourceSys(Integer.parseInt(pOrder.getSourceSys()));
			payResult.setOrderId(pOrder.getOrderId());
			payResult.setStatus(0);
			payResult.setCreateTime(new Date());
			if (payStatus.equals(String.valueOf(PayStatusEnum.SUCCESS.getStatus()))) {
				payResultDao.addPayResult(payResult);
			}
			//记录日志
			payManager.payFlowLogAdd(pOrder.getFlowId().toString(), Integer.parseInt(pOrder.getSourceSys()), "00", new Integer(PayFlowLogStatusEnum.SUCCESS.getStatus()), "线下支付记录确认付款成功");
		}else {
			//记录日志
			payManager.payFlowLogAdd(pOrder.getFlowId().toString(), Integer.parseInt(pOrder.getSourceSys()), "00", new Integer(PayFlowLogStatusEnum.FAIL.getStatus()), "线下支付记录确认付款失败");
		}
		return flag;
	}

	@Override
	public PayFlowListVo getPayFlowVoByFlowId(String flowId) throws Exception {
		return payOrderDao.getPayFlowVoByFlowId(flowId);
	}

	@Override
	public MessageVo verifyOrder(String orderId,String amtType) throws Exception {
		MessageVo mVo = new MessageVo();
		//获取交易订单信息
		BusiOrder busiOrder = busiOrderDao.selectBusiOrderById(orderId);
		if (null == busiOrder) {
			log.info("busiOrder is null!");
			mVo.setOk(false);
			mVo.addError("Error04", "交易订单不存在，请重新输入交易订单号!");
			return mVo;
		}
		//线下付尾款时，校验保证金是否是线下付款,如果是线下付款，才能添加尾款支付记录，否则无法添加
		if("1".equals(amtType)){
			PayOrder payOrder = payOrderDao.getPaySuccessOrderByOrderId(orderId);
			if (!(null != payOrder && payOrder.getPayChannel().intValue() == 3 && payOrder.getAmtType().intValue() == 0)) {
				mVo.setOk(false);
				mVo.addError("error07", "该交易订单的保证金不是通过线下支付，无法添加尾款线下支付流水记录!");
				return mVo;
			}
		}
		//付保证金时，交易订单的交易状态必须是已下单，否则无法添加保证金支付记录
		if ("0".equals(amtType) && busiOrder.getOrderstate().intValue() != 0) {
			mVo.setOk(false);
			mVo.addError("error05", "添加支付保证金记录时，交易订单状态必须为已下单状态，请确认该交易订单状态是否为已下单状态!");
			return mVo;
		}
		//付尾款时，交易订单的交易状态必须是已备货，否则无法添加尾款的支付记录
		if("1".equals(amtType) && busiOrder.getOrderstate().intValue() != 5){
			mVo.setOk(false);
			mVo.addError("error06", "添加支付尾款记录时，交易订单状态必须为已备货状态，请确认该交易订单状态是否为已备货状态!");
			return mVo;
		}
		mVo.setOk(true);
		return mVo;
	}
		
	@Override
	public int insertPayflowOffLine4WeiXin(PayReqDto payReqDto,String clientIp,String picPath)throws Exception {
		PayOrder payOrder = new PayOrder();
		payOrder.setOrderId(payReqDto.getOrderId());
		payOrder.setAmount(new BigDecimal(payReqDto.getAmount()));
		payOrder.setCurrencyCode("CNY");
		payOrder.setPayChannel(BankConfigConstant.PayChannel.BANK_TRANSFER);//支付渠道
		payOrder.setBankCode("BVOUCHER");
		payOrder.setSourceSys(payReqDto.getSourceSys());
		payOrder.setUserId(Integer.parseInt(payReqDto.getUserId()));//付款人
		payOrder.setRecieveId(Integer.parseInt(payReqDto.getRecieveId()));//收款人
		
		payOrder.setClientIp(clientIp);//用户ip
		payOrder.setAmtType(Integer.parseInt(payReqDto.getAmtType()));
		payOrder.setStatus(PayStatusEnum.UNPAY.getStatus());
		payOrder.setOrderTitle(payReqDto.getOrderTitle());//商品名
		payOrder.setFlowId(new BigDecimal(PayUtil.getSeqence()));//支付流水号
		payOrder.setVoucherPic(picPath);
		payOrder.setCreateTime(new Date());	
		int flag = payOrderDao.insertPayOrder(payOrder);
		if (flag > 0) {
			log.info("pay voucher add is success!");
			//记录日志
			payManager.payFlowLogAdd(payOrder.getFlowId().toString(), Integer.parseInt(payOrder.getSourceSys()), "00", new Integer(PayFlowLogStatusEnum.SUCCESS.getStatus()), "[微信]线下支付记录添加成功");
		}else {
			//记录日志
			payManager.payFlowLogAdd(payOrder.getFlowId().toString(), Integer.parseInt(payOrder.getSourceSys()), "00", new Integer(PayFlowLogStatusEnum.FAIL.getStatus()), "[微信]线下支付记录添加失败");
		}
		return flag;
	}
}
