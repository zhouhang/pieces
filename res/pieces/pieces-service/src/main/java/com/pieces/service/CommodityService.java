package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVO;

import java.util.List;

/**
 * Created by kevin1 on 7/12/16.
 * 商品
 */
public interface CommodityService extends ICommonService<Commodity>{

    /**
     * 保存或者更新商品信息
     * @param commodity
     */
    public void saveOrUpdate(Commodity commodity);
    /**
     * 根据传入的参数查询商品信息
     * @param commodity
     * @return
     */
    public PageInfo<CommodityVO> query(CommodityVO commodity, int pageNum, int pageSize);

    public PageInfo<CommodityVO> findVoByPage(int pageNum, int pageSize);

    public CommodityVO findVoById(Integer id);

    public CommodityVO findCommodityByBreedId(Integer id);

}
