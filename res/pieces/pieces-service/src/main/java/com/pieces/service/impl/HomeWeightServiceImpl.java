package com.pieces.service.impl;

import com.pieces.dao.HomeWeightDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.HomeWeight;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.CategoryService;
import com.pieces.service.CommodityService;
import com.pieces.service.HomeWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangbin on 2016/8/8.
 */
@Service
public class HomeWeightServiceImpl extends AbsCommonService<HomeWeight> implements HomeWeightService{

    @Autowired
    private HomeWeightDao homeWeightDao;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private CategoryService categoryService;

    public List<CommodityVo> getHomeCommoditys(String type){
        List<HomeWeight>  homeWeights = findByType(type);
        HomeWeight result = homeWeights.get(0);
        String commodityIds = result!=null?result.getValue():null;
        return commodityService.findVoByIds(commodityIds);
    }


    @Override
    public List<Category> getHomeCategorys(String type) {
        List<HomeWeight>  homeWeights = findByType(type);
        List<Integer> idList = new ArrayList<>();
        for(HomeWeight homeWeight : homeWeights){
            idList.add(Integer.parseInt(homeWeight.getValue()));
        }
        return categoryService.findByIds(idList);
    }

    @Override
    public Map<Integer,List<Category>> getHomeBreeds(String type) {
        Map<Integer,List<Category>> result = new HashMap<>();
        List<HomeWeight>  homeWeights = findByType(type);
        for(HomeWeight homeWeight : homeWeights){
            String  breedIds =  homeWeight.getValue();
            List<Category> breedList =  categoryService.findByIds(breedIds);
            Integer parentId =  homeWeight.getRelevanceId();
            result.put(parentId,breedList);
        }
        return result;
    }

    @Override
    public Map<Integer,List<CommodityVo>> getHomeCategoryCommoditys(String type) {
        Map<Integer,List<CommodityVo>> result = new HashMap<>();
        List<HomeWeight>  homeWeights = findByType(type);
        for(HomeWeight homeWeight : homeWeights){
            String  commodityIds =  homeWeight.getValue();
            List<CommodityVo> breedList =  commodityService.findVoByIds(commodityIds);
            Integer parentId =  homeWeight.getRelevanceId();
            result.put(parentId,breedList);
        }
        return result;
    }


    @Override
    public ICommonDao<HomeWeight> getDao() {
        return this.homeWeightDao;
    }


    private List<HomeWeight> findByType(String type){
        HomeWeight homeWeight = new HomeWeight();
        homeWeight.setType(type);
        List<HomeWeight>  homeWeights = homeWeightDao.findByParams(homeWeight);
        return  homeWeights;
    }
}
