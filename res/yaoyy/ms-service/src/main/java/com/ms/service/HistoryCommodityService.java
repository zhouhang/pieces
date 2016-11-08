package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.HistoryCommodity;
import com.ms.dao.vo.HistoryCommodityVo;

public interface HistoryCommodityService extends ICommonService<HistoryCommodity>{

    public PageInfo<HistoryCommodityVo> findByParams(HistoryCommodityVo historyCommodityVo,Integer pageNum,Integer pageSize);
}
