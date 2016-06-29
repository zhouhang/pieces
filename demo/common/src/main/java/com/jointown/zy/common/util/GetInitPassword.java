package com.jointown.zy.common.util;

/**
 * 随机生成初始密码 
 * author ldp
 * 2014年11月27日
 */
public class GetInitPassword {
	
	/** 生成密码长度 */
	private static final int PWD_INIT_LENGTH = 6;
	/** 生成密码的元素 */
	private static final String PWD_ELEMENT = "0123456789abcdefghijklmnopqrstuvwxyz";
	/** 生成短信验证码的元素 */
	private static final String MOBILE_ELEMENT ="0123456789";
	private static final int MOBILE_INIT_LENGTH = 6;
	
	/**
	 * 随机生成密码
	 * @return
	 */
	public static String getPwd(int pwdLength){
		return getRandomCode(PWD_ELEMENT, pwdLength);
	}
	
	/**
	 * 随机生成6位长度的密码（数字、字母）
	 * @return
	 */
	public static String get6BitPwd(){
		return getPwd(PWD_INIT_LENGTH);
	}
	
	/**
	 * 随机生成6位短信验证码（纯数字）
	 * @return
	 */
	public static String getRand6BitCode(){
		return getRandomCode(MOBILE_ELEMENT, MOBILE_INIT_LENGTH);
	}
	
	/**
	 * 生成一定长度的随机码
	 * @param element
	 *             基础元素
	 * @param randomCodeLength
	 * 			        生成随机码的长度
	 * @return
	 */
	public static String getRandomCode(String element,int randomCodeLength){
		char[] pwdElement = element.toCharArray();
		StringBuffer pwd = new StringBuffer();
		for(int i=0;i<randomCodeLength;i++){
			pwd.append(pwdElement[(int) (Math.random()*element.length())]);
		}
		return pwd.toString();
	}
}
