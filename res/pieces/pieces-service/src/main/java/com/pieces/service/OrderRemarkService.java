package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.OrderRemark;
import com.pieces.dao.vo.OrderRemarkVo;

import java.util.List;

public interface OrderRemarkService extends ICommonService<OrderRemark>{

    public PageInfo<OrderRemarkVo> findByParams(OrderRemarkVo orderRemarkVo, Integer pageNum, Integer pageSize);

    /**
     * 根据订单获取用户的
     * @param orderId
     * @return
     */
    public List<OrderRemarkVo> findByOrderId(Integer orderId);
}
