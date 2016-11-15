package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.AnonEnquiry;
import com.pieces.dao.vo.AnonEnquiryVo;

import java.util.List;
@AutoMapper
public interface AnonEnquiryDao extends ICommonDao<AnonEnquiry>{

    public List<AnonEnquiryVo> findByParams(AnonEnquiryVo anonEnquiryVo);

}
