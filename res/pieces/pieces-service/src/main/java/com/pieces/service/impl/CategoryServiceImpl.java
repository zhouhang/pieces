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
import com.pieces.service.enums.CommodityEnum;

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
		t.setPartenId(0);
		t.setAliases(classifyName);
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
		categoryDao.create(ca);
		Integer breedid = ca.getId();
		//保存code
		String[] specifications = bvo.getSpecifications().trim().replace("，", ",").split(",");
		for(int i=0 ; i<specifications.length ; i++){
			if(!specifications[i].trim().equals("")){
				Code spe= codeDao.getCode(i, specifications[i].trim(), breedid, CommodityEnum.COMMODITY_SPECIFICATIONS.getValue());
				codeDao.create(spe);
			}
		}
		String[] place = bvo.getPlace().trim().replace("，", ",").split(",");
		for(int i=0 ; i<place.length ; i++){
			if(!place[i].trim().equals("")){
				Code spe = codeDao.getCode(i, place[i].trim(), breedid, CommodityEnum.COMMODITY_PLACE.getValue());
				codeDao.create(spe);
			}
		}
		String[] level = bvo.getLevel().trim().replace("，", ",").split(",");
		for(int i=0 ; i<level.length ; i++){
			if(!level[i].trim().equals("")){
				Code spe = codeDao.getCode(i, level[i].trim(), breedid, CommodityEnum.COMMODITY_LEVEL.getValue());
				codeDao.create(spe);
			}
		}
	}
	
	/**
	 * 更新品种
	 */
	@Override
	@Transactional
	public void updateBreed(BreedVo bvo) {
		//1.修改code
		String[] specifications_new = bvo.getSpecifications().replace("，", ",").trim().split(",");
		codeDao.updateCode(specifications_new,Integer.parseInt(bvo.getId()),CommodityEnum.COMMODITY_SPECIFICATIONS.getValue());
		String[] place_new = bvo.getPlace().trim().replace("，", ",").split(",");
		codeDao.updateCode(place_new,Integer.parseInt(bvo.getId()),CommodityEnum.COMMODITY_PLACE.getValue());
		String[] level_new = bvo.getLevel().trim().replace("，", ",").split(",");
		codeDao.updateCode(level_new,Integer.parseInt(bvo.getId()),CommodityEnum.COMMODITY_LEVEL.getValue());
		//修改category
		Category ca = new Category();
		ca.setName(bvo.getName());
		ca.setPartenId(bvo.getClassifyId());
		ca.setAliases(bvo.getAliases());
		ca.setStatus(1);
		ca.setLevel(2);
		ca.setId(Integer.parseInt(bvo.getId()));
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
		Category parten = categoryDao.findById(category.getPartenId());
		String specifications = getbreedProtity(id,CommodityEnum.COMMODITY_SPECIFICATIONS.getValue());
		String places = getbreedProtity(id,CommodityEnum.COMMODITY_PLACE.getValue());
		String level = getbreedProtity(id,CommodityEnum.COMMODITY_LEVEL.getValue());
		BreedVo bvo = new BreedVo();
		bvo.setId(id.toString());
		bvo.setAliases(category.getAliases());
		bvo.setName(category.getName());
		bvo.setPlace(places);
		bvo.setSpecifications(specifications);
		bvo.setClassifyId(category.getPartenId());
		bvo.setClassifyName(parten.getName());
		bvo.setLevel(level);
		return bvo;
	}
	
	@Override
	public String getbreedProtity(Integer id,Integer codeType) {
		List<Code> list = findCode(id,codeType);
		String codes = "";
		for(Code code : list){
			codes = codes + code.getName() + ",";
		}
		codes = codes.substring(0, codes.length()-1);
		return codes;
	}

	@Override
	public List<Code> findCode(Integer beedId,Integer typeId) {
		Code code = new Code();
		code.setTypeId(typeId);
		code.setRelatedCode(beedId);
		code.setStatus(1);
		return codeDao.find(code);
	}


}
