package com.pieces.service;

import com.pieces.dao.model.Area;
import com.pieces.dao.model.User;

import java.util.List;

/**
 * Created by wangbin on 2016/6/27.
 */
public interface AreaService {

    public List<Area> findByParent(Integer parentId);

    public List<Area> findByLevel(Integer level);

    public Area findById(Integer id);

}
