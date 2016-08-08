package com.pieces.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Category;
import com.pieces.dao.vo.CategoryVo;

public interface CategoryDao extends ICommonDao<Category>{
	//根据条件查询分类表,分页
	public PageInfo<Category> findClassify(CategoryVo t, int pageNum, int pageSize);
	//根据条件查询分类表,分页
	public List<Category> findClassify(CategoryVo t);
	
	//根据条件查询品种表
	public PageInfo<CategoryVo> findBreed(CategoryVo vo, int pageNum, int pageSize);
	
	//根据条件查询分类表,分页
	public List<Category> findBreedByName(String breedName);
	public List<CategoryVo> findBreedByParentId(Integer parentId);
	public List<CategoryVo> findBreedNoPage(CategoryVo categoryVo);


	public List<CategoryVo> findByLevelAndPinyin(CategoryVo categoryVo);
}
