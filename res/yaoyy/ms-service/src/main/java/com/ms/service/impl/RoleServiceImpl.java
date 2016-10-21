package com.ms.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.RoleDao;
import com.ms.dao.model.Role;
import com.ms.dao.vo.RoleVo;
import com.ms.service.RoleMemberService;
import com.ms.service.RoleResourcesService;
import com.ms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wangbin on 2016/7/8.
 */
@Transactional(readOnly = true)
@Service
public class RoleServiceImpl extends AbsCommonService<Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMemberService roleMemberService;
    @Autowired
    private RoleResourcesService roleResourcesService;


    @Override
    public ICommonDao<Role> getDao() {
        return roleDao;
    }

    @Override
    public PageInfo<Role> findByCondition(RoleVo roleVo, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list =  roleDao.findByCondition(roleVo);
        PageInfo page = new PageInfo(list);
        return page;
    }


    @Override
    @Transactional
    public int add(Role role) {
        role.setCreateDate(new Date());
        create(role);
        return role.getId();
    }

    @Override
    @Transactional
    public void deleteRole(Integer roleId) {
        roleMemberService.deleteByRole(roleId);
        roleResourcesService.deleteByRoleId(roleId);
        roleDao.deleteById(roleId);
    }
}
