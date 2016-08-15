package com.pieces.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.pieces.dao.ICommonDao;
import com.pieces.dao.OrderInvoiceDao;
import com.pieces.dao.model.OrderInvoice;
import com.pieces.service.AbsCommonService;
import com.pieces.service.OrderInvoiceService;

public class OrderInvoiceServiceImpl extends AbsCommonService<OrderInvoice> implements OrderInvoiceService {
		
	@Autowired
	OrderInvoiceDao orderInvoiceDao;
	
	@Override
	public ICommonDao<OrderInvoice> getDao() {
		return orderInvoiceDao;
	}
	
	@Override
	public OrderInvoice getInvoicebyOrderId(Integer orderId){
		//获取订单
		//通过发票id获取发票
		return null;
	}

}
