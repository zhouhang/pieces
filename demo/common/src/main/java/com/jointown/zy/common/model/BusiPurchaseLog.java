package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

public class BusiPurchaseLog implements Serializable {
    private Long id;

    private String purchaseId;

    private String purchaseCode;

    private String operator;

    private String remarks;

    private Integer optype;

    private Date createTime;

    private Date updateTime;

    private String purchaseSnapshot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getOptype() {
        return optype;
    }

    public void setOptype(Integer optype) {
        this.optype = optype;
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

    public String getPurchaseSnapshot() {
        return purchaseSnapshot;
    }

    public void setPurchaseSnapshot(String purchaseSnapshot) {
        this.purchaseSnapshot = purchaseSnapshot;
    }

}