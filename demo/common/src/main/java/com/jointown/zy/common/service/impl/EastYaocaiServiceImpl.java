package com.jointown.zy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.EastYaocaiDao;
import com.jointown.zy.common.model.EastYaocai;
import com.jointown.zy.common.service.EastYaocaiService;

/**
 * 药材ServiceImpl
 * 
 * @author wangjunhu
 *
 * @data 2015年3月5日
 */
@Service
public class EastYaocaiServiceImpl implements EastYaocaiService {

	@Autowired
	private EastYaocaiDao eastYaocaiDao;
	
	@Override
	public List<EastYaocai> findEastYaocaiPzs() {
		return eastYaocaiDao.selectEastYaocaiPzs();
	}

}
