/**
 * @author guoyb
 * 2015年3月18日 上午11:37:04
 */
package com.jointown.zy.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.HomePageAdDao;
import com.jointown.zy.common.model.HomePageAd;
import com.jointown.zy.common.model.Page;

/**
 * @author guoyb
 * 2015年3月18日 上午11:37:04
 */
@Repository
public class HomePageAdDaoImpl extends BaseDaoImpl implements HomePageAdDao{

	public List<HomePageAd> selectHomePageAdBy(HashMap<String,Object> map) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.HomePageAdDao.selectHomePageAdBy",map);
	}
	
	public List<HomePageAd> selectAdByPage(Page<HomePageAd> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.HomePageAdDao.selectAdByPage",page);
	}
	
	/**
	 * 根据广告id查询单个广告对象
	 * @param adid
	 * @return HomePageAd
	 */
	public HomePageAd getHomePageAdById(int adid){
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.HomePageAdDao.getHomePageAdById",adid);
	}

	/* (non-Javadoc)
	 * @see com.jointown.zy.common.dao.HomePageAdDao#insertHomePageAd(com.jointown.zy.common.model.HomePageAd)
	 */
	@Override
	public int insertHomePageAd(HomePageAd ad) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.HomePageAdDao.insertHomePageAd",ad);
	}

	/* (non-Javadoc)
	 * @see com.jointown.zy.common.dao.HomePageAdDao#updateHomePageAdByAdid(com.jointown.zy.common.model.HomePageAd)
	 */
	@Override
	public int updateHomePageAdByAdid(HomePageAd ad) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.HomePageAdDao.updateHomePageAdByAdid",ad);
	}

	@Override
	public List<HomePageAd> selectHomePageAdByType(HashMap<String, Object> map) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.HomePageAdDao.selectHomePageAdByType",map);
	}

	@Override
	public List<HomePageAd> selectHomePageAdsByType(String type,String nums)
			throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("nums", nums);
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.HomePageAdDao.selectHomePageAdsByType", params);
	}

	@Override
	public HomePageAd queryMinSortno() throws Exception {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.HomePageAdDao.selectMinSortno");
	}
	
	
}