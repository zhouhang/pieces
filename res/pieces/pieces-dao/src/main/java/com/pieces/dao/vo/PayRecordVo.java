package com.pieces.dao.vo;

import com.pieces.dao.model.PayRecord;

public class PayRecordVo extends PayRecord{

    private Integer payAccountId;

    public Integer getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Integer payAccountId) {
        this.payAccountId = payAccountId;
    }
}