package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.PickDao;
import com.ms.dao.model.Pick;
import com.ms.dao.vo.PickVo;
import com.ms.service.PickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PickServiceImpl  extends AbsCommonService<Pick> implements PickService{

	@Autowired
	private PickDao pickDao;


	@Override
	public PageInfo<PickVo> findByParams(PickVo pickVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<PickVo>  list = pickDao.findByParams(pickVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<Pick> getDao() {
		return pickDao;
	}

}
