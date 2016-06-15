package com.jointown.zy.common.vo;
/**
 * 类目VO
 * @author wangjunhu
 *	2014-12-24
 */
public class CategorysVo {
	
	private Long id;
	private Long classId;
	private String categorysName;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getCategorysName() {
		return categorysName;
	}
	public void setCategorysName(String categorysName) {
		this.categorysName = categorysName;
	}
}
