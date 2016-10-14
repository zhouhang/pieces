package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Commodity;
import com.ms.dao.vo.CommodityVo;

public interface CommodityService extends ICommonService<Commodity>{

    public PageInfo<CommodityVo> findByParams(CommodityVo commodityVo,Integer pageNum,Integer pageSize);
}
