package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.SeoSetting;
import com.pieces.dao.vo.SeoSettingVo;

import java.util.List;
@AutoMapper
public interface SeoSettingDao extends ICommonDao<SeoSetting>{

    public List<SeoSettingVo> findByParams(SeoSettingVo seoSettingVo);
    public SeoSettingVo findByType(Integer type);

}
