package com.pieces.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.CategoryDao;
import com.pieces.dao.CodeDao;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Code;
import com.pieces.dao.vo.BreedVo;
import com.pieces.dao.vo.CategoryVo;
import com.pieces.service.CategoryService;
import com.pieces.service.enums.CodeEnum;

@Service
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
	public PageInfo<Category> findClassify(CategoryVo t, int pageNum, int pageSize) {
		return categoryDao.findClassify(t, pageNum, pageSize);
	}
	
	@Override
	public List<Category> findClassify(CategoryVo t) {
		return categoryDao.findClassify(t);
	}

	@Override
	public int updateClassify(String classifyName, int id) {
		Category t = new Category();
		t.setId(id);
		t.setName(classifyName);
		t.setParentId(0);
		t.setAliases(classifyName);
		t.setLevel(1);
		t.setStatus(1);
		return this.update(t);
	}

	@Override
	public int addClassify(String classifyName) {
		Category t = new Category();
		t.setName(classifyName);
		t.setParentId(0);
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
	public List<CategoryVo> findBreedByParentId(Integer parentId) {
		return categoryDao.findBreedByParentId(parentId);
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
		ca.setParentId(bvo.getClassifyId());
		ca.setAliases(bvo.getAliases());
		ca.setStatus(1);
		ca.setLevel(2);
		ca.setCreateTime(new Date());
		ca.setSpecs(bvo.getSpece());
		ca.setOrigins(bvo.getOrigins());
		ca.setLevels(bvo.getLevels());
		categoryDao.create(ca);
	}
	
	/**
	 * 更新品种
	 */
	@Override
	@Transactional
	public void updateBreed(BreedVo bvo) {
		//修改category
		Category ca = new Category();
		ca.setName(bvo.getName());
		ca.setParentId(bvo.getClassifyId());
		ca.setAliases(bvo.getAliases());
		ca.setStatus(1);
		ca.setLevel(2);
		ca.setId(Integer.parseInt(bvo.getId()));
		ca.setSpecs(bvo.getSpece());
		ca.setOrigins(bvo.getOrigins());
		ca.setLevels(bvo.getLevels());
		categoryDao.update(ca);
	}
	
	/**
	 * 获取品种列表
	 */
	@Override
	public PageInfo<CategoryVo> findBreed(CategoryVo vo, int pageNum, int pageSize) {
		return categoryDao.findBreed(vo, pageNum, pageSize);
	}
	
	/**
	 * 获取品种信息
	 */
	@Override
	public BreedVo getBreedById(Integer id) {
		Category category = categoryDao.findById(id);
		Category parent = categoryDao.findById(category.getParentId());
		BreedVo bvo = new BreedVo();
		bvo.setId(id.toString());
		bvo.setAliases(category.getAliases());
		bvo.setName(category.getName());
		bvo.setSpece(category.getSpecs());
		bvo.setOrigins(category.getOrigins());
		bvo.setClassifyId(category.getParentId());
		bvo.setClassifyName(parent.getName());
		bvo.setLevels(category.getLevels());
		return bvo;
	}

	@Override
	public List<Code> findCode(String propoty) {
		Code code = new Code();
		code.setCode(propoty);
		return codeDao.find(code);
	}

	@Override
	public List<Code> findCodeByString(String str) {
		return codeDao.findByString(str);
	}

	/**
	 * TODO: 枚举里面要加个getByName 方法.
	 * @param breedId
	 * @param type
     * @return
     */
	@Override
	public List<Code> findCode(Integer breedId , String type) {
		Category category = this.findById(breedId);
		if(CodeEnum.Type.SPEC.name().equals(type)){
			return this.findCodeByString(category.getSpecs());
		}
		if(CodeEnum.Type.ORIGIN.name().equals(type)){
			return this.findCodeByString(category.getOrigins());
		}
		if(CodeEnum.Type.LEVEL.name().equals(type)){
			return this.findCodeByString(category.getLevels());
		}
		return null;
	}

	@Override
	public List<CategoryVo> findBreedNoPage(CategoryVo categoryVo) {
		return categoryDao.findBreedNoPage(categoryVo);
	}
	
	
}
