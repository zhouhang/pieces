package com.ms.dao;

import com.ms.dao.model.Commodity;
import com.ms.dao.vo.CommodityVo;

import java.util.List;

public interface CommodityDao extends ICommonDao<Commodity>{

    public List<CommodityVo> findByParams(CommodityVo commodityVo);

}
