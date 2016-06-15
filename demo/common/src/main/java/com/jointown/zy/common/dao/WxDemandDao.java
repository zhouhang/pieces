package com.jointown.zy.common.dao;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxDemand;
import com.jointown.zy.common.vo.WxDemandVo;

public interface WxDemandDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-03-12 18:12:35
     */
    int deleteByPrimaryKey(Long demandId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-03-12 18:12:35
     */
    int insert(WxDemand record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-03-12 18:12:35
     */
    int insertSelective(WxDemand record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-03-12 18:12:35
     */
    WxDemand selectByPrimaryKey(Long demandId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-12 18:12:35
     */
    int updateByPrimaryKeySelective(WxDemand record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-12 18:12:35
     */
    int updateByPrimaryKey(WxDemand record);
    
    /**
     * 后台审核：查询所有求购信息
     *
     * @param page
     * @return
     *
     * @author aizhengdong
     *
     * @data 2015年3月27日
     */
    List<WxDemandVo> selectWxDemandsByCondition(Page<WxDemandVo> page);
    

    /**
     * @Description:查询东网与珍药材的求购信息（翻页，每次显示5条）
     * @author lichenxiao
     * @Date:2015年4月28日下午16：10
     * @param map
     * @return
     */
	List<WxDemandVo> selectInfoByDemand(Map<String, Object> map);
	
	/**
	 * 
	 * @Description: 查询微信求购信息，分页条件查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月15日
	 * @param page
	 * @return
	 */
	List<WxDemandVo> selectWxDemandsByPage(Page<WxDemand> page);
	
	/**
	 * 
	 * @Description: 查询微信求购信息，条件查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月15日
	 * @param wxDemand
	 * @return
	 */
	WxDemandVo selectWxDemandById(WxDemand wxDemand);
	
	/**
	 * 后台求购信息管理：查询单条微信求购信息
	 *
	 * @param wxDemand
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年6月19日
	 */
	WxDemandVo selectWxDemandByIdFromBack(WxDemand wxDemand);


}