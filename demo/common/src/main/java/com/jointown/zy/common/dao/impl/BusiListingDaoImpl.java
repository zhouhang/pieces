package com.jointown.zy.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiListingDao;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiGoodsInfoVo;
import com.jointown.zy.common.vo.BusiGoodsRecommenVo;
import com.jointown.zy.common.vo.BusiGoodsSellerVo;
import com.jointown.zy.common.vo.BusiListingVo;

/**
 * 挂牌操作DaoImpl
 * @author Mr.songwei
 * 	2014-12-27
 */
@Repository
public class BusiListingDaoImpl extends BaseDaoImpl implements BusiListingDao {

//	@Override
//	public int deleteByPrimaryKey(String listingid) {
//		return this.getSqlSession().delete("com.jointown.zy.common.dao.BusiListingDao.deleteByPrimaryKey", listingid);
//	}

	@Override
	public int insertListing(BusiListing record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BusiListingDao.insertListing", record);
	}

	@Override
	public int insertSelective(BusiListing record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BusiListingDao.insertSelective", record);
	}

	@Override
	public BusiListing selectSingleListing(String listingid) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiListingDao.selectSingleListing", listingid);
	}

	@Override
	public int updateByPrimaryKey(BusiListing record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiListingDao.updateByPrimaryKey", record);
	}

	@Override
	public List<BusiListingVo> selectListingsByCondition(Page<BusiListingVo> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.BusiListingDao.selectListingsByCondition", page);
	}

	@Override
	public List<BusiListingVo> selectListingsByNotExaminels(Page<BusiListingVo> page){
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.BusiListingDao.selectListingsNotExaminels", page);
	}
	
	//后台取消挂牌
	@Override
	public int updateListingState(String listingid) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiListingDao.updateListingState", listingid);
	}
	
	@Override
	public int updateListingRecommend(BusiListing record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiListingDao.updateListingRecommend", record);
	}

	@Override
	public int updateListingFlag(BusiListing record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiListingDao.updateListingFlag", record);
	}

	@Override
	public int updateByIdSelective(BusiListing record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiListingDao.updateByIdSelective", record);
	}

	@Override
	public int updateWlsurPlus(BusiListing record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiListingDao.updateWlsurPlus", record);
	}
	
	@Override
	public BusiGoodsSellerVo selectGoodsSellerInfo(String listingid) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiListingDao.selectGoodsSellerInfo", listingid);
	}

	@Override
	public BusiGoodsInfoVo selectGoodsInfo(String listingid) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiListingDao.selectGoodsInfo", listingid);
	}

	@Override
	public List<BusiGoodsRecommenVo> selectGoodsRecommenList(Integer count) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.BusiListingDao.selectGoodsRecommenList", count);
	}

	@Override
	public int selectGoodsOrderState(String listingid){
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiListingDao.selectGoodsOrderState",listingid);
	}

	@Override
	public List<BusiListing> selectNotExpiredListings(Integer...beforeDays) {
		Integer beforeDay = beforeDays.length>0?beforeDays[0]:0;
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.BusiListingDao.selectNotExpiredListings",beforeDay);
	}

	@Override
	public int updateListingSurplusByOrderAmount(BusiListing record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiListingDao.updateListingSurplusByOrderAmount", record);
	}

	
	@Override
	public int updateListingFirstExamineInfo(BusiListing record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiListingDao.updateListingFirstExamineInfo", record);
	}


	@SuppressWarnings("rawtypes")
	@Override
	public HashMap getSurplusesAndVolume(HashMap map) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiListingDao.getSurplusesAndVolume",map);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<BusiListingVo> findHistoryListing(Page<BusiListingVo> page){
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.BusiListingDao.findHistoryListing",page);
	}

	@Override
	public List<Map<String, Object>> selectListingByBreed(
			Map<String, Object> conMap) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.BusiListingDao.selectListingByBreed",conMap);
	}
}