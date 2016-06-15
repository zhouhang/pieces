package com.jointown.zy.web.servlet;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;

import com.jointown.zy.web.job.QuartzJob;
import com.jointown.zy.web.task.QuartzManager;

/**
 * 自定义spring分发servlet。用于初始化一些基础的配置等
 * @author LiuPiao
 *
 */
public class JztTaskServlet extends DispatcherServlet{
	private static final Logger log = LoggerFactory.getLogger(JztTaskServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void initFrameworkServlet() throws ServletException {
		String job_name = "取消挂牌任务调度:1小时";
		log.info("initFrameworkServlet::"+job_name);
		QuartzManager.addJob(job_name, QuartzJob.class, "0 0 */1 * * ?");   //1h
		//     0/10 * * * * ? 10秒
		//     0 0 */1 * * ?  1小时
	}
}
