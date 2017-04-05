package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.LogisticalTrace;
import com.pieces.dao.vo.LogisticalTraceVo;

import java.util.List;

public interface LogisticalTraceService extends ICommonService<LogisticalTrace>{

    public PageInfo<LogisticalTraceVo> findByParams(LogisticalTraceVo logisticalTraceVo, Integer pageNum, Integer pageSize);

    /**
     * 通过快递公司的code以及快递单号查找快递轨迹
     * @param logisticalTraceVo
     * @return
     */
    public List<LogisticalTraceVo> findByVo(LogisticalTraceVo logisticalTraceVo);
}
