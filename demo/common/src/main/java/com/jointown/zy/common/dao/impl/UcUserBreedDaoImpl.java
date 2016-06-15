package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.UcUserBreedDao;
import com.jointown.zy.common.model.UcUserBreed;

/**
 * @ClassName: UcUserBreedDaoImpl
 * @Description:  经营品种DaoImpl
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
@Repository
public class UcUserBreedDaoImpl extends BaseDaoImpl implements UcUserBreedDao {

	@Override
	public int addUcUserBreed(UcUserBreed ucUserBreed) throws Exception {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.UcUserBreedDao.addUcUserBreed", ucUserBreed);
	}
	
	public int delUcUserBreed(UcUserBreed ucUserBreed) throws Exception{
		return this.getSqlSession().update("com.jointown.zy.common.dao.UcUserBreedDao.delUserBreed", ucUserBreed);
	}
	
	public List<UcUserBreed> getUcUserBreed(UcUserBreed ucUserBreed) throws Exception{
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.UcUserBreedDao.queryUserBreed", ucUserBreed);
	}

}
