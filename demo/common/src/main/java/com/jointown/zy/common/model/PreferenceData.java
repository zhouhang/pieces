/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.model;

/**
 * @ClassName: PrefenrenceData
 * @Description: TODO
 * @Author: robin.liu
 * @Date: 2015年8月18日
 * @Version: 1.0
 */
public class PreferenceData {
	
	private Long userId;
	private Long itemId;
	private Long times;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getTimes() {
		return times;
	}
	public void setTimes(Long times) {
		this.times = times;
	}

}
