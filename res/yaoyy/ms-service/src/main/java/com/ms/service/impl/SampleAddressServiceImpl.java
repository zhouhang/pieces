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
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
	public SampleAddressVo findBySendId(Integer sendId) {
		return sampleAddressDao.findBySendId(sendId);
	}

	@Override
	@Transactional
	public void save(SampleAddress address) {
		Date now=new Date();
		if(address.getId()==null){
			address.setCreateTime(now);
			address.setUpdateTime(now);
			sampleAddressDao.create(address);
		}
		else{
			address.setUpdateTime(now);
			sampleAddressDao.update(address);
		}
	}


	@Override
	public ICommonDao<SampleAddress> getDao() {
		return sampleAddressDao;
	}

}
