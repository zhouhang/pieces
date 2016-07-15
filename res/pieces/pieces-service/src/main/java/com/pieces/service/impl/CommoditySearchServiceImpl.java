package com.pieces.service.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.elasticsearch.repository.CommoditySearchRepository;
import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVO;
import com.pieces.service.CommoditySearchService;
import com.pieces.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangbin on 2016/7/14.
 */
@Service
public class CommoditySearchServiceImpl implements CommoditySearchService{

    @Autowired
    private CommoditySearchRepository commoditySearchRepository;

    @Autowired
    private CommodityService commodityService;

    @Override
    public CommodityDoc create(Commodity commodity) {
        return null;
    }

    @Override
    public List<CommodityDoc> create(List<Commodity> commodityList) {
        return null;
    }

    @Override
    public Page<CommodityDoc> findByName(String name) {
        return commoditySearchRepository.findByNameLike(name, new PageRequest(0,10));
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


    @Override
    public Page<CommodityDoc> findByAnyField(Integer pageNum, Integer pageSize, String filed) {
        Page<CommodityDoc>  page= commoditySearchRepository.findByField(filed,new PageRequest(pageNum,pageSize));
        return page;
    }

    private CommodityDoc vo2doc(CommodityVO commodityVO){
        CommodityDoc commodityDoc = new CommodityDoc();
        commodityDoc.setId(commodityVO.getId());
        commodityDoc.setName(commodityVO.getName());
        commodityDoc.setFactory(commodityVO.getFactory());
        commodityDoc.setExterior(commodityVO.getExterior());
        commodityDoc.setOriginOf(commodityVO.getOriginOfName());
        commodityDoc.setExecutiveStandard(commodityVO.getExecutiveStandardName());
        commodityDoc.setPictureUrl(commodityVO.getPictureUrl());
        commodityDoc.setSpec(commodityVO.getSpecName());
        return commodityDoc;
    }

}
