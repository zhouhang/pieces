package com.jointown.zy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.FeedBackDao;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.FeedBackService;
import com.jointown.zy.common.vo.HomePageFeedBackVo;

@Service
public class FeedBackServiceImpl implements FeedBackService {
	
	@Autowired
	FeedBackDao feedBackDao;
	
	@Override
	public List<HomePageFeedBackVo> findFeedBacksByCondition(Page<HomePageFeedBackVo> page) {
		List<HomePageFeedBackVo> homePageFeedBackVos = feedBackDao.selectFeedBacksByCondition(page);
		return homePageFeedBackVos;
	}

	@Override
	public int operFeedBack(HomePageFeedBackVo homePageFeedBackVo) {
		int ok = feedBackDao.operFeedBack(homePageFeedBackVo);
		return ok;
	}

	@Override
	public int addFeedBack(HomePageFeedBackVo homePageFeedBackVo) {
		int ok = feedBackDao.addFeedBack(homePageFeedBackVo);
		return ok;
	}

}
