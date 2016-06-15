/**
 * @author guoyb
 * 2015年3月18日 上午11:35:16
 */
package com.jointown.zy.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.HomePageListingDao;
import com.jointown.zy.common.dto.HomePageListingDto;
import com.jointown.zy.common.model.Page;

/**
 * @author guoyb
 * 2015年3月18日 上午11:35:16
 */
@Repository
public class HomePageListingDaoImpl extends BaseDaoImpl implements HomePageListingDao {

	/**
	 * 根据status获取首页using数据和预览数据
	 * 2015年3月22日 下午10:18:51
	 * @param status
	 * @return
	 */
	@Override
	public List<HomePageListingDto> selectHomePageListingsByType(Map<String, String> map) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.HomePageListingDao.selectHomePageListingsByType",map);
	}

	/**
	 * 2015年3月19日 下午7:23:36
	 * @param page
	 * @return
	 */
	@Override
	public List<HomePageListingDto> selectReviewedListings(
			Page<HomePageListingDto> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.HomePageListingDao.selectReviewedListings",page);
	}

	/**
	 * 2015年3月20日 下午1:59:09
	 * @param homePageListingDto
	 * @return
	 */
	@Override
	public Integer insertHomePageListing(HomePageListingDto homePageListingDto) {
		//执行添加到当前status的动作
		int insert = this.getSqlSession().insert("com.jointown.zy.common.dao.HomePageListingDao.insertHomePageListing",homePageListingDto);
		return insert;
	}

	@Override
	public Integer updateHomePageListing(HomePageListingDto homePageListingDto) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.HomePageListingDao.updateHomePageListing",homePageListingDto);
	}

	@Override
	public HomePageListingDto selectHomePageListingsBy(
			HomePageListingDto homePageListingDto) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.HomePageListingDao.selectCurListings",homePageListingDto);
	}

	@Override
	public int removeHomePageListingBylisting_Id(HomePageListingDto homePageListingDto) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.HomePageListingDao.updateHomePageListing",homePageListingDto);
	}

	/* (non-Javadoc)
	 * @see com.jointown.zy.common.dao.HomePageListingDao#selectRecomListing(java.util.Map)
	 */
	@Override
	public List<HomePageListingDto> selectRecomListing(Map<String, Object> map) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.HomePageListingDao.selectHomePageRecommListings",map);
	}

	@Override
	public List<HomePageListingDto> selectNewHomePageListingData(Map<String, String> queryParam) {
			return getSqlSession().selectList("com.jointown.zy.common.dao.HomePageListingDao.queryHomePageListing", queryParam);
	}

	@Override
	public List<HomePageListingDto> selectNewBargain() {
		return getSqlSession().selectList("com.jointown.zy.common.dao.HomePageListingDao.selectNewBargain");
	}

	@Override
	public List<HomePageListingDto> selectBigWarehouse(Map<String, Integer> param) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.HomePageListingDao.queryBigWarehouse", param) ;
	}

	@Override
	public List<HomePageListingDto> selectAutoCompleteListing() {
		return  getSqlSession().selectList("com.jointown.zy.common.dao.HomePageListingDao.queryNewListing");
	}
}
