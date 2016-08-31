package com.pieces.dao.vo;

import com.pieces.dao.enums.PayEnum;
import com.pieces.dao.model.PayDocument;
import com.pieces.dao.model.PayRecord;

import java.util.List;

public class PayRecordVo extends PayRecord{

    private Integer payAccountId;

    public Integer getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Integer payAccountId) {
        this.payAccountId = payAccountId;
    }
    // 订购用户
    private String orderUserName;

    // 用药单位
    private String companyFullName;

    //状态
    private String statusText;

    //审核人员
    private String memberName;

    private List<PayDocumentVo> imgs;

    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public String getStatusText() {
        if (getStatus()!= null) {
            statusText = PayEnum.findByValue(getStatus());
        }
        return statusText;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public List<PayDocumentVo> getImgs() {
        return imgs;
    }

    public void setImgs(List<PayDocumentVo> imgs) {
        this.imgs = imgs;
    }
}