package com.pieces.dao.vo;

import com.pieces.dao.model.AnonEnquiry;
import com.pieces.dao.model.AnonEnquiryDetail;

import java.util.Date;
import java.util.List;

public class AnonEnquiryVo extends AnonEnquiry {

    /**
     * 商品列表信息
     */
    private AnonEnquiryDetail detail;

    /**
     * 上传文件信息
     */
    private List<AnonEnquiryDetail> files;

    /**
     * 最后跟进人Name
     */
    private String lastFollowName;

    private Date publishTimeStart;

    private Date publishTimeEnd;

    private Date lastFollowTimeStart;

    private Date lastFollowTimeEnd;


    private String  enquriyBillCode;

    public String getEnquriyBillCode() {
        return enquriyBillCode;
    }

    public void setEnquriyBillCode(String enquriyBillCode) {
        this.enquriyBillCode = enquriyBillCode;
    }

    public AnonEnquiryDetail getDetail() {
        return detail;
    }

    public void setDetail(AnonEnquiryDetail detail) {
        this.detail = detail;
    }

    public List<AnonEnquiryDetail> getFiles() {
        return files;
    }

    public void setFiles(List<AnonEnquiryDetail> files) {
        this.files = files;
    }

    public String getLastFollowName() {
        return lastFollowName;
    }

    public void setLastFollowName(String lastFollowName) {
        this.lastFollowName = lastFollowName;
    }

    public Date getPublishTimeStart() {
        return publishTimeStart;
    }

    public void setPublishTimeStart(Date publishTimeStart) {
        this.publishTimeStart = publishTimeStart;
    }

    public Date getPublishTimeEnd() {
        return publishTimeEnd;
    }

    public void setPublishTimeEnd(Date publishTimeEnd) {
        this.publishTimeEnd = publishTimeEnd;
    }

    public Date getLastFollowTimeStart() {
        return lastFollowTimeStart;
    }

    public void setLastFollowTimeStart(Date lastFollowTimeStart) {
        this.lastFollowTimeStart = lastFollowTimeStart;
    }

    public Date getLastFollowTimeEnd() {
        return lastFollowTimeEnd;
    }

    public void setLastFollowTimeEnd(Date lastFollowTimeEnd) {
        this.lastFollowTimeEnd = lastFollowTimeEnd;
    }
}