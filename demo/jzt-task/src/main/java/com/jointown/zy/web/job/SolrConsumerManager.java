package com.jointown.zy.web.job;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.enums.SolrOperationTypeEnum;
import com.jointown.zy.common.rabbitmq.RabbitmqCodecUtil;
import com.jointown.zy.common.rabbitmq.RabbitmqConsumer;
import com.jointown.zy.common.rabbitmq.RabbitmqDelivery;
import com.jointown.zy.common.rabbitmq.SolrMessage;
import com.jointown.zy.common.solr.SolrListingVo;
import com.jointown.zy.common.solr.SolrManager;
import com.jointown.zy.common.solr.SolrPurchaseVo;
import com.jointown.zy.common.solr.SolrManager.SolrCollection;
import com.jointown.zy.common.util.SpringUtil;

/**
 * @ClassName: SolrConsumerManager
 * @Description: solr消费者
 * @Author: 赵航
 * @Date: 2015年4月18日
 * @Version: 1.0
 */
public class SolrConsumerManager extends TimerTask{
	
	private static final Logger logger = LoggerFactory.getLogger(SolrConsumerManager.class);
	
	private RabbitmqConsumer rabbitmqConsumer;
	
	private SolrManager solrManagerListing;
	
	private SolrManager solrManagerPurchase;
	
	private static SolrConsumerManager solrConsumerManager;
	
	/**
	 * @Description: 获取SolrConsumerManager的单实例
	 * @Author: 赵航
	 * @Date: 2015年4月18日
	 * @return
	 */
	public static SolrConsumerManager getInstance(){
		if(solrConsumerManager == null){
			synchronized (SolrConsumerManager.class) {
				if(solrConsumerManager == null){
					solrConsumerManager = new SolrConsumerManager();
				}
			}
		}
		return solrConsumerManager;
	}
	
	private SolrConsumerManager(){
		logger.info("SolrConsumerManager.init...");
		solrManagerListing = ((SolrManager) SpringUtil.getBean("solrManager")).getInstance(SolrCollection.listing);
		solrManagerPurchase = ((SolrManager) SpringUtil.getBean("solrManager")).getInstance(SolrCollection.purchase);
		rabbitmqConsumer = new RabbitmqConsumer("ROUTINGKEY_SOLR,ROUTINGKEY_PURCHASE", "QUEUE_SOLR", "EXCHANGE_DIRECT");
	}

	@Override
	public void run() {
		logger.info("SolrConsumerManager.run...");
		RabbitmqDelivery mqDelivery = rabbitmqConsumer.getMsg();
		if (null != mqDelivery) {
			try {
				if("ROUTINGKEY_SOLR".equals(mqDelivery.getDelivery().getEnvelope().getRoutingKey())){
					SolrMessage message = (SolrMessage) RabbitmqCodecUtil.BytesToObject(mqDelivery.getDelivery().getBody());
					if(SolrOperationTypeEnum.DELETE.equals(message.getOperationType())){
						List<String> list = new ArrayList<String>();
						for(SolrListingVo vo : message.getSolrDataList()){
							list.add(vo.getListingId());
						}
						solrManagerListing.deleteIndexes(list);
					} else if(SolrOperationTypeEnum.ADD.equals(message.getOperationType()) || SolrOperationTypeEnum.UPDATE.equals(message.getOperationType())){
						solrManagerListing.addIndexes(message.getSolrDataList());
					}
				} else if("ROUTINGKEY_PURCHASE".equals(mqDelivery.getDelivery().getEnvelope().getRoutingKey())) {
					@SuppressWarnings("unchecked")
					List<SolrPurchaseVo> list = (List<SolrPurchaseVo>) RabbitmqCodecUtil.BytesToObject(mqDelivery.getDelivery().getBody());
					solrManagerPurchase.addIndexes(list);
				}
			} catch (Exception e) {
				logger.error("execute message to create index is failure, error info is " + e.getMessage());
			}
			rabbitmqConsumer.ack(mqDelivery);
		}
	}

}
