package com.pieces.service.impl;

import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.OrderCommodity;
import com.pieces.service.AbsCommonService;
import com.pieces.service.OrderCommodityService;

import java.util.List;

/**
 * Author: koabs
 * 8/15/16.
 */
public class OrderCommodityServiceImpl extends AbsCommonService<OrderCommodity> implements OrderCommodityService {

    @Override
    public ICommonDao<OrderCommodity> getDao() {
        return null;
    }


    @Override
    public void save(List<OrderCommodity> list) {

    }

    @Override
    public void getCommodityByOrderId(Integer orderId) {

    }
}
