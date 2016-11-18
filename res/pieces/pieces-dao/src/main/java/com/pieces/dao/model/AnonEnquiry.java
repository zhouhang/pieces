package com.pieces.dao.model;

import com.pieces.tools.config.ValidPattern;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;


public class AnonEnquiry  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//内容
	private String content;
	
	//联系人
	@NotEmpty
	private String contacts;
	
	//发布时间
	private Date publishTime;
	
	//手机号
	//手机号码
	@NotEmpty
	@Pattern(regexp = ValidPattern.mobile, message = "手机格式错误")
	private String phone;
	
	//最后跟踪时间
	private Date lastFollowTime;
	
	//最后跟踪id
	private Integer lastFollowId;
	
	//状态 1 未处理 2 已处理
	private Integer status;
	
	public AnonEnquiry(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Date getLastFollowTime() {
		return lastFollowTime;
	}

	public void setLastFollowTime(Date lastFollowTime) {
		this.lastFollowTime = lastFollowTime;
	}
	
	public Integer getLastFollowId() {
		return lastFollowId;
	}

	public void setLastFollowId(Integer lastFollowId) {
		this.lastFollowId = lastFollowId;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}