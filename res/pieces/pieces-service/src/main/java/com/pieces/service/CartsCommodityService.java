package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.CartsCommodity;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.CartsCommodityVo;

import java.util.List;

public interface CartsCommodityService extends ICommonService<CartsCommodity>{

    public PageInfo<CartsCommodityVo> findByParams(CartsCommodityVo cartsCommodityVo,Integer pageNum,Integer pageSize);

    public void save(CartsCommodity cartsCommodity);

    public Integer deleteByVo(CartsCommodityVo cartsCommodityVo);

    /**
     * 合并cookie和数据库数据
     * @param ids
     */
    public void combine(String[] ids ,User user);

    /**
     * 获取用户购物车商品ids
     */
    public List<Integer> getIds(Integer userId);




}
