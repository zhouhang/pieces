package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.Pick;
import com.ms.dao.vo.PickVo;

import java.util.List;
@AutoMapper
public interface PickDao extends ICommonDao<Pick>{

    public List<PickVo> findByParams(PickVo pickVo);

    public PickVo findVoById(Integer id);

}
