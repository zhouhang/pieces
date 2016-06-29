package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.BusiOrderStateEnum;

/**
 * 
 * 描述： 订单表的映射对象基础模型
 * 
 * 日期： 2014年12月31日
 * 
 * 作者： 赵航
 *
 * 版本： V1.0
 */
public class BusiOrder implements Serializable{
	
	private static final long serialVersionUID = 6456058298767940887L;

	/** 订单ID */
	private String orderid;
	
	/** 挂牌者ID */
	private Long userid;
	
	/** 购买者ID */
	private Long buyer;
	
	/** 仓单ID */
	private String wlid;
	
	/** 挂牌ID */
	private String listingid;
	
	/** 单价 */
	private BigDecimal unitprice;
	
	/** 数量 */
	private BigDecimal amount;
	
	/** 保证金 */
	private BigDecimal deposit;
	
	/** 实际付款 */
	private BigDecimal actualPayment;
	
	/** 成交数量 */
	private BigDecimal volume;
	
	/** 订单总价 */
	private BigDecimal totalprice;
	
	/** 优惠总价 */
	private BigDecimal discountprice;
	
	/** 订单状态 */
	private Integer orderstate;
	
	/** 异常申诉状态 */
	private Integer appealstate;
	
	/** 创建日期 */
	private Date createtime;
	
	/** 修改日期 */
	private Date updatetime;
	
	/** 是否需要发票*/
	private Short hasbill;
	
	/** 订单过期时间 */
	private Date expiretime;
	
//	<!-- add by fanyuna 2015.09.07 增加订单类型、分割标识  start-->
	
	private Short orderType;
	
	private Short splitFlag;
	
//	<!-- add by fanyuna 2015.09.07 增加订单类型、分割标识  end-->
	
	private BusiListing listingEntity;

	/**
	 * 
	 */
	public BusiOrder() {}
	
	public BusiOrder(String orderId){
		this.orderid = orderId;
	}
	
	/**
	 * 
	 * @Description: 订单支付保证金后
	 * @Author: robin.liu
	 * @Date: 2015年8月26日
	 * @return
	 */
	public BusiOrder depositPaid() throws Exception{
		if(listingEntity!=null){
			if(BusiListingFlagEnum.LISTING_CANCEL.getCode()
					.equals(String.valueOf(listingEntity.getListingflag())) ){//挂牌取消
				if(listingEntity.getWhListEntity()!=null){
					listingEntity.getWhListEntity().minusSurplus(getAmount());
				}
			}else{
				listingEntity.minusSurplus(getAmount());
			}
		}
		return this;
	}
	
	/**
	 * 
	 * @Description: 关闭订单
	 * @Author: robin.liu
	 * @Date: 2015年8月26日
	 */
	public BusiOrder closeOrder(){
		setOrderstate(Integer.parseInt(BusiOrderStateEnum.CLOSED_ORDER.getCode()));
		setUpdatetime(new Date());
		return this;
	}
	
	/**
	 * 设定订单ID
	 * @param orderid 订单ID
	 */
	public BusiOrder setOrderid(String orderid) {
	    this.orderid = orderid;
	    return this;
	}

	/**
	 * 获取订单ID
	 * @return 订单ID
	 */
	public String getOrderid() {
	    return orderid;
	}

	/**
	 * 设定挂牌者ID
	 * @param userid 挂牌者ID
	 */
	public BusiOrder setUserid(Long userid) {
	    this.userid = userid;
	    return this;
	}

	/**
	 * 获取挂牌者ID
	 * @return 挂牌者ID
	 */
	public Long getUserid() {
	    return userid;
	}

	/**
	 * 设定购买者ID
	 * @param buyer 购买者ID
	 */
	public BusiOrder setBuyer(Long buyer) {
	    this.buyer = buyer;
	    return this;
	}

	/**
	 * 获取购买者ID
	 * @return 购买者ID
	 */
	public Long getBuyer() {
	    return buyer;
	}

	/**
	 * 设定仓单ID
	 * @param wlid 仓单ID
	 */
	public BusiOrder setWlid(String wlid) {
	    this.wlid = wlid;
	    return this;
	}

	/**
	 * 获取仓单ID
	 * @return 仓单ID
	 */
	public String getWlid() {
	    return wlid;
	}

	/**
	 * 设定挂牌ID
	 * @param listingid 挂牌ID
	 */
	public BusiOrder setListingid(String listingid) {
	    this.listingid = listingid;
	    return this;
	}

	/**
	 * 获取挂牌ID
	 * @return 挂牌ID
	 */
	public String getListingid() {
	    return listingid;
	}

	/**
	 * 设定单价
	 * @param unitprice 单价
	 */
	public BusiOrder setUnitprice(BigDecimal unitprice) {
	    this.unitprice = unitprice;
	    return this;
	}

	/**
	 * 获取单价
	 * @return 单价
	 */
	public BigDecimal getUnitprice() {
	    return unitprice;
	}

