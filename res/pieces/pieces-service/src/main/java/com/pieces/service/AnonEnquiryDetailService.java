package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.AnonEnquiryDetail;
import com.pieces.dao.vo.AnonEnquiryDetailVo;

import java.util.List;

public interface AnonEnquiryDetailService extends ICommonService<AnonEnquiryDetail>{

    public PageInfo<AnonEnquiryDetailVo> findByParams(AnonEnquiryDetailVo anonEnquiryDetailVo,Integer pageNum,Integer pageSize);

    public void save(List<AnonEnquiryDetail> list);

    public List<AnonEnquiryDetail> findByType(Integer id, Integer type);
}
