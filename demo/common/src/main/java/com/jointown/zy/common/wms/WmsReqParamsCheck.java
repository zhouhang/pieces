package com.jointown.zy.common.wms;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.dto.WmsWareHouseDto;
import com.jointown.zy.common.dto.WmsWlDto;
import com.jointown.zy.common.dto.WmsWlSplitDto;

/**
 * wms 请求参数校验类
 * @author ldp
 * date Mar 25, 2015
 * version 1.0
 */
public class WmsReqParamsCheck {
	
	private static final Logger log = LoggerFactory.getLogger(WmsReqParamsCheck.class);

	private static final String NUM_REG = "^[1-9]+\\d*$";
	/**
	 * 仓单信息参数校验
	 * @updater fanyuna  质检信息中除等级规格字段不为空外，其余均可为空
	 * @param wmsWl
	 * @return
	 */
	public static Map<String,String> wlCheckParams(WmsWlDto wmsWl){
		Map<String,String> map = new HashMap<String,String>();
		if (StringUtils.isBlank(wmsWl.getWlId())) {
			log.info("wlId is not null");
			map.put("code", "105");
			map.put("reason", "仓单编号为空");
			return map;
		}
		if (StringUtils.isBlank(wmsWl.getUserName())) {
			log.info("货主不能为空");
			map.put("code", "105");
			map.put("reason", "货主为空");
			return map;
		}
		if (StringUtils.isBlank(wmsWl.getBreedID())) {
			log.info("breedId is not null");
			map.put("code", "105");
			map.put("reason", "品种编码为空");
			return map;
		}
		if (null == wmsWl.getWltotal()) {
			log.info("wl total is not null");
			map.put("code", "105");
			map.put("reason", "仓单总量为空");
			return map;
		}
		Pattern p = Pattern.compile(NUM_REG);
		/*Matcher m = p.matcher(String.valueOf(wmsWl.getWltotal()));
		if (!m.matches()) {
			log.info("wl total is illegal!");
			return 105;
		}*/
		if(wmsWl.getWltotal().compareTo(new Double(0.0))<=0){
			log.info("wl total is illegal!");
			map.put("code", "105");
			map.put("reason", "仓单总量不合法");
			return map;
		}
		
		if (StringUtils.isBlank(wmsWl.getWlrkdate())) {
			log.info("wl rkdate is not null");
			map.put("code", "105");
			map.put("reason", "入库日期为空");
			return map;
		}
		
		if (StringUtils.isBlank(wmsWl.getOrigin())) {
			log.info("origin is not null");
			map.put("code", "105");
			map.put("reason", "商品产地为空");
			return map;
		}
		if (StringUtils.isBlank(wmsWl.getBatch())) {
			log.info("batch is not null");
			map.put("code", "105");
			map.put("reason", "仓单批次号为空");
			return map;
		}
		if (StringUtils.isBlank(wmsWl.getContractNum())) {
			log.info("ContractNum is not null");
			map.put("code", "105");
			map.put("reason", "仓单合同号为空");
			return map;
		}
		if (StringUtils.isBlank(wmsWl.getWareHouseID())) {
			log.info("WareHouseID is not null");
			map.put("code", "105");
			map.put("reason", "所在仓库编号为空");
			return map;
		}
		
		if (StringUtils.isBlank(wmsWl.getWmioCode())) {
			log.info("wmioCode is not null");
			map.put("code", "105");
			map.put("reason", "入库单编号为空");
			return map;
		}
		
		if (null == wmsWl.getSkuNumber()) {
			log.info("skuNumber is not null");
			map.put("code", "105");
			map.put("reason", "商品件数为空");
			return map;
		}
		
		Matcher m = p.matcher(String.valueOf(wmsWl.getSkuNumber()));
		if (!m.matches()) {
			log.info("skuNumber is illegal!");
			map.put("code", "105");
			map.put("reason", "商品件数不合法");
			return map;
		}
		
		if (StringUtils.isBlank(wmsWl.getStoreName())) {
			log.info("storeName is not null");
			map.put("code", "105");
			map.put("reason", "保管公司为空");
			return map;
		}
		
		if (StringUtils.isBlank(wmsWl.getOrderWeightUnit())) {
			log.info("orderWeightUnit is not null");
			map.put("code", "105");
			map.put("reason", "交易单位为空");
			return map;
		}
		
		if (StringUtils.isBlank(wmsWl.getSignatureUser())) {
			log.info("signatureUser is not null");
			map.put("code", "105");
			map.put("reason", "仓单确认人为空");
			return map;
		}
		
		if (null==wmsWl.getSignatureDate()) {
			log.info("signatureDate is not null");
			map.put("code", "105");
			map.put("reason", "仓单确认日期为空");
			return map;
		}
		
		if(null == wmsWl.getOutedNumber()){
			log.info("outedNumber is not null");
			map.put("code", "105");
			map.put("reason", "已出库数量为空");
			return map;
		}
		
		if(null == wmsWl.getStorageFee()){
			log.info("storageFee is not null");
			map.put("code", "105");
			map.put("reason", "存储费用为空");
			return map;
		}
		
		if(StringUtils.isBlank(wmsWl.getLossStandard())){
			log.info("lossStandard is not null");
			map.put("code", "105");
			map.put("reason", "常规损耗标准为空");
			return map;
		}
		
		/*************质检信息**************/
		if (null == wmsWl.getQualityInfo()) {
			log.info("QualityInfo is not null");
			map.put("code", "105");
			map.put("reason", "仓单质检信息为空");
			return map;
		}
		/*if (StringUtils.isBlank(wmsWl.getQualityInfo().getCheckNum())) {
			log.info("QualityInfo[checkNum] is not null");
			map.put("code", "105");
			map.put("reason", "仓单质检信息中检品数量为空");
			return map;
		}
		Matcher m2 = p.matcher(String.valueOf(wmsWl.getQualityInfo().getCheckNum()));
		if (!m2.matches()) {
			log.info("check num is illegal!");
			map.put("code", "105");
			map.put("reason", "仓单质检信息中检品数量不合法");
			return map;
		}*/
		if (StringUtils.isBlank(wmsWl.getQualityInfo().getGrade())) {
			log.info("QualityInfo[Grade] is not null");
			map.put("code", "105");
			map.put("reason", "仓单质检信息中等级规格为空");
			return map;
		}
		/*if (StringUtils.isBlank(wmsWl.getQualityInfo().getQualityPerson())) {
			log.info("QualityInfo[QualityPerson] is not null");
			map.put("code", "105");
			map.put("reason", "仓单质检信息中质检人为空");
			return map;
		}
		if (StringUtils.isBlank(wmsWl.getQualityInfo().getQualityCheckDate())) {
			log.info("QualityInfo[QualityCheckDate] is not null");
			map.put("code", "105");
			map.put("reason", "仓单质检信息中质检日期为空");
			return map;
		}
		if (StringUtils.isBlank(wmsWl.getQualityInfo().getQualityReportDate())) {
			log.info("QualityInfo[QualityReportDate] is not null");
			map.put("code", "105");
			map.put("reason", "仓单质检信息中质检报告日期为空");
			return map;
		}
		if (StringUtils.isBlank(wmsWl.getQualityInfo().getQualityPic())) {
			log.info("QualityInfo[QualityPic] is not null");
			map.put("code", "105");
			map.put("reason", "仓单质检信息中质检图片为空");
			return map;
		}*/
		/****************图片信息***********************/
		if (null == wmsWl.getPicInfo()) {
			log.info("PicInfo is not null");
			map.put("code", "105");
			map.put("reason", "仓单图片信息为空");
			return map;
		}
		if (wmsWl.getPicInfo().size() < 6) {
			log.info("图片数量不足，必须6张细节照");
			map.put("code", "105");
			map.put("reason", "仓单图片数量不足，必须6张细节照");
			return map;
		}
		return map;
	}
	
