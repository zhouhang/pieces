package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WxDemand implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * WEIXIN.WX_DEMAND.DEMAND_ID (求购信息表ID，主键)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private Long demandId;

    /**
     * WEIXIN.WX_DEMAND.USER_ID (发布人（前台用户ID）)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private Long userId;

    /**
     * WEIXIN.WX_DEMAND.BREED_ID (品种ID)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private Long breedId;

    /**
     * WEIXIN.WX_DEMAND.BREED_NAME (品种名称)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private String breedName;

    /**
     * WEIXIN.WX_DEMAND.STANDARD_LEVEL_ID (规格)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private Long standardLevelId;

    /**
     * WEIXIN.WX_DEMAND.BREED_STANDARD_LEVEL (规格名称)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private String breedStandardLevel;

    /**
     * WEIXIN.WX_DEMAND.QTY (数量)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private BigDecimal qty;

    /**
     * WEIXIN.WX_DEMAND.QTY_UNIT (数量单位)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private String qtyUnit;

    /**
     * WEIXIN.WX_DEMAND.STATUS (状态：0-初始，未审核  1-审核通过 2-审核未通过3-撤销)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private Short status;

    /**
     * WEIXIN.WX_DEMAND.CREATE_TIME (创建时间)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private Date createTime;

    /**
     * WEIXIN.WX_DEMAND.UPDATE_TIME (最后更新时间)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private Date updateTime;

    /**
     * WEIXIN.WX_DEMAND.APPROVE_TIME (审核时间)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private Date approveTime;

    /**
     * WEIXIN.WX_DEMAND.APPROVER (审核人ID)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private Long approver;

    /**
     * WEIXIN.WX_DEMAND.AREA_CODE (货物所在地)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private String areaCode;

    /**
     * WEIXIN.WX_DEMAND.AREA_NAME (货物所在地名称)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private String areaName;

    /**
     * WEIXIN.WX_DEMAND.USER_NAME (发布人（真实姓名）)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private String userName;

    /**
     * WEIXIN.WX_DEMAND.USER_MOBILE (发布人（手机）)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private String userMobile;

    /**
     * WEIXIN.WX_DEMAND.SYPPLY_RESOURCE (申请来源（微信-0、东方中药材-1、珍药材-2、客服-3）)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private Short sypplyResource;

    /**
     * WEIXIN.WX_DEMAND.BREED_PLACE (产地名称)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private String breedPlace;

    /**
     * WEIXIN.WX_DEMAND.PLACE_ID (产地ID)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private Long placeId;

    /**
     * WEIXIN.WX_DEMAND.REMARKS (备注（最多500字）)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private String remarks;

    /**
     * WEIXIN.WX_DEMAND.DEPICT (描述（最多500字）)
     * @ibatorgenerated 2015-06-17 11:59:55
     */
    private String depict;

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
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

    public String getBreedPlace() {
        return breedPlace;
    }

    public void setBreedPlace(String breedPlace) {
        this.breedPlace = breedPlace;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }
}