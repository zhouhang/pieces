/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.service.searchengine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.jointown.zy.common.dto.BusiListingSearchEngineDto;
import com.jointown.zy.common.enums.ListingSearchEngineEnum;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.solr.SolrListingVo;
import com.jointown.zy.common.solr.SolrManager;
import com.jointown.zy.common.solr.SolrSearchParam;
import com.jointown.zy.common.vo.BusiListingSearchVo;

/**
 * @ClassName: SearchStrategy
 * @Description: TODO
 * @Author: robin.liu
 * @Date: 2015年7月29日
 * @Version: 1.0
 */
public abstract class SearchStrategy {
	
	private SolrManager solr;
	
	private SolrSearchParam param;
	
	/**
	 * 
	 * @Description: 获取合适的策略对象
	 * @Author: robin.liu
	 * @Date: 2015年7月29日
	 * @return
	 */
	public static SearchStrategy getSearchStrategy(String searchType){
		if(ListingSearchEngineEnum.SEARCH_MODE_LINK.getValue().equals(searchType)){//仓库搜索模式
			return new LinkSearchStrategy();
		}else if(ListingSearchEngineEnum.SEARCH_MODE_WAREHOUSE.getValue().equals(searchType)){
			return new SingleSearchKeywordsStrategy();
		}else{
			return new CompositeSearchKeywordsStrategy();
		}
	}
	
	/**
	 * 
	 * @Description: 根据不同策略选择不同的搜索模式搜索
	 * @Author: robin.liu
	 * @Date: 2015年7月29日
	 * @param dto
	 * @param page
	 * @return
	 */
	public abstract List<SolrListingVo> search(BusiListingSearchEngineDto dto,Page<BusiListingSearchVo> page);

	public SolrManager getSolr() {
		return solr;
	}

	public SearchStrategy setSolr(SolrManager solr) {
		this.solr = solr;
		return this;
	}


	public SolrSearchParam getParam() {
		return param;
	}


	public SearchStrategy setParam(SolrSearchParam param) {
		this.param = param;
		return this;
	}

}
