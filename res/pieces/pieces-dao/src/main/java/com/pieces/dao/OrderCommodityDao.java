package com.pieces.dao;

import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.vo.OrderCommodityVo;

import java.util.List;

public interface OrderCommodityDao extends ICommonDao<OrderCommodity>{

    public List<OrderCommodityVo> findByParams(OrderCommodityVo orderCommodityVo);

}
