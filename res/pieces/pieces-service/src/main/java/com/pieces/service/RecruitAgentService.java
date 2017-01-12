package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.RecruitAgent;
import com.pieces.dao.vo.RecruitAgentVo;

public interface RecruitAgentService extends ICommonService<RecruitAgent>{

    public PageInfo<RecruitAgentVo> findByParams(RecruitAgentVo recruitAgentVo, Integer pageNum, Integer pageSize);
}
