package com.pieces.service.impl;

import com.pieces.dao.EnquiryCommoditysDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.service.AbsCommonService;
import com.pieces.service.EnquiryCommoditysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangbin on 2016/7/21.
 */
@Service
public class EnquiryCommoditysServiceImpl extends AbsCommonService<EnquiryCommoditys> implements EnquiryCommoditysService {
    @Autowired
    private EnquiryCommoditysDao enquiryCommoditysDao;



    @Override
    public ICommonDao<EnquiryCommoditys> getDao() {
        return enquiryCommoditysDao;
    }

    @Override
    public List<EnquiryCommoditys> findByBillId(Integer billId, Integer pageSize) {
        return enquiryCommoditysDao.findByBillId(billId,pageSize);
    }
}
