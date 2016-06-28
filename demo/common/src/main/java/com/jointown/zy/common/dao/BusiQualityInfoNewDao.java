package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.BusiQualityInfoNew;

public interface BusiQualityInfoNewDao {

    int deleteByPrimaryKey(Long qualityInfoId);

    int insert(BusiQualityInfoNew record);

    int insertSelective(BusiQualityInfoNew record);

    BusiQualityInfoNew selectByPrimaryKey(Long qualityInfoId);

    int updateByPrimaryKeySelective(BusiQualityInfoNew record);

    int updateByPrimaryKey(BusiQualityInfoNew record);
    
    BusiQualityInfoNew selectQualityByWlId(String wlId);
}