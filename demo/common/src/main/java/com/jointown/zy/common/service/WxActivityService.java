package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxActivity;
import com.jointown.zy.common.vo.WxActivityVo;
import com.jointown.zy.common.vo.WxReqBaseMessageVo;

/**
 * 微信公众平台开发--活动Service
 * 
 * @author aizhengdong
 *
 * @data 2015年2月14日
 */
public interface WxActivityService {
    
	/**
	 * 找活动
	 * 
	 * @param reqMessage 接收的消息
	 * @return 图文消息
	 */
	String findActivity(WxReqBaseMessageVo reqMessage);
	
	/**
	 * 卖药材介绍
	 * 
	 * @param reqMessage 接收的消息
	 * @return 图文消息
	 */
	String findSellInfo(WxReqBaseMessageVo reqMessage);
	

	/**
     * 查询活动，分页查询
     * @param page
     * @return
     */
    List<WxActivityVo> findWxActivitysByCondition(Page<WxActivityVo> page);
    
    /**
     * 查询数量，条件查询
     * @param wxActivity
     * @return
     */
    int findByCondition(WxActivity wxActivity);
    /**
     * 添加活动
     * @param wxActivity
     * @return
     */
    int addWxActivity(WxActivity wxActivity);
    
    /**
     * 微信后台管理：添加活动
     * 
	 * @author aizhengdong
	 * @date 2015年8月18日
     */
    int addActivityByWxBoss(WxActivity wxActivity);
    
    /**
     * 更新活动
     * @param wxActivity
     * @return
     */
    int updateWxActivity(WxActivity wxActivity);
    
    /**
     * 微信后台管理：更新活动
     * 
	 * @author aizhengdong
	 * @date 2015年8月18日
     */
    int updateActivityByWxBoss(WxActivity wxActivity);
    
    /**
     * 删除活动
     * @param wxActivity
     * @return
     */
    int deleteWxActivity(WxActivity wxActivity);
    
    /**
     * 微信后台管理：删除活动
     * 
	 * @author aizhengdong
	 * @date 2015年8月19日
     */
    int deleteActivityByWxBoss(WxActivity wxActivity);
    
    /**
     * 查找活动，根据ID
     * @param activityId
     * @return
     */
    WxActivityVo findWxActivityById(Long activityId);
    
	/**
	 * 保存图片到资源服务器
	 * @param filePath
	 * @throws Exception
	 */
    String saveWxActivityPic(String filePath);
}
