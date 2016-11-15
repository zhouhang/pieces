package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.UserQualification;
import com.pieces.dao.vo.UserQualificationVo;

import java.util.List;
@AutoMapper
public interface UserQualificationDao extends ICommonDao<UserQualification>{

    public List<UserQualificationVo> findByParams(UserQualificationVo userQualificationVo);

}
