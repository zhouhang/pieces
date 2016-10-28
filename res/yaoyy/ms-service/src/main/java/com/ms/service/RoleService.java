package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Role;
import com.ms.dao.vo.RoleVo;

/**
 * Created by wangbin on 2016/7/8.
 */
public interface RoleService extends ICommonService<Role>{


    PageInfo<Role> findByCondition(RoleVo memberVo, Integer pageNum, Integer pageSize);

    public int add(Role role);

    public int update(Role role);

    public void deleteRole(Integer roleId);
}
