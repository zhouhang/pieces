package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.Logistical;
import com.pieces.dao.vo.LogisticalVo;

import java.util.List;
@AutoMapper
public interface LogisticalDao extends ICommonDao<Logistical>{

    public List<LogisticalVo> findByParams(LogisticalVo logisticalVo);
    
    public List<LogisticalVo> findByUser(LogisticalVo logisticalVo);

}
