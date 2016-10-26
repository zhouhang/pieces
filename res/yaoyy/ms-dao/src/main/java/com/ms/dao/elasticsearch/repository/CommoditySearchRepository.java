package com.ms.dao.elasticsearch.repository;

import com.ms.dao.elasticsearch.document.CommodityDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;




public interface CommoditySearchRepository extends ElasticsearchRepository<CommodityDoc, Integer> {

    Page<CommodityDoc> findByNameOrCategoryName(String name, String categoryName, Pageable page);

    @Query("{\"bool\" : {\"should\" : [{\"term\" : {\"name\" : \"?0\"}}, {\"term\" : {\"categoryName\" : \"?0\"}}]}}}")
    Page<CommodityDoc> cusFindByNameOrCategoryName(String field, Pageable page);


}
