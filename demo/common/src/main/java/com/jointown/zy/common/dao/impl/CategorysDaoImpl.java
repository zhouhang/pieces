package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.CategorysDao;
import com.jointown.zy.common.model.Categorys;

@Repository
public class CategorysDaoImpl extends BaseDaoImpl implements CategorysDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(Categorys record) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.insert("com.jointown.zy.common.dao.CategorysMapper.insert", record);
	}

	@Override
	public int insertSelective(Categorys record) {
		return 0;
	}

	@Override
	public Categorys selectByPrimaryKey(Long id) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectOne("com.jointown.zy.common.dao.CategorysMapper.selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(Categorys record) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.update("com.jointown.zy.common.dao.CategorysMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(Categorys record) {
		return 0;
	}

	@Override
	public long selectCategorysSeq() {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectOne("com.jointown.zy.common.dao.CategorysMapper.selectCategorysSeq");
	}

	@Override
	public List<Categorys> selectList(Categorys record) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectList("com.jointown.zy.common.dao.CategorysMapper.selectList",record);
	}

	@Override
	public List<Categorys> getCategorysByNameAndClassName(Long breedId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectList("com.jointown.zy.common.dao.CategorysMapper.selectCategorysByName",breedId);
	}

}
