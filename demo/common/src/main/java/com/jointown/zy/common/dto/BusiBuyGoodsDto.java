package com.jointown.zy.common.dto;

public class BusiBuyGoodsDto extends BaseDto{

	private static final long serialVersionUID = 5430961590351211397L;
	
	/** 购买单价 */
	private String price;
	
	/** 购买数量 */
	private String amount;
	
	/** 挂牌者ID */
	private Long listingerId;
	
	/** 购买者ID */
	private Long buyerId;
	
	/** 挂牌ID */
	private String listingId;
	
	/** 仓单ID */
	private String wlid;
	
	/** 订单总价 */
	private String totalPrice;
	
	/** 保证金 add by guoyb 2015.4.7 16:15*/
	private String deposit;
	
	/** 品种ID add by Mr.songwei 2015.1.13 16:38 方便solr搜索*/  
	private Long breedcode;
	
	/** 是否开具发票 */
	private String isneedBill;

	public String getIsneedBill() {
		return isneedBill;
	}

	public void setIsneedBill(String isneedBill) {
		this.isneedBill = isneedBill;
	}

	public Long getBreedcode() {
		return breedcode;
	}

	public void setBreedcode(Long breedcode) {
		this.breedcode = breedcode;
	}

	/**
	 * 取得购买单价
	 * @return 购买单价
	 */
	public String getPrice() {
	    return price;
	}

	/**
	 * 设定购买单价
	 * @param price 购买单价
	 */
	public void setPrice(String price) {
	    this.price = price;
	}

	/**
	 * 取得购买数量
	 * @return 购买数量
	 */
	public String getAmount() {
	    return amount;
	}

	/**
	 * 设定购买数量
	 * @param amount 购买数量
	 */
	public void setAmount(String amount) {
	    this.amount = amount;
	}

	/**
	 * 取得挂牌者ID
	 * @return 挂牌者ID
	 */
	public Long getListingerId() {
	    return listingerId;
	}

	/**
	 * 设定挂牌者ID
	 * @param listingerId 挂牌者ID
	 */
	public void setListingerId(Long listingerId) {
	    this.listingerId = listingerId;
	}

	/**
	 * 取得购买者ID
	 * @return 购买者ID
	 */
	public Long getBuyerId() {
	    return buyerId;
	}

	/**
	 * 设定购买者ID
	 * @param buyerId 购买者ID
	 */
	public void setBuyerId(Long buyerId) {
	    this.buyerId = buyerId;
	}

	/**
	 * 取得挂牌ID
	 * @return 挂牌ID
	 */
	public String getListingId() {
	    return listingId;
	}

	/**
	 * 设定挂牌ID
	 * @param listingId 挂牌ID
	 */
	public void setListingId(String listingId) {
	    this.listingId = listingId;
	}

	/**
	 * 取得仓单ID
	 * @return 仓单ID
	 */
	public String getWlid() {
	    return wlid;
	}

	/**
	 * 设定仓单ID
	 * @param wlid 仓单ID
	 */
	public void setWlid(String wlid) {
	    this.wlid = wlid;
	}

	/**
	 * 取得订单总价
	 * @return 订单总价
	 */
	public String getTotalPrice() {
	    return totalPrice;
	}

	/**
	 * 设定订单总价
	 * @param totalPrice 订单总价
	 */
	public void setTotalPrice(String totalPrice) {
	    this.totalPrice = totalPrice;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

}
