package com.pieces.dao;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.Area;

@AutoMapper
public interface AreaDao extends ICommonDao<Area>{
    public List<Area> findByParent(Integer parentId);

    public List<Area> findByLevel(Integer level);
    
    public Area findByCode(String areacode);


    public Area findParentsByCode(Integer id);
}