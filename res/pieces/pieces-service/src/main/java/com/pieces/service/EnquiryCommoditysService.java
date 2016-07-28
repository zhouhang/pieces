package com.pieces.service;

import com.pieces.dao.model.EnquiryCommoditys;

import java.util.List;

/**
 * Created by wangbin on 2016/7/21.
 */
public interface EnquiryCommoditysService extends ICommonService<EnquiryCommoditys>{

    public List<EnquiryCommoditys> findByBillId(Integer billId, Integer pageSize);


    /**
     * 报价
     * 更新报价人,报价时间字段
     * @param list
     * @return
     */
    public Integer quoted(List<EnquiryCommoditys> list, Integer memberId, Integer billsId);



    /**
     * 更新报价信息
     * 更新修改人,修改时间字段
     * @param list
     * @return
     */
    public Integer quotedUpdate(List<EnquiryCommoditys> list, Integer menmberId, Integer billsId);

}
