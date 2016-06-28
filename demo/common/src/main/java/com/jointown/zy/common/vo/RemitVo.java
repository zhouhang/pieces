package com.jointown.zy.common.vo;

/**
 * @ClassName: RemitVo
 * @Description: 划账管理列表VO
 * @Author: ldp
 * @Date: 2015年7月2日
 * @Version: 1.0
 */
public class RemitVo {

	/**流水号*/
	private String remitFlowId;
	/**订单编号*/
	private String orderId;
	/**分润金额*/
	private String remitAmt;
	/**买方金额*/
	private String buyerAmt;
	/**卖方金额*/
	private String sellerAmt;
	/**平台金额*/
	private String platformAmt;
	/**支付渠道*/
	private String payChannel;
	/**状态*/
	private String status;
	/**分账完成时间*/
	private String remitTime;
	/**操作人*/
	private String opraterId;
	/**操作时间*/
	private String opraterTime;
	
	public String getRemitFlowId() {
		return remitFlowId;
	}
	public void setRemitFlowId(String remitFlowId) {
		this.remitFlowId = remitFlowId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getRemitAmt() {
		return remitAmt;
	}
	public void setRemitAmt(String remitAmt) {
		this.remitAmt = remitAmt;
	}
	public String getBuyerAmt() {
		return buyerAmt;
	}
	public void setBuyerAmt(String buyerAmt) {
		this.buyerAmt = buyerAmt;
	}
	public String getSellerAmt() {
		return sellerAmt;
	}
	public void setSellerAmt(String sellerAmt) {
		this.sellerAmt = sellerAmt;
	}
	public String getPlatformAmt() {
		return platformAmt;
	}
	public void setPlatformAmt(String platformAmt) {
		this.platformAmt = platformAmt;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemitTime() {
		return remitTime;
	}
	public void setRemitTime(String remitTime) {
		this.remitTime = remitTime;
	}
	public String getOpraterId() {
		return opraterId;
	}
	public void setOpraterId(String opraterId) {
		this.opraterId = opraterId;
	}
	public String getOpraterTime() {
		return opraterTime;
	}
	public void setOpraterTime(String opraterTime) {
		this.opraterTime = opraterTime;
	}
	
	
	
	
}
