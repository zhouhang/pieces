package com.jointown.zy.common.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.jointown.zy.common.util.NumberUtils;

/**
 * 仓单管理
 * @author wangjunhu
 *	2014-12-18
 */
public class BusiWhlist implements Serializable {

	private static final long serialVersionUID = 1L;

	//仓单ID
	private String wlId;
	//用户ID
	private Long userId;
	//类目ID
	private Long categoryId;
	//品种编码
	private Long breedCode;
	//仓库ID
	private String wareHouseId;
	//区域ID
	private String areaId;
	//仓单总量
//	private double wlTotal;
	private Double wlTotal;
	//计量单位
	private String wlUnit;
	//入库日期
	private Date wlrkDate;
	//仓单状态
	private Integer wlState;
	//合同编号
	private String contractNum;
	//产地
	private String origin;
	//批次
	private String batch;
	//包装方式
	private String packingWay;
	//有效标志（0标识有效，1标识无效）
	private Integer wlFlag;
	//创建日期
	private Date createTime;
	//修改日期
	private Date updateTime;
	//用户账号
	private String account;
	//出货日期
	private Date deliveryTime;
	//出货状态
	private Integer deliveryState;
	//提货编号
	private String delivery;
	//仓单可挂数量
	//private double wlSurplus;
	private Double wlSurplus;
	
	//add by fanyuna 仓单表新增仓单损益数量、父仓单号、交易订单号、业务员ID
	private Double profitLoss;
	
	private String pWlid;
	
	private String orderId;
	
	private Long createrId;
	
	//ADD BY FANYUNA 2015.11.10 仓单表增加字段
	//仓单入库总量
	private BigDecimal intotal;

    private String wmiocode;

    private Integer skunumber;

    private String storename;

  //  private String orderweightunit;

    private String signatureuser;

    private Date signaturedate;

    private Integer outednumber;

    private String contractname;

    private String pledgename;

    private Date storagetermstart;

    private Date storagetermend;

    private BigDecimal storagefee;

    private String lossstandard;
	
	public BusiWhlist() {
		super();
	}

	public BusiWhlist(String wlId, Long userId, Long categoryId,
			Long breedCode, String wareHouseId, String areaId, Double wlTotal,
			String wlUnit, Date wlrkDate, Integer wlState, String contractNum,
			String origin, String batch, String packingWay, Integer wlFlag,
			Date createTime, Date updateTime, String account,
			Date deliveryTime, Integer deliveryState, String delivery,
			Double wlSurplus) {
		this.wlId = wlId;
		this.userId = userId;
		this.categoryId = categoryId;
		this.breedCode = breedCode;
		this.wareHouseId = wareHouseId;
		this.areaId = areaId;
		this.wlTotal = wlTotal;
		this.wlUnit = wlUnit;
		this.wlrkDate = wlrkDate;
		this.wlState = wlState;
		this.contractNum = contractNum;
		this.origin = origin;
		this.batch = batch;
		this.packingWay = packingWay;
		this.wlFlag = wlFlag;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.account = account;
		this.deliveryTime = deliveryTime;
		this.deliveryState = deliveryState;
		this.delivery = delivery;
		this.wlSurplus = wlSurplus;
	}
	
