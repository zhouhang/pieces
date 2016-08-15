package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.OrderForm;
import com.pieces.dao.vo.OrderFormVo;

/**
 * Author: koabs
 * 8/15/16.
 */
public interface OrderFormService extends ICommonService<OrderForm>{

    public PageInfo<OrderFormVo> findByParams(OrderFormVo orderFormVo,Integer pageNum,Integer pageSize);

    /**
     * 保存订单
     * @param orderFormVo
     */
    public void save(OrderFormVo orderFormVo);


    /**
     * 根据id查询订单信息
     * @param id
     * @return
     */
    public OrderForm findById(Integer id);
}
