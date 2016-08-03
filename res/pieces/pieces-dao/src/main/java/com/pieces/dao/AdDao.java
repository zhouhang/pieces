package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Ad;
import com.pieces.dao.vo.AdVo;

public interface AdDao extends ICommonDao<Ad>{


    public PageInfo<AdVo> findByParam (AdVo adVo, int pageNum, int pageSize);

}
