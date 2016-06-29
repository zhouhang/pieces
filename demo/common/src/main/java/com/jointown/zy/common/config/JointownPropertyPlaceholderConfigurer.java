package com.jointown.zy.common.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import com.jointown.zy.common.util.SpringUtil;

public class JointownPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	
	private Resource[] matchedlocations;
	private String preConfigLocation;
	private String envSuffixKey;
	private static final String DEFAULT_PRE_CONFIG_LOCATION = "/com/jointown/zy/common/properties/preConfig.properties";
	private static final String DEFAULT_ENV_SUFFIX_KEY = "jointown.env";
	private static final String DEFAULT_NEEDED_PROPERTIES_PATTERN = "";
	
	
	private String fileEncoding = "UTF-8";
	private Boolean ignoreResourceNotFound;
	
	/**
	 * 设置加载配置文件地址，此时做了自定义，按照预加载配置中的环境变量加载相应的配置文件
	 */
	@Override
	public void setLocations(Resource[] locations) {
		//循环过滤,只取有环境配置后缀名的配置文件。如xxxx_<envSuffix>.properties
		List<Resource> resourceLocations = new ArrayList<Resource>();
		for(Resource loc:locations){
			if(match(loc.getFilename())){
				resourceLocations.add(loc);
			}
		}
		//用于本地缓存匹配的locations路径
		Resource[] resources = resourceLocations.toArray(new Resource[resourceLocations.size()]);
		this.setMatchedLocations(resources);
		super.setLocations(resources);
	}
	
	/**
	 * 客户化匹配逻辑
	 * @param fileName
	 * @return
	 */
	public boolean match(String fileName){
		//获取环境配置后缀名
		String envSuffix = (String)getPreConfig().get(this.envSuffixKey!=null?envSuffixKey:DEFAULT_ENV_SUFFIX_KEY);
		boolean flag = false;
		if(fileName.matches(".*_\\["+envSuffix+"\\].properties")){
			flag = true;
		}else if(!fileName.matches(".*_\\[\\w+\\]\\.properties")){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 获取preConfig.properties
	 * @return
	 */
	public Properties getPreConfig(){
		Properties prop = new Properties();
		try {
			if(this.preConfigLocation!=null){
				prop.load(this.getClass().getResourceAsStream(this.preConfigLocation));
			}else{
				prop.load(this.getClass().getResourceAsStream(DEFAULT_PRE_CONFIG_LOCATION));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * bean后处理器重写方法.
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		try {
			Properties mergedProps = mergeProperties();
			// Convert the merged properties, if necessary.
			convertProperties(mergedProps);
			// 设置所有属性
			SpringUtil.addConfigProperties(mergedProps);
			// Let the subclass process the properties.
			processProperties(beanFactory, mergedProps);
		}
		catch (IOException ex) {
			throw new BeanInitializationException("Could not load properties", ex);
		}
	}
	
	/**
	 * 客户化加载配置文件属性，支持自定义编码格式，默认UTF-8
	 * 用fileEncoding设置
	 */
	@Override
	protected void loadProperties(Properties props) throws IOException {
		Resource[] matchedLocations = this.getMatchedLocations();
		if (matchedLocations != null) {
			for (Resource location : matchedLocations) {
				if (logger.isInfoEnabled()) {
					logger.info("Loading properties file from " + location);
				}
				try {
					PropertiesLoaderUtils.fillProperties(
							props, new EncodedResource(location, this.fileEncoding));
				}
				catch (IOException ex) {
					if (this.ignoreResourceNotFound) {
						if (logger.isWarnEnabled()) {
							logger.warn("Could not load properties from " + location + ": " + ex.getMessage());
						}
					}
					else {
						throw ex;
					}
				}
			}
		}
	}
	
	
	public Resource[] getMatchedLocations() {
		return matchedlocations;
	}

	public void setMatchedLocations(Resource[] locationsPath) {
		this.matchedlocations = locationsPath;
	}

	public String getPreConfigLocation() {
		return preConfigLocation;
	}

	public void setPreConfigLocation(String preConfigLocation) {
		this.preConfigLocation = preConfigLocation;
	}

	public String getEnvSuffixKey() {
		return envSuffixKey;
	}

	public void setEnvSuffixKey(String envSuffixKey) {
		this.envSuffixKey = envSuffixKey;
	}


	public Resource[] getMatchedlocations() {
		return matchedlocations;
	}


	public void setMatchedlocations(Resource[] matchedlocations) {
		this.matchedlocations = matchedlocations;
	}


	public String getFileEncoding() {
		return fileEncoding;
	}


	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}


	public Boolean getIgnoreResourceNotFound() {
		return ignoreResourceNotFound;
	}


	public void setIgnoreResourceNotFound(Boolean ignoreResourceNotFound) {
		this.ignoreResourceNotFound = ignoreResourceNotFound;
	}
	
}
