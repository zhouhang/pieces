package com.jointown.zy.web.listener;

import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jointown.zy.common.logback.JointownLogbackContextListener;
import com.jointown.zy.web.job.BusiOrderJob;
import com.jointown.zy.web.job.BusiPurchaseJob;
import com.jointown.zy.web.job.OrderCancelJob;
import com.jointown.zy.web.job.OrderDepositJob;
import com.jointown.zy.web.job.QuartzJob;
import com.jointown.zy.web.job.SalesmanJob;
import com.jointown.zy.web.job.SolrConsumerManager;
import com.jointown.zy.web.job.WmsConsumerManager;
import com.jointown.zy.web.task.QuartzManager;

/**
 * 重写 ContextLoaderListener，把 ApplicationContext 设置到 SpringContextUtil
 * 以方便使用
 * @author tendy
 *
 */
public class JointownContextListener extends JointownLogbackContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		
		// 把 把 ApplicationContext 设置到 SpringContextUtil
		ServletContext context = event.getServletContext();
		
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        SpringContextUtil.setContext(ctx);
        
        //初始化表等
        
		QuartzManager.addJob("取消挂牌任务调度:1小时", QuartzJob.class, "0 0 */1 * * ?");   //1h
		
		//每60s扫描一次过期订单并取消
        QuartzManager.addJob("扫描过期订单并取消", OrderCancelJob.class, "0/60 * * * * ?");
        
		//SOLR挂牌索引数据
        Timer timer = new Timer();
        timer.schedule(SolrConsumerManager.getInstance(), 1000, 2000);
        
        //WMS同步数据
        Timer wms = new Timer();
        wms.schedule(WmsConsumerManager.getInstance(), 1000, 2000);
        
        //支付——>交易——>WMS同步任务（每1s扫描一次）
		Timer orderJob = new Timer();
        orderJob.schedule(BusiOrderJob.getInstance(), 1000, 1000);
        
        //扫描支付划账结果（每60s一次）
        Timer depositJob = new Timer();
        depositJob.schedule(OrderDepositJob.getInstance(), 1000, 60000);
        
        //每60s扫描一次过期采购并取消
        QuartzManager.addJob("扫描过期采购并取消", BusiPurchaseJob.class, "0/60 * * * * ?");
        
        //added by biran 20151022 更新历史挂牌，订单业务员信息 每天一次，12点开始
		QuartzManager.addJob("更新历史挂牌，订单业务员信息", SalesmanJob.class, "0 0 12 * * ?");  
        
	}

}
