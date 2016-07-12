package com.pieces.service.impl;


import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.RoleDao;
import com.pieces.dao.model.Role;
import com.pieces.dao.model.RoleMember;
import com.pieces.dao.model.RoleResources;
import com.pieces.dao.vo.RoleVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.RoleMemberService;
import com.pieces.service.RoleResourcesService;
import com.pieces.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
    public PageInfo<Role> findByCondition(RoleVo memberVo, Integer pageNum, Integer pageSize) {
        return roleDao.findByCondition(memberVo,pageNum,pageSize);
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
