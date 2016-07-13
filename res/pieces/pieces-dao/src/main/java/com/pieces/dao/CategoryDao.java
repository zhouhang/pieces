package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Category;
import com.pieces.dao.vo.CategoryVo;

public interface CategoryDao extends ICommonDao<Category>{
	//根据条件查询分类表
	public PageInfo<Category> findClassify(Category t, int pageNum, int pageSize);
	
	//根据条件查询品种表
	public PageInfo<Category> findBreed(CategoryVo vo, int pageNum, int pageSize);
}
