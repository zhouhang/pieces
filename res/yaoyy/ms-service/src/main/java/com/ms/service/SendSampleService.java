package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.SendSample;
import com.ms.dao.vo.SendSampleVo;

import java.util.List;

public interface SendSampleService extends ICommonService<SendSample>{

    public PageInfo<SendSampleVo> findByParams(SendSampleVo sendSampleVo,Integer pageNum,Integer pageSize);

    public SendSampleVo findDetailById(Integer id);

    public List<SendSampleVo> findByCommodityId(int userId,List<Integer> ids);

    public List<SendSampleVo> findByUserId(Integer userId);

    public void save(SendSampleVo sendSampleVo);
}
