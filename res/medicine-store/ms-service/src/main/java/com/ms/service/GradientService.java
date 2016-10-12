package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Gradient;
import com.ms.dao.vo.GradientVo;

public interface GradientService extends ICommonService<Gradient>{

    public PageInfo<GradientVo> findByParams(GradientVo gradientVo,Integer pageNum,Integer pageSize);
}
