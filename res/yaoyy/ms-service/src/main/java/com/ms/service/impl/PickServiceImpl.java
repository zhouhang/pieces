package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.PickDao;
import com.ms.dao.model.Pick;
import com.ms.dao.vo.PickCommodityVo;
import com.ms.dao.vo.PickVo;
import com.ms.service.PickCommodityService;
import com.ms.service.PickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PickServiceImpl  extends AbsCommonService<Pick> implements PickService{

	@Autowired
	private PickDao pickDao;


	@Autowired
	private PickCommodityService pickCommodityService;



	@Override
	public PageInfo<PickVo> findByParams(PickVo pickVo,Integer pageNum,Integer pageSize) {
		pageNum = pageNum==null?1:pageNum;
		pageSize = pageSize==null?10:pageSize;
        PageHelper.startPage(pageNum, pageSize);
    	List<PickVo>  list = pickDao.findByParams(pickVo);
		list.forEach(p->{
			List<PickCommodityVo> pickCommodityVos=pickCommodityService.findByPickId(p.getId());
			float total=0;

			for(PickCommodityVo vo :pickCommodityVos){
				total+=vo.getTotal();
			}
			p.setTotal(total);

			p.setPickCommodityVoList(pickCommodityVos);
		});
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public PickVo findVoById(Integer id) {
		PickVo pickVo=pickDao.findVoById(id);
		List<PickCommodityVo> pickCommodityVos=pickCommodityService.findByPickId(id);
		float total=0;

		for(PickCommodityVo vo :pickCommodityVos){
			total+=vo.getTotal();
		}
		pickVo.setTotal(total);

		pickVo.setPickCommodityVoList(pickCommodityVos);

		return pickVo;
	}


	@Override
	public ICommonDao<Pick> getDao() {
		return pickDao;
	}



}
