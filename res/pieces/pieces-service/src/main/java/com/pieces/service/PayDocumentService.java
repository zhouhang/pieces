package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.PayDocument;
import com.pieces.dao.vo.PayDocumentVo;

public interface PayDocumentService extends ICommonService<PayDocument>{

    public PageInfo<PayDocumentVo> findByParams(PayDocumentVo payDocumentVo, Integer pageNum, Integer pageSize);
}
