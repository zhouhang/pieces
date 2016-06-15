package com.jointown.zy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.EastPzDanganDao;
import com.jointown.zy.common.model.EastPzDangan;
import com.jointown.zy.common.service.EastPzDanganService;

/**
 * 品种档案ServiceImpl
 * 
 * @author wangjunhu
 *
 * @data 2015年3月6日
 */
@Service
public class EastPzDanganServiceImpl implements EastPzDanganService {
	
	@Autowired
	private EastPzDanganDao eastPzDanganDao;
	
	@Override
	public EastPzDangan findByPrimaryKey(String ycnam) {
		return eastPzDanganDao.selectByPrimaryKey(ycnam);
	}

	@Override
	public String findBreedNameByName(String name) {
		List<String> breedNames = eastPzDanganDao.selectBreedNameByName(name);
		String breedName = "";
		int breedNameNum = breedNames.size();
		if(breedNameNum==1){
			breedName = breedNames.get(0);
			return breedName;
		}else if(breedNameNum>1){
			int breedNameMinLength = 0;
			for (String string : breedNames) {
				if(name.equals(string)){
					breedName = string;
					return breedName;
				}
				int stringLength = string.length();
				if(breedNameMinLength == 0 || stringLength < breedNameMinLength){
					breedNameMinLength = stringLength;
					breedName = string;
				}
			}
		}
		return breedName;
	}

}
