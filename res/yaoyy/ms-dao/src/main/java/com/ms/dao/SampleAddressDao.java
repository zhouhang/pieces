package com.ms.dao;

import com.ms.dao.model.SampleAddress;
import com.ms.dao.vo.SampleAddressVo;
import com.ms.dao.config.AutoMapper;

import java.util.List;
@AutoMapper
public interface SampleAddressDao extends ICommonDao<SampleAddress>{

    public List<SampleAddressVo> findByParams(SampleAddressVo sampleAddressVo);

}
