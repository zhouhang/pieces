package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Ad;
import com.ms.dao.vo.AdVo;

import java.util.List;

public interface AdService extends ICommonService<Ad>{

    public PageInfo<AdVo> findByParams(AdVo adVo,Integer pageNum,Integer pageSize);

    /**
     * 根据广告类型id 获取广告
     * 只会有启用状态的广告
     * @param typeId
     * @return
     */
    public List<AdVo> findByType(Integer typeId);

    public void save(Ad ad);

    /**
     * 改变广告状态
     * @param id
     * @param status
     */
    public void changeStatus(Integer id, Integer status);
}
