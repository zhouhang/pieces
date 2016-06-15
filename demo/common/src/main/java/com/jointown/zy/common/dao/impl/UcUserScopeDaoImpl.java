package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.UcUserScopeDao;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.UcUserBreed;
import com.jointown.zy.common.model.UcUserScope;
/**
 * @ClassName: UcUserScopeDaoImpl
 * @Description: 经营范围信息DaoImpl
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
@Repository
public class UcUserScopeDaoImpl extends BaseDaoImpl implements UcUserScopeDao {

	@Override
	public int addUcUserScope(UcUserScope ucUserScope) throws Exception {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.UcUserScopeDao.addUcUserScope", ucUserScope);
	}
	
	public List<Breed> getBreeds(String param){
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.UcUserScopeDao.selectBreeds", param);
	}
	
	public List<UcUserBreed> getBreedsByUserId(Long userId){
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.UcUserScopeDao.selectBreedsByUserId", userId);
	}
	
	@Override
	public UcUserScope selectUcUserScopeById(Long userId) throws Exception {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.UcUserScopeDao.selectUcUserScopeByUserId", userId);
	}

	@Override
	public int updateUcUserScope(UcUserScope ucUserScope) throws Exception {
		return this.getSqlSession().update("com.jointown.zy.common.dao.UcUserScopeDao.updateUcUserScopeById", ucUserScope);
	}


}
