package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BossRoleDao;
import com.jointown.zy.common.model.BossRole;
import com.jointown.zy.common.model.Organization;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BossRoleVO;

/**
 * 后台角色DaoImpl
 * @author biran
 * 2014-11-27
 */
@Repository
public class BossRoleDaoImpl extends BaseDaoImpl implements BossRoleDao {

	@Override
	public void addBossRole(BossRole bossRole) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.insert("addBossRole",bossRole);
	}
	
	
	@Override
	public void UpdateBossRoleByName(BossRole bossRole) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.update("UpdateBossRoleByName", bossRole);
	}
	
	@Override
	public void UpdateBossRole(BossRole bossRole) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.update("UpdateBossRole", bossRole);
	}
	
	@Override
	public void deleteBossRoleById(BossRole bossRole) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.update("deleteBossRoleById", bossRole);
	}
	
	@Override
	public List<BossRole> findBossRoles() {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossRole> Rolelist = sqlSession.selectList("findBossRoles1");
		return Rolelist;
	}
	
	@Override
	public List<BossRole> findBossRoles(BossRoleVO bossRole) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossRole> Rolelist = sqlSession.selectList("findBossRoles",bossRole);
		return Rolelist;
	}
	
	@Override
	public List<BossRole> findBossRoles(Page page) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossRole> Rolelist = sqlSession.selectList("findBossRolesByPage",page);
		return Rolelist;
	}

	@Override
	public BossRole findBossRoleByid(Integer id) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		BossRole Role = sqlSession.selectOne("findBossRoleByid", id);
		return Role;
	}
	
	@Override
	public  List<BossRole>  findBossRoleByUserCode(String UserCode){
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossRole> Rolelist = sqlSession.selectList("findBossRoleByUserCode",UserCode);
		return Rolelist;
	}

	@Override
	public  BossRole findBossRoleByRoleName(String RoleName){
		SqlSessionTemplate sqlSession = this.getSqlSession();
		BossRole role = sqlSession.selectOne("findBossRoleByRoleName",RoleName);
		return role;
	}
	
	@Override
	public  BossRole findBossRoleByRoleId(String roleId){
		SqlSessionTemplate sqlSession = this.getSqlSession();
		BossRole role = sqlSession.selectOne("findBossRoleByRoleId",roleId);
		return role;
	}
	
	



}
