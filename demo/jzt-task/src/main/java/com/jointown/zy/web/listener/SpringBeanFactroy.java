package com.jointown.zy.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @功能描述:工厂类,在spring被web容器加载前初始化spring ApplicationContext,
 * 并根据beanId获取所在的本地bean对象。
 * @author Mr.songwei
 * @date 2014-11-26
 */
public class SpringBeanFactroy {
	private static final Logger log = LoggerFactory.getLogger(SpringBeanFactroy.class);
	//实例化ApplicationContext
	private static ApplicationContext ac=null;
	
	static{
		try{
			ac = new ClassPathXmlApplicationContext("classpath:com/jointown/zy/task/spring/spring.xml");
		}catch(java.lang.ExceptionInInitializerError e){
			log.error("spring非web容器下加载异常："+e.getMessage());
		}
	}
	
	/**
	 * @function getBean 通过beanId查找bean实例
	 * @param beanId
	 * @return Object bean对象
	 */
	public static  Object getBean(String beanId){
		if(ac==null){
			log.info("LocalSpringBeanFactroy:空指针异常!");
			throw new NullPointerException("LocalSpringBeanFactroy:空指针异常!");
		}
		return ac.getBean(beanId);
	}
	
	/**
	 * @function getBean 通过class查找bean实例
	 * @param Class<T> cls
	 * @return <T> Object bean对象
	 */
	public static <T> Object getBean(Class<T> cls){
		log.info("getBean ===>>>!");
		if(ac==null){
			log.info("LocalSpringBeanFactroy:空指针异常!");
			throw new NullPointerException("LocalSpringBeanFactroy:空指针异常!");
		}
		return ac.getBean(cls);
	}
}