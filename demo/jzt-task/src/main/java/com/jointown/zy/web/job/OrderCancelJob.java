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
import com.jointown.zy.common.service.BusiOrderService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.task.MessageTaskSend;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.util.TimeUtil;

/**
 * @ClassName: OrderCancelJob
 * @Description: 定时扫描过期订单，并取消相关订单
 * @Author: 赵航
 * @Date: 2015年4月16日
 * @Version: 1.0
 */
public class OrderCancelJob implements Job{
	
	private static final Logger logger = LoggerFactory.getLogger(OrderCancelJob.class);
	
	private BusiOrderService busiOrderService = (BusiOrderService) SpringUtil.getBean(BusiOrderService.class);
			
	private BossOrderCancelService bossOrderCancelService = (BossOrderCancelService) SpringUtil.getBean(BossOrderCancelService.class);
	

	//已下单订单72小时过期天数
	private Integer afterDays = Integer.parseInt(SpringUtil.getConfigProperties("jointown.busi.order.expire.days.after"));
	//即将过期账期订单提前4天提醒
	private Integer beforeDays = Integer.parseInt(SpringUtil.getConfigProperties("jointown.busi.term.order.expire.days.before"));
	//提醒时间（时分秒）
	private String warnHms = SpringUtil.getConfigProperties("jointown.busi.term.order.warn.hms");
	
		
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("OrderCancelJob execute");
		// 检索已下单的72小时过期的订单
		List<BusiOrder> busiOrders = null;
		try {
			busiOrders = busiOrderService.findOverTimeOrdersByPlaced(afterDays);
		} catch (Exception e) {
			logger.error("OrderCancelJob execute findOverTimeOrdersByPlaced, error is " + e.getMessage());
			//扫描出现异常，直接停止当前任务
			return;
		}
		if(busiOrders != null && busiOrders.size() > 0){
			for(BusiOrder order : busiOrders){
				try {
					//执行订单关闭操作
					String orderId = order.getOrderid();
					busiOrderService.updateOverTimeOrderById(orderId);
				} catch (Exception e) {
					logger.error("OrderCancelJob execute updateOverTimeOrderById[" + order.getOrderid() +"], error is " + e.getMessage());
					//执行出现异常，当前订单操作回滚（AOP方式），待下次扫描时，最次执行。
					//并继续执行其它的订单关闭操作
					continue;
				}
			}
		}
		// 检索已备货的过期订单
		List<BusiOrder> orderList = null;
		try {
			orderList = bossOrderCancelService.selectOverTimeOrderList(10);
		} catch (Exception e) {
			logger.error("OrderCancelJob execute selectOverTimeOrderList, error is " + e.getMessage());
			//扫描出现异常，直接停止当前任务
			return;
		}
		if(orderList != null && orderList.size() > 0){
			for(BusiOrder order : orderList){
				try {
					//执行订单取消操作
					bossOrderCancelService.orderCancelByTask(order);
				} catch (Exception e) {
					logger.error("OrderCancelJob execute updateOrderCancelByTask[" + order.getOrderid() +"], error is " + e.getMessage());
					//执行出现异常，当前订单操作回滚（AOP方式），待下次扫描时，最次执行。
					//并继续执行其它的订单取消操作
					continue;
				}
			}
		}
		
		/**
		 * add by fanyuna 2015.09.08 
		 * 1、设置的账期时间快到期未付款的通知
		 * 2、设置超过账期时间未付款的通知
		 * =======start======
		 */
		//设置的账期时间快到期的通知
		busiOrderService.TermOrderSendMsg(beforeDays,warnHms);
		
		//设置超过账期时间未付款的通知
		busiOrderService.TermOrderSendMsg(-1,warnHms);
		
		/**
		 * add by fanyuna 2015.09.08 
		 * 1、设置的账期时间快到期的通知
		 * 2、设置超过账期时间未付款的通知
		 * =======end======
		 */
	}
}
