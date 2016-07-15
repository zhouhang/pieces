package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Commodity;

public interface CommodityDao extends ICommonDao<Commodity>{

    /**
     * 根据参数获查询商品信息
     * @param commodity
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Commodity> findByParam (Commodity commodity, int pageNum, int pageSize);
}
