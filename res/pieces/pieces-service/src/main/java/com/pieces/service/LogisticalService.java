package com.pieces.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Logistical;
import com.pieces.dao.vo.LogisticalVo;

public interface LogisticalService extends ICommonService<Logistical>{

    public PageInfo<LogisticalVo> findByParams(LogisticalVo logisticalVo, Integer pageNum, Integer pageSize);
    
    public PageInfo<LogisticalVo> findByUser(LogisticalVo logisticalVo, Integer pageNum, Integer pageSize);

    /**
     * 保存物流信息
     * @param logistical
     * @return
     */
    Logistical save(Logistical logistical);

    /**
     * 根据订单ID 查询物流信息
     * @param orderId
     * @return
     */
    LogisticalVo findByOrderId(Integer orderId);

    // 订阅第三方物流

    // 接收第三方物流平台的回调信息

    // 查询快递信息和轨迹(本地或者快递编号和公司直接查询)
}
