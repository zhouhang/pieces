package com.pieces.service.impl;

import com.pieces.dao.HomeWeightDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.HomeWeight;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.CommodityService;
import com.pieces.service.HomeWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangbin on 2016/8/8.
 */
@Service
public class HomeWeightServiceImpl extends AbsCommonService<HomeWeight> implements HomeWeightService{

    @Autowired
    private HomeWeightDao homeWeightDao;

    @Autowired
    private CommodityService commodityService;


    public List<CommodityVo> getHomeCommoditys(String type){
        HomeWeight homeWeight = new HomeWeight();
        homeWeight.setType(type);
        List<HomeWeight>  homeWeights = homeWeightDao.findByParams(homeWeight);
        HomeWeight result = homeWeights.get(0);
        String commodityIds = result!=null?result.getValue():null;
        return commodityService.findVoByIds(commodityIds);
    }




    @Override
    public ICommonDao<HomeWeight> getDao() {
        return this.homeWeightDao;
    }


}
