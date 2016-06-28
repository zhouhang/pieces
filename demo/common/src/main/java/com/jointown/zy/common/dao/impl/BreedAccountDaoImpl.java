package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BreedAccountDao;
import com.jointown.zy.common.dto.BreedAccountDto;
import com.jointown.zy.common.model.BreedAccountModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BreedAccountCountVo;

@Repository(value="breedAccountDaoImpl")
public class BreedAccountDaoImpl extends BaseDaoImpl  implements BreedAccountDao {
	
	public List<BreedAccountModel> selectPageList(Page<BreedAccountModel> page){
		return getSqlSession().selectList("com.jointown.zy.common.persistence.BreedAccountMapper.getPageList", page);
	}
	
	public BreedAccountCountVo selectBreedAccountCount(BreedAccountDto dto){
		return getSqlSession().selectOne("com.jointown.zy.common.persistence.BreedAccountMapper.getBreedAccountCount", dto);
	}

}
