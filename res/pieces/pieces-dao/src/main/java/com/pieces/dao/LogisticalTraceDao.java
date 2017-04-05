package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.LogisticalTrace;
import com.pieces.dao.vo.LogisticalTraceVo;

import java.util.List;
@AutoMapper
public interface LogisticalTraceDao extends ICommonDao<LogisticalTrace>{

    public List<LogisticalTraceVo> findByParams(LogisticalTraceVo logisticalTraceVo);

}
