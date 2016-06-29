package com.jointown.zy.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.jointown.zy.common.util.BeanUtil;
import com.jointown.zy.common.util.TimeUtil;

/**
 * wms 仓单接口dto
 * @author ldp
 * date 2015-03-23
 * version 1.0
 * @updater  fanyuna
 * 仓单基本信息中增加父仓单编号、交易订单号（挂牌交易成功）、业务员ID
 */
public class WmsWlDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8360292174251164481L;
	/** 货主*/
	private String userName;
	/** 品种编码*/
	private String breedID;
	/** 类目*/
	//private String breedCatagroy;
	/** 仓单编号*/
	private String wlId;
	/** 仓单状态*/
	private String wlstate;
	/** 数量*/
	private Double wltotal;
	/** 入库日期*/
	private String wlrkdate;
	/** 产地*/
	private String origin;
	/** 批次号*/
	private String batch;
	/** 合同编号*/
	private String contractNum;
	/** 仓库ID*/
	private String wareHouseID;
	/** 区域ID*/
	private String areaID;
	/** 包装方式*/
	private String packingWay;
	/** add by fanyuna  =======begin======*/
	/** 父仓单编号*/
	private String pWlId;
	/** 交易订单号*/
	private String orderId;
	/** 业务员用户名*/
	private String salesmanUserCode;
	/** add by fanyuna  =======end======*/
	/** add by fanyuna 2015.11.10 仓单接口增加多个字段  ====begin===== */
	private BigDecimal intotal;

    private String wmioCode;

    private Integer skuNumber;

    private String storeName;

    private String orderWeightUnit;

    private String signatureUser;

    private String signatureDate;

    private Integer outedNumber;

    private String contractName;

    private String pledgeName;

    private String storageTermStart;

    private String storageTermEnd;

    private BigDecimal storageFee;

    private String lossStandard;	
	/** add by fanyuna 2015.11.10 仓单接口增加多个字段  ====end===== */
	/** 质检信息*/
	private WmsQualityDto qualityInfo;
	/** 图片信息*/
	private List<WmsPicInfoDto> picInfo;
	
	public WmsWlDto() {
		super();
	}
	public WmsWlDto(String userName, String breedID, //String breedCatagroy,
			String wlId, String wlstate, Double wltotal, String wlrkdate,
			String origin, String batch, String contractNum,
			String wareHouseID, String areaID, String packingWay,
			WmsQualityDto qualityInfo, List<WmsPicInfoDto> picInfo) {
		super();
		this.userName = userName;
		this.breedID = breedID;
		//this.breedCatagroy = breedCatagroy;
		this.wlId = wlId;
		this.wlstate = wlstate;
		this.wltotal = wltotal;
		this.wlrkdate = wlrkdate;
		this.origin = origin;
		this.batch = batch;
		this.contractNum = contractNum;
		this.wareHouseID = wareHouseID;
		this.areaID = areaID;
		this.packingWay = packingWay;
		this.qualityInfo = qualityInfo;
		this.picInfo = picInfo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBreedID() {
		return breedID;
	}

	public void setBreedID(String breedID) {
		this.breedID = breedID;
	}

	/*public String getBreedCatagroy() {
		return breedCatagroy;
	}

	public void setBreedCatagroy(String breedCatagroy) {
		this.breedCatagroy = breedCatagroy;
	}*/

	public String getWlId() {
		return wlId;
	}
	public void setWlId(String wlId) {
		this.wlId = wlId;
	}
	public String getWlstate() {
		return wlstate;
	}

	public void setWlstate(String wlstate) {
		this.wlstate = wlstate;
	}

	public Double getWltotal() {
		return wltotal;
	}
	public void setWltotal(Double wltotal) {
		this.wltotal = wltotal;
	}
	public String getWlrkdate() {
		return wlrkdate;
	}

	public void setWlrkdate(String wlrkdate) {
		this.wlrkdate = wlrkdate;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getWareHouseID() {
		return wareHouseID;
	}

	public void setWareHouseID(String wareHouseID) {
		this.wareHouseID = wareHouseID;
	}

	public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	public String getPackingWay() {
		return packingWay;
	}

	public void setPackingWay(String packingWay) {
		this.packingWay = packingWay;
	}
	
	public String getpWlId() {
		return pWlId;
	}
	public void setpWlId(String pWlId) {
		this.pWlId = pWlId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSalesmanUserCode() {
		return salesmanUserCode;
	}
	public void setSalesmanUserCode(String salesmanUserCode) {
		this.salesmanUserCode = salesmanUserCode;
	}
	public WmsQualityDto getQualityInfo() {
		return qualityInfo;
	}
	public void setQualityInfo(WmsQualityDto qualityInfo) {
		this.qualityInfo = qualityInfo;
	}
	public List<WmsPicInfoDto> getPicInfo() {
		return picInfo;
	}
	public void setPicInfo(List<WmsPicInfoDto> picInfo) {
		this.picInfo = picInfo;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	
	public BigDecimal getIntotal() {
		return intotal;
	}
	public void setIntotal(BigDecimal intotal) {
		this.intotal = intotal;
	}
	public String getWmioCode() {
		return wmioCode;
	}
	public void setWmioCode(String wmioCode) {
		this.wmioCode = wmioCode;
	}
	public Integer getSkuNumber() {
		return skuNumber;
	}
	public void setSkuNumber(Integer skuNumber) {
		this.skuNumber = skuNumber;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getOrderWeightUnit() {
		return orderWeightUnit;
	}
	public void setOrderWeightUnit(String orderWeightUnit) {
		this.orderWeightUnit = orderWeightUnit;
	}
	public String getSignatureUser() {
		return signatureUser;
	}
	public void setSignatureUser(String signatureUser) {
		this.signatureUser = signatureUser;
	}
	public Integer getOutedNumber() {
		return outedNumber;
	}
	public void setOutedNumber(Integer outedNumber) {
		this.outedNumber = outedNumber;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getPledgeName() {
		return pledgeName;
	}
	public void setPledgeName(String pledgeName) {
		this.pledgeName = pledgeName;
	}
	public BigDecimal getStorageFee() {
		return storageFee;
	}
	public void setStorageFee(BigDecimal storageFee) {
		this.storageFee = storageFee;
	}
	public String getLossStandard() {
		return lossStandard;
	}
	public void setLossStandard(String lossStandard) {
		this.lossStandard = lossStandard;
	}
	
	
	
	public String getSignatureDate() {
		return signatureDate;
	}
	public void setSignatureDate(String signatureDate) {
		this.signatureDate = signatureDate;
	}
	public String getStorageTermStart() {
		return storageTermStart;
	}
	public void setStorageTermStart(String storageTermStart) {
		this.storageTermStart = storageTermStart;
	}
	public String getStorageTermEnd() {
		return storageTermEnd;
	}
	public void setStorageTermEnd(String storageTermEnd) {
		this.storageTermEnd = storageTermEnd;
	}
	public static void main(String[] args) {
//		String json="{\"wlId\":\"0001\",\"wareHouseID\":\"001\",\"userName\":\"张三\",\"wltotal\":20,\"wlstate\":1,\"breedID\":002,\"batch\":1001,\"areaID\":1001,\"wlrkdate\":\"2015-03-18 11:01:30\",\"origin\":湖北武汉,\"contractNum\":10001,\"packingWay\":包,\"breedCatagroy\":根茎类,\"qualityInfo\":{\"qualityPerson\":张三,\"qualityCheckDate\":\"2015-03-19 10:30:27\",\"qualityReportDate\":\"2015-03-20 11:03:30\",\"qualityPic\":\"http://www.baidu.com\",\"qualityItems\":{\"灰分\":\"10%\",\"水分\":\"20%\"}},\"picInfo\":[{\"picType\":1,picUrl:\"http://127.168.0.1/images/1.jpg\"},{\"picType\":2,\"picUrl\":\"http://127.168.0.1/images/2.jpg\"},{\"picType\":3,\"picUrl\":\"http://127.168.0.1/images/1.jpg\"},{\"picType\":4,picUrl:\"http://127.168.0.1/images/2.jpg\"},{\"picType\":5,picUrl:\"http://127.168.0.1/images/1.jpg\"},{\"picType\":6,picUrl:\"http://127.168.0.1/images/2.jpg\"}]}";
//		String json="{\"wlId\":\"0001\",\"wltotal\":20}";
//		String json="{\"wlId\":\"0002\",\"wareHouseID\":\"001\",\"userName\":\"daoping13\",\"wltotal\":20,\"wlstate\":1,\"breedID\":002,\"batch\":1001,\"areaID\":1001,\"wlrkdate\":\"2015-03-18 11:01:30\",\"origin\":湖北武汉,\"contractNum\":10001,\"packingWay\":包,\"pWlId\":\"0001\",\"orderId\":\"ZYC2015020510022\",\"qualityInfo\":{\"qualityPerson\":张三,\"qualityCheckDate\":\"2015-03-19 10:30:27\",\"qualityReportDate\":\"2015-03-20 11:03:30\",\"qualityPic\":\"http://www.baidu.com\",\"levelEva\":\"等级评定\",\"qualityItemsInfo\":[{\"qualityType\":\"检查\",\"qualityItem\":\"水分\",\"qualityStandard\":\"不大于5%\",\"qualityResult\":\"50\"}]},\"picInfo\":[{\"picType\":1,picUrl:\"http://127.168.0.1/images/1.jpg\"},{\"picType\":2,\"picUrl\":\"http://127.168.0.1/images/2.jpg\"},{\"picType\":3,\"picUrl\":\"http://127.168.0.1/images/1.jpg\"},{\"picType\":4,picUrl:\"http://127.168.0.1/images/2.jpg\"},{\"picType\":5,picUrl:\"http://127.168.0.1/images/1.jpg\"},{\"picType\":6,picUrl:\"http://127.168.0.1/images/2.jpg\"}]}";
		String json="{\"areaID\":\"420105\",\"batch\":\"B001L05006150630023\",\"breedID\":\"05006\",\"contractNum\":\"00000000\",\"origin\":\"广西\",\"packingWay\":\"袋\",\"picInfo\":[{\"picType\":3,\"picUrl\":\"http://wms.54315.com:8081/static/upload/image/20150630/20150630170619_99.jpg\"},{\"picType\":2,\"picUrl\":\"http://wms.54315.com:8081/static/upload/image/20150630/20150630170611_741.jpg\"},{\"picType\":4,\"picUrl\":\"http://wms.54315.com:8081/static/upload/image/20150630/20150630170625_44.jpg\"},{\"picType\":1,\"picUrl\":\"http://wms.54315.com:8081/static/upload/image/20150630/20150630170548_311.jpg\"},{\"picType\":6,\"picUrl\":\"http://wms.54315.com:8081/static/upload/image/20150630/20150630170606_86.jpg\"},{\"picType\":5,\"picUrl\":\"http://wms.54315.com:8081/static/upload/image/20150630/20150630170556_70.jpg\"}],\"qualityInfo\":{\"checkNum\":\"500\",\"grade\":\"碎\",\"qualityCheckDate\":\"2015-06-29\",\"qualityItemsInfo\":[{\"qualityItem\":\"性状鉴别\",\"qualityResult\":\"待检验\",\"qualityStandard\":\"含有未知掺伪品\",\"qualityType\":\"鉴别\"}],\"qualityPerson\":\"林飞\",\"qualityPic\":\"http://wms.54315.com:8081/static/upload/image/20150630/20150630170637_40.jpg\",\"qualityReportDate\":\"2015-06-30\"},\"userName\":\"mayuxin\",\"wareHouseID\":\"B001L\",\"wlId\":\"B001L150630016\",\"wlrkdate\":\"2015-06-30 00:00:00\",\"wlstate\":1,\"wltotal\":5000}";
		WmsWlDto dto = BeanUtil.jsonToObject(json, WmsWlDto.class);
//		System.out.println(dto.getBatch()+"-"+dto.getPicInfo().get(0).getPicUrl()+"  "+dto.getQualityInfo().getQualityItems().get("水分"));
		System.out.println(dto.getQualityInfo().getQADesc());
		}

}
