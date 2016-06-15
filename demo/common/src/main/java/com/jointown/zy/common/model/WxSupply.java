package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WxSupply implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * WEIXIN.WX_SUPPLY.SUPPLY_ID (供应信息表ID，主键)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private Long supplyId;

    /**
     * WEIXIN.WX_SUPPLY.USER_ID (发布人（前台用户ID）)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private Long userId;

    /**
     * WEIXIN.WX_SUPPLY.BREED_ID (品种ID)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private Long breedId;

    /**
     * WEIXIN.WX_SUPPLY.BREED_NAME (品种名称)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private String breedName;

    /**
     * WEIXIN.WX_SUPPLY.STANDARD_LEVEL_ID (规格)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private Long standardLevelId;

    /**
     * WEIXIN.WX_SUPPLY.BREED_STANDARD_LEVEL (规格名称)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private String breedStandardLevel;

    /**
     * WEIXIN.WX_SUPPLY.PRICE (价格)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private BigDecimal price;

    /**
     * WEIXIN.WX_SUPPLY.PRICE_UNIT (价格单位)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private String priceUnit;

    /**
     * WEIXIN.WX_SUPPLY.QTY (数量)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private BigDecimal qty;

    /**
     * WEIXIN.WX_SUPPLY.QTY_UNIT (数量单位)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private String qtyUnit;

    /**
     * WEIXIN.WX_SUPPLY.PLACE_ID (产地ID)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private Long placeId;

    /**
     * WEIXIN.WX_SUPPLY.BREED_PLACE (产地名称)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private String breedPlace;

    /**
     * WEIXIN.WX_SUPPLY.WHID (市场ID)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private Long whid;

    /**
     * WEIXIN.WX_SUPPLY.STATUS (状态：未处理-0、有效-1、无效-2、用户撤销-3)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private Short status;

    /**
     * WEIXIN.WX_SUPPLY.CREATE_TIME (创建时间)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private Date createTime;

    /**
     * WEIXIN.WX_SUPPLY.UPDATE_TIME (最后更新时间)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private Date updateTime;

    /**
     * WEIXIN.WX_SUPPLY.APPROVE_TIME (审核时间)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private Date approveTime;

    /**
     * WEIXIN.WX_SUPPLY.APPROVER (审核人ID)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private Long approver;

    /**
     * WEIXIN.WX_SUPPLY.AREA_CODE (货物所在地（行政区划编码）)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private String areaCode;

    /**
     * WEIXIN.WX_SUPPLY.AREA_NAME (货物所在地（行政区划名称）)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private String areaName;

    /**
     * WEIXIN.WX_SUPPLY.USER_NAME (发布人（真实姓名）)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private String userName;

    /**
     * WEIXIN.WX_SUPPLY.USER_MOBILE (发布人（手机）)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private String userMobile;

    /**
     * WEIXIN.WX_SUPPLY.SYPPLY_RESOURCE (申请来源（微信-0、东方中药材-1、珍药材-2、客服-3）)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private Short sypplyResource;

    /**
     * WEIXIN.WX_SUPPLY.REMARKS (备注)
     * @ibatorgenerated 2015-06-12 09:32:03
     */
    private String remarks;

    public Long getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Long supplyId) {
        this.supplyId = supplyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBreedId() {
        return breedId;
    }

    public void setBreedId(Long breedId) {
        this.breedId = breedId;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public Long getStandardLevelId() {
        return standardLevelId;
    }

    public void setStandardLevelId(Long standardLevelId) {
        this.standardLevelId = standardLevelId;
    }

    public String getBreedStandardLevel() {
        return breedStandardLevel;
    }

    public void setBreedStandardLevel(String breedStandardLevel) {
        this.breedStandardLevel = breedStandardLevel;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getQtyUnit() {
        return qtyUnit;
    }

    public void setQtyUnit(String qtyUnit) {
        this.qtyUnit = qtyUnit;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getBreedPlace() {
        return breedPlace;
    }

    public void setBreedPlace(String breedPlace) {
        this.breedPlace = breedPlace;
    }

    public Long getWhid() {
        return whid;
    }

    public void setWhid(Long whid) {
        this.whid = whid;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
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

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Long getApprover() {
        return approver;
    }

    public void setApprover(Long approver) {
        this.approver = approver;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Short getSypplyResource() {
        return sypplyResource;
    }

    public void setSypplyResource(Short sypplyResource) {
        this.sypplyResource = sypplyResource;
    }

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}