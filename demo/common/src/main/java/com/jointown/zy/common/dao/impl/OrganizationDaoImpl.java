package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.OrganizationDao;
import com.jointown.zy.common.model.Organization;
/**
 * 组织机构DaoImpl
 * @author zhouji
 * 2014年11月6日下午4:53:12
 */
@Repository
public class OrganizationDaoImpl extends BaseDaoImpl implements OrganizationDao {

	@Override
	public void addOrganization(Organization organization) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.insert("addOrganization",organization);
	}

	@Override
	public void deleteOrganization(Organization organization) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.update("deleteOrganization", organization);
	}

	@Override
	public void alterOrganization(Organization organization) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.update("updateOrganization", organization);
	}

	@Override
	public List<Organization> getAllOrganization() {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<Organization> orglist = sqlSession.selectList("com.jointown.zy.common.dao.OrganizationDao.findOrganization");
		return orglist;
	}

	@Override
	public Organization getOrganizationById(Integer id) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		Organization org = sqlSession.selectOne("com.jointown.zy.common.dao.OrganizationDao.findOrganizationById", id);
		return org;
	}

	@Override
	public Organization getOrganizationByOrgName(String orgName) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		Organization org = sqlSession.selectOne("com.jointown.zy.common.dao.OrganizationDao.findOrganizationByOrgName", orgName);
		return org;
	}

	@Override
	public List<Organization> getOrganizationByPid(Integer pId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<Organization> orglist = sqlSession.selectList("com.jointown.zy.common.dao.OrganizationDao.findOrganizationByPId",pId);
		return orglist;
	}

}
