package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.BusiQualityinfo;


public interface BusiQualityInfoDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    int deleteByPrimaryKey(Long qualityinfoid);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    int insertQualityInfo(BusiQualityinfo record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    int insertSelective(BusiQualityinfo record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    BusiQualityinfo selectByPrimaryKey(Long qualityinfoid);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    int updateByPrimaryKeySelective(BusiQualityinfo record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    int updateByPrimaryKey(BusiQualityinfo record);
    
    /**
     * 根据仓单id修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    int updateByWLIDSelective(BusiQualityinfo record);

    /**
     * 根据仓单id查询
     * 参数:查询条件,仓单id
     * 返回:对象
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    BusiQualityinfo selectByWLID(String wlid);
}