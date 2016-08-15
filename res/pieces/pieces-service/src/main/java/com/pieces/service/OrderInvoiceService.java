package com.pieces.service;

import com.pieces.dao.model.OrderInvoice;

/**
 * 发票service
 * @author feng
 *
 */
public interface OrderInvoiceService {

	OrderInvoice getInvoicebyOrderId(Integer orderId);
	
}
