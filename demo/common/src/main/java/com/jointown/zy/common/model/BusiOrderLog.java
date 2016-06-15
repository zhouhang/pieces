package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单日志表
 * 
 * @author Mr.song
 *
 * @date 2015-3-13
 */
@SuppressWarnings("serial")
public class BusiOrderLog implements Serializable {
    /**
     * ZYC.BUSI_ORDER_LOG.ID (订单日志编号)
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    private Long id;

    /**
     * ZYC.BUSI_ORDER_LOG.USERID (订单所有者ID)
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    private Long userid;

    /**
     * ZYC.BUSI_ORDER_LOG.BUYER (购买者ID)
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    private Long buyer;

//    /**
//     * ZYC.BUSI_ORDER_LOG.IP (操作者所在IP)
//     * @ibatorgenerated 2015-03-13 14:20:48
//     */
//    private String ip;
    
    /**
     * ZYC.BUSI_ORDER_LOG.OPERATOR (操作人)
     */
    private Long operator;


	/**
     * ZYC.BUSI_ORDER_LOG.ORDERID (订单ID)
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    private String orderid;

    /**
     * ZYC.BUSI_ORDER_LOG.WLID (仓单ID)
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    private String wlid;

    /**
     * ZYC.BUSI_ORDER_LOG.LISTINGID (挂牌ID)
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    private String listingid;

    /**
     * ZYC.BUSI_ORDER_LOG.OPTYPE (操作类型(添加，修改，异常))
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    private Short optype;
    
    /**
     * ZYC.BUSI_ORDER_LOG.ORDER_SNAPSHOT (订单快照，表示每次操作前订单的状态)
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    private String orderSnapshot;

    /**
     * ZYC.BUSI_ORDER_LOG.CREATETIME (创建日期)
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    private Date createtime;

    /**
     * ZYC.BUSI_ORDER_LOG.UPDATETIME (修改日期)
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    private Date updatetime;

//    /**
//     * ZYC.BUSI_ORDER_LOG.OPAMOUNT (操作数量)
//     * @ibatorgenerated 2015-03-13 14:20:48
//     */
//    private BigDecimal opamount;
//
//    /**
//     * ZYC.BUSI_ORDER_LOG.TOTALPRICE (成交总价)
//     * @ibatorgenerated 2015-03-13 14:20:48
//     */
//    private BigDecimal totalprice;

    /**
     * ZYC.BUSI_ORDER_LOG.REMARKS (日志描述)
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getBuyer() {
        return buyer;
    }

    public void setBuyer(Long buyer) {
        this.buyer = buyer;
    }


    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getWlid() {
        return wlid;
    }

    public void setWlid(String wlid) {
        this.wlid = wlid;
    }

    public String getListingid() {
        return listingid;
    }

    public void setListingid(String listingid) {
        this.listingid = listingid;
    }

    public Short getOptype() {
        return optype;
    }

    public void setOptype(Short optype) {
        this.optype = optype;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public String getOrderSnapshot() {
		return orderSnapshot;
	}

	public void setOrderSnapshot(String orderSnapshot) {
		this.orderSnapshot = orderSnapshot;
	}
}