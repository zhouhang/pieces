package com.jointown.zy.common.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.CompanyCertifyDao;
import com.jointown.zy.common.model.CompanyCertify;

@Repository
public class CompanyCertifyDaoImpl extends BaseDaoImpl implements CompanyCertifyDao {

	@Override
	public int addCompanyCertify(CompanyCertify companyCertify) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.insert("insertCompanyCertify",companyCertify);
	}

	@Override
	public CompanyCertify findCompanyCertifyByUserId(Integer userId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectOne("findCompanyCertifyByUserId", userId);
	}
	
	@Override
	public CompanyCertify findCompanyCertifyByUserId1(Integer userId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectOne("findCompanyCertifyByUserId1", userId);
	}
	
	
	/**
	 * 根据用户id查询认证企业认证信息
	 * @author zhouji
	 * @param userId
	 */
	public CompanyCertify  findReallyCompanyCertifyByUserId(Integer userId){
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectOne("com.jointown.zy.common.dao.CompanyCertifyDao.findReallyCompanyCertifyByUserId", userId);
	}

	@Override
	public CompanyCertify findCompanyCertifyByCondition(String userCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findSeqNext() {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectOne("findSeqNext");
	}

	public int updateCompanyCertify(CompanyCertify companyCertify) {
		return getSqlSession().update("com.jointown.zy.common.dao.CompanyCertifyDao.updateCompanyCertify", companyCertify);
	}
	
	@Override
	public int updateCompanyCertifyInfoAfterPass(CompanyCertify companyCertify) {
		return getSqlSession().update("com.jointown.zy.common.dao.CompanyCertifyDao.updateCompanyCertifyInfoAfterPass", companyCertify);
	}

	@Override
	public CompanyCertify findCompanyCertifyByCertifyId(Integer certifyId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectOne("com.jointown.zy.common.dao.CompanyCertifyDao.findCompanyCertifyByCertifyId", certifyId);
	}
	
}
