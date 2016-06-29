package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.DictInfo;

public interface DictInfoDao {
	public    List<DictInfo> getDictList(String dict_type);
	
	public List<DictInfo> selectDictByCode(String dictCode);

	
	/**
	 * 
	 * @Description: 查询字典表最大的ID
	 * @Author: fanyuna
	 * @Date: 2015年11月10日
	 * @return
	 */
	public Long getMaxId();
	
	/**
	 * 
	 * @Description: 插入字典信息
	 * @Author: fanyuna
	 * @Date: 2015年11月10日
	 * @param dictInfo
	 * @return
	 */
	public int insert(DictInfo dictInfo);

	
	/**
	 * 
	 * @Description: 根据字典值获取其对象
	 * @Author: fanyuna
	 * @Date: 2015年10月16日
	 * @param value 如：公斤
	 * @return
	 */
	public DictInfo getDictByValue(String value);
}
