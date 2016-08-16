package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.OrderForm;
import com.pieces.dao.vo.OrderFormVo;

import java.util.List;
@AutoMapper
public interface OrderFormDao extends ICommonDao<OrderForm>{

    public List<OrderFormVo> findByParams(OrderFormVo orderFormVo);

}
