package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.HomeWeight;

import java.util.List;

@AutoMapper
public interface HomeWeightDao extends ICommonDao<HomeWeight>{


    public List<HomeWeight> findByParams(HomeWeight homeWeight);


}
