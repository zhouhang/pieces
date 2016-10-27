package com.ms.service;

import com.ms.dao.elasticsearch.document.CommodityDoc;
import com.ms.dao.model.Commodity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CommoditySearchService {

    /**
     * 创建或更新商品索引
     * @param commodity
     * @return
     */
    public CommodityDoc save(Commodity commodity);


    /**
     * 搜索索引名称
     * @param commodityName
     * @return
     */
    public List<CommodityDoc> findByCommodityName(String commodityName);

    /**
     * 更新商品库所有商品到索引
     */
    public void createAllCommodityDoc();


    public void  deleteByCommodityId(Integer commodityId);

}
