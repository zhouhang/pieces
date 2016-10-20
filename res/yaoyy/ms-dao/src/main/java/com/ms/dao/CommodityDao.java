package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.Commodity;
import com.ms.dao.vo.CommodityVo;

import java.util.Collection;
import java.util.List;

@AutoMapper
public interface CommodityDao extends ICommonDao<Commodity>{

    public List<CommodityVo> findByParams(CommodityVo commodityVo);

    public List<Commodity>  findByIds(Collection<Integer> collection);

}
