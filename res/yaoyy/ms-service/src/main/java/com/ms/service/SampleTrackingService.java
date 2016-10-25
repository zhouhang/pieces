package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.SampleTracking;
import com.ms.dao.model.TrackingDetail;
import com.ms.dao.vo.SampleTrackingVo;

import java.util.List;

public interface SampleTrackingService extends ICommonService<SampleTracking>{

    public PageInfo<SampleTrackingVo> findByParams(SampleTrackingVo sampleTrackingVo,Integer pageNum,Integer pageSize);
    public List<SampleTrackingVo> findAllByParams(SampleTrackingVo sampleTrackingVo);
    public void save(SampleTracking sampleTracking, TrackingDetail trackingDetail);
}
