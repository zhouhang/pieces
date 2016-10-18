package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.SendSampleDao;
import com.ms.dao.model.SendSample;
import com.ms.dao.vo.SendSampleVo;
import com.ms.service.SendSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SendSampleServiceImpl  extends AbsCommonService<SendSample> implements SendSampleService{

	@Autowired
	private SendSampleDao sendSampleDao;


	@Override
	public PageInfo<SendSampleVo> findByParams(SendSampleVo sendSampleVo,Integer pageNum,Integer pageSize) {
		if (pageNum == null || pageSize == null) {
			pageNum = 1;
			pageSize = 10;
		}
        PageHelper.startPage(pageNum, pageSize);
    	List<SendSampleVo>  list = sendSampleDao.findByParams(sendSampleVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<SendSample> getDao() {
		return sendSampleDao;
	}

}
