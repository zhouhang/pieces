package com.jointown.zy.common.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
/**
 * wms 仓单接口，图片信息Dto
 * @author ldp
 * date 2015年3月23日
 * version 1.0
 */
public class WmsPicInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 图片类型*/
	private Integer picType;
	/** 图片地址*/
	private String picUrl;
	
	public WmsPicInfoDto() {
		super();
	}
	public WmsPicInfoDto(Integer picType, String picUrl) {
		super();
		this.picType = picType;
		this.picUrl = picUrl;
	}
	public Integer getPicType() {
		return picType;
	}
	public void setPicType(Integer picType) {
		this.picType = picType;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	

}
