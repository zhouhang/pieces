package com.pieces.service.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.elasticsearch.repository.CommoditySearchRepository;
import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVO;
import com.pieces.service.CommoditySearchService;
import com.pieces.service.CommodityService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;


/**
 * Created by wangbin on 2016/7/14.
 */
@Service
public class CommoditySearchServiceImpl implements CommoditySearchService{

    @Autowired
    private CommoditySearchRepository commoditySearchRepository;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    ElasticsearchTemplate esTemplate;

    @Override
    public CommodityDoc create(Commodity commodity) {
        CommodityVO commodityVO = commodityService.findVoById(commodity.getId());
        CommodityDoc commodityDoc =  vo2doc(commodityVO);
        commoditySearchRepository.save(commodityDoc);
        return commodityDoc;
    }




    @Override
    public void createAllCommodityDoc() {
        List<CommodityDoc> commodityDocList = new ArrayList<>();
        for(int i=1;;i++){
            PageInfo<CommodityVO> commodityVoPageInfo = commodityService.findVoByPage(i,50);
            for(CommodityVO commodityVO : commodityVoPageInfo.getList()){
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


    public Page<CommodityDoc> findByNameOrCategoryName(Integer pageNum, Integer pageSize, String field){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(boolQuery().should(matchQuery("name", field)).should(matchQuery("categoryName",field)))
                .withHighlightFields()
                .withPageable(new PageRequest(pageNum-1,pageSize))
                .build();
        Page<CommodityDoc> result = esTemplate.queryForPage(searchQuery, CommodityDoc.class);
        return result;
    }

    private CommodityDoc vo2doc(CommodityVO commodityVO){
        CommodityDoc commodityDoc = new CommodityDoc();
        commodityDoc.setId(commodityVO.getId());
        commodityDoc.setName(commodityVO.getName());
        commodityDoc.setFactory(commodityVO.getFactory());
        commodityDoc.setExterior(commodityVO.getExterior());
        commodityDoc.setOriginOf(commodityVO.getOriginOfName());
        commodityDoc.setExecutiveStandard(commodityVO.getExecutiveStandard());
        commodityDoc.setPictureUrl(commodityVO.getPictureUrl());
        commodityDoc.setSpec(commodityVO.getSpecName());
        commodityDoc.setCategoryName(commodityVO.getCategoryName());
        return commodityDoc;
    }

}
