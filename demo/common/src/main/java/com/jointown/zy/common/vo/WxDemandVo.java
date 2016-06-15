package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 微信求购信息Vo
 *
 * @author aizhengdong
 *
 * @data 2015年3月27日
 */
public class WxDemandVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 编号：求购信息ID */
    private Long demandId;

    /** 发布人姓名 */
    private String userName;
    
    /** 发布人手机号码  */
    private String userMobile;
    
    /**  
	 * 信息来源
	 * @author aizhengdong 2015年6月12日
	 */  
	private String applyResource;
    
    /** 品种名称 */
    private String breedName;
    
    /** 规格名称 */
    private String breedStandardLevel;
    
    /**  
  	 * 价格
  	 * @author aizhengdong 2015年6月12日
  	 */  
  	private String breedPrice;
     
    /** 产地ID */
    private String placeId;
    
    /** 品种产地 */
    private String breedPlace;

    /** 数量 */
    private BigDecimal qty;

    /** 数量单位 */
    private String qtyUnit;
    
    /** 数量加数量单位 */
    private String qtyUnitQty;

	/** 货物所在地ID */
    private String areaCode;
    
    /** 货物所在地名称 */
    private String areaName;
    
    /** 状态：未处理-0、有效-1、无效-2、用户撤销-3 */
    private Short status;
    
    /** 创建时间 */
    private Date createTime;

    /** 最后更新时间 */
    private Date updateTime;

    /** 审核时间*/
    private Date approveTime;

    /** 审核人姓名*/
    private String approverName;

    /** 货源地 */
    private String huoYuandi;
    
	/**  
	 * 备注
	 * @author aizhengdong 2015年6月19日
	 */  
	private String remarks;
	
	/**  
	 * 描述
	 * @author aizhengdong 2015年6月19日
	 */  
	private String depict;
    
    
	@Override
	public String toString() {
		return "WxDemandVo [demandId=" + demandId + ", userName=" + userName
				+ ", userMobile=" + userMobile + ", breedName=" + breedName
				+ ", breedStandardLevel=" + breedStandardLevel
				+ ", placeId=" + placeId + ", breedPlace=" + breedPlace + ", qty=" + qty + ", qtyUnit="
				+ qtyUnit + ", areaCode=" + areaCode + ", areaName=" + areaName + "status=" + status + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", approveTime=" + approveTime
				+ ", approverName=" + approverName + ", huoYuandi=" + huoYuandi
				+ "]";
	}
 
    public String getQtyUnitQty() {
		return qtyUnitQty;
	}

	public void setQtyUnitQty(String qtyUnitQty) {
		this.qtyUnitQty = qtyUnitQty;
	}
	
	public String getHuoYuandi() {
		return huoYuandi;
	}

	public void setHuoYuandi(String huoYuandi) {
		this.huoYuandi = huoYuandi;
	}

	public Long getDemandId() {
		return demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
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

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getBreedPlace() {
		return breedPlace;
	}

	public void setBreedPlace(String breedPlace) {
		this.breedPlace = breedPlace;
	}

	public String getApplyResource() {
		return applyResource;
	}

	public void setApplyResource(String applyResource) {
		this.applyResource = applyResource;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	
	public String getBreedPrice() {
		return breedPrice;
	}

	public void setBreedPrice(String breedPrice) {
		this.breedPrice = breedPrice;
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