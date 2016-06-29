/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.rabbitmq;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


import com.jointown.zy.common.dao.SolrDataDao;
import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.enums.SolrContentTypeEnum;
import com.jointown.zy.common.enums.SolrOperationTypeEnum;
import com.jointown.zy.common.solr.SolrListingVo;
import com.jointown.zy.common.solr.SolrPurchaseVo;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.util.SysLogUtil;

/**
 * @ClassName: RabbitmqProducerManager
 * @Description: Rabbitmq生产者管理类
 * @Author: 赵航
 * @Date: 2015年4月18日
 * @Version: 1.0
 */
public class RabbitmqProducerManager {
	
	//private static final Logger logger = LoggerFactory.getLogger(RabbitmqProducerManager.class);
	
	private RabbitmqProducer rabbitmqProducer;
	
	private ExecutorService executorService = Executors.newCachedThreadPool();
	
	private String exchangeName = "EXCHANGE_DIRECT";
	
	private String solrKey = "ROUTINGKEY_SOLR";
	
	private String apiKey = "ROUTINGKEY_API";
	
	private String purchaseKey = "ROUTINGKEY_PURCHASE";
	
	private static RabbitmqProducerManager rabbitmqProducerManager;
	
	private SolrDataDao solrDataDao;
	
	/**
	 * @Description: 获取生产者管理类的单实例
	 * @Author: 赵航
	 * @Date: 2015年4月18日
	 * @return
	 */
	public static RabbitmqProducerManager getInstance(){
		if(rabbitmqProducerManager == null){
			synchronized (RabbitmqProducerManager.class) {
				if(rabbitmqProducerManager == null){
					rabbitmqProducerManager = new RabbitmqProducerManager();
				}
			}
		}
		return rabbitmqProducerManager;
	}
	
	private RabbitmqProducerManager(){
		solrDataDao = (SolrDataDao) SpringUtil.getBean(SolrDataDao.class);
		rabbitmqProducer = new RabbitmqProducer(exchangeName);
	}
	
	/**
	 * @Description: 生产者发送solr消息
	 * @Author: 赵航
	 * @Date: 2015年4月18日
	 * @param operationType 操作类型
	 * @param contentType 内容类型
	 * @param content 实际内容（仓单ID，挂牌ID，品种ID等）
	 */
	public void pushMsgForSolr(SolrOperationTypeEnum operationType, SolrContentTypeEnum contentType, String content){
		List<String> contentList = new ArrayList<String>();
		contentList.add(content);
		pushMsgForSolr(operationType, contentType, contentList);
	}
	
	/**
	 * @Description: 生产者发送solr消息
	 * @Author: 赵航
	 * @Date: 2015年4月18日
	 * @param operationType 操作类型
	 * @param contentType 内容类型
	 * @param contentList 实际内容（仓单ID，挂牌ID，品种ID集合等）
	 */
	public void pushMsgForSolr(SolrOperationTypeEnum operationType, SolrContentTypeEnum contentType, List<String> contentList){
		pushMsgForSolr(operationType, contentType, contentList, solrKey);
	}
	
