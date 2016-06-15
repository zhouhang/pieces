package com.jointown.zy.common.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BusiAppealVo
 * @Description: 订单申诉VO
 * @Author: wangjunhu
 * @Date: 2015年4月9日
 * @Version: 1.0
 */
public class BusiAppealVo {
	
	/** 挂牌标题 */
	private String title;
	
	/** 单价 */
	private BigDecimal unitprice;
	
	/** 计量单位 */
	private String dictvalue;
	
	/** 数量 */
	private BigDecimal amount;
	
	/** 订单总价 */
	private BigDecimal totalprice;
	
	/** 仓库名称 */
	private String warehousename;
	
	/** 散货图片 */
	private String path;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public String getDictvalue() {
		return dictvalue;
	}

	public void setDictvalue(String dictvalue) {
		this.dictvalue = dictvalue;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
     * ZYC.BUSI_APPEAL.APPEALID (订单申诉表ID)
     * @ibatorgenerated 2015-04-09 10:15:02
     */
    private Long appealid;

    /**
     * ZYC.BUSI_APPEAL.ORDERID (订单ID)
     * @ibatorgenerated 2015-04-09 10:15:02
     */
    private String orderid;

    /**
     * ZYC.BUSI_APPEAL.REASON (问题描述内容)
     * @ibatorgenerated 2015-04-09 10:15:02
     */
    private String reason;

    /**
     * ZYC.BUSI_APPEAL.APPEALTYPE (申诉类型)
     * @ibatorgenerated 2015-04-09 10:15:02
     */
    private String appealtype;

    /**
     * ZYC.BUSI_APPEAL.EXAMINESTATE (BOSS审核状态)
     * @ibatorgenerated 2015-04-09 10:15:02
     */
    private Short examinestate;
    
    /**
     * ZYC.BUSI_APPEAL.EVIDENCEPIC (证据地址（逗号分隔）)
     * @ibatorgenerated 2015-04-09 14:03:12
     */
    private List<String> evidencepics;
    
    /**
     * ZYC.BUSI_APPEAL.REJECTREASON (驳回原因)
     * @ibatorgenerated 2015-04-09 14:03:12
     */
    private String rejectreason;

	public Long getAppealid() {
		return appealid;
	}

	public void setAppealid(Long appealid) {
		this.appealid = appealid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAppealtype() {
		return appealtype;
	}

	public void setAppealtype(String appealtype) {
		this.appealtype = appealtype;
	}

	public Short getExaminestate() {
		return examinestate;
	}

	public void setExaminestate(Short examinestate) {
		this.examinestate = examinestate;
	}

	public List<String> getEvidencepics() {
		return evidencepics;
	}

	public void setEvidencepics(String evidencepic) {
		if(evidencepic!=null&&!evidencepic.isEmpty()){
			this.evidencepics = new ArrayList<String>();
			String evidencepicz[] = evidencepic.split(",");
			for (String string : evidencepicz) {
				this.evidencepics.add(string);
			}
		}
	}

	public String getRejectreason() {
		return rejectreason;
	}

	public void setRejectreason(String rejectreason) {
		this.rejectreason = rejectreason;
	}
    
}
