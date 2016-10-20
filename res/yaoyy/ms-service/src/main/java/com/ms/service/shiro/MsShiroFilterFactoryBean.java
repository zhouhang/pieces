package com.ms.service.shiro;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 改造的shiro filter factorybean,可以通过字符串在properties文件定义filter链
 * @author LiuPiao
 *
 */
public class MsShiroFilterFactoryBean extends ShiroFilterFactoryBean implements ApplicationContextAware,InitializingBean {
	
	private ApplicationContext applicationContext = null;
	
	private Map<String, String> filtersStringMap = new LinkedHashMap<String, String>(); 
	private Map<String, String> filterChainDefinitionsStringMap = new LinkedHashMap<String, String>();
	
	/**
	 * 解析string类型filters到 filters map
	 * @param filters
	 */
 	public void setFiltersString(String filters) {  
        if(StringUtils.isEmpty(StringUtils.trim(filters))) {  
            return;  
        }  
        String[] filterArray = StringUtils.trim(filters).split(";");  
        for(String filter : filterArray) {  
            String[] filterPair = filter.split("=");
            filtersStringMap.put(StringUtils.trim(filterPair[0]), StringUtils.trim(filterPair[1]));
        }  
    }  
 	
 	/**
 	 * 解析string类型的filters definition到 filter chain definition
 	 * @param filterChainDefinitions
 	 */
    public void setFilterChainDefinitionsString(String filterChainDefinitions) {  
        if(StringUtils.isEmpty(StringUtils.trim(filterChainDefinitions))) {  
            return;  
        }  
        String[] chainDefinitionsArray = StringUtils.trim(filterChainDefinitions).split(";");  
        for(String filter : chainDefinitionsArray) {  
            String[] filterPair = filter.split("=");  
            filterChainDefinitionsStringMap.put(StringUtils.trim(filterPair[0]), StringUtils.trim(filterPair[1]));
        }  
    }

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public Map<String, String> getFiltersStringMap() {
		return filtersStringMap;
	}

	public Map<String, String> getFilterChainDefinitionsStringMap() {
		return filterChainDefinitionsStringMap;
	}


	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 初始化 filters 和 filters chain definitions
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if(!MapUtils.isEmpty(filtersStringMap)){
			for(Entry<String, String> entry:filtersStringMap.entrySet()){
				getFilters().put(entry.getKey(), (Filter)applicationContext.getBean(entry.getValue()));
			}
		}
		if(!MapUtils.isEmpty(filterChainDefinitionsStringMap)){
			setFilterChainDefinitionMap(filterChainDefinitionsStringMap);
		}
	} 
	
}