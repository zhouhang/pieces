package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Ad;
import com.pieces.dao.vo.AdVo;

/**
 * Created by wangbin on 2016/8/3.
 */
public interface AdService extends ICommonService<Ad>{

    public Ad createAd(Ad ad);


    public PageInfo<AdVo> findByParam(AdVo adVo, int pageNum, int pageSize);

}
