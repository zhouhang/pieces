/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.web.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.constant.MessageConstant;
import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.service.BossOrderCancelService;
import com.jointown.zy.common.service.BusiListingService;
import com.jointown.zy.common.service.BusiOrderService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.task.MessageTaskSend;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.util.TimeUtil;

/*
 * @ClassName: SalesmanJob
 * @Description: 更新挂牌，订单的业务员信息
 * @Author: biran
 * @Date: 2015-10-14
 * @Version: 1.0
 */
public class SalesmanJob implements Job{
	
	private static final Logger logger = LoggerFactory.getLogger(SalesmanJob.class);
	
	private BusiListingService busiListingService = (BusiListingService) SpringUtil.getBean(BusiListingService.class);
			
		
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.debug("SalesmanJob execute 开始执行更新挂牌，订单的业务员信息定时任务！");
		try {
			busiListingService.updateSalesManInfoByPRO();
		} catch (Exception e) {
			logger.error("更新挂牌，订单的业务员信息定时任务，执行错误"+e.getMessage());
			return;
		}
		logger.debug("SalesmanJob execute 执行结束！");
	}
}
