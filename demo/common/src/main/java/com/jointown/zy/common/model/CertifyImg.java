package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

public class CertifyImg implements Serializable {
	private Integer imgId;
	//认证信息表ID
	private Integer certifyId;
	//0:身份证正面(原) 1：身份证正面(小)2:身份证正面(大)3:身份证反面(原)4:身份证反面(小)5:身份证反面(大)6:营业执照(原)7:营业执照(小)8:营业执照(大)9:组织机构代码证(原)10:组织机构代码证(小)11:组织机构代码证(大)
	private Integer type;
	//图片服务器地址
	private String path;
	//上传时间
	private Date createTime;
	//修改时间
	private Date updateTime;
	//状态
	private Integer status;
	public CertifyImg() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CertifyImg(Integer imgId, Integer certifyId, Integer type,
			String path, Date createTime, Date updateTime, Integer status) {
		super();
		this.imgId = imgId;
		this.certifyId = certifyId;
		this.type = type;
		this.path = path;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.status = status;
	}
	public Integer getImgId() {
		return imgId;
	}
	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}
	public Integer getCertifyId() {
		return certifyId;
	}
	public void setCertifyId(Integer certifyId) {
		this.certifyId = certifyId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
