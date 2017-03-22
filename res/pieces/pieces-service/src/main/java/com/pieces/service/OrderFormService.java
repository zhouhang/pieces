package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.OrderForm;
import com.pieces.dao.model.OrderInvoice;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.service.constant.bean.Result;

/**
 * Author: koabs
 * 8/15/16.
 */
public interface OrderFormService extends ICommonService<OrderForm>{

    /**
     * 根据参数查询订单列表
     * @param orderFormVo
     * @param pageNum
     * @param pageSize
     * @return
     */
     PageInfo<OrderFormVo> findByParams(OrderFormVo orderFormVo, Integer pageNum, Integer pageSize);

    /**
     * 保存订单
     * @param orderFormVo
     */
     void save(OrderFormVo orderFormVo, User user);


    /**
     * 根据id查询订单信息
     * @param id
     * @return
     */
     OrderFormVo findVoById(Integer id);


    /**
     *根据用户的id 获取用的订单列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<OrderFormVo> findOrderByUserId(Integer userId, Integer pageNum, Integer pageSize);

    /**
     *根据代理商id 获取用户订单列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<OrderFormVo> findOrderByAgentId(Integer userId, Integer pageNum, Integer pageSize);

    OrderFormVo create(OrderFormVo orderFormVo);


    OrderFormVo create(OrderFormVo orderFormVo, Integer origOrderId);
    /**
     * 修改订单状态
     * @param orderId
     * @param status
     * @return
     */
    Result changeOrderStatus(Integer orderId, Integer status);


    OrderForm findByOrderCode(String orderCode);


    /**
     * 补开发票
     * @param orderId
     * @param invoice
     */
    void saveInvoice(Integer orderId, OrderInvoice invoice);

    /**
     * 统计当天新增的订单
     * @return
     */
    Integer countOrderNew();

}
