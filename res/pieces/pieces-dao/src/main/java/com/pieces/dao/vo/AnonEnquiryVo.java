package com.pieces.dao.vo;

import com.pieces.dao.model.AnonEnquiry;
import com.pieces.dao.model.AnonEnquiryDetail;

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
}