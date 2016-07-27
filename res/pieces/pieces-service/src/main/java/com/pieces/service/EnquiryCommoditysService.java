package com.pieces.service;

import com.pieces.dao.model.EnquiryCommoditys;

import java.util.List;

/**
 * Created by wangbin on 2016/7/21.
 */
public interface EnquiryCommoditysService extends ICommonService<EnquiryCommoditys>{

    public List<EnquiryCommoditys> findByBillId(Integer billId, Integer pageSize);

}
