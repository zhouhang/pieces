/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.service.searchengine;

import java.util.List;

import com.jointown.zy.common.dto.BusiListingSearchEngineDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.solr.SolrListingVo;
import com.jointown.zy.common.vo.BusiListingSearchVo;

/**
 * @ClassName: SingleSearchStrategy
 * @Description: TODO
 * @Author: robin.liu
 * @Date: 2015年7月29日
 * @Version: 1.0
 */
public class SingleSearchKeywordsStrategy extends SearchStrategy {

	/* (non-Javadoc)
	 * @see BusiListingSearchServiceImpl.SearchStrategy#search()
	 */
	@Override
	public List<SolrListingVo> search(BusiListingSearchEngineDto dto,Page<BusiListingSearchVo> page) {
		//直接匹配'所在仓库'
		return getSolr().begin(getParam().getQueryString(), getSolr().chineseTypeSearchField("warehouseName") , dto.getKeyWords())
						.search(getParam(), SolrListingVo.class, page);//搜索
	}

}
