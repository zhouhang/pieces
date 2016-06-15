package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.dto.BreedAccountDto;
import com.jointown.zy.common.model.BreedAccountModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BreedAccountCountVo;

public interface BreedAccountDao {
	
	public List<BreedAccountModel> selectPageList(Page<BreedAccountModel> page);
	public BreedAccountCountVo selectBreedAccountCount(BreedAccountDto dto);

}
