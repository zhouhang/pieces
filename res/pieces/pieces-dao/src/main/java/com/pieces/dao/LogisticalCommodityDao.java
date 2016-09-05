package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.LogisticalCommodity;
import com.pieces.dao.vo.LogisticalCommodityVo;
import com.pieces.dao.vo.LogisticalVo;

import java.util.List;
@AutoMapper
public interface LogisticalCommodityDao extends ICommonDao<LogisticalCommodity>{

    public List<LogisticalCommodityVo> findByParams(LogisticalCommodityVo logisticalCommodityVo);

	public List<LogisticalCommodityVo> findCommoditys(LogisticalCommodityVo logisticalCommodityVo);

}
