package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.SampleAddress;
import com.ms.dao.vo.SampleAddressVo;

public interface SampleAddressService extends ICommonService<SampleAddress>{

    public PageInfo<SampleAddressVo> findByParams(SampleAddressVo sampleAddressVo,Integer pageNum,Integer pageSize);
    public SampleAddressVo findBySendId(Integer sendId);
    public void save(SampleAddress address);
}
