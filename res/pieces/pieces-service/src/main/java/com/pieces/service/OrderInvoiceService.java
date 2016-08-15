package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.OrderInvoice;
import com.pieces.dao.vo.OrderInvoiceVo;
/**
 * 发票service
 * @author feng
 *
 */
public interface OrderInvoiceService extends ICommonService<OrderInvoice>{

	public PageInfo<OrderInvoiceVo> findByParams(OrderInvoiceVo orderInvoiceVo,Integer pageNum,Integer pageSize);


	OrderInvoice getInvoicebyOrderId(Integer orderId);
	
}
