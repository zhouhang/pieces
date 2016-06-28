package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.BusiPurchaseApply;
import com.jointown.zy.common.vo.BusiPurchaseApplyVo;

public interface BusiPurchaseApplyDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    int deleteByPrimaryKey(Long purchaseId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    int insert(BusiPurchaseApply record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    int insertSelective(BusiPurchaseApply record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    BusiPurchaseApply selectByPrimaryKey(Long purchaseId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    int updateByPrimaryKeySelective(BusiPurchaseApply record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-05-19 21:28:24
     */
    int updateByPrimaryKey(BusiPurchaseApply record);
    
    /**
     * 后台求购信息管理：查询单条采购信息
     *
     * @param purchaseId
     * @return
     *
     * @author aizhengdong
     *
     * @data 2015年6月19日
     */
    BusiPurchaseApplyVo selectBusiPurchaseApplyById(Long purchaseId);
}