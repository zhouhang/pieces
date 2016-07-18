package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVO;

public interface CommodityDao extends ICommonDao<Commodity>{

    public PageInfo<CommodityVO> findVoByPage(int pageNum, int pageSize);

    public PageInfo<CommodityVO> findByParam (CommodityVO commodity, int pageNum, int pageSize);

	public CommodityVO findCommodityByBreedId(Integer id);
}
