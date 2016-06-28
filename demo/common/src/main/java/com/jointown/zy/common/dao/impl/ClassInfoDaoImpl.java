package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.ClassInfoDao;
import com.jointown.zy.common.model.ClassInfo;

@Repository
public class ClassInfoDaoImpl extends BaseDaoImpl implements ClassInfoDao {

	@Override
	public int deleteByPrimaryKey(Long classId) {
		return 0;
	}

	@Override
	public int insert(ClassInfo record) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.insert("com.jointown.zy.common.dao.ClassInfoMapper.insert", record);
		
	}

	@Override
	public int insertSelective(ClassInfo record) {
		return 0;
	}

	@Override
	public ClassInfo selectByPrimaryKey(Long classId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectOne("com.jointown.zy.common.dao.ClassInfoMapper.selectByPrimaryKey", classId);
	}

	@Override
	public int updateByPrimaryKeySelective(ClassInfo record) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.update("com.jointown.zy.common.dao.ClassInfoMapper.updateByPrimaryKeySelective", record);
		
	}

	@Override
	public int updateByPrimaryKey(ClassInfo record) {
		return 0;
	}

	@Override
	public List<ClassInfo> selectList(ClassInfo record) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectList("com.jointown.zy.common.dao.ClassInfoMapper.selectList", record);
	}

}
