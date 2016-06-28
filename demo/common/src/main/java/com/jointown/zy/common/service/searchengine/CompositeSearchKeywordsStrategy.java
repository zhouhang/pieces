/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.service.searchengine;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.jointown.zy.common.dto.BusiListingSearchEngineDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.solr.SolrListingVo;
import com.jointown.zy.common.vo.BusiListingSearchVo;

/**
 * @ClassName: CompositeSearchKeywordsStrategy
 * @Description: TODO
 * @Author: robin.liu
 * @Date: 2015年7月29日
 * @Version: 1.0
 */
public class CompositeSearchKeywordsStrategy extends SearchStrategy{

	/* (non-Javadoc)
	 * @see BusiListingSearchServiceImpl.SearchStrategy#search(com.jointown.zy.common.solr.SolrSearchParam, com.jointown.zy.common.dto.BusiListingSearchEngineDto, com.jointown.zy.common.model.Page)
	 */
	@Override
	public List<SolrListingVo> search(BusiListingSearchEngineDto dto, Page<BusiListingSearchVo> page) {
		List<SolrListingVo> result = null;
		//首先匹配品种
		result = getSolr().putParams(getParam().getQueryValues(),null, "breedName", dto.getKeyWords())
			.putParams(getParam().getQueryValues(),null, "breedName_synonym", dto.getKeyWords())
			.or(getParam().getQueryString(), getParam().getQueryValues())
			.search(getParam(), SolrListingVo.class, page);
		if(CollectionUtils.isNotEmpty(result)){
			return result;
		}
		clearQuery();
		//其次匹配 '挂牌标题' 或者 '所在仓库' 或者 '产地'
		return getSolr().putParams(getParam().getQueryValues(), null,"title", dto.getKeyWords())
			.putParams(getParam().getQueryValues(), null,getSolr().chineseTypeSearchField("warehouseName"), dto.getKeyWords())
			.putParams(getParam().getQueryValues(), null,getSolr().chineseTypeSearchField("origin"), dto.getKeyWords())
			.or(getParam().getQueryString(), getParam().getQueryValues())
			.search(getParam(), SolrListingVo.class, page);
	}
	
	private void clearQuery(){
		getParam().getQueryValues().clear();
		getParam().getQueryString().delete(0, getParam().getQueryString().length());
	}

}
