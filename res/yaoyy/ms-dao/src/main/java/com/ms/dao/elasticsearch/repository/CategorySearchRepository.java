package com.ms.dao.elasticsearch.repository;

import com.ms.dao.elasticsearch.document.CategoryDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by xiao on 2016/10/27.
 */
public interface CategorySearchRepository extends ElasticsearchRepository<CategoryDoc, Integer> {

    Page<CategoryDoc> findByVarietyOrParentName(String variety, String parentName, Pageable page);


    @Query("{\"bool\" : {\"should\" : [{\"term\" : {\"variety\" : \"?0\"}}, {\"term\" : {\"parentName\" : \"?0\"}}]}}}")
    Page<CategoryDoc> cusFindByVarietyOrParentName(String field, Pageable page);



}
