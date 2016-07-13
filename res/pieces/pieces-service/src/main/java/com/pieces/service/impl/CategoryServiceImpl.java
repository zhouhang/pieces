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
			Code spe= getCode(i, specifications[i].trim(), breedid, CommodityEnum.COMMODITY_SPECIFICATIONS.getValue());
			codeDao.create(spe);
		}
		String[] place = bvo.getPlace().trim().split(",");
		for(int i=0 ; i<place.length ; i++){
			Code spe = getCode(i, place[i].trim(), breedid, CommodityEnum.COMMODITY_PLACE.getValue());
			codeDao.create(spe);
		}
	}
	
	@Override
	@Transactional
	public void updateBreed(BreedVo bvo) {
		//1.修改code
		String[] specifications_new = bvo.getSpecifications().trim().split(",");
		updateCode(specifications_new,bvo.getId(),CommodityEnum.COMMODITY_SPECIFICATIONS.getValue());
		String[] place_new = bvo.getPlace().trim().split(",");
		updateCode(place_new,bvo.getId(),CommodityEnum.COMMODITY_PLACE.getValue());
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
	
	/**
	 * upate code
	 * @param newString 新的code字符串数组,如规格，产地
	 * @param relatedCode 关联的品种
	 * @param typeId code类型
	 */
	private void updateCode(String[] newString,Integer relatedCode,Integer typeId){
		//查询原始specifications
		Code code = new Code();
		code.setRelatedCode(relatedCode);
		code.setTypeId(typeId);
		List<Code> specifications_old = codeDao.find(code);
		//将两个新，旧两个转成map，便于比较
		Map<String,Code> old_set = new HashMap<String,Code>();
		Map<String,String> new_set = new HashMap<String,String>();
		Map<String,String> common_set = new HashMap<String,String>();
		for(Code code_old : specifications_old){
			old_set.put(code_old.getName(), code_old);
		}
		for(int i=0 ; i<newString.length ; i++){
			new_set.put(newString[i].trim(),newString[i].trim());
		}
		//求交集和需要删除的
		for(String key : old_set.keySet()){
			Code delet = old_set.get(key);
			if(new_set.containsKey(key)){
				common_set.put(key, key);
				if(delet.getStatus() != 1){
					delet.setStatus(1);
					codeDao.update(delet);
				}
			}else{
				
				delet.setStatus(-1);
				codeDao.update(delet);
			}
		}
		//新添加的的
		for(String key : new_set.keySet()){
			if(!common_set.containsKey(key)){
				int i = old_set.size();
				Code spe = getCode(i, key, relatedCode, typeId);
				codeDao.create(spe);
			}
		}
	}
	
	/**
	 * 根据条件构造code
	 * @param code
	 * @param name
	 * @param relatedCode
	 * @param typeId
	 * @return
	 */
	private Code getCode(int code,String name,Integer relatedCode,Integer typeId){
		Code spe = new Code();
		spe.setCode(String.valueOf(code));
		spe.setName(name);
		spe.setRelatedCode(relatedCode);
		spe.setStatus(1);
		spe.setTypeId(typeId);
		spe.setCreateTime(new Date());
		return spe;
	}
}
