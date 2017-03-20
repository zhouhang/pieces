package com.pieces.service.impl;

import com.pieces.dao.EnquiryBillsDao;
import com.pieces.dao.EnquiryCommoditysDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.vo.EnquiryBillsVo;
import com.pieces.dao.vo.EnquiryCommoditysVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.EnquiryCommoditysService;
import com.pieces.tools.utils.BeanUtils;
import com.pieces.tools.utils.FileUtil;
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

    public final static String param = "pictureUrl";

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
        removeNullQuoted(list);

        EnquiryBills enquiryBills = new EnquiryBills();
        enquiryBills.setId(billsId);
        enquiryBills.setUpdateTime(new Date());
        enquiryBills.setUpdateUser(memberId);
        enquiryBills.setType(1);
        if (list!= null && list.size()>0){
            enquiryBills.setExpireDate(list.get(0).getExpireDate());
        }

        enquiryBillsDao.update(enquiryBills);

        if(list != null && list.size()>0) {
            enquiryCommoditysDao.quotedUpdate(list);

            // 报价更新后发生短信
            EnquiryBillsVo billsVo = enquiryBillsDao.findVOById(billsId);
            smsService.sendQuotedUpdate(billsVo);
        }
    }

    @Override
    public List<EnquiryCommoditys> findByBillId(Integer billId,Integer userId, Integer pageSize) {
        return FileUtil.convertAbsolutePathToUrl(enquiryCommoditysDao.findByBillId(billId, userId,pageSize),param);
    }

    @Override
    public void create(List<EnquiryCommoditys> enquiryCommoditysList) {
        enquiryCommoditysDao.batchCreate(enquiryCommoditysList);
    }

    @Override
    @Transactional
    public void quoted(List<EnquiryCommoditys> list, Integer memberId, Integer billsId) {
        // 删除空值行
        removeNullQuoted(list);

        EnquiryBills enquiryBills = new EnquiryBills();
        enquiryBills.setId(billsId);
        enquiryBills.setQuotedTime(new Date());
        enquiryBills.setMemberId(memberId);

        enquiryBills.setUpdateTime(new Date());
        enquiryBills.setUpdateUser(memberId);
        enquiryBills.setStatus(1);
        enquiryBills.setType(1);
        if (list!= null && list.size()>0){
            enquiryBills.setExpireDate(list.get(0).getExpireDate());
        }
        enquiryBillsDao.update(enquiryBills);

        if(list != null && list.size()>0) {
            // 报价时给合同价设置默认值
            for (EnquiryCommoditys commoditys :list) {
                commoditys.setPrice(commoditys.getMyPrice());
            }
            enquiryCommoditysDao.quotedUpdate(list);
            // 报价后发生短信
            EnquiryBillsVo billsVo = enquiryBillsDao.findVOById(billsId);
            EnquiryCommoditys commoditys = enquiryCommoditysDao.findById(list.get(0).getId());
            smsService.sendQuoted(billsVo);
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
        List<EnquiryCommoditysVo> list =  enquiryCommoditysDao.findByIds(ids);
        List<EnquiryCommoditys> param = null;
        try {
            param = BeanUtils.copyList(list,EnquiryCommoditys.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
		return param;
	}

    @Override
    @Transactional
    public void priceUpdate(List<EnquiryCommoditys> list, Integer userId) {
        for (EnquiryCommoditys commoditys: list) {
            commoditys.setUserId(userId);
        }
        enquiryCommoditysDao.priceUpdate(list);
    }

    @Override
    public List<EnquiryCommoditysVo> findVoByIds(String ids) {
        List<EnquiryCommoditysVo> list = enquiryCommoditysDao.findByIds(ids);
        list = FileUtil.convertAbsolutePathToUrl(list,param);
        return list;
    }
}
