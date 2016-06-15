package com.jointown.zy.common.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.FinanceReportDao;
import com.jointown.zy.common.dto.FinanceReportDto;
import com.jointown.zy.common.model.FinanceReportModel;
import com.jointown.zy.common.model.Organization;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.FinanceReportService;
import com.jointown.zy.common.shiro.CacheManager;

@Service
public class FinanceReportServiceImpl implements FinanceReportService {
	private static final Log logger = LogFactory.getLog(FinanceReportService.class);
	
	@Autowired
	private  FinanceReportDao financeReportDao;
	@Autowired
	private CacheManager cacheManager;
	
	
	public List<FinanceReportModel> getPageList(Page<FinanceReportModel> page){
		return financeReportDao.getPageList(page);
	}
	
	public List<FinanceReportModel> getList(FinanceReportDto dto){
		return financeReportDao.getList(dto);
	}
	
	public List<Organization> getOrgList(){
		return financeReportDao.getOrgList();
	}
	
	/**
	 * 获取组织机构缓存
	 */
	public List<Organization> getOrgListCache(){
		List<Organization> orgList = null;
		try {
			orgList = cacheManager.getCache(RedisEnum.ORGLIST_CACHE.getValue(), RedisEnum.ORGLIST_KEY.getValue());
			if(CollectionUtils.isEmpty(orgList)){
				cacheManager.putCache(RedisEnum.ORGLIST_CACHE.getValue(), RedisEnum.ORGLIST_KEY.getValue(), getOrgList());
				orgList = cacheManager.getCache(RedisEnum.ORGLIST_CACHE.getValue(), RedisEnum.ORGLIST_KEY.getValue());
			}
		} catch (Exception e) {
			logger.error("FinanceReportServiceImpl getOrgList erro : 缓存加载失败" + e);
			orgList = getOrgList();
		}
		return orgList;
	}

}
