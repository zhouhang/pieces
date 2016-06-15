package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.JztTask;

public interface JztTaskDao {
    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-01-04 15:05:14
     */
    int insertTask(JztTask record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-01-04 15:05:14
     */
    int insertSelective(JztTask record);
    
    /**
     * 查询所有任务列表
     * 参数:无
     * 返回:List<JztTask>
     * @ibatorgenerated 2015-01-04 15:05:14
     */
    List<JztTask> selectTaskList();
}