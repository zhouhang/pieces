package com.pieces.dao;

import com.pieces.dao.model.EnquiryCommoditys;

import java.util.List;

public interface EnquiryCommoditysDao extends ICommonDao<EnquiryCommoditys>{

    public List<EnquiryCommoditys> findByBillId(Integer billId,Integer pageSize);
	
}
