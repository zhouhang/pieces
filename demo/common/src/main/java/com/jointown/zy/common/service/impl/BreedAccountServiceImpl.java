package com.jointown.zy.common.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BreedAccountDao;
import com.jointown.zy.common.dto.BreedAccountDto;
import com.jointown.zy.common.model.BreedAccountModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BreedAccountService;
import com.jointown.zy.common.vo.BreedAccountCountVo;

@Service("breedAccountService")
public class BreedAccountServiceImpl implements BreedAccountService{
	
	private static final Log logger = LogFactory.getLog(BreedAccountServiceImpl.class);
	
	@Autowired
	private BreedAccountDao breedAccountDao;
	
	/**
	 * 品种账务统计数据查询
	 */
	public List<BreedAccountModel> getPageList(Page<BreedAccountModel> page){
		List<BreedAccountModel> breedAccountList = null;
		try {
			breedAccountList = breedAccountDao.selectPageList(page);
		} catch (Exception e) {
			logger.error("BreedAccountServiceImpl getPageList error: " + e);
		}
		return breedAccountList;
	}
	
	/**
	 * 品种账务统计
	 * 仓单统计
	 * 挂牌统计
	 * 交易量统计
	 */
	public BreedAccountCountVo getAccountCountVo(BreedAccountDto dto){
		BreedAccountCountVo count = new BreedAccountCountVo();
		try {
			count = breedAccountDao.selectBreedAccountCount(dto);
		} catch (Exception e) {
			logger.error("BreedAccountServiceImpl getAccountCountVo error: " + e);
		}
		return count;
	}
	
	/**
	 * 获取相同品种的个数
	 */
	public Map<String,Object> countAlikeBreeds (List<BreedAccountModel> breedAccountList){
		Map<String,Object> alikeMap = new LinkedHashMap<String,Object>();//统计品种名相同个数
		//Map<String,Object> repetition = new LinkedHashMap<String,Object>();
		int alikeNum = 0;
		for(int i=0;i<breedAccountList.size();i++){
			//若在alikeMap中出现 则增加次数
			if(alikeMap.containsKey(breedAccountList.get(i).getBreedId())){
				alikeNum = Integer.valueOf(alikeMap.get(breedAccountList.get(i).getBreedId()).toString()) + 1;
				alikeMap.put(breedAccountList.get(i).getBreedId(),alikeNum);
			}else{
				alikeMap.put(breedAccountList.get(i).getBreedId(),1);//将所有记录put到alikeMap中 以品种id为key
			}
		}
		return alikeMap;
	}
}
