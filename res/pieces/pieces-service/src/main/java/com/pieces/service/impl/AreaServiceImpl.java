package com.pieces.service.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.AreaDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.Area;
import com.pieces.service.AbsCommonService;
import com.pieces.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangbin on 2016/6/27.
 */
@Service
public class AreaServiceImpl extends AbsCommonService<Area> implements AreaService {

    @Autowired
    private  AreaDao areaDao;

    @Override
    public List<Area> findByParent(Integer parentId) {
        return areaDao.findByParent(parentId);
    }

    @Override
    public List<Area> findByLevel(Integer level) {
        return areaDao.findByLevel(level);
    }

    @Override
    public PageInfo<Area> findByPage(Integer level, Integer pageNum, Integer pageSize) {
        return areaDao.findByPage(level, pageNum, pageSize);
    }


    @Override
    public ICommonDao<Area> getDao() {
        return areaDao;
    }



}
