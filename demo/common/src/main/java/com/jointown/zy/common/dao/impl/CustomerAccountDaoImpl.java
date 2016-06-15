package com.jointown.zy.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.CustomerAccountDao;
import com.jointown.zy.common.dto.CustomerAccountDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.CustomerAccountTotalVo;
import com.jointown.zy.common.vo.CustomerAccountVo;

/**
 * @ClassName: CustomerAccountDaoImpl
 * @Description: 客户账务报表统计
 * @Author: ldp
 * @Date: 2015年10月9日
 * @Version: 1.0
 */
@Repository
public class CustomerAccountDaoImpl extends BaseDaoImpl implements
		CustomerAccountDao {

	@Override
	public List<CustomerAccountVo> selectCustomerAccountList(
			Page<CustomerAccountVo> query) throws Exception {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.CustomerAccountDao.selectCustomerAccountList", query);
	}

	/**
	 * @Description: 查询条件DTO参数转换成Map
	 * @Author: ldp
	 * @Date: 2015年10月13日
	 * @param customerAccountDto
	 * @return
	 */
	public Map<String, Object> getParamsMap(CustomerAccountDto customerAccountDto){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", customerAccountDto.getUserName());
		paramMap.put("realName", customerAccountDto.getRealName());
		paramMap.put("salesMan", customerAccountDto.getSalesMan());
		paramMap.put("orgId", customerAccountDto.getOrgId());
		paramMap.put("startDate", customerAccountDto.getStartDate());
		paramMap.put("endDate", customerAccountDto.getEndDate());
		return paramMap;
	}

	@Override
	public CustomerAccountTotalVo selectCustomerAccountTotals(
			CustomerAccountDto customerAccountDto) throws Exception {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.CustomerAccountDao.selectCustomerAccountTotals", getParamsMap(customerAccountDto));
	}

}
