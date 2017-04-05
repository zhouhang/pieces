package com.pieces.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVo;
import org.apache.ibatis.annotations.Param;

@AutoMapper
public interface CommodityDao extends ICommonDao<Commodity>{

	List<CommodityVo> findVoByPage();

	List<CommodityVo> findByParam (CommodityVo commodity);

	CommodityVo findVoById(Integer id);

	List<CommodityVo> findVoByIds(Collection<Integer> ids);

	/**
	 * 根据id 的字符串查询id列表
	 * @param ids
	 * @return
     */
	List<CommodityVo> findByIds(String ids);

	List<CommodityVo> findCommodityByBreedId(Integer id);

	List<CommodityVo> findStandardByBreedId(CommodityVo vo);
	
	List<CommodityVo> findFactoryByBreedId(CommodityVo vo);

	List<CommodityVo> findCommodityByName(CommodityVo commodityVO);
	
	List<CommodityVo> findCommodityByNameLx(CommodityVo commodityVO);

	List<Commodity> findByName(String name);

	List<CommodityVo> findDistinctName(CommodityVo commodityVO);

	// 批量更新商品信息
	Integer batchUpdate(List<Commodity> list);

	List<CommodityVo> searchForOrder(@Param("userId")Integer userId, @Param("name") String name);


	List<CommodityVo> searchOrderByIds(@Param("userId")Integer userId, @Param("ids") String ids);
}
