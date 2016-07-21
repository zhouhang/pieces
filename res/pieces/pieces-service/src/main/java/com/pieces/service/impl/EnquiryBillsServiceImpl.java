package com.pieces.service.impl;

import com.pieces.dao.EnquiryBillsDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.service.AbsCommonService;
import com.pieces.service.EnquiryBillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangbin on 2016/7/21.
 */
@Service
public class EnquiryBillsServiceImpl extends AbsCommonService<EnquiryBills> implements EnquiryBillsService{
    @Autowired
    private EnquiryBillsDao enquiryBillsDao;


    @Override
    public ICommonDao<EnquiryBills> getDao() {
        return enquiryBillsDao;
    }
}
