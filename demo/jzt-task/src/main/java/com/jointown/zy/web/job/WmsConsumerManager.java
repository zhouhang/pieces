package com.jointown.zy.web.job;

import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.rabbitmq.ApiJsonMessage;
import com.jointown.zy.common.rabbitmq.RabbitmqCodecUtil;
import com.jointown.zy.common.rabbitmq.RabbitmqConsumer;
import com.jointown.zy.common.rabbitmq.RabbitmqDelivery;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.wms.WmsApi;
import com.jointown.zy.common.wms.WmsApiCommon;

/**
 * @ClassName: WmsConsumerManager
 * @Description: WMS消费者
 * @Author: 赵航
 * @Date: 2015年4月18日
 * @Version: 1.0
 */
public class WmsConsumerManager extends TimerTask{
	
	private static final Logger logger = LoggerFactory.getLogger(WmsConsumerManager.class);
	
	private RabbitmqConsumer rabbitmqConsumer;
	
	private WmsApi wmsApi;
	
	private static WmsConsumerManager wmsConsumerManager;
	
	/**
	 * @Description: 获取WmsConsumerManager单实例
	 * @Author: 赵航
	 * @Date: 2015年4月18日
	 * @return
	 */
	public static WmsConsumerManager getInstance(){
		if(wmsConsumerManager == null){
			synchronized (WmsConsumerManager.class) {
				if(wmsConsumerManager == null){
					wmsConsumerManager = new WmsConsumerManager();
				}
			}
		}
		return wmsConsumerManager;
	}
	
	private WmsConsumerManager(){
		wmsApi = (WmsApi) SpringUtil.getBean("wmsApi");
		rabbitmqConsumer = new RabbitmqConsumer("ROUTINGKEY_API", "QUEUE_WMS", "EXCHANGE_DIRECT");
		logger.info("WmsConsumerManager init! Key is:ROUTINGKEY_API, QUEUE is:ROUTINGKEY_API");
	}

	@Override
	public void run() {
		RabbitmqDelivery mqDelivery = rabbitmqConsumer.getMsg();
		if (null != mqDelivery) {
			int num=0;
			ApiJsonMessage message = (ApiJsonMessage) RabbitmqCodecUtil.BytesToObject(mqDelivery.getDelivery().getBody());
			switch (message.getApiFlag()) {
				case UC_USER_ADD:{
					num = wmsApi.wmsConsumer(message.getApiFlag(), WmsApiCommon.WMS_ADD_USER_URL, message.getApiJsonData(),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
					break;
				}
				case UC_USER_UPDATE:{
					num = wmsApi.wmsConsumer(message.getApiFlag(), WmsApiCommon.WMS_UPDATE_USER_URL, message.getApiJsonData(),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
					break;
				}
				case BOSS_USER_ADD:{
					num = wmsApi.wmsConsumer(message.getApiFlag(), WmsApiCommon.WMS_BOSS_USER_ADD_URL, message.getApiJsonData(),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
					break;
				}
				case BOSS_USER_UPDATE:{
					num = wmsApi.wmsConsumer(message.getApiFlag(), WmsApiCommon.WMS_BOSS_USER_UPDATE_URL, message.getApiJsonData(),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
					break;
				}
				case BREED_ADD:{
					num = wmsApi.wmsConsumer(message.getApiFlag(), WmsApiCommon.WMS_BREED_ADD_URL, message.getApiJsonData(),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
					break;
				}
				case BREED_UPDATE:{
					num = wmsApi.wmsConsumer(message.getApiFlag(), WmsApiCommon.WMS_BREED_UPDATE_URL, message.getApiJsonData(),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
					break;
				}
				default:
					break;
			}
			if(num==0){
				rabbitmqConsumer.fail(mqDelivery);	
			}else if(num==1){
			rabbitmqConsumer.ack(mqDelivery);
			}
		}
	}

}
