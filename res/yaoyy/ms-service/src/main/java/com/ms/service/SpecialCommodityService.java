package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.SpecialCommodity;
import com.ms.dao.vo.SpecialCommodityVo;

public interface SpecialCommodityService extends ICommonService<SpecialCommodity>{

    public PageInfo<SpecialCommodityVo> findByParams(SpecialCommodityVo specialCommodityVo,Integer pageNum,Integer pageSize);
}
