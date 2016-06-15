package com.jointown.zy.common.service;

import java.util.Map;

import com.jointown.zy.common.model.BusiAppeal;
import com.jointown.zy.common.vo.BusiAppealVo;

/**
 * @ClassName: BusiAppealService
 * @Description: 订单申诉Service
 * @Author: wangjunhu
 * @Date: 2015年4月9日
 * @Version: 1.0
 */
public interface BusiAppealService {

	/**
	 * @Description: 查询订单申诉详情
	 * @Author: wangjunhu
	 * @Date: 2015年4月9日
	 * @param orderId
	 * @return
	 */
	BusiAppealVo findBusiAppealVoByOrderId(Map<String,Object> map);

	/**
	 * @Description: 插入申诉数据
	 * @Author: guoyb
	 * @Date: 2015年4月17日
	 * @param busiAppeal
	 */
	int inserBusiAppeal(BusiAppeal busiAppeal);
}
