/**
 * @author guoyb
 * 2015年3月19日 下午3:06:43
 */
package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esotericsoftware.minlog.Log;
import com.jointown.zy.common.dao.HomePageListingDao;
import com.jointown.zy.common.dao.MahoutDao;
import com.jointown.zy.common.dto.HomePageListingDto;
import com.jointown.zy.common.enums.HomePageListingStatusEnum;
import com.jointown.zy.common.enums.HomepageListingEnum;
import com.jointown.zy.common.mahout.MahoutManager;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.HomePageListingService;

/**
 * @author guoyb
 * 2015年3月19日 下午3:06:43
 */
@Service
public class HomePageListingServiceImpl implements HomePageListingService {
	
	@Autowired
	private HomePageListingDao homePageListingDao;
	
	@Autowired
	private MahoutDao mahoutDao;
	
	
	/**
	 * @Description: 获取热门挂牌推荐列表
	 * @Author: 宋威
	 * @Date: 2015年5月11日
	 * @return
	 */
	@Override
	public List<HomePageListingDto> getRecommendList(Long userId){
		Map<String, Object> recMap = new HashMap<String, Object>();
		recMap.put("length",HomepageListingEnum.HOMEPAGE_LISTING_RECOM.getLength());
		//根据用户是否登陆决定是否要用自动推荐功能
		if(userId!=null){
			try {
				List<String> listingIds = MahoutManager.recommendListingByUserId(userId, mahoutDao.getPreferenceData(), true);
				if(CollectionUtils.isNotEmpty(listingIds)){//有推荐，则获取推荐商品ID
					recMap.put("listingIds", listingIds);
				}
			} catch (Exception e) {
				Log.error("HomePageListingServiceImpl.selectHomePageListings error, mathout recommend error,"+e);
			}
		}
		return homePageListingDao.selectRecomListing(recMap);
	}
	
	
	/**
	 * 主页的真正查找的数据
	 * 2015年3月20日 下午5:10:43
	 * @return
	 */
	@Override
	public Map<String, List<HomePageListingDto>> selectHomePageListings(Long userId) {
		Map<String, List<HomePageListingDto>> dtoMap = new HashMap<String, List<HomePageListingDto>>();
		Map<String,String> typeLengthMap = HomepageListingEnum.getTypeLengMap();
		Iterator<String> i =typeLengthMap.keySet().iterator();
		Map<String, String> map = new HashMap<String, String>();
		while (i.hasNext()) {
			String type = i.next();
			map.put("type", type);
			map.put("length", typeLengthMap.get(type));
			dtoMap.put(type, this.homePageListingDao.selectHomePageListingsByType(map));
		}
		//获取热门挂牌推荐列表
		dtoMap.put("recom", getRecommendList(userId));
		return dtoMap;
	}

	/**
	 * 分页获取当前启用数据
	 * 2015年3月19日 下午4:13:07
	 * @param page
	 * @return
	 */
	@Override
	public List<HomePageListingDto> selectReviewedListings(Page<HomePageListingDto> page) {
		return this.homePageListingDao.selectReviewedListings(page);
	}

	@Override
	public Integer insertHomePageListing(
			HomePageListingDto homePageListingDto) {
		return this.homePageListingDao.insertHomePageListing(homePageListingDto);
	}

	@Override
	public Integer updateHomePageListing(
			HomePageListingDto homePageListingDto) {
		homePageListingDto.setStatus(Integer.parseInt(HomePageListingStatusEnum.HOMEPAGE_LISTING_TYPE_USING.getType()));
		return this.homePageListingDao.updateHomePageListing(homePageListingDto);
	}

	@Override
	public HomePageListingDto selectHomePageListingByListingid(String listingid) {
		HomePageListingDto homePageListingDto = new HomePageListingDto();
		homePageListingDto.setListingid(listingid);
		return this.homePageListingDao.selectHomePageListingsBy(homePageListingDto);
	}

	@Override
	public HomePageListingDto selectHomePageListingByListing_Id(Integer listing_Id) {
		HomePageListingDto homePageListingDto = new HomePageListingDto();
		homePageListingDto.setListing_Id(listing_Id);
		return this.homePageListingDao.selectHomePageListingsBy(homePageListingDto);
	}

	@Override
	public int removeHomePageListingBylisting_Id(HomePageListingDto homePageListingDto) {
		return this.homePageListingDao.removeHomePageListingBylisting_Id(homePageListingDto);
	}
	
//////////////////////////////////////////////////////////// 首页2.0 ////////////////////////////////////////////////////////////
	
	/**
	 * 获取首页2.0挂牌数据
	 * @Description: 包含 大户资源数据|道地药材数据
	 * @Author: Calvin.wh
	 * @Date: 2015-11-5
	 * @param userId
	 * @return
	 */
	public Map<String, List<HomePageListingDto>> getHomePageListingData() {
		Map<String, String> queryParam = new HashMap<String, String>();
		Map<String, List<HomePageListingDto>> dtoMap = new HashMap<String, List<HomePageListingDto>>();
		/**
		 * 获取每个type的length
		 * length个数 表示 首页显示某个类别挂牌的个数
		 */
		Map<String,String> typeLengthMap = HomepageListingEnum.getTypeLengMap();
		Iterator<String> i =typeLengthMap.keySet().iterator();
		while (i.hasNext()) {
			String type = i.next();
			queryParam.put("type", type);
			queryParam.put("length", typeLengthMap.get(type));
			List<HomePageListingDto> datas= homePageListingDao.selectNewHomePageListingData(queryParam);
			//参数根据类型分组
			dtoMap.put(type, datas);
		}
		return dtoMap;
	}
	
