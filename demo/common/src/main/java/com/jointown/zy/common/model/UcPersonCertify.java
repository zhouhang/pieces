package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ldp
 * date 2015年1月7日
 * Verison 0.0.1
 */
public class UcPersonCertify implements Serializable {

	private static final long serialVersionUID = 7180947263181125622L;
	
	/** 认证ID*/
	private Integer certifyId;
	/** 会员ID*/
	private Integer userId;
	/** 个人认证姓名*/
	private String name;
	/** 身份证号*/
	private String idCard;
	/** 认证状态，0待审核 1通过 2不通过*/
	private Integer status;
	/** 创建时间*/
	private Date createTime;
	/** 最后更新时间*/
	private Date updateTime;
	/** 提交时间*/
	private Date submitTime;
	/** 审批时间*/
	private Date approveTime;
	/** 审批人ID*/
	private Integer approverId;
	/** 不通过原因*/
	private Integer rejectMemo;
	/**
	 * 取得认证ID
	 * @return 认证ID
	 */
	public Integer getCertifyId() {
	    return certifyId;
	}
	/**
	 * 设定认证ID
	 * @param certifyId 认证ID
	 */
	public void setCertifyId(Integer certifyId) {
	    this.certifyId = certifyId;
	}
	/**
	 * 取得会员ID
	 * @return 会员ID
	 */
	public Integer getUserId() {
	    return userId;
	}
	/**
	 * 设定会员ID
	 * @param userId 会员ID
	 */
	public void setUserId(Integer userId) {
	    this.userId = userId;
	}
	/**
	 * 取得个人认证姓名
	 * @return 个人认证姓名
	 */
	public String getName() {
	    return name;
	}
	/**
	 * 设定个人认证姓名
	 * @param name 个人认证姓名
	 */
	public void setName(String name) {
	    this.name = name;
	}
	/**
	 * 取得身份证号
	 * @return 身份证号
	 */
	public String getIdCard() {
	    return idCard;
	}
	/**
	 * 设定身份证号
	 * @param idCard 身份证号
	 */
	public void setIdCard(String idCard) {
	    this.idCard = idCard;
	}
	/**
	 * 取得认证状态，0待审核 1通过 2不通过
	 * @return 认证状态，0待审核 1通过 2不通过
	 */
	public Integer getStatus() {
	    return status;
	}
	/**
	 * 设定认证状态，0待审核 1通过 2不通过
	 * @param status 认证状态，0待审核 1通过 2不通过
	 */
	public void setStatus(Integer status) {
	    this.status = status;
	}
	/**
	 * 取得创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
	    return createTime;
	}
	/**
	 * 设定创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
	    this.createTime = createTime;
	}
	/**
	 * 取得最后更新时间
	 * @return 最后更新时间
	 */
	public Date getUpdateTime() {
	    return updateTime;
	}
	/**
	 * 设定最后更新时间
	 * @param updateTime 最后更新时间
	 */
	public void setUpdateTime(Date updateTime) {
	    this.updateTime = updateTime;
	}
	/**
	 * 取得提交时间
	 * @return 提交时间
	 */
	public Date getSubmitTime() {
	    return submitTime;
	}
	/**
	 * 设定提交时间
	 * @param submitTime 提交时间
	 */
	public void setSubmitTime(Date submitTime) {
	    this.submitTime = submitTime;
	}
	/**
	 * 取得审批时间
	 * @return 审批时间
	 */
	public Date getApproveTime() {
	    return approveTime;
	}
	/**
	 * 设定审批时间
	 * @param approveTime 审批时间
	 */
	public void setApproveTime(Date approveTime) {
	    this.approveTime = approveTime;
	}
	/**
	 * 取得审批人ID
	 * @return 审批人ID
	 */
	public Integer getApproverId() {
	    return approverId;
	}
	/**
	 * 设定审批人ID
	 * @param approverId 审批人ID
	 */
	public void setApproverId(Integer approverId) {
	    this.approverId = approverId;
	}
	/**
	 * 取得不通过原因
	 * @return 不通过原因
	 */
	public Integer getRejectMemo() {
	    return rejectMemo;
	}
	/**
	 * 设定不通过原因
	 * @param rejectMemo 不通过原因
	 */
	public void setRejectMemo(Integer rejectMemo) {
	    this.rejectMemo = rejectMemo;
	}
	

}
