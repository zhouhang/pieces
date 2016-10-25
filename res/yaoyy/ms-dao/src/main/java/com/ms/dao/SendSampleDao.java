package com.ms.dao;

import com.ms.dao.model.SendSample;
import com.ms.dao.vo.SendSampleVo;
import com.ms.dao.config.AutoMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
@AutoMapper
public interface SendSampleDao extends ICommonDao<SendSample>{

    public List<SendSampleVo> findByParams(SendSampleVo sendSampleVo);

    public SendSampleVo findDetailById(int id);

    public List<SendSampleVo> findByCommodityId(@Param(value = "userId") Integer userId,@Param(value="ids")String ids);

}
