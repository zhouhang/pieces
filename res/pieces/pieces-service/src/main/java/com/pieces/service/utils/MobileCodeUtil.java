package com.pieces.service.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobileCodeUtil {
	
	private static final Logger log = LoggerFactory.getLogger(MobileCodeUtil.class);
	
	public static final String MOBILE_CODE = "mobileCodeMap";
	
	public static final String MOBILE_CODE_REG_WX = "mobileCodeRegWx";
	
	public static final String MOBILE_CODE_PHONE_WX = "mobileCodePhoneWx";
	
	public static final String MOBILE_CODE_EMAIL_WX = "mobileCodeEmailWx";
	
	private static final int MOBILE_CODE_EXPIRE = 5;//短信验证码过期时间5分钟
	/**
	 * 获取短信验证码内容,及过期时间
	 * @return
	 * 		短信验证码和过期时间put到map中
	 */
	public static Map<String, Object> getMobileCode(String mobileNo){
		Map<String, Object> codeMap = new HashMap<String, Object>();
		codeMap.put("mobileCode", GetInitPassword.getRand6BitCode());
		codeMap.put("mobileNo", mobileNo);
		
		//短信验证码过期时间
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, MOBILE_CODE_EXPIRE);
		//2分钟内防重复获取验证码时间
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 2);
		
		codeMap.put("expireDate",calendar.getTime());
		codeMap.put("reSendDate",c.getTime());
		return codeMap;
	}
	
	/**
	 * 验证验证码是否匹配（是否过期）
	 * @param codeMap
	 * @param inputMobileCode
	 * @param 
	 * @return
	 */
	public static String verityMobileCode(Map<String, Object> codeMap,
			String inputMobileCode, String inputMobile) {
		if (null == codeMap) {
			log.info("codeMap is:" + codeMap + "[codeMap is null]");
			return "手机号与验证码不匹配[codeMap is null]";
		}
		Date expireDate = (Date) codeMap.get("expireDate");
		if (expireDate.compareTo(new Date()) < 0) {// 验证码是否过期
			log.info("expireDate is:" + expireDate + "[inputMobileCode is expired]");
			return "验证码已过期";
		}
		if (!codeMap.get("mobileCode").equals(inputMobileCode)
				|| !codeMap.get("mobileNo").equals(inputMobile)) {
			log.info("inputMobileCode is :" + inputMobileCode);
			log.info("inputMobile is:" + inputMobile);
			log.info("[inputMobileCode is not match mobileCode in session]");
			return "手机号与验证码不匹配";
		} else {
			return "y";// 验证通过
		}
	}
	
	/**
	 * 只验证手机短信验证码是否过期
	 * @param codeMap
	 * @param inputMobileCode
	 * @return
	 */
	public static String verityMobileCode(Map<String, Object> codeMap,
			String inputMobileCode) {
		if (null == codeMap) {
			log.info("verityMobileCode param codeMap is null !");
			return "验证码错误,请获取验证码";
		}
		log.info("verityMobileCode codeMap is :" + codeMap);
		
		Date expireDate = (Date) codeMap.get("expireDate");
		if (expireDate.compareTo(new Date()) < 0) {// 验证码是否过期
			log.info("mobileCode expire!");
			return "验证码已过期,请重新获取";
		}
		
		log.info("codeMap mobileCode:" + codeMap.get("mobileCode"));
		log.info("inputMobileCode:" + inputMobileCode);
		//比较验证码是否一致
		if (!codeMap.get("mobileCode").equals(inputMobileCode)){
			log.info("codeMap mobileCode not equals inputMobileCode!");
			return "验证码错误,请重新输入";
		} else {
			return "success";// 验证通过
		}
	}
	
	/**
	 * 2分钟内防止重复获取验证码
	 * @param mobileCodeMap
	 * @param mobileNo
	 * @return
	 */
	public static String checkRepeatGain(Map<String, Object> mobileCodeMap,String mobileNo){
		String result ="ok";
		//获取当前时间
		Date time = new Date();
		//验证session手机号一致性
		if(mobileCodeMap!=null&&mobileCodeMap.get("mobileNo").equals(mobileNo)){
			//获取有效时间
			Date reSendDate = (Date) mobileCodeMap.get("reSendDate");
			//有效时间 是否大于当前时间
			if(reSendDate.after(time)){
				result =  "error";
			}
		}
		return result;
	}

}
