package com.pieces.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Code;
import com.pieces.dao.vo.BreedVo;
import com.pieces.dao.vo.CategoryVo;

public interface CategoryService extends ICommonService<Category> {
	//获取分类
	public PageInfo<Category> findClassify(CategoryVo t, int pageNum, int pageSize);
	//获取分类
	public List<Category> findClassify(CategoryVo t);
	//修改分类
	public int updateClassify(String classifyName,int id);
	//新增分类
	public int addClassify(String classifyName);
	//通过品种名称查询品种
	public List<Category> findBreedByName(String breedName);
	//新增品种
	public void addBreed(BreedVo bvo);
	//修改品种
	public void updateBreed(BreedVo bvo);
	//获取分类
	public PageInfo<CategoryVo> findBreed(CategoryVo vo, int pageNum, int pageSize);
	
	public BreedVo getBreedById(Integer id);
	//通过propoty查询品种属性
	public List<Code> findCode(String propoty);
	//public String getbreedProtity(Integer id, Integer codeType);
	public List<CategoryVo> findBreedByParentId(Integer parentId);
	
	public List<Code> findCodeByString(String str);
	List<Code> findCode(Integer breedId, String type);
	public List<CategoryVo> findBreedNoPage(CategoryVo categoryVo);

	public List<CategoryVo> findByLevelAndPinyin(Integer level,String pinyin);

	void allCategory2Pinyin();
	
}
