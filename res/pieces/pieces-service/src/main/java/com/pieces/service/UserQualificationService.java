package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.UserQualification;
import com.pieces.dao.vo.UserQualificationVo;

import java.util.List;

public interface UserQualificationService extends ICommonService<UserQualification>{

    public PageInfo<UserQualificationVo> findByParams(UserQualificationVo userQualificationVo,Integer pageNum,Integer pageSize);

    public List<UserQualificationVo> findAll(UserQualificationVo userQualificationVo);

    public UserQualificationVo findByCondition(UserQualificationVo userQualificationVo);
}
