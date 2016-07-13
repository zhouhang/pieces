package com.pieces.dao.vo;

public class BreedVo {
	private int id;
	//类别
	private Integer classifyId;
	//名称
	private String name;
	//别名
	private String aliases;
	//规格
	private String specifications;
	//产地
	private String place;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
