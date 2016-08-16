package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.vo.OrderCommodityVo;

import java.util.List;

/**
 * Author: koabs
 * 8/15/16.
 */
public interface OrderCommodityService extends ICommonService<OrderCommodity> {



    public PageInfo<OrderCommodityVo> findByParams(OrderCommodityVo orderCommodityVo,Integer pageNum,Integer pageSize);

    /**
     * 保存订单商品列表
     * @param list
     */
    public void save(List<OrderCommodity> list);

}
