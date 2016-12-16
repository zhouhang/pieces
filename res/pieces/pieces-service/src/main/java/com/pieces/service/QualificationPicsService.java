package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.QualificationPics;
import com.pieces.dao.vo.QualificationPicsVo;

public interface QualificationPicsService extends ICommonService<QualificationPics>{

    public PageInfo<QualificationPicsVo> findByParams(QualificationPicsVo qualificationPicsVo,Integer pageNum,Integer pageSize);
}
