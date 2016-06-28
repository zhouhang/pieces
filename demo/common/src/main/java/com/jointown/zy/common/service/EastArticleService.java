/**
 * @author guoyb
 * 2015年3月3日 上午9:20:42
 */
package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.EastArticleVo;
import com.jointown.zy.common.vo.WxReqBaseMessageVo;

/**
 * 东方中药材网  文章
 * @author guoyb
 * 2015年3月3日 上午9:20:42
 */
public interface EastArticleService {
	
	/**
	 * 行业新闻lmid=11  市场动态lmid=2
	 * @author guoyb
	 * 2015年3月3日 上午9:20:42
	 */
	public String findEastArticles(WxReqBaseMessageVo reqMessage,Integer lmid);
	
	/**
	 * 把行业行文/市场详情的数据封装为微信接口格式的数据
	 * @author guoyb
	 * 2015年3月3日 上午9:21:42
	 */
	public EastArticleVo findOneEastArticle(Integer acid);
	
	
	/**
	 * 分页查询 品种分析和研究报告
	 * @author guoyb
	 * 2015年3月4日 下午2:55:12
	 */
	public List<EastArticleVo> findEastArticleByPage(Page<Map<String, Object>> page);
	
	/**
	 * 查询行情快讯，根据条件
	 * @param page
	 * @return
	 */
	public List<EastArticleVo> findEastArticleNewsByCondition(Page<EastArticleVo> page);
	
	/**
	 * 
	 * @Description: 更新文章点击次数
	 * @Author: wangjunhu
	 * @Date: 2015年7月8日
	 * @param acid
	 * @return
	 */
	void updateEastArticleTipByACid(Integer acid) throws Exception;
}
