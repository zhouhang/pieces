package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.BusiOrderSalesman;

public interface BusiOrderSalesmanDao {

    int deleteByPrimaryKey(Long infoId) throws Exception;

    int insert(BusiOrderSalesman record) throws Exception;

    BusiOrderSalesman selectByPrimaryKey(Long infoId) throws Exception;

    int updateByPrimaryKeySelective(BusiOrderSalesman record) throws Exception;

    int updateByPrimaryKey(BusiOrderSalesman record) throws Exception;
    
    BusiOrderSalesman selectByOrderid(String orderid) throws Exception;
    
}