	public BusiWhlist clone(){
		try {
			return (BusiWhlist)BeanUtilsBean.getInstance().cloneBean(this);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @Description: 仓单可挂数量缩减
	 * @Author: robin.liu
	 * @Date: 2015年8月26日
	 * @param toMinus
	 * @return
	 */
	public BusiWhlist minusSurplus(BigDecimal toMinus) throws Exception{
		BigDecimal wlSurplus = new BigDecimal(getWlSurplus()).subtract(toMinus);
		if(wlSurplus.compareTo(BigDecimal.ZERO)<0){
			throw new Exception("减少仓单可挂数量后，可挂数量:"+wlSurplus+",小于0！");
		}
		setWlSurplus(NumberUtils.halfUp(wlSurplus).doubleValue());
		return this;
	}
	
	/**
	 * 
	 * @Description: 仓单可挂数量增加
	 * @Author: robin.liu
	 * @Date: 2015年8月26日
	 * @param toAdd
	 * @return
	 */
	public BusiWhlist addSurplus(BigDecimal toAdd) throws Exception{
		Double wlSurplus = NumberUtils.halfUp(new BigDecimal(getWlSurplus()).add(toAdd)).doubleValue();
		if(wlSurplus.compareTo(getWlTotal())>0){
			throw new Exception("增加仓单可挂数量后，可挂数量:"+wlSurplus+",大于仓单总量！");
		}
		setWlSurplus(wlSurplus);
		return this;
	}
	
	public String getWlId() {
		return wlId;
	}

	public void setWlId(String wlId) {
		this.wlId = wlId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getBreedCode() {
		return breedCode;
	}

	public void setBreedCode(Long breedCode) {
		this.breedCode = breedCode;
	}

	public String getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(String wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Double getWlTotal() {
		return wlTotal;
	}

	public void setWlTotal(Double wlTotal) {
		this.wlTotal = wlTotal;
	}

	public String getWlUnit() {
		return wlUnit;
	}

	public void setWlUnit(String wlUnit) {
		this.wlUnit = wlUnit;
	}

	public Date getWlrkDate() {
		return wlrkDate;
	}

	public void setWlrkDate(Date wlrkDate) {
		this.wlrkDate = wlrkDate;
	}

	public Integer getWlState() {
		return wlState;
	}

	public void setWlState(Integer wlState) {
		this.wlState = wlState;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
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

	public String getPackingWay() {
		return packingWay;
	}

	public void setPackingWay(String packingWay) {
		this.packingWay = packingWay;
	}

	public Integer getWlFlag() {
		return wlFlag;
	}

	public void setWlFlag(Integer wlFlag) {
		this.wlFlag = wlFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public BusiWhlist renewUpdateTime() {
		this.updateTime = new Date();
		return this;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(Integer deliveryState) {
		this.deliveryState = deliveryState;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public Double getWlSurplus() {
		return wlSurplus;
	}

	public void setWlSurplus(Double wlSurplus) {
		this.wlSurplus = wlSurplus;
	}

	public Double getProfitLoss() {
		return profitLoss;
	}

	public void setProfitLoss(Double profitLoss) {
		this.profitLoss = profitLoss;
	}

	public String getpWlid() {
		return pWlid;
	}

	public void setpWlid(String pWlid) {
		this.pWlid = pWlid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public BigDecimal getIntotal() {
		return intotal;
	}

	public void setIntotal(BigDecimal intotal) {
		this.intotal = intotal;
	}

	public String getWmiocode() {
		return wmiocode;
	}

	public void setWmiocode(String wmiocode) {
		this.wmiocode = wmiocode;
	}

	public Integer getSkunumber() {
		return skunumber;
	}

	public void setSkunumber(Integer skunumber) {
		this.skunumber = skunumber;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}


	public String getSignatureuser() {
		return signatureuser;
	}

	public void setSignatureuser(String signatureuser) {
		this.signatureuser = signatureuser;
	}

	public Date getSignaturedate() {
		return signaturedate;
	}

	public void setSignaturedate(Date signaturedate) {
		this.signaturedate = signaturedate;
	}

	public Integer getOutednumber() {
		return outednumber;
	}

	public void setOutednumber(Integer outednumber) {
		this.outednumber = outednumber;
	}

	public String getContractname() {
		return contractname;
	}

	public void setContractname(String contractname) {
		this.contractname = contractname;
	}

	public String getPledgename() {
		return pledgename;
	}

	public void setPledgename(String pledgename) {
		this.pledgename = pledgename;
	}

	public Date getStoragetermstart() {
		return storagetermstart;
	}

	public void setStoragetermstart(Date storagetermstart) {
		this.storagetermstart = storagetermstart;
	}

	public Date getStoragetermend() {
		return storagetermend;
	}

	public void setStoragetermend(Date storagetermend) {
		this.storagetermend = storagetermend;
	}

	public BigDecimal getStoragefee() {
		return storagefee;
	}

	public void setStoragefee(BigDecimal storagefee) {
		this.storagefee = storagefee;
	}

	public String getLossstandard() {
		return lossstandard;
	}

	public void setLossstandard(String lossstandard) {
		this.lossstandard = lossstandard;
	}
	
	
	
}
