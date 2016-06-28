/**
 * Copyright © 2014-2015 珍药材版权所有
 */
package com.jointown.zy.web.job;

import java.util.List;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonObject;
import com.jointown.zy.common.service.BusiOrderDepositService;
import com.jointown.zy.common.util.SpringUtil;

/**
 * @ClassName: OrderDepositJob
 * @Description: 交易划账
 * @Author: 赵航
 * @Date: 2015年7月3日
 * @Version: 1.0
 */
public class OrderDepositJob extends TimerTask{
	
	private static final Logger logger = LoggerFactory.getLogger(OrderDepositJob.class);
	
	private static OrderDepositJob orderDepositJob;
	
	private BusiOrderDepositService busiOrderDepositService;
	
	/**
	 * 获取OrderDepositJob单实例
	 */
	public static OrderDepositJob getInstance(){
		if(orderDepositJob == null){
			synchronized (OrderDepositJob.class) {
				if(orderDepositJob == null){
					orderDepositJob = new OrderDepositJob();
				}
			}
		}
		return orderDepositJob;
	}
	
	private OrderDepositJob() {
		busiOrderDepositService = SpringUtil.getBean(BusiOrderDepositService.class);
	}

	@Override
	public void run() {
		// 扫描支付划账结果
		List<JsonObject> jsonList = null;
		try {
			jsonList = busiOrderDepositService.selectOrderDepositResults(1);
		} catch (Exception e) {
			logger.error("OrderDepositJob execute selectOrderDepositResults, error is " + e.getMessage());
			//扫描出现异常，直接停止当前任务
			return;
		}
		
		if(jsonList != null && jsonList.size() > 0){
			for(JsonObject json : jsonList){
				try {
					//执行订单划账更新操作
					busiOrderDepositService.changOrderDepositState(json);
				} catch (Exception e) {
					logger.error("OrderDepositJob execute changOrderDepositState[" + json.get("orderId").getAsString() +"], error is " + e.getMessage());
					//执行出现异常，当前订单划账操作略过，待下次扫描时，再次执行。
					//并继续执行其它的订单划账操作
					continue;
				}
			}
		}
	}

}
