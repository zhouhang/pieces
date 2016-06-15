package com.jointown.zy.common.service;

import java.util.HashMap;
import java.util.List;

import com.jointown.zy.common.dto.CmsArticleDto;
import com.jointown.zy.common.exception.ErrorException;
import com.jointown.zy.common.vo.CmsArticleSourceVo;

/**
 * 文章Service
 * @author Mr.songwei
 * @date 2014年11月25日下午4:13:44
 */
public interface CmsArticleService {
	/**
	 * 添加文章及详情 
	 * @author Mr.songwei
	 * @param cmsarticledto
	 * @throws ErrorException 
	 */
	public void addArt(CmsArticleDto cmsarticledto) throws ErrorException;
	
	/**
     * 查询所有的文章，以及对应的栏目名称
     * 参数:HashMap<String,String> 包括查询日期，文章标题，关键字
     * 返回:List<CmsArticleSourceVo>
     * @ibatorgenerated 2014-11-26 09:46:32
     */
    List<CmsArticleSourceVo> selectAll(HashMap<String,String> queryString);
}
