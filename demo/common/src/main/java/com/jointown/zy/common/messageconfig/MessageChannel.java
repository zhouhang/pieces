package com.jointown.zy.common.messageconfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: MessageChannel
 * @Description: 短信通道抽象类
 * @Author: ldp
 * @Date: 2015年9月10日
 * @Version: 1.0
 */

public abstract class MessageChannel {
	
	/**
	 * 匹配手机号码，正则表达式
	 */
	public static final String REGX = "^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$|177[0-9]{8}$";

	/**
	 * @Description: 短信发送方法
	 * @Author: ldp
	 * @Date: 2015年9月10日
	 * @param mobileNo
	 * @param msgContext
	 * @return
	 */
	public abstract String sendMessage(String mobileNo,String msgContext);
	
	/**
	 * @Description: 发短信前验证手机号码是否正确
	 * @Author: ldp
	 * @Date: 2015年10月27日
	 * @param mobileNo
	 * @param regx
	 * @return
	 */
	public boolean validateMobile(String mobileNo){
		Pattern pattern = Pattern.compile(REGX);
        Matcher matcher = pattern.matcher(mobileNo);
        if (!matcher.matches()) {
			return false;
		}
        return true;
	}
	
}
