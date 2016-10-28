package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.PickTracking;
import com.ms.dao.vo.PickTrackingVo;

public interface PickTrackingService extends ICommonService<PickTracking>{

    public PageInfo<PickTrackingVo> findByParams(PickTrackingVo pickTrackingVo,Integer pageNum,Integer pageSize);
}
