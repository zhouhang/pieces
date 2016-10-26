package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.AdType;
import com.ms.dao.vo.AdTypeVo;

public interface AdTypeService extends ICommonService<AdType>{

    public PageInfo<AdTypeVo> findByParams(AdTypeVo adTypeVo,Integer pageNum,Integer pageSize);
}
