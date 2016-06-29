package com.jointown.zy.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BreedDao;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.Page;

@Repository
public class BreedDaoImpl extends BaseDaoImpl implements BreedDao {

	@Override
	public int deleteByPrimaryKey(Long breedId) {
		return 0;
	}

	@Override
	public int insert(Breed record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BreedMapper.insert", record);
	}

	@Override
	public int insertSelective(Breed record) {
		return 0;
	}

	@Override
	public Breed selectByPrimaryKey(Long breedId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BreedMapper.selectByPrimaryKey", breedId);
	}

	@Override
	public int updateByPrimaryKeySelective(Breed record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BreedMapper.updateByPrimaryKeySelective", record);
	}

		
	@Override
	public int updateByPrimaryKeyWithBLOBs(Breed record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Breed record) {
		return 0;
	}

	@Override
	public List<Breed> selectBreedBy(Page<Map<String, Object>> page) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectList("com.jointown.zy.common.dao.BreedMapper.selectBreedByPz",page);
	}
	/**
	 * @author seven
	 * @description 逻辑删除
	 */
	@Override
	public int deleteBreedLogic(Breed breed) {
		//删除关系Breed的逻辑关系(只穿breed的ID和删除状态)
		Breed deleteBreed = new Breed();
		deleteBreed.setBreedId(breed.getBreedId());
		deleteBreed.setStatus(Short.parseShort("0"));
		return this.getSqlSession().update("com.jointown.zy.common.dao.BreedMapper.updateByPrimaryKeySelective", deleteBreed);
	}

	

	@Override
	public List<Breed> selectBreedByCategory(Page page) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectList("com.jointown.zy.common.dao.BreedMapper.selectBreedByCategory",page);
	}

	@Override
	public List<Map<String, Object>> selectBreedInfoBy(
			Page<Map<String, Object>> page) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectList("com.jointown.zy.common.dao.BreedMapper.selectBreedInfoBy",page);
	}

	@Override
	public List<Breed> selectBreedByCat(Long categoryId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectList("com.jointown.zy.common.dao.BreedMapper.selectBreedByCat",categoryId);
	}

	@Override
	public List<Breed> selectList(Breed breed) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectList("com.jointown.zy.common.dao.BreedMapper.selectList",breed);
	}

	@Override
	public Breed selectBreedByBreedName(String breedName) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectOne("com.jointown.zy.common.dao.BreedMapper.selectBreedByBreedName",breedName);
	}
	
	@Override
	public List<Breed> selectBreedsByName(String name) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectList("com.jointown.zy.common.dao.BreedMapper.selectBreedsByName",name);
	}
	
	@Override
	public List<Breed> selectBreedByKeyword(String breedName) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectList("com.jointown.zy.common.dao.BreedMapper.selectBreedByKeyword",breedName);
	}
}
