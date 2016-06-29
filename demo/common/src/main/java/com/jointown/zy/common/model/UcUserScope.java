package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UcUserScope
 * @Description: 会员经营信息model
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
public class UcUserScope implements Serializable {
	
	/** TODO */
	private static final long serialVersionUID = -4783532249653449600L;
	
	/**会员经营范围ID(主键)*/
	private Long scopeId;
	/**会员ID*/
	private Long userId;
	/**业务类型 1：我买药材 2：我卖药材 3：我既买药材，也卖药材*/
	private Integer dealType;
	/**经营身份 1：产地经营户 2：市场经营大户 3：中药饮片厂 4：中成药厂 5：种植合作社 6：药农 7：其他*/
	private Integer dealRole;
	/**经营规模 1：100万以下/年 2：00万—500万/年 3：500万—1000万/年 4：1000万—5000万/年 5： 5000万以上/年*/
	private Integer scale;
	/**省份编码*/
	private String provinceCode;
	/**城市编码*/
	private String cityCode;
	/**地址*/
	private String address;
	/**邮编*/
	private String zipCode;
	/**传真区号*/
	private String areaCode;
	/**传真*/
	private String fax;
	/**创建时间*/
	private Date createTime;
	/**最后更新时间*/
	private Date updateTime;
	/**创建人ID (boss后台)*/
	private Integer createrId;
	/**最后更新人ID (boss后台)*/
	private Integer updaterId;
	
	public Long getScopeId() {
		return scopeId;
	}
	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getDealType() {
		return dealType;
	}
	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}
	public Integer getDealRole() {
		return dealRole;
	}
	public void setDealRole(Integer dealRole) {
		this.dealRole = dealRole;
	}
	public Integer getScale() {
		return scale;
	}
	public void setScale(Integer scale) {
		this.scale = scale;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
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
	public Integer getCreaterId() {
		return createrId;
	}
	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}
	public Integer getUpdaterId() {
		return updaterId;
	}
	public void setUpdaterId(Integer updaterId) {
		this.updaterId = updaterId;
	}
	

}
