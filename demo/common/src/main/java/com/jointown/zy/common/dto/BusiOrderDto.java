package com.jointown.zy.common.dto;

/**
 * @ClassName: BusiOrderDto
 * @Description: 定单dto查询使用
 * @updater: guoyb
 * @Date: 2015年4月9日
 * @Version: 1.0
 */
public class BusiOrderDto extends BaseDto{

	private static final long serialVersionUID = -5156435685367607515L;
	
	/** 订购时间(起) */
	private String orderStartDate;
	
	/** 订购时间(止) */
	private String orderEndDate;
	
	/** 订购状态 */
	private Integer orderstate;
	
	/** 订单ID */
	private String orderid;
	
	/** 商品标题 */
	private String title;
	
	/** 挂牌者ID */
	private Long userid;
	
	/** 购买者ID */
	private Long buyer;
	
	/** 订购价格起 ,查询价格区间用*/
	private String orderStartPrice;
	
	/** 订购价格止,查询价格区间用 */
	private String orderEndPrice;
	/******add by ldp 2015-08-18*******************/
	/** 卖方订单-品种查询条件  */
	private String breedName;
	
	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}

	public String getBreedName() {
		return breedName;
	}

	public String getOrderStartPrice() {
		return orderStartPrice;
	}

	public void setOrderStartPrice(String orderStartPrice) {
		this.orderStartPrice = orderStartPrice;
	}

	public String getOrderEndPrice() {
		return orderEndPrice;
	}

	public void setOrderEndPrice(String orderEndPrice) {
		this.orderEndPrice = orderEndPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 取得订购时间(起)
	 * @return 订购时间(起)
	 */
	public String getOrderStartDate() {
	    return orderStartDate;
	}

	/**
	 * 设定订购时间(起)
	 * @param orderStartDate 订购时间(起)
	 */
	public void setOrderStartDate(String orderStartDate) {
	    this.orderStartDate = orderStartDate;
	}

	/**
	 * 取得订购时间(止)
	 * @return 订购时间(止)
	 */
	public String getOrderEndDate() {
	    return orderEndDate;
	}

	/**
	 * 设定订购时间(止)
	 * @param orderEndDate 订购时间(止)
	 */
	public void setOrderEndDate(String orderEndDate) {
	    this.orderEndDate = orderEndDate;
	}

	/**
	 * 取得订购状态
	 * @return 订购状态
	 */
	public Integer getOrderstate() {
	    return orderstate;
	}

	/**
	 * 设定订购状态
	 * @param orderstate 订购状态
	 */
	public void setOrderstate(Integer orderstate) {
		this.orderstate = orderstate;	
	}

	
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	/**
	 * 取得商品标题
	 * @return 商品标题
	 */
	public String getTitle() {
	    return title;
	}

	/**
	 * 设定商品标题
	 * @param title 商品标题
	 */
	public void setTitle(String title) {
	    this.title = title;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	/**
	 * 取得购买者ID
	 * @return 购买者ID
	 */
	public Long getBuyer() {
	    return buyer;
	}

	/**
	 * 设定购买者ID
	 * @param buyer 购买者ID
	 */
	public void setBuyer(Long buyer) {
	    this.buyer = buyer;
	}

}
