package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.FacetField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dto.BusiListingSearchEngineDto;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.ListingSearchEngineEnum;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BusiListingSearchService;
import com.jointown.zy.common.service.searchengine.SearchStrategy;
import com.jointown.zy.common.solr.SolrListingVo;
import com.jointown.zy.common.solr.SolrManager;
import com.jointown.zy.common.solr.SolrManager.SOLR_TYPE;
import com.jointown.zy.common.solr.SolrManager.SolrCollection;
import com.jointown.zy.common.solr.SolrSearchParam;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.BusiListingSearchVo;

@SuppressWarnings("javadoc")
@Service
public class BusiListingSearchServiceImpl implements BusiListingSearchService {
	
	private final static Logger logger = LoggerFactory.getLogger(BusiListingSearchServiceImpl.class);
	
	private SolrManager solr = SolrManager.getInstance(SolrCollection.listing);
	
	private List<BusiListingSearchVo> copy(List<SolrListingVo> source,List<BusiListingSearchVo> dest){
		if(CollectionUtils.isNotEmpty(source)){
			for(SolrListingVo sr:source){
				BusiListingSearchVo bean = new BusiListingSearchVo();
				BeanUtils.copyProperties(sr, bean);
				bean.setCreateTimeString(sr.getCreateTime()==null?"":TimeUtil.getYMDHMS(sr.getCreateTime()));
				bean.setExamineTimeString(sr.getExamineTime()==null?"":TimeUtil.getYMDHMS(sr.getExamineTime()));
				dest.add(bean);
			}
		}
		return dest;
	}
	
	//填充排序
	private BusiListingSearchServiceImpl fillSort(BusiListingSearchEngineDto dto,Map<String, String> sortValues){
		if(StringUtils.isNotEmpty(dto.getSortListingSurplus())){//库存排序
			sortValues.put("listingSurplus", dto.getSortListingSurplus());
		}
		if(StringUtils.isNotEmpty(dto.getSortPrice())){//价格排序
			sortValues.put("price_td", dto.getSortPrice());
		}
		if(StringUtils.isNotEmpty(dto.getSortExamineTime())){//挂牌日期排序
			sortValues.put("examineTime_tdt", dto.getSortExamineTime());
		}
		//如果没有排序信息，默认按照挂牌日期倒序排列
		if(StringUtils.isEmpty(dto.getSortListingSurplus())
				&& StringUtils.isEmpty(dto.getSortPrice())
				&& StringUtils.isEmpty(dto.getSortExamineTime())){
			sortValues.put("examineTime_tdt", ORDER.desc.name());
		}
		return this;
	}
	
	//填充过滤
	private BusiListingSearchServiceImpl fillFilter(BusiListingSearchEngineDto dto,Map<String, String[]> filterValues){
		if(!ArrayUtils.isEmpty(dto.getPriceRange())){//价格范围
			solr.putParams(filterValues, null,"price_td", dto.getPriceRange());
		}
		if(!ArrayUtils.isEmpty(dto.getExamineTimeRange())){//挂牌时间范围
			solr.putParams(filterValues,  "Date","examineTime_tdt", dto.getExamineTimeRange());
		}
		if(StringUtils.isNotEmpty(dto.getOriginText())){//产地
			solr.putParams(filterValues,  null,"origin", dto.getOriginText());
		}
		//过滤挂牌中状态的
		solr.putParams(filterValues,  null,"listingFlag", BusiListingFlagEnum.LISTING.getCode());
		//过滤挂牌中可摘数量为大于0的
		solr.putParams(filterValues,  null,"listingSurplus", new String[]{SOLR_TYPE.OPERATE_GREATER_THAN.getCode(),"0","*"});
		return this;
	}
	
	/**
	 * 文本框关键字搜索
	 */
	@Override
	public List<BusiListingSearchVo> searchByKeyWords(BusiListingSearchEngineDto dto,Page<BusiListingSearchVo> page) {
		List<BusiListingSearchVo> result = new ArrayList<BusiListingSearchVo>();
		if(dto!=null){
			if(StringUtils.isEmpty(dto.getKeyWords())){
				dto.setKeyWords("*");
			}
			SolrSearchParam param = new SolrSearchParam();
			//填充排序,过滤参数
			this.fillSort(dto, param.getSortValues())
				.fillFilter(dto, param.getFilterValues());
			//按照模式类型进行搜索
			copy(SearchStrategy.getSearchStrategy(dto.getMode())
					.setSolr(solr)
					.setParam(param)
					.search(dto, page), result);
		}
		page.setResults(result);
		return result;
	}
	
	@Override
	public List<BusiListingSearchVo> suggestByKeyWords(BusiListingSearchEngineDto dto,
			Page<BusiListingSearchVo> page) {
		List<BusiListingSearchVo> result = new ArrayList<BusiListingSearchVo>();
		if(dto!=null){
			if(StringUtils.isEmpty(dto.getKeyWords())){
				dto.setKeyWords("*");
			}
			SolrSearchParam param = new SolrSearchParam();
			//填充排序,过滤参数
			this.fillSort(dto, param.getSortValues())
				.fillFilter(dto, param.getFilterValues());
			//匹配品种
			List<String> solrResults = solr.putParams(param.getQueryValues(),null, "listingKeyWords", dto.getKeyWords())
					.begin(param.getQueryString(), "listingKeyWords", dto.getKeyWords())
					.suggest(param, page);
			for(String s : solrResults){
				BusiListingSearchVo vo = new BusiListingSearchVo();
				vo.setSuggestedValue(s);
				result.add(vo);
			}
		}
		page.setResults(result);
		return result;
	}

