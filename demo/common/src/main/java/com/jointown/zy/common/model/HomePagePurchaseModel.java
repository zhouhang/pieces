package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: HomePagePurchaseModel
 * @Description: 首页大货采购维护
 * @Author: Calvin.wh
 * @Date: 2015-11-2
 * @Version: 1.0
 */
public class HomePagePurchaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer homePageId;
	//采购订单编号
	private String purchaseId;
	private Integer type;
	private Integer sortNo;
	private Integer status;
	private Date createTime;
	private Date updateTime;
	private Integer creater;
	private Integer updater;

	public Integer getHomePageId() {
		return homePageId;
	}

	public void setHomePageId(Integer homePageId) {
		this.homePageId = homePageId;
	}

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Integer getUpdater() {
		return updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

}
