package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.BusiQualityItem;

public interface BusiQualityItemDao {
    
    int deleteByPrimaryKey(Long qualityItemId);

    int insert(BusiQualityItem record);

    int insertSelective(BusiQualityItem record);

    BusiQualityItem selectByPrimaryKey(Long qualityItemId);

    int updateByPrimaryKeySelective(BusiQualityItem record);

    int updateByPrimaryKey(BusiQualityItem record);
    /**
     * 
     * @Description: 根据仓单ID删除其相应的质检项信息
     * @Author: fanyuna
     * @Date: 2015年4月16日
     * @param wlId
     * @return
     */
    int delItemByQualityId(String wlId);
    
    List<BusiQualityItem> selectQualityItemByWlId(String wlId);
}