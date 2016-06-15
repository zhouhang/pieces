/**
 * @author guoyb
 * 2015年3月18日 下午2:23:43
 */
package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.dao.HomePageAdDao;
import com.jointown.zy.common.enums.HomepageAdEnum;
import com.jointown.zy.common.model.HomePageAd;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.HomePageAdService;

/**
 * @author guoyb
 * 2015年3月18日 下午2:23:43
 */
@Service
public class HomePageAdServiceImpl implements HomePageAdService{

	@Autowired
	private HomePageAdDao homePageAdDao;
	
	private final static Logger logger = LoggerFactory.getLogger(HomePageAdServiceImpl.class);

	public List<HomePageAd> selectHomePageAdByCategory(int category) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("category", category);
		return homePageAdDao.selectHomePageAdBy(map);
	}
	
	public List<HomePageAd> selectAdByPage(Page<HomePageAd> page) {
		return homePageAdDao.selectAdByPage(page);
	}
	
	@SuppressWarnings("rawtypes")
	public HashMap<String,List<HomePageAd>> selectAdByCategory() {
		logger.info("HomePageAdServiceImpl.selectAdByCategory");
		
		/** 封装查询条件 **/
		HashMap<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("category", ConfigConstant.AD_CATEGORY);
		/** 封装查询条件 **/
		
		/** 返回对应的key=广告type  value=type对应的数据 **/
		HashMap<String,List<HomePageAd>> map = new HashMap<String,List<HomePageAd>>();
		try{
			/** 查询所有的 有效 首页广告数据 **/
			List<HomePageAd> list = homePageAdDao.selectHomePageAdByType(queryParam);
			Iterator iter = HomepageAdEnum.toMap().entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				/**获取广告类型 **/
				int adType = Integer.parseInt(entry.getKey().toString());
				List<HomePageAd> hpa = new ArrayList<HomePageAd>();
				for(HomePageAd ad :list){
					/**根据广告类别将数据进行分组**/
					if(ad.getType() == adType){
						hpa.add(ad);
						map.put(String.valueOf(adType),hpa);
					}
				}
			}
		}catch(Exception s){
			logger.info("HomePageAdServiceImpl.selectAdByCategory. error:【{}】",s);
		}
		return map;
	}
	
	/**
	 * @Description: 道地药材广告 去重 取排序号最小的
	 * @Author: Calvin.wh
	 * @Date: 2015-11-17
	 */
	@SuppressWarnings("rawtypes")
	public HashMap<String,List<HomePageAd>> getDaoDiMedicineAd(){
		logger.info("HomePageAdServiceImpl.getDaoDiMedicineAd");
		/** 封装查询条件 **/
		HashMap<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("category", ConfigConstant.AD_CATEGORY);
		/** 封装查询条件 **/
		
		/** 返回对应的key=广告type  value=type对应的数据 **/
		HashMap<String,List<HomePageAd>> map = new HashMap<String,List<HomePageAd>>();
		try{
			/** 查询所有的 有效 首页广告数据 **/
			List<HomePageAd> list = homePageAdDao.selectHomePageAdByType(queryParam);
			Iterator iter = HomepageAdEnum.getDaoDiTypeMap().entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				/**获取广告类型 **/
				int adType = Integer.parseInt(entry.getKey().toString());
				List<HomePageAd> hpa = new ArrayList<HomePageAd>();
				
				for(HomePageAd ad :list){
					if(Integer.valueOf(HomepageAdEnum.HOMEPAGE_AD_BIDS.getType())!=adType){
						if(ad.getType() == adType){
							if(!map.containsKey(String.valueOf(adType))){
								hpa.add(ad);
								map.put(String.valueOf(adType),hpa);
							}
						}
					}else{
						if(ad.getType() == adType){
							hpa.add(ad);
							map.put(String.valueOf(adType),hpa);
						}
					}
				}
			}
		}catch(Exception s){
			logger.info("HomePageAdServiceImpl.getDaoDiMedicineAd. error:【{}】",s);
		}
		return map;
	}
	
	/**
	 * 根据id查询首页广告
	 * 2015年3月18日 下午2:31:12
	 * @return
	 */
	public HomePageAd getHomePageAdById(int adid) {
		HomePageAd ad = homePageAdDao.getHomePageAdById(adid);
		return ad;
	}
	
	public List<HomePageAd> selectHomePageAdByType(HashMap<String, Object> map) {
		return homePageAdDao.selectHomePageAdByType(map);
	}

	/* (non-Javadoc)
	 * @see com.jointown.zy.common.service.HomePageAdService#insertHomePageAd(com.jointown.zy.common.model.HomePageAd)
	 */
	@Override
	public int insertHomePageAd(HomePageAd homePageAd) {
		return homePageAdDao.insertHomePageAd(homePageAd);
	}

	/* (non-Javadoc)
	 * @see com.jointown.zy.common.service.HomePageAdService#updateHomePageAdByAdid(com.jointown.zy.common.model.HomePageAd)
	 */
	@Override
	public int updateHomePageAdByAdid(HomePageAd homePageAd) {
		return homePageAdDao.updateHomePageAdByAdid(homePageAd);
	}

	/* (non-Javadoc)
	 * @see com.jointown.zy.common.service.HomePageAdService#abandonHomePageAdByAdid(com.jointown.zy.common.model.HomePageAd)
	 */
	@Override
	public int abandonHomePageAdByAdid(int adid) {
		HomePageAd homePageAd = getHomePageAdById(adid);
		homePageAd.setStatus((short)1);
		return homePageAdDao.updateHomePageAdByAdid(homePageAd);
	}
	
	@Override
	public List<HomePageAd> getHomePageAds(String type,String num) {
		try {
			return homePageAdDao.selectHomePageAdsByType(type,num);
		} catch (Exception e) {
			logger.error("NewHomePageAdServiceImpl.getHomePageAds error:", e);
			return null;
		}
	}
	
	
	public HomePageAd getMinSortno(){
		try {
			return homePageAdDao.queryMinSortno();
		} catch (Exception e) {
			logger.error("NewHomePageAdServiceImpl.getMinSortno error:", e);
			return new HomePageAd();
		}
	}
	
}
