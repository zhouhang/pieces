package com.jointown.zy.cms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 重写 ContextLoaderListener，把 ApplicationContext 设置到 SpringContextUtil
 * 以方便使用
 * @author tendy
 *
 */
public class JointownContextListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		
		// 把 把 ApplicationContext 设置到 SpringContextUtil
		ServletContext context = event.getServletContext();
		
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        SpringContextUtil.setContext(ctx);
        
        //初始化表等
	}

}
