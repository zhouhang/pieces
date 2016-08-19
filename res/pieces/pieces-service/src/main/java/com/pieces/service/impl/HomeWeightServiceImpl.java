package com.pieces.service.impl;

import com.pieces.dao.HomeWeightDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.Ad;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.HomeWeight;
import com.pieces.dao.vo.AdVo;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.dao.vo.HomeCategoryVo;
import com.pieces.service.*;
import com.pieces.service.enums.CodeEnum;
import com.pieces.service.enums.WeightEnum;
import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private AdService adService;

    public List<CommodityVo> getHomeCommoditys(String type){
        List<HomeWeight>  homeWeights = findByType(type);
        HomeWeight result = homeWeights.get(0);
        String commodityIds = result!=null?result.getValue():null;
        return commodityService.findVoByIds(commodityIds);
    }


    public List<HomeCategoryVo> getHomeCategorys(){
        List<HomeWeight>  homeWeights = findByType(WeightEnum.CATEGORY.name());
        //橱窗广告
        List<AdVo> adList = adService.findByType(CodeEnum.AD_SHOWCASE.getId());
        //首页分类橱窗
        List<HomeCategoryVo> list  = new ArrayList<>();
        for(HomeWeight homeWeight : homeWeights){
            HomeCategoryVo homeCategoryVo = new HomeCategoryVo();
            homeCategoryVo.setTitle(homeWeight.getName());
            homeCategoryVo.setPictureUrl(homeWeight.getPictureUrl());

            //获取橱窗广告
            EqualPredicate eqlPredicate = new EqualPredicate(homeWeight.getName());
            Collection showcaseCollection = CollectionUtils.select(adList,new BeanPredicate("title",eqlPredicate));
            if(!showcaseCollection.isEmpty()){
                homeCategoryVo.setShowcase((Ad)showcaseCollection.iterator().next());
            }

            Integer categoryId =  homeWeight.getId();
            HomeWeight breedHomeWeight =  findByTypeAndRelevance(WeightEnum.BREED.name(),categoryId);
            HomeWeight cateCommodityHomeWeight =  findByTypeAndRelevance(WeightEnum.CATE_COMMODITY.name(),categoryId);
            //分类下的品种
            if(breedHomeWeight!=null){
                List<Category> breedList =  categoryService.findByIds(breedHomeWeight.getValue());
                homeCategoryVo.setBreedList(breedList);
            }
            //分类下的商品
            if(cateCommodityHomeWeight!=null){
                List<CommodityVo> commodityList =  commodityService.findVoByIds(cateCommodityHomeWeight.getValue());
                homeCategoryVo.setCommodityList(commodityList);
            }

            list.add(homeCategoryVo);
        }

        return list;
    }




    /**
     * 根据类型查询
     * @param type
     * @return
     */
    private List<HomeWeight> findByType(String type){
        HomeWeight homeWeight = new HomeWeight();
        homeWeight.setType(type);
        List<HomeWeight>  homeWeights = homeWeightDao.findByParams(homeWeight);
        return  homeWeights;
    }

    /**
     * 根据类型和关联查询
     * @param type
     * @param relevanceId
     * @return
     */
    private HomeWeight findByTypeAndRelevance(String type,Integer relevanceId){
        HomeWeight homeWeight = new HomeWeight();
        homeWeight.setType(type);
        homeWeight.setRelevanceId(relevanceId);
        return homeWeightDao.findOneByParams(homeWeight);
    }


    @Override
    public ICommonDao<HomeWeight> getDao() {
        return this.homeWeightDao;
    }
}
