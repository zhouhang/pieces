package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Commodity;
import com.ms.dao.vo.CommodityVo;

import java.util.Collection;
import java.util.List;

public interface CommodityService extends ICommonService<Commodity>{

    public PageInfo<CommodityVo> findByParams(CommodityVo commodityVo,Integer pageNum,Integer pageSize);

    public List<CommodityVo> findByIds(String ids);

    public void save(CommodityVo commodity);

    public CommodityVo findById(Integer id);

    public List<Commodity>  searchComodity(CommodityVo commodityVo);

    public List<Commodity> findByName(String name);

    public PageInfo<CommodityVo> findVoByPage(int pageSize,int pageNum);

    public List<CommodityVo> findByCategoryId(Integer id);

    public void updateStatusByCategoryId(Commodity commodity);






}
