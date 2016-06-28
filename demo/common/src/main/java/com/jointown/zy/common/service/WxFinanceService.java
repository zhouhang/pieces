package com.jointown.zy.common.service;

import com.jointown.zy.common.dto.WxFinanceDto;

/**
 * 我要融资
 * @author Mr.song
 *
 */
public interface WxFinanceService {

    /**
     * 保存我要融资基本数据
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-08-25 12:30:34
     */
    public boolean saveFinance(WxFinanceDto wxFinanceDto);
}