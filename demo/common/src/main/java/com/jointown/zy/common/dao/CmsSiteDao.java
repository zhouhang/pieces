package com.jointown.zy.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.CmsSite;

public interface CmsSiteDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-11-25 12:26:56
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-11-25 12:26:56
     */
    int insert(CmsSite cmsSite);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-11-25 12:26:56
     */
    int insertSelective(CmsSite cmsSite);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-25 12:26:56
     */
    CmsSite selectByPrimaryKey(Long id);
    /**
     * 在数据库中查询出所有正常的站点
     * 返回list对象
     * @return
     */
    List <CmsSite> selectAllCmsSite();
    /**
     * 在数据库中更具要求查询数据
     * @return
     */
    List <CmsSite> selectDynamicCmsSite(Map queryMap);
    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-25 12:26:56
     */
    int updateByPrimaryKeySelective(CmsSite cmsSite);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-25 12:26:56
     */
    int updateByPrimaryKeyWithBLOBs(CmsSite cmsSite);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-25 12:26:56
     */
    int updateByPrimaryKey(CmsSite cmsSite);
}