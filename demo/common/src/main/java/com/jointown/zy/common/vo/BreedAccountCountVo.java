package com.jointown.zy.common.vo;

import java.io.Serializable;

/**
 * 品种账务统计 查询结果统计VO
 * 
 * @ClassName:BreedAccountCountVo
 * @author:Calvin.Wangh
 * @date:2015-10-12下午4:22:41
 * @version V1.0
 * @Description:
 */
public class BreedAccountCountVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//仓单总量统计
	private String whlistCount;
	//挂牌总量统计
	private String listingCount;
	//交易总量统计
	private String orderCount;
	
	public String getWhlistCount() {
		return whlistCount;
	}
	public void setWhlistCount(String whlistCount) {
		this.whlistCount = whlistCount;
	}
	public String getListingCount() {
		return listingCount;
	}
	public void setListingCount(String listingCount) {
		this.listingCount = listingCount;
	}
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
}
