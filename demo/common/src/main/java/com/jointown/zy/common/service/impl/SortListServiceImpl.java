package com.jointown.zy.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.SortListDao;
import com.jointown.zy.common.service.SortListService;
@Service
public class SortListServiceImpl implements SortListService {
	@Autowired
	private SortListDao sortListDao;
	@Override
	public List<Map<Object, Object>> queryAllMedicinal() {
		return sortListDao.selectAllMedicinal();
	}
	@Override
	public Map selectInfoByBreedid(String breedId) {
		return sortListDao.selectInfoByBreedid(breedId);
	}
	@Override
	public List <Map<Object,Object>> selectBreedByCategorys(String id){
		return sortListDao.selectBreedByCategorys(id);
	}
	@Override
	public List<Map<Object, Object>> queryMedicinalByClassName(String className) {
		return sortListDao.selectMedicinalByClassName(className);
	}

}
