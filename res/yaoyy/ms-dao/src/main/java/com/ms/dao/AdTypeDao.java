package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.AdType;
import com.ms.dao.vo.AdTypeVo;

import java.util.List;
@AutoMapper
public interface AdTypeDao extends ICommonDao<AdType>{

    public List<AdTypeVo> findByParams(AdTypeVo adTypeVo);

}
