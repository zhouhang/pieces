package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.UserCertification;
import com.pieces.dao.vo.UserCertificationVo;

public interface UserCertificationService extends ICommonService<UserCertification>{

    public PageInfo<UserCertificationVo> findByParams(UserCertificationVo userCertificationVo,Integer pageNum,Integer pageSize);
}
