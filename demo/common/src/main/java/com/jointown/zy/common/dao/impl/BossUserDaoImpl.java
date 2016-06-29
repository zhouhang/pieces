package com.jointown.zy.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.BossUserRole;
import com.jointown.zy.common.model.Organization;
import com.jointown.zy.common.model.Page;

@Repository
public class BossUserDaoImpl extends BaseDaoImpl implements BossUserDao {

	@Override
	public void addBossUser(BossUser bossUser) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.insert("insertBossUser",bossUser);
	}

	@Override
	public void deleteBossUser(BossUser bossUser) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.update("deleteOrganization",bossUser);
	}

	@Override
	public int alterBossUser(BossUser bossUser) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.update("updateBossUser", bossUser);
	}

	@Override
	public List<BossUser> getBossUsersByOrgId(Integer orgId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossUser> bossuser = sqlSession.selectList("com.jointown.zy.common.dao.BossUserDao.findBossUserByOrgId",orgId);
		return bossuser;
	}

	@Override
	public List<BossUser> getBossUsersByCondition(Page page) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossUser> bossuser = sqlSession.selectList("com.jointown.zy.common.dao.BossUserDao.findBossUsersByCondition",page);
		return bossuser;
	}

	@Override
	public BossUser findBossUserByUserCode(String userCode,boolean...ignoreStatus) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		Map <Object,Object> queryMap = new HashMap<Object, Object>();
		queryMap.put("userCode", userCode);
		if(ignoreStatus.length>0&&ignoreStatus[0]){//忽略状态
			queryMap.put("ignoreStatus", "true");
		}
		List<BossUser> list = sqlSession.selectList("findBossUserByUserCode", queryMap);
		return CollectionUtils.isNotEmpty(list)?list.get(0):null;
	}

	@Override
	public BossUser findBossUserByUserId(String userId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectOne("findBossUserById", userId);
	}

	@Override
	public void addBossUserRole(BossUserRole userRole) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.insert("insertBossUserRole",userRole);
	}

	@Override
	public void alertBossUserRole(BossUserRole userRole) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.update("updateBossUserRole",userRole);
	}

	@Override
	public BossUserRole findBossUserRoleByPK(BossUserRole bossUserRole) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectOne("findBossUserRoleByPK", bossUserRole);
	}
	
	@Override
	public List<BossUserRole> findBossUserRoleByRoleID(BossUserRole bossUserRole) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectList("findBossUserRoleByRoleID", bossUserRole);
	}

	@Override
	public List<BossUser> getCurAndSubBossUsersByOrgId(Integer orgId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectList("com.jointown.zy.common.dao.BossUserDao.findCurAndSubBossUserByOrgId", orgId);
	}

	@Override
	public BossUser getBossUserByUserId(Long userId, boolean... ignoreStatus) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		Map <Object,Object> queryMap = new HashMap<Object, Object>();
		queryMap.put("userId", userId);
		if(ignoreStatus.length>0&&ignoreStatus[0]){//忽略状态
			queryMap.put("ignoreStatus", "true");
		}
		List<BossUser> list = sqlSession.selectList("com.jointown.zy.common.dao.BossUserDao.findBossUserByUserId", queryMap);
		return CollectionUtils.isNotEmpty(list)?list.get(0):null;
	}

}
