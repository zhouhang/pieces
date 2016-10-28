package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.PickTracking;
import com.ms.dao.vo.PickTrackingVo;

import java.util.List;
@AutoMapper
public interface PickTrackingDao extends ICommonDao<PickTracking>{

    public List<PickTrackingVo> findByParams(PickTrackingVo pickTrackingVo);

}
