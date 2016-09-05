package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.LogisticalCommodity;
import com.pieces.dao.vo.LogisticalCommodityVo;

public interface LogisticalCommodityService extends ICommonService<LogisticalCommodity>{

    public PageInfo<LogisticalCommodityVo> findByParams(LogisticalCommodityVo logisticalCommodityVo, Integer pageNum, Integer pageSize);
}
