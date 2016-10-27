package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.SpecialCommodity;
import com.ms.dao.vo.SpecialCommodityVo;

import java.util.List;
@AutoMapper
public interface SpecialCommodityDao extends ICommonDao<SpecialCommodity>{

    public List<SpecialCommodityVo> findByParams(SpecialCommodityVo specialCommodityVo);

    public void deleteBySpecialId(Integer specialId);

}
