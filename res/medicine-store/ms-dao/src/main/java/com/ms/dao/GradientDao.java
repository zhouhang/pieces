package com.ms.dao;

import com.ms.dao.model.Gradient;
import com.ms.dao.vo.GradientVo;

import java.util.List;
@AutoMapper
public interface GradientDao extends ICommonDao<Gradient>{

    public List<GradientVo> findByParams(GradientVo gradientVo);

}
