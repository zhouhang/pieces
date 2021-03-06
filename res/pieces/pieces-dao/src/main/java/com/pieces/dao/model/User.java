package com.pieces.dao.model;

import com.pieces.dao.group.Biz;
import com.pieces.dao.group.Boss;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 前台用户
 * @author feng
 */
public class User  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	
	//用户名
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9]{5,19}$", groups = {Biz.class, Boss.class})
	private String userName;
	
	//密码
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9_]{6,20}$", groups = {Biz.class, Boss.class})
	private String password;
	
	//密码盐
	private String salt;
	
	//企业全称
	private String companyFullName;
	
	//企业注册地省份
	private Integer areaId;
	
	//联系人姓名
	@NotEmpty
	@Pattern(regexp = "^([a-zA-Z]|[\\u4e00-\\u9fa5]){2,50}$", groups = {Biz.class, Boss.class})
	private String contactName;
	
	//联系人手机
	@NotEmpty
	@Pattern(regexp = "^1[3-9]\\d{9}$", groups = {Biz.class, Boss.class})
	private String contactMobile;

	
	//在线状态（0：离线，1：在线）
	private Boolean onlineStatus;
	
	//是否绑定erp（0：未绑定，1：绑定）
	private Boolean bindErp;
	
	//创建渠道（0：前台  1：后台 2 询价自动创建 3 微信）
	private Integer source;
	
	//地址全称
	private String areaFull;
	
	//用户状态： 0：有效 1：删除
	private Boolean isDel;

	//用户类型 1：终端 2：代理商
	private Integer type;

	//0未认证,1认证通过
	private Integer certifyStatus;

	//认证完成时间
	private Date certifyTime;

	private Integer serviceId;
	
	//创建时间
	private Date createTime;
	
	//修改时间
	private Date updateTime;

	private String openId;
	
	public User(){}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Date getCertifyTime() {
		return certifyTime;
	}

	public void setCertifyTime(Date certifyTime) {
		this.certifyTime = certifyTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCertifyStatus() {
		return certifyStatus;
	}

	public void setCertifyStatus(Integer certifyStatus) {
		this.certifyStatus = certifyStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getCompanyFullName() {
		return companyFullName;
	}

	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}
	
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	
	public Boolean getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Boolean onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	
	public Boolean getBindErp() {
		return bindErp;
	}

	public void setBindErp(Boolean bindErp) {
		this.bindErp = bindErp;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getAreaFull() {
		return areaFull;
	}

	public void setAreaFull(String areaFull) {
		this.areaFull = areaFull;
	}
	
	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
}