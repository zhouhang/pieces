package com.pieces.service.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.pieces.tools.utils.httpclient.HttpClientUtil;
import com.pieces.tools.utils.httpclient.common.HttpConfig;
import com.pieces.tools.utils.httpclient.exception.HttpProcessException;

public class YPSendMessage extends SendMessage {

	private static String ENCODING = "UTF-8";
	private static String SMS_URL = "https://sms.yunpian.com/v2/sms/single_send.json";
	private static String SMS_USERID = "49a8a3011d2eb2734a5e88938bea8c1e";

	@Override
	public String send(String mobileNo, String code) {
		String context = getContext(code);
		String res = "";
		try {
			res = sendSms(SMS_USERID, context, mobileNo);
			System.out.println("--------------------" + res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return context;
	}

	/**
	 * 智能匹配模板接口发短信
	 *
	 * @param apikey
	 *            apikey
	 * @param text
	 *            短信内容
	 * @param mobile
	 *            接受的手机号
	 * @return json格式字符串
	 * @throws IOException
	 * @throws HttpProcessException
	 */

	public static String sendSms(String apikey, String text, String mobile) throws HttpProcessException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("apikey", apikey);
		params.put("text", text);
		params.put("mobile", mobile);
		// return post(SMS_URL, params);
		return HttpClientUtil.post(HttpConfig.custom().url(SMS_URL).map(params));
	}

	/**
	 * 基于HttpClient 4.3的通用POST方法
	 *
	 * @param url
	 *            提交的URL
	 * @param paramsMap
	 *            提交<参数，值>Map
	 * @return 提交响应
	 */

	public static String post(String url, Map<String, String> paramsMap) {
		CloseableHttpClient client = HttpClients.createDefault();
		String responseText = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost method = new HttpPost(url);
			if (paramsMap != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> param : paramsMap.entrySet()) {
					NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
					paramList.add(pair);
				}
				method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
			}
			response = client.execute(method);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseText = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return responseText;
	}

}
