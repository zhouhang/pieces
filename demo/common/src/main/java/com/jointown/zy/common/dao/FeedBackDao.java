package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.HomePageFeedBackVo;

public interface FeedBackDao { 
    /**
     * 后台审核：查询所有求购信息
     *
     * @param page
     * @return
     *
     * @author ff
     *
     * @data 2015年11月02日
     */
    List<HomePageFeedBackVo> selectFeedBacksByCondition(Page<HomePageFeedBackVo> page);

	int operFeedBack(HomePageFeedBackVo homePageFeedBackVo);

	int addFeedBack(HomePageFeedBackVo homePageFeedBackVo);

}