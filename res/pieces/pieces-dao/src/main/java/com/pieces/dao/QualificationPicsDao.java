package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.QualificationPics;
import com.pieces.dao.vo.QualificationPicsVo;

import java.util.List;
@AutoMapper
public interface QualificationPicsDao extends ICommonDao<QualificationPics>{

    public List<QualificationPicsVo> findByParams(QualificationPicsVo qualificationPicsVo);

}
