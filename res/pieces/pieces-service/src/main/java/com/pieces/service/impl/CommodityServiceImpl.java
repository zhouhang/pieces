package com.pieces.service.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.CommodityDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.Area;
import com.pieces.dao.model.Commodity;
import com.pieces.service.AbsCommonService;
import com.pieces.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: koabs
 * 7/12/16.
 */
@Service
public class CommodityServiceImpl  extends AbsCommonService<Commodity> implements CommodityService {

    @Autowired
    CommodityDao commodityDao;

    @Override
    public ICommonDao<Commodity> getDao() {
        return commodityDao;
    }

}