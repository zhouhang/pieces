package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Role;
import com.pieces.dao.vo.RoleVo;

/**
 * Created by wangbin on 2016/7/8.
 */
public interface RoleService extends ICommonService<Role>{


    PageInfo<Role> findByCondition(RoleVo memberVo, Integer pageNum, Integer pageSize);

    public int add(Role role);


}