	/**
	 * 设定数量
	 * @param amount 数量
	 */
	public BusiOrder setAmount(BigDecimal amount) {
	    this.amount = amount;
	    return this;
	}

	/**
	 * 获取数量
	 * @return 数量
	 */
	public BigDecimal getAmount() {
	    return amount;
	}

	/**
	 * 设定保证金
	 * @param deposit 保证金
	 */
	public BusiOrder setDeposit(BigDecimal deposit) {
	    this.deposit = deposit;
	    return this;
	}

	/**
	 * 获取保证金
	 * @return 保证金
	 */
	public BigDecimal getDeposit() {
	    return deposit;
	}

	/**
	 * 设定订单总价
	 * @param totalprice 订单总价
	 */
	public BusiOrder setTotalprice(BigDecimal totalprice) {
	    this.totalprice = totalprice;
	    return this;
	}

	/**
	 * 获取实际付款
	 * @return 实际付款
	 */
	public BigDecimal getActualPayment() {
	    return actualPayment;
	}

	/**
	 * 设定实际付款
	 * @param actualPayment 实际付款
	 */
	public BusiOrder setActualPayment(BigDecimal actualPayment) {
	    this.actualPayment = actualPayment;
	    return this;
	}

	/**
	 * 获取成交数量
	 * @return 成交数量
	 */
	public BigDecimal getVolume() {
	    return volume;
	}

	/**
	 * 设定成交数量
	 * @param volume 成交数量
	 */
	public BusiOrder setVolume(BigDecimal volume) {
	    this.volume = volume;
	    return this;
	}

	/**
	 * 获取订单总价
	 * @return 订单总价
	 */
	public BigDecimal getTotalprice() {
	    return totalprice;
	}

	/**
	 * 设定优惠总价
	 * @param discountprice 优惠总价
	 */
	public BusiOrder setDiscountprice(BigDecimal discountprice) {
	    this.discountprice = discountprice;
	    return this;
	}

	/**
	 * 获取优惠总价
	 * @return 优惠总价
	 */
	public BigDecimal getDiscountprice() {
	    return discountprice;
	}

	/**
	 * 设定订单状态
	 * @param orderstate 订单状态
	 */
	public BusiOrder setOrderstate(Integer orderstate) {
	    this.orderstate = orderstate;
	    return this;
	}

	/**
	 * 获取订单状态
	 * @return 订单状态
	 */
	public Integer getOrderstate() {
	    return orderstate;
	}

	/**
	 * 设定异常申诉状态
	 * @param appealstate 异常申诉状态
	 */
	public BusiOrder setAppealstate(Integer appealstate) {
	    this.appealstate = appealstate;
	    return this;
	}

	/**
	 * 获取异常申诉状态
	 * @return 异常申诉状态
	 */
	public Integer getAppealstate() {
	    return appealstate;
	}

	/**
	 * 设定创建日期
	 * @param createtime 创建日期
	 */
	public BusiOrder setCreatetime(Date createtime) {
	    this.createtime = createtime;
	    return this;
	}

	/**
	 * 获取创建日期
	 * @return 创建日期
	 */
	public Date getCreatetime() {
	    return createtime;
	}

	/**
	 * 设定修改日期
	 * @param updatetime 修改日期
	 */
	public BusiOrder setUpdatetime(Date updatetime) {
	    this.updatetime = updatetime;
	    return this;
	}
	
	public BusiOrder renewUpdateTime(){
		this.updatetime = new Date();
		return this;
	}

	/**
	 * 获取修改日期
	 * @return 修改日期
	 */
	public Date getUpdatetime() {
	    return updatetime;
	}

	/**
	 * 获取是否需要发票
	 * @return 是否需要发票
	 */
	public Short getHasbill() {
		return hasbill;
	}

	/**
	 * 设定是否需要发票
	 * @param hasbill 是否需要发票
	 */
	public BusiOrder setHasbill(Short hasbill) {
		this.hasbill = hasbill;
		return this;
	}

	/**
	 * 获取订单过期时间
	 * @return 订单过期时间
	 */
	public Date getExpiretime() {
		return expiretime;
	}

	/**
	 * 设定订单过期时间
	 * @param expiretime 订单过期时间
	 */
	public BusiOrder setExpiretime(Date expiretime) {
		this.expiretime = expiretime;
		return this;
	}

	public BusiListing getListingEntity() {
		return listingEntity;
	}

	public BusiOrder setListingEntity(BusiListing listingEntity) {
		this.listingEntity = listingEntity;
		return this;
	}

	public Short getOrderType() {
		return orderType;
	}

	public void setOrderType(Short orderType) {
		this.orderType = orderType;
	}

	public Short getSplitFlag() {
		return splitFlag;
	}

	public void setSplitFlag(Short splitFlag) {
		this.splitFlag = splitFlag;
	}
	
}
