package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.SampleAddressDao;
import com.ms.dao.model.SampleAddress;
import com.ms.dao.vo.SampleAddressVo;
import com.ms.service.SampleAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SampleAddressServiceImpl  extends AbsCommonService<SampleAddress> implements SampleAddressService{

	@Autowired
	private SampleAddressDao sampleAddressDao;


	@Override
	public PageInfo<SampleAddressVo> findByParams(SampleAddressVo sampleAddressVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<SampleAddressVo>  list = sampleAddressDao.findByParams(sampleAddressVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<SampleAddress> getDao() {
		return sampleAddressDao;
	}

}
