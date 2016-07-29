package com.pieces.dao;

import com.pieces.dao.model.EnquiryCommoditys;

import java.util.List;

public interface EnquiryCommoditysDao extends ICommonDao<EnquiryCommoditys>{

    public List<EnquiryCommoditys> findByBillId(Integer billId,Integer pageSize);

    public List<EnquiryCommoditys> findByBillId(Integer userId,Integer billId,Integer pageSize);

    /**
     * 批量更新报价信息
     * @param list
     * @return
     */
    public Integer quotedUpdate(List<EnquiryCommoditys> list);

    public void deleteByBillId(Integer billId);
	
}
