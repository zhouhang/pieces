/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.web.job;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.service.BusiPurchaseService;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.vo.BusiPurchaseVo;

/**
 * @ClassName: BusiPurchaseJob
 * @Description: 定时扫描过期采购
 * @Author: 赵航
 * @Date: 2015年4月16日
 * @Version: 1.0
 */
public class BusiPurchaseJob implements Job{
	
	private static final Logger logger = LoggerFactory.getLogger(BusiPurchaseJob.class);
	
	private BusiPurchaseService purchaseService = (BusiPurchaseService) SpringUtil.getBean(BusiPurchaseService.class);
	
		
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("BusiPurchaseJob start!");
		List<BusiPurchaseVo> purchases = purchaseService.getExpiredPurchases();
		if(!CollectionUtils.isEmpty(purchases)){
			for(BusiPurchaseVo purchase:purchases){
				try {
					//过期采购
					purchaseService.expirePurchases(purchase);
				}catch (Exception e) {
					logger.error("BusiPurchaseJob execute expirePurchases error, purchaseId is:[" + purchase.getPurchaseId() +"],purchaseCode is:[" + purchase.getPurchaseCode() +"] error is " + e.getMessage());
					continue;
				}
				
			}
		}
		logger.info("BusiPurchaseJob end!");
		
	}
}
