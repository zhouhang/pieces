package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.DictInfo;

public interface DictInfoService {
	public    List<DictInfo> getDictList(String dict_type);
	public    List<Map<Object,Object>> getLimitDictList(String dict_type);
	
	/**
	 * 
	 * @Description: 根据字典code获取字典信息，原则上只会返回一条记录
	 * @Author: fanyuna
	 * @Date: 2015年10月22日
	 * @param dictCode
	 * @return
	 */
	public List<DictInfo> selectDictByCode(String dictCode);
}
