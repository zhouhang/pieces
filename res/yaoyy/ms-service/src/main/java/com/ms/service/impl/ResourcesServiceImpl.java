package com.ms.service.impl;

import com.ms.dao.ICommonDao;
import com.ms.dao.ResourcesDao;
import com.ms.dao.model.Resources;
import com.ms.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangbin on 2016/7/8.
 */
@Service
public class ResourcesServiceImpl extends AbsCommonService<Resources> implements ResourcesService {

    @Autowired
    private ResourcesDao resourcesDao;

    @Override
    public ICommonDao<Resources> getDao() {
        return resourcesDao;
    }


}
