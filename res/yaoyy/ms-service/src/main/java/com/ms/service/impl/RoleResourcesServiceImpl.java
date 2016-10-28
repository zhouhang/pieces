package com.ms.service.impl;

import com.ms.dao.ICommonDao;
import com.ms.dao.RoleResourcesDao;
import com.ms.dao.model.Role;
import com.ms.dao.model.RoleResources;
import com.ms.service.RoleResourcesService;
import com.ms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangbin on 2016/7/8.
 */
@Service
@Transactional(readOnly = true)
public class RoleResourcesServiceImpl extends AbsCommonService<RoleResources> implements RoleResourcesService{


    @Autowired
    private RoleResourcesDao roleResourcesDao;
    @Autowired
    private RoleService roleService;

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
    public void updateRoleResources(Integer roleId, Integer[] resourcesIds,String roleName) {

        if(roleId==null){
            Role role = new Role();
            role.setCreateDate(new Date());
            role.setName(roleName);
            roleService.create(role);
            roleId = role.getId();
        }else{
            //修改角色名称
            Role role = new Role();
            role.setId(roleId);
            role.setName(roleName);
            roleService.update(role);
        }


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
        if(roleId==null){
            return resourceIds;
        }
        List<RoleResources> roleResourcesList = roleResourcesDao.findByRoleId(roleId);
        for(RoleResources roleResources : roleResourcesList){
            resourceIds.add(roleResources.getResourcesId());
        }
        return resourceIds;
    }


}
