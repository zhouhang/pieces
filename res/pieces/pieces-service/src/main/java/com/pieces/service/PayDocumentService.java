package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.PayDocument;
import com.pieces.dao.vo.PayDocumentVo;

import java.util.List;

public interface PayDocumentService extends ICommonService<PayDocument>{

    public PageInfo<PayDocumentVo> findByParams(PayDocumentVo payDocumentVo, Integer pageNum, Integer pageSize);

    /**
     * 根据付款记录id找到所有的付款凭证图片
     * @param payId
     * @return
     */
    public List<PayDocumentVo> findByPayId(Integer payId);
}
