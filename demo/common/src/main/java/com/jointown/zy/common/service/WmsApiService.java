package com.jointown.zy.common.service;

import java.util.Map;

import com.jointown.zy.common.dto.WmsWareHouseDto;
import com.jointown.zy.common.dto.WmsWlDto;
import com.jointown.zy.common.dto.WmsWlSplitDto;
import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.vo.MessageVo;

public interface WmsApiService {
	
	/**
	 * 将json转换的对象信息保存在数据库中
	 * @param wlDto 将json转换的对象
	 * update by fanyuna 2015.08.27 仓单新增成功后记仓单日志
	 * @return
	 */
	public int syncWlInfo(WmsWlDto wlDto,Map<String,String> picInfo);
	
	/**
	 * wms 仓单同步修改接口
	 * @param wlDto
	 * update by fanyuna 2015.08.27 仓单修改成功后记仓单日志
	 * @return
	 */
	public int wmsWlUpdate(WmsWlDto wlDto,Map<String,String> updatePicInfo);
	
	/**
	 * wms 仓库新增
	 * @param busiWareHouse
	 */
	public int wmsWareHouseAdd(WmsWareHouseDto wmsWareHouse);
	
	/**
	 * wms 仓库修改
	 * @param busiWareHouse
	 */
	public int wmsWareHouseUpdate(WmsWareHouseDto wmsWareHouse);
	
	/**
	 * 
	 * @Description: 仓单冻结成功接口
	 * @Author: fanyuna
	 * @Date: 2015年4月15日
	 * @param map wlId 仓单编号,wlActualCount 实际冻结数量,orderId 交易订单号

	 * @return
	 */
	public int wmsFreezeSuccess(Map<String,String> map);
	
	
	/**
	 * 
	 * @Description: 仓单分割成功接口
	 * @Author: fanyuna
	 * @Date: 2015年4月15日
	 * @param wmsWlSplitDto 仓单分割json串转DTO
	 * @return
	 */
	public int wmsWlSplitSuccess(WmsWlSplitDto wmsWlSplitDto);
	
	/**
	 * 
	 * @Description: 解冻接口 客户端(调用WMS)
	 * @Author: fanyuna
	 * @Date: 2015年4月15日
	 * @param wlId  仓单编号
	 * @param unfreezeCount 实际解冻数量
	 * @param orderId 交易订单号  判断是那条电商订单号产生的冻结计划
	 * @return  true为解冻成功 false为解冻失败
	 */
	public boolean unfreezeTrade(String wlId,double unfreezeCount,String orderId);
	
	/**
	 * 
	 * @Description: 申请仓单交易冻结接口（交保证金时调用）
	 * @Author: fanyuna
	 * @Date: 2015年4月16日
	 * @param wlId 仓单编号
	 * @param freezeCount 冻结数量(即买家拍下数量)
	 * @param orderId 订单号
	 * @return true 申请冻结成功,false 申请冻结失败
	 */
	public boolean applyWlFreeze(String wlId,double freezeCount,String orderId);
	
	/**
	 * 
	 * @Description: 申请仓单分割接口
	 * @Author: fanyuna
	 * @Date: 2015年4月16日
	 * @param wlId 仓单编号
	 * @param freezeCount 分割数量（实际冻结数量）
	 * @param orderId 订单号
	 * @param buyer 买家用户名
	 * @return
	 */
	public boolean applyWlSplit(String wlId,double freezeCount,String orderId,String buyer);
	
	/**
	 * wms api接口同步日志
	 * @param apiFlag
	 * 			接口标识
	 * @param data
	 * 			同步数据
	 * @param reasons
	 * 			失败原因
	 * @param synFlag
	 * 			同步是否成功，1成功 2失败
	 * @param flag 1 wms,2金融链
	 */
	public void wmsApiSynLog(String apiFlag,String dataId,String data,String reasons,Integer synStatus,ApiFlagEnums flag);
	
	
	/**
	 * 
	 * @Description: 申请仓单分割接口
	 * @Author: fanyuna
	 * @Date: 2015年9月16日
	 * @param json 请求分割接口的json串
	 * @return
	 */
	public MessageVo applyWlSplit(String json);
	
}
