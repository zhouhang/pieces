package com.pieces.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVO;

public interface CommodityDao extends ICommonDao<Commodity>{

    public PageInfo<CommodityVO> findVoByPage(int pageNum, int pageSize);

    public PageInfo<CommodityVO> findByParam (CommodityVO commodity, int pageNum, int pageSize);

    public CommodityVO findVoById(Integer id);

	public List<CommodityVO> findCommodityByBreedId(Integer id);

	List<CommodityVO> findStandardByBreedId(String ids);
	
	List<CommodityVO> findFactoryByBreedId(String ids);

	List<Commodity> findByParamNoPage(CommodityVO commodity);
}
