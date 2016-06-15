package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.solr.SolrListingVo;
import com.jointown.zy.common.solr.SolrPurchaseVo;

public interface SolrDataDao {
	
	/**
	 * 根据采购ID查询
	 */
	List<SolrPurchaseVo> selectByPurchaseIds(List<String> purchaseIds);
	
	/**
	 * 
	 * @Description: 根据采购批次号查询
	 * @Author: 刘漂
	 * @Date: 2015年10月21日
	 * @param purchaseCodes
	 * @return
	 */
	public List<SolrPurchaseVo> selectByPurchaseCodes(List<String> purchaseCodes);
	
	/**
	 * 根据挂牌ID查询
	 */
	List<SolrListingVo> selectByListingIds(List<String> listingIds);
	/**
	 * 根据仓单ID查询
	 */
	List<SolrListingVo> selectByWlIds(List<String> wlIds);
	/**
	 * 根据品种ID查询
	 */
	List<SolrListingVo> selectByBreedIds(List<String> breedIds);
	/**
	 * 根据计量单位编码查询
	 */
	List<SolrListingVo> selectByWlunits(List<String> wlunits);
}
