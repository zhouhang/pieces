package com.pieces.dao;

import java.util.List;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVo;

public interface CommodityDao extends ICommonDao<Commodity>{

    public PageInfo<CommodityVo> findVoByPage(int pageNum, int pageSize);

    public PageInfo<CommodityVo> findByParam (CommodityVo commodity, int pageNum, int pageSize);

    public CommodityVo findVoById(Integer id);

    public List<CommodityVo> findVoByIds(Set<Integer> ids);

	/**
	 * 根据id 的字符串查询id列表
	 * @param ids
	 * @return
     */
	public List<CommodityVo> findByIds(String ids);

	public List<CommodityVo> findCommodityByBreedId(Integer id);

	List<CommodityVo> findStandardByBreedId(String ids);
	
	List<CommodityVo> findFactoryByBreedId(String ids);

	List<Commodity> findByParamNoPage(CommodityVo commodity);
}
