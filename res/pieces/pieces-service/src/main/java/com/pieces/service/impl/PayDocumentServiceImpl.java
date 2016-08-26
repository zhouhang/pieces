package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.PayDocumentDao;
import com.pieces.dao.model.PayDocument;
import com.pieces.dao.vo.PayDocumentVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.PayDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PayDocumentServiceImpl  extends AbsCommonService<PayDocument> implements PayDocumentService{

	@Autowired
	private PayDocumentDao payDocumentDao;


	@Override
	public PageInfo<PayDocumentVo> findByParams(PayDocumentVo payDocumentVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<PayDocumentVo>  list = payDocumentDao.findByParams(payDocumentVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<PayDocument> getDao() {
		return payDocumentDao;
	}

}
