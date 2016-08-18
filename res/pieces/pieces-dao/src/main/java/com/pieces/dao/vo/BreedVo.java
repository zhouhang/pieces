package com.pieces.dao.vo;

import java.util.List;

import com.pieces.dao.model.Code;

public class BreedVo {
	private Integer id;
	//类别id
	private Integer classifyId;
	//类别中文
	private String classifyName;
	//名称
	private String name;
	//别名
	private String aliases;
	private String specs;
	
	private String origins;
	
	private String levels;
	
	private List<Code> specelist;
	
	private List<Code> originlist;
	
	private List<Code> levellist;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
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

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public String getOrigins() {
		return origins;
	}

	public void setOrigins(String origins) {
		this.origins = origins;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public List<Code> getSpecelist() {
		return specelist;
	}

	public void setSpecelist(List<Code> specelist) {
		this.specelist = specelist;
	}

	public List<Code> getOriginlist() {
		return originlist;
	}

	public void setOriginlist(List<Code> originlist) {
		this.originlist = originlist;
	}

	public List<Code> getLevellist() {
		return levellist;
	}

	public void setLevellist(List<Code> levellist) {
		this.levellist = levellist;
	}
	
	
	
}
