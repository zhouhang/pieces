package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class UcUserVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1632944507383091312L;
	
	
	private Long userId;

    private String userName;

    private String password;

    private String salt;

    private String mobile;

    private String email;

    private String accessIp;

    private Date createTime;

    private Date updateTime;

    private Date expireTime;

    private Date lastAccessTime;

    private Integer status;
    
    private String companyName;
    
    private String remark;
    
	/** 认证是否通过标识 */
	private Integer certifyStatus;
 	/** 本次访问的URL */
	private String url;
	
	/** 用户来源   author:lichenxiao date:2015年7月15日 */
	private Integer source;
	
	/** 标记是否完善经营信息 true完善  false未完善 */
	private boolean hasScope;
	
	/** 标记是否完善联系人信息 true完善  false未完善 */
	private boolean hasContacter;
	
	
    public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

    public String getAccessIp() {
        return accessIp;
    }

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
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

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	

	public Integer getCertifyStatus() {
	    return certifyStatus;
	}

	public void setCertifyStatus(Integer certifyStatus) {
	    this.certifyStatus = certifyStatus;
	} 
	
	

	public String getUrl() {
	    return url;
	}

	public void setUrl(String url) {
	    this.url = url;
	}

	public boolean isHasScope() {
		return hasScope;
	}

	public void setHasScope(boolean hasScope) {
		this.hasScope = hasScope;
	}

	public boolean isHasContacter() {
		return hasContacter;
	}

	public void setHasContacter(boolean hasContacter) {
		this.hasContacter = hasContacter;
	}

}
