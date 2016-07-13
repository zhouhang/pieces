package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Category;

public interface CategoryService extends ICommonService<Category> {
	//获取分类
	public PageInfo<Category> findClassify(Category t, int pageNum, int pageSize);
	//修改分类
	public int updateClassify(String classifyName,int id);
	//新增分类
	public int addClassify(String classifyName);
}
