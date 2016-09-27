package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.AdDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.Ad;
import com.pieces.dao.vo.AdVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.AdService;
import com.pieces.service.enums.PathEnum;
import com.pieces.tools.utils.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wangbin on 2016/8/3.
 */
@Service
public class AdServiceImpl extends AbsCommonService<Ad> implements AdService{

    private String param = "pictureUrl";

    @Autowired
    private AdDao adDao;

    @Override
    public ICommonDao<Ad> getDao() {
        return adDao;
    }


    @Transactional
    @Override
    public Ad createAd(Ad ad) {
        if(StringUtils.isNotBlank(ad.getPictureUrl())){
            ad.setPictureUrl(FileUtil.saveFileFromTemp(ad.getPictureUrl(),PathEnum.WOOL.getValue()));
        }
        ad.setCreateTime(new Date());
        create(ad);
        return ad;
    }

    @Transactional
    @Override
    public int update(Ad ad) {
        if("none".equals(ad.getPictureUrl())){
            ad.setPictureUrl("");
        }
        if(StringUtils.isNotBlank(ad.getPictureUrl())){
            ad.setPictureUrl(FileUtil.saveFileFromTemp(ad.getPictureUrl(), PathEnum.WOOL.getValue()));
        }
        return super.update(ad);
    }

    @Override
    public PageInfo<AdVo> findByParam(AdVo adVo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AdVo> list = adDao.findByParam(adVo);
        list = FileUtil.convertAbsolutePathToUrl(list,param);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public List<AdVo> findByType(Integer typeId) {
        return FileUtil.convertAbsolutePathToUrl(adDao.findByType(typeId),param);
    }

    @Override
    public Ad findById(int id) {
        return (Ad) FileUtil.convertAbsolutePathToUrl(super.findById(id), param);
    }

    @Override
    public PageInfo<Ad> find(int pageNum, int pageSize) {
        PageInfo<Ad> pageInfo = super.find(pageNum, pageSize);
        List<Ad> list = pageInfo.getList();
        pageInfo.setList(FileUtil.convertAbsolutePathToUrl(list,param));
        return pageInfo;
    }
}
