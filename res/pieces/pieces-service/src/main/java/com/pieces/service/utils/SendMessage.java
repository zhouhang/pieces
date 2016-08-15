package com.pieces.service.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class SendMessage {
	private static final String SMS_PREFIX = "【上工之选】您的验证码是";
	private static final String SMS_INTERVAL_COUNT = "3";
	private static final String SMS_INTERVAL_TIME = "3600";
	
	public abstract String send(String mobileNo,String code);
	
	public String createCode(){
		return GetInitPassword.getRand6BitCode();
	}
	
	public String getContext(String code){
		return SMS_PREFIX + code;
	}
	
	public void saveSession(HttpServletRequest request,String mobileNo,long count,long time,String code){
		Map initMap = new HashMap();
		initMap.put("count", count);
		initMap.put("time", time);
		initMap.put("code", code);
		request.getSession().setAttribute(mobileNo,initMap);
	}
	
	public boolean sendMessage(HttpServletRequest request,String mobileNo){
//		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map smsInfoMap = (Map) request.getSession().getAttribute(mobileNo);
		if(smsInfoMap!=null){
			long count = (Long)smsInfoMap.get("count");
			long time = (Long)smsInfoMap.get("time");
			long nowTime = new Date().getTime();
			//验证过期时间
			if(((nowTime-time)/1000)<=Long.parseLong(SMS_INTERVAL_TIME)){
				//验证次数
				if(count<=Long.parseLong(SMS_INTERVAL_COUNT)){
					count = count + 1;
					String code = createCode();
					//发送消息
					send(mobileNo,code);
					saveSession(request,mobileNo,count,time,code);
					return true;
				}else{
					saveSession(request,mobileNo,count,time,"");
					return false;
				}
			}else if(((nowTime-time)/1000)>Long.parseLong(SMS_INTERVAL_TIME)){
				String code = createCode();
				//发送消息
				send(mobileNo,code);
				count = 1;
				time = new Date().getTime();
				saveSession(request,mobileNo,count,time,code);
				return true;
			}else{
				return false;
			}
		}else{
			String code = createCode();
			//发送消息
			send(mobileNo,code);
			long count = 1;
			long time = new Date().getTime();
			saveSession(request,mobileNo,count,time,code);
			return true;
		}
		
	}
}
