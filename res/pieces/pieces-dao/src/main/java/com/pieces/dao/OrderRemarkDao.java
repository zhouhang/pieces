package com.pieces.dao;

import com.pieces.dao.model.OrderRemark;
import com.pieces.dao.vo.OrderRemarkVo;

import java.util.List;

public interface OrderRemarkDao extends ICommonDao<OrderRemark>{

    public List<OrderRemarkVo> findByParams(OrderRemarkVo orderRemarkVo);

}
