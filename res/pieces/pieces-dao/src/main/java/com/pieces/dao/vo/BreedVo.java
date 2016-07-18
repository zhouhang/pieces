package com.pieces.dao.vo;

public class BreedVo {
	private String id;
	//类别id
	private Integer classifyId;
	//类别中文
	private String classifyName;
	//名称
	private String name;
	//别名
	private String aliases;
	//规格
	private String specifications;
	//产地
	private String place;
	//等级
	private String level;
	public Integer getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAliases() {
		return aliases;
	}
	public void setAliases(String aliases) {
		this.aliases = aliases;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
}
