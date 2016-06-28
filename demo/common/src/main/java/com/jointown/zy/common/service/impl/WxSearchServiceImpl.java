package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.dao.BusiListingDao;
import com.jointown.zy.common.dao.IndexDao;
import com.jointown.zy.common.dto.BusiListingSearchEngineDto;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.WxSearchService;
import com.jointown.zy.common.service.searchengine.SearchStrategy;
import com.jointown.zy.common.solr.SolrListingVo;
import com.jointown.zy.common.solr.SolrManager;
import com.jointown.zy.common.solr.SolrManager.SOLR_TYPE;
import com.jointown.zy.common.solr.SolrManager.SolrCollection;
import com.jointown.zy.common.solr.SolrSearchParam;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.BusiGoodsSellerVo;
import com.jointown.zy.common.vo.BusiListingSearchVo;

/**
* 项目名称：common   
* 类名称：WxSearchServiceImpl   
* 类描述：WxSearchService接口实现类。微信-小珍现货,无登录状态下的挂牌搜索、排序，查看货主信息等  
* 创建人：宋威   
* 创建时间：2015年7月16日
* @version v1.0   
 */
@Service
public class WxSearchServiceImpl implements WxSearchService	 {
	
	private static final Logger logger = LoggerFactory.getLogger(WxSearchServiceImpl.class);
	
	private SolrManager solr = SolrManager.getInstance(SolrCollection.listing);
	
	@Autowired 
	private IndexDao indexDao;
	
	@Autowired
	private BusiListingDao busiListingDao;

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
		page.setPageSize(ConfigConstant.WX_USER_CENTER_PAGE_SIZE);  //设置一页显示5条记录
		page.setResults(result);
		return result;
	}
	
//	/**
//	 * 高级搜索搜索
//	 */
//	@Override
//	public List<BusiListingSearchVo> seniorSearchByKeyWords(BusiListingSearchEngineDto dto,Page<BusiListingSearchVo> page) {
//		List<BusiListingSearchVo> result = new ArrayList<BusiListingSearchVo>();
//		if(dto!=null){
//			if(StringUtils.isEmpty(dto.getKeyWords())){
//				dto.setKeyWords("*");
//			}
//			SolrSearchParam param = new SolrSearchParam();
//			//填充排序,过滤参数
//			this.fillSort(dto, param.getSortValues())
//				.fillFilter(dto, param.getFilterValues());
//			//匹配品种
//			List<String> solrResults = solr.putParams(param.getQueryValues(),null, "listingKeyWords", dto.getKeyWords())
//					.begin(param.getQueryString(), "listingKeyWords", dto.getKeyWords())
//					.suggest(param, page);
//			for(String s : solrResults){
//				BusiListingSearchVo vo = new BusiListingSearchVo();
//				vo.setSuggestedValue(s);
//				result.add(vo);
//			}
//		}
//		page.setPageSize(ConfigConstant.WX_USER_CENTER_PAGE_SIZE);  //设置一页显示5条记录
//		page.setResults(result);
//		return result;
//	}

	/**
	 * 方法描述：仓单总吨数  换算公式: 除了公斤，克，条外都除以1000换算成吨，
	 * 然后累加并四舍五入求整数。
	 * 
	 */
	@Override
	public String getWarrantsTunnage() {
		return indexDao.getWarrantsTunnage();
	}

	@Override
	public BusiGoodsSellerVo selectGoodsSellerInfo(String listingid) {
		if(listingid==null){
			logger.error("WxSearchServiceImpl.selectGoodsSellerInfo:listingid is null.");
			return new BusiGoodsSellerVo();
		}
		return busiListingDao.selectGoodsSellerInfo(listingid);
	}
	
	//填充排序
	private WxSearchServiceImpl fillSort(BusiListingSearchEngineDto dto,Map<String, String> sortValues){
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
	private WxSearchServiceImpl fillFilter(BusiListingSearchEngineDto dto,Map<String, String[]> filterValues){
		if(!ArrayUtils.isEmpty(dto.getPriceRange())){//价格范围
			solr.putParams(filterValues, null,"price_td", dto.getPriceRange());
		}
		if(!ArrayUtils.isEmpty(dto.getExamineTimeRange())){//挂牌时间范围
			solr.putParams(filterValues,  "Date","examineTime_tdt", dto.getExamineTimeRange());
		}
		if(StringUtils.isNotEmpty(dto.getOrigin())){//产地
			solr.putParams(filterValues,  null,"origin", dto.getOrigin());
		}
		if(StringUtils.isNotEmpty(dto.getWarehouseName())){//所在仓库
			solr.putParams(filterValues, null,"warehouseName", dto.getWarehouseName());
		}
		if(StringUtils.isNotEmpty(dto.getGrade())){//规格等级
				solr.putParams(filterValues, null,"grade", dto.getGrade());
		}
		//过滤挂牌中状态的
		solr.putParams(filterValues,  null,"listingFlag", BusiListingFlagEnum.LISTING.getCode());
		//过滤挂牌中可摘数量为大于0的
		solr.putParams(filterValues,  null,"listingSurplus", new String[]{SOLR_TYPE.OPERATE_GREATER_THAN.getCode(),"0","*"});
		return this;
	}
	
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

	@Override
	public List<Map<String, String>> getCateGorys() {
		List<Map<String, String>> list = indexDao.getCateGorys();
		if(list ==null)
			list = new ArrayList<Map<String, String>>();
		return list;
	}

	@Override
	public String getWlTotalByCateGoryId(String categoryId) {
		return indexDao.getWlTotalByCateGoryId(categoryId);
	}

	@Override
	public String getSearchBreedId(String categoryId) {
		if(categoryId ==null || "".equals(categoryId)){
			return "";
		}
		List<String> breedIds =null;
		try{
			breedIds = indexDao.findBreedIdsByCid(categoryId);
		}catch(NullPointerException e){
			breedIds = new ArrayList<String>();
		}
		StringBuffer buffer = new StringBuffer("^");
		int i=0;
		for(String breedId:breedIds){
			buffer.append(breedId);
			if(i<breedIds.size()-1)
				buffer.append("^");
			i++;
		}
		return (!"^".equals(buffer.toString()) ? buffer.toString():"");
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
		page.setPageSize(ConfigConstant.WX_USER_CENTER_PAGE_SIZE);  //设置一页显示5条记录
		page.setResults(result);
		return result;
	}
}