package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.AdminDao;
import com.ms.dao.model.Admin;
import com.ms.dao.vo.AdminVo;
import com.ms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl  extends AbsCommonService<Admin> implements AdminService{

	@Autowired
	private AdminDao adminDao;


	@Override
	public PageInfo<AdminVo> findByParams(AdminVo adminVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<AdminVo>  list = adminDao.findByParams(adminVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<Admin> getDao() {
		return adminDao;
	}

}
