package com.jointown.zy.common.model;

import java.io.Serializable;

/**
 * 文章详情表（所有文章列表对应的详情表）
 * @author Mr.songwei 
 * @date 2014年11月7日下午12:16:00
 */
public class CmsArticleData implements Serializable {
	private static final long serialVersionUID = 756954846817787478L;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCopyfrom() {
		return copyfrom;
	}
	public void setCopyfrom(String copyfrom) {
		if(null == copyfrom ||"".equals(copyfrom)){
			copyfrom = "中药材电商";
    	}
		this.copyfrom = copyfrom;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public int getAllow_comment() {
		return allowComment;
	}
	public void setAllow_comment(int allowComment) {
		this.allowComment = allowComment;
	}
	//编号
	private Long id;
	//文章详情
	private String content;
	//文章来源
	private String copyfrom;
	//相关文章
	private String relation;
	//是否允许评论(默认允许评论)
	private int allowComment;
	//默认构造函数
	public CmsArticleData() {
		super();
	}
	//带参构造函数
	public CmsArticleData(Long id, String content, String copyfrom,
			String relation, int allowComment) {
		super();
		this.id = id;
		this.content = content;
		this.copyfrom = copyfrom;
		this.relation = relation;
		this.allowComment = allowComment;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CmsArticleData other = (CmsArticleData) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
