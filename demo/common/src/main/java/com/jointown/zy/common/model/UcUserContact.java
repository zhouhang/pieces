package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UcUserContact
 * @Description: 会员信息Model
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
public class UcUserContact implements Serializable {

	/** TODO */
	private static final long serialVersionUID = 4004973466676158977L;
	
	/**会员联系人表ID*/
	private Long contactId;
	/**会员ID*/
	private Long userId;
	/**名称*/
	private String name;
	/**性别 0：男，1：女*/
	private Integer sex;
	/**部门*/
	private String department;
	/**职位*/
	private String job;
	/**座机*/
	private String phone;
	/**手机*/
	private String mobile;
	/**邮箱*/
	private String email;
	/**状态 1：有效 0：删除 */
	private Integer status;
	/**创建时间*/
	private Date createTime;
	/**最后更新时间*/
	private Date updateTime;
	/**创建人ID (boss后台)*/
	private Integer createrId;
	/**最后更新人ID (boss后台)*/
	private Integer updaterId;
	
	public Long getContactId() {
		return contactId;
	}
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
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
