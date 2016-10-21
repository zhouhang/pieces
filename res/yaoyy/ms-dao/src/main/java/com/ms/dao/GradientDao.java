package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.Gradient;
import com.ms.dao.vo.GradientVo;

import java.util.List;

@AutoMapper
public interface GradientDao extends ICommonDao<Gradient>{

    public List<GradientVo> findByParams(GradientVo gradientVo);

    public List<Gradient> findByCommodityId(Integer id);

    public Integer deleteByCommodityId(Integer id);

    public Integer batchCreate(List<Gradient> list);
}
