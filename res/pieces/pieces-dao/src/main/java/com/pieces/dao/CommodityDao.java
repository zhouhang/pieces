package com.pieces.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVo;

@AutoMapper
public interface CommodityDao extends ICommonDao<Commodity>{

    public List<CommodityVo> findVoByPage();

    public List<CommodityVo> findByParam (CommodityVo commodity);

    public CommodityVo findVoById(Integer id);

    public List<CommodityVo> findVoByIds(Collection<Integer> ids);

	/**
	 * 根据id 的字符串查询id列表
	 * @param ids
	 * @return
     */
	public List<CommodityVo> findByIds(String ids);

	public List<CommodityVo> findCommodityByBreedId(Integer id);

	List<CommodityVo> findStandardByBreedId(CommodityVo vo);
	
	List<CommodityVo> findFactoryByBreedId(CommodityVo vo);

	public List<CommodityVo> findCommodityByName(CommodityVo commodityVO);
	
	public List<CommodityVo> findCommodityByNameLx(CommodityVo commodityVO);

	public List<Commodity> findByName(String name);

	public List<CommodityVo> findDistinctName(CommodityVo commodityVO);
}
