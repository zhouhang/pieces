/**
 *
 * @author ldp
 * date 2015年1月7日
 * Verison 0.0.1
 */
package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 认证日志实体
 * @author ldp
 * date 2015年1月7日
 * Verison 0.0.1
 */
public class UcUserCertifyLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4001372959061814551L;
	
	/** 日志Id*/
	private Integer logId;
	/** 认证Id*/
	private Integer certifyId;
	/** 认证类型，0：个人认证  1企业认证*/
	private Integer type;
	/** 说明*/
	private String memo;
	/** 日志日期*/
	private Date createDate;
	/** 操作人ID*/
	private Integer opraterId;
	/** 操作人名称*/
	private String opraterName;
	/**
	 * 取得日志Id
	 * @return 日志Id
	 */
	public Integer getLogId() {
	    return logId;
	}
	/**
	 * 设定日志Id
	 * @param logId 日志Id
	 */
	public void setLogId(Integer logId) {
	    this.logId = logId;
	}
	/**
	 * 取得认证Id
	 * @return 认证Id
	 */
	public Integer getCertifyId() {
	    return certifyId;
	}
	/**
	 * 设定认证Id
	 * @param certifyId 认证Id
	 */
	public void setCertifyId(Integer certifyId) {
	    this.certifyId = certifyId;
	}
	/**
	 * 取得认证类型，0：个人认证  1企业认证
	 * @return 认证类型，0：个人认证  1企业认证
	 */
	public Integer getType() {
	    return type;
	}
	/**
	 * 设定认证类型，0：个人认证  1企业认证
	 * @param type 认证类型，0：个人认证  1企业认证
	 */
	public void setType(Integer type) {
	    this.type = type;
	}
	/**
	 * 取得说明
	 * @return 说明
	 */
	public String getMemo() {
	    return memo;
	}
	/**
	 * 设定说明
	 * @param memo 说明
	 */
	public void setMemo(String memo) {
	    this.memo = memo;
	}
	/**
	 * 取得日志日期
	 * @return 日志日期
	 */
	public Date getCreateDate() {
	    return createDate;
	}
	/**
	 * 设定日志日期
	 * @param createDate 日志日期
	 */
	public void setCreateDate(Date createDate) {
	    this.createDate = createDate;
	}
	/**
	 * 取得操作人ID
	 * @return 操作人ID
	 */
	public Integer getOpraterId() {
	    return opraterId;
	}
	/**
	 * 设定操作人ID
	 * @param opraterId 操作人ID
	 */
	public void setOpraterId(Integer opraterId) {
	    this.opraterId = opraterId;
	}
	/**
	 * 取得操作人名称
	 * @return 操作人名称
	 */
	public String getOpraterName() {
	    return opraterName;
	}
	/**
	 * 设定操作人名称
	 * @param opraterName 操作人名称
	 */
	public void setOpraterName(String opraterName) {
	    this.opraterName = opraterName;
	}

}
