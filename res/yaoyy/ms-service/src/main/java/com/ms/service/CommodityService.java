package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Commodity;
import com.ms.dao.vo.CommodityVo;

import java.util.Collection;
import java.util.List;

public interface CommodityService extends ICommonService<Commodity>{

    public PageInfo<CommodityVo> findByParams(CommodityVo commodityVo,Integer pageNum,Integer pageSize);

    public List<Commodity> findByIds(String ids);
}
