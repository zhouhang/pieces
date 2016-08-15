package com.pieces.dao.vo;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.pieces.dao.model.Category;

public class CategoryVo extends Category{

	private String classifyName;
	
	private String[] pinyins;

	private Map<String,List<CategoryVo>>  categoryPinyin;

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String[] getPinyins() {
		return pinyins;
	}

	public void setPinyins(String[] pinyins) {
		this.pinyins = pinyins;
	}

	public Map<String, List<CategoryVo>> getCategoryPinyin() {
		return categoryPinyin;
	}

	public void setCategoryPinyin(Map<String, List<CategoryVo>> categoryPinyin) {
		this.categoryPinyin = categoryPinyin;
	}
}