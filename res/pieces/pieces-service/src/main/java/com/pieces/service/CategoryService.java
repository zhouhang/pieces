package com.pieces.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Code;
import com.pieces.dao.vo.BreedVo;

public interface CategoryService extends ICommonService<Category> {
	//获取分类
	public PageInfo<Category> findClassify(Category t, int pageNum, int pageSize);
	//获取分类
	public List<Category> findClassify(Category t);
	//修改分类
	public int updateClassify(String classifyName,int id);
	//新增分类
	public int addClassify(String classifyName);
	//通过品种名称查询品种
	public List<Category> findBreedByName(String breedName);
	//通过type_id查询品种属性
	public List<Code> findCode(Integer beedId,Integer typeId);
	//新增品种
	public void addBreed(BreedVo bvo);
	//修改品种
	public void updateBreed(BreedVo bvo);
}
