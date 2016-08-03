package com.pieces.service.impl;

import com.pieces.dao.AdDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.Ad;
import com.pieces.service.AbsCommonService;
import com.pieces.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangbin on 2016/8/3.
 */
@Service
public class AdServiceImpl extends AbsCommonService<Ad> implements AdService{

    @Autowired
    private AdDao adDao;


    @Override
    public ICommonDao<Ad> getDao() {
        return adDao;
    }
}
