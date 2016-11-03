package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Pick;
import com.ms.dao.vo.PickVo;

public interface PickService extends ICommonService<Pick>{

    public PageInfo<PickVo> findByParams(PickVo pickVo,Integer pageNum,Integer pageSize);

    public PickVo findVoById(Integer id);

    public void save(PickVo pickVo);
}
