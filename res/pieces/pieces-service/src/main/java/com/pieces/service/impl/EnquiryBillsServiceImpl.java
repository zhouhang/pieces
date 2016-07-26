package com.pieces.service.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.EnquiryBillsDao;
import com.pieces.dao.EnquiryCommoditysDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.service.AbsCommonService;
import com.pieces.service.EnquiryBillsService;
import com.pieces.service.EnquiryCommoditysService;
import com.pieces.tools.utils.SeqNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wangbin on 2016/7/21.
 */
@Service
public class EnquiryBillsServiceImpl extends AbsCommonService<EnquiryBills> implements EnquiryBillsService{

    @Autowired
    private EnquiryBillsDao enquiryBillsDao;
    @Autowired
    private EnquiryCommoditysService enquiryCommoditysService;


    @Override
    public ICommonDao<EnquiryBills> getDao() {
        return enquiryBillsDao;
    }


    @Override
    @Transactional
    public void create(List<EnquiryCommoditys> enquiryCommoditysList, User user) {
        EnquiryBills enquiryBills = new EnquiryBills();
        enquiryBills.setUserId(user.getId());
        enquiryBills.setCreateTime(new Date());
        enquiryBills.setStatus(0);
        enquiryBillsDao.create(enquiryBills);
        String code = SeqNoUtil.getEnquiryCode(enquiryBills.getId(),5);
        enquiryBills.setCode(code);
        enquiryBillsDao.update(enquiryBills);

        for(EnquiryCommoditys enquiryCommoditys : enquiryCommoditysList){
            enquiryCommoditys.setBillsId(enquiryBills.getId());
            enquiryCommoditys.setUserId(user.getId());
            enquiryCommoditys.setCreateTime(new Date());
            enquiryCommoditysService.create(enquiryCommoditys);
        }
    }

    @Override
    public PageInfo<EnquiryBills> findByPage(int pageNum, int pageSize, String commodityName, Date startDate, Date endDate) {
        return enquiryBillsDao.findByCommoditys(pageNum,pageSize,commodityName,startDate,endDate);
    }

}
