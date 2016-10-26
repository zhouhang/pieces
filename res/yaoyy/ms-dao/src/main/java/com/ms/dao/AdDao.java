package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.Ad;
import com.ms.dao.vo.AdVo;

import java.util.List;
@AutoMapper
public interface AdDao extends ICommonDao<Ad>{

    public List<AdVo> findByParams(AdVo adVo);

}
