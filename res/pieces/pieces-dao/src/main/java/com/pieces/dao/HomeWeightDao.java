package com.pieces.dao;

import com.pieces.dao.model.HomeWeight;

import java.util.List;

public interface HomeWeightDao extends ICommonDao<HomeWeight>{


    public List<HomeWeight> findByParams(HomeWeight homeWeight);

    public HomeWeight findOneByParams(HomeWeight homeWeight);

}
