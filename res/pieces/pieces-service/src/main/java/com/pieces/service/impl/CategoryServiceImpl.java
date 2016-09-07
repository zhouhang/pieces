package com.pieces.service.impl;

import java.util.*;

import com.pieces.dao.ICommonDao;
import com.pieces.dao.vo.HomeCategoryVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.enums.CategoryEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import com.pieces.tools.utils.PinyinUtil;
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
public class CategoryServiceImpl extends AbsCommonService<Category> implements CategoryService {

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
	@Transactional
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
		t.setAliases(classifyName);
		return this.update(t);
	}

	@Override
	public int addClassify(String classifyName) {
		Category t = new Category();
		t.setName(classifyName);
		t.setParentId(0);
		t.setAliases(classifyName);
		t.setCreateTime(new Date());
		t.setLevel(CategoryEnum.LEVEL_CATEGORY.getValue());
		t.setStatus(CategoryEnum.STATUS_VALID.getValue());
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
		ca.setStatus(CategoryEnum.STATUS_VALID.getValue());
		ca.setLevel(CategoryEnum.LEVEL_BREED.getValue());
		ca.setCreateTime(new Date());
		String pinyin = PinyinUtil.field2Pinyin(ca.getName());
		ca.setPinyin(pinyin);
		categoryDao.create(ca);
		bvo.setId(ca.getId());
	}
	
	/**
	 * 更新品种
	 */
	@Override
	@Transactional
	public void updateBreed(BreedVo bvo) {
		//修改category
		Category ca = new Category();
		BeanUtils.copyProperties(bvo, ca);
		ca.setParentId(bvo.getClassifyId());
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
		BeanUtils.copyProperties(category, bvo);
		bvo.setClassifyName(parent.getName());
		bvo.setClassifyId(category.getParentId());
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
		if(StringUtils.isBlank(str)){
			return null;
		}
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


	@Override
	public List<CategoryVo> findByLevelAndPinyin(Integer level, Integer parentId, String pinyin,Integer pageSize) {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setLevel(level);
		categoryVo.setParentId(parentId);
		if(StringUtils.isNotBlank(pinyin)){
			categoryVo.setPinyins(pinyin.split(","));
		}
		return pageSize==null?categoryDao.findByLevelAndPinyin(categoryVo):categoryDao.findByLevelAndPinyin(categoryVo,pageSize);
	}

	@Override
	public List<CategoryVo> findByLevelAndPinyin(Integer level, Integer parentId, String pinyin) {
		return findByLevelAndPinyin( level,parentId,pinyin,null);
	}


	@Override
	@Transactional
	public void allCategory2Pinyin() {
		List<Category> categoryList = this.findAll();
		for(Category category :categoryList){
			String name = category.getName();
			String pinyin = PinyinUtil.field2Pinyin(name);
			category.setPinyin(pinyin);
			this.update(category);
		}
	}

	@Override
	public List<Category> findByIds(List<Integer> ids) {
		return categoryDao.findByIds(ids);
	}

	@Override
	public List<Category> findByIds(String ids) {
		List<Integer> list = new ArrayList<>();
		for(String id :ids.split(",")){
			list.add(Integer.parseInt(id));
		}
		return categoryDao.findByIds(list);
	}



	@Override
	public List<HomeCategoryVo> findHomeCategoryByIds(List<Integer> ids) {
		return categoryDao.findHomeCategoryByIds(ids);
	}


	@Override
	public List<CategoryVo> menuCategoryBreed(Integer parentId, String letter) {
		String pinyin = letterShift(letter,false);
		List<CategoryVo> breedList=	findByLevelAndPinyin(2,parentId,pinyin,30);
		return breedList;
	}

	@Override
	public Category findByNameAndLevel(String name, Integer level) {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName(name);
		categoryVo.setLevel(level);
		Category category =	categoryDao.findByNameAndLevel(categoryVo);
		return category;
	}

	private String letterShift(String letter,Boolean capital){
		String[] letters =  new String[]{"A","B","C","D","E","F","G","H","I","J",
				"K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

		String[] lesArr  = letter.split("~");
		if(lesArr.length!=2){
			throw new RuntimeException("参数必须是A~H的格式!");
		}
		Integer  index1 = Arrays.binarySearch(letters,lesArr[0]);
		Integer  index2 = Arrays.binarySearch(letters,lesArr[1]);
		List<String> tempList = new ArrayList<>();
		for(int i = index1;i<=index2;i++){
			tempList.add(letters[i]);
		}
		String result =  StringUtils.join(tempList, ",");
		if(!capital){
			return result.toLowerCase();
		}
		return result;
	}

	@Override
	public ICommonDao<Category> getDao() {
		return categoryDao;
	}

	@Override
	public List<CategoryVo> findBreedByNameLx(CategoryVo categoryVo) {
		return categoryDao.findBreedByNameLx(categoryVo);
	}

}
