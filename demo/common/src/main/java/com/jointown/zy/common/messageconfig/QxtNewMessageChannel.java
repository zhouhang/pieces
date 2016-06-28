package com.jointown.zy.common.messageconfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;
import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.util.EncryptUtil;

/**
 * @ClassName: QxtNewMessageChannel
 * @Description: 企信通新短信接口通道
 * @Author: ldp
 * @Date: 2015年10月27日
 * @Version: 1.0
 */
public class QxtNewMessageChannel extends MessageChannel {
	
	private static final Logger log = LoggerFactory.getLogger(QxtNewMessageChannel.class);
	
	private static final String URL = ConfigConstant.NEW_QXT_URL;
	private static final String USERID = ConfigConstant.NEW_QXT_USERID;
	private static final String PASSWORD = ConfigConstant.NEW_QXT_PASSWORD;
	private static final String FAILED = "fail";
	
	@Override
	public String sendMessage(String mobileNo, String msgContext) {
		log.info("QxtNewMessageChannel.sendMessage mobileNo:" + mobileNo);
		log.info("QxtNewMessageChannel.sendMessage msgContext:" + msgContext);
		try {
			if (!validateMobile(mobileNo)) {
				log.info("QxtNewMessageChannel.sendMessage mobileNo is illegal");
				return FAILED;
			}
			String respInfo = httpGetRequest(spliceParams(msgContext, mobileNo));
			log.info("QxtNewMessageChannel.sendMessage respInfo:" + respInfo);
			return respInfo.split(";")[0];
		} catch (UnsupportedEncodingException e) {
			log.error("QxtNewMessageChannel.sendMessage error:", e);
			return FAILED;
		}
	}
	
	/**
	 * @Description: 短信参数拼接
	 * @Author: ldp
	 * @Date: 2015年10月27日
	 * @param content 短信内容
	 * @param mobile 手机号码
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private String spliceParams(String content,String mobile) throws UnsupportedEncodingException {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(URL).append("?").append("action=sendsms&userId=");
		stringBuilder.append(USERID).append("&md5password=");
		stringBuilder.append(md5Password(PASSWORD)).append("&mobile=");
		stringBuilder.append(mobile).append("&content=");
		stringBuilder.append(URLEncoder.encode(content,"UTF-8"));
		return stringBuilder.toString();
	}
	/**
	 * @Description: 密码MD5处理
	 * @Author: ldp
	 * @Date: 2015年10月27日
	 * @param password
	 * @return
	 */
	private String md5Password(String password){
		return EncryptUtil.getMD5(password, "UTF-8").toLowerCase();
	}
	
	/**
	 * @Description: 发送httpget请求
	 * @Author: ldp
	 * @Date: 2015年10月27日
	 * @param sendURL
	 * @return
	 */
	public String httpGetRequest(String sendURL){
		String flag =null;
		if(StringUtils.isEmpty(sendURL)){
			return flag;
		}
		BufferedReader rd  = null;
		HttpURLConnection connection = null;
		StringBuffer respInfo = null;
		try{
			URL getUrl = new URL(sendURL);//拼装URL对象 
			connection = (HttpURLConnection) getUrl.openConnection();  
			connection.setConnectTimeout(30000); //设置连接超过30s
			connection.setReadTimeout(30000);    //设置读操作超过30s
			connection.setRequestMethod("GET");  //默认就是get,也可以不设置
			connection.connect(); //连接服务器   
			log.info("Contents of get request start");  
			rd = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 取得输入流，并使用Reader读取  
			respInfo = new StringBuffer();
			int ch;
			while ((ch = rd.read()) > -1) {
				respInfo.append((char) ch);
			}
			log.info("Contents of get request ends");  
			flag = respInfo.toString();
		}catch(Exception e){
			log.error("QxtNewMessageChannel.httpGetRequest error:" + e);
			flag="fail";
		}finally{
			try {
				if(rd!=null) rd.close();//关闭缓冲流
			} catch (IOException e) {
				log.error("httpGetRequest--->BufferedReader closed fail.");
			}  
			if(connection!=null) connection.disconnect(); // 断开连接  
		}
		return flag;
	}
	
}
