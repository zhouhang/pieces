package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.PickCommodity;
import com.ms.dao.vo.PickCommodityVo;

import java.util.List;
@AutoMapper
public interface PickCommodityDao extends ICommonDao<PickCommodity>{

    public List<PickCommodityVo> findByParams(PickCommodityVo pickCommodityVo);



}
