/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.logback;

import java.io.IOException;

import javax.servlet.ServletContextEvent;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.ContextLoaderListener;

/**
 * @ClassName: JointownLogbackContextListener
 * @Description: 加载logback的监听
 * @Author: robin.liu
 * @Date: 2015年9月1日
 * @Version: 1.0
 */
public class JointownLogbackContextListener extends ContextLoaderListener {
	
	private static final String LOCATION_NAME = "logbackConfigLocation";
    /* (non-Javadoc)
     * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
    	super.contextInitialized(event);
    	String path = event.getServletContext().getInitParameter(LOCATION_NAME);
    	if(StringUtils.isEmpty(path)){
    		path = "logback.xml";
    	}
    	Resource resource = new ClassPathResource(path);
        try {  
        	new JointownLogbackConfigurator().init(resource.getURL());
        }catch(IOException e){
	        	e.printStackTrace(); 
        }
    }
	
	

}
