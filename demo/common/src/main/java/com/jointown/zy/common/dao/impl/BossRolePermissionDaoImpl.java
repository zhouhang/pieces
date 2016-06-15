package com.jointown.zy.common.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BossRolePermissionDao;
import com.jointown.zy.common.model.BossRolePermission;

/**
 * 后台角色权限DaoImpl
 * @author biran
 * 2014-12-05
 */
@Repository
public class BossRolePermissionDaoImpl extends BaseDaoImpl implements BossRolePermissionDao {

	@Override
	public void addBossRolePermission(BossRolePermission bossRolePermission) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.insert("addBossRolePermission",bossRolePermission);
	}
	
	
	 //删除该用户下，该0级权限下，所有下级权限（1,2,3级）
	@Override
	public void deleBossRolePermissionByLevel0Id(Integer roleId,Integer parentId) {
		Map<String, Object> map=new HashMap();
		map.put("roleId", roleId);
		map.put("parentId", parentId);
		map.put("now", new Date());
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.update("deleBossRolePermissionByLevel0Id",map);
	}
	
	 //根据主键，权限ID,角色ID查找角色权限
	@Override
	public BossRolePermission findBossRolePermissionByPK(Integer permissionId,Integer roleId) {
			Map<String, Object> map=new HashMap();
			map.put("roleId", roleId);
			map.put("permissionId", permissionId);
			SqlSessionTemplate sqlSession = this.getSqlSession();
			return sqlSession.selectOne("findBossRolePermissionByPK",map);
	}
	
	 //通过主键更新角色权限
	@Override
	public void  UpdateBossRolePermissionByPK(BossRolePermission rolePermission) {
			SqlSessionTemplate sqlSession = this.getSqlSession();
			sqlSession.update("UpdateBossRolePermissionByPK",rolePermission);
	}
	
	//根据第2级菜单，找到第1级目录,参数：0级系统ID,角色ID,子菜单等级
	@Override
	public   List<BossRolePermission> findBossRolePermissionLevel(Integer parentId,Integer roleId,Integer sonType) {
		Map<String, Integer> map=new HashMap();
		map.put("roleId", roleId);
		map.put("parentId", parentId);
		map.put("sonType", sonType);
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectList("findBossRolePermissionLevel",map);
	}
	
	
}
