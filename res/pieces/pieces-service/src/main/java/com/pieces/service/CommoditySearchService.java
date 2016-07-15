package com.pieces.service;

import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.Commodity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by wangbin on 2016/7/14.
 */
public interface CommoditySearchService {

    public CommodityDoc create(Commodity commodity);

    public List<CommodityDoc> create(List<Commodity> commodityList);

    public Page<CommodityDoc> findByName(String name);

    /**
     * 查询所有商品创建document
     */
    public void createAllCommodityDoc();

    /**
     * 对所有commodityDoc字段进行全文检索
     * @param pageNum
     * @param pageSize
     * @param filed
     * @return
     */
    public Page<CommodityDoc> findByAnyField(Integer pageNum,Integer pageSize,String filed);
}
