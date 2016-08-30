package com.pieces.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.CommodityCollect;
import com.pieces.dao.vo.CommodityCollectVo;

public interface CommodityCollectService extends ICommonService<CommodityCollect>{

    public PageInfo<CommodityCollectVo> findByParams(CommodityCollectVo commodityCollectVo, Integer pageNum, Integer pageSize);

	public List<CommodityCollectVo> findByUser(Integer userId);

	public void deleteCollect(CommodityCollect commodityCollect);
}
