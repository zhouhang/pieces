package com.pieces.dao;

import com.pieces.dao.model.Area;

import java.util.List;

public interface AreaDao {
    public List<Area> findByParent(Integer parentId);

    public List<Area> findByLevel(Integer level);

    public Area findById(Integer id);
}