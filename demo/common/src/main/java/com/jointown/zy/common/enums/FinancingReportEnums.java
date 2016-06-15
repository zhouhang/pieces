package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 账表枚举类
 * @ClassName:FinancingReportEnums
 * @author:Calvin.Wangh
 * @date:2015-7-28下午2:12:43
 * @version V1.0
 * @Description:
 */
public enum FinancingReportEnums {
	
	PAY_DEPOSIT(10,"支付保证金"),
	FINAL_PAYMENT(11,"支付尾款"),
	PAID_IN_FULL(12,"支付全款"),
	REMIT(21,"分润"),
	COMPENSATE(22,"赔付"),
	REFUND(23,"退款"),
	PROFIT(30,"收益");
	
	private int code;
	private String codeDesc;
	
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (FinancingReportEnums fre : FinancingReportEnums.values()) {
			map.put(String.valueOf(fre.getCode()), fre.getCodeDesc());
		}
		return map;
	}
	
	public static String obtainCodeName(Integer code){
		String codeName = "";
		if(null!=code){
			for (FinancingReportEnums item : FinancingReportEnums.values()) {
				if(code==item.code){
					codeName = item.codeDesc;
				}
			}
		}
		return codeName;
	}
	
	/**
	 * 业务员类型
	 * @ClassName:salesManType
	 * @author:Calvin.Wangh
	 * @date:2015-7-30上午9:59:01
	 * @version V1.0
	 * @Description:
	 */
	public static class salesManType{
		public static final String BUYER_TYPE = "1001"; 
		public static final String SELLER_TYPE = "1002";
	}
	
	private FinancingReportEnums() {}
	private FinancingReportEnums(int code, String codeDesc) {
		this.code = code;
		this.codeDesc = codeDesc;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
}
