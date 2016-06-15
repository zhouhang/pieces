package com.jointown.zy.common.vo;

import java.io.Serializable;
/**
 * @ClassName: HomePagePurchaseVo
 * @Description: 首页大货采购
 * @Author: Calvin.wh
 * @Date: 2015-11-2
 * @Version: 1.0
 */
public class HomePagePurchaseVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String homePageId;
	private String purchaseId;
	private String purchaser;
	private String type;
	private String sortNo;
	
	public String getHomePageId() {
		return homePageId;
	}
	public void setHomePageId(String homePageId) {
		this.homePageId = homePageId;
	}
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getPurchaser() {
		return purchaser;
	}
	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSortNo() {
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
}
