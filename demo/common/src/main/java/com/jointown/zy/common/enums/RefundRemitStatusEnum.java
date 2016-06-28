package com.jointown.zy.common.enums;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jointown.zy.common.constant.Constant;

/**
 * @ClassName: RemitStatusEnum
 * @Description: 退款划账流水状态枚举
 * @Author: ldp
 * @Date: 2015年7月1日
 * @Version: 1.0
 */
public enum RefundRemitStatusEnum {
	UN_REMIT(0,"未退款"),
	REMIT_SUCCESS(1,"退款成功"),
	REMIT_FAILED(2,"退款失败"),
	REMIT_REFUSE(3,"拒绝退款"),
	REMIT_EXCEPTION(4,"退款异常");
	
	public int status;
	public String title;
	
	private RefundRemitStatusEnum(int status, String title) {
		this.status = status;
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public static Map<String,String> toMap(){
		Map<String,String> map = new HashMap<String, String>(); 
		for(RefundRemitStatusEnum status:RefundRemitStatusEnum.values()){
			map.put(String.valueOf(status.getStatus()), status.getTitle());
		}
		return map;
	}
	
	/**
	 * 区分货款分润管理 / 退款管理 列表显示数据 类型
	 * @ClassName:RemitType
	 * @author:Calvin.Wangh
	 * @date:2015-7-7下午4:00:03
	 * @version V1.0
	 * @Description:
	 */
	public static class RemitType{
		
		public static final String REMIT_TYPE = "1001"; 
		
		public static final String REFUND_TYPE = "1002";
	}
	
	//退款类型
	public static class RefundStatus {
		//订单完成划账
		public static final int FINISH_REMIT= 1;
		//订单过期划账
		public static final int OVERDUE_REMIT = 2;
		//订单申退划账
		public static final int APPLY_FOR_REMIT = 3;
		
		public static final String getName(int iCode) {
			String strReturn = "";
			switch ((int) iCode) {
			case (int) FINISH_REMIT:
				strReturn = "完成划账";
				break;
			case (int) OVERDUE_REMIT:
				strReturn = "过期退款";
				break;
			case (int) APPLY_FOR_REMIT:
				strReturn = "申请退款";
				break;
			}
			return strReturn;
		}
		
		public static final List showList(String constantName,String refundStatus, String resultList){
			return Constant.getConstantResult.getList(constantName,refundStatus, resultList);
		}
	}

}
