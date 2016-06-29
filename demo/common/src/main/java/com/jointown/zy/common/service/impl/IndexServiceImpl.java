package com.jointown.zy.common.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.IndexDao;
import com.jointown.zy.common.dto.Article1Dto;
import com.jointown.zy.common.dto.ArticleDto;
import com.jointown.zy.common.service.IndexService;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.common.vo.PriceIndexVo;

/**
 * 首页ServiceImpl
 * @author zhouji
 * 2015-03-18
 */
@Service
public class IndexServiceImpl implements IndexService {
	
	private static final Logger log = LoggerFactory.getLogger(IndexServiceImpl.class);

	@Autowired 
	private IndexDao indexDao;
	
	@Override
	public String getWarrantsTunnage() {
		return indexDao.getWarrantsTunnage();
	}

	@Override
	public List<ArticleDto> getArticleList(String categoryId,String num) {
		return indexDao.getArticleList(categoryId,num);
	}

	@Override
	public List<Article1Dto> getWeixinArticleList(String lmid,Integer rownum) {
		return indexDao.getWeixinArticleList(lmid,rownum);
	}
	@Override
	public List<Article1Dto> getMKArticleList() {
		return indexDao.getMKArticleList();
	}

	@Override
	public List<Map<Object, Object>> broadcastEveryday() {
		return indexDao.broadcastEveryday();
	}

	@Override
	public Map<String,String> getBigAreaTunage() {
		Map<String, String> bigAreaMap = new HashMap<String, String>();
		try {
			List<Map<String, Object>> bigAreaTunage = indexDao.getBigAreaTunnage();
			if(bigAreaTunage == null || bigAreaTunage.size() == 0){
				return null;
			}
			for(Map<String, Object> map : bigAreaTunage){
				bigAreaMap.put(String.valueOf(map.get("DISTRICT_NAME")), String.valueOf(map.get("BIGTUNNUMS")));
			}
			return bigAreaMap;
		} catch (Exception e) {
			log.error("IndexServiceImpl.getBigAreaTunage error:", e);
			return null;
		}
	}

	@Override
	public MessageVo getPriceIndex(String type, String k) {
		MessageVo mvo = new MessageVo();
		List<String> dateList = new ArrayList<String>();
		List<BigDecimal> priceList = new ArrayList<BigDecimal>();
		if (StringUtils.isBlank(type)) {
			type = "q";//默认总指数
		}
		if (StringUtils.isBlank(k)) {
			k = "w";//默认周K
		}
		try {
			List<Map<String, Object>> listMap = indexDao.getPriceIndex(type, k);
			if(listMap == null || listMap.size() == 0){
				mvo.setOk(false);
				return mvo;
			}
			for(Map<String, Object> map : listMap){
				dateList.add(TimeUtil.formate_YYYYMMDD.format(map.get("DTM")));
				priceList.add(new BigDecimal(String.valueOf(map.get("ZHISHU"))));
			}
			PriceIndexVo piv = new PriceIndexVo();
			piv.setDateStr(dateList);
			piv.setData(priceList);
			mvo.setObj(piv);
			mvo.setOk(true);
			return mvo;
		} catch (Exception e) {
			log.error("IndexServiceImpl.getPriceIndex error:", e);
			mvo.setOk(false);
			return mvo;
		}
	}

	@Override
	public MessageVo getCompositeIndex(String type) {
		MessageVo mvo = new MessageVo();
		PriceIndexVo piv = new PriceIndexVo();
		String dateNums[] = {"0","7","30","180","365"};
		try {
			for (int i = 0; i < dateNums.length; i++) {
				List<Map<String, Object>> listMap = indexDao.getCompositeIndex(type, dateNums[i]);
				if (listMap != null && listMap.size() > 0) {
					if ("0".equals(dateNums[i])) {
						piv.setTodayIndex(String.valueOf(listMap.get(0).get("ZHISHU")));
					}else if ("7".equals(dateNums[i])) {
						piv.setWeekIndex(String.valueOf(listMap.get(0).get("ZHISHU")));
					}else if ("30".equals(dateNums[i])) {
						piv.setMonthIndex(String.valueOf(listMap.get(0).get("ZHISHU")));
					}else if ("180".equals(dateNums[i])) {
						piv.setHalfYearIndex(String.valueOf(listMap.get(0).get("ZHISHU")));
					}else if ("365".equals(dateNums[i])) {
						piv.setYearIndex(String.valueOf(listMap.get(0).get("ZHISHU")));
					}
				}
				listMap.clear();
			}
			mvo.setObj(piv);
			mvo.setOk(true);
			return mvo;
		} catch (Exception e) {
			log.error("IndexServiceImpl.getPriceIndex error:", e);
			mvo.setOk(false);
			return mvo;
		}
	}

	@Override
	public List<Article1Dto> getHerbalNews() {
		try {
			return indexDao.getHerbalNews();
		} catch (Exception e) {
			log.error("IndexServiceImpl.getHerbalNews error:", e);
			return null;
		}
	}
	
}
