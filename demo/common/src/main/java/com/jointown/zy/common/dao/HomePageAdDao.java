/**
 * @author guoyb
 * 2015年3月18日 上午11:36:35
 */
package com.jointown.zy.common.dao;

import java.util.HashMap;
import java.util.List;
import com.jointown.zy.common.model.HomePageAd;
import com.jointown.zy.common.model.Page;

/**
 * @author guoyb
 * 2015年3月18日 上午11:36:35
 */
public interface HomePageAdDao {
	
	/**
	 * 查询所有的广告信息，供首页查询接口所需
	 * 2015年3月18日 下午2:41:28
	 * @param map
	 * @return List<HomePageAd>
	 */
	public List<HomePageAd> selectHomePageAdBy(HashMap<String,Object> map);
	
	/**
	 * 根据分页条件查询广告信息
	 * @param page
	 * @return List<HomePageAd>
	 */
	public List<HomePageAd> selectAdByPage(Page<HomePageAd> page);
	
	/**
	 * 根据广告id查询单个广告对象
	 * @param adid
	 * @return HomePageAd
	 */
	public HomePageAd getHomePageAdById(int adid);
	
	/**
	 * 
	 * 2015年3月18日 下午2:41:28
	 * @param map
	 * @return
	 */
	public List<HomePageAd> selectHomePageAdByType(HashMap<String, Object> map);
	
	/**
	 * 2015年3月18日 下午2:41:31
	 * @param homePageAd
	 * @return
	 */
	public int insertHomePageAd(HomePageAd homePageAd);
	
	/**
	 * 2015年3月18日 下午2:41:33
	 * @param homePageAd
	 * @return
	 */
	public int updateHomePageAdByAdid(HomePageAd homePageAd);
	
	/**
	 * @Description: 根据广告类型查询广告
	 * @Author: ldp
	 * @Date: 2015年11月3日
	 * @param type
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public List<HomePageAd> selectHomePageAdsByType(String type,String num) throws Exception;
	
	
	/**
	 * @Description: 取道地药材中 类型排序号最小的
	 * @Author: Calvin.wh
	 * @Date: 2015-11-17
	 * @return
	 * @throws Exception
	 */
	public HomePageAd queryMinSortno() throws Exception;
}
