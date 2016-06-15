package com.jointown.zy.common.pay.ucs.service;

import java.util.Map;

import com.jointown.zy.common.dto.PayReqDto;
import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.model.RemitFlow;

public interface UcsService {

	/**
	 * 珍药宝订单请求
	 * 
	 * @param payReqDto
	 * @return
	 */
	public Map<String, String> payOrder(PayReqDto payReqDto);

	/**
	 * 珍药宝增加支付流水
	 * 
	 * @param payReqDto
	 * @param paymentNo
	 * @param ip
	 * @return
	 */
	public int addPayFlow(PayReqDto payReqDto, String paymentNo, String ip);

	/**
	 * 珍药宝后台回调
	 * 
	 * @param data
	 * @param signdata
	 * @throws Exception
	 */
	public void payCallBack(String data, String signdata) throws Exception;

	/**
	 * 珍药宝单点登录
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 * @author zhouji
	 */
	public String payLogin(String type) throws Exception;
	/**
	 * 调用批量划账接口
	 * @param remitAccountInfo
	 * @return
	 * @throws Exception
	 */
	public String batchRemitAccount(RemitFlow remitFlow) throws Exception;
	/**
	 * 记录划账流水信息
	 * @param remitAccountInfo
	 * @return
	 * @throws Exception
	 */
	public String addRemitFlow(RemitFlow remitFlow) throws Exception;
	
	
}
