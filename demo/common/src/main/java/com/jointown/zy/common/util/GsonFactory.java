package com.jointown.zy.common.util;

import org.apache.commons.lang.StringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * Gson工厂类，可以获取能够对日期格式化的gson实例
 *
 */
public class GsonFactory {
	
	/**
	 * 获取GSON对象,日期类型按默认格式转换（默认格式：yyyy-MM-dd）
	 * @return
	 */
	public static Gson createGson(){
		return createGson("yyyy-MM-dd");
	}
	
	/**
	 * 获取GSON对象，该对象能够让日期类型按某种格式转换
	 * @param dateFormat 日期转换格式
	 * @return GSON对象
	 */
	public static Gson createGson(String dateFormat){
		GsonBuilder builder = new GsonBuilder();
		if(!StringUtils.isEmpty(dateFormat)){
			builder.setDateFormat(dateFormat);
		}
		return builder.serializeNulls().create();
	}
	
	/**
	 * @Description: 字符串转换成对象(日期类型默认为“yyyy-MM-dd”且与json字符串格式相同)
	 * @Author: 赵航
	 * @Date: 2015年5月20日
	 * @param jsonString json格式字符串
	 * @param targetCls 目标对象类型
	 * @return 目标对象
	 */
	public static <T> T fromJson(String jsonString, Class<T> targetCls){
		return fromJson(jsonString, targetCls, null);
	}
	
	/**
	 * @Description: 字符串转换成对象
	 * @Author: 赵航
	 * @Date: 2015年5月20日
	 * @param jsonString json格式字符串
	 * @param targetCls 目标对象类型
	 * @param dateFormat 日期类型格式（要与字符串中相同）
	 * @return 目标对象
	 */
	public static <T> T fromJson(String jsonString, Class<T> targetCls, String dateFormat){
		Gson gson = null;
		if(StringUtils.isEmpty(dateFormat)){
			gson = createGson();
		} else {
			gson = createGson(dateFormat);
		}
		return gson.fromJson(jsonString, targetCls);
	}
	
	/**
	 * @Description: 对象转换成字符串(来源对象中的日期类型在字符串中的显示方式为“yyyy-MM-dd”)
	 * @Author: 赵航
	 * @Date: 2015年5月20日
	 * @param obj 来源对象
	 * @return json字符串
	 */
	public static String toJson(Object obj){
		return toJson(obj,null);
	}
	
	/**
	 * @Description: 对象转换成字符串
	 * @Author: 赵航
	 * @Date: 2015年5月20日
	 * @param obj 来源对象
	 * @param dateFormat 日期类型格式（来源对象中的日期类型在字符串中的显示方式）
	 * @return json字符串
	 */
	public static String toJson(Object obj, String dateFormat){
		Gson gson = null;
		if(StringUtils.isEmpty(dateFormat)){
			gson = createGson();
		} else {
			gson = createGson(dateFormat);
		}
		return gson.toJson(obj);
	}
}