	/**
	 * 仓库参数校验
	 * @param wmsWareHouse
	 * @return
	 */
	public static Map<String,String> wareHouseParams(WmsWareHouseDto wmsWareHouse){
		Map<String,String> map = new HashMap<String,String>();
		if (StringUtils.isBlank(wmsWareHouse.getWareHouseCode())) {
			log.info("WareHouseCode is not null");
			map.put("code", "105");
			map.put("reason", "仓库编号为空");
			return map;
		}
		if (StringUtils.isBlank(wmsWareHouse.getWareHouseName())) {
			log.info("WareHouseName is not null");
			map.put("code", "105");
			map.put("reason", "仓库名称为空");
			return map;
		}
		return map;
	}
	
	
	/**
	 * 仓单分割信息参数校验
	 * @param wmsWl
	 * @return
	 */
	public static Map<String,String> wlSplitCheckParams(WmsWlSplitDto wmsWlSplit){
		Map<String,String> map = new HashMap<String,String>();
		if (wmsWlSplit.getOldWlInfo()==null) {
			log.info("WmsWlSplitDto.oldWlInfo is not null");
			map.put("code", "105");
			map.put("reason", "原仓单信息为空");
			return map;
		}
		if (StringUtils.isBlank(wmsWlSplit.getOldWlInfo().getWlId())) {
			log.info("WmsWlSplitDto.oldWlInfo.wlId is not null");
			map.put("code", "105");
			map.put("reason", "原仓单信息中的仓单号为空");
			return map;
		}
		if (wmsWlSplit.getOldWlInfo().getRemainCount()==null) {
			log.info("WmsWlSplitDto.oldWlInfo.remainCount is not null");
			map.put("code", "105");
			map.put("reason", "原仓单信息中的分割后的数量为空");
			return map;
		}
		if (null == wmsWlSplit.getNewWlInfo()) {
			log.info("WmsWlSplitDto.newWlInfo is not null");
			map.put("code", "105");
			map.put("reason", "新仓单信息为空");
			return map;
		}
		
		if (StringUtils.isBlank(wmsWlSplit.getNewWlInfo().getpWlId())) {
			log.info("WmsWlSplitDto.newWlInfo.wlId is not null");
			map.put("code", "105");
			map.put("reason", "新仓单信息中的仓单号为空");
			return map;
		}
		
		if (StringUtils.isBlank(wmsWlSplit.getNewWlInfo().getOrderId())) {
			log.info("WmsWlSplitDto.newWlInfo.orderId is not null");
			map.put("code", "105");
			map.put("reason", "新仓单信息中的订单号为空");
			return map;
		}
		if (wmsWlSplit.getNewWlInfo().getActualCount()==null) {
			log.info("WmsWlSplitDto.newWlInfo.actualCount is not null");
			map.put("code", "105");
			map.put("reason", "新仓单信息中的实际分割数量为空");
			return map;
		}
		if (StringUtils.isBlank(wmsWlSplit.getNewWlInfo().getUserName())) {
			log.info("WmsWlSplitDto.newWlInfo.userName is not null");
			map.put("code", "105");
			map.put("reason", "新仓单信息中的货主为空");
			return map;
		}
		if (StringUtils.isBlank(wmsWlSplit.getNewWlInfo().getpWlId())) {
			log.info("WmsWlSplitDto.newWlInfo.pWlId is not null");
			map.put("code", "105");
			map.put("reason", "新仓单信息中的父仓单号为空");
			return map;
		}
		
		return map;
	}
	
	
	public static void main(String[] args) {
		Pattern p = Pattern.compile(NUM_REG);
		Matcher m = p.matcher("120a");
		if (!m.matches()) {
			System.out.println("wl total is not illegal!");
		}
	}
}
