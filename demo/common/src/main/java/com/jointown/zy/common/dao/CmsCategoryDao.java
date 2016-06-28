package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.CmsCategory;


public interface CmsCategoryDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    int insert(CmsCategory record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    int insertSelective(CmsCategory record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    CmsCategory selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    int updateByPrimaryKeySelective(CmsCategory record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    int updateByPrimaryKey(CmsCategory record);
    /**
     * 通过cmsSiteId查询出该站点下面的所有的所有的目录
     * @param cmsSiteID
     * @return
     */
    List<CmsCategory> selectCmsCategoryByCmsSiteID(Long cmsSiteID);
}