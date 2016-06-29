package com.jointown.zy.common.service;


import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.CmsSite;

public interface CmsSiteService {
	/**
	 * 添加站点
	 * @param cmsSite
	 */
	public int addCmsSite(CmsSite cmsSite);
	/**
	 * 查询出所有正常的站点
	 * @return List<CmsSite>
	 */
	public List<CmsSite> selectAllCmsSite();
	/**
	 * 查询出按照需求的站点
	 * @return
	 */
	public List<CmsSite> selectDynamicCmsSite(Map queryMap);
	/**
	 * 根据ID查询出CmsSite
	 * @param id
	 * @return
	 */
	public CmsSite selectByPrimaryKey(Long id);
	/**
	 * update操作，用来修改站点的信息
	 * @param cmsSite
	 * @return
	 */
	public int updateByPrimaryKeySelective(CmsSite cmsSite);
	
	/**
	 * 删除操作，修改可用的标记
	 * @param cmsSite
	 * @return
	 */
	public int deleteCmsSiteById(CmsSite cmsSite);
}
