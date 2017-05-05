package com.pieces.dao.model;

import java.io.Serializable;


/**
 * 用户提交资质审核时用来存储资质审核的图片
 */
public class QualificationPics  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//user_qualification_id
	private Integer qid;
	
	//图片地址
	private String pictureUrl;
	
	//图片顺序
	private Integer indexNum;
	
	//标记图片类型，暂为null
	private Integer type;
	
	public QualificationPics(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public Integer getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(Integer indexNum) {
		this.indexNum = indexNum;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}