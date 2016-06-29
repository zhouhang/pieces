package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.util.TimeUtil;

/**
 * @ClassName: BusiOrderVo
 * @Description: 订单VO
 * @Author: guoyb
 * @Date: 2015年4月20日
 * @Version: 1.0
 */
public class BusiOrderVo implements Serializable{

	private static final long serialVersionUID = -3953799272934543261L;
	
	private static final Logger logger = LoggerFactory.getLogger(BusiOrderVo.class);
	
	/** 订单编号 */
	private String orderid;
	
	/** 挂牌编号 */
	private String listingid;
	
	/** 单价 */
	private BigDecimal unitprice;
	
	/** 订单数量 */
	private BigDecimal amount;
	
	/** 订单总价 */
	private BigDecimal totalprice;
	
	/** 实际付款 */
	private BigDecimal discountprice;
	
	/** 挂牌标题 */
	private String title;
	
	/** 计量单位 */
	private String wlunit;
	
	/** 订单状态 */
	private Integer orderstate;
	
	/** 订购日期 */
	private Date createtime;
	
	/** 散货图片 */
	private String path;
	
	/** 挂牌者姓名 */
	private String username;
	
	/** 挂牌者id*/
	private Integer userId;
	
	/** 购买者姓名 */
	private String buyername;
	
	/** 摘牌者id*/
	private Integer buyerId;

	/** 操作修改时间 */
	private Date updatetime;
	
	/** 实际付款 */
	private BigDecimal actualPayment;
	
	/** 成交数量 */
	private BigDecimal volume;
	
	/** 是否有申诉 */
	private Integer appealState;
	
	/** 保证金 */
	private BigDecimal deposit;
	
	/** 尾款金额*/
	private BigDecimal finalPayment;
	
	/** 退款金额*/
	private BigDecimal remitPayment;
	
	/** 订单过期时间*/
	private Date expiretime;
	
	/** 订单取消原因 */
	private String orderCancelReasean;
	
	/** 品种名称 */
	private String breedName;
	
	/** 品种规格 */
	private String grade;
	
	/** 申述审核状态 */
	private Integer examineState;
	
	/** 订单类型*/
	private Short orderType;
	/** 是否分割*/
	private Short splitFlag;
	
	/** 账期订单开始日期 */
	private Date startTime;
	/** 账期订单结束日期 */
	private Date endTime;

	/** 账期订单延期天数*/
	private String dataDiff;
	
	//add by Mr.song 仓单id
	private String wlid;
	
	public String getWlid() {
		return wlid;
	}

	public void setWlid(String wlid) {
		this.wlid = wlid;
	}
	
	/**
	 * 取得 订单编号
	 * @return 订单编号
	 */
	public String getOrderid() {
	    return orderid;
	}

	/**
	 * 設定 订单编号
	 * @param orderid 订单编号
	 */
	public void setOrderid(String orderid) {
	    this.orderid = orderid;
	}

	/**
	 * 取得 挂牌编号
	 * @return 挂牌编号
	 */
	public String getListingid() {
	    return listingid;
	}

	/**
	 * 設定 挂牌编号
	 * @param listingid 挂牌编号
	 */
	public void setListingid(String listingid) {
	    this.listingid = listingid;
	}

	/**
	 * 取得 单价
	 * @return 单价
	 */
	public BigDecimal getUnitprice() {
	    return unitprice;
	}

	/**
	 * 設定 单价
	 * @param unitprice 单价
	 */
	public void setUnitprice(BigDecimal unitprice) {
	    this.unitprice = unitprice;
	}

	/**
	 * 取得 订单数量
	 * @return 订单数量
	 */
	public BigDecimal getAmount() {
	    return amount;
	}

	/**
	 * 設定 订单数量
	 * @param amount 订单数量
	 */
	public void setAmount(BigDecimal amount) {
	    this.amount = amount;
	}

