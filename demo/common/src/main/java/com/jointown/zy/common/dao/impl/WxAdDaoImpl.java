package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.WxAdDao;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxAd;
import com.jointown.zy.common.vo.WxAdVo;

/**
 * 微信公众平台开发--广告DaoImpl
 * 
 * @author lichenxiao
 *
 * @data 2015年3月17日
 */
@Repository
public class WxAdDaoImpl extends BaseDaoImpl implements WxAdDao {

	/**
	 * 广告列表，分页查询
	 * @param page
	 * @return
	 */
	@Override
	public List<WxAdVo> selectWxAdsByCondition(Page<WxAdVo> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxAdDao.selectWxAdsByCondition", page);
	}

    /**
     * 查询数量，条件查询
     * @param wxAd
     * @return
     */
	@Override
	public int selectByCondition(WxAd record) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxAdDao.selectByCondition", record);
	}

	/**
	 * 添加广告
	 * @param wxAd
	 * @return
	 */
	@Override
	public int insertSelective(WxAd record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.WxAdDao.insertSelective", record);
	}

	/**
	 * 修改广告
	 * @param wxAd
	 * @return
	 */
	@Override
	public int updateByPrimaryKeySelective(WxAd record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.WxAdDao.updateByPrimaryKeySelective", record);
	}

	/**
	 * 得到当前广告
	 * @param adId
	 * @return
	 */
	@Override
	public WxAdVo selectByPrimaryKey(Long adId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxAdDao.selectByPrimaryKey", adId);
	}

	/**
	 * 通过广告位置得到广告
	 */
	@Override
	public WxAdVo findWxAdsByPostionName(String adPostionName) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxAdDao.selectByPostionName",adPostionName);
	}

}
