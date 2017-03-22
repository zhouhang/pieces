package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.SeoSetting;
import com.pieces.dao.vo.SeoSettingVo;

public interface SeoSettingService extends ICommonService<SeoSetting>{

    public PageInfo<SeoSettingVo> findByParams(SeoSettingVo seoSettingVo,Integer pageNum,Integer pageSize);

    public SeoSettingVo findByType(Integer type);

    public void save(SeoSettingVo seoSettingVo);
}
