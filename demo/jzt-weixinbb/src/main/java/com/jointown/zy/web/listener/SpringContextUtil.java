package com.jointown.zy.web.listener;

import org.springframework.context.ApplicationContext;

/**
 * Spring context util
 * 获得 Spring context 的 context
 * @author tendy
 *
 */
public class SpringContextUtil {

	private static ApplicationContext context = null;

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext context) {
		SpringContextUtil.context = context;
	}

	public static Object getBean(String beanId) {
		return context.getBean(beanId);
	}
}
