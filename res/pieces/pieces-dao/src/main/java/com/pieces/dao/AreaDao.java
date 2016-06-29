package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Area;

import java.util.List;

public interface AreaDao extends ICommonDao<Area>{
    public List<Area> findByParent(Integer parentId);

    public List<Area> findByLevel(Integer level);

    public PageInfo<Area> findByPage(Integer level,Integer pageNum,Integer pageSize);

}