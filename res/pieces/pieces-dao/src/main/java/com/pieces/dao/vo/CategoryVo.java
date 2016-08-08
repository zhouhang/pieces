package com.pieces.dao.vo;

import java.lang.reflect.Field;

public class CategoryVo{

	private Integer id;
	
	private String name;

	private String pinyin;

	private String classifyName;
	
	private String parentId;
	//别名
	private String aliases;
	
	private String status;
	
	//类目级别 1 代表一级商品类别. 2 代表二级商品类别.
	private Integer level;
	
	private String specs;
	
	private String origins;
	
	private String levels;

	private String[] pinyins;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getAliases() {
		return aliases;
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String[] getPinyins() {
		return pinyins;
	}

	public void setPinyins(String[] pinyins) {
		this.pinyins = pinyins;
	}

	@Override
    public String toString() {
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        try {
            for(Field field : fields){
                if(field.get(this)!=null){
                    sb.append("&").append(field.getName()).append("=").append(field.get(this).toString());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}