package com.jointown.zy.common.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.messageconfig.MessageChannel;

/**
 * 短信发送任务线程
 * author ldp
 * 2014年12月24日
 */
public class MessageTaskSend implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(MessageTaskSend.class); 
	
	private String mobileNo;
	private String msg;
	private String[] logMessaagePrefix;
	private MessageChannel msgChannel;
	
	
	public MessageTaskSend() {}

	public MessageTaskSend(String mobileNo, String msg, MessageChannel msgChannel,String...logMessaagePrefix) {
		this.mobileNo = mobileNo;
		this.msg = msg;
		this.logMessaagePrefix = logMessaagePrefix;
		this.msgChannel = msgChannel;
	}

	@Override
	public void run() {
		logger.info("mobiles send message start!");
		try {
			msgChannel.sendMessage(mobileNo, msg);
		} catch (Exception e) {
			logger.error(logMessaagePrefix!=null&&logMessaagePrefix.length>0?logMessaagePrefix[0]:"error is :", e);
		}
		logger.info("mobiles send message end!");
	}
	

	
}
