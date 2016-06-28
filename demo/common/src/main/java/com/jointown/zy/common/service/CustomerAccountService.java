package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.dto.CustomerAccountDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.CustomerAccountTotalVo;
import com.jointown.zy.common.vo.CustomerAccountVo;

/**
 * @ClassName: CustomerAccountService
 * @Description: 客户账务报表service
 * @Author: ldp
 * @Date: 2015年10月9日
 * @Version: 1.0
 */
public interface CustomerAccountService {
	
	/**
	 * @Description: 获取客户账务报表列表
	 * @Author: ldp
	 * @Date: 2015年10月9日
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<CustomerAccountVo> getCustomerAccountList(Page<CustomerAccountVo> query) throws Exception;
	
	/**
	 * @Description: 获取不同单位下，相同用户名的个数
	 * @Author: ldp
	 * @Date: 2015年10月12日
	 * @param customerAccountList
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getUserNumByUnit(List<CustomerAccountVo> customerAccountList) throws Exception;
	
	/**
	 * @Description: 获取客户账务统计总量
	 * @Author: ldp
	 * @Date: 2015年10月13日
	 * @param cAccountDto
	 * @return
	 * @throws Exception
	 */
	CustomerAccountTotalVo getCustomerAccountTotals(CustomerAccountDto cAccountDto) throws Exception;
	
}
