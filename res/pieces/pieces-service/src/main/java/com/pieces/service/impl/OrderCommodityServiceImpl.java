package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.OrderCommodityDao;
import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.vo.OrderCommodityVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.OrderCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Author: koabs
 * 8/15/16.
 */
@Service
public class OrderCommodityServiceImpl extends AbsCommonService<OrderCommodity> implements OrderCommodityService {

    @Autowired
    private OrderCommodityDao orderCommodityDao;



    @Override
    public PageInfo<OrderCommodityVo> findByParams(OrderCommodityVo orderCommodityVo,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderCommodityVo>  list = orderCommodityDao.findByParams(orderCommodityVo);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public void save(List<OrderCommodity> list) {
        orderCommodityDao.batchCreate(list);
    }

    @Override
    public ICommonDao<OrderCommodity> getDao() {
        return orderCommodityDao;
    }

    @Override
    public List<OrderCommodity> getCommodityByOrderId(Integer orderId) {
        return orderCommodityDao.findCommodityByOrderId(orderId);
    }
}
