package com.pieces.service.impl;

import com.pieces.dao.EnquiryBillsDao;
import com.pieces.dao.EnquiryCommoditysDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.vo.EnquiryBillsVo;
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
    private EnquiryBillsDao enquiryBillsDao;

    @Autowired
    private SmsService smsService;

    @Override
    public ICommonDao<EnquiryCommoditys> getDao() {
        return enquiryCommoditysDao;
    }

    @Override
    @Transactional
    public void quotedUpdate(List<EnquiryCommoditys> list, Integer memberId, Integer billsId) {
        EnquiryBills enquiryBills = new EnquiryBills();
        enquiryBills.setId(billsId);
        enquiryBills.setUpdateTime(new Date());
        enquiryBills.setUpdateUser(memberId);
        enquiryBillsDao.update(enquiryBills);

        if(list != null && list.size()>0) {
            removeNullQuoted(list);
            enquiryCommoditysDao.quotedUpdate(list);

            // 报价更新后发生短信
            EnquiryBillsVo billsVo = enquiryBillsDao.findVOById(billsId);
            smsService.sendQuotedUpdate(billsVo.getContactName(), billsVo.getCode(), billsVo.getContactMobile());
        }
    }

    @Override
    public List<EnquiryCommoditys> findByBillId(Integer billId, Integer pageSize) {
        return enquiryCommoditysDao.findByBillId(billId,pageSize);
    }

    @Override
    public List<EnquiryCommoditys> findByBillId(Integer userId, Integer billId, Integer pageSize) {
        return enquiryCommoditysDao.findByBillId(userId, billId, pageSize);
    }

    @Override
    public void create(List<EnquiryCommoditys> enquiryCommoditysList) {
        enquiryCommoditysDao.batchCreate(enquiryCommoditysList);
    }

    @Override
    @Transactional
    public void quoted(List<EnquiryCommoditys> list, Integer memberId, Integer billsId) {
        EnquiryBills enquiryBills = new EnquiryBills();
        enquiryBills.setId(billsId);
        enquiryBills.setQuotedTime(new Date());
        enquiryBills.setMemberId(memberId);

        enquiryBills.setUpdateTime(new Date());
        enquiryBills.setUpdateUser(memberId);
        enquiryBills.setStatus(1);
        enquiryBillsDao.update(enquiryBills);

        // 删除空值行
        removeNullQuoted(list);
        if(list != null && list.size()>0) {
            enquiryCommoditysDao.quotedUpdate(list);
            // 报价后发生短信
            EnquiryBillsVo billsVo = enquiryBillsDao.findVOById(billsId);
            EnquiryCommoditys commoditys = enquiryCommoditysDao.findById(list.get(0).getId());
            smsService.sendQuoted(billsVo.getContactName(), billsVo.getCode(),commoditys.getCommodityName(),
                    billsVo.getContactMobile());
        }
    }

    private void removeNullQuoted(List<EnquiryCommoditys> list){
        if (list != null) {
            Iterator<EnquiryCommoditys> iter = list.iterator();
            while(iter.hasNext()){
                EnquiryCommoditys commoditys = iter.next();
                if(commoditys.getMyPrice() == null && commoditys.getExpireDate() == null){
                    iter.remove();
                }
            }
        }
    }

	@Override
	public List<EnquiryCommoditys> findByIds(String ids) {
		return enquiryCommoditysDao.findByIds(ids);
	}
}
