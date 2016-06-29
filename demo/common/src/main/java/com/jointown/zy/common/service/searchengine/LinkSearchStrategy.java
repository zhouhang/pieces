/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.service.searchengine;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jointown.zy.common.dto.BusiListingSearchEngineDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.solr.SolrListingVo;
import com.jointown.zy.common.vo.BusiListingSearchVo;

/**
 * @ClassName: LinkSearchStrategy
 * @Description: TODO
 * @Author: robin.liu
 * @Date: 2015年7月29日
 * @Version: 1.0
 */
public class LinkSearchStrategy extends SearchStrategy {

	/* (non-Javadoc)
	 * @see com.jointown.zy.common.service.searchengine.SearchStrategy#search(com.jointown.zy.common.dto.BusiListingSearchEngineDto, com.jointown.zy.common.model.Page)
	 */
	@Override
	public List<SolrListingVo> search(BusiListingSearchEngineDto dto,
			Page<BusiListingSearchVo> page) {
		//首先匹配品种
		if(StringUtils.isNotEmpty(dto.getWarehouseName())){//所在仓库
			getSolr().putParams(getParam().getQueryValues(), null,"warehouseName", dto.getWarehouseName());
		}
		if(StringUtils.isNotEmpty(dto.getGrade())){//规格等级
			getSolr().putParams(getParam().getQueryValues(), null,"grade", dto.getGrade());
		}
		if(StringUtils.isNotEmpty(dto.getOrigin())){//产地
			getSolr().putParams(getParam().getQueryValues(), null,"origin", dto.getOrigin());
		}
		if(StringUtils.isNotEmpty(dto.getBreedId())){//品种ID查询
			getSolr().putParams(getParam().getQueryValues(), null,"breedId", dto.getBreedId());
		}
		return getSolr().and(getParam().getQueryString(), getParam().getQueryValues())
						.search(getParam(), SolrListingVo.class, page);
	}

}