	/**
	 * @Description: 根据选择类目下的品种，点击品种搜索对应的品种所有有效挂牌列表
	 * @Author: 宋威
	 * @Date: 2015年6月2日
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<BusiListingSearchVo> searchBreedsBySolr(BusiListingSearchEngineDto dto,Page<BusiListingSearchVo> page) {
		List<BusiListingSearchVo> result = new ArrayList<BusiListingSearchVo>();
		if(dto!=null){
			SolrSearchParam param = new SolrSearchParam();
			//填充排序,过滤参数
			this.fillSort(dto, param.getSortValues())
				.fillFilter(dto, param.getFilterValues());
			//按照模式类型进行搜索
			copy(SearchStrategy.getSearchStrategy(dto.getMode())
					.setSolr(solr)
					.setParam(param)
					.search(dto, page), result);
		}
		page.setResults(result);
		return result;
	}
	
	/**
	 * @Description: 根据所有类目品种列表集，按照A-F,G-L,M-R,S-Z等顺序封装集合，另外查询该目录下所有拥有正常挂牌的品种ID
	 * @Author: 宋威
	 * @Date: 2015年6月2日
	 * @param list 所有目录品种集合
	 * @param id   指定目录ID
	 * @param breedIds 该目录下所有拥有正常挂牌的品种ID集合
	 * @return Map <String ,Object> 返回 A-F,G-L,M-R,S-Z 品种导航
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map <String ,Object> getBreedsByCategoryId(List <Map<Object,Object>> list,String id,List<String> breedIds){
		Map <String ,Object> resultMap = new HashMap<String, Object>();
		if(list==null || list.size()==0 || StringUtils.isEmpty(id))
			return resultMap;
		for(Map m :list){
			if(m.get("categorys_id").toString().equals(id)){
				//根据
				List<Object> firstListBreed = (ArrayList<Object>)m.get("firstListBreed");
				resultMap.put("firstListBreed",firstListBreed);
				List<Object> secondListBreed = (ArrayList<Object>)m.get("secondListBreed");
				resultMap.put("secondListBreed",secondListBreed);
				List<Object> thirdListBreed = (ArrayList<Object>)m.get("thirdListBreed");
				resultMap.put("thirdListBreed",thirdListBreed);
				List<Object> fourthListBreed = (ArrayList<Object>)m.get("fourthListBreed");
				resultMap.put("fourthListBreed",fourthListBreed);
				//first
				for(Object first:firstListBreed){
					Map m_first = (HashMap)first;
					if("1".equals(String.valueOf(m_first.get("FLAG")))){
						breedIds.add(String.valueOf(m_first.get("BREED_ID")));
					}
				}
				//second
				for(Object second:secondListBreed){
					Map m_second = (HashMap)second;
					if("1".equals(String.valueOf(m_second.get("FLAG")))){
						breedIds.add(String.valueOf(m_second.get("BREED_ID")));
					}
				}
				//third
				for(Object third:thirdListBreed){
					Map m_third = (HashMap)third;
					if("1".equals(String.valueOf(m_third.get("FLAG")))){
						breedIds.add(String.valueOf(m_third.get("BREED_ID")));
					}
				}
				//fourth
				for(Object fourth:fourthListBreed){
					Map m_fourth = (HashMap)fourth;
					if("1".equals(String.valueOf(m_fourth.get("FLAG")))){
						breedIds.add(String.valueOf(m_fourth.get("BREED_ID")));
					}
				}
				break;
			}
		}
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getBreedSearchQuery(String breedId) {
		Map<String, Object> resultMap = null;
		if(StringUtils.isNotEmpty(breedId)){
			StringBuilder query = new StringBuilder();
			Map<String, String[]> queryValues = new HashMap<String, String[]>();
			solr.putParams(queryValues, null, "breedId", breedId);
			solr.putParams(queryValues, null, "listingFlag", BusiListingFlagEnum.LISTING.getCode());
			solr.and(query, queryValues);
			try {
				resultMap = new HashMap<String, Object>();
				Map<String, FacetField> facetMap = solr.facetFields(query.toString(), null, "grade", "warehouseName", "origin");
				FacetField grade = facetMap.get("grade");
				FacetField warehouseName = facetMap.get("warehouseName");
				FacetField origin = facetMap.get("origin");
				resultMap.put("breedStandLevelList", grade != null ? grade.getValues() : null);
				resultMap.put("listAllWarehouse", warehouseName != null ? warehouseName.getValues() : null);
				resultMap.put("listBreedPlace", origin != null ? origin.getValues() : null);
			} catch (Exception e) {
				logger.error("BusiListingSearchServiceImpl.getBreedSearchQuery error is " + e.getMessage());
				return null;
			}
		}
		return resultMap;
	}
		
}
