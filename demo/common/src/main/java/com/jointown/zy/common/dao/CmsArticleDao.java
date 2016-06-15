package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.CmsArticle;


public interface CmsArticleDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    int insert(CmsArticle record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    int insertSelective(CmsArticle record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    CmsArticle selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    int updateByPrimaryKeySelective(CmsArticle record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    int updateByPrimaryKeyWithBLOBs(CmsArticle record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    int updateByPrimaryKey(CmsArticle record);
}