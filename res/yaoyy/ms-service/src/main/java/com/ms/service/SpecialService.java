package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Commodity;
import com.ms.dao.model.Special;
import com.ms.dao.vo.CommodityVo;
import com.ms.dao.vo.SpecialVo;

import java.util.List;

public interface SpecialService extends ICommonService<Special>{

    public PageInfo<SpecialVo> findByParams(SpecialVo specialVo,Integer pageNum,Integer pageSize);

    /**
     * 根据专场id获取商品信息
     * @param specId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<CommodityVo> findCommodity(Integer specId, Integer pageNum,Integer pageSize );

    public List<CommodityVo> findCommodity(Integer specId);

    public void save(SpecialVo specialVo);
}