	/**
	 * @Description: 首页2.0 最新成交挂牌数据
	 * @Author: Calvin.wh
	 * @Date: 2015-11-4
	 * @return
	 */
	public List<HomePageListingDto> getNewBargain(){
		List<HomePageListingDto> newBargainList = homePageListingDao.selectNewBargain();
		return newBargainList;
	}
	
	
	/**
	 * @Description:全国大仓数据读取规则：
	 * （1）、取最新的挂牌数据;（2）、同屏幕品种不得重复;（3）、取挂牌量最大的;（4）、全国6大仓库至少都要有 取他们的交集。
	 * @Author: Calvin.wh
	 * @Date: 2015-11-16
	 * @param listingList 挂牌数据 100 条
	 * @param bigWarehouse 后台录入 全国大仓数据
	 * @param areaList 大区区域 
	 * @param areaCount 0 查询 1统计
	 */
	public List<HomePageListingDto> getBigWarehouse(){
		List<HomePageListingDto> bigWarehouse = new ArrayList<HomePageListingDto>();
		Map<String,Integer> param = new HashMap<String,Integer>();
		try {
			//100条最新挂牌数据[交集]
			List<HomePageListingDto> listingList = homePageListingDao.selectAutoCompleteListing();
			
			//取全国大仓后台录入数据
			param.put("areaCount", 0);
			bigWarehouse = homePageListingDao.selectBigWarehouse(param);
			param.clear();
			
			//取全国大仓类型
			List<HomePageListingDto> areaList = new ArrayList<HomePageListingDto>();
			
			if(CollectionUtils.isNotEmpty(bigWarehouse)){
				//所在仓总数 要包含6大仓库
				param.put("areaCount", 1);
				areaList = homePageListingDao.selectBigWarehouse(param);
				param.clear();
			}
			
			//若全国大仓数据为空 则默认去挂牌数据的第一条
			if (CollectionUtils.isEmpty(bigWarehouse)) {
				for (int i = 0; i < listingList.size();) {
					bigWarehouse.add(listingList.get(i));
					// 添加大仓
					HomePageListingDto one = new HomePageListingDto();
					one.setInWarehouse(listingList.get(i).getInWarehouse());
					areaList.add(one);
					break;
				}
			}
			/**
			 * 填充数据 直至数据补齐18条
			 * 补充不完整原因  : 100条挂牌数据中 ,只取6大区数据 ,而地区重复数据过多 导致无法补齐 ,固要循环多次 直至补齐 
			 */
			for (int bigWarehouseLength = 0 ; bigWarehouseLength  < 18;) {
				if(bigWarehouseLength >= 18){
					return bigWarehouse;
				}else if(bigWarehouseLength < 18){
					bigWarehouse = completeBigWarehouse(bigWarehouse,listingList,areaList);
				}
				/** 使用 全国大仓数据 每次完善之后 ,作为步长 **/
				if(CollectionUtils.isEmpty(bigWarehouse)){
					bigWarehouseLength =  bigWarehouseLength ++ ;
				}else{
					bigWarehouseLength += bigWarehouse.size() ;
				}
			}
			
		} catch (Exception e) {
			Log.error("HomePageListingServiceImpl.getBigWarehouse[全国大仓数据] error:" + e);
		}
		return bigWarehouse;
	}
	
	/**
	 * @Description: 完(补)善(全) 全国大仓数据
	 * @Author: Calvin.wh
	 * @Date: 2015-11-17
	 * @param bigWarehouse
	 * @param listingList
	 * @param areaList
	 * @return
	 * @throws Exception
	 */
	private List<HomePageListingDto> completeBigWarehouse(List<HomePageListingDto> bigWarehouse,
				List<HomePageListingDto> listingList,List<HomePageListingDto> areaList) throws Exception{
			for(int i=0;i<listingList.size();i++){
				if(!compare(bigWarehouse,areaList,listingList.get(i))) continue;
				bigWarehouse.add(listingList.get(i));
				areaList.add(listingList.get(i));
				if(bigWarehouse.size() >= 18) break;
			}
			return bigWarehouse;
	}
	
	/**
	 * @Description: 比较 品种名不重复 , 全国6大区不重复 
	 * @Author: Calvin.wh
	 * @Date: 2015-11-16
	 * @param bigWarehouse
	 * @param areaList
	 * @param listing
	 * @return
	 * @throws Exception
	 */
	private boolean compare(List<HomePageListingDto> bigWarehouse,List<HomePageListingDto> areaList,HomePageListingDto listing) throws Exception{
		if(null!=listing){
			if(StringUtils.isBlank(listing.getName())) return Boolean.FALSE;
			/** 品种名验证重复 **/
			for(HomePageListingDto dto : bigWarehouse){
				if(listing.getName().equals(dto.getName()))return Boolean.FALSE;
			}	
		}
		if(CollectionUtils.isNotEmpty(areaList)){
			/** 若包含全国6大区 则不再验证区域重复 **/
			if(areaList.size()>=6) return Boolean.TRUE;
			if(StringUtils.isBlank(listing.getInWarehouse())) return Boolean.FALSE;
			for(HomePageListingDto dto : areaList){
				if(listing.getInWarehouse().equals(dto.getInWarehouse())) return Boolean.FALSE;;
			}
		}
		return Boolean.TRUE;
	}
}
