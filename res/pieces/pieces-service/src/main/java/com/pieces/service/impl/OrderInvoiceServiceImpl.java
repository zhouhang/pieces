package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.vo.OrderInvoiceVo;
import org.springframework.beans.factory.annotation.Autowired;

import com.pieces.dao.ICommonDao;
import com.pieces.dao.OrderInvoiceDao;
import com.pieces.dao.model.OrderInvoice;
import com.pieces.service.AbsCommonService;
import com.pieces.service.OrderInvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInvoiceServiceImpl extends AbsCommonService<OrderInvoice> implements OrderInvoiceService {
		
	@Autowired
	OrderInvoiceDao orderInvoiceDao;



	@Override
	public PageInfo<OrderInvoiceVo> findByParams(OrderInvoiceVo orderInvoiceVo, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<OrderInvoiceVo> list = orderInvoiceDao.findByParams(orderInvoiceVo);
		PageInfo page = new PageInfo(list);
		return page;
	}


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
