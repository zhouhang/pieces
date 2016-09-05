package com.pieces.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Logistical;
import com.pieces.dao.vo.LogisticalVo;

public interface LogisticalService extends ICommonService<Logistical>{

    public PageInfo<LogisticalVo> findByParams(LogisticalVo logisticalVo, Integer pageNum, Integer pageSize);
    
    public PageInfo<LogisticalVo> findByUser(LogisticalVo logisticalVo, Integer pageNum, Integer pageSize);
}
