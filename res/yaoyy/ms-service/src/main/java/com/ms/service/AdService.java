package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Ad;
import com.ms.dao.vo.AdVo;

public interface AdService extends ICommonService<Ad>{

    public PageInfo<AdVo> findByParams(AdVo adVo,Integer pageNum,Integer pageSize);
}
