package com.jointown.zy.common.dao;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.dto.Article1Dto;
import com.jointown.zy.common.dto.ArticleDto;


/**
 * 首页Dao
 * @author zhouji
 * 2015-03-18
 */
public interface IndexDao {
	/**
	 * 仓单总吨数
	 * @author zhouji
	 * @return
	 */
	public String getWarrantsTunnage();	

	/**
	 * 微信获取形态分类下的所有类目分类列表
	 * @author 宋威 2015-7-20
	 * @return List<Map<String, String>>
	 */
	public List<Map<String, String>> getCateGorys();
	
	/**
	 * 微信根据类目ID查询该指定类目下的所有在库药材吨数
	 * @author 宋威 2015-7-20
	 * @return String
	 */
	public String getWlTotalByCateGoryId(String categoryId);
	
	/**
	 * 微信根据类目ID查询该指定类目下的所有正常可摘牌的品种id集合
	 * @author 宋威 2015-7-20
	 * @return List<String>
	 */
	public List<String> findBreedIdsByCid(String categoryId);
	/**
	 * 获取cms文章列表
	 * @param categoryId
	 * @param num
	 * @return
	 */
	public List<ArticleDto> getArticleList(String categoryId,String num);
	/**
	 * 获取微信东网文章列表
	 * @param lmid
	 * @param rownum
	 * @return
	 */
	public List<Article1Dto> getWeixinArticleList(String lmid,Integer rownum);
	/**
	 * 获取微信东网市场快讯文章列表
	 * @return
	 */
	public List<Article1Dto> getMKArticleList();
	/**
	 * 查询首页的天天播报，更具仓单中入库的时间倒序查询出仓单的数目(最新的15条)
	 * @return
	 */
	public List<Map<Object, Object>> broadcastEveryday();

	/**
	 * @Description: 获取各大区现货(吨)
	 * @Author: ldp
	 * @Date: 2015年11月5日
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getBigAreaTunnage() throws Exception;
	
	/**
	 * @Description: 获取价格指数数据
	 * @Author: ldp
	 * @Date: 2015年11月5日
	 * @param type 总指数，家中，野生，小品种，香料，疫情    分别对应的type 是  q,j,y,s,x,c
	 * @param k 周K,月K，年K  是w,m,y
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getPriceIndex(String type,String k) throws Exception;
	
	/**
	 * @Description: 获取综合指数
	 * @Author: ldp
	 * @Date: 2015年11月6日
	 * @param type 总指数，家中，野生，小品种，香料，疫情    分别对应的type 是  q,j,y,s,x,c
	 * @param dateNums 今日，近一周，近一月，近半年，近一年  0,7,30,180,365
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getCompositeIndex(String type,String dateNums) throws Exception;
	
	/**
	 * @Description: 获取近2日药材快讯数据（包括产地快讯和市场快讯）
	 * @Author: ldp
	 * @Date: 2015年11月9日
	 * @return
	 * @throws Exception
	 */
	public List<Article1Dto> getHerbalNews() throws Exception;
}
