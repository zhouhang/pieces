package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.EnquiryCommoditys;

import java.util.Date;

public interface EnquiryBillsDao extends ICommonDao<EnquiryBills>{

    public PageInfo<EnquiryBills> findByCommoditys(int pageNum, int pageSize, String commodityName, Date startDate,Date endDate);
}
