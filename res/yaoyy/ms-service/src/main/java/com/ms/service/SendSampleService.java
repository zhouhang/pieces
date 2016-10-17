package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.SendSample;
import com.ms.dao.vo.SendSampleVo;

public interface SendSampleService extends ICommonService<SendSample>{

    public PageInfo<SendSampleVo> findByParams(SendSampleVo sendSampleVo,Integer pageNum,Integer pageSize);
}
