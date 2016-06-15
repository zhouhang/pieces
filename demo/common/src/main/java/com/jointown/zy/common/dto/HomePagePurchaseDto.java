package com.jointown.zy.common.dto;


/**
 * @ClassName: HomePagePurchaseDto
 * @Description: TODO
 * @Author: Calvin.wh
 * @Date: 2015-11-2
 * @Version: 1.0
 */
public class HomePagePurchaseDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	
	private String homePageId;
	
	private String purchaseId;
	
	private String  type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
