package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.vo.OrderCommodityVo;

import java.util.List;
@AutoMapper
public interface OrderCommodityDao extends ICommonDao<OrderCommodity>{

    public List<OrderCommodityVo> findByParams(OrderCommodityVo orderCommodityVo);

    public void batchCreate(List<OrderCommodity> list);




}
