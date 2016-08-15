package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.OrderRemark;
import com.pieces.dao.vo.OrderRemarkVo;

public interface OrderRemarkService extends ICommonService<OrderRemark>{

    public PageInfo<OrderRemarkVo> findByParams(OrderRemarkVo orderRemarkVo, Integer pageNum, Integer pageSize);
}
