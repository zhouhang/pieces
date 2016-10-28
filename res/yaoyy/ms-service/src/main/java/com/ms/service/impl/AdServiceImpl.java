package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.AdDao;
import com.ms.dao.model.Ad;
import com.ms.dao.vo.AdVo;
import com.ms.service.AdService;
import com.ms.tools.upload.PathConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AdServiceImpl  extends AbsCommonService<Ad> implements AdService{

	@Autowired
	private AdDao adDao;

	private String folderName ="publicity/";

	@Autowired
	private PathConvert pathConvert;

	@Override
	public PageInfo<AdVo> findByParams(AdVo adVo,Integer pageNum,Integer pageSize) {
		if (pageNum==null || pageSize== null) {
			pageNum =1;
			pageSize = 10;
		}
    	PageHelper.startPage(pageNum, pageSize);
    	List<AdVo>  list = adDao.findByParams(adVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public List<AdVo> findByType(Integer typeId) {
		AdVo vo = new AdVo();
		vo.setTypeId(typeId);
		vo.setStatus(1);
		List<AdVo> list = adDao.findByParams(vo);
		list.forEach(c->{
			c.setPictureUrl(pathConvert.getUrl(c.getPictureUrl()));
		});
		return list;
	}


	@Override
	@Transactional
	public void save(Ad ad) {
		ad.setPictureUrl(pathConvert.saveFileFromTemp(ad.getPictureUrl(),folderName));
		// 图片处理
		if (ad.getId() != null) {
			ad.setUpdateTime(new Date());
			ad.setUpdateMem(0);
			update(ad);
		} else {
			ad.setStatus(1);
			ad.setCreateTime(new Date());
			ad.setCreateMem(0);
			create(ad);
		}
	}

	@Override
	@Transactional
	public void changeStatus(Integer id, Integer status) {
		Ad ad = new Ad();
		ad.setStatus(status);
		ad.setId(id);
		update(ad);
	}

	@Override
	public Ad findById(int id) {
		Ad ad = super.findById(id);
		ad.setPictureUrl(pathConvert.getUrl(ad.getPictureUrl()));
		return ad;
	}

	@Override
	public ICommonDao<Ad> getDao() {
		return adDao;
	}

}
