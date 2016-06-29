package com.jointown.zy.common.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import oracle.net.aso.p;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BossPermissionDao;
import com.jointown.zy.common.model.BossPermission;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BossPermissionService;

/**
 * 后台角色ServiceImpl
 * @author biran
 * 2014-11-26
 */
@Service
public class BossPermissionServiceImpl implements BossPermissionService {
	@Autowired
	private BossPermissionDao bossPermissionDao;
	
	/**
	 * 条件查询权限列表
	 * @author lichenxiao
	 * @param page
	 * @return
	 */
	@Override
	public List<BossPermission> findAllBossPermission(Page page) {
		return bossPermissionDao.listBossPermission(page);
	}

	
	
	/**
	 * 验证添加的权限名称是否重复
	 * @author lichenxiao
	 * @param permissionCode
	 * @return 
	 */
	@Override
	public BossPermission findBossPermissionByPermissionCode(String permissionCode) {
		return bossPermissionDao.findBossPermissionByPermissionCode(permissionCode);
	}
	
	/**
	 * 验证修改的权限名称是否与其他已存在的权限名称重复
	 * @author lichenxiao
	 * @param permissionCode
	 * @return 
	 */	
	@Override
	public List<BossPermission> findBossPermissionByPermissionCodeForUpdate(
			String permissionCode,String permissionId) {
		return bossPermissionDao.findBossPermissionByPermissionCodeForUpdate(permissionCode,permissionId);
	}
	
	
	@Override
	public List<BossPermission> findBossPermissionByParentName(String parentName) {
		return bossPermissionDao.findBossPermissionByParentName(parentName);
	}
	
	
	@Override
	public String findParentNameByParentId(Integer parentId) {
		// TODO Auto-generated method stub
		return bossPermissionDao.findParentNameByParentId(parentId);
	}
	
	/**
	 * 无条件查询所有权限
	 * @author lichenxiao
	 * @param
	 * @return 
	 */
	@Override
	public List<BossPermission> findAllBossPermission() {
		// TODO Auto-generated method stub
		return bossPermissionDao.finAllBossPermission();
	}
	
	/**
	 * 添加权限
	 * @author lichenxiao
	 * @param bossPermission 前台转入实体对象
	 */
	@Override
	public String addBossPermission(BossPermission bossPermission) {
		Date date = new Date();
		bossPermission.setStatus(0);
		bossPermission.setCreateTime(date);
		bossPermission.setUpdateTime(date);
		return bossPermissionDao.addBossPermission(bossPermission);
		
	}
	
	/**
	 * 修改权限
	 * @author lichenxiao
	 */
	@Override
	public String updateBossPermission(BossPermission bossPermission,String path) {
		Date date = new Date();
		bossPermission.setUpdateTime(date);
		bossPermissionDao.updateBossPermission(bossPermission);
		if (path != null && path != "") {
			bossPermissionDao.updateBossPermissionTypeAndPath(bossPermission);
			List<BossPermission> bplist = bossPermissionDao.findBossPermissionByParentid(bossPermission.getPermissionId());
			this.recursion(bplist);
		}
		return "updateSuccess";
	}
	public void recursion(List<BossPermission> bplist){
		for (BossPermission bossPermission2 : bplist) {
			bossPermissionDao.updateBossPermissionTypeAndPath(bossPermission2);
			if(bossPermission2.getType()<3){
				List<BossPermission> bplist1 = bossPermissionDao.findBossPermissionByParentid(bossPermission2.getPermissionId());
				this.recursion(bplist1);
			}
		}
	}
	
	@Override
	public void updateBossPermissionTypeAndPath(BossPermission bossPermission) {
		bossPermissionDao.updateBossPermissionTypeAndPath(bossPermission);
	}
	
	/**
	 * 根据权限Id获得权限对象
	 * @author lichenxiao
	 * @param permissionId 权限ID
	 * @return 
	 */
	@Override
	public BossPermission findBossPermissionById(int Id) {
		
		return bossPermissionDao.findBossPermissionById(Id);
	}

	/**
	 * 查找子结点个数
	 * @author lichenxiao
	 */
	@Override
	public int findBossPermissionByNoteNumber(int permissionId) {
		return bossPermissionDao.findBossPermissionByNoteNumber(permissionId);
	}
	
	/**
	 * 查找权限是否已被某角色使用
	 * @author lichenxiao
	 */
	@Override
	public int findBossPermissionByRoleNumber(int permissionId) {
		return bossPermissionDao.findBossPermissionByRoleNumber(permissionId);
	}

