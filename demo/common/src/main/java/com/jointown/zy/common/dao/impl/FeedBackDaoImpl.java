package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.FeedBackDao;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.HomePageFeedBackVo;

/**
 * 求购信息DaoImpl
 * 
 * @author wangjunhu
 *
 * @data 2015年3月12日
 */
@Repository
public class FeedBackDaoImpl extends BaseDaoImpl implements FeedBackDao {

	@Override
	public List<HomePageFeedBackVo> selectFeedBacksByCondition(Page<HomePageFeedBackVo> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.FeedBackDao.selectFeedBacksByCondition", page);
	}

	@Override
	public int operFeedBack(HomePageFeedBackVo homePageFeedBackVo) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.FeedBackDao.operFeedBack", homePageFeedBackVo);
	}

	@Override
	public int addFeedBack(HomePageFeedBackVo homePageFeedBackVo) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.FeedBackDao.addFeedBack", homePageFeedBackVo);
	}
}
