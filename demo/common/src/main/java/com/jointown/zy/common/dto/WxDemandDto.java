package com.jointown.zy.common.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.jointown.zy.common.model.WxDemand;

/**
 * 
 * @ClassName: WxDemandDto
 * @Description: 微信求购信息Dto
 * @Author: wangjunhu
 * @Date: 2015年5月15日
 * @Version: 1.0
 */
public class WxDemandDto {

	/**
     * WEIXIN.WX_DEMAND.DEMAND_ID (求购信息表ID，主键)
     * @ibatorgenerated 2015-03-12 18:12:10
     */
    private Long demandId;

    /**
     * WEIXIN.WX_DEMAND.USER_ID (发布人（前台用户ID）)
     * @ibatorgenerated 2015-03-12 18:12:10
     */
    private Long userId;

    /**
     * WEIXIN.WX_DEMAND.BREED_ID (品种ID)
     * @ibatorgenerated 2015-03-12 18:12:10
     */
    private Long breedId;

    /**
     * WEIXIN.WX_DEMAND.BREED_NAME (品种名称)
     * @ibatorgenerated 2015-03-12 18:12:10
     */
    private String breedName;

    /**
     * WEIXIN.WX_DEMAND.STANDARD_LEVEL_ID (规格)
     * @ibatorgenerated 2015-03-12 18:12:10
     */
    private Long standardLevelId;

    /**
     * WEIXIN.WX_DEMAND.BREED_STANDARD_LEVEL (规格名称)
     * @ibatorgenerated 2015-03-12 18:12:10
     */
    private String breedStandardLevel;

    /**
     * WEIXIN.WX_DEMAND.QTY (数量)
     * @ibatorgenerated 2015-03-12 18:12:10
     */
    private BigDecimal qty;

    /**
     * WEIXIN.WX_DEMAND.QTY_UNIT (数量单位)
     * @ibatorgenerated 2015-03-12 18:12:10
     */
    private String qtyUnit;

    /**
     * WEIXIN.WX_DEMAND.AREA_CODE (货物所在地ID)
     * @ibatorgenerated 2015-03-04 14:34:03
     */
    private String areaCode;
    
    /**
     * WEIXIN.WX_DEMAND.AREA_NAME (货物所在地名称)
     * @ibatorgenerated 2015-03-04 14:34:03
     */
    private String areaName;
    
    private WxDemand wxDemand;
    
	/**  
	 * 发布人姓名 
	 * @author aizhengdong 2015年6月10日
	 */  
	private String userName;
	
	/**  
	 * 联系电话 
	 * @author aizhengdong 2015年6月10日
	 */  
	private String userMobile;
	
	/**  
	 * 状态
	 * @author aizhengdong 2015年6月10日
	 */  
	private Short status;
	
	/**  
	 * 数量加单位
	 * @author aizhengdong 2015年6月15日
	 */  
    private String qtyUnitQty;
    
    /**  
  	 * 价格
  	 * @author aizhengdong 2015年6月15日
  	 */  
  	private String breedPrice;
  	
  	 /**  
  	 * 价格单位
  	 * @author aizhengdong 2015年8月7日
  	 */  
  	private String breedPriceUnit;
  	
	/**  
	 * 信息来源
	 * @author aizhengdong 2015年6月10日
	 */  
	private String applyResource;
	
	/**  
	 * 产地ID
	 * @author wangjunhu 2015年7月6日
	 */  
    private Long placeId;
    
	/**  
	 * 品种产地
	 * @author aizhengdong 2015年6月15日
	 */  
    private String breedPlace;
    
	/**  
	 * 开始时间
	 * @author aizhengdong 2015年6月10日
	 */  
	private String startDate;
	
	/**  
	 * 结束时间
	 * @author aizhengdong 2015年6月10日
	 */  
	private String endDate;
	
	/**  
	 * 备注
	 * @author aizhengdong 2015年6月10日
	 */  
	private String remarks;
	
	/**  
	 * 描述
	 * @author aizhengdong 2015年6月10日
	 */  
	private String depict;
	
    /**
     * 审核时间
     * @author aizhengdong 2015年8月7日
     */
    private Date approveTime;

    /**
     * 审核人ID
     * @author aizhengdong 2015年8月7日
     */
    private Long approver;
    
    public WxDemandDto() {
		super();
		wxDemand = new WxDemand();
	}

	public Long getDemandId() {
		return demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
		wxDemand.setDemandId(demandId);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
		wxDemand.setUserId(userId);
	}

	public Long getBreedId() {
		return breedId;
	}

	public void setBreedId(Long breedId) {
		this.breedId = breedId;
		wxDemand.setBreedId(breedId);
	}

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
		wxDemand.setBreedName(breedName);
	}

	public Long getStandardLevelId() {
		return standardLevelId;
	}

	public void setStandardLevelId(Long standardLevelId) {
		this.standardLevelId = standardLevelId;
		wxDemand.setStandardLevelId(standardLevelId);
	}

	public String getBreedStandardLevel() {
		return breedStandardLevel;
	}

	public void setBreedStandardLevel(String breedStandardLevel) {
		this.breedStandardLevel = breedStandardLevel;
		wxDemand.setBreedStandardLevel(breedStandardLevel);
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
		wxDemand.setQty(qty);
	}

	public String getQtyUnit() {
		return qtyUnit;
	}

	public void setQtyUnit(String qtyUnit) {
		this.qtyUnit = qtyUnit;
		wxDemand.setQtyUnit(qtyUnit);
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
		wxDemand.setAreaCode(areaCode);
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
		wxDemand.setAreaName(areaName);
	}

	public WxDemand getWxDemand() {
		return wxDemand;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		wxDemand.setUserName(userName);
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
		wxDemand.setUserMobile(userMobile);
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getApplyResource() {
		return applyResource;
	}

	public void setApplyResource(String applyResource) {
		this.applyResource = applyResource;
		if(applyResource!=null && applyResource.matches("\\d+")){
			wxDemand.setSypplyResource(Short.parseShort(applyResource));
		}
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
		wxDemand.setRemarks(remarks);
	}

	public String getQtyUnitQty() {
		return qtyUnitQty;
	}

	public void setQtyUnitQty(String qtyUnitQty) {
		this.qtyUnitQty = qtyUnitQty;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
		wxDemand.setPlaceId(placeId);
	}

	public String getBreedPrice() {
		return breedPrice;
	}

	public void setBreedPrice(String breedPrice) {
		this.breedPrice = breedPrice;
		wxDemand.setBreedPlace(breedPrice);
	}

	public String getBreedPlace() {
		return breedPlace;
	}

	public void setBreedPlace(String breedPlace) {
		this.breedPlace = breedPlace;
		wxDemand.setBreedPlace(breedPlace);
	}

	public String getDepict() {
		return depict;
	}

	public void setDepict(String depict) {
		this.depict = depict;
		wxDemand.setDepict(depict);
	}

	public String getBreedPriceUnit() {
		return breedPriceUnit;
	}

	public void setBreedPriceUnit(String breedPriceUnit) {
		this.breedPriceUnit = breedPriceUnit;
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
	
}
