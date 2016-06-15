/**
 * @author guoyb
 * 2015年3月18日 上午11:34:49
 */
package com.jointown.zy.common.dao;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.dto.HomePageListingDto;
import com.jointown.zy.common.model.Page;

/**
 * @author guoyb
 * 2015年3月18日 上午11:34:49
 */
public interface HomePageListingDao {

	/**
	 * 根据type获取首页using数据和预览数据
	 * 2015年3月22日 下午10:18:51
	 * @param status
	 * @return
	 */
	public List<HomePageListingDto> selectHomePageListingsByType(Map<String, String> map);

	/**
	 * 2015年3月19日 下午7:23:36
	 * @param page
	 * @return
	 */
	public List<HomePageListingDto> selectReviewedListings(
			Page<HomePageListingDto> page);

	/**
	 * 2015年3月20日 下午1:59:09
	 * @param homePageListingDto
	 * @return
	 */
	public Integer insertHomePageListing(HomePageListingDto homePageListingDto);

	/**
	 * 2015年3月20日 下午4:11:16
	 * @param homePageListingDto
	 * @return
	 */
	public Integer updateHomePageListing(HomePageListingDto homePageListingDto);

	/**
	 * 2015年3月23日 下午4:27:03
	 * @param homePageListingDto
	 * @return
	 */
	public HomePageListingDto selectHomePageListingsBy(
			HomePageListingDto homePageListingDto);

	/**
	 * 2015年3月24日 上午10:01:38
	 * @param listing_Id
	 * @return
	 */
	public int removeHomePageListingBylisting_Id(HomePageListingDto homePageListingDto);
	
	public List<HomePageListingDto> selectRecomListing(Map<String, Object> map);
	
	public List<HomePageListingDto> selectNewHomePageListingData(Map<String, String> queryParam);
	
	public List<HomePageListingDto> selectNewBargain();
	
	public List<HomePageListingDto> selectBigWarehouse(Map<String,Integer> param);
	
	public List<HomePageListingDto> selectAutoCompleteListing();
	
}
