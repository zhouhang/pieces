package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.DictInfoDao;
import com.jointown.zy.common.model.DictInfo;
import com.jointown.zy.common.service.DictInfoService;
@Service
public class DictInfoServiceImpl implements DictInfoService {
	@Autowired
	private DictInfoDao dictInfoDao;
	/**
	 * @description 输入dict_type查询出对应的值
	 */
	@Override
	public  List<DictInfo> getDictList(String dict_type) {
		return dictInfoDao.getDictList(dict_type);
	}
	/**
	 * @description 传入list后将list转化为符合要求的格式
	 */
	@Override
	public  List <Map<Object,Object>> getLimitDictList(String dict_type) {
		List<DictInfo> listDirct=this.getDictList(dict_type);
		List resultList = new ArrayList ();
		for(DictInfo dictInfo :listDirct){
			Map <Object ,Object> resultMap= new HashMap<Object,Object>();
			resultMap.put("DICT_CODE", dictInfo.getDictCode());
			resultMap.put("DICT_VALUE", dictInfo.getDictValue());
			resultList.add(resultMap);
		}
		return resultList;
	}
	@Override
	public List<DictInfo> selectDictByCode(String dictCode) {
		return dictInfoDao.selectDictByCode(dictCode);
	}


}
