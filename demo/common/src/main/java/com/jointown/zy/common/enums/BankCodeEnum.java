package com.jointown.zy.common.enums;

/**
 * 银行机构码 对应银行名称
 * 
 * @ClassName:BankCodeEnum
 * @author:Calvin.Wangh
 * @Description:
 * @date:2015-5-15下午2:11:48
 * @version V1.0
 */
public enum BankCodeEnum {
	ICBC("ICBC", "中国工商银行");

	private String bankCode;
	private String bankCodeDesc;
	
	
	private BankCodeEnum(){}
	private BankCodeEnum(String bankCode, String bankCodeDesc) {
		this.bankCode = bankCode;
		this.bankCodeDesc = bankCodeDesc;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankCodeDesc() {
		return bankCodeDesc;
	}

	public void setBankCodeDesc(String bankCodeDesc) {
		this.bankCodeDesc = bankCodeDesc;
	}

}
