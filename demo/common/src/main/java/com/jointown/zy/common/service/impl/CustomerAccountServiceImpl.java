package com.jointown.zy.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.CustomerAccountDao;
import com.jointown.zy.common.dto.CustomerAccountDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.CustomerAccountService;
import com.jointown.zy.common.vo.CustomerAccountTotalVo;
import com.jointown.zy.common.vo.CustomerAccountVo;

/**
 * @ClassName: CustomerAccountServiceImpl
 * @Description: 客户账务报表serviceImpl
 * @Author: ldp
 * @Date: 2015年10月9日
 * @Version: 1.0
 */
@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

	@Autowired
	private CustomerAccountDao customerAccountDao;
	
	@Override
	public List<CustomerAccountVo> getCustomerAccountList(
			Page<CustomerAccountVo> query) throws Exception {
		if (query == null) {
			return null;
		}
		return customerAccountDao.selectCustomerAccountList(query);
	}

	@Override
	public Map<String, Object> getUserNumByUnit(
			List<CustomerAccountVo> customerAccountList) throws Exception {
		Map<String,Object> alikeMap = new HashMap<String,Object>();
		Map<String,Object> repetition = new HashMap<String,Object>();//统计用户名相同个数
		int alikeNum = 1;
		for(int i=0;i<customerAccountList.size();i++){
			if(alikeMap.containsKey(customerAccountList.get(i).getUserName())){
				alikeNum = Integer.parseInt(alikeMap.get(customerAccountList.get(i).getUserName()).toString()) + 1;
				repetition.put(customerAccountList.get(i).getUserName(), alikeNum);
				alikeMap.put(customerAccountList.get(i).getUserName(),alikeNum);
			}else{
				alikeMap.put(customerAccountList.get(i).getUserName(),1);
			}
		}
		return repetition;
	}

	@Override
	public CustomerAccountTotalVo getCustomerAccountTotals(
			CustomerAccountDto cAccountDto) throws Exception {
		CustomerAccountTotalVo customerAccountTotalVo = new CustomerAccountTotalVo();
		if (cAccountDto == null) {
			return customerAccountTotalVo; 
		}
		customerAccountTotalVo = customerAccountDao.selectCustomerAccountTotals(cAccountDto);
		return customerAccountTotalVo;
	}

}
