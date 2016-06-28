package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 企业认证审核备注
 * @author ldp
 * date 2015年1月21日
 * Verison 0.0.1
 */
public enum CompanyCertifyCheckRemarkEnum {
	LICENSE_3MONTH_EXPIRE("1","营业执照三个月内即将到期"),
	COMPANYNAME_CORPORATION_LICENSE_PIC_NOT_MATCH("2","企业名称、法人代表，营业执照信息与上传营业执照图片信息不匹配"),
	ORGCODE_PIC_NOT_MATCH("3","组织机构代码信息与组织机构代码证件图片信息不匹配"),
	COMPANY_INFO_UNTRUTHFUL("4","企业信息不明或缺乏真实性等其他问题");
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
	private CompanyCertifyCheckRemarkEnum(){}
	/**
	 * @param code
	 * @param msg
	 */
	private CompanyCertifyCheckRemarkEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (CompanyCertifyCheckRemarkEnum item : CompanyCertifyCheckRemarkEnum.values()) {
			map.put(item.getCode(), item.getMsg());
		}
		return map;
	}
}
