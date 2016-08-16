package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.OrderInvoice;
import com.pieces.dao.vo.OrderInvoiceVo;

import java.util.List;
@AutoMapper
public interface OrderInvoiceDao extends ICommonDao<OrderInvoice>{

    public List<OrderInvoiceVo> findByParams(OrderInvoiceVo orderInvoiceVo);

}
