package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.Admin;
import com.ms.dao.vo.AdminVo;

import java.util.List;
@AutoMapper
public interface AdminDao extends ICommonDao<Admin>{

    public List<AdminVo> findByParams(AdminVo adminVo);

}
