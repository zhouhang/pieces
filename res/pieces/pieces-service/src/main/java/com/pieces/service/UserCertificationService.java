package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.UserCertification;
import com.pieces.dao.vo.UserCertificationVo;

import java.util.List;

public interface UserCertificationService extends ICommonService<UserCertification>{

    public PageInfo<UserCertificationVo> findByParams(UserCertificationVo userCertificationVo,Integer pageNum,Integer pageSize);

    /**
     * 查询指定的资质
     * @param userCertificationVo
     * @return
     */
    public UserCertificationVo  findAll(UserCertificationVo userCertificationVo);


}
