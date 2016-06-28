package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.SolrDataDao;
import com.jointown.zy.common.solr.SolrListingVo;
import com.jointown.zy.common.solr.SolrPurchaseVo;

@Repository
public class SolrDataDaoImpl extends BaseDaoImpl implements SolrDataDao{
	
	@Override
	public List<SolrPurchaseVo> selectByPurchaseIds(List<String> purchaseIds) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.SolrDataDao.selectByPurchaseIds", purchaseIds);
	}
	
	@Override
	public List<SolrPurchaseVo> selectByPurchaseCodes(List<String> purchaseCodes) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.SolrDataDao.selectByPurchaseCodes", purchaseCodes);
	}
	
	@Override
	public List<SolrListingVo> selectByListingIds(List<String> listingIds) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.SolrDataDao.selectByListingIds", listingIds);
	}

	@Override
	public List<SolrListingVo> selectByWlIds(List<String> wlIds) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.SolrDataDao.selectByWlIds", wlIds);
	}

	@Override
	public List<SolrListingVo> selectByBreedIds(List<String> breedIds) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.SolrDataDao.selectByBreedIds", breedIds);
	}

	@Override
	public List<SolrListingVo> selectByWlunits(List<String> wlunits) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.SolrDataDao.selectByWlunits", wlunits);
	}

}
