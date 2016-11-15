package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.AnonEnquiryDetailDao;
import com.pieces.dao.model.AnonEnquiryDetail;
import com.pieces.dao.vo.AnonEnquiryDetailVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.AnonEnquiryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnonEnquiryDetailServiceImpl  extends AbsCommonService<AnonEnquiryDetail> implements AnonEnquiryDetailService{

	@Autowired
	private AnonEnquiryDetailDao anonEnquiryDetailDao;


	@Override
	public PageInfo<AnonEnquiryDetailVo> findByParams(AnonEnquiryDetailVo anonEnquiryDetailVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<AnonEnquiryDetailVo>  list = anonEnquiryDetailDao.findByParams(anonEnquiryDetailVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<AnonEnquiryDetail> getDao() {
		return anonEnquiryDetailDao;
	}

}
