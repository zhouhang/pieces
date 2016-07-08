package com.pieces.service.impl;

import com.pieces.dao.ICommonDao;
import com.pieces.dao.RoleResourcesDao;
import com.pieces.dao.model.RoleResources;
import com.pieces.service.AbsCommonService;
import com.pieces.service.RoleResourcesService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangbin on 2016/7/8.
 */
public class RoleResourcesServiceImpl extends AbsCommonService<RoleResources> implements RoleResourcesService{
    @Autowired
    private RoleResourcesDao roleResourcesDao;

    @Override
    public ICommonDao<RoleResources> getDao() {
        return roleResourcesDao;
    }
}
