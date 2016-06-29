package com.jointown.zy.common.vo;

/**
 * 微信公众平台开发--接收消息的基类
 * 
 * @author aizhengdong
 *
 * @data 2015年2月10日
 */
public class WxReqBaseMessageVo {
	/** 开发者微信号 */
	private String ToUserName;
	
	/** 发送方帐号 */
	private String FromUserName;
	
	/** 消息创建时间 （整型） */
	private long CreateTime;
	
	/** 消息类型 */
	private String MsgType;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
	
}
