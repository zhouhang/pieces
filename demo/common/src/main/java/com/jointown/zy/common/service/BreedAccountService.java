package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.dto.BreedAccountDto;
import com.jointown.zy.common.model.BreedAccountModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BreedAccountCountVo;
public interface BreedAccountService {
	public List<BreedAccountModel> getPageList(Page<BreedAccountModel> page);
	public Map<String,Object> countAlikeBreeds (List<BreedAccountModel> breedAccountList);
	public BreedAccountCountVo getAccountCountVo(BreedAccountDto dto);
}
