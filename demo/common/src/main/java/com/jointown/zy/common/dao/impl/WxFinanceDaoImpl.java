package com.jointown.zy.common.dao.impl;

import org.springframework.stereotype.Repository;
import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.WxFinanceDao;
import com.jointown.zy.common.model.WxFinance;

@Repository(value="wxFinanceDaoImpl")
public class WxFinanceDaoImpl extends BaseDaoImpl implements WxFinanceDao {

	@Override
	public int insertSelective(WxFinance record) {
		return getSqlSession().insert("com.jointown.zy.common.dao.WxFinanceDao.insertSelective", record);
	}
}
