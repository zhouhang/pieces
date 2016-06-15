package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.HomePageFeedBackVo;

/**
 * 同一任务ServiceImpl
 * 
 * @author ff
 *
 * @data 2015年11月02日
 */
public interface FeedBackService {
	/**
	 * 后台统一任务管理：根据条件查询任务
	 *
	 * @param page
	 * @return
	 *
	 * @author ff
	 *
	 * @data 2015年11月02日
	 */
	List<HomePageFeedBackVo> findFeedBacksByCondition(Page<HomePageFeedBackVo> page);

	int operFeedBack(HomePageFeedBackVo homePageFeedBackVo);

	int addFeedBack(HomePageFeedBackVo homePageFeedBackVo);
}
