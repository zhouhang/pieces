package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.SampleTracking;
import com.ms.dao.vo.SampleTrackingVo;

public interface SampleTrackingService extends ICommonService<SampleTracking>{

    public PageInfo<SampleTrackingVo> findByParams(SampleTrackingVo sampleTrackingVo,Integer pageNum,Integer pageSize);
}
