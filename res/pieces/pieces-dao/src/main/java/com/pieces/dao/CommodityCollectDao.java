package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.CommodityCollect;
import com.pieces.dao.vo.CommodityCollectVo;

import java.util.List;
@AutoMapper
public interface CommodityCollectDao extends ICommonDao<CommodityCollect>{

    public List<CommodityCollectVo> findByParams(CommodityCollectVo commodityCollectVo);

	public List<CommodityCollectVo> findByUser(Integer userId);

	public void deleteCollect(CommodityCollect commodityCollect);

}
