package com.jointown.zy.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 属性文件（property）操作
 * author ldp
 * 2014年12月17日
 */
public class PropertiesUtils {
	private static Log log = LogFactory.getLog(PropertiesUtils.class);
	
	private static Map<String, PropertiesConfiguration> configMap = new HashMap<String, PropertiesConfiguration>();
	
	private static PropertiesConfiguration initConfigFile(String configFileName) throws ConfigurationException {
		PropertiesConfiguration propConfig = new PropertiesConfiguration();
		propConfig.setDelimiterParsingDisabled(true);		
		propConfig.setFileName(configFileName);
		propConfig.load();
		configMap.put(configFileName, propConfig);
		return propConfig;
	}
	
	public static void setProperty(String configFileName, String key, String value) {
		PropertiesConfiguration propConfig = configMap.get(configFileName);		
		try {
			if(propConfig == null) {
				propConfig = initConfigFile(configFileName);
			}
			
			propConfig.setProperty(key, value);
			propConfig.save();
		} catch(ConfigurationException ex) {
			log.error("save property value error,file name:"+configFileName+" key:"+key+" value:"+value, ex);
		}
	}
	
	public static String getProperty(String configFileName, String key) {
		PropertiesConfiguration propConfig = configMap.get(configFileName);
		String value = null;
		try {
			if(propConfig == null) {
				propConfig = initConfigFile(configFileName);
			}
			if(propConfig.containsKey(key)) {
				Object propValue = propConfig.getProperty(key);
				value = (String)propValue;
			}			
		} catch(ConfigurationException ex) {
			log.error("get property value error,file name:"+configFileName+" key:"+key, ex);
		}
		
		return value;		
	}
	
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getProperties(String configFileName) {
		PropertiesConfiguration propConfig = configMap.get(configFileName);
		try {
			if(propConfig == null) {
				propConfig = initConfigFile(configFileName);
			}
		} catch(ConfigurationException ex) {
			log.error("init config file error,file name:"+configFileName, ex);
			return null;
		}
		Map<String, Object> propMap = new HashMap<String, Object>();
		Iterator iter = propConfig.getKeys();
		while(iter.hasNext()) {
			String key = (String)iter.next();
			propMap.put(key, propConfig.getProperty(key));
		}
		return propMap;
	}
	
		
//	public static void main(String[] args) {
//		//"E:/jzt/jzt_workspace_svn/jointown/common/src/main/resources/com/jointown/zy/common/properties/config.properties";
//		String config_file = "com/jointown/zy/common/properties/config.properties";
//		System.out.println(PropertiesUtils.getProperty(config_file, "email.smtp"));
//		System.out.println(PropertiesUtils.getProperties(config_file));
//		//PropertiesUtils.setProperty(config_file, "test", "123456");
//	}
	

}
