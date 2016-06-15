package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.dto.Article1Dto;
import com.jointown.zy.common.dto.ArticleDto;
import com.jointown.zy.common.vo.MessageVo;

/**
 * 首页Service
 * @author zhouji
 * 2015-03-18
 */
public interface IndexService {
	/**
	 * 仓单总吨数
	 * @author zhouji
	 * @return
	 */
	public String getWarrantsTunnage();	
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
	public List<Map<Object,Object>> broadcastEveryday();
	
	/**
	 * @Description: 获取各大区仓库现货总量（吨）
	 * @Author: ldp
	 * @Date: 2015年11月5日
	 * @return
	 */
	public Map<String,String> getBigAreaTunage();
	
	/**
	 * @Description: 获取价格指数
	 * @Author: ldp
	 * @Date: 2015年11月6日
	 * @param type 总指数，家中，野生，小品种，香料，疫情    分别对应的type 是  q,j,y,s,x,c
	 * @param k 周K,月K，年K  是w,m,y
	 * @return
	 */
	public MessageVo getPriceIndex(String type,String k);
	
	/**
	 * @Description: TODO
	 * @Author: ldp
	 * @Date: 2015年11月6日
	 * @param type 总指数，家中，野生，小品种，香料，疫情    分别对应的type 是  q,j,y,s,x,c
	 * @return
	 */
	public MessageVo getCompositeIndex(String type);
	
	/**
	 * @Description: 获取近2日药材快讯数据（包括产地快讯和市场快讯）
	 * @Author: ldp
	 * @Date: 2015年11月9日
	 * @return
	 */
	public List<Article1Dto> getHerbalNews();
	
}
