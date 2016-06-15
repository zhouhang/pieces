package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.CmsCategory;



public interface CmsCategoryService {
	/**
	 * 通过站点的id查询出站点下面的目录
	 * @return
	 */
	public List<CmsCategory> getAllCmsCategoryByCmsSiteId(Long cmsSiteID);
}
