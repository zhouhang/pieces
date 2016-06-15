package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 个人认证审核备注
 * @author ldp
 * date 2015年1月21日
 * Verison 0.0.1
 */
public enum PersonCertifyCheckRemarkEnum {
	IDCARD_NAME_NOT_MATCH("1","身份证号码与真实姓名不匹配"),
	IDCARD_NAME_PIC_NOT_MATCH("2","真实姓名、身份证号码与上传正反照片信息不一致"),
	IDCARD_ISSUING_AUTHORITY_NOT_MATCH("3","身份证信息与签发机关不匹配"),
	IDCARD_UNTRUTHFUL("4","身份信息不明或缺乏真实性等其他问题");
	private String code;
	private String msg;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	private PersonCertifyCheckRemarkEnum(){}
	/**
	 * @param code
	 * @param msg
	 */
	private PersonCertifyCheckRemarkEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (PersonCertifyCheckRemarkEnum item : PersonCertifyCheckRemarkEnum.values()) {
			map.put(item.getCode(), item.getMsg());
		}
		return map;
	}
}
