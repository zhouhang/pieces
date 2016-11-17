package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.AnonEnquiryDao;
import com.pieces.dao.model.AnonEnquiry;
import com.pieces.dao.model.AnonEnquiryDetail;
import com.pieces.dao.vo.AnonEnquiryVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.AnonEnquiryDetailService;
import com.pieces.service.AnonEnquiryService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.AnonEnquiryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnonEnquiryServiceImpl  extends AbsCommonService<AnonEnquiry> implements AnonEnquiryService{

	@Autowired
	private AnonEnquiryDao anonEnquiryDao;

	@Autowired
	private AnonEnquiryDetailService detailService;



	@Override
	public PageInfo<AnonEnquiryVo> findByParams(AnonEnquiryVo anonEnquiryVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<AnonEnquiryVo>  list = anonEnquiryDao.findByParams(anonEnquiryVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	@Transactional
	public Result save(AnonEnquiry enquiry, String captcha, String json, String fileName, String fileUrl) {
		enquiry.setStatus(AnonEnquiryEnum.TODO.getValue());
		enquiry.setPublishTime(new Date());
		List<AnonEnquiryDetail> list = new ArrayList<>();

		if (!StringUtils.isEmpty(json)){
			AnonEnquiryDetail detail = new AnonEnquiryDetail();
			detail.setType(0);
			detail.setContent(json);
			enquiry.setContent("");
			list.add(detail);
		}

		if (!StringUtils.isEmpty(fileName) && !StringUtils.isEmpty(fileUrl)) {
			AnonEnquiryDetail detail = new AnonEnquiryDetail();
			detail.setType(1);
			detail.setContent(fileName);
			// 保存到指定文件夹替换
			detail.setAttachmentUrl(fileUrl);
			list.add(detail);
			enquiry.setContent("批量询价");
		}
		anonEnquiryDao.create(enquiry);

		for (AnonEnquiryDetail detail: list){
			detail.setAnonEnquiryId(enquiry.getId());
		}
		detailService.save(list);
		return new Result(true).info("询价成功!");
	}

	@Override
	public ICommonDao<AnonEnquiry> getDao() {
		return anonEnquiryDao;
	}

}
