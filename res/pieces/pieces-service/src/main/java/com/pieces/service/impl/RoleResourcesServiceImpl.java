package com.pieces.service.impl;

import com.pieces.dao.ICommonDao;
import com.pieces.dao.RoleResourcesDao;
import com.pieces.dao.model.RoleResources;
import com.pieces.service.AbsCommonService;
import com.pieces.service.RoleResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangbin on 2016/7/8.
 */
@Service
@Transactional(readOnly = true)
public class RoleResourcesServiceImpl extends AbsCommonService<RoleResources> implements RoleResourcesService{


    @Autowired
    private RoleResourcesDao roleResourcesDao;

    @Override
    public ICommonDao<RoleResources> getDao() {
        return roleResourcesDao;
    }


    @Override
    @Transactional
    public int deleteByRoleId(int roleId) {
        return roleResourcesDao.deleteByRoleId(roleId);
    }

    @Override
    @Transactional
    public void updateRoleResources(Integer roleId, Integer[] resourcesIds) {
        //先删除角色下所有的权限再添加
        deleteByRoleId(roleId);
        if(resourcesIds==null||resourcesIds.length==0){
            return;
        }
        for(Integer id : resourcesIds){
            RoleResources roleResources = new RoleResources();
            roleResources.setResourcesId(id);
            roleResources.setRoleId(roleId);
            create(roleResources);
        }
    }

    @Override
    public List<Integer> findResourcesByRole(Integer roleId) {
        List<Integer> resourceIds = new ArrayList<>();
        List<RoleResources> roleResourcesList = roleResourcesDao.findByRole(roleId);
        for(RoleResources roleResources : roleResourcesList){
            resourceIds.add(roleResources.getResourcesId());
        }
        return resourceIds;
    }


}
