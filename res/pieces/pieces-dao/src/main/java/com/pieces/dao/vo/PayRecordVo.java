package com.pieces.dao.vo;

import com.pieces.dao.model.PayRecord;

public class PayRecordVo extends PayRecord{

    // 订购用户
    private String orderUserName;

    // 用药单位
    private String companyFullName;

    //应付金额
    private String amountsPayable;
}