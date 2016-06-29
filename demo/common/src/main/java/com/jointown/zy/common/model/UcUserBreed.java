package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UcUserBreed
 * @Description: 经营品种model
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
public class UcUserBreed implements Serializable {
	
	/** TODO */
	private static final long serialVersionUID = 2909280091643194825L;
	/**会员经营品种表ID*/
	private Long seqId;
	/**会员ID*/
	private Long userId;
	/**品种ID*/
	private Long breedId;
	/**品种名称*/
	private String breedName;
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
	
	public Long getSeqId() {
		return seqId;
	}
	public void setSeqId(Long seqId) {
		this.seqId = seqId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getBreedId() {
		return breedId;
	}
	public void setBreedId(Long breedId) {
		this.breedId = breedId;
	}
	public String getBreedName() {
		return breedName;
	}
	public void setBreedName(String breedName) {
		this.breedName = breedName;
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
