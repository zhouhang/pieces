package com.jointown.zy.common.messageconfig;

import com.jointown.zy.common.util.MessageSend;

/**
 * @ClassName: QxtMessageChannel
 * @Description: 企信通短信通道
 * @Author: ldp
 * @Date: 2015年9月10日
 * @Version: 1.0
 */
public class QxtMessageChannel extends MessageChannel {

	@Override
	public String sendMessage(String mobileNo, String msgContext) {
		return MessageSend.sendMessage(mobileNo, msgContext);
	}

}
