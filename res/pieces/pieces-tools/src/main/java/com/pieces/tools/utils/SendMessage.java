package com.pieces.tools.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
/**
 * @ClassName: SendMessage
 * @Description: 用于发送短信验证码
 */
public class SendMessage {
	private static final String SMS_URL = "111";
	private static final String SMS_USERID ="111";
	private static final String SMS_ACCOUNT ="111";
	private static final String SMS_PWD ="111";
	//号码是否匹配，正则表达式
	private static final String REGX = "^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$|177[0-9]{8}$";
	
	public static String sendMessage(String mobileNo,String contextMsg){
		if (StringUtils.isBlank(mobileNo)) {
			return "fail";
		}
		//判断手机号格式是否正确
		Pattern pattern = Pattern.compile(REGX);
        Matcher matcher = pattern.matcher(mobileNo);
        if (!matcher.matches()) {
			return "fail";
		}
        
        String resp = null;
        //短信发送
		//resp = SmsClientSend.sendSms(LXZK_URL, LXZK_USERID, LXZK_ACCOUNT, LXZK_PWD, mobileNo, contextMsg);
		if (null == resp || "".equals(resp)) {
			return "fail";
		}
		String returnStatus = null;
		String flagStart = "<returnstatus>";
		String flagEnd = "</returnstatus>";
		if (resp.indexOf(flagStart) != -1) {
			returnStatus = resp.substring(resp.indexOf(flagStart) + flagStart.length(),resp.indexOf(flagEnd));
			if ("success".equals(returnStatus.toLowerCase())) {
				return "success";
			}
		}
		return "fail";
	}
	
}
