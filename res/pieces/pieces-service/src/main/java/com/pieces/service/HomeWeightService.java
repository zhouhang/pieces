package com.pieces.service;

import com.pieces.dao.model.HomeWeight;
import com.pieces.dao.vo.CommodityVo;

import java.util.List;

/**
 * Created by wangbin on 2016/8/8.
 */
public interface HomeWeightService extends ICommonService<HomeWeight>{


    public List<CommodityVo> getHomeCommoditys(String type);

}
