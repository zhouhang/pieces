package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.BusiQuote;

public interface BusiQuoteLogDao {

    /**
     * @Description: 添加报价
     * @Author:
     * @Date: 2015年10月16日
     * @param quoteInfo 订单信息
     * @param remark 日志备注信息
     * @param operatorId 操作者ID
     * @param optype 操作类型
     * @return
     */
    int insertBusiQuoteLog(BusiQuote  quoteInfo, String remark, String operatorId, String optype);

 
}
