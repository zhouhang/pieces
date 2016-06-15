package com.jointown.zy.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.IBossUserOrgRoleDao;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.BossUserOrg;
import com.jointown.zy.common.model.BossUserRole;

@Repository(value="bossUserOrgRoleDaoImpl")
public class BossUserOrgRoleDaoImpl extends BaseDaoImpl implements IBossUserOrgRoleDao {

	@Override
	public List<BossUserRole> selectBossUserRole(String userId) {
		return getSqlSession().selectList("selectBossUserRole", userId);
	}
	
	@Override
	public List<BossUserOrg> selectBossUserOrg(String userCode) {
		return getSqlSession().selectList("selectBossUserOrg",userCode);
	}


	@Override
	public BossUser getBossUserPasswordInfo(String userCode) {
		return getSqlSession().selectOne("getBossUserPasswordInfo", userCode);
	}


	@Override
	public int modifyBossUserSecret(Map<String,String> map) {
		return getSqlSession().update("com.jointown.zy.common.dao.BossUserOrgRoleDao.modifyBossUserSecret", map);
	}
	
}
