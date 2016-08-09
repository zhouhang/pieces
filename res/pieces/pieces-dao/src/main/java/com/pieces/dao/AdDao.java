package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Ad;
import com.pieces.dao.vo.AdVo;

import java.util.List;

public interface AdDao extends ICommonDao<Ad>{


    public PageInfo<AdVo> findByParam (AdVo adVo, int pageNum, int pageSize);

    public List<AdVo> findByType(Integer typeId);

}
