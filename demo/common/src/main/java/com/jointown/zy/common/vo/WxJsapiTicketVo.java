package com.jointown.zy.common.vo;

/**
 * 调用微信JS接口的临时票据
 * 
 * @author aizhengdong
 * @date 2015年10月12日
 */
public class WxJsapiTicketVo {

	/** 获取到的凭证 */
	private String ticket;

	/** 凭证有效时间，单位：秒 */
	private int expiresIn;

	/** 创建时间 */
	private long createTime;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

}
