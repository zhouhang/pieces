package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.OrderRemark;
import com.pieces.dao.vo.OrderRemarkVo;

import java.util.List;
@AutoMapper
public interface OrderRemarkDao extends ICommonDao<OrderRemark>{

    public List<OrderRemarkVo> findByParams(OrderRemarkVo orderRemarkVo);

}
