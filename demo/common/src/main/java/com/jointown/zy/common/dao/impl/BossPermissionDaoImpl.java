package com.jointown.zy.common.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BossPermissionDao;
import com.jointown.zy.common.model.BossPermission;
import com.jointown.zy.common.model.BossRolePermission;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.util.SpringUtil;

/**
 * 后台权限DaoImpl
 * 
 * @author biran 2014-11-26
 */
@Repository
public class BossPermissionDaoImpl extends BaseDaoImpl implements
		BossPermissionDao {

	@Override
	public List<BossPermission> finAllBossPermission() {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossPermission> permissionList = sqlSession
				.selectList("com.jointown.zy.common.dao.BossPermissionDao.findAllBossPermissionForType");
		return permissionList;
	}

	@Override
	public List<BossPermission> listBossPermission(Page page) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params = (HashMap<String, Object>) page.getParams();
		String permissionName = (String) params.get("permissionName");
		if (permissionName == null) {
			permissionName = "";
		}
		String parentName = (String) params.get("parentName");
		List<BossPermission> permissionList = null;
		if (parentName != null && parentName != "" && permissionName != "") {
			permissionList = sqlSession
					.selectList(
							"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByPermissionAndParentIdByPage",
							page);

		}
		if (parentName == null || parentName == "" && permissionName != "") {
			permissionList = sqlSession
					.selectList(
							"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByPermissionCodeByPage",
							page);
		}
		if (parentName != null && parentName != "" && permissionName == "") {
			permissionList = sqlSession
					.selectList(
							"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByParentIdByPage",
							page);

		}
		if (parentName == null || parentName == "" && permissionName == "") {
			permissionList = sqlSession
					.selectList(
							"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByPage",
							page);
		}

		return permissionList;
	}

	@Override
	public BossPermission findBossPermissionByPermissionCode(
			String permissionCode) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		BossPermission bossPermission = sqlSession
				.selectOne(
						"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByPermissionCode",
						permissionCode);
		return bossPermission;
	}

	@Override
	public List<BossPermission> findBossPermissionByPermissionCodeForUpdate(
			String permissionCode, String permissionId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();

		BossPermission bossPermission = sqlSession
				.selectOne(
						"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByPermissionName",
						permissionCode);
		if (bossPermission != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("permissionId", permissionId);
			map.put("permissionName", permissionCode);
			List<BossPermission> bossPermissions = sqlSession
					.selectList(
							"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByPermissionCodeForUpdate",
							map);
			return bossPermissions;
		}

		return null;
	}

	@Override
	public List<BossPermission> findBossPermissionByParentName(String parentName) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession
				.selectList(
						"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByParentName",
						parentName);
	}

	@Override
	public String findParentNameByParentId(Integer parentId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		String permissionName = "无上级";
		if (parentId != 0) {
			BossPermission bossPermission = sqlSession
					.selectOne(
							"com.jointown.zy.common.dao.BossPermissionDao.findParentNameByParentId",
							parentId);
			permissionName = bossPermission.getPermissionName();
		}
		return permissionName;
	}

	/**
	 * 修改权限
	 * 
	 * @author lichenxiao
	 * 
	 */
	@Override
	public String updateBossPermission(BossPermission bossPermission) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession
				.update("com.jointown.zy.common.dao.BossPermissionDao.updateBossPermission",
						bossPermission);
		return "updateSuccess";
	}
	
	@Override
	public void updateBossPermissionTypeAndPath(BossPermission bossPermission) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession
				.update("com.jointown.zy.common.dao.BossPermissionDao.updatebossPermissionForPath",
						bossPermission);
	}

	/**
	 * 添加权限
	 * 
	 * @author lichenxiao
	 * @param bossPermission
	 */
	@Override
	public String addBossPermission(BossPermission bossPermission) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.insert("addBossPermission", bossPermission);
		List<BossPermission> bossPermissions = sqlSession
				.selectList("com.jointown.zy.common.dao.BossPermissionDao.findAllbossPermission");
		int id = bossPermissions.get(0).getPermissionId();
		sqlSession
				.update("com.jointown.zy.common.dao.BossPermissionDao.updatebossPermissionForPath",
						id);

		return "addSuccess";
	}

	/**
	 * 跟据权限ID查询
	 * 
	 * @author lichenxiao
	 */

	@Override
	public BossPermission findBossPermissionById(int id) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		BossPermission bossPermission = sqlSession
				.selectOne(
						"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByIdForParentName",
						id);
		return bossPermission;
	}

	/**
	 * 查找子结点个数
	 * 
	 * @author lichenxiao
	 */
	@Override
	public int findBossPermissionByNoteNumber(int permissionId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossPermission> bossPermissions = sqlSession
				.selectList(
						"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByNoteNumber",
						permissionId);
		return bossPermissions.size();
	}

	/**
	 * 通过权限角色关联表查询该权限是否被角色引用
	 * 
	 * @author lichenxiao
	 */
	@Override
	public int findBossPermissionByRoleNumber(int permissionId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossRolePermission> bossRolePermissions = sqlSession
				.selectList(
						"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByRoleNumber",
						permissionId);
		return bossRolePermissions.size();
	}

	/**
	 * 权限删除
	 * 
	 * @author lichenxiao
	 */
	@Override
	public int bossPermissionByUpdateStatus(int permissionId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("permissionId", permissionId);
		int i = sqlSession
				.update("com.jointown.zy.common.dao.BossPermissionDao.bossPermissionByUpdateStatus",
						map);
		return i;
	}

	@Override
	public List<BossPermission> findBossPermissionByid(Integer id) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossPermission> permissionlist = sqlSession
				.selectList(
						"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByid",
						id);
		return permissionlist;
	}

	@Override
	public List<BossPermission> findBossPermissionByUserCode(String UserCode) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossPermission> permissionlist = sqlSession
				.selectList(
						"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByUserCode",
						UserCode);
		return permissionlist;
	}
	
	@Override
	public List<BossPermission> findBossPermissionByUserCode4BossRealm(String UserCode) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossPermission> permissionlist = sqlSession.selectList("findBossPermissionByUserCode4BossRealm",UserCode);
		return permissionlist;
	}

	@Override
	public List<BossPermission> findLevel0BossPermissionByUserCode(
			String UserCode) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossPermission> permissionlist = sqlSession
				.selectList(
						"com.jointown.zy.common.dao.BossPermissionDao.findLevel0BossPermissionByUserCode",
						UserCode);
		return permissionlist;
	}

	@Override
	public List<BossPermission> findBossPermissionByParentid(Integer id) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossPermission> permissionlist = sqlSession
				.selectList(
						"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByParentid",
						id);
		return permissionlist;
	}

	@Override
	public List<BossPermission> findLevel2BossPermissionByParentid(
			String UserCode, Integer id) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", id);
		map.put("usercode", UserCode);
		List<BossPermission> permissionlist = sqlSession
				.selectList(
						"com.jointown.zy.common.dao.BossPermissionDao.findLevel2BossPermissionByParentid",
						map);
		return permissionlist;
	}

	@Override
	public List<BossPermission> findAllLevel0BossPermission() {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossPermission> permissionlist = sqlSession
				.selectList("com.jointown.zy.common.dao.BossPermissionDao.findAllLevel0BossPermission");
		return permissionlist;
	}

	@Override
	public List<BossPermission> findBossPermissionByParentidAndType(
			Integer parentId, Integer type, Integer roleId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("parentId", parentId);
		map.put("type", type);
		map.put("roleId", roleId);
		List<BossPermission> permissionlist = sqlSession
				.selectList(
						"com.jointown.zy.common.dao.BossPermissionDao.findBossPermissionByParentidAndType",
						map);
		return permissionlist;
	}

	@Override
	public List<BossPermission> findBossPermissionLeve3ByUserCode(
			String UserCode) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossPermission> permissionlist = sqlSession.selectList(
				"findBossPermissionLeve3ByUserCode", UserCode);
		return permissionlist;
	}

	@Override
	public BossPermission findBossPermissionByResource(String Resource) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		BossPermission permission = sqlSession.selectOne(
				"findBossPermissionByResource", Resource);
		return permission;
	}

	@Override
	public List<BossPermission> findLevel1BossPermissionByParentid(
			String userCode, Integer id) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userCode", userCode);
		paraMap.put("id", id);
		List<BossPermission> bossPermissionLists = getSqlSession()
				.selectList(
						"com.jointown.zy.common.dao.BossPermissionDao.findLevel1BossPermissionByParentid",
						paraMap);
		return bossPermissionLists;
	}

	/**
	 * 得到ID最大值
	 * 
	 * @author lichenxiao
	 */
	@Override	
	public Integer getIdMax() {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BossPermission> bossPermissions = getSqlSession().selectList(
				"com.jointown.zy.common.dao.BossPermissionDao.getIdMax");
		BossPermission bossPermission = bossPermissions.get(0);
		return bossPermission.getPermissionId() + 1;
	}

	/**
	 * 
	 * @author biran
	 * @param 
	 * @return
	 */
	@Override
	public String updateBossPermissionUnusual(String sql) 
	throws SQLException {
		  Connection con = null;
		  Statement stmt = null;
		  ResultSet rs = null;	
		  Properties jdbProperties =SpringUtil.getConfigProperties();
		  String url= jdbProperties.getProperty("db.jdbcUrl");
		  String user= jdbProperties.getProperty("db.user");
		  String password= jdbProperties.getProperty("db.password");
		  try {
			  Class.forName("oracle.jdbc.OracleDriver");
			  con = DriverManager.getConnection(
					  url, user, password);
			  stmt = con.createStatement();
			  //去换行
			  sql=sql.replaceAll("\n","");
			  String[] sqls= sql.split(";");
			  for(int i=0;i<sqls.length;i++){
				  try{
					  stmt.executeUpdate( sqls[i]);
				  }catch (SQLException s) {
					  	s.printStackTrace();
						con.rollback();
						int j=i+1;
						return "第"+j+"行错误，"+s.getMessage();
					  
				  }
				 
			  }
			  con.commit();
		  } catch (ClassNotFoundException e) {
			  e.printStackTrace();
		  }  finally {
			   
			    if (rs != null) {
				     rs.close();
				     rs = null;
			    }
			    if (stmt != null) {
				     stmt.close();
				     stmt = null;
			    }
			    if (con != null) {
				     con.close();
				     con = null;
			    }

		  }
		
		 return "success";
	}

}
