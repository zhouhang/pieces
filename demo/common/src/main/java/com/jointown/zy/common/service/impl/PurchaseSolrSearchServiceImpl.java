/**
 * Copyright © 2014-2015 珍药材版权所有
 */
package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.jointown.zy.common.dto.PurchaseSearchDto;
import com.jointown.zy.common.enums.BusiPurchaseStatusEnum;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BaseService;
import com.jointown.zy.common.service.PurchaseSolrSearchService;
import com.jointown.zy.common.solr.SolrManager;
import com.jointown.zy.common.solr.SolrPurchaseVo;
import com.jointown.zy.common.solr.SolrManager.SolrCollection;
import com.jointown.zy.common.solr.SolrSearchParam;

/**
 * @ClassName: PurchaseSolrSearchServiceImpl
 * @Description: 采购信息搜索Service实现类
 * @Author: 赵航
 * @Date: 2015年10月15日
 * @Version: 1.0
 */
@Service
public class PurchaseSolrSearchServiceImpl extends BaseService implements PurchaseSolrSearchService{
	
	private final static Logger logger = LoggerFactory.getLogger(PurchaseSolrSearchServiceImpl.class);
	
	private SolrManager solr = SolrManager.getInstance(SolrCollection.purchase);
	
	//填充排序
	private PurchaseSolrSearchServiceImpl fillSort(PurchaseSearchDto dto,Map<String, String> sortValues){
		if(StringUtils.isNotEmpty(dto.getExpireTimeSort())){//到期时间排序
			sortValues.put("expireTime", dto.getExpireTimeSort());
		}
		//如果没有排序信息，默认按照发布时间倒序排列
		if(StringUtils.isEmpty(dto.getExpireTimeSort())){
			sortValues.put("publishTime", ORDER.desc.name());
		}
		return this;
	}
	
	@Override
	public Page<SolrPurchaseVo> searchSolrPurchasePage(PurchaseSearchDto query)
			throws Exception {
		logger.info("PurchaseSolrSearchServiceImpl.searchSolrPurchasePage");
		try {
			Page<SolrPurchaseVo> page = new Page<SolrPurchaseVo>();
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
			//查询参数
			SolrSearchParam param = new SolrSearchParam();
			StringBuilder sbd = new StringBuilder();
			if(StringUtils.isEmpty(query.getBreedNameMatchType()) || "fuzzy".equals(query.getBreedNameMatchType())){
				if(StringUtils.isEmpty(query.getBreedName())){
					sbd.append("breedName:*");
				}else{
					sbd.append("breedName:" + query.getBreedName());
				}
			} else if("accurate".equals(query.getBreedNameMatchType())){
				if(StringUtils.isEmpty(query.getBreedName())){
					sbd.append("breedName_s:*");
				}else{
					sbd.append("breedName_s:" + query.getBreedName());
				}
			}
			sbd.append(" AND status:(" + BusiPurchaseStatusEnum.OFFER_WAITING.getCode() + " OR " + BusiPurchaseStatusEnum.NEGOTIATING.getCode() + ")");
			param.setQueryString(sbd);//设置查询条件
			fillSort(query, param.getSortValues());//设置排序规则
			List<SolrPurchaseVo> list = solr.search(param, SolrPurchaseVo.class, page);
			page.setResults(list);
			return page;
		} catch (Exception e) {
			logger.error("PurchaseSolrSearchServiceImpl.searchSolrPurchasePage:搜索采购信息失败，错误信息：" + e.getMessage());
			throw setException(e);
		}
	}

	@Override
	public List<String> searchHotPurchaseBreed(Integer maxShowCount) throws Exception {
		logger.info("PurchaseSolrSearchServiceImpl.searchHotPurchaseBreed");
		try {
			List<String> list = new ArrayList<String>();
			StringBuilder query = new StringBuilder();
			query.append("*:*");
//			List<String> filterQueryString = new ArrayList<String>();
//			filterQueryString.add("status:(" + BusiPurchaseStatusEnum.OFFER_WAITING.getCode() + " OR " + BusiPurchaseStatusEnum.NEGOTIATING.getCode() + ")");
//			Map<String, FacetField> facetMap = solr.facetFields(query.toString(), filterQueryString, "breedName_s");
			Map<String, FacetField> facetMap = solr.facetFields(query.toString(), null, "breedName_s");
			FacetField breedName = facetMap.get("breedName_s");
			List<Count> countList = breedName.getValues();
			if(countList != null && countList.size() > 0){
				int size = countList.size();
				if(maxShowCount != null && maxShowCount < size){
					size = maxShowCount;
				}
				for(int i = 0; i < size; i++){
					list.add(countList.get(i).getName());
				}
			}
			return list;
		} catch (Exception e) {
			logger.error("PurchaseSolrSearchServiceImpl.searchHotPurchaseBreed:搜索热门采购品种失败，错误信息：" + e.getMessage());
			throw setException(e);
		}
	}

}
