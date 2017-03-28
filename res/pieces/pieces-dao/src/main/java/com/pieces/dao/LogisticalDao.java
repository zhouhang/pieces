package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.Logistical;
import com.pieces.dao.vo.LogisticalVo;

import java.util.List;
@AutoMapper
public interface LogisticalDao extends ICommonDao<Logistical>{

    List<LogisticalVo> findByParams(LogisticalVo logisticalVo);
    
    List<LogisticalVo> findByUser(LogisticalVo logisticalVo);

    // 根据订单ID 查询物流信息
    LogisticalVo findByOrderId(Logistical logistical);

}
