package com.jointown.zy.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.CmsSiteDao;
import com.jointown.zy.common.model.CmsSite;
/**
 * 
 * @author seven
 *
 */
@Repository
public class CmsSiteDaoImpl extends BaseDaoImpl implements CmsSiteDao {
	 
	@Override
	public int deleteByPrimaryKey(Long id) {
		SqlSessionTemplate selSessionTemple = this.getSqlSession();
		int result=selSessionTemple.delete("com.jointown.zy.common.dao.CmsSiteDao.deleteByPrimaryKey", id);
		return result;
	}

	@Override
	public int insert(CmsSite cmsSite) {
		SqlSessionTemplate selSessionTemple = this.getSqlSession();
		int result=selSessionTemple.insert("com.jointown.zy.common.dao.CmsSiteDao.insert", cmsSite);
		return result;
	}

	@Override
	public int insertSelective(CmsSite cmsSite) {
		SqlSessionTemplate selSessionTemple = this.getSqlSession();
		int result=selSessionTemple.insert("com.jointown.zy.common.dao.CmsSiteDao.insertSelective", cmsSite);
		return result;
	}

	@Override
	public CmsSite selectByPrimaryKey(Long id) {
		SqlSessionTemplate selSessionTemple = this.getSqlSession();
		CmsSite result=selSessionTemple.selectOne("com.jointown.zy.common.dao.CmsSiteDao.selectByPrimaryKey", id);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(CmsSite cmsSite) {
		return this.getSqlSession().update
				("com.jointown.zy.common.dao.CmsSiteDao.updateByPrimaryKeySelective", cmsSite);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(CmsSite cmsSite) {
		return this.getSqlSession().update
				("com.jointown.zy.common.dao.CmsSiteDao.updateByPrimaryKeyWithBLOBs", cmsSite);
	}

	@Override
	public int updateByPrimaryKey(CmsSite cmsSite) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.CmsSiteDao.updateByPrimaryKey", cmsSite);
	}

	@Override
	public List<CmsSite> selectAllCmsSite() {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.CmsSiteDao.selectAllCmsSite");
	}

	@Override
	public List<CmsSite> selectDynamicCmsSite(Map queryMap) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.CmsSiteDao.selectDynamicCmsSite",queryMap);
	}

}
