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
import com.pieces.service.enums.PathEnum;
import com.pieces.tools.utils.BeanUtils;
import com.pieces.tools.utils.FileUtil;
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
		pageNum=pageNum==null?1:pageNum;
		pageSize=pageSize==null?10:pageSize;
    	PageHelper.startPage(pageNum, pageSize);
    	List<AnonEnquiryVo>  list = anonEnquiryDao.findByParams(anonEnquiryVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	@Transactional
	public Result save(AnonEnquiryVo enquiry) {
		enquiry.setStatus(AnonEnquiryEnum.TODO.getValue());
		enquiry.setPublishTime(new Date());
		List<AnonEnquiryDetail> list = new ArrayList<>();

		if (enquiry.getDetail()!= null){
			enquiry.getDetail().setType(0);
			enquiry.setContent("");
			list.add(enquiry.getDetail());
		}

		if (enquiry.getFiles() != null && enquiry.getFiles().size() > 0) {
			for (AnonEnquiryDetail detail: enquiry.getFiles()){
				// 保存询价问价到对应文件夹
				detail.setAttachmentUrl(FileUtil.saveFileFromTemp(detail.getAttachmentUrl(), PathEnum.ANON.getValue()));
				detail.setType(1);
				list.add(detail);
			}
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
	public AnonEnquiryVo findVoById(Integer id) {
		AnonEnquiryVo vo = new AnonEnquiryVo();
		AnonEnquiry anonEnquiry = findById(id);
		if (anonEnquiry == null) {
			throw new RuntimeException("询价记录不存在");
		}
		BeanUtils.copy(anonEnquiry,vo);
		List<AnonEnquiryDetail> details = detailService.findByType(id,0);
		if (details != null && details.size()>0) {
			vo.setDetail(details.get(0));
		}
		List<AnonEnquiryDetail> files =detailService.findByType(id,1);
		files = FileUtil.convertAbsolutePathToUrl(files, "attachmentUrl");
		vo.setFiles(files);
		return vo;
	}

	@Override
	public ICommonDao<AnonEnquiry> getDao() {
		return anonEnquiryDao;
	}

}
