package com.jointown.zy.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiGoodsInfoVo;
import com.jointown.zy.common.vo.BusiGoodsRecommenVo;
import com.jointown.zy.common.vo.BusiGoodsSellerVo;
import com.jointown.zy.common.vo.BusiListingVo;

public interface BusiListingDao {
//    /**
//     * 根据主键删除
//     * 参数:主键
//     * 返回:删除个数
//     * @ibatorgenerated 2014-12-27 13:22:22
//     */
//    int deleteByPrimaryKey(String listingid);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    int insertListing(BusiListing record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    int insertSelective(BusiListing record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    BusiListing selectSingleListing(String listingid);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    int updateByPrimaryKey(BusiListing record);
    
    /**
     * 根据属性是否为空选择性的覆盖，修改
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    int updateByIdSelective(BusiListing record);
    
    /**
     * 根据条件查询我的挂牌列表
     * 参数:hashmap
     * 返回:List<ListingVo>
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    List<BusiListingVo> selectListingsByCondition(Page<BusiListingVo> page);
    
    /**
     * 根据条件查询我的挂牌列表（后台查询）
     * @param page
     * @return
     */
    List<BusiListingVo> selectListingsByNotExaminels(Page<BusiListingVo> page);
    
    /**
     * 下架挂牌
     * 参数:String 挂牌id
     * 返回:修改条数
     * @ibatorgenerated 2014-12-31 16:22:21
     */
    int updateListingState(String listingid);
    
    /**
     * 是否推荐
     * @param record
     * @return
     */
    int updateListingRecommend(BusiListing record);
    
    /**
     * 审核状态
     * @param record
     * @return
     */
    int updateListingFlag(BusiListing record);
    
    /**
     * 更新仓单可挂数量
     * @param record
     * @return
     */
    int updateWlsurPlus(BusiListing record);
    
    /**
     * 查询挂牌商品的卖家信息
     * @param listingid 挂牌商品ID
     */
    BusiGoodsSellerVo selectGoodsSellerInfo(String listingid);
    
    /**
     * 查询挂牌商品的信息（包括基本信息、交收信息、质检信息、图片）
     * @param listingid 挂牌商品ID
     */
    BusiGoodsInfoVo selectGoodsInfo(String listingid);
    
    /**
     * 查询平台推荐商品信息
     * @param count 前count条推荐信息
     */
    List<BusiGoodsRecommenVo> selectGoodsRecommenList(Integer count);
    
	/**
	 * 查询挂牌商品是否已经售完
	 */
	int selectGoodsOrderState(String listingid);
	
	/**
	 * 
	 * @Description: 定时器查询审核中的挂牌列表
	 * @Author: wangjunhu
	 * @Date: 2015年4月23日
	 * @return
	 */
    List<BusiListing> selectNotExpiredListings(Integer...beforeDays);
	
	/**
	 * @Description: 订单取消后，挂牌未下架的情况，返回订单数量给挂牌的可摘数量
	 * @Author: 赵航
	 * @Date: 2015年4月22日
	 * @param record：surpluses 订单量；updatetime 更新时间；listingid 挂牌ID
	 * @return
	 */
	int updateListingSurplusByOrderAmount(BusiListing record);
	
	/**
	 * @Description: 后台审核时，更新第一次审核的信息，只有第一次审核时更新
	 * @Author: biran
	 * @Date: 2015-10-08
	 * @param listingid 挂牌ID
	 * @return
	 */
    int updateListingFirstExamineInfo(BusiListing record);

	/**
	 * 根据用户id,挂牌状态或者订单状态查询挂牌剩余量、挂牌被摘牌总量
	 * @Date: 2015年8月4日 
	 * @param map
	 * @return HashMap
	 */
	@SuppressWarnings("rawtypes")
	public HashMap getSurplusesAndVolume(HashMap map);
	
	/**
	 * 微信-我要挂牌-历史挂牌
	 * @param map
	 * @return List<BusiListingVo>
	 */
	@SuppressWarnings("rawtypes")
	public List<BusiListingVo> findHistoryListing(Page<BusiListingVo> page);
	
	/**
	 * 
	 * @Description: 获取指定品种，挂牌中，价格最低的 4个挂牌：标题、散货图、未开发票价格、单位
	 * @Author: fanyuna
	 * @Date: 2015年10月14日
	 * @param conMap 包括 breedId 品种ID，listingflag 挂牌标识，num 记录数，picindex 仓单图片标识 
	 * @return Map中包括 title 挂牌标题，lowunitprice 未开发票价格，path 图片路径，dict_value 单位值
	 */
	public List<Map<String,Object>> selectListingByBreed(Map<String,Object> conMap);
}