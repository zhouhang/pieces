package com.jointown.zy.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;
import com.jointown.zy.common.constant.ConfigConstant;

/**
 * 短信发送 author ldp 2014-11-24
 */
public class MessageSend {
	
	private static Logger log = LoggerFactory.getLogger(MessageSend.class);
	
	private static final String MESSAGE_URL = ConfigConstant.MOB_MSG_URL;
	private static final String NAME = ConfigConstant.USER_NAME;
	private static final String PASSWORD = ConfigConstant.PASSWORD;
	private static final String REGX = "^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$|177[0-9]{8}$";
	/**
	 * 短信发送
	 * @param mobileNos
	 *           手机号码，多个手机号用逗号隔开
	 * @param contentMsg 
	 * 			   短信内容，最多字符不超过500个字符，70个字符内按一条短信算，长短信67个字符算一条
	 * @return 返回参数 发送成功返回success，发送失败返回fail
	 */
	public static String sendMessage(String mobileNos,String contentMsg){
		/*** 手机号码非空判断 add by Mr.song 2015.5.26*/
		if(StringUtils.isEmpty(mobileNos)){
			return "fail";
		}
		log.info("send message mobileNos is:" + mobileNos);
		log.info("send message is:" + contentMsg);
		String respFlag = null;
		Pattern pattern = Pattern.compile(REGX);
        Matcher matcher = pattern.matcher(mobileNos);
        if (!matcher.matches()) {
			log.info("mobileNo is illegal !");
			return "fail";
		}
        
		respFlag = sendSMS(getMessage(mobileNos, contentMsg));
		log.info("send message response info:" + respFlag);
		
		/***********非空判断************/
		if(respFlag==null || "".equals(respFlag)) return "fail";
		
		if (respFlag.startsWith("success")) {
			return "success";
		}
		return "fail";	
	}
	
	/**
	 * 组织短信数据(短信参数包括name，key,dest,content)
	 * @param mobileNos
	 * @param contentMsg
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getMessage(String mobileNos,String contentMsg){
		
		String seed = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		//MD5签名
		String key = MD5((MD5(PASSWORD) + seed));
		String dest = null;
		String content = null;
		try {
			dest = URLEncoder.encode(mobileNos, "GB2312");
			content = URLEncoder.encode(contentMsg,"GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new StringBuilder().append(MESSAGE_URL)
				.append("?name=").append(URLEncoder.encode(NAME))
				.append("&seed=").append(URLEncoder.encode(seed))
				.append("&key=").append(URLEncoder.encode(String.valueOf(key)))
				.append("&dest=").append(URLEncoder.encode(dest))
				.append("&content=").append(content).toString();
		
	}
	
	/**
	 * MD5 signature
	 * @param context
	 * @return
	 */
	public static String MD5(String context) {
		return EncryptUtil.getMD5(context, "GBK").toLowerCase();
	}

	/**
	 * send method (http get)
	 * @param sendURL
	 * @return
	 * @throws IOException
	 */
	public static String sendSMS(String sendURL){
		/**
		 * update by Mr.song 2015/5/26 17:19 start
		 */
		String flag =null;
		/**********************非空判断************************/
		if(StringUtils.isEmpty(sendURL)){
			return flag;
		}
		/**********************非空判断************************/
		BufferedReader rd  = null;
		HttpURLConnection connection = null;
		StringBuffer respInfo = null;
		try{
			//拼装URL对象
			URL getUrl = new URL(sendURL);  
			// 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection  
			connection = (HttpURLConnection) getUrl.openConnection();  
			connection.setConnectTimeout(30000); //设置连接超过30s
			connection.setReadTimeout(30000);    //设置读操作超过30s
			connection.setRequestMethod("GET");  //默认就是get,也可以不设置
			//连接服务器  
			connection.connect();  
			log.info("Contents of get request start");  
			// 取得输入流，并使用Reader读取  
			rd = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
			
			respInfo = new StringBuffer();
			int ch;
			while ((ch = rd.read()) > -1) {
				respInfo.append((char) ch);
			}
			log.info("Contents of get request ends");  
			flag = respInfo.toString();
		}catch(Exception e){
			log.error("url request fail.");
			flag="fail";
		}finally{
			//关闭缓冲流
			try {
				if(rd!=null) rd.close();
			} catch (IOException e) {
				log.error("sendSMS--->BufferedReader closed fail.");
			}  
			// 断开连接  
			if(connection!=null) connection.disconnect(); 
		}
		return flag;  
		/**
		 * update by Mr.song 2015/5/26 17:19 end
		 */
		
		
//		StringBuffer respInfo = null;
//		URL url = null;
//		HttpURLConnection urlConn = null;
//		try {
//			url = new URL(sendURL);
//			urlConn = (HttpURLConnection) url.openConnection();
//			urlConn.setRequestMethod("GET");
//			urlConn.setDoOutput(true);
//			log.info("url of send sms is " + sendURL);
//			OutputStream out = urlConn.getOutputStream();
//			log.info("send sms is success" + sendURL);
//			out.flush();
//			out.close();
//			
//			BufferedReader rd = new BufferedReader(new InputStreamReader(
//					urlConn.getInputStream(), "GBK"));
//	
//			respInfo = new StringBuffer();
//			int ch;
//			while ((ch = rd.read()) > -1) {
//				respInfo.append((char) ch);
//			}
//			rd.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return respInfo.toString();
	}
}
