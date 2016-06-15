package com.jointown.zy.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 反射工具类
 * @author LiuPiao
 *
 */
public class ReflectionUtil {
//	private static Logger log = LoggerFactory.getLogger(ReflectionUtil.class);
	private static final String CAPS_PATTERN = "[ABCDEFGHIJKLMNOPQRSTUVWXYZ]";
	
	/**
	 * 获取public类型的setter方法,包括父类中的方法
	 * @param cls
	 * @param fieldName
	 * @return
	 */
	public static Method setter(Class<?> cls,String fieldName){
		try {
			return cls.getMethod("set"+StringUtils.capitalize(fieldName));
		} catch (NoSuchMethodException e) {
//			log.error(e.getMessage());
		} catch (SecurityException e) {
//			log.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 获取public类型的getter方法,包括父类中的方法
	 * @param cls
	 * @param fieldName
	 * @return
	 */
	public static Method getter(Class<?> cls,String fieldName){
		try {
			return cls.getMethod("get"+StringUtils.capitalize(fieldName));
		} catch (NoSuchMethodException e) {
//			log.error(e.getMessage());
		} catch (SecurityException e) {
//			log.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 获取声明了的所有getter方法,组成方法名:方法Map. 不包括父类中的方法
	 * @param cls
	 * @return
	 */
	public static Map<String, Method> declaredGetters(Class<?> cls){
		Map<String, Method> map = null;
		for(Method m:cls.getDeclaredMethods()){
			if(m.getName().startsWith("get") && Pattern.matches(CAPS_PATTERN,new String(new char[]{m.getName().charAt(3)}))){
				if(map==null){
					map = new HashMap<String, Method>();
				}
				map.put(m.getName(), m);
			}
		}
		return map;
	}
	
	/**
	 * setter方法设值
	 * @param target
	 * @param fieldName
	 * @param value
	 */
	public static void set(Object target,String fieldName,String value){
		invokeMethod(setter(target.getClass(), fieldName), target, value);
	}
	
	/**
	 * getter方法获值
	 * @param target
	 * @param fieldName
	 * @return
	 */
	public static Object get(Object target,String fieldName){
		return invokeMethod(getter(target.getClass(), fieldName), target);
	}
	
	
	/**
	 * 获取声明了的成员Map组成 成员名:成员 Map
	 * @param cls
	 * @return
	 */
	public static Map<String,Field> getDeclaredFieldsMap(Class<?> cls){
		Map<String,Field> map = new HashMap<String, Field>();
		for(Field f:cls.getDeclaredFields()){
			map.put(f.getName(), f);
		}
		return map;
	}
	
	/**
	 * 获取声明了的方法Map组成 方法名:方法 Map
	 * @param cls
	 * @return
	 */
	public static Map<String,Method> getDeclaredMethodsMap(Class<?> cls){
		Map<String,Method> map = new HashMap<String, Method>();
		for(Method m:cls.getDeclaredMethods()){
			map.put(m.getName(), m);
		}
		return map;
	}
	
	/**
	 * 反射调用方法
	 * @param m
	 * @param target
	 * @param params
	 * @return
	 */
	public static Object invokeMethod(Method m,Object target,Object...params){
		if(m!=null&&target!=null){
			try {
				return m.invoke(target, params);
			} catch (IllegalAccessException e) {
//				log.error(e.getMessage());
			} catch (IllegalArgumentException e) {
//				log.error(e.getMessage());
			} catch (InvocationTargetException e) {
//				log.error(e.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * 反射调用方法
	 * @param m
	 * @param target
	 * @param params
	 * @return
	 */
	public static Object getAnnotationValue(String name,Annotation annotation){
			try {
				return annotation.annotationType().getDeclaredMethod(name).invoke(annotation);
			} catch (IllegalAccessException e) {
//				log.error(e.getMessage());
			} catch (IllegalArgumentException e) {
//				log.error(e.getMessage());
			} catch (InvocationTargetException e) {
//				log.error(e.getMessage());
			} catch (NoSuchMethodException e) {
//				log.error(e.getMessage());
			} catch (SecurityException e) {
//				log.error(e.getMessage());
			}
		return null;
	}
	
	public static <S,T> List<T> getList(Collection<S> srcCollection,Class<T> cls,String fieldName){
		if(CollectionUtils.isNotEmpty(srcCollection)){
			List<T> list = new ArrayList<T>();
			for(S c:srcCollection){
				T t = (T)ReflectionUtil.get(c, fieldName);
				list.add(t);
			}
			return list;
		}
		return null;
	}
	
}
