package com.pieces.service.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.AdDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.Ad;
import com.pieces.dao.vo.AdVo;
import com.pieces.dao.vo.CommodityVO;
import com.pieces.service.AbsCommonService;
import com.pieces.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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



    @Transactional
    @Override
    public Ad createAd(Ad ad) {
        ad.setCreateTime(new Date());
        create(ad);
        return ad;
    }

    @Override
    public PageInfo<AdVo> findByParam(AdVo adVo, int pageNum, int pageSize) {
        PageInfo<AdVo> adVoPageInfo = adDao.findByParam(adVo,pageNum,pageSize);
        return adVoPageInfo;
    }


}
