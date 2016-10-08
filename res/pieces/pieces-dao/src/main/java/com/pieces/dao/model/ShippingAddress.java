package com.pieces.dao.model;

import com.pieces.dao.group.Biz;
import com.pieces.dao.group.Boss;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;


public class ShippingAddress  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotNull
	private Integer userId;
	
	//是否默认地址1 默认
	@NotNull
	private Boolean isDefault;
	
	//收货人
	@NotEmpty
	private String consignee;
	
	//手机号码
	@NotEmpty
	@Pattern(regexp = "^1[3-9]\\d{9}$")
	private String tel;
	
	//所在区域
	@NotNull
	private Integer areaId;

	private String postcode;

	//详细地址
	@NotEmpty
	private String detail;
	
	private Date createTime;
	
	//别名
	private String aliases;
	
	public ShippingAddress(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getAliases() {
		return aliases;
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
}