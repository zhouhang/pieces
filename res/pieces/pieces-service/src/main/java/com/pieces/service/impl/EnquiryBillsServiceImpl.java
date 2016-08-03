package com.pieces.service.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.EnquiryBillsDao;
import com.pieces.dao.EnquiryCommoditysDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.EnquiryBillsVo;
import com.pieces.dao.vo.EnquiryRecordVo;
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
    private EnquiryCommoditysDao enquiryCommoditysDao;

    @Autowired
    private EnquiryCommoditysService enquiryCommoditysService;


    @Override
    public ICommonDao<EnquiryBills> getDao() {
        return enquiryBillsDao;
    }


    @Override
    @Transactional
    public void update(List<EnquiryCommoditys> enquiryCommoditysList, User user, int billId) {
        EnquiryBills enquiryBills = findById(billId);
        if(enquiryBills.getStatus().equals(1)){
            throw new RuntimeException("该询价单已报价，无法重新报价!");
        }
        enquiryCommoditysDao.deleteByBillId(billId);
        enquiryBills.setCreateTime(new Date());
        enquiryBillsDao.update(enquiryBills);

        //创建报价单商品
        createCommoditys(enquiryCommoditysList,user.getId(),billId);


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


        //创建报价单商品
        createCommoditys(enquiryCommoditysList,user.getId(),enquiryBills.getId());
    }

    @Override
    public PageInfo<EnquiryBillsVo> findByParam(EnquiryBillsVo enquiryBillsVO, Integer pageNum, Integer pageSize) {
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        return enquiryBillsDao.findByParam(enquiryBillsVO, pageNum, pageSize);
    }

    @Override
    public EnquiryBillsVo findVOById(Integer id) {
        EnquiryBillsVo vo = enquiryBillsDao.findVOById(id);
        vo.setEnquiryCommoditys(enquiryCommoditysDao.findByBillId(id, null));
        return vo;
    }
    @Override
    public PageInfo<EnquiryBills> findByPage(int pageNum, int pageSize,EnquiryRecordVo enquiryRecordVo) {
        return enquiryBillsDao.findByCommoditys(pageNum,pageSize,enquiryRecordVo);
    }


    private void createCommoditys(List<EnquiryCommoditys> enquiryCommoditysList, Integer userId, Integer billId){
        for(EnquiryCommoditys enquiryCommoditys : enquiryCommoditysList){
            enquiryCommoditys.setBillsId(billId);
            enquiryCommoditys.setUserId(userId);
            enquiryCommoditys.setCreateTime(new Date());
        }
        enquiryCommoditysService.create(enquiryCommoditysList);

    }

}
