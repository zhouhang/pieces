package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.WxFinance;

/**
 * 我要融资
 * @author Mr.song
 *
 */
public interface WxFinanceDao {

    /**
     * 保存我要融资基本数据
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-08-25 12:30:34
     */
    int insertSelective(WxFinance record);
}