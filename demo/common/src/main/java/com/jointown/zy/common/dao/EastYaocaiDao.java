package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.EastYaocai;

public interface EastYaocaiDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-03-05 14:06:59
     */
    int deleteByPrimaryKey(String ycnam);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-03-05 14:06:59
     */
    int insert(EastYaocai record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-03-05 14:06:59
     */
    int insertSelective(EastYaocai record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-03-05 14:06:59
     */
    EastYaocai selectByPrimaryKey(String ycnam);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-05 14:06:59
     */
    int updateByPrimaryKeySelective(EastYaocai record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-05 14:06:59
     */
    int updateByPrimaryKey(EastYaocai record);
    
    /**
     * 查询药材品种，根据品种档案
     * @return
     */
    List<EastYaocai> selectEastYaocaiPzs();
}