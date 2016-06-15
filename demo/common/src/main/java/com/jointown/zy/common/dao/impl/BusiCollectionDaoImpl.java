package com.jointown.zy.common.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiCollectionDao;
import com.jointown.zy.common.model.BusiCollection;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiCollectionVo;

@Repository
public class BusiCollectionDaoImpl extends BaseDaoImpl implements BusiCollectionDao{

	@Override
	public int insertBusiCollention(BusiCollection collection) {
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiCollectionDao.insertCollectionInfo", collection);
	}

	@Override
	public BusiCollection selectCollention(Long userid, String listingid) {
		BusiCollection collection = new BusiCollection();
		collection.setUserid(userid);
		collection.setListingid(listingid);
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiCollectionDao.selectCollention", collection);
	}

	@Override
	public int updateCollention(Long userid, String listingid, String cstate) {
		BusiCollection collection = new BusiCollection();
		collection.setUserid(userid);
		collection.setListingid(listingid);
		collection.setCstate(Short.valueOf(cstate));
		collection.setUpdatetime(new Date());
		return getSqlSession().update("com.jointown.zy.common.dao.BusiCollectionDao.updateCollention", collection);
	}

	@Override
	public List<BusiCollectionVo> selectCollentionsByUserId(Page<Map<String, Object>> page) {
		return getSqlSession().selectList("selectCollentionsByUserId", page);
	}
	
	@Override
	public int updateCollectionBy(BusiCollection collection) {
		 return getSqlSession().update("com.jointown.zy.common.dao.BusiCollectionDao.updateCollectionBy", collection);
	}
	
	/**
	 * 根据用户ID查询我的收藏列表中的收藏品种清单
	 */
	@Override
	public List<HashMap<String,String>> selectCollectionBread(Long userid){
		return getSqlSession().selectList("selectCollectionBread", userid);
	}

	@Override
	public List<HashMap<String, Object>> getListingSaleCountBy() {
		return getSqlSession().selectList("getListingSaleCountBy");
	}

	@Override
	public List<HashMap<String, Object>> getListingSaleInfo(List<String> listingIds) {
		if(listingIds==null || listingIds.isEmpty()) return null;
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiCollectionDao.getListingSaleInfo",listingIds);
	}

	@Override
	public List<HashMap<String, Object>> getListingSaleIds(
			HashMap<String, Object> map) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiCollectionDao.getListingSaleIds",map);
	}
}
