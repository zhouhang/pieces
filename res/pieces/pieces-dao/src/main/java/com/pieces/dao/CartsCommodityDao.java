package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.CartsCommodity;
import com.pieces.dao.vo.CartsCommodityVo;

import java.util.List;
@AutoMapper
public interface CartsCommodityDao extends ICommonDao<CartsCommodity>{

    public List<CartsCommodityVo> findByParams(CartsCommodityVo cartsCommodityVo);

    public Integer deleteByVo(CartsCommodityVo cartsCommodityVo);

    public void combine(List<CartsCommodityVo> cartsCommodityVos);

    public List<Integer> getIds(Integer userId);

}
