package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.util.List;


/**
 * 会员首页vo
 * @author ldp	
 * date 2015-1-04
 */
public class MemberHomeVo implements Serializable {
	
	/** 会员名称 */
	private String userName;
	/** 真实姓名或公司名称*/
	private String realName;
	/** 当前登录IP */
	private String accessIp;
	/** 上次访问时间 */
	private String lastAccessDate;
	/** 认证是否通过标识 */
	private Integer certifyStatus;
 	/** 本次访问的URL */
	private String url;
	/**邮箱 */
	private String email;
	

	
	
	/**
	 * 取得 会员名称
	 * @return 会员名称
	 */
	public String getUserName() {
	    return userName;
	}
	/**
	 * 設定 会员名称
	 * @param userName 会员名称
	 */
	public void setUserName(String userName) {
	    this.userName = userName;
	}
	/**
	 * 取得 真实姓名或公司名称
	 * @return 真实姓名或公司名称
	 */
	public String getRealName() {
	    return realName;
	}
	/**
	 * 設定 真实姓名或公司名称
	 * @param realName 真实姓名或公司名称
	 */
	public void setRealName(String realName) {
	    this.realName = realName;
	}
	/**
	 * 取得 当前登录IP
	 * @return 当前登录IP
	 */
	public String getAccessIp() {
	    return accessIp;
	}
	/**
	 * 設定 当前登录IP
	 * @param accessIp 当前登录IP
	 */
	public void setAccessIp(String accessIp) {
	    this.accessIp = accessIp;
	}
	/**
	 * 取得 上次访问时间
	 * @return 上次访问时间
	 */
	public String getLastAccessDate() {
	    return lastAccessDate;
	}
	/**
	 * 設定 上次访问时间
	 * @param lastAccessDate 上次访问时间
	 */
	public void setLastAccessDate(String lastAccessDate) {
	    this.lastAccessDate = lastAccessDate;
	}
	/**
	 * 取得 认证是否通过标识
	 * @return 认证是否通过标识
	 */
	public Integer getCertifyStatus() {
	    return certifyStatus;
	}
	/**
	 * 設定 认证是否通过标识
	 * @param certifyStatus 认证是否通过标识
	 */
	public void setCertifyStatus(Integer certifyStatus) {
	    this.certifyStatus = certifyStatus;
	} 

	/**
	 * 取得 当前登录url
	 * @return 当前登录url
	 */
	public String getUrl() {
	    return url;
	}
	/**
	 * 設定 当前登录url
	 * @param url 当前登录url
	 */
	public void setUrl(String url) {
	    this.url = url;
	}
	
	//邮箱相关
    public String getEmail() {
	        return email;
    }

    public void setEmail(String email) {
	        this.email = email;
    }

}
