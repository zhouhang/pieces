package com.pieces.dao;

import java.util.Collection;
import java.util.List;
import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.Category;
import com.pieces.dao.vo.CategoryVo;
import com.pieces.dao.vo.HomeCategoryVo;

@AutoMapper
public interface CategoryDao extends ICommonDao<Category>{
	//根据条件查询分类表,分页
	public List<Category> findClassify(CategoryVo t);
	
	//根据条件查询品种表
	public List<CategoryVo> findBreed(CategoryVo vo);

	public List<CategoryVo> findBreedByParentId(Integer parentId);

	public List<CategoryVo> findBreedNoPage(CategoryVo categoryVo);

	public List<CategoryVo> findByLevelAndPinyin(CategoryVo categoryVo);

	public List<Category>  findByIds(Collection<Integer> collection);

	public List<HomeCategoryVo>  findHomeCategoryByIds(Collection<Integer> collection);

	public Category findByNameAndLevel(CategoryVo categoryVo);
	public List<CategoryVo> findBreedByNameLx(CategoryVo categoryVo);

}