	private void pushMsgForSolr(final SolrOperationTypeEnum operationType, final SolrContentTypeEnum contentType,
			final List<String> contentList, final String routingKey){
		if(operationType == null || contentType == null || contentList == null || contentList.size() <= 0 || StringUtils.isEmpty(routingKey)){
			throw new RuntimeException("args cound not be empty");
		}
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				List<SolrListingVo> list = null;
				switch (operationType) {
				case ADD : {
					try {
						if(SolrContentTypeEnum.LISTING.equals(contentType)){
							list = solrDataDao.selectByListingIds(contentList);
						}
					} catch (Exception e) {
						SysLogUtil.logForSolr(operationType.getName() +"|"+ contentType.getName() +"|"+ contentList.toString());
					}
					break;
				}
				case UPDATE : {
					try{
						if(SolrContentTypeEnum.LISTING.equals(contentType)){
							list = solrDataDao.selectByListingIds(contentList);
						}
						if(SolrContentTypeEnum.WHLIST.equals(contentType)){
							list = solrDataDao.selectByWlIds(contentList);
						}
						if(SolrContentTypeEnum.BREED.equals(contentType)){
							list = solrDataDao.selectByBreedIds(contentList);
						}
						if(SolrContentTypeEnum.WLUNIT.equals(contentType)){
							list = solrDataDao.selectByWlunits(contentList);
						}
					} catch (Exception e) {
						SysLogUtil.logForSolr(operationType.getName() +"|"+ contentType.getName() +"|"+ contentList.toString());
					}
					break;
				}
				case DELETE : {
					list = new ArrayList<SolrListingVo>();
					for(String str : contentList){
						SolrListingVo vo = new SolrListingVo();
						vo.setListingId(str);
						list.add(vo);
					}
					break;
				}
				default:
					break;
				}
				if(list != null && list.size() > 0){
					for(SolrListingVo item : list){
						StringBuffer sbf = new StringBuffer();
						sbf.append(operationType.getName() + "|");
						sbf.append(contentType.getName()+ "|");
						sbf.append(GsonFactory.createGson("yyyy-MM-dd HH:mm:ss").toJson(item));
						SysLogUtil.logForSolr(sbf.toString());
					}
					SolrMessage message = new SolrMessage(operationType, contentType, list);
					rabbitmqProducer.putMsg(RabbitmqCodecUtil.ObjectToBytes(message), routingKey);
				}
			}
		});
	}
	
	/**
	 * @Description: 生产者发送WMS或供应链金融消息
	 * @Author: 赵航
	 * @Date: 2015年4月18日
	 * @param apiFlag 接口标识
	 * @param apiJsonData 消息内容
	 */
	public void putMsgForApi(ApiFlagEnums apiFlag, String apiJsonData){
		putMsgForApi(apiFlag, apiJsonData, apiKey);
	}
	
	private void putMsgForApi(final ApiFlagEnums apiFlag, final String apiJsonData, final String routingKey){
		if(apiFlag == null || StringUtils.isEmpty(apiJsonData) || StringUtils.isEmpty(routingKey)){
			throw new RuntimeException("args cound not be empty");
		}
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				ApiJsonMessage message = new ApiJsonMessage(apiFlag, apiJsonData);
				rabbitmqProducer.putMsg(RabbitmqCodecUtil.ObjectToBytes(message), routingKey);
			}
		});
	}
	
	/**
	 * @Description: 发送采购信息
	 * @Author: 赵航
	 * @Date: 2015年10月15日
	 * @param purchaseId 单条发送
	 */
	public void pushPurchaseMsgForSolr(String purchaseId){
		List<String> purchaseIds = new ArrayList<String>();
		purchaseIds.add(purchaseId);
		pushPurchaseMsgForSolr(purchaseIds);
	}
	
	/**
	 * @Description: 发送采购信息
	 * @Author: 赵航
	 * @Date: 2015年10月15日
	 * @param purchaseIds 多条同时发送
	 */
	public void pushPurchaseMsgForSolr(List<String> purchaseIds){
		pushPurchaseMsgForSolr(SolrContentTypeEnum.PURCHASE,purchaseIds, purchaseKey);
	}
	
	/**
	 * 
	 * @Description: 发送采购信息
	 * @Author: 刘漂
	 * @Date: 2015年10月21日
	 * @param purchaseIds
	 */
	public void pushPurchaseMsgForSolrByPurchaseCodes(List<String> purchaseCodes){
		pushPurchaseMsgForSolr(SolrContentTypeEnum.PURCHASE_BATCH,purchaseCodes, purchaseKey);
	}
	
	private void pushPurchaseMsgForSolr(final SolrContentTypeEnum type,final List<String> identities, final String routingKey){
		if(identities == null || identities.size() <= 0 || StringUtils.isEmpty(routingKey)){
			throw new RuntimeException("args cound not be empty");
		}
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				List<SolrPurchaseVo> list = null;
				try {
					switch(type){
					case PURCHASE:		list = solrDataDao.selectByPurchaseIds(identities);break;
					case PURCHASE_BATCH:list = solrDataDao.selectByPurchaseCodes(identities);break;
					}
				} catch (Exception e) {
					//SysLogUtil.logForSolr(purchaseIds.toString());
				}
				if(list != null && list.size() > 0){
//					for(SolrPurchaseVo item : list){
//						StringBuffer sbf = new StringBuffer();
//						sbf.append(GsonFactory.createGson("yyyy-MM-dd HH:mm:ss").toJson(item));
//						SysLogUtil.logForSolr(sbf.toString());
//					}
					rabbitmqProducer.putMsg(RabbitmqCodecUtil.ObjectToBytes(list), routingKey);
				}
			}
		});
	}
	
}
