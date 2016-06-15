package com.jointown.zy.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.jointown.zy.common.constant.WxConstant;
import com.jointown.zy.common.vo.WxAccessTokenVo;
import com.jointown.zy.common.vo.WxArticleVo;
import com.jointown.zy.common.vo.WxJsapiTicketVo;
import com.jointown.zy.common.vo.WxReqBaseMessageVo;
import com.jointown.zy.common.vo.WxRespBaseMessageVo;
import com.jointown.zy.common.vo.WxRespNewsMessageVo;
import com.jointown.zy.common.vo.WxRespTextMessageVo;

/**
 * 微信公众平台开发--工具类
 * 
 * @author aizhengdong
 *
 * @data 2015年2月5日
 */
public class WxUtils {

	/** access token */
	private static WxAccessTokenVo accessToken;
	
	/** jsapi_ticket */
	private static WxJsapiTicketVo jsapiTicket;

	private static final Logger LOG = LoggerFactory.getLogger(WxUtils.class);

	/**
	 * 验证签名
	 * <p>
	 * 流程如下：<br>
	 * 1. 将token、timestamp、nonce三个参数进行字典序排序<br>
	 * 2. 将三个参数字符串拼接成一个字符串进行sha1加密<br>
	 * 3. 将加密后的字符串与signature对比
	 * </p>
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp,
			String nonce) {
		if (signature == null || timestamp == null || nonce == null) {
			return false;
		}

		// 将token、timestamp、nonce三个参数进行字典序排序
		String[] arr = new String[] { WxConstant.TOKEN, timestamp, nonce };
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}

		MessageDigest md = null;
		String tmpStr = null;

		try {
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// 将sha1加密后的字符串与signature对比
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 生成JS-SDK使用权限签名
	 *
	 * @param jsapiTicket jsapi_ticket
	 * @param nonceStr 生成签名的随机串
	 * @param timestamp 生成签名的时间戳
	 * @param url 
	 * @return
	 *
	 * @author aizhengdong
	 * @date 2015年10月12日
	 */
	private static String createWxJsSignature(String jsapiTicket, String nonceStr, String timestamp, String url){
	    String tmpStr = "jsapi_ticket=" + jsapiTicket + 
	    		   "&noncestr=" + nonceStr +  
	    		   "&timestamp=" + timestamp +  
	    		   "&url=" + url;  
	    
	    LOG.info("[WxUtils][createWxJsSignature] start create JS-SDK signature, ready parameter: " + tmpStr);
	    
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(tmpStr.getBytes("UTF-8"));
			String signature = byteToHex(crypt.digest());
			
			LOG.info("[WxUtils][createWxJsSignature] create JS-SDK signature success");
			
			return signature;
		}catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
	    		
		LOG.info("[WxUtils][createWxJsSignature] create JS-SDK signature fail");
		
