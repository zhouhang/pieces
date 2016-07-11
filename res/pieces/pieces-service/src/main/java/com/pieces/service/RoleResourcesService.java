package com.pieces.service;

import com.pieces.dao.model.RoleResources;

import java.util.List;

/**
 * Created by wangbin on 2016/7/8.
 */
public interface RoleResourcesService extends ICommonService<RoleResources>{


    int deleteByRoleId(int roleId);

    void updateRoleResources(Integer roleId, Integer[] resourcesIds);


    List<Integer> findResourcesByRole(Integer roleId);


}
