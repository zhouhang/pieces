package com.pieces.service.impl;

import com.pieces.dao.AreaDao;
import com.pieces.dao.model.Area;
import com.pieces.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangbin on 2016/6/27.
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> findByParent(Integer parentId) {
        return areaDao.findByParent(parentId);
    }

    @Override
    public List<Area> findByLevel(Integer level) {
        return areaDao.findByLevel(level);
    }

    @Override
    public Area findById(Integer id) {
        return areaDao.findById(id);
    }

}
