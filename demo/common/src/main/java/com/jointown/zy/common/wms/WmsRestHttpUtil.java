package com.jointown.zy.common.wms;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.jointown.zy.common.constant.MessageConstant;

/**
 * 
 * @author ldp
 * date:2015-03-06
 * version 0.1
 */
public class WmsRestHttpUtil {
	
	private static List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	public WmsRestHttpUtil() {}
	
	static {
		messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
	}

	/**
	 * rest post方式
	 * @param url
	 * @param paramsMap
	 * @return
	 */
	public static String wmsRestPost(String url,String json)throws Exception {
		RestTemplate restTemplate = new RestTemplate(messageConverters);
		//add by fanyuna 增加接口的连接、读取超时时间
//		((SimpleClientHttpRequestFactory)restTemplate.getRequestFactory()).setConnectTimeout(MessageConstant.API_CONNECTION_TIMEOUT.intValue());
//		((SimpleClientHttpRequestFactory)restTemplate.getRequestFactory()).setReadTimeout(MessageConstant.API_READ_TIMEOUT.intValue());
		return restTemplate.postForObject(url, json, String.class);
	}
	
	/*
     *初始化RestTemplate，RestTemplate会默认添加HttpMessageConverter
     * 添加的StringHttpMessageConverter非UTF-8
     * 所以先要移除原有的StringHttpMessageConverter，
     * 再添加一个字符集为UTF-8的StringHttpMessageConvert
     * */
     private static void reInitMessageConverter(RestTemplate restTemplate){
         List<HttpMessageConverter<?>> converterList=restTemplate.getMessageConverters();
         HttpMessageConverter<?> converterTarget = null;
         for (HttpMessageConverter<?> item : converterList) {
             if (item.getClass() == StringHttpMessageConverter.class) {
                 converterTarget = item;
                 break;
             }
         }

         if (converterTarget != null) {
             converterList.remove(converterTarget);
         }
         HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
         converterList.add(converter);
     }
	
	
	/*public static String wmsEdit(String url,JsonObject jsonObject){
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.put(url, null, jsonObject);
	}*/
	
	/**
	 * rest get方式
	 * @param url
	 * @param paramsMap
	 * @return
	 */
	public static String wmsRestGet(String url,JsonObject jsonObject){
		RestTemplate restTemplate = new RestTemplate(messageConverters);
		return restTemplate.getForObject(url,String.class, jsonObject);
	}
	
}
