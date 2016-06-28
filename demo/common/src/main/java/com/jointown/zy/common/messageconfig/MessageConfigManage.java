package com.jointown.zy.common.messageconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jointown.zy.common.model.MessageConfig;
import com.jointown.zy.common.service.MessageConfigService;
import com.jointown.zy.common.task.MessageTaskSend;

/**
 * @ClassName: MessageConfigManage
 * @Description: 短信通道配置管理
 * @Author: ldp
 * @Date: 2015年9月7日
 * @Version: 1.0
 */
@Component("MessageConfigManage")
public class MessageConfigManage {

	private static final Logger log = LoggerFactory.getLogger(MessageConfigManage.class);
	
	public static final int QINXINTONG_ID = 1;//企信通,默认通道
	public static final int LXZK_ID = 2;//凌汛中科
	
	public static final String HEADSIGN = "【珍药材】";//短信签名
	
	@Autowired
	private MessageConfigService messageConfigService;
	/**短信通道*/
	private MessageChannel msgChannel = null;
	
	/**
	 * @Description: 获取通道ID
	 * @Author: ldp
	 * @Date: 2015年9月8日
	 * @return
	 */
	public int getChaneelId(){
		try {
			MessageConfig mc = messageConfigService.getAvailableChannel();
			if (null == mc) {
				return QINXINTONG_ID;//默认企信通
			}
			return mc.getConfigId().intValue();
		} catch (Exception e) {
			log.error("MessageConfigManage.getChaneelId error is:", e);
			return QINXINTONG_ID;
		}
		
	}
	
	/**
	 * @Description: 获取发短信任务
	 * @Author: ldp
	 * @Date: 2015年9月8日
	 * @param mobileNo
	 * @param msg
	 * @param logMessaagePrefix
	 * @return
	 */
	public MessageTaskSend getMessageChannelTask(String mobileNo, String msg, String...logMessaagePrefix){
		return new MessageTaskSend(mobileNo, doneMsgContext(msg), getMsgChannel(), logMessaagePrefix);
	}
	
	/**
	 * @Description: 获取短信通道对象
	 * @Author: ldp
	 * @Date: 2015年9月14日
	 * @return
	 */
	private MessageChannel getMsgChannel(){
		if (getChaneelId() == QINXINTONG_ID) {
			msgChannel = new QxtNewMessageChannel();
			//msgChannel = new QxtMessageChannel();
		}else if (getChaneelId() == LXZK_ID) {
			msgChannel = new LxzkMessageChannel();
		}
		return msgChannel;
	}
	
	/**
	 * @Description: 短信内容处理(不同的通道加不同的前缀和后缀)
	 * @Author: ldp
	 * @Date: 2015年9月14日
	 * @param msgContext
	 * @return
	 */
	private String doneMsgContext(String msgContext){
		return new StringBuilder(HEADSIGN).append(msgContext).toString();
	}
	
}
