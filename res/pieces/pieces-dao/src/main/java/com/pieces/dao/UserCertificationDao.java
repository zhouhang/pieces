package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.UserCertification;
import com.pieces.dao.vo.UserCertificationVo;

import java.util.List;
@AutoMapper
public interface UserCertificationDao extends ICommonDao<UserCertification>{

    public List<UserCertificationVo> findByParams(UserCertificationVo userCertificationVo);

    public Integer updateByRecordId(UserCertificationVo userCertificationVo);



}
