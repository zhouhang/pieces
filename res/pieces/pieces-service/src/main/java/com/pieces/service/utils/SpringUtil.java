package com.pieces.service.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

/**
 * Spring context util 获得 Spring context 的 context
 * 
 * @author Liupiao
 * 
 */
@Component
public class SpringUtil implements ApplicationContextAware{
	
	private static final Logger logger = LoggerFactory.getLogger(SpringUtil.class);

	private static ApplicationContext applicationContext = null;
	
	private static Properties configProperties = new Properties();

//	static{
//		configProperties = loadProperties("com/jointown/zy/common/properties/config.properties");
//		logger.info("config properties loaded,it is:"+configProperties);
//	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		applicationContext = context;
//		logger.info("config properties loaded,it is:"+configProperties);configProperties = getConfigProperties();
	}
	
	public static Object getBean(String beanId) {
		return getApplicationContext().getBean(beanId);
	}
	
	public static <T> T getBean(Class<T> cls){
		return getApplicationContext().getBean(cls);
	}
	
	public static <T> T getBean(String name,Class<T> cls){
		return getApplicationContext().getBean(name, cls);
	}
	
	/**
	 * 
	 * @Description: 获取所有的配置文件属性
	 * @Author: robin.liu
	 * @Date: 2015年9月2日
	 * @return
	 */
	public static Properties getConfigProperties(){
		return configProperties;
	}
	
	/**
	 * 
	 * @Description: 获取所有的配置文件属性值
	 * @Author: robin.liu
	 * @Date: 2015年9月2日
	 * @return
	 */
	public static String getConfigProperties(String key){
		return getConfigProperties().getProperty(key);
	}
	
	/**
	 * 
	 * @Description: 添加配置信息
	 * @Author: robin.liu
	 * @Date: 2015年9月2日
	 * @param prop
	 */
	@SuppressWarnings("unchecked")
	public static void addConfigProperties(Properties prop){
		Enumeration<String> names = (Enumeration<String>)prop.propertyNames();
		while(names.hasMoreElements()){
			String propName = names.nextElement();
			configProperties.put(propName,prop.getProperty(propName));
		}
		logger.info("config properties loaded,it is:"+configProperties);
	}
	
	/**
	 * 
	 * @Description: 加载配置信息并返回
	 * @Author: robin.liu
	 * @Date: 2015年9月2日
	 * @param propertiesPah
	 * @return
	 */
	public static Properties loadProperties(String propertiesPah,String...encoding){
		EncodedResource encodedResource = new EncodedResource(new ClassPathResource(propertiesPah), ArrayUtils.isEmpty(encoding)?"UTF-8":encoding[0]);
		try {
			return PropertiesLoaderUtils.loadProperties(encodedResource);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
}
