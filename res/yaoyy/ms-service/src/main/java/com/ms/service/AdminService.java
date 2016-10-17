package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Admin;
import com.ms.dao.vo.AdminVo;

public interface AdminService extends ICommonService<Admin>{

    public PageInfo<AdminVo> findByParams(AdminVo adminVo,Integer pageNum,Integer pageSize);
}
