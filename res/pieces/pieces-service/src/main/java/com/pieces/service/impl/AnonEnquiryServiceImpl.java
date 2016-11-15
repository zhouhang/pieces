package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.AnonEnquiryDao;
import com.pieces.dao.model.AnonEnquiry;
import com.pieces.dao.vo.AnonEnquiryVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.AnonEnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnonEnquiryServiceImpl  extends AbsCommonService<AnonEnquiry> implements AnonEnquiryService{

	@Autowired
	private AnonEnquiryDao anonEnquiryDao;


	@Override
	public PageInfo<AnonEnquiryVo> findByParams(AnonEnquiryVo anonEnquiryVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<AnonEnquiryVo>  list = anonEnquiryDao.findByParams(anonEnquiryVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<AnonEnquiry> getDao() {
		return anonEnquiryDao;
	}

}