	/**
	 * 取得 订单总价
	 * @return 订单总价
	 */
	public BigDecimal getTotalprice() {
	    return totalprice;
	}

	/**
	 * 設定 订单总价
	 * @param totalprice 订单总价
	 */
	public void setTotalprice(BigDecimal totalprice) {
	    this.totalprice = totalprice;
	}

	/**
	 * 取得 实际付款
	 * @return 实际付款
	 */
	public BigDecimal getDiscountprice() {
	    return discountprice;
	}

	/**
	 * 設定 实际付款
	 * @param discountprice 实际付款
	 */
	public void setDiscountprice(BigDecimal discountprice) {
	    this.discountprice = discountprice;
	}

	/**
	 * 取得 挂牌标题
	 * @return 挂牌标题
	 */
	public String getTitle() {
	    return title;
	}

	/**
	 * 設定 挂牌标题
	 * @param title 挂牌标题
	 */
	public void setTitle(String title) {
	    this.title = title;
	}

	/**
	 * 取得 计量单位
	 * @return 计量单位
	 */
	public String getWlunit() {
	    return wlunit;
	}

	/**
	 * 設定 计量单位
	 * @param wlunit 计量单位
	 */
	public void setWlunit(String wlunit) {
	    this.wlunit = wlunit;
	}

	/**
	 * 取得 订单状态
	 * @return 订单状态
	 */
	public Integer getOrderstate() {
	    return orderstate;
	}

	/**
	 * 設定 订单状态
	 * @param orderstate 订单状态
	 */
	public void setOrderstate(Integer orderstate) {
	    this.orderstate = orderstate;
	}

	/**
	 * 取得 订购日期
	 * @return 订购日期
	 */
	public Date getCreatetime() {
	    return createtime;
	}

	/**
	 * 設定 订购日期
	 * @param createtime 订购日期
	 */
	public void setCreatetime(Date createtime) {
	    this.createtime = createtime;
	}

	/**
	 * 取得 散货图片
	 * @return 散货图片
	 */
	public String getPath() {
	    return path;
	}

	/**
	 * 設定 散货图片
	 * @param path 散货图片
	 */
	public void setPath(String path) {
	    this.path = path;
	}

	/**
	 * 取得 挂牌者姓名
	 * @return 挂牌者姓名
	 */
	public String getUsername() {
	    return username;
	}

	/**
	 * 設定 挂牌者姓名
	 * @param username 挂牌者姓名
	 */
	public void setUsername(String username) {
	    this.username = username;
	}

	/**
	 * 取得 挂牌者id
	 * @return 挂牌者id
	 */
	public Integer getUserId() {
	    return userId;
	}

	/**
	 * 設定 挂牌者id
	 * @param userId 挂牌者id
	 */
	public void setUserId(Integer userId) {
	    this.userId = userId;
	}

	/**
	 * 取得 购买者姓名
	 * @return 购买者姓名
	 */
	public String getBuyername() {
	    return buyername;
	}

	/**
	 * 設定 购买者姓名
	 * @param buyername 购买者姓名
	 */
	public void setBuyername(String buyername) {
	    this.buyername = buyername;
	}

	/**
	 * 取得 摘牌者id
	 * @return 摘牌者id
	 */
	public Integer getBuyerId() {
	    return buyerId;
	}

	/**
	 * 設定 摘牌者id
	 * @param buyerId 摘牌者id
	 */
	public void setBuyerId(Integer buyerId) {
	    this.buyerId = buyerId;
	}

	/**
	 * 取得 操作修改时间
	 * @return 操作修改时间
	 */
	public Date getUpdatetime() {
	    return updatetime;
	}

	/**
	 * 設定 操作修改时间
	 * @param updatetime 操作修改时间
	 */
	public void setUpdatetime(Date updatetime) {
	    this.updatetime = updatetime;
	}

	/**
	 * 取得 实际付款
	 * @return 实际付款
	 */
	public BigDecimal getActualPayment() {
	    return actualPayment;
	}

