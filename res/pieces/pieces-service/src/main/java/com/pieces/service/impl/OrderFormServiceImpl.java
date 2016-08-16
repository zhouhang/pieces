package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.OrderFormDao;
import com.pieces.dao.model.OrderForm;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Author: koabs
 * 8/15/16.
 */
@Service
public class OrderFormServiceImpl extends AbsCommonService<OrderForm> implements OrderFormService {

    @Autowired
    private OrderFormDao orderFormDao;


    @Override
    public ICommonDao<OrderForm> getDao() {
        return null;
    }



    @Override
    public PageInfo<OrderFormVo> findByParams(OrderFormVo orderFormVo,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderFormVo>  list = orderFormDao.findByParams(orderFormVo);
        PageInfo page = new PageInfo(list);
        return page;
    }


    @Override
    public void save(OrderFormVo orderFormVo) {

    }

    @Override
    public OrderForm findById(Integer id) {
        return null;
    }
}
