package com.pieces.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.LogisticalCommodity;
import com.pieces.dao.vo.LogisticalCommodityVo;
import com.pieces.dao.vo.LogisticalVo;

public interface LogisticalCommodityService extends ICommonService<LogisticalCommodity>{

    public PageInfo<LogisticalCommodityVo> findByParams(LogisticalCommodityVo logisticalCommodityVo, Integer pageNum, Integer pageSize);

	public List<LogisticalCommodityVo> findCommoditys(LogisticalCommodityVo logisticalCommodityVo);
}
