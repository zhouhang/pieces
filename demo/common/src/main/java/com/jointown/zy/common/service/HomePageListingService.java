/**
 * @author guoyb
 * 2015年3月19日 下午3:06:29
 */
package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.dto.HomePageListingDto;
import com.jointown.zy.common.model.BusiPurchase;
import com.jointown.zy.common.model.Page;

/**
 * @author guoyb
 * 2015年3月19日 下午3:06:30
 */
public interface HomePageListingService {
	
	/**
	 * @Description: 获取挂牌推荐列表
	 * @Author: 宋威
	 * @Date: 2015年5月11日
	 * @return
	 */
	List<HomePageListingDto> getRecommendList(Long userId);
	
	/**
	 * 主页的真正查找的数据
	 * 2015年3月20日 下午5:10:43
	 * @return
	 */
	Map<String, List<HomePageListingDto>> selectHomePageListings(Long userId);
	
	

	/**
	 * 获取所有的已经审核当前正在挂牌的数据
	 * 2015年3月19日 下午4:13:07
	 * @param page
	 * @return
	 */
	List<HomePageListingDto> selectReviewedListings(Page<HomePageListingDto> page);
	
	/**
	 * 插入数据
	 * 2015年3月23日 下午3:42:47
	 * @param homePageListingDto
	 * @return
	 */
	Integer insertHomePageListing(HomePageListingDto homePageListingDto);
	
	/**
	 * 更新数据
	 * 2015年3月23日 下午3:42:47
	 * @param homePageListingDto
	 * @return
	 */
	Integer updateHomePageListing(HomePageListingDto homePageListingDto);

	/**
	 * 2015年3月23日 下午4:20:21
	 * @param listingid
	 * @return
	 */
	HomePageListingDto selectHomePageListingByListingid(String listingid);

	/**
	 * 2015年3月23日 下午5:17:01
	 * @param listing_Id
	 * @return
	 */
	HomePageListingDto selectHomePageListingByListing_Id(Integer listing_Id);

	/**
	 * 2015年3月24日 上午9:59:54
	 * @param homePageListingDto 
	 * @param string
	 * @return
	 */
	int removeHomePageListingBylisting_Id(HomePageListingDto homePageListingDto);
	
//////////////////////////////////////////////////////////// 首页2.0 ////////////////////////////////////////////////////////////
	
	public List<HomePageListingDto> getNewBargain();
	
	public Map<String, List<HomePageListingDto>> getHomePageListingData();
	
	public List<HomePageListingDto> getBigWarehouse();

}
