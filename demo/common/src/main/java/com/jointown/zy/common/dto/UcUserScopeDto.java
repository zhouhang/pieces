package com.jointown.zy.common.dto;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.jointown.zy.common.model.UcUserBreed;

/**
 * @ClassName: UcUserScopeDto
 * @Description: 经营信息Dto
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
public class UcUserScopeDto extends BaseDto {

	/** TODO */
	private static final long serialVersionUID = 1920057333757502445L;
	/** 会员id */
	private String userId;
	/** 业务类型 */
	private String dealType;
	/** 经营身份 */
	private String dealRole;
	/** 主营品种 */
	// private String breed;
	private List<UcUserBreed> breeds;
	/** 经营规模 */
	private String scale;
	/** 省份 */
	private String provinceCode;
	/** 城市 */
	private String cityCode;
	/** 街道地址 */
	private String address;
	/** 邮编 */
	private String zipCode;
	/** 传真区号 */
	private String areaCode;
	/** 传真号码 */
	private String fax;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getDealRole() {
		return dealRole;
	}

	public void setDealRole(String dealRole) {
		this.dealRole = dealRole;
	}

	public List<UcUserBreed> getBreeds() {
		return breeds;
	}

	public void setBreeds(List<UcUserBreed> breeds) {
		this.breeds = breeds;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
