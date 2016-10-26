package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Special;
import com.ms.dao.vo.SpecialVo;

public interface SpecialService extends ICommonService<Special>{

    public PageInfo<SpecialVo> findByParams(SpecialVo specialVo,Integer pageNum,Integer pageSize);
}
