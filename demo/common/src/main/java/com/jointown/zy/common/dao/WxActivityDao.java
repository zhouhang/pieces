package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxActivity;
import com.jointown.zy.common.vo.WxActivityVo;

/**
 * 微信公众平台开发--活动Dao
 * 
 * @author aizhengdong
 *
 * @data 2015年2月14日
 */
public interface WxActivityDao {

	/**
     * 根据主键删除
     * 参数:主键值
     * 返回:删除个数
     * @ibatorgenerated 2015-03-01 16:47:31
     */
    int deleteByPrimaryKey(Long activityId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-03-01 16:47:31
     */
    int insert(WxActivity record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-03-01 16:47:31
     */
    int insertSelective(WxActivity record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-03-01 16:47:31
     */
    WxActivityVo selectByPrimaryKey(Long activityId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-01 16:47:31
     */
    int updateByPrimaryKeySelective(WxActivity record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-01 16:47:31
     */
    int updateByPrimaryKey(WxActivity record);
    
    /**
     * 查询活动，分页查询
     * @param page
     * @return
     */
    List<WxActivityVo> selectWxActivitysByCondition(Page<WxActivityVo> page);
    
    /**
     * 根据对象查询
     * 参数:查询条件,对象
     * 返回:成功查询个数
     * @ibatorgenerated 2015-03-01 16:47:31
     */
    int selectByCondition(WxActivity record);

	/**
	 * 找活动
	 * 
	 * @return
	 */
	List<WxActivity> findActivity();
	
	/**
	 * 查询卖药材介绍
	 * 
	 * @return
	 */
	List<WxActivity> findSellInfo();
	
}
