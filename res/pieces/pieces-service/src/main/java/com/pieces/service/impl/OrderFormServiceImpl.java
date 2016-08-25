package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.OrderFormDao;
import com.pieces.dao.enums.OrderEnum;
import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.model.OrderForm;
import com.pieces.dao.model.OrderInvoice;
import com.pieces.dao.model.ShippingAddressHistory;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.tools.utils.SeqNoUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Author: koabs
 * 8/15/16.
 */
@Service
public class OrderFormServiceImpl extends AbsCommonService<OrderForm> implements OrderFormService {

    @Autowired
    private OrderFormDao orderFormDao;

    @Autowired
    private OrderCommodityService orderCommodityService;

    @Autowired
    private ShippingAddressHistoryService shippingAddressHistoryService;


    @Autowired
    private OrderInvoiceService orderInvoiceService;


    @Override
    public ICommonDao<OrderForm> getDao() {
        return orderFormDao;
    }



    @Override
    public PageInfo<OrderFormVo> findByParams(OrderFormVo orderFormVo,Integer pageNum,Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;

        PageHelper.startPage(pageNum, pageSize);
        List<OrderFormVo>  list = orderFormDao.findByParams(orderFormVo);
        PageInfo page = new PageInfo(list);
        return page;
    }


    @Override
    @Transactional
    public void save(OrderFormVo orderFormVo, User user) {
        // 1. 保存订单信息
        // 2. 订单地址
        // 3. 订单商品
        // 4. 发票信息
        orderFormVo.setUserId(user.getId());
        shippingAddressHistoryService.create(orderFormVo.getAddress());
        
        OrderInvoice orderInvoice = orderFormVo.getInvoice();
        if(!orderInvoice.getName().equals("")){
        	orderInvoiceService.create(orderInvoice);
        }

        orderFormVo.setInvoiceId(orderFormVo.getInvoice().getId());
        orderFormVo.setAddrHistoryId(orderFormVo.getAddress().getId());
        orderFormVo.setCreaterTime(new Date());
        orderFormVo.setStatus(OrderEnum.UNPAID.getValue());
        orderFormDao.create(orderFormVo);
        OrderForm of = new OrderForm();
        of.setId(orderFormVo.getId());
        of.setCode(SeqNoUtil.get("", orderFormVo.getId(), 6));
        orderFormVo.setCode(of.getCode());
        orderFormDao.update(of);
        
        List<OrderCommodity> list = orderFormVo.getCommodities();
        for(OrderCommodity oc : list){
        	oc.setOrderId(orderFormVo.getId());
        }
        orderCommodityService.save(list);
    }

    @Override
    public OrderFormVo findVoById(Integer id) {
        return orderFormDao.findVoById(id);
    }

    @Override
    public PageInfo<OrderFormVo> findOrderByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        OrderFormVo vo = new OrderFormVo();
        vo.setUserId(userId);
        vo.setIsUserSearch(1); // 用来过滤已删除的订单 使其不在前台显示.
        PageInfo<OrderFormVo> page = findByParams(vo,pageNum,pageSize);
        // 根据查询出来的订单查询订单商品信息
        for (OrderFormVo form : page.getList()) {
            List<OrderCommodity> commodityVos = orderCommodityService.getCommodityByOrderId(form.getId());
            form.setCommodities(commodityVos);
        }
        return page;
    }




    /**
     *
     * @param orderFormVo
     * @return
     */
    @Override
    @Transactional
    public OrderFormVo create(OrderFormVo orderFormVo) {
        //保存订单收货地址
        ShippingAddressHistory shippingAddressHistory =  shippingAddressHistoryService.createByAddress(orderFormVo.getShippingAddress());

        //保存订单
        orderFormVo.setCreaterTime(new Date());
        orderFormVo.setStatus(OrderEnum.UNPAID.getValue());
        orderFormVo.setAddrHistoryId(shippingAddressHistory.getId());
        orderFormDao.create(orderFormVo);
        if(StringUtils.isBlank(orderFormVo.getCode())){
            orderFormVo.setCode(SeqNoUtil.get("", orderFormVo.getId(), 6));
        }
        orderFormDao.update(orderFormVo);

        //保存商品
        List<OrderCommodity> list = orderFormVo.getCommodities();
        for(OrderCommodity commodity : list){
            BigDecimal total= new BigDecimal(commodity.getAmount()).multiply(new BigDecimal(commodity.getPrice()));
            commodity.setSubtotal(total.doubleValue());
            commodity.setOrderId(orderFormVo.getId());
        }
        orderCommodityService.save(list);

        return orderFormVo;
    }


    @Override
    @Transactional
    public OrderFormVo create(OrderFormVo orderFormVo, Integer origOrderId){
        //原订单状态改成已取消
        OrderForm orderForm =findById(origOrderId);
        orderForm.setStatus(OrderEnum.CANCEL.getValue());
        orderFormDao.update(orderForm);
        //改变订单号
        String origCode = orderForm.getCode();
        if(!origCode.contains("-")){
            orderFormVo.setCode(origCode+"-1");
        }else{
            String[] code = origCode.split("-");
            Integer time = Integer.valueOf(code[1])+1;
            orderFormVo.setCode(code[0]+"-"+time);
        }
        return create(orderFormVo);
    }

    @Override
    @Transactional
    public Result changeOrderStatus(Integer orderId, Integer status) {
        OrderForm form = new OrderForm();
        form.setId(orderId);
        form.setStatus(status);
        orderFormDao.update(form);
        return new Result(true).info("");
    }
}
