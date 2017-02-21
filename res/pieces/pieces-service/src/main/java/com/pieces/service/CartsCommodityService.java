package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.CartsCommodity;
import com.pieces.dao.vo.CartsCommodityVo;

public interface CartsCommodityService extends ICommonService<CartsCommodity>{

    public PageInfo<CartsCommodityVo> findByParams(CartsCommodityVo cartsCommodityVo,Integer pageNum,Integer pageSize);
}
