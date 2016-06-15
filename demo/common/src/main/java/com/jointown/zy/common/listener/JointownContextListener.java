package com.jointown.zy.common.listener;

import javax.servlet.ServletContextEvent;

import com.jointown.zy.common.logback.JointownLogbackContextListener;

/**
 * 重写 ContextLoaderListener，加载logback等等
 * 以方便使用
 * @author tendy
 *
 */
public class JointownContextListener extends JointownLogbackContextListener {
	

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
	}
	
}
