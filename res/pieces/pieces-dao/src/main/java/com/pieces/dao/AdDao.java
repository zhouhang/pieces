package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.Ad;
import com.pieces.dao.vo.AdVo;

import java.util.List;

@AutoMapper
public interface AdDao extends ICommonDao<Ad>{


    public List<AdVo> findByParam (AdVo adVo);

    public List<AdVo> findByType(Integer typeId);

}
