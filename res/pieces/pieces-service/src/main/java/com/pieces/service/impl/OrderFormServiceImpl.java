package com.pieces.service.impl;

import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.OrderForm;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.OrderFormService;

/**
 * Author: koabs
 * 8/15/16.
 */
public class OrderFormServiceImpl extends AbsCommonService<OrderForm> implements OrderFormService {
    @Override
    public ICommonDao<OrderForm> getDao() {
        return null;
    }

    @Override
    public void save(OrderFormVo orderFormVo) {

    }

    @Override
    public OrderForm findById(Integer id) {
        return null;
    }
}
