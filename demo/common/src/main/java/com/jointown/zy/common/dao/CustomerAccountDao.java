package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.dto.CustomerAccountDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.CustomerAccountTotalVo;
import com.jointown.zy.common.vo.CustomerAccountVo;

/**
 * @ClassName: CustomerAccountDao
 * @Description: 客户账务报表Dao
 * @Author: ldp
 * @Date: 2015年10月9日
 * @Version: 1.0
 */
public interface CustomerAccountDao {
	
	/**
	 * @Description: 查询客户账务列表
	 * @Author: ldp
	 * @Date: 2015年10月9日
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<CustomerAccountVo> selectCustomerAccountList(Page<CustomerAccountVo> query) throws Exception;
	
	/**
	 * @Description: 统计客户账务总量
	 * @Author: ldp
	 * @Date: 2015年10月13日
	 * @param customerAccountDto
	 * @return
	 * @throws Exception
	 */
	CustomerAccountTotalVo selectCustomerAccountTotals(CustomerAccountDto customerAccountDto)throws Exception;
	
	
	
}
