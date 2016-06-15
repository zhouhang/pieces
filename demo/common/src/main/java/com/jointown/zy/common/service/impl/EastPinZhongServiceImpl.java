package com.jointown.zy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.EastPinZhongDao;
import com.jointown.zy.common.service.EastPinzhongService;

@Service
public class EastPinZhongServiceImpl implements EastPinzhongService{

	@Autowired
	private EastPinZhongDao eastPinZhongDao;
	
	@Override
	public List<String> selectAllChandi() {
		return this.eastPinZhongDao.selectAllChandi();
	}
}
