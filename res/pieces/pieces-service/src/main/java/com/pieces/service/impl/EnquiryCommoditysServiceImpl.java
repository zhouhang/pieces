package com.pieces.service.impl;

import com.pieces.dao.EnquiryBillsDao;
import com.pieces.dao.EnquiryCommoditysDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.service.AbsCommonService;
import com.pieces.service.EnquiryCommoditysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangbin on 2016/7/21.
 */
@Service
public class EnquiryCommoditysServiceImpl extends AbsCommonService<EnquiryCommoditys> implements EnquiryCommoditysService {
    @Autowired
    private EnquiryCommoditysDao enquiryCommoditysDao;

    @Autowired
    EnquiryBillsDao enquiryBillsDao;

    @Override
    public ICommonDao<EnquiryCommoditys> getDao() {
        return enquiryCommoditysDao;
    }

    @Override
    @Transactional
    public Integer quotedUpdate(List<EnquiryCommoditys> list, Integer memberId, Integer billsId) {
        EnquiryBills enquiryBills = new EnquiryBills();

        Iterator<EnquiryCommoditys> iter = list.iterator();
        while(iter.hasNext()){
            EnquiryCommoditys commoditys = iter.next();
            if(commoditys.getMyPrice() == null && commoditys.getExpireDate() == null){
                iter.remove();
            }
        }
        enquiryBills.setId(billsId);
        enquiryBills.setUpdateTime(new Date());
        enquiryBills.setUpdateUser(memberId);
        enquiryBillsDao.update(enquiryBills);
        return enquiryCommoditysDao.quotedUpdate(list);
    }

    @Override
    public List<EnquiryCommoditys> findByBillId(Integer billId, Integer pageSize) {
        return enquiryCommoditysDao.findByBillId(billId,pageSize);
    }

    @Override
    @Transactional
    public Integer quoted(List<EnquiryCommoditys> list, Integer memberId, Integer billsId) {
        EnquiryBills enquiryBills = new EnquiryBills();
        enquiryBills.setId(billsId);
        enquiryBills.setQuotedTime(new Date());
        enquiryBills.setMemberId(memberId);

        enquiryBills.setUpdateTime(new Date());
        enquiryBills.setUpdateUser(memberId);
        enquiryBills.setStatus(1);
        enquiryBillsDao.update(enquiryBills);
        Iterator<EnquiryCommoditys> iter = list.iterator();
        while(iter.hasNext()){
            EnquiryCommoditys commoditys = iter.next();
            if(commoditys.getMyPrice() == null && commoditys.getExpireDate() == null){
                iter.remove();
            }
        }
        return enquiryCommoditysDao.quotedUpdate(list);
    }
}
