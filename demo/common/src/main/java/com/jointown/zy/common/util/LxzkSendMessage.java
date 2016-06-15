package com.jointown.zy.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.constant.ConfigConstant;
import com.ruanwei.interfacej.SmsClientSend;

/**
 * @ClassName: LxzkSendMessage
 * @Description: 凌讯中科短信接口，用于发送短信验证码
 * @Author: ldp
 * @Date: 2015年7月14日
 * @Version: 1.0
 */
public class LxzkSendMessage {

	private static final Logger log = LoggerFactory.getLogger(LxzkSendMessage.class);
	
	private static final String LXZK_URL = ConfigConstant.LXZK_SMS_URL;
	private static final String LXZK_USERID = ConfigConstant.LXZK_SMS_USERID;
	private static final String LXZK_ACCOUNT = ConfigConstant.LXZK_SMS_ACCOUNT;
	private static final String LXZK_PWD = ConfigConstant.LXZK_SMS_PASSWORD;
	//号码是否匹配，正则表达式
	private static final String REGX = "^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$|177[0-9]{8}$";
	
	/**
	 * @Description: 发送短信
	 * @Author: ldp
	 * @Date: 2015年7月14日
	 * @param mobileNo
	 * @param contextMsg
	 * @return
	 */
	public static String sendMessage(String mobileNo,String contextMsg){
		log.info("mobileNo is :" + mobileNo);
		log.info("contextMsg is:" + contextMsg);
		if (StringUtils.isBlank(mobileNo)) {
			log.info("mobile is null");
			return "fail";
		}
		//判断手机号格式是否正确
		Pattern pattern = Pattern.compile(REGX);
        Matcher matcher = pattern.matcher(mobileNo);
        if (!matcher.matches()) {
			log.info("mobileNo is illegal !");
			return "fail";
		}
        
        String resp = null;
        //短信发送
		resp = SmsClientSend.sendSms(LXZK_URL, LXZK_USERID, LXZK_ACCOUNT, LXZK_PWD, mobileNo, contextMsg);
		if (null == resp || "".equals(resp)) {
			log.info("resp info is null !");
			return "fail";
		}
		String returnStatus = null;
		String flagStart = "<returnstatus>";
		String flagEnd = "</returnstatus>";
		if (resp.indexOf(flagStart) != -1) {
			returnStatus = resp.substring(resp.indexOf(flagStart) + flagStart.length(),resp.indexOf(flagEnd));
			log.info("send Message return status:" + returnStatus);
			if ("success".equals(returnStatus.toLowerCase())) {
				return "success";
			}
		}
		return "fail";
	}
	
}
