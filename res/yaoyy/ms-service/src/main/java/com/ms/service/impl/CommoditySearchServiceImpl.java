package com.ms.service.impl;

import com.github.pagehelper.PageInfo;
import com.ms.dao.elasticsearch.document.CommodityDoc;
import com.ms.dao.elasticsearch.repository.CommoditySearchRepository;
import com.ms.dao.model.Commodity;
import com.ms.dao.vo.CommodityVo;
import com.ms.service.CommoditySearchService;
import com.ms.service.CommodityService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;


@Service
public class CommoditySearchServiceImpl implements CommoditySearchService{

    @Autowired
    private CommoditySearchRepository commoditySearchRepository;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    private boolean debug = true;
    @Override
    public CommodityDoc save(Commodity commodity) {
        if (debug) return null;
        CommodityVo commodityVO = commodityService.findById(commodity.getId());
        CommodityDoc commodityDoc =  vo2doc(commodityVO);
        commoditySearchRepository.save(commodityDoc);
        return commodityDoc;
    }


    @Override
    public void createAllCommodityDoc() {
        List<CommodityDoc> commodityDocList = new ArrayList<>();
        for(int i=1;;i++){
            PageInfo<CommodityVo> commodityVoPageInfo = commodityService.findVoByPage(50,i);
            for(CommodityVo commodityVO : commodityVoPageInfo.getList()){
                CommodityDoc commodityDoc = vo2doc(commodityVO);
                commodityDocList.add(commodityDoc);
            }
            if(commodityVoPageInfo.getList().size()<50){
                break;
            }
        }
        commoditySearchRepository.deleteAll();
        commoditySearchRepository.save(commodityDocList);
    }


    @Override
    public void deleteByCommodityId(Integer commodityId) {
        if (debug) return;
        commoditySearchRepository.delete(commodityId);
    }





    @Override
    public List<CommodityDoc> findByCommodityName(String commodityName) {
        if (debug) return null;
        SearchQuery nameSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("name",commodityName))
                .withPageable(new PageRequest(0,10)).build();
        Page<CommodityDoc> commodityDocPage = esTemplate.queryForPage(nameSearchQuery, CommodityDoc.class);
        return commodityDocPage.getContent();
    }


    private CommodityDoc vo2doc(CommodityVo commodityVO){
        CommodityDoc commodityDoc = new CommodityDoc();
        commodityDoc.setId(commodityVO.getId());
        commodityDoc.setName(commodityVO.getName());
        commodityDoc.setTitle(commodityVO.getTitle());
        commodityDoc.setOrigin(commodityVO.getOrigin());
        commodityDoc.setPictureUrl(commodityVO.getPictureUrl());
        commodityDoc.setSpec(commodityVO.getSpec());
        commodityDoc.setCategoryName(commodityVO.getCategoryName());
        return commodityDoc;
    }



}
