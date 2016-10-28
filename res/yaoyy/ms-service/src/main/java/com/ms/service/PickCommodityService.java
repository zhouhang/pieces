package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.PickCommodity;
import com.ms.dao.vo.PickCommodityVo;

public interface PickCommodityService extends ICommonService<PickCommodity>{

    public PageInfo<PickCommodityVo> findByParams(PickCommodityVo pickCommodityVo,Integer pageNum,Integer pageSize);
}
