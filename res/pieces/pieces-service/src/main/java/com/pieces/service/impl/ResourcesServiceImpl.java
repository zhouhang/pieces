package com.pieces.service.impl;

import com.pieces.dao.ICommonDao;
import com.pieces.dao.ResourcesDao;
import com.pieces.dao.model.Resources;
import com.pieces.service.AbsCommonService;
import com.pieces.service.ResourcesService;
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
