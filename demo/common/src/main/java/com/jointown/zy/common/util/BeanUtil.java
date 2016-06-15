package com.jointown.zy.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * 进行基础的对象转换<br>
 * 如：对象与json转换,Map与json转换等
 * 
 * @author kevinzhou
 * 
 */
public class BeanUtil {

	private static Gson gson = new GsonBuilder()
			.enableComplexMapKeySerialization()
			// 支持Map的key为复杂对象的形式
			// .setDateFormat("yyyy-MM-dd HH:mm:ss")// 时间转化为特定格式
			.registerTypeAdapter(Date.class, new DateSerializerUtil()).registerTypeAdapter(Calendar.class, new CalendarSerializerUtil())
			.registerTypeAdapter(GregorianCalendar.class, new CalendarSerializerUtil()).setDateFormat(DateFormat.LONG).setPrettyPrinting() // 对json结果格式化.
			.create();

	public static Gson getGson() {
		return new GsonBuilder()
				.enableComplexMapKeySerialization()
				// 支持Map的key为复杂对象的形式
				// .setDateFormat("yyyy-MM-dd HH:mm:ss")// 时间转化为特定格式
				.registerTypeAdapter(Date.class, new DateSerializerUtil()).registerTypeAdapter(Calendar.class, new CalendarSerializerUtil())
				.registerTypeAdapter(GregorianCalendar.class, new CalendarSerializerUtil()).setDateFormat(DateFormat.LONG).setPrettyPrinting() // 对json结果格式化.
				.create();
	}

	/**
	 * 将对象转换为json字符串
	 * 
	 * @param object
	 * @return json字符串
	 */
	public static <T> String objectToJson(T object) {
		if (object == null) {
			return null;
		}
		return gson.toJson(object);
	}

	/**
	 * 将json字符串转换为对象
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonToObject(String json, Class<T> clazz) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		return gson.fromJson(json, clazz);
	}

	/**
	 * 将Map转换为json
	 * 
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<String, ?> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		return gson.toJson(map);
	}

	/**
	 * 将json转换为MAP
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, String> jsonToMap(String json) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		return gson.fromJson(json, new TypeToken<Map<String, String>>() {
		}.getType());

	}

	/**
	 * 将对象转换为MAP
	 * 
	 * @param object
	 * @return
	 */
	public static <T> Map<String, String> objectToMap(T object) {
		if (object == null) {
			return null;
		}
		String json = objectToJson(object);
		return jsonToMap(json);
	}

	/**
	 * 将Map转换为对象
	 * 
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T mapToObject(Map<String, ?> map, Class<T> clazz) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		String json = mapToJson(map);
		return jsonToObject(json, clazz);
	}
	
	
	/**
	* 复制源对象和目标对象的属性值
	**/

	public static void copy(Object source, Object target) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class sourceClass = source.getClass();//得到对象的Class
		Class targetClass = target.getClass();//得到对象的Class
		Field[] sourceFields = sourceClass.getDeclaredFields();//得到Class对象的所有属性
		Field[] targetFields = targetClass.getDeclaredFields();//得到Class对象的所有属性
		for(Field sourceField : sourceFields){
			String name = sourceField.getName();//属性名
			Class type = sourceField.getType();//属性类型
			String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
			Method getMethod = sourceClass.getMethod("get" + methodName);//得到属性对应get方法
			Object value = getMethod.invoke(source);//执行源对象的get方法得到属性值
			for(Field targetField : targetFields){
				String targetName = targetField.getName();//目标对象的属性名
				if(targetName.equals(name)){
					Method setMethod = targetClass.getMethod("set" + methodName, type);//属性对应的set方法
					setMethod.invoke(target, value);//执行目标对象的set方法
				}
			}
		}
	}
	

}
