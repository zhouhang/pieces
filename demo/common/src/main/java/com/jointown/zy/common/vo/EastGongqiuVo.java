package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 东方中药材供求信息Vo
 *
 * @author aizhengdong
 *
 * @data 2015年6月19日
 */
public class EastGongqiuVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 供求信息ID */
    private Long gqid;

    /** 发布人姓名 */
    private String userName;
    
    /** 发布人手机号码  */
    private String userMobile;
    
    /** 品种名称 */
    private String ycnam;
    
    /** 规格名称 */
    private String guige;
    
    /** 价格 */
  	private String pri;
    
    /** 品种产地 */
    private String chandi;

    /** 数量 */
    private BigDecimal shul;

    /** 数量单位 */
    private String danw;
    
    /** 数量加数量单位 */
    private String qtyUnitQty;
    
    /** 状态：未处理-0、有效-1、无效-2、用户撤销-3 */
    private Short status;
    
    /** 创建时间 */
    private Date dtm;

    /** 最后更新时间 */
    private Date updtm;

    /** 审核时间*/
    private Date approveTime;

    /** 审核人姓名*/
    private String approverName;

	@Override
	public String toString() {
		return "EastGongqiuVo [gqid=" + gqid + ", userName=" + userName
				+ ", userMobile=" + userMobile + ", ycnam=" + ycnam
				+ ", guige=" + guige + ", pri=" + pri + ", chandi=" + chandi
				+ ", shul=" + shul + ", danw=" + danw + ", qtyUnitQty="
				+ qtyUnitQty + ", status=" + status + ", dtm=" + dtm
				+ ", updtm=" + updtm + ", approveTime=" + approveTime
				+ ", approverName=" + approverName + "]";
	}
	
	public Long getGqid() {
		return gqid;
	}

	public void setGqid(Long gqid) {
		this.gqid = gqid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getYcnam() {
		return ycnam;
	}

	public void setYcnam(String ycnam) {
		this.ycnam = ycnam;
	}

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

	public String getPri() {
		return pri;
	}

	public void setPri(String pri) {
		this.pri = pri;
	}

	public String getChandi() {
		return chandi;
	}

	public void setChandi(String chandi) {
		this.chandi = chandi;
	}

	public BigDecimal getShul() {
		return shul;
	}

	public void setShul(BigDecimal shul) {
		this.shul = shul;
	}

	public String getDanw() {
		return danw;
	}

	public void setDanw(String danw) {
		this.danw = danw;
	}

	public String getQtyUnitQty() {
		return qtyUnitQty;
	}

	public void setQtyUnitQty(String qtyUnitQty) {
		this.qtyUnitQty = qtyUnitQty;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getDtm() {
		return dtm;
	}

	public void setDtm(Date dtm) {
		this.dtm = dtm;
	}

	public Date getUpdtm() {
		return updtm;
	}

	public void setUpdtm(Date updtm) {
		this.updtm = updtm;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
    
}