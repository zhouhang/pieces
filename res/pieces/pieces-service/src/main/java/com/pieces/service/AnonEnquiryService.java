package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.AnonEnquiry;
import com.pieces.dao.vo.AnonEnquiryVo;
import com.pieces.service.constant.bean.Result;

public interface AnonEnquiryService extends ICommonService<AnonEnquiry>{

    public PageInfo<AnonEnquiryVo> findByParams(AnonEnquiryVo anonEnquiryVo,Integer pageNum,Integer pageSize);

    public Result save(AnonEnquiryVo enquiry);
}
