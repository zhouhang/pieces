package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.PayDocument;
import com.pieces.dao.vo.PayDocumentVo;

import java.util.List;
@AutoMapper
public interface PayDocumentDao extends ICommonDao<PayDocument>{

    public List<PayDocumentVo> findByParams(PayDocumentVo payDocumentVo);

}
