/**
 * @Description: 
 *
 * @Title: QuartzJob.java
 * @Package com.joyce.quartz
 * @Copyright: Copyright (c) 2014
 *
 * @author Comsys-LZP
 * @date 2014-6-26 下午03:37:11
 * @version V2.0
 */
package com.jointown.zy.web.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.dao.BusiListingSalesmanDao;
import com.jointown.zy.common.dao.BreedDao;
import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.enums.SolrContentTypeEnum;
import com.jointown.zy.common.enums.SolrOperationTypeEnum;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.BusiListingSalesman;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BusiListingService;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.web.listener.SpringBeanFactroy;

/**
 * @Description: 任务执行类
 *
 * @ClassName: QuartzJob
 * @Copyright: Copyright (c) 2014
 *
 * @author Comsys-LZP
 * @date 2014-6-26 下午03:37:11
 * @version V2.0
 */
public class QuartzJob implements Job {
	private static final Logger log = LoggerFactory.getLogger(QuartzJob.class);
	
	BusiListingService busiListingService = (BusiListingService) SpringBeanFactroy.getBean(BusiListingService.class);
	BusiWhlistService busiWhlistService=(BusiWhlistService)SpringBeanFactroy.getBean(BusiWhlistService.class);
	//发送线程
	private ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor)SpringBeanFactroy.getBean(ThreadPoolTaskExecutor.class);
	private UcUserDao ucUserDao = (UcUserDao)SpringBeanFactroy.getBean(UcUserDao.class);
	//提前天数
	private Integer beforeDays = Integer.parseInt(SpringUtil.getConfigProperties("jointown.busi.listing.expire.days.before"));
	//短信任务
	private MessageConfigManage  messageConfigManage= (MessageConfigManage) SpringBeanFactroy.getBean(MessageConfigManage.class);
	
	private BusiListingSalesmanDao busiListingSalesmanDao = (BusiListingSalesmanDao)SpringBeanFactroy.getBean(BusiListingSalesmanDao.class);
	
	private BossUserDao bossUserDao = (BossUserDao)SpringBeanFactroy.getBean(BossUserDao.class);
	
	private BreedDao breedDao = (BreedDao)SpringBeanFactroy.getBean(BreedDao.class);
	
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		log.debug("QuartzJob execute 提前通知挂牌过期任务开始执行！");
		//查询当前即将过期的挂牌中状态的挂牌
		List<BusiListing> nearlyExpiredListings = busiListingService.selectNotExpiredListings(beforeDays);
		//给即将过期的发邮件
		for(BusiListing busiListing :nearlyExpiredListings){
			//to do发送短信通知卖家挂牌即将过期
			//to do发送邮件通知运营挂牌即将过期
			//更新挂牌 发出通知标志（记得审核通过后要清除该标志）————已省略
			try {
				String listingId = busiListing.getListingid();
				Date expireTime = TimeUtil.moveDays(busiListing.getExaminetime(), busiListing.getListingtimelimit());
				//业务员对象
				BossUser salesmanInfo =null;
				//发送短信通知卖家挂牌过期
				Long userId = busiListing.getUserid();
				UcUser ucUser = ucUserDao.findMemberByUserId(userId.toString());
				if(ucUser!=null){
					String userMobile = ucUser.getMobile();
					taskExecutor.execute(messageConfigManage.getMessageChannelTask(userMobile,GetMessageContext.getListingOvertimeWarningMsg(busiListing, expireTime)));
					//added by biran 20151021  给业务员发送通知短信
					BusiListingSalesman salesman =busiListingSalesmanDao.findBusiListingSalesmanByListingId(listingId);
					
					if(salesman!=null && salesman.getSalesmanId()!=null  && !salesman.getSalesmanId().equals("")){//有对应业务员时
						 salesmanInfo = bossUserDao.getBossUserByUserId(Long.valueOf(salesman.getSalesmanId()));//卖家关联的业务员信息
						if(salesmanInfo.getMobile()!=null && !salesmanInfo.getMobile().equals("")){
							Breed breed=breedDao.selectByPrimaryKey(busiListing.getBreedid());//品种信息
							String realName=ucUserDao.getCertifyNameByUserId(busiListing.getUserid());//认证名称
							taskExecutor.execute(messageConfigManage.getMessageChannelTask(salesmanInfo.getMobile(),
								GetMessageContext.getListingOvertimeWarningMsg4SalesMan(busiListing.getListingid(),realName+"("+ucUser.getUserName()+")",breed.getBreedName(),expireTime),
								"挂牌[" + busiListing.getListingid() + "]过期提醒，发送短信通知出错，错误信息是："));
						}
					}
					//added end
				}
				//发送邮件通知运营挂牌过期
				taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_LISTING_OVERTIME_WARNING, GetEmailContext.getJYOperatorEmail(), GetEmailContext.getListingOvertimeWarningEmailMsg(busiListing,ucUser.getUserName(), expireTime,salesmanInfo)));
			} catch (Exception e) {
				log.error("提前通知挂牌过期任务失败："+e.getMessage(), e);
			}
		}
		log.debug("QuartzJob execute 提前通知挂牌过期任务执行结束！");
		log.debug("QuartzJob execute 取消挂牌任务开始执行！");
		List<String> listingIds = new ArrayList<String>(); 
		//查询当前已过期的挂牌中状态的挂牌
		List<BusiListing> busiListings = busiListingService.selectNotExpiredListings();
		for(BusiListing busiListing :busiListings){
			if(busiListing!=null){
				String listingId = busiListing.getListingid();
				Date expireTime = TimeUtil.moveDays(busiListing.getExaminetime(), busiListing.getListingtimelimit());
				try {
					Long userId = busiListing.getUserid();
					//设置操作者-null
					busiListing.setUserid(null);
					busiListingService.updateListingFlagDisabled(busiListing);
					listingIds.add(listingId);
					//发送短信通知卖家挂牌过期
					UcUser ucUser = ucUserDao.findMemberByUserId(userId.toString());
					if(ucUser!=null){
						String userMobile = ucUser.getMobile();
						taskExecutor.execute(messageConfigManage.getMessageChannelTask(userMobile,GetMessageContext.getListingOvertimeMsg(busiListing, expireTime)));
					}
					//业务员对象
					BossUser salesmanInfo =null;
					//added by biran 20151021  给业务员发送通知短信
					BusiListingSalesman salesman =busiListingSalesmanDao.findBusiListingSalesmanByListingId(listingId);
					if(salesman!=null && salesman.getSalesmanId()!=null  && !salesman.getSalesmanId().equals("")){//有对应业务员时
						salesmanInfo = bossUserDao.getBossUserByUserId(Long.valueOf(salesman.getSalesmanId()));//卖家关联的业务员信息
						if(salesmanInfo.getMobile()!=null && !salesmanInfo.getMobile().equals("")){
							Breed breed=breedDao.selectByPrimaryKey(busiListing.getBreedid());//品种信息
							String realName=ucUserDao.getCertifyNameByUserId(userId);//认证名称
							taskExecutor.execute(messageConfigManage.getMessageChannelTask(salesmanInfo.getMobile(),
								GetMessageContext.getListingOvertimeMsg4SalesMan(busiListing.getListingid(),realName+"("+ucUser.getUserName()+")",breed.getBreedName(),expireTime),
								"挂牌[" + busiListing.getListingid() + "]过期，发送短信通知出错，错误信息是："));
						}
					}
					//added end
					
					//发送邮件通知运营挂牌过期
					taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_LISTING_OVERTIME, GetEmailContext.getJYOperatorEmail(), GetEmailContext.getListingOvertimeEmailMsg(busiListing,ucUser.getUserName(), expireTime,salesmanInfo)));
				} catch (Exception e) {
					log.error("取消挂牌任务失败："+e.getMessage(), e);
				}
			}
		}
		if(listingIds.size() > 0){
			RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, listingIds);
		}
		log.debug("QuartzJob execute 取消挂牌任务执行结束！");
	}
}
