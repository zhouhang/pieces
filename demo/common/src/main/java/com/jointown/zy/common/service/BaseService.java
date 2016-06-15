/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

import com.jointown.zy.common.exception.BossErrorException;

/**
 * @ClassName: BaseService
 * @Description: Service基础类
 * @Author: 赵航
 * @Date: 2015年5月27日
 * @Version: 1.0
 */
public class BaseService {
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	/**
	 * @return the transactionTemplate
	 */
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	
	protected Exception setException(Exception e) {
		if(e instanceof BossErrorException){
			return e;
		} else {
			return new Exception("系统发生异常，异常信息：" + e.getMessage());
		}
	}
}
