package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.pay.RemitResultResp;

/**
 * @ClassName: RemitResultService
 * @Description: 划账流水结果处理service
 * @Author: ldp
 * @Date: 2015年7月3日
 * @Version: 1.0
 */
public interface RemitResultService {
	/**
	 * @Description: 根据系统来源和记录条数，查询划账流水结果
	 * @Author: ldp
	 * @Date: 2015年7月3日
	 * @param sysSource
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public List<RemitResultResp> queryRemitResult(Integer sysSource,Integer num) throws Exception;
	
	/**
	 * @Description: 根据划账流水结果ID，修改划账结果记录状态
	 * @Author: ldp
	 * @Date: 2015年7月3日
	 * @param remitResultId
	 * @return
	 * @throws Exception
	 */
	public String doneRemitResult(Integer remitResultId) throws Exception;

}