	/**
	 * 設定 实际付款
	 * @param actualPayment 实际付款
	 */
	public void setActualPayment(BigDecimal actualPayment) {
	    this.actualPayment = actualPayment;
	}

	/**
	 * 取得 成交数量
	 * @return 成交数量
	 */
	public BigDecimal getVolume() {
	    return volume;
	}

	/**
	 * 設定 成交数量
	 * @param volume 成交数量
	 */
	public void setVolume(BigDecimal volume) {
	    this.volume = volume;
	}

	/**
	 * 取得 是否有申诉
	 * @return 是否有申诉
	 */
	public Integer getAppealState() {
	    return appealState;
	}

	/**
	 * 設定 是否有申诉
	 * @param appealState 是否有申诉
	 */
	public void setAppealState(Integer appealState) {
	    this.appealState = appealState;
	}

	/**
	 * 取得 保证金
	 * @return 保证金
	 */
	public BigDecimal getDeposit() {
	    return deposit;
	}

	/**
	 * 設定 保证金
	 * @param deposit 保证金
	 */
	public void setDeposit(BigDecimal deposit) {
	    this.deposit = deposit;
	}

	/**
	 * 取得 尾款金额
	 * @return 尾款金额
	 */
	public BigDecimal getFinalPayment() {
	    return finalPayment;
	}

	/**
	 * 設定 尾款金额
	 * @param finalPayment 尾款金额
	 */
	public void setFinalPayment(BigDecimal finalPayment) {
	    this.finalPayment = finalPayment;
	}

	/**
	 * 取得 退款金额
	 * @return 退款金额
	 */
	public BigDecimal getRemitPayment() {
	    return remitPayment;
	}

	/**
	 * 設定 退款金额
	 * @param remitPayment 退款金额
	 */
	public void setRemitPayment(BigDecimal remitPayment) {
	    this.remitPayment = remitPayment;
	}

	/**
	 * 取得 订单过期时间
	 * @return 订单过期时间
	 */
	public Date getExpiretime() {
	    return expiretime;
	}

	/**
	 * 設定 订单过期时间
	 * @param expiretime 订单过期时间
	 */
	public void setExpiretime(Date expiretime) {
	    this.expiretime = expiretime;
	}

	/**
	 * 取得 订单取消原因
	 * @return 订单取消原因
	 */
	public String getOrderCancelReasean() {
	    return orderCancelReasean;
	}

	/**
	 * 設定 订单取消原因
	 * @param orderCancelReasean 订单取消原因
	 */
	public void setOrderCancelReasean(String orderCancelReasean) {
	    this.orderCancelReasean = orderCancelReasean;
	}

	/**
	 * 取得 品种名称
	 * @return 品种名称
	 */
	public String getBreedName() {
	    return breedName;
	}

	/**
	 * 設定 品种名称
	 * @param breedName 品种名称
	 */
	public void setBreedName(String breedName) {
	    this.breedName = breedName;
	}

	/**
	 * 取得 品种规格
	 * @return 品种规格
	 */
	public String getGrade() {
	    return grade;
	}

	/**
	 * 設定 品种规格
	 * @param grade 品种规格
	 */
	public void setGrade(String grade) {
	    this.grade = grade;
	}

	/**
	 * 取得 申述审核状态
	 * @return 申述审核状态
	 */
	public Integer getExamineState() {
	    return examineState;
	}

	/**
	 * 設定 申述审核状态
	 * @param examineState 申述审核状态
	 */
	public void setExamineState(Integer examineState) {
	    this.examineState = examineState;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDataDiff() {
		if(this.startTime != null){
			try {
				this.dataDiff=String.valueOf(TimeUtil.daysBetween(getStartTime(),getEndTime()));
			} catch (ParseException e) {
				logger.error(e.getMessage());
			}
		}
		return dataDiff;
	}
	

}
