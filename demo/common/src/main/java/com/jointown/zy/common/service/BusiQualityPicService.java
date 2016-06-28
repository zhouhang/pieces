package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.BusiQualitypic;


public interface BusiQualityPicService {
	 /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 19:15:47
     */
    int deleteByPrimaryKey(Long qcid);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 19:15:47
     */
    int insertBusiQualitypic(BusiQualitypic record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 19:15:47
     */
    int insertSelective(BusiQualitypic record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-25 19:15:47
     */
    BusiQualitypic selectByPrimaryKey(Long qcid);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 19:15:47
     */
    int updateByPrimaryKeySelective(BusiQualitypic record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 19:15:47
     */
    int updateByPrimaryKey(BusiQualitypic record);
    
    /**
     * 根据仓单ID和图片编号更新
     * @param record
     * @return
     */
    int updateByWlIdAndPicIndex(BusiQualitypic record);
    
    //根据仓单id查询图片列表
    List<BusiQualitypic> selectAllPicByWLID(String wlid);
    
    //查询质检报告图片
    BusiQualitypic findZJPicByWLID(String wlid);
}