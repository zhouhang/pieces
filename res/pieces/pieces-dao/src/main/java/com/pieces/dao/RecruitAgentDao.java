package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.RecruitAgent;
import com.pieces.dao.vo.RecruitAgentVo;

import java.util.List;
@AutoMapper
public interface RecruitAgentDao extends ICommonDao<RecruitAgent>{

    public List<RecruitAgentVo> findByParams(RecruitAgentVo recruitAgentVo);

}
