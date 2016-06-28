/**
 * @author guoyb
 * 2015年3月18日 下午2:23:32
 */
package com.jointown.zy.common.service;

import java.util.HashMap;
import java.util.List;

import com.jointown.zy.common.model.HomePageAd;
import com.jointown.zy.common.model.Page;

/**
 * @author guoyb
 * 2015年3月18日 下午2:23:32
 */
/**
 * @author guoyb
 * 2015年3月18日 下午2:34:04
 */
public interface HomePageAdService {
	
	
	/**
	 * 根据大分类查询首页广告
	 * 2015年3月18日 下午2:31:12
	 * @return List<HomePageAd>
	 */
	public List<HomePageAd> selectHomePageAdByCategory(int category);
	
	/**
	 * 分页查询广告列表
	 * @param map
	 * @return List<HomePageAd>
	 */
	public List<HomePageAd> selectAdByPage(Page<HomePageAd> page);
	
	/**
	 * 一次性查询首页广告列表（首页接口所需）
	 * @return TreeMap<String,List<HomePageAd>>
	 */
	HashMap<String,List<HomePageAd>> selectAdByCategory();
	
	
	/**
	 * 根据id查询首页广告
	 * 2015年3月18日 下午2:31:12
	 * @return HomePageAd
	 */
	public HomePageAd getHomePageAdById(int adid);
	
	/**
	 * 根据查询条件查询首页广告
	 * 2015年3月18日 下午2:31:12
	 * @return List<HomePageAd>
	 */
	public List<HomePageAd> selectHomePageAdByType(HashMap<String, Object> m);
	
	/**
	 * 插入一条广告信息
	 * 2015年3月18日 下午2:32:34
	 * @param homePageAd
	 * @return int
	 */
	public int insertHomePageAd(HomePageAd homePageAd);
	
	/**
	 * 根据id更新一条广告信息
	 * 2015年3月18日 下午2:33:00
	 * @param homePageAd
	 * @return int
	 */
	public int updateHomePageAdByAdid(HomePageAd homePageAd);
	
	
	/**
	 * 把一条广告删除(软删)
	 * 2015年3月18日 下午2:34:07
	 * @param homePageAd
	 * @return int
	 */
	public int abandonHomePageAdByAdid(int adid);
	
	/**
	 * @Description: 根据广告类型和数量，获取首页广告记录
	 * @Author: ldp
	 * @Date: 2015年11月3日
	 * @param type 广告类型 
	 *  			20   大幅banner2.0  
	 *  			21  专题banner2.0
	 *  			22   直销广告2.0
	 * @param num 广告数
	 * @return
	 * 			返回广告实体List
	 */
	public List<HomePageAd> getHomePageAds(String type,String num);
	
	
	/**
	 * @Description: 首页道地药材广告
	 * @Author: Calvin.wh
	 * @Date: 2015-11-17
	 * @return
	 */
	public HashMap<String,List<HomePageAd>> getDaoDiMedicineAd();
	
	/**
	 * @Description: 道地药材类型排序最小值
	 * @Author: Calvin.wh
	 * @Date: 2015-11-17
	 * @return
	 */
	public HomePageAd getMinSortno();
}