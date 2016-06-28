package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BossRolePermissionDao;
import com.jointown.zy.common.model.BossPermission;
import com.jointown.zy.common.model.BossRolePermission;
import com.jointown.zy.common.service.BossRolePermissionService;



/**
 * 后台角色权限ServiceImpl
 * @author biran
 * 2014-12-05
 */
@Service
public class BossRolePermissionServiceImpl implements BossRolePermissionService {
	@Autowired
	private BossRolePermissionDao bossRolePermissionDao;
	

	//  更新后台角色权限
	 @Override
	 public void saveRolePermission(Integer parentId,Integer roleId,String[] permissionIds){
		 //先删除该用户下，该0级权限下，所有下级权限（1,2,3级） 递归删除	
		 bossRolePermissionDao.deleBossRolePermissionByLevel0Id(roleId,parentId);
		 if(permissionIds!=null&&permissionIds.length>0){
			 //插入2，3级菜单按钮
			 for(int i=0;i<permissionIds.length;i++){
				 Integer permissionId =Integer.valueOf(permissionIds[i]);
				 InsertBossRolePermission(permissionId,roleId);
			 }
		 }
		
		//根据第2级菜单，找到第1级目录,参数：0级系统ID,角色ID
		 List <BossRolePermission> list1=bossRolePermissionDao.findBossRolePermissionLevel(parentId,roleId,2);
		 for(BossRolePermission p:list1){
				Integer permissionId=p.getPermissionId();
				InsertBossRolePermission(permissionId,roleId);
			}
		//根据第2级菜单，找到第1级目录,参数：0级系统ID,角色ID
		 List <BossRolePermission> list0=bossRolePermissionDao.findBossRolePermissionLevel(parentId,roleId,1);
		 for(BossRolePermission p:list0){
				Integer permissionId=p.getPermissionId();
				InsertBossRolePermission(permissionId,roleId);
			}
		 
		 return;
	 }
	 
	 //根据状态，插入或更新角色权限
	 public void InsertBossRolePermission(Integer permissionId,Integer roleId){
//		//先判断是否有状态为删除的角色权限，有的话更新，否则插入
//		
//		BossRolePermission RolePermission=bossRolePermissionDao.findBossRolePermissionByPK(permissionId,roleId);
//		if(RolePermission!=null){
//			if(RolePermission.getStatus()==1){//更新
//				BossRolePermission rolePermission=new BossRolePermission();
//				Date date = new Date();
//				rolePermission.setPermissionId(permissionId);
//				rolePermission.setRoleId(roleId);
//				rolePermission.setUpdateTime(date);
//				rolePermission.setStatus(0);
//				bossRolePermissionDao.UpdateBossRolePermissionByPK(rolePermission);
//			}
//		}else{
//			BossRolePermission newrolePermission=new BossRolePermission();
//			Date date = new Date();
//			newrolePermission.setPermissionId(permissionId);
//			newrolePermission.setRoleId(roleId);
//			newrolePermission.setStatus(0);
//			newrolePermission.setCreateTime(date);
//			newrolePermission.setUpdateTime(date);
//			
//			//直接插入
//			bossRolePermissionDao.addBossRolePermission(newrolePermission);
//		}
		 //alter by biran 20150122 修改逻辑，直接改为新增
		 BossRolePermission newrolePermission=new BossRolePermission();
			Date date = new Date();
			newrolePermission.setPermissionId(permissionId);
			newrolePermission.setRoleId(roleId);
			newrolePermission.setStatus(0);
			newrolePermission.setCreateTime(date);
			newrolePermission.setUpdateTime(date);
			
			//直接插入
			bossRolePermissionDao.addBossRolePermission(newrolePermission);
	 }
	
}
