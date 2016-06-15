package com.jointown.zy.common.pay;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.dto.PayReqDto;
import com.jointown.zy.common.enums.PayErrorCode;
import com.jointown.zy.common.enums.PayFlowLogStatusEnum;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * @ClassName: PayReqDataCheck
 * @Description: 支付请求数据校验
 * @Author: ldp
 * @Date: 2015年4月8日
 * @Version: 1.0
 */
public class PayReqDataCheck {

	private static final Logger log = LoggerFactory.getLogger(PayReqDataCheck.class);
	
	private static PayManager payManager;
	
	static{
		payManager = (PayManager) SpringUtil.getBean("payManager");
	}
	
	/**
	 * 校验支付请求的数据
	 * @Author: ldp
	 * @Date: 2015年4月8日
	 * @param payReqDto
	 * @return
	 */
	public static PayErrorResult reqDataCheck(PayReqDto payReqDto){
		PayErrorResult per = null;
		//判断是否登录
		Subject subject = SecurityUtils.getSubject();
		UcUserVo ucUserVo = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		if (null == ucUserVo) {
			payManager.payFlowLogAdd(payReqDto.getOrderId()==null?"":payReqDto.getOrderId(), Integer.parseInt(payReqDto.getSourceSys()), PayErrorCode.PAY_UN_LOGIN.getCode(), PayFlowLogStatusEnum.FAIL.getStatus(), PayErrorCode.PAY_UN_LOGIN.getCodeDes());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_UN_LOGIN.getCode());
			per.setCodeDes(PayErrorCode.PAY_UN_LOGIN.getCodeDes());
			return per;
		}
		//付款人不能为空
		if (StringUtils.isBlank(payReqDto.getUserId())) {
			log.info("pay req data[userId] is not null");
			payManager.payFlowLogAdd(payReqDto.getOrderId()==null?"":payReqDto.getOrderId(), payReqDto.getSourceSys()==null?null:Integer.parseInt(payReqDto.getSourceSys()), PayErrorCode.PAY_REQDATA_USERID_IS_NOT_NULL.getCode(), PayFlowLogStatusEnum.FAIL.getStatus(), PayErrorCode.PAY_REQDATA_USERID_IS_NOT_NULL.getCodeDes());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_REQDATA_USERID_IS_NOT_NULL.getCode());
			per.setCodeDes(PayErrorCode.PAY_REQDATA_USERID_IS_NOT_NULL.getCodeDes());
			return per;
		}
		log.info("ucUserVo info is:" + String.valueOf(ucUserVo));
		//判断登录名与用户名是否一致
		if (!String.valueOf(ucUserVo.getUserId()).equals(payReqDto.getUserId())) {
			log.info("pay req data[userId] is:" + payReqDto.getUserId());
			log.info("session ucUserVo userId is: " + ucUserVo.getUserId());
			payManager.payFlowLogAdd(payReqDto.getOrderId()==null?"":payReqDto.getOrderId(), payReqDto.getSourceSys()==null?null:Integer.parseInt(payReqDto.getSourceSys()), PayErrorCode.PAYUSER_AND_LOGIN_USER_DISCORD.getCode(), PayFlowLogStatusEnum.FAIL.getStatus(), PayErrorCode.PAYUSER_AND_LOGIN_USER_DISCORD.getCodeDes());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAYUSER_AND_LOGIN_USER_DISCORD.getCode());
			per.setCodeDes(PayErrorCode.PAYUSER_AND_LOGIN_USER_DISCORD.getCodeDes());
			return per;
		}
		//判断签名数据是否为空
		if (StringUtils.isBlank(payReqDto.getSignData())) {
			log.info("pay req data[signData] is not null");
			payManager.payFlowLogAdd(payReqDto.getOrderId()==null?"":payReqDto.getOrderId(), payReqDto.getSourceSys()==null?null:Integer.parseInt(payReqDto.getSourceSys()), PayErrorCode.PAY_REQDATA_SIGNDATA_IS_NOT_NULL.getCode(), PayFlowLogStatusEnum.FAIL.getStatus(), PayErrorCode.PAY_REQDATA_SIGNDATA_IS_NOT_NULL.getCodeDes());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_REQDATA_SIGNDATA_IS_NOT_NULL.getCode());
			per.setCodeDes(PayErrorCode.PAY_REQDATA_SIGNDATA_IS_NOT_NULL.getCodeDes());
			return per;
		}
		//判断订单号是否为空
		if (StringUtils.isBlank(payReqDto.getOrderId())) {
			log.info("pay req data[orderId] is not null");
			payManager.payFlowLogAdd(payReqDto.getOrderId()==null?"":payReqDto.getOrderId(), payReqDto.getSourceSys()==null?null:Integer.parseInt(payReqDto.getSourceSys()), PayErrorCode.PAY_REQDATA_ORDERID_IS_NOT_NULL.getCode(), PayFlowLogStatusEnum.FAIL.getStatus(), PayErrorCode.PAY_REQDATA_ORDERID_IS_NOT_NULL.getCodeDes());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_REQDATA_ORDERID_IS_NOT_NULL.getCode());
			per.setCodeDes(PayErrorCode.PAY_REQDATA_ORDERID_IS_NOT_NULL.getCodeDes());
			return per;
		}
		//判断金额是否为空
		if (StringUtils.isBlank(payReqDto.getAmount())) {
			log.info("pay req data[amount] is not null");
			payManager.payFlowLogAdd(payReqDto.getOrderId(), payReqDto.getSourceSys()==null?null:Integer.parseInt(payReqDto.getSourceSys()), PayErrorCode.PAY_REQDATA_AMOUNT_IS_NOT_NULL.getCode(), PayFlowLogStatusEnum.FAIL.getStatus(), PayErrorCode.PAY_REQDATA_AMOUNT_IS_NOT_NULL.getCodeDes());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_REQDATA_AMOUNT_IS_NOT_NULL.getCode());
			per.setCodeDes(PayErrorCode.PAY_REQDATA_AMOUNT_IS_NOT_NULL.getCodeDes());
			return per;
		}
		//判断金额格式是否合法
		if (!PayUtil.isIegalAmt(String.valueOf(payReqDto.getAmount()))) {
			log.info("pay req data[amount] is notIegal");
			payManager.payFlowLogAdd(payReqDto.getOrderId(), payReqDto.getSourceSys()==null?null:Integer.parseInt(payReqDto.getSourceSys()), PayErrorCode.PAY_REQDATA_AMOUNT_IS_ILLAGEL.getCode(), PayFlowLogStatusEnum.FAIL.getStatus(), PayErrorCode.PAY_REQDATA_AMOUNT_IS_ILLAGEL.getCodeDes());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_REQDATA_AMOUNT_IS_ILLAGEL.getCode());
			per.setCodeDes(PayErrorCode.PAY_REQDATA_AMOUNT_IS_ILLAGEL.getCodeDes());
			return per;
		}
		//金额款项类型不能为空
		if (StringUtils.isBlank(payReqDto.getAmtType())) {
			log.info("pay req data[amtType] is not null");
			payManager.payFlowLogAdd(payReqDto.getOrderId(), payReqDto.getSourceSys()==null?null:Integer.parseInt(payReqDto.getSourceSys()), PayErrorCode.PAY_REQDATA_AMTTYPE_IS_NOT_NULL.getCode(), PayFlowLogStatusEnum.FAIL.getStatus(), PayErrorCode.PAY_REQDATA_AMTTYPE_IS_NOT_NULL.getCodeDes());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_REQDATA_AMTTYPE_IS_NOT_NULL.getCode());
			per.setCodeDes(PayErrorCode.PAY_REQDATA_AMTTYPE_IS_NOT_NULL.getCodeDes());
			return per;
		}
		//收款人不能为空
		if (StringUtils.isBlank(payReqDto.getRecieveId())) {
			log.info("pay req data[recieveId] is not null");
			payManager.payFlowLogAdd(payReqDto.getOrderId(), payReqDto.getSourceSys()==null?null:Integer.parseInt(payReqDto.getSourceSys()), PayErrorCode.PAY_REQDATA_RECIEVEID_IS_NOT_NULL.getCode(), PayFlowLogStatusEnum.FAIL.getStatus(), PayErrorCode.PAY_REQDATA_RECIEVEID_IS_NOT_NULL.getCodeDes());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_REQDATA_RECIEVEID_IS_NOT_NULL.getCode());
			per.setCodeDes(PayErrorCode.PAY_REQDATA_RECIEVEID_IS_NOT_NULL.getCodeDes());
			return per;
		}
		//付款系统标识不能为空
		if (StringUtils.isBlank(payReqDto.getSourceSys())) {
			log.info("pay req data[sourceSys] is not null");
			payManager.payFlowLogAdd(payReqDto.getOrderId(), payReqDto.getSourceSys()==null?null:Integer.parseInt(payReqDto.getSourceSys()), PayErrorCode.PAY_REQDATA_SOURCESYS_IS_NOT_NULL.getCode(), PayFlowLogStatusEnum.FAIL.getStatus(), PayErrorCode.PAY_REQDATA_SOURCESYS_IS_NOT_NULL.getCodeDes());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_REQDATA_SOURCESYS_IS_NOT_NULL.getCode());
			per.setCodeDes(PayErrorCode.PAY_REQDATA_SOURCESYS_IS_NOT_NULL.getCodeDes());
			return per;
		}
		//商品名称不能为空
		if (StringUtils.isBlank(payReqDto.getOrderTitle())) {
			log.info("pay req data[orderTitle] is not null");
			payManager.payFlowLogAdd(payReqDto.getOrderId(), Integer.parseInt(payReqDto.getSourceSys()), PayErrorCode.PAY_REQDATA_ORDERTITLE_IS_NOT_NULL.getCode(), PayFlowLogStatusEnum.FAIL.getStatus(), PayErrorCode.PAY_REQDATA_ORDERTITLE_IS_NOT_NULL.getCodeDes());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_REQDATA_ORDERTITLE_IS_NOT_NULL.getCode());
			per.setCodeDes(PayErrorCode.PAY_REQDATA_ORDERTITLE_IS_NOT_NULL.getCodeDes());
			return per;
		}
		//验签
		if (!PayUtil.sign(payReqDto)) {
			log.info("sign is error");
			payManager.payFlowLogAdd(payReqDto.getOrderId(), Integer.parseInt(payReqDto.getSourceSys()), PayErrorCode.PAY_SIGN_ERROR.getCode(), PayFlowLogStatusEnum.FAIL.getStatus(), PayErrorCode.PAY_SIGN_ERROR.getCodeDes());
			per = new PayErrorResult();
			per.setCode(PayErrorCode.PAY_SIGN_ERROR.getCode());
			per.setCodeDes(PayErrorCode.PAY_SIGN_ERROR.getCodeDes());
			return per;
		}
		return per;
	}
}
