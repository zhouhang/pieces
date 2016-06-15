package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class UcUser implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    
    /** 会员实名认证状态 */
    private Integer certifyStatus;
    
    //本次登录时间
    private Date AccessTime;
    
    //业务员ID
    private Long salesmanId;
    
    //业务员
    private String salesMan;
    
    //来源 0-珍药材注册 1-微信注册 2-BOSS后台添加
    private Integer source;
    
    //创建人ID（后台用户）
    private Integer createrId;

    //最后更新人ID（后台用户）
    private Integer updaterId;
    
    private String dealType;
    
    private String dealRole;
    
	public Integer getCertifyStatus() {
		return certifyStatus;
	}

	public void setCertifyStatus(Integer certifyStatus) {
		this.certifyStatus = certifyStatus;
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
    
	
   public Date getAccessTime() {
        return AccessTime;
    }

    public void setAccessTime(Date AccessTime) {
        this.AccessTime = AccessTime;
    }

	public Long getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Long salesmanId) {
		this.salesmanId = salesmanId;
	}
	
	//业务员
    public String getSalesMan() {
		return salesMan;
	}
    
	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}
    
	//来源
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
	
	//创建人ID（后台用户）
	public Integer getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}
    
	//最后更新人ID（后台用户）
	public Integer getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(Integer updaterId) {
		this.updaterId = updaterId;
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
}