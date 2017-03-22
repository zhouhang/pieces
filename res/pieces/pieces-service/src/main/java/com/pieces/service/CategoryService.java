package com.pieces.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Code;
import com.pieces.dao.vo.BreedVo;
import com.pieces.dao.vo.CategoryVo;
import com.pieces.dao.vo.HomeCategoryVo;

public interface CategoryService extends ICommonService<Category> {
	//获取分类
	public PageInfo<Category> findClassify(CategoryVo t, int pageNum, int pageSize);
	//获取分类
	public List<Category> findClassify(CategoryVo t);
	//修改分类
	public int updateClassify(Category category);
	//新增分类
	public int addClassify(Category category);
	//通过品种名称查询品种
	public List<CategoryVo> findBreedByName(String breedName);
	//新增品种
	public void addBreed(BreedVo bvo);
	//修改品种
	public void updateBreed(BreedVo bvo);
	//获取分类
	public PageInfo<CategoryVo> findBreed(CategoryVo vo, int pageNum, int pageSize);
	
	public BreedVo getBreedById(Integer id);
	//通过propoty查询品种属性
	public List<Code> findCode(String propoty);

	public List<CategoryVo> findBreedByParentId(Integer parentId);
	
	public List<Code> findCodeByString(String str);
	List<Code> findCode(Integer breedId, String type);
	public List<CategoryVo> findBreedNoPage(CategoryVo categoryVo);

	public List<CategoryVo> findByLevelAndPinyin(Integer level, Integer parentId, String pinyin,Integer pageSize);

	public List<CategoryVo> findByLevelAndPinyin(Integer level, Integer parentId, String pinyin);


	void allCategory2Pinyin();

	public List<Category> findByIds(List<Integer> ids);

	public List<Category> findByIds(String ids);

	public List<HomeCategoryVo>  findHomeCategoryByIds(List<Integer> ids);

	public List<CategoryVo> menuCategoryBreed(Integer parentId, String letter);


	public Category findByNameAndLevel(String name,Integer level);
	public List<CategoryVo> findBreedByNameLx(CategoryVo categoryVo);



}
