package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.BusiQuality;

public interface BusiQualityDao {
    
    int deleteByPrimaryKey(Long qualityId);
    
    /**
     * 根据仓单删除质检信息
     * @param wlID
     * @return
     */
    int deleteByWlID(String wlID);

    int insert(BusiQuality record);

    int insertSelective(BusiQuality record);

    BusiQuality selectByPrimaryKey(Long qualityId);

    int updateByPrimaryKeySelective(BusiQuality record);

    int updateByPrimaryKey(BusiQuality record);
}