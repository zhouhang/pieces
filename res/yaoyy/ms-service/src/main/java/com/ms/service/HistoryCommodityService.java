package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.HistoryCommodity;
import com.ms.dao.vo.CommodityVo;
import com.ms.dao.vo.HistoryCommodityVo;

import java.util.List;

public interface HistoryCommodityService extends ICommonService<HistoryCommodity>{

    public PageInfo<HistoryCommodityVo> findByParams(HistoryCommodityVo historyCommodityVo,Integer pageNum,Integer pageSize);
    public List<HistoryCommodityVo> findByIds(String ids);
    public HistoryCommodity saveCommodity(CommodityVo commodityVo);
    public  List<HistoryCommodity> findByName(String name);
}
