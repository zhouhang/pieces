package com.jointown.zy.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.BusiListingDetail;
import com.jointown.zy.common.vo.BusiListingDetailVo;

public interface BusiListingDetailDao {
    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-27 13:22:31
     */
    int insertListingDetail(BusiListingDetail record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-27 13:22:31
     */
    int insertSelective(BusiListingDetail record);
    
    /**
     * 根据主键查询
     * 参数:查询条件,挂牌ID
     * 返回:对象
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    BusiListingDetail selectByListId(String listingid);
    
    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    int updateByListId(HashMap<String,Object> map);
    /**
     * 根据挂牌ID查询单个挂牌详情
     * 参数:挂牌id
     * 返回:BusiListingDetailVo
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    BusiListingDetailVo selectSingleListingDetail(String listingid);

    /**
     * 通过wlid查询出卖家信息
     * @param wlid
     * @return
     */
    public Map<String,Object> getSellerInfo(String wlid);
    
}