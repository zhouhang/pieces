package com.jointown.zy.common.service;

import java.io.InputStream;

import com.jointown.zy.common.dto.PayReqDto;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.common.vo.PayFlowListVo;
import com.jointown.zy.common.vo.UcUserVo;
import com.jointown.zy.common.dto.PayVoucherAddDto;
/**
 * @ClassName: PayVoucherService
 * @Description: 银行汇款支付凭证上传service
 * @Author: ldp
 * @Date: 2015年5月14日
 * @Version: 1.0
 */
public interface PayVoucherService {

	/**
	 * 汇款凭证上传
	 * @Author: ldp
	 * @Date: 2015年5月14日
	 */
	public String payVoucherUpload(InputStream is,PayReqDto payReqDto,String clientIp);
	
	/**
	 * 支付页面用户未上传凭证，后台操作人员新增支付凭证，生成支付流水
	 * @Author: ldp
	 * @Date: 2015年5月14日
	 */
	public int payVoucherAdd(PayVoucherAddDto payVoucherAddDto)throws Exception;
	
	/**
	 * 银行汇款账号,短信提示和邮件提醒客服
	 * @Author: ldp
	 * @Date: 2015年5月14日
	 */
	public boolean smsAndEmailSend(UcUserVo ucUser,String orderId,String bankCode);
	
	/**
	 * 根据交易订单号获取订单信息
	 * @Author: ldp
	 * @Date: 2015年5月18日
	 * @param orderId
	 * @param amtType
	 * @return
	 */
	public PayVoucherAddDto getBusiOrderInfo(String orderId,String amtType);
	
	/**
	 * 根据支付流水号，确认线下支付
	 * @Author: ldp
	 * @Date: 2015年5月19日
	 * @param flowId
	 * @return
	 */
	public int payConfirm(String flowId,String payDate,String payStatus) throws Exception;
	
	/**
	 * 根据支付流水号获取PayFlowVo
	 * @Author: ldp
	 * @Date: 2015年5月20日
	 * @return
	 * @throws Exception
	 */
	public PayFlowListVo getPayFlowVoByFlowId(String flowId)throws Exception;
	
	/**
	 * 生成支付流水前，验证交易订单状态
	 * @Author: ldp
	 * @Date: 2015年5月22日
	 * @param orderId
	 * @param amtType
	 * @return
	 */
	public MessageVo verifyOrder(String orderId,String amtType) throws Exception;
	
	/**
	 * 微信项目使用，生成线下支付流水
	 * @Author: biran
	 * @Date: 2015-10-08
	 */
	public int insertPayflowOffLine4WeiXin(PayReqDto payReqDto,String clientIp,String picPath)throws Exception;
	
}
