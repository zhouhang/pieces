package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.FinanceReportDao;
import com.jointown.zy.common.dto.FinanceReportDto;
import com.jointown.zy.common.model.FinanceReportModel;
import com.jointown.zy.common.model.Organization;
import com.jointown.zy.common.model.Page;

@Repository
public class FinanceReportDaoImpl extends BaseDaoImpl implements FinanceReportDao {
	
	
	public List<FinanceReportModel> getPageList(Page<FinanceReportModel> page){
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.FinanceReportDao.getPageList", page);
	}
	
	public List<FinanceReportModel> getList(FinanceReportDto dto){
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.FinanceReportDao.getList", dto);
	}
	
	public List<Organization> getOrgList(){
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.FinanceReportDao.selectOrg");
	}
}
