package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxAd;
import com.jointown.zy.common.vo.WxAdVo;

/**
 * 微信公众平台开发--广告Dao
 * 
 * @author lichenxiao
 *
 * @data 2015年3月17日
 */
public interface WxAdDao {
	
	/**
	 * 广告列表，分页查询
	 * @param page
	 * @return
	 */
	List<WxAdVo> selectWxAdsByCondition(Page<WxAdVo> page);

    /**
     * 查询数量，条件查询
     * @param wxAd
     * @return
     */
	int selectByCondition(WxAd record);

	/**
	 * 添加广告
	 * @param wxAd
	 * @return
	 */
	int insertSelective(WxAd record);

	/**
	 * 修改广告
	 * @param wxAd
	 * @return
	 */
	int updateByPrimaryKeySelective(WxAd record);
	
	/**
	 * 得到当前广告
	 * @param adId
	 * @return
	 */
	WxAdVo selectByPrimaryKey(Long adId);

	/**
	 * 通过广告位置查找广告
	 * @param adPostionName
	 * @return
	 */
	WxAdVo findWxAdsByPostionName(String adPostionName);

}
