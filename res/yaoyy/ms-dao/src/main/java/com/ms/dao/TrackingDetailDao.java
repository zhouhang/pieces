package com.ms.dao;

import com.ms.dao.model.TrackingDetail;
import com.ms.dao.vo.TrackingDetailVo;
import com.ms.dao.config.AutoMapper;

import java.util.List;
@AutoMapper
public interface TrackingDetailDao extends ICommonDao<TrackingDetail>{

    public List<TrackingDetailVo> findByParams(TrackingDetailVo trackingDetailVo);

}
