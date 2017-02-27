package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.CommodityCollectDao;
import com.pieces.dao.model.CommodityCollect;
import com.pieces.dao.vo.CommodityCollectVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.CommodityCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommodityCollectServiceImpl  extends AbsCommonService<CommodityCollect> implements CommodityCollectService{

	@Autowired
	private CommodityCollectDao commodityCollectDao;


	@Override
	public PageInfo<CommodityCollectVo> findByParams(CommodityCollectVo commodityCollectVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<CommodityCollectVo>  list = commodityCollectDao.findByParams(commodityCollectVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<CommodityCollect> getDao() {
		return commodityCollectDao;
	}


	@Override
	public List<CommodityCollectVo> findByUser(Integer userId) {
		return commodityCollectDao.findByUser(userId);
		
	}


	@Override
	@Transactional
	public void deleteCollect(CommodityCollect commodityCollect) {
		commodityCollectDao.deleteCollect(commodityCollect);
		
	}

	@Override
	public boolean check(Integer cid, Integer userId) {
		CommodityCollectVo commodityCollectVo = new CommodityCollectVo();
		commodityCollectVo.setCommodityId(cid);
		commodityCollectVo.setUserId(userId);
		PageInfo<CommodityCollectVo> ccp = findByParams(commodityCollectVo,1, 10);
		return ccp.getSize() == 0?false:true;
	}
}
