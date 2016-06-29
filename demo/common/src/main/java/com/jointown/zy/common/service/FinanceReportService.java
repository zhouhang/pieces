package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.dto.FinanceReportDto;
import com.jointown.zy.common.model.FinanceReportModel;
import com.jointown.zy.common.model.Organization;
import com.jointown.zy.common.model.Page;

public interface FinanceReportService {
	
	public List<FinanceReportModel> getPageList(Page<FinanceReportModel> page);
	public List<FinanceReportModel> getList(FinanceReportDto dto);
	public List<Organization> getOrgList();
	public List<Organization> getOrgListCache();

}
