package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.RecruitAgent;
import com.pieces.dao.vo.RecruitAgentVo;

import java.util.List;

public interface RecruitAgentService extends ICommonService<RecruitAgent>{

    public PageInfo<RecruitAgentVo> findByParams(RecruitAgentVo recruitAgentVo, Integer pageNum, Integer pageSize);
    public RecruitAgentVo findVoById(Integer id);

    /**
     * 未处理数目
     * @return
     */
    public Integer getNotHandleCount();


    public List<Integer> getNotHandleIds();
}
