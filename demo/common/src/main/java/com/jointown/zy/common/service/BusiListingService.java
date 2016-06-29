package com.jointown.zy.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jointown.zy.common.dto.BusiListingDto;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiListingDetailVo;
import com.jointown.zy.common.vo.BusiListingVo;

/**
 * 仓单挂牌管理Service
 * @author Mr.songwei
 * 2014-12-27
 */
public interface BusiListingService {
	
	/**
	 * 
	 * @Description: 新增挂牌
	 * @Author: wangjunhu
	 * @Date: 2015年4月28日
	 * @param busilistingDto
	 * @throws Exception
	 */
	public void addBusiListing(BusiListingDto busilistingDto) throws Exception;
	
	/**
	 * 
	 * @Description: 修改挂牌
	 * @Author: wangjunhu
	 * @Date: 2015年4月28日
	 * @param busiListingDto
	 * @throws Exception
	 */
	void updateBusiListing(BusiListingDto busiListingDto) throws Exception;
	
	/**
     * 根据条件查询我的挂牌列表
     * 参数:hashmap
     * 返回:List<ListingVo>
     * @ibatorgenerated 2014-12-30 13:22:22
     */
    List<BusiListingVo> findListingsByCondition(Page<BusiListingVo> page);
    
    /**
     * 
     * @Description: 下架挂牌
     * @Author: wangjunhu
     * @Date: 2015年4月24日
     * @param busiListing
     * @throws Exception
     */
    void updateListingFlagDisabled(BusiListing busiListing) throws Exception;
    
    /**
     * 是否推荐
     * @param record
     * @return
     */
    int updateListingRecommend(BusiListing record);
       
    /**
     * 根据条件查询我的挂牌列表（后台查询）
     * @param page
     * @return
     */
    List<BusiListingVo> findListingsByNotExaminels(Page<BusiListingVo> page);
    
    /**
     * 审核状态
     * @param record
     * @return
     */
    void changeListingFlag(BusiListing record) throws Exception;
    
    /**
     * 根据挂牌ID查询单个挂牌详情
     * 参数:挂牌id
     * 返回:BusiListingDetailVo
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    BusiListingDetailVo findSingleListingDetail(String listingid);
    
    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    BusiListing findByPrimaryKey(String listingid);
    
    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    int updateListingDetailByListId(HashMap<String,Object> map);

    /**
     * 定时器查询审核中的挂牌列表
     * 返回:List<BusiListingDetailVo>
     * 参数:beforeDays 快要过期提前的天数
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    public List<BusiListing> selectNotExpiredListings(Integer... beforeDays);
    
	
   /**
	 * 查询挂牌商品是否已经售完
	 * @param listingid
	 * @return
	 */
	int findGoodsOrderState(String listingid);
	
	/**
	 * 微信/我的挂牌/挂牌剩余量、我的挂牌被摘总量 ，单位：吨
	 */
	@SuppressWarnings("rawtypes")
	public HashMap getSurplusesAndVolume(HashMap map);
	
	/**
	 * 微信查询历史挂牌
	 */
	public List<BusiListingVo> findHistoryListing(Page<BusiListingVo> page);
	
	/**
	 * 
	 * @Description: 获取指定品种，挂牌中，价格最低的 4个挂牌：标题、散货图、未开发票价格、单位
	 * @Author: fanyuna
	 * @Date: 2015年10月14日
	 * @param conMap 包括 breedId 品种ID，listingflag 挂牌标识，num 记录数，picindex 仓单图片标识 
	 * @return Map中包括 title 挂牌标题，lowunitprice 未开发票价格，path 图片路径，dict_value 单位值
	 */
	public List<Map<String, Object>> selectListingByBreed(Map<String,Object> conMap);
	
	/**
	 * 
	 * @Description: 调用存储过程，更新历史挂牌
	 * @Author: biran
	 * @Date: 2015年10月14日
	 * @param none
	 * @throws Exception
	 */
	public void updateSalesManInfoByPRO() throws Exception;
	
	
}
