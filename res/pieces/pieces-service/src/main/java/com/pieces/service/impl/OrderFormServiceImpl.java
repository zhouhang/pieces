package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.OrderFormDao;
import com.pieces.dao.enums.OrderEnum;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;

import com.pieces.service.utils.ExcelParse;
import com.pieces.tools.log.api.LogAuditing;
import com.pieces.tools.utils.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Author: koabs
 * 8/15/16.
 */
@Service
public class OrderFormServiceImpl extends AbsCommonService<com.pieces.dao.model.OrderForm> implements OrderFormService {

    @Autowired
    private OrderFormDao orderFormDao;
    @Autowired
    private OrderCommodityService orderCommodityService;
    @Autowired
    private ShippingAddressHistoryService shippingAddressHistoryService;
    @Autowired
    private OrderInvoiceService orderInvoiceService;
    @Autowired
    private SerialNumberService serialNumberService;


    @Override
    public ICommonDao<com.pieces.dao.model.OrderForm> getDao() {
        return orderFormDao;
    }



    @Override
    public PageInfo<OrderFormVo> findByParams(OrderFormVo orderFormVo, Integer pageNum, Integer pageSize) {
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
        String orderCode = serialNumberService.generateOrderCode();
        if (user.getType() ==2) {
            orderFormVo.setAgentId(user.getId());
        } else {
            orderFormVo.setUserId(user.getId());
        }

        shippingAddressHistoryService.create(orderFormVo.getAddress());
        
        OrderInvoice orderInvoice = orderFormVo.getInvoice();
        Integer invoiceId = null;
        if(orderInvoice != null && !"".equals(orderInvoice.getName())){
        	orderInvoiceService.create(orderInvoice);
            invoiceId = orderFormVo.getInvoice().getId();
        }
        orderFormVo.setCode(orderCode);
        orderFormVo.setInvoiceId(invoiceId);
        orderFormVo.setAddrHistoryId(orderFormVo.getAddress().getId());
        orderFormVo.setCreaterTime(new Date());
        orderFormVo.setStatus(OrderEnum.UNPAID.getValue());
        orderFormDao.create(orderFormVo);

        List<OrderCommodity> list = orderFormVo.getCommodities();
        for(OrderCommodity oc : list){
        	oc.setOrderId(orderFormVo.getId());
        }
        orderCommodityService.save(list);
    }

    @Override
    public OrderFormVo findVoById(Integer id) {
        // 商品图片转换.
        OrderFormVo vo = orderFormDao.findVoById(id);
        if (vo!= null) {
            vo.setCommodities(FileUtil.convertAbsolutePathToUrl(vo.getCommodities(),"pictureUrl"));
        }
        return vo;
    }

    @Override
    public PageInfo<OrderFormVo> findOrderByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        OrderFormVo vo = new OrderFormVo();
        vo.setUserId(userId);
        return findOrderByVo(vo,pageNum,pageSize);
    }


    @Override
    public PageInfo<OrderFormVo> findOrderByAgentId(Integer agentId, Integer pageNum, Integer pageSize) {
        OrderFormVo vo = new OrderFormVo();
        vo.setAgentId(agentId);
        return findOrderByVo(vo,pageNum,pageSize);
    }

    private PageInfo<OrderFormVo> findOrderByVo(OrderFormVo vo, Integer pageNum, Integer pageSize){
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

        if(StringUtils.isBlank(orderFormVo.getCode())){
            String orderCode = serialNumberService.generateOrderCode();
            orderFormVo.setCode(orderCode);
        }
        orderFormDao.create(orderFormVo);


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
            Integer time = Integer.parseInt(code[1])+1;
            orderFormVo.setCode(code[0]+"-"+time);
        }
        // 取消订单
        LogAuditing.audit(origOrderId,null,"订单","修改订单取消原来订单重新下单");
        return create(orderFormVo);
    }

    @Override
    @Transactional
    public Result changeOrderStatus(Integer orderId, Integer status) {
        OrderForm form = new OrderForm();
        form.setId(orderId);
        form.setStatus(status);
        if (status == OrderEnum.SHIPPED_FAIL.getValue() ||
                status == OrderEnum.SHIPPED.getValue()) {
            form.setDeliveryDate(new Date());
        }

        if (status == OrderEnum.COMPLETE.getValue()) {
            form.setFinishDate(new Date());
        }
        orderFormDao.update(form);
        LogAuditing.audit(orderFormDao.findById(orderId),form,"订单","修改订单状态");
        return new Result(true).info("");
    }

    @Override
    public OrderForm findByOrderCode(String orderCode) {
        OrderForm orderForm = orderFormDao.findByOrderCode(orderCode);
        return orderForm;
    }

    @Override
    @Transactional
    public void saveInvoice(Integer orderId, OrderInvoice invoice) {
        orderInvoiceService.create(invoice);
        OrderForm orderForm = new OrderForm();
        orderForm.setId(orderId);
        orderForm.setInvoiceId(invoice.getId());
        orderFormDao.update(orderForm);
    }

    @Override
    public Integer countOrderNew() {
        OrderFormVo vo = new OrderFormVo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            vo.setStartTime(sdf.parse(DateFormatUtils.format(new Date(),"yyyy/MM/dd")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orderFormDao.findByParams(vo).size();
    }

    @Override
    public void exportOrderExcel(HttpServletResponse response, HttpServletRequest request, Integer id) {
        OrderFormVo vo = findVoById(id);
        Workbook workbook = ExcelParse.exportOrderInfo(vo);
        ExcelParse.returnExcel(response,request, workbook,"订单详情"+ id);
    }
}
