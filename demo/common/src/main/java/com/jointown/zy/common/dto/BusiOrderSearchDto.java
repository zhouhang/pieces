package com.jointown.zy.common.dto;

public class BusiOrderSearchDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderid;
	private String title;
	private Integer orderstate;
	private String startlistingdate;
	private String endlistingdate;

	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(Integer orderstate) {
		this.orderstate = orderstate;
	}
	public String getStartlistingdate() {
		return startlistingdate;
	}
	public void setStartlistingdate(String startlistingdate) {
		this.startlistingdate = startlistingdate;
	}
	public String getEndlistingdate() {
		return endlistingdate;
	}
	public void setEndlistingdate(String endlistingdate) {
		this.endlistingdate = endlistingdate;
	}
}
