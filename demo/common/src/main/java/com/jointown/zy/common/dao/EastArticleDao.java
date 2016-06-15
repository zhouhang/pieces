/**
e * @author guoyb
 * 2015年3月3日 上午9:31:48
 */
package com.jointown.zy.common.dao;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.EastArticleVo;

/**
 * @author guoyb
 * 2015年3月3日 上午9:31:48
 */
public interface EastArticleDao {

	/**
	 * 根据栏目 查询 文章列表----新闻类(行业新闻、市场动态使用，固定查询前6条)
	 * @author guoyb
	 * 2015年3月3日 上午9:31:48
	 */
	List<EastArticleVo> selectEastArticleByLMId(Integer lmid);
	
	/**
	 * 根据ID查询文章详情
	 * @author guoyb
	 * 2015年3月3日 上午9:31:48
	 */
	EastArticleVo selectEastArticleByACid(Integer acid);
	
	/**
	 * 根据栏目 分页 查询 文章列表----报告分析类(品种分析和研究报告使用，分页每次10条记录)
	 * @author guoyb
	 * 2015年3月4日 下午2:24:58
	 */
	List<EastArticleVo> selectEastArticlePagedByLMId(Page<Map<String, Object>> page);
	
	/**
	 * 查询行情快讯，根据品种名称
	 * @param page
	 * @return
	 */
	List<EastArticleVo> selectEastArticleNewsByCondition(Page<EastArticleVo> page);
	
	/**
	 * 
	 * @Description: 更新文章点击次数
	 * @Author: wangjunhu
	 * @Date: 2015年7月8日
	 * @param acid
	 * @return
	 */
	int updateEastArticleTipByACid(Integer acid);
}
