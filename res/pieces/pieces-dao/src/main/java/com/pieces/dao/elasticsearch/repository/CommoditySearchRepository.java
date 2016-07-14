package com.pieces.dao.elasticsearch.repository;

import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.Commodity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by wangbin on 2016/7/14.
 */

public interface CommoditySearchRepository extends ElasticsearchRepository<CommodityDoc, Integer> {

    Page<CommodityDoc> findByNameLike(String name, Pageable page);



}
