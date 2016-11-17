package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.AnonEnquiryDetail;
import com.pieces.dao.vo.AnonEnquiryDetailVo;

import java.util.List;
@AutoMapper
public interface AnonEnquiryDetailDao extends ICommonDao<AnonEnquiryDetail>{

    public List<AnonEnquiryDetailVo> findByParams(AnonEnquiryDetailVo anonEnquiryDetailVo);

    public int batchCreate(List<AnonEnquiryDetail> list);
}
