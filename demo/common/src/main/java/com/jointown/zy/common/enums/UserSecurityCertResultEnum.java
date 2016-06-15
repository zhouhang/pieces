package com.jointown.zy.common.enums;

/**
 * @ClassName: UserSecurityCertResultEnum
 * @Description: 会员安全信息修改前，身份验证结果枚举
 * @Author: ldp
 * @Date: 2015年8月25日
 * @Version: 1.0
 */
public enum UserSecurityCertResultEnum {

	/**身份验证未通过*/
	SECURITY_CERT_UNPASS(0,"身份验证未通过"),
	/**修改手机号码,身份验证通过*/
	SECURITY_CERT_MOBILE_PASS(1,"修改手机号码,身份验证通过"),
	/**邮箱设置、邮箱修改，身份认证通过*/
	SECURITY_CERT_EMAIL_PASS(2,"身份认证通过");
	private int certCode;
	private String certTile;
	public int getCertCode() {
		return certCode;
	}
	public void setCertCode(int certCode) {
		this.certCode = certCode;
	}
	public String getCertTile() {
		return certTile;
	}
	public void setCertTile(String certTile) {
		this.certTile = certTile;
	}
	private UserSecurityCertResultEnum(){}
	
	private UserSecurityCertResultEnum(int certCode, String certTile) {
		this.certCode = certCode;
		this.certTile = certTile;
	}
	
}