		return null;
	}
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();

		BufferedReader bufferedReader = null;
		OutputStream outputStream = null;
		HttpsURLConnection httpUrlConn = null;
		try {
			URL url = new URL(requestUrl);
			httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}

			// 当有数据需要提交时
			if (outputStr != null) {
				outputStream = httpUrlConn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
			}

			if (httpUrlConn.getResponseCode() == 200) {
				// 将返回的输入流转换成字符串
				bufferedReader = new BufferedReader(new InputStreamReader(
						httpUrlConn.getInputStream(), "UTF-8"));

				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					buffer.append(str);
				}

				String result = buffer.toString();
				LOG.info("[WxUtils][httpRequest]msg=result: " + result);

				jsonObject = JSONObject.parseObject(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}

				if (outputStream != null) {
					outputStream.close();
				}

				if (httpUrlConn != null) {
					httpUrlConn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsonObject;
	}

	/**
	 * 下载文件
	 *
	 * @param url
	 * @param filePath
	 * @return
	 *
	 */
	public static boolean downloadFile(String url, String filePath) {
		InputStream is = null;
		FileOutputStream fos = null;
		HttpURLConnection httpURLConnection = null;
		try {
			URL realUrl = new URL(url);
			httpURLConnection = (HttpURLConnection) realUrl.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setConnectTimeout(30 * 1000);
			httpURLConnection.setReadTimeout(30 * 1000);
			httpURLConnection.connect();

			is = httpURLConnection.getInputStream();
			if (is != null) {
				File file = new File(filePath);

				// 写入到文件
				fos = new FileOutputStream(file);
				if (fos != null) {
					int c = is.read();
					while (c != -1) {
						fos.write(c);
						c = is.read();
					}

					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}

				if (fos != null) {
					fos.close();
				}

				if (httpURLConnection != null) {
					httpURLConnection.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return false;

	}

	/**
	 * 获取access_token值
	 * 
	 * @return
	 */
	public static String getAccessToken() {
		LOG.info("[WxUtils][getAccessToken] start get access_token");
		
		String token = null;
		
		// 检查本地保存的access_token值是否可用
		if(isExpiresInAccessToken()){
			token = accessToken.getToken();
			
			LOG.info("[WxUtils][getAccessToken] get access_token success: " + token);
			
			return token;
		}
		
		LOG.info("[WxUtils][getAccessToken] get access_token fail, start create");
		
		// 请求网络获取access_token
		String requestUrl = WxConstant.ACCESS_TOKEN_URL.replace("APPID", WxConstant.APPID).replace("APPSECRET", WxConstant.APPSECRET);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		if (jsonObject != null) {
			try {
				if(jsonObject.containsKey("access_token")){
					token = jsonObject.getString("access_token");
					int expiresIn = jsonObject.getInteger("expires_in");
					
					// 获取成功
					accessToken = new WxAccessTokenVo();
					accessToken.setToken(token);
					accessToken.setExpiresIn(expiresIn);
					accessToken.setCreateTime(getCurrentTime());
					
					LOG.info("[WxUtils][getAccessToken] create access_token success: " + token);
					
					return token;
				}else if(jsonObject.containsKey("errcode")){
					// 打印错误信息
					int errcode = jsonObject.getIntValue("errcode");
					String errmsg = jsonObject.getString("errmsg");
					
					LOG.info("[WxUtils][getAccessToken] create access_token error: [errcode]: " + errcode + " [errmsg]: " + errmsg);
				}
			} catch (Exception e) {
				e.printStackTrace();
				accessToken = null;
			}
		}

		LOG.error("[WxUtils][getAccessToken] create access_token fail");
		
		return null;
	}

	/**
	 * 获取调用微信JS接口的临时票据jsapi_ticket
	 *
	 * @return
	 *
	 * @author aizhengdong
	 * @date 2015年10月12日
	 */
	public static String getJsapiTicket(){
		String ticket = null;
		
		LOG.info("[WxUtils][getJsapiTicket] start get jsapi_ticket");
		
		// 检查本地保存的jsapi_ticket值是否可用
		if(isExpiresInJsapiTicket()){
			ticket = jsapiTicket.getTicket();
			
			LOG.info("[WxUtils][getJsapiTicket] get jsapi_ticket success: " + ticket);
			
			return ticket;
		}
		
		LOG.info("[WxUtils][getJsapiTicket] get jsapi_ticket fail, start create");
		
		// 请求网络获取jsapi_ticket
		if (WxUtils.getAccessToken() != null) {
			String url = WxConstant.JSAPI_TICKET_URL.replace("ACCESS_TOKEN", accessToken.getToken());
			JSONObject jsonObject = httpRequest(url, "GET", null);
			if (jsonObject != null && jsonObject.containsKey("errcode")) {
				try {
					Integer errcode = jsonObject.getInteger("errcode");
					if(errcode != null && errcode == 0){
						ticket = jsonObject.getString("ticket");
						int expiresIn = jsonObject.getInteger("expires_in");
						
						// 获取成功
						jsapiTicket = new WxJsapiTicketVo();
						jsapiTicket.setTicket(ticket);
						jsapiTicket.setExpiresIn(expiresIn);
						jsapiTicket.setCreateTime(getCurrentTime());
						
						LOG.info("[WxUtils][getJsapiTicket] create jsapi_ticket success: " + ticket);
						
						return ticket;
					}else{
						// 打印错误信息
						String errmsg = jsonObject.getString("errmsg");
						
						LOG.info("[WxUtils][getJsapiTicket] create jsapi_ticket error: [errcode]: " + errcode + " [errmsg]: " + errmsg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					jsapiTicket = null;
				}
			}
		}
		
		LOG.error("[WxUtils][getJsapiTicket] create jsapi_ticket fail");
		
		return null;
	}
	
	/**
	 * 解析微信发送的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		InputStreamReader inputStreamReader = null;
		try {
			inputStreamReader = new InputStreamReader(request.getInputStream(),
					"utf-8");
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStreamReader);
			Element root = document.getRootElement();
			List<Element> elementList = root.elements();

			for (Element element : elementList) {
				map.put(element.getName(), element.getText());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return map;
	}

	/**
	 * 创建文本消息
	 * 
	 * @param content
	 * @param reqMessage
	 * @return
	 */
	public static String createTextMessage(String content,
			WxReqBaseMessageVo reqMessage) {
		WxRespTextMessageVo textMessage = new WxRespTextMessageVo();
		textMessage.setToUserName(reqMessage.getFromUserName());
		textMessage.setFromUserName(reqMessage.getToUserName());
		textMessage.setCreateTime(getCurrentTime());
		textMessage.setMsgType(WxConstant.TEXT_RESP_MESSAGE_TYPE);
		textMessage.setContent(content);

		return WxUtils.textMessageToXml(textMessage);
	}

	/**
	 * 创建图文消息
	 * 
	 * @param articles
	 * @param reqMessage
	 * @return
	 */
	public static String createNewsMessage(List<WxArticleVo> articles,
			WxReqBaseMessageVo reqMessage) {
		WxRespNewsMessageVo newsMessage = new WxRespNewsMessageVo();
		newsMessage.setToUserName(reqMessage.getFromUserName());
		newsMessage.setFromUserName(reqMessage.getToUserName());
		newsMessage.setCreateTime(getCurrentTime());
		newsMessage.setMsgType(WxConstant.NEWS_RESP_MESSAGE_TYPE);
		newsMessage.setArticleCount(articles.size());
		newsMessage.setArticles(articles);

		return WxUtils.newsMessageToXml(newsMessage);
	}

	/**
	 * 创建客服消息
	 *
	 * @param reqMessage
	 * @return
	 *
	 * @author aizhengdong
	 * @date 2015年9月21日
	 */
	public static String createCustomerServiceMessage(WxReqBaseMessageVo reqMessage) {
		WxRespBaseMessageVo customerServiceMessage = new WxRespTextMessageVo();
		customerServiceMessage.setToUserName(reqMessage.getFromUserName());
		customerServiceMessage.setFromUserName(reqMessage.getToUserName());
		customerServiceMessage.setCreateTime(getCurrentTime());
		customerServiceMessage.setMsgType(WxConstant.CUSTOMER_SERVICE_RESP_MESSAGE_TYPE);

		return WxUtils.customerServiceMessageToXml(customerServiceMessage);
	}
	
	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessage
	 *            文本消息对象
	 * @return xml格式的String
	 */
	private static String textMessageToXml(WxRespTextMessageVo textMessage) {
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("xml");

		Element ToUserNameElement = rootElement.addElement("ToUserName");
		ToUserNameElement.addCDATA(textMessage.getToUserName());

		Element FromUserNameElement = rootElement.addElement("FromUserName");
		FromUserNameElement.addCDATA(textMessage.getFromUserName());

		Element CreateTimeElement = rootElement.addElement("CreateTime");
		CreateTimeElement.setText(Long.toString(textMessage.getCreateTime()));

		Element MsgTypeElement = rootElement.addElement("MsgType");
		MsgTypeElement.addCDATA(textMessage.getMsgType());

		Element ContentElement = rootElement.addElement("Content");
		ContentElement.addCDATA(WxUtils.filterText(textMessage.getContent()));

		return writeXml(document);
	}

	/**
	 * 图文消息对象转换成xml
	 * 
	 * @param newsMessage
	 *            图文消息对象
	 * @return xml格式的String
	 */
	private static String newsMessageToXml(WxRespNewsMessageVo newsMessage) {
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("xml");

		Element ToUserNameElement = rootElement.addElement("ToUserName");
		ToUserNameElement.addCDATA(newsMessage.getToUserName());

		Element FromUserNameElement = rootElement.addElement("FromUserName");
		FromUserNameElement.addCDATA(newsMessage.getFromUserName());

		Element CreateTimeElement = rootElement.addElement("CreateTime");
		CreateTimeElement.setText(Long.toString(newsMessage.getCreateTime()));

		Element MsgTypeElement = rootElement.addElement("MsgType");
		MsgTypeElement.addCDATA(newsMessage.getMsgType());

		Element ArticleCountElement = rootElement.addElement("ArticleCount");
		ArticleCountElement.setText(Integer.toString(newsMessage
				.getArticleCount()));

		Element ArticlesElement = rootElement.addElement("Articles");
		List<WxArticleVo> articles = newsMessage.getArticles();
		for (int i = 0; i < articles.size(); i++) {
			WxArticleVo article = articles.get(i);

			Element itemElement = ArticlesElement.addElement("item");

			Element TitleElement = itemElement.addElement("Title");
			TitleElement.addCDATA(WxUtils.filterText(article.getTitle()));

			Element DescriptionElement = itemElement.addElement("Description");
			DescriptionElement.addCDATA(WxUtils.filterText(article
					.getDescription()));

			Element PicUrlElement = itemElement.addElement("PicUrl");
			if (i == 0) {
				// 第一条消息中，没有图片时，设置一张默认图片
				if (article.getPicUrl() == null || article.getPicUrl() == "") {
					PicUrlElement.addCDATA(WxConstant.DEFAULT_PIC_URL);
				} else {
					PicUrlElement.addCDATA(article.getPicUrl());
				}
			} else {
				PicUrlElement.addCDATA(WxUtils.filterText(article.getPicUrl()));
			}

			Element UrlElement = itemElement.addElement("Url");
			UrlElement.addCDATA(article.getUrl());
		}

		return writeXml(document);
	}


	/**
	 * 客服消息对象转换成xml
	 * 
	 * @return xml格式的String
	 * 
	 * @author aizhengdong
	 * @date 2015年9月21日
	 */
	private static String customerServiceMessageToXml(WxRespBaseMessageVo customerServiceMessage) {
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("xml");

		Element ToUserNameElement = rootElement.addElement("ToUserName");
		ToUserNameElement.addCDATA(customerServiceMessage.getToUserName());

		Element FromUserNameElement = rootElement.addElement("FromUserName");
		FromUserNameElement.addCDATA(customerServiceMessage.getFromUserName());

		Element CreateTimeElement = rootElement.addElement("CreateTime");
		CreateTimeElement.setText(Long.toString(customerServiceMessage.getCreateTime()));

		Element MsgTypeElement = rootElement.addElement("MsgType");
		MsgTypeElement.addCDATA(customerServiceMessage.getMsgType());

		return writeXml(document);
	}
	
	/**
	 * 封装一条图文消息
	 *
	 * @param title
	 * @param description
	 * @param url
	 * @param picUrl
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年4月24日
	 */
	public static WxArticleVo createWxArticle(String title, String description,
			String url, String picUrl) {
		WxArticleVo article = new WxArticleVo();
		article.setTitle(title);
		article.setDescription(description);
		article.setUrl(url);
		article.setPicUrl(picUrl);
		return article;
	}

	/**
	 * 生成JS-SDK权限验证的签名的相关方法
	 *
	 * @author aizhengdong
	 * @date 2015年10月13日
	 */
	private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
	
	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}
	
	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}

	/**
	 * 检查上次获取的jsapi_ticket是否在有效期内
	 * <p>
	 * 设置600秒作为误差时间，保证有效性
	 * </p>
	 * 
	 * @return true 在有效期内 <br>
	 *         false 已超过有效期
	 *         
	 * @author aizhengdong
	 * @date 2015年10月12日
	 */
	private static boolean isExpiresInJsapiTicket() {
		if (jsapiTicket == null) {
			return false;
		}

		long pastTime = getCurrentTime() - jsapiTicket.getCreateTime();
		long expiresIn = jsapiTicket.getExpiresIn();

		return (expiresIn - 600) > pastTime;
	}
	
	/**
	 * 检查上次获取的access token是否在有效期内
	 * <p>
	 * 设置600秒作为误差时间，保证access token的有效性
	 * </p>
	 * 
	 * @return true 在有效期内 <br>
	 *         false 已超过有效期
	 */
	private static boolean isExpiresInAccessToken() {
		if (accessToken == null) {
			return false;
		}

		long pastTime = getCurrentTime() - accessToken.getCreateTime();
		long expiresIn = accessToken.getExpiresIn();

		return (expiresIn - 600) > pastTime;
	}

	/**
	 * 获取当前时间（秒）
	 * 
	 * @return
	 */
	private static long getCurrentTime() {
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 生成xml格式String
	 * 
	 * @param document
	 * @return
	 */
	private static String writeXml(Document document) {
		OutputFormat format = OutputFormat.createCompactFormat();
		StringWriter writer = new StringWriter();
		XMLWriter output = new XMLWriter(writer, format);
		try {
			output.write(document);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				writer.close();
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return writer.toString();
	}

	/**
	 * 过滤字符串，用于null值处理
	 * 
	 * @param text
	 * @return
	 */
	private static String filterText(String text) {
		return text == null ? "" : text;
	}
	
	/**
	 * 检测是否为业务员编号
	 *
	 * @param number
	 * @return
	 *
	 * @author aizhengdong
	 * @date 2015年10月8日
	 */
	public static boolean isBusinessNum(String number){
		try {
			int num = Integer.parseInt(number);
			if(1000 <= num && num <= 9999){
				return true;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 创建JS-SDK所需的config
	 *
	 * @param url 当前网页的URL，不包含#及其后面部分
	 * @return
	 *
	 * @author aizhengdong
	 * @date 2015年10月12日
	 */
	public static Map<String, Object> createWxJsConfig(String url){
		LOG.info("[WxUtils][createWxJsConfig] start create WxJsConfig");
		
		String ticket = WxUtils.getJsapiTicket();
		if(ticket == null){
			LOG.info("[WxUtils][createWxJsConfig] create WxJsConfig fail");
			return null;
		}
		
		String appId = WxConstant.APPID;
		String nonceStr = UUID.randomUUID().toString();
		String timestamp = Long.toString(getCurrentTime());
		String signature = WxUtils.createWxJsSignature(ticket, nonceStr, timestamp, url);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appId", appId);
		map.put("timestamp", timestamp);
		map.put("nonceStr", nonceStr);
		map.put("signature", signature);
		
		LOG.info("[WxUtils][createWxJsConfig] create WxJsConfig success: "
				+ " [appId]: " + appId
				+ " [timestamp]: " + timestamp
				+ " [nonceStr]: " + nonceStr
				+ " [signature]: " + signature);
		
		return map;
	}
	
}
