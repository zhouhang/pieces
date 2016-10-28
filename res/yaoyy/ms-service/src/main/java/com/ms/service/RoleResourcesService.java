package com.ms.service;

import com.ms.dao.model.RoleResources;

import java.util.List;

/**
 * Created by wangbin on 2016/7/8.
 */
public interface RoleResourcesService extends ICommonService<RoleResources>{


    int deleteByRoleId(int roleId);

    void updateRoleResources(Integer roleId, Integer[] resourcesIds,String roleName);


    List<Integer> findResourcesByRole(Integer roleId);


}
