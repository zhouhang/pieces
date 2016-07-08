package com.pieces.service.impl;

import com.pieces.dao.ICommonDao;
import com.pieces.dao.RoleDao;
import com.pieces.dao.model.Role;
import com.pieces.service.AbsCommonService;
import com.pieces.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangbin on 2016/7/8.
 */
public class RoleServiceImpl extends AbsCommonService<Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public ICommonDao<Role> getDao() {
        return roleDao;
    }
}
