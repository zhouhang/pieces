package com.pieces.service;

import com.pieces.dao.model.Category;
import com.pieces.dao.model.HomeWeight;
import com.pieces.dao.vo.CategoryVo;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.dao.vo.HomeCategoryVo;

import java.util.List;
import java.util.Map;

/**
 * Created by wangbin on 2016/8/8.
 */
public interface HomeWeightService extends ICommonService<HomeWeight>{

    /**
     * 获取首页权重商品
     * @param type
     * @return
     */
    public List<CommodityVo> getHomeCommoditys(String type);



    /**
     * 获取首页权重类别
     * @return
     */
    public List<HomeCategoryVo> getHomeCategorys();



}
