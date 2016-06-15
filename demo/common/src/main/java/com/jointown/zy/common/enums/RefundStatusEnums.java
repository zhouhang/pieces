package com.jointown.zy.common.enums;

/**
 * 退款状态枚举
 * @author ldp
 * date 2015.2.10
 */
public enum RefundStatusEnums {
	REFUND_FAILED(0,"退款失败"),
	REFUND_SUCCESS(1,"退款成功"),
	REFUND_PROCESSING(2,"退款处理中");
	
	private int refundStatus;
	private String refundStstusDesc;
	public int getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(int refundStatus) {
		this.refundStatus = refundStatus;
	}
	public String getRefundStstusDesc() {
		return refundStstusDesc;
	}
	public void setRefundStstusDesc(String refundStstusDesc) {
		this.refundStstusDesc = refundStstusDesc;
	}
	private RefundStatusEnums(int refundStatus, String refundStstusDesc) {
		this.refundStatus = refundStatus;
		this.refundStstusDesc = refundStstusDesc;
	}
	
}
