package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Commodity;
import com.ms.dao.model.Gradient;
import com.ms.dao.vo.CommodityVo;
import com.ms.dao.vo.GradientVo;

import java.util.List;

public interface GradientService extends ICommonService<Gradient>{

    public PageInfo<GradientVo> findByParams(GradientVo gradientVo,Integer pageNum,Integer pageSize);

    /**
     * 更新价格区间
     * 先删除再更新
     * @param list
     */
    public void update(List<Gradient> list);

    public List<Gradient> findByCommodityId(Integer id);

    public void deleteByCommodityId(Integer id);

    /**
     * 根据商品id 获取商品价格范围
     * @param id
     * @return
     */
    public String getCommodityPrice(Integer id);
}
