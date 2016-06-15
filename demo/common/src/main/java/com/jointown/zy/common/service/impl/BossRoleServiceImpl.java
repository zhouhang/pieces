package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BossRoleDao;
import com.jointown.zy.common.dao.BossRolePermissionDao;
import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.model.BossRole;
import com.jointown.zy.common.model.BossUserRole;
import com.jointown.zy.common.model.Organization;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BossRoleService;
import com.jointown.zy.common.vo.BossRoleVO;

/**
 * 后台角色ServiceImpl
 * @author biran
 * 2014-11-27
 */
@Service
public class BossRoleServiceImpl implements BossRoleService {
	@Autowired
	private BossRoleDao bossRoleDao;
	
	@Autowired
	private  BossUserDao bossUserDao;
	//参数为page的
	@Override
	public List<BossRole> findBossRoles(Page page) {

		return bossRoleDao.findBossRoles(page) ;
	}
	
	@Override
	public List<BossRole> findBossRoles(BossRoleVO bossRole) {

		return bossRoleDao.findBossRoles(bossRole) ;
	}
	//没有参数的
	@Override
	public List<BossRole> findBossRoles() {

		return bossRoleDao.findBossRoles() ;
	}
	
	
	@Override
	public List<BossRole> findBossRoleByUserCode(String UserCode) {
		return bossRoleDao.findBossRoleByUserCode(UserCode);
	}
	@Override
	public void addBossRole(String newRoleName){
//		//先判断是否有状态为删除的角色，有的话更新，否则插入
//		BossRole role=bossRoleDao.findBossRoleByRoleName(newRoleName);
//		if(role!=null){
//			if(role.getStatus()==1){//更新
//				BossRole newrole=new BossRole();
//				Date date = new Date();
//				newrole.setRoleName(newRoleName);
//				newrole.setStatus(0);
//				newrole.setCreateTime(date);
//				newrole.setUpdateTime(date);
//				
//				bossRoleDao.UpdateBossRoleByName(newrole);
//			}
//		}else{
//			BossRole newrole=new BossRole();
//			Date date = new Date();
//			newrole.setRoleName(newRoleName);
//			newrole.setStatus(0);
//			newrole.setCreateTime(date);
//			newrole.setUpdateTime(date);
//			
//			//直接插入
//			bossRoleDao.addBossRole(newrole);
//		}
		//alter by biran 20150122修改新增逻辑，直接新增
		BossRole newrole=new BossRole();
		Date date = new Date();
		newrole.setRoleName(newRoleName);
		newrole.setStatus(0);
		newrole.setCreateTime(date);
		newrole.setUpdateTime(date);
		
		//直接插入
		bossRoleDao.addBossRole(newrole);
		
		
	}
	@Override
	public void updateBossRole(Integer roleId,String roleName){

			BossRole role=new BossRole();
			Date date = new Date();
			role.setRoleId(roleId);
			role.setRoleName(roleName);
			role.setUpdateTime(date);
			bossRoleDao.UpdateBossRole(role);
	}
	
	@Override
	public void deleteBossRoleById(Integer roleId){

		BossRole role=new BossRole();
		Date date = new Date();
		role.setRoleId(roleId);
		role.setUpdateTime(date);
		bossRoleDao.deleteBossRoleById(role);
	}
	
	
	
	@Override
	public String validateRoleName(String roleName) {
		String msg = "";
		BossRole role=bossRoleDao.findBossRoleByRoleName(roleName);
		if(role!=null){
			if(role.getStatus()==1){//状态为删除
				
			}else{
				//角色名被占用
				msg="角色名已被占用";
				
			}
		}
		return msg;
	}
	
	@Override
	public String validateRoleStatus(String roleId) {
		String msg = "";
		BossRole role=bossRoleDao.findBossRoleByRoleId(roleId);
		if(role!=null){
			if(role.getStatus()==1){//状态为删除
				//角色已删除
				msg="01";
			}else{
				//角色正常
				msg="00";
				
			}
		}else{
			//角色已删除
			msg="01";
		}
		return msg;
	}
	
	
	@Override
	public String validateRoleDele(String roleId) {
		String msg = "";
		BossRole role=bossRoleDao.findBossRoleByRoleId(roleId);
		if(role!=null){
			if(role.getStatus()==1){//状态为删除
				//角色已删除
				msg="01";
			}
		}else{
			//角色已删除
			msg="01";
		}
		if(msg!=null&&!msg.equals("")){
			return msg;
		}
		//校验角色是否有被引用
		BossUserRole bossUserRole = new BossUserRole();
		bossUserRole.setRoleId(Integer.parseInt(roleId));
		List<BossUserRole> list=bossUserDao.findBossUserRoleByRoleID(bossUserRole);
		if(list!=null&&list.size()>0){
			return "02";
		}
		return msg;
	}
}
