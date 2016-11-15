package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.AnonEnquiry;
import com.pieces.dao.vo.AnonEnquiryVo;

public interface AnonEnquiryService extends ICommonService<AnonEnquiry>{

    public PageInfo<AnonEnquiryVo> findByParams(AnonEnquiryVo anonEnquiryVo,Integer pageNum,Integer pageSize);
}
