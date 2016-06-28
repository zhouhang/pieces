package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxActivity;
import com.jointown.zy.common.model.WxAd;
import com.jointown.zy.common.vo.WxAdVo;

public interface WxAdService {

	
	/**
	 * 广告列表，分页查询
	 * @param page
	 * @return
	 */
	List<WxAdVo> findWxAdsByCondition(Page<WxAdVo> page);
    /**
     * 查询数量，条件查询
     * @param wxAd
     * @return
     */
    int findByCondition(WxAd wxAd);
	/**
	 * 得到当前广告
	 * @param adId
	 * @return
	 */
	WxAdVo findWxAdById(Long adId);
	
	/**
	 * 通过广告位置查找广告
	 * @param adPostionName
	 * @return
	 */
	WxAdVo findWxAdsByPostionName(String adPostionName);

	/**
	 * 添加广告
	 * @param wxAd
	 * @return
	 */
	int addWxAd(WxAd wxAd);
	
    /**
     * 微信后台管理：添加广告
     * 
	 * @author aizhengdong
	 * @date 2015年8月21日
     */
	int addAdByWxBoss(WxAd wxAd);

	/**
	 * 修改广告
	 * @param wxAd
	 * @return
	 */
	int updateWxAd(WxAd wxAd);
	
    /**
     * 微信后台管理：修改广告
     * 
	 * @author aizhengdong
	 * @date 2015年8月21日
     */
	int updateAdByWxBoss(WxAd wxAd);

	/**
	 * 删除广告
	 * @param wxAd
	 * @return
	 */
	int deleteWxAd(WxAd wxAd);
	
	/**
	 * 微信后台管理：删除广告
	 *
	 * @author aizhengdong
	 * @date 2015年8月24日
	 */
	int deleteAdByWxBoss(WxAd wxAd);

}
