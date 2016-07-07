package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Area;

import java.util.List;

/**
 * Created by wangbin on 2016/6/27.
 */
public interface AreaService extends ICommonService<Area>{

    public List<Area> findByParent(Integer parentId);

    public List<Area> findByLevel(Integer level);
    
    public Area findByCode(String areacode);

    public PageInfo<Area> findByPage(Integer level, Integer pageNum, Integer pageSize);

}
