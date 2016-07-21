package com.pieces.service;

import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.Commodity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangbin on 2016/7/14.
 */
public interface CommoditySearchService {

    /**
     * 创建或更新商品索引
     * @param commodity
     * @return
     */
    public CommodityDoc save(Commodity commodity);

    /**
     * 搜索名称和类别名
     * @param pageNum
     * @param pageSize
     * @param field
     * @return
     */
    public Page<CommodityDoc> findByNameOrCategoryName(Integer pageNum, Integer pageSize, String field);

    public List<Map<String,String>> findByName(String keyword);


    /**
     * 更新商品库所有商品到索引
     */
    public void createAllCommodityDoc();

    /**
     * 对商品名和品种名搜索
     * @param pageNum
     * @param pageSize
     * @param filed
     * @return
     */
}
