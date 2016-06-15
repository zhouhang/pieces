package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jointown.zy.common.model.WxSupplyPic;

/**
 * 微信供应信息Vo
 *
 * @author aizhengdong
 *
 * @data 2015年3月17日
 */
public class WxSupplyVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 编号：供应信息ID */
    private Long supplyId;

    /** 发布人姓名 */
    private String userName;
    
    /** 发布人手机号码  */
    private String userMobile;
    
    /** 品种ID */
    private Long breedId;
    
    /** 品种名称 */
    private String breedName;
    
    /** 规格名称 */
    private String breedStandardLevel;
    
    /** 价格 */
    private BigDecimal price;
    
    /** 价格单位 */
    private String priceUnit;
    
    /** 价格加价格单位 */
    private String priceUnitPrice;
   
    /** 数量 */
    private BigDecimal qty;

    /** 数量单位 */
    private String qtyUnit;

	/** 数量加数量单位 */
    private String qtyUnitQty;
    
    /** 产地ID */
    private String placeId;
    
    /** 产地名称 */
    private String breedPlace;
    
    /** 状态：0-初始，未审核  1-审核通过  2-审核未通过 3-撤销 */
    private Short status;
    
    /** 货物所在地ID */
    private String areaCode;
    
    /** 货物所在地名称 */
    private String areaName;
    
    /** 创建时间 */
    private Date createTime;

    /** 最后更新时间 */
    private Date updateTime;

    /** 审核时间*/
    private Date approveTime;

    /** 审核人姓名*/
    private String approverName;
    
    /** 供应信息图片 */
    private List<WxSupplyPic> picList;
    
    /** 供应信息图片 */
    private List<WxSupplyPicVo> picVoList;

    /** 微信上传图片第一张图地址 */
    private String supplyPicOne;
    
    /** H5页面查询供应信息的价格为字符串类型 */
    private String supplyPrice;
    
    /**
     * 供应信息来源
     */
    private Short sypplyResource;

    @Override
	public String toString() {
		return "WxSupplyVo [supplyId=" + supplyId + ", userName=" + userName
				+ ", userMobile=" + userMobile + ", breedId=" + breedId
				+ ", breedName=" + breedName
				+ ", breedStandardLevel=" + breedStandardLevel + ", price="
				+ price + ", priceUnit=" + priceUnit + ", qty=" + qty
				+ ", qtyUnit=" + qtyUnit + ", placeId=" + placeId + ", breedPlace=" + breedPlace
				+ ", status=" + status + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", approveTime=" + approveTime
				+ ", approverName=" + approverName + ", picList=" + picList
				+ ", picVoList=" + picVoList + ", supplyPicOne=" + supplyPicOne
				+ ", huoYuandi=" + huoYuandi + ", areaCode=" + areaCode + ", areaName=" + areaName + "]";
	}
    
    public String getQtyUnitQty() {
		return qtyUnitQty;
	}

	public void setQtyUnitQty(String qtyUnitQty) {
		this.qtyUnitQty = qtyUnitQty;
	}
    
	public String getSupplyPrice() {
		return supplyPrice;
	}

	public void setSupplyPrice(String supplyPrice) {
		this.supplyPrice = supplyPrice;
	}

	/** 货源地 */
    private String huoYuandi;
    
    public String getHuoYuandi() {
		return huoYuandi;
	}
    
	public void setHuoYuandi(String huoYuandi) {
		this.huoYuandi = huoYuandi;
	}

    public String getSupplyPicOne() {
		return supplyPicOne;
	}

	public void setSupplyPicOne(String supplyPicOne) {
		this.supplyPicOne = supplyPicOne;
	}

	public Long getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(Long supplyId) {
		this.supplyId = supplyId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getPriceUnitPrice() {
		return priceUnitPrice;
	}

	public void setPriceUnitPrice(String priceUnitPrice) {
		this.priceUnitPrice = priceUnitPrice;
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

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getBreedPlace() {
		return breedPlace;
	}

	public void setBreedPlace(String breedPlace) {
		this.breedPlace = breedPlace;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
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

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public List<WxSupplyPic> getPicList() {
		return picList;
	}

	public void setPicList(List<WxSupplyPic> picList) {
		this.picList = picList;
	}

	public List<WxSupplyPicVo> getPicVoList() {
		return picVoList;
	}

	public void setPicVoList(List<WxSupplyPicVo> picVoList) {
		this.picVoList = picVoList;
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
}