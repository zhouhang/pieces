package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.LogisticalTraceDao;
import com.pieces.dao.model.LogisticalTrace;
import com.pieces.dao.vo.LogisticalTraceVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.LogisticalTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LogisticalTraceServiceImpl  extends AbsCommonService<LogisticalTrace> implements LogisticalTraceService{

	@Autowired
	private LogisticalTraceDao logisticalTraceDao;

	@Autowired
	private KdApiService kdApiService;


	@Override
	public PageInfo<LogisticalTraceVo> findByParams(LogisticalTraceVo logisticalTraceVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<LogisticalTraceVo>  list = logisticalTraceDao.findByParams(logisticalTraceVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public List<LogisticalTraceVo> findByVo(LogisticalTraceVo logisticalTraceVo) {
		List<LogisticalTraceVo>  list = logisticalTraceDao.findByParams(logisticalTraceVo);
		//如果数据库中数据为空，调用api去查询
		if(list.size()==0){
			try {
				list=kdApiService.getOrderTraces(logisticalTraceVo.getShipperCode(),logisticalTraceVo.getLogisticCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	@Override
	public ICommonDao<LogisticalTrace> getDao() {
		return logisticalTraceDao;
	}

}
