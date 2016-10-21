package com.ms.dao;

import com.ms.dao.model.SampleTracking;
import com.ms.dao.vo.SampleTrackingVo;
import com.ms.dao.config.AutoMapper;

import java.util.List;
@AutoMapper
public interface SampleTrackingDao extends ICommonDao<SampleTracking>{

    public List<SampleTrackingVo> findByParams(SampleTrackingVo sampleTrackingVo);

}