	/**
	 * 删除权限
	 * @author lichenxiao
	 */
	@Override
	public int bossPermissionByUpdateStatus(int permissionId) {
		return bossPermissionDao.bossPermissionByUpdateStatus(permissionId);
	}
	
	// 通过后台人员ID 查找对应权限（所有）
	//框架页左侧菜单使用
	@Override
	public List<BossPermission> findBossPermissionByUserCode(String UserCode) {
		//取到0级系统的LIST
		List<BossPermission> Level0List=bossPermissionDao.findLevel0BossPermissionByUserCode(UserCode);
		List<BossPermission> List=new ArrayList();//最终返回值
		for(BossPermission p:Level0List){
			Integer permissionid=p.getPermissionId();
			//由一级权限ID取2级菜单LIST，要关联角色授权
			/*List<BossPermission> Level3List=bossPermissionDao.findLevel2BossPermissionByParentid(UserCode,permissionid);
			if(Level3List!=null  && !Level3List.isEmpty()  ){
				p.setSonList(Level3List);
			}*/
			//由0级菜单权限获取1级菜单
			List<BossPermission> level1List = bossPermissionDao.findLevel1BossPermissionByParentid(UserCode, permissionid);
			if (level1List !=null && !level1List.isEmpty()) {
				for (BossPermission bPermission : level1List) {
					 Integer permissionParentId = bPermission.getPermissionId();
					 //由1级菜单权限获取2级菜单
					 List<BossPermission> Level3List=bossPermissionDao.findLevel2BossPermissionByParentid(UserCode,permissionParentId);
					 if (Level3List !=null && !Level3List.isEmpty()) {
						bPermission.setSonList(Level3List);
					}
				}
				
				p.setSonList(level1List);
			}
			List.add(p);
		}
		return List;
	}
	
	//查找所有0级菜单，分配权限使用
	@Override
	public List<BossPermission> findAllLevel0BossPermission() {
		return bossPermissionDao.findAllLevel0BossPermission();
	}
	
	// 通过0级父ID，查找下面几级菜单权限，包括1级目录，2级菜单，3级按钮
	 @Override
	 public List<BossPermission> findBossPermissionByLevel0Id(Integer parentId,Integer roleId){
		 return getBossPermissionByParentidAndType(parentId,1,roleId);
	 }
	 
	 //构造当前等级的list,递归使用,关联当前角色ID(是否选中使用)
	 public List<BossPermission> getBossPermissionByParentidAndType(Integer parentId,Integer type,Integer roleId){
		 List<BossPermission> List=new ArrayList();//最终返回值
		 Integer sonType=type+1;
		 //当前一层的权限
		 List<BossPermission> MyList=bossPermissionDao.findBossPermissionByParentidAndType(parentId,type,roleId);
		 for(BossPermission p:MyList){
				Integer permissionid=p.getPermissionId();
				//找子权限
				List<BossPermission> sonList=getBossPermissionByParentidAndType(permissionid,sonType,roleId);
				if(sonList!=null && !sonList.isEmpty()){
					p.setSonList(sonList);
				}
				List.add(p);
		 }
		 return List;
	 }

	 
	@Override
	public List<BossPermission> findAllBossPermissionsByUserCode(String userCode) {
		return bossPermissionDao.findBossPermissionByUserCode(userCode);
	}
	 
	@Override
	public List<BossPermission> findBossPermissionByUserCode4BossRealm(String userCode) {
		return bossPermissionDao.findBossPermissionByUserCode4BossRealm(userCode);
	}
	
	
	
	
	 //通过用户名，查找对应的3级按钮权限，格式：菜单名-按钮名
	@Override
	public List<BossPermission> findBossPermissionLeve3ByUserCode(String userCode) {
		return bossPermissionDao.findBossPermissionLeve3ByUserCode(userCode);
	}
	 

	  // 通过资源URL查询Permission
	 @Override
	 public BossPermission findBossPermissionByResource(String Resource) {
		 return bossPermissionDao.findBossPermissionByResource(Resource);
	 }


	 /**
	  * 得到ID最大值
	  * @author lichenxiao
	  * 
	  */
	@Override
	public Integer getIdMax() {
		return bossPermissionDao.getIdMax();
	}

	/**
	  * @author biran
	  * 
	  */
	@Override
	public String updateBossPermissionUnusual(String sql) {

		String result=null;;
		try {
			result = bossPermissionDao.updateBossPermissionUnusual(sql);
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
