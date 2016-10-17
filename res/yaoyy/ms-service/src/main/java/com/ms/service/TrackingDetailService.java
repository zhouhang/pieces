package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.TrackingDetail;
import com.ms.dao.vo.TrackingDetailVo;

public interface TrackingDetailService extends ICommonService<TrackingDetail>{

    public PageInfo<TrackingDetailVo> findByParams(TrackingDetailVo trackingDetailVo,Integer pageNum,Integer pageSize);
}
