package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.CommodityCollect;
import com.pieces.dao.vo.CommodityCollectVo;

public interface CommodityCollectService extends ICommonService<CommodityCollect>{

    public PageInfo<CommodityCollectVo> findByParams(CommodityCollectVo commodityCollectVo, Integer pageNum, Integer pageSize);
}
