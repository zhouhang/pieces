package com.pieces.service.impl;

import com.pieces.dao.elasticsearch.repository.CommoditySearchRepository;
import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.Commodity;
import com.pieces.service.CommoditySearchService;
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
}
