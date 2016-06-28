package com.jointown.zy.common.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BreedCategoryDao;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BreedCategory;
@Repository
public class BreedCategoryDaoImpl extends BaseDaoImpl implements BreedCategoryDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.BreedCategoryMapper.deleteByPrimaryKey", id);
	}

	@Override
	public int insert(BreedCategory record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BreedCategoryMapper.insert", record);
	}

	@Override
	public int insertSelective(BreedCategory record) {
		return 0;
	}

	@Override
	public BreedCategory selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BreedCategory record) {
		return 0;
	}
	
	/**
	 * 功能：根据主键id修改排序规则
	 * 增加日期：2015.4.3
	 * 添加人：Mr.song
	 */
	public int updateIndex(HashMap<String,Object> map) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BreedCategoryMapper.updateIndex", map);
	}

	@Override
	public int updateByPrimaryKey(BreedCategory record) {
		return 0;
	}
	/**
	 * @author seven
	 * @descripton 逻辑删除BREED_CATEGORY中的关系
	 */
	@Override
	public int deleteLogicByBreedId(Breed breed) {
		BreedCategory breedCategory = new BreedCategory();
		breedCategory.setBreedId(breed.getBreedId());
		//状态值改为0,表示去掉关系
		breedCategory.setStatus(Short.parseShort("0"));
		return this.getSqlSession().update("com.jointown.zy.common.dao.BreedCategoryMapper.deleteLogicByBreedId", breedCategory);
	}

	@Override
	public List<BreedCategory> selectByBreedId(Long breedId) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.BreedCategoryMapper.selectByBreedId", breedId);
	}

	@Override
	public int deleteBreedCategoryBybreedId(Long breedId) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.BreedCategoryMapper.deleteBybreedId", breedId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int selectRules(HashMap map) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BreedCategoryMapper.selectRules",map);
	}
}
