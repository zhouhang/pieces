package com.jointown.zy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BreedAttrDao;
import com.jointown.zy.common.model.BreedAttr;
import com.jointown.zy.common.service.BreedAttrService;
/**
 * 操作breedAttr
 * @author seven
 *
 */
@Service
public class BreedAttrServiceImpl implements BreedAttrService {
	//注入
	@Autowired BreedAttrDao breedAttrDao;
	@Override
	public List<BreedAttr> queryBreedAttr(BreedAttr breedAttr) {
		return breedAttrDao.queryBreedAttr(breedAttr);
	}

}
