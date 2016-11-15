package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.AnonEnquiryDetail;
import com.pieces.dao.vo.AnonEnquiryDetailVo;

public interface AnonEnquiryDetailService extends ICommonService<AnonEnquiryDetail>{

    public PageInfo<AnonEnquiryDetailVo> findByParams(AnonEnquiryDetailVo anonEnquiryDetailVo,Integer pageNum,Integer pageSize);
}
