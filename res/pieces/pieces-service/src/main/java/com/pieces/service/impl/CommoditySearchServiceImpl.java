package com.pieces.service.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.elasticsearch.repository.CommoditySearchRepository;
import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.service.CommoditySearchService;
import com.pieces.service.CommodityService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.*;
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
    public CommodityDoc save(Commodity commodity) {
        CommodityVo commodityVO = commodityService.findVoById(commodity.getId());
        CommodityDoc commodityDoc =  vo2doc(commodityVO);
        commoditySearchRepository.save(commodityDoc);
        return commodityDoc;
    }


    @Override
    public void createAllCommodityDoc() {
        List<CommodityDoc> commodityDocList = new ArrayList<>();
        for(int i=1;;i++){
            PageInfo<CommodityVo> commodityVoPageInfo = commodityService.findVoByPage(i,50);
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
        commoditySearchRepository.delete(commodityId);
    }


    public Page<CommodityDoc> findByNameOrCategoryName(Integer pageNum, Integer pageSize, String field){
        SearchQuery searchQuery =null;
        if(StringUtils.isBlank(field)){
            searchQuery = new NativeSearchQueryBuilder()
                            .withQuery(matchAllQuery())
                            .withPageable(new PageRequest(pageNum-1,pageSize))
                            .build();
        }
        else{
            MatchQueryBuilder matchQueryBuilder = null;
            if(field.contains("原药材:")){
                field = field.replace("原药材:","");
                matchQueryBuilder = matchQuery("categoryName", field);
            }else{
                matchQueryBuilder = matchQuery("name", field);
            }
            searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchQueryBuilder)
                    .withPageable(new PageRequest(pageNum-1,pageSize))
                    .build();
        }

        Page<CommodityDoc> result = esTemplate.queryForPage(searchQuery, CommodityDoc.class);
        return result;
    }

    @Override
    public List<Map<String,String>> findByName(String keyword) {
        //查询名称
        SearchQuery nameSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("name",keyword))
                .withPageable(new PageRequest(0,7)).build();
        Page<CommodityDoc> nameResult = esTemplate.queryForPage(nameSearchQuery, CommodityDoc.class);
        Set<String> nameSet = new HashSet<>();
        for(CommodityDoc commodityDoc : nameResult.getContent()){
            nameSet.add(commodityDoc.getName());
        }
        //查询品种
        SearchQuery categorySearchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("categoryName",keyword))
                .withPageable(new PageRequest(0,3)).build();
        Page<CommodityDoc> categoryResult = esTemplate.queryForPage(categorySearchQuery, CommodityDoc.class);
        Set<String> categorySet = new HashSet<>();
        for(CommodityDoc commodityDoc : categoryResult.getContent()){
            categorySet.add(commodityDoc.getCategoryName());
        }

        List<Map<String,String>> result = new ArrayList<>();

        Iterator<String>  nameIt =   nameSet.iterator();
        while (nameIt.hasNext()){
            Map<String,String> map = new HashMap<>();
            map.put("value",nameIt.next());
            map.put("category","");
            result.add(map);
        }
        Iterator<String>  categoryIt =   categorySet.iterator();
        while (categoryIt.hasNext()){
            Map<String,String> map = new HashMap<>();
            map.put("value",categoryIt.next());
            map.put("category","原药材");
            result.add(map);
        }
        return result;
    }


    @Override
    public List<CommodityDoc> findByCommodityName(String commodityName) {
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
        commodityDoc.setFactory(commodityVO.getFactory());
        commodityDoc.setExterior(commodityVO.getExterior());
        commodityDoc.setOriginOf(commodityVO.getOriginOfName());
        commodityDoc.setLevel(commodityVO.getLevelName());
        commodityDoc.setExecutiveStandard(commodityVO.getExecutiveStandard());
        commodityDoc.setPictureUrl(commodityVO.getPictureUrl());
        commodityDoc.setSpec(commodityVO.getSpecName());
        commodityDoc.setCategoryName(commodityVO.getCategoryName());
        return commodityDoc;
    }

}
