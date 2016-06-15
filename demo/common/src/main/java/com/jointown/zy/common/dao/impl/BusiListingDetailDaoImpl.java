package com.jointown.zy.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiListingDetailDao;
import com.jointown.zy.common.model.BusiListingDetail;
import com.jointown.zy.common.vo.BusiListingDetailVo;

/**
 * 挂牌商品详情表操作DaoImpl
 * @author Mr.songwei
 * 	2014-12-27
 */
@Repository
public class BusiListingDetailDaoImpl extends BaseDaoImpl implements BusiListingDetailDao {

	@Override
	public int insertListingDetail(BusiListingDetail record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BusiListingDetailDao.insertListingDetail", record);
	}

	@Override
	public int insertSelective(BusiListingDetail record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BusiListingDetailDao.insertSelective", record);
	}

	@Override
	public BusiListingDetail selectByListId(String listingid) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiListingDetailDao.selectByListId", listingid);
	}

	@Override
	public int updateByListId(HashMap<String,Object> map) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiListingDetailDao.updateByListId", map);
	}

	@Override
	public BusiListingDetailVo selectSingleListingDetail(String listingid) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiListingDetailDao.selectSingleListingDetail", listingid);
	}

	@Override
	public Map<String, Object> getSellerInfo(String wlid) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiListingDetailDao.selectSellInfoByWlid", wlid);
	}
}