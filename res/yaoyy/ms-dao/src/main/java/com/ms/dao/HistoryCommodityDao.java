package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.HistoryCommodity;
import com.ms.dao.vo.HistoryCommodityVo;

import java.util.List;
@AutoMapper
public interface HistoryCommodityDao extends ICommonDao<HistoryCommodity>{

    public List<HistoryCommodityVo> findByParams(HistoryCommodityVo historyCommodityVo);

}
