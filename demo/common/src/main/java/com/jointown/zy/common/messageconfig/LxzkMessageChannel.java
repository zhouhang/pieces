package com.jointown.zy.common.messageconfig;

import com.jointown.zy.common.util.LxzkSendMessage;

/**
 * @ClassName: LxzkMessageChannel
 * @Description: 凌讯中科短信通道
 * @Author: ldp
 * @Date: 2015年9月10日
 * @Version: 1.0
 */
public class LxzkMessageChannel extends MessageChannel {

	@Override
	public String sendMessage(String mobileNo,String msgContext) {
		return LxzkSendMessage.sendMessage(mobileNo, msgContext);
	}

}
