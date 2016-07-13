package com.pieces.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.CategoryDao;
import com.pieces.dao.CodeDao;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Code;
import com.pieces.dao.vo.BreedVo;
import com.pieces.service.CategoryService;
import com.pieces.service.enums.CommodityEnum;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private CodeDao codeDao;

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public PageInfo<Category> find(int pageNum, int pageSize) {
		return categoryDao.find(pageNum, pageSize);
	}

	@Override
	public Category findById(int id) {
		return categoryDao.findById(id);
	}

	@Override
	public int deleteById(int id) {
		return categoryDao.deleteById(id);
	}

	@Override
	public int create(Category t) {
		return categoryDao.create(t);
	}

	@Override
	public int update(Category t) {
		return categoryDao.update(t);
	}

	@Override
	public PageInfo<Category> findClassify(Category t, int pageNum, int pageSize) {
		return categoryDao.findClassify(t, pageNum, pageSize);
	}
	
	@Override
	public List<Category> findClassify(Category t) {
		return categoryDao.findClassify(t);
	}

	@Override
	public int updateClassify(String classifyName, int id) {
		Category t = new Category();
		t.setId(id);
		t.setName(classifyName);
		t.setPartenId(0);
		t.setAliases(classifyName);
		t.setCreateTime(new Date());
		t.setLevel(1);
		t.setStatus(1);
		return this.update(t);
	}

	@Override
	public int addClassify(String classifyName) {
		Category t = new Category();
		t.setName(classifyName);
		t.setPartenId(0);
		t.setAliases(classifyName);
		t.setCreateTime(new Date());
		t.setLevel(1);
		t.setStatus(1);
		return this.create(t);
	}

	@Override
	public List<Category> findBreedByName(String breedName) {
		return categoryDao.findBreedByName(breedName);
	}

	@Override
	public List<Code> findCode(Integer beedId,Integer typeId) {
		Code code = new Code();
		code.setTypeId(typeId);
		code.setRelatedCode(beedId);
		code.setStatus(1);
		return codeDao.find(code);
	}
	
	/**
	 * 添加品种
	 */
	@Override
	@Transactional
	public void addBreed(BreedVo bvo) {
		//保存品种
		Category ca = new Category();
		ca.setName(bvo.getName());
		ca.setPartenId(bvo.getClassifyId());
		ca.setAliases(bvo.getAliases());
		ca.setStatus(1);
		ca.setLevel(2);
		ca.setCreateTime(new Date());
		Integer breedid = categoryDao.create(ca);
		//保存code
		String[] specifications = bvo.getSpecifications().trim().split(",");
		for(int i=0 ; i<specifications.length ; i++){
			Code spe= codeDao.getCode(i, specifications[i].trim(), breedid, CommodityEnum.COMMODITY_SPECIFICATIONS.getValue());
			codeDao.create(spe);
		}
		String[] place = bvo.getPlace().trim().split(",");
		for(int i=0 ; i<place.length ; i++){
			Code spe = codeDao.getCode(i, place[i].trim(), breedid, CommodityEnum.COMMODITY_PLACE.getValue());
			codeDao.create(spe);
		}
	}
	
	/**
	 * 更新品种
	 */
	@Override
	@Transactional
	public void updateBreed(BreedVo bvo) {
		//1.修改code
		String[] specifications_new = bvo.getSpecifications().trim().split(",");
		codeDao.updateCode(specifications_new,bvo.getId(),CommodityEnum.COMMODITY_SPECIFICATIONS.getValue());
		String[] place_new = bvo.getPlace().trim().split(",");
		codeDao.updateCode(place_new,bvo.getId(),CommodityEnum.COMMODITY_PLACE.getValue());
		//修改category
		Category ca = new Category();
		ca.setName(bvo.getName());
		ca.setPartenId(bvo.getClassifyId());
		ca.setAliases(bvo.getAliases());
		ca.setStatus(1);
		ca.setLevel(2);
		ca.setId(bvo.getId());
		categoryDao.update(ca);
	}
}
