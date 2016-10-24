package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.CommodityDao;
import com.ms.dao.ICommonDao;
import com.ms.dao.SendSampleDao;
import com.ms.dao.model.Commodity;
import com.ms.dao.model.SendSample;
import com.ms.dao.vo.SendSampleVo;
import com.ms.service.CommodityService;
import com.ms.service.SendSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SendSampleServiceImpl  extends AbsCommonService<SendSample> implements SendSampleService{

	@Autowired
	private SendSampleDao sendSampleDao;

	@Autowired
	private CommodityService commodityService;





	@Override
	public PageInfo<SendSampleVo> findByParams(SendSampleVo sendSampleVo,Integer pageNum,Integer pageSize) {
		pageNum = pageNum==null?1:pageNum;
		pageSize = pageSize==null?10:pageSize;
        PageHelper.startPage(pageNum, pageSize);
    	List<SendSampleVo>  list = sendSampleDao.findByParams(sendSampleVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public SendSampleVo findDetailById(Integer id) {
		return sendSampleDao.findDetailById(id);
	}

	@Override
	public List<SendSampleVo> findByCommodityId(int commodityId) {
		List<SendSampleVo> sendSampleVos= sendSampleDao.findByComdityId(commodityId);
		for(SendSampleVo s:sendSampleVos)
		{
			List<Commodity> commodityList =commodityService.findByIds(s.getIntention());
			s.setCommodityList(commodityList);

		}
		return sendSampleVos;
	}




	@Override
	public ICommonDao<SendSample> getDao() {
		return sendSampleDao;
	}

}
