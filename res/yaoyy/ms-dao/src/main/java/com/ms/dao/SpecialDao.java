package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.Special;
import com.ms.dao.vo.SpecialVo;

import java.util.List;
@AutoMapper
public interface SpecialDao extends ICommonDao<Special>{

    public List<SpecialVo> findByParams(SpecialVo specialVo);

}
