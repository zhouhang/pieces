package com.pieces.dao.model;

import java.io.Serializable;


/**
 * 新客询价的附件详情
 * 目前只存有图片
 */
public class AnonEnquiryDetail  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer anonEnquiryId;
	
	//类型 0:商品列表 1：附件内容
	private Integer type;
	
	//商品列表,附件json文本
	private String content;
	
	//附件地址
	private String attachmentUrl;
	
	public AnonEnquiryDetail(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getAnonEnquiryId() {
		return anonEnquiryId;
	}

	public void setAnonEnquiryId(Integer anonEnquiryId) {
		this.anonEnquiryId = anonEnquiryId;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}
	
}