package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.CommodityDao;
import com.ms.dao.model.Commodity;
import com.ms.dao.model.Gradient;
import com.ms.dao.vo.CommodityVo;
import com.ms.service.CommoditySearchService;
import com.ms.service.CommodityService;
import com.ms.service.GradientService;
import com.ms.tools.ClazzUtil;
import com.ms.tools.upload.PathConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CommodityServiceImpl extends AbsCommonService<Commodity> implements CommodityService {

    @Autowired
    private CommodityDao commodityDao;

    @Autowired
    private GradientService gradientService;

    @Autowired
    private PathConvert pathConvert;

    //@Autowired
    //private CommoditySearchService commoditySearchService;

    /**
     * 商品图片保存路径
     */
    private String folderName = "commodity/";

    @Override
    public PageInfo<CommodityVo> findByParams(CommodityVo commodityVo, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            pageNum = 1;
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<CommodityVo> list = commodityDao.findByParams(commodityVo);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public List<CommodityVo> findByIds(String ids) {
        List<Integer> list = new ArrayList<>();
        for(String id :ids.split(",")){
            list.add(Integer.parseInt(id));
        }
        List<CommodityVo> commodities= commodityDao.findByIds(list);
        commodities.forEach(c->{
            c.setPictureUrl(pathConvert.getUrl(c.getPictureUrl()));
            List<Gradient> gradients = gradientService.findByCommodityId(c.getId());
            c.setGradient(gradients);
            if(c.getMark()==1){
                c.setPrice(c.getGradient().get(0).getPrice());
            }
        });
        return commodities;
    }




    @Override
    public ICommonDao<Commodity> getDao() {
        return commodityDao;
    }

    @Override
    @Transactional
    public void save(CommodityVo commodity) {

        commodity.setPictureUrl(pathConvert.saveFileFromTemp(commodity.getPictureUrl(),folderName));
        if (commodity.getId() == null) {
            commodity.setCreateTime(new Date());
            commodity.setUpdateTime(new Date());
            commodityDao.create(commodity);
        } else {
            commodity.setUpdateTime(new Date());
            commodityDao.update(commodity);
        }

        if (commodity.getGradient() != null) {
            commodity.getGradient().forEach(gradient -> {
                gradient.setCreateTime(new Date());
                gradient.setUpdateTime(new Date());
                gradient.setCommodityId(commodity.getId());
            });
            gradientService.update(commodity.getGradient());
        }
        //commoditySearchService.save(commodity);
    }

    @Override
    public CommodityVo findById(Integer id) {
        CommodityVo vo = new CommodityVo();
        vo.setId(id);
        List<CommodityVo> commodityVos=commodityDao.findByParams(vo);
        if(commodityVos.size()==0){
            return null;
        }
        vo = commodityVos.get(0);
        List<Gradient> gradients = gradientService.findByCommodityId(id);
        vo.setGradient(gradients);
        vo.setPictureUrl(pathConvert.getUrl(vo.getPictureUrl()));
        return vo;
    }

    @Override
    public List<Commodity> searchComodity(CommodityVo commodityVo) {
        return commodityDao.searchComodity(commodityVo);
    }

    @Override
    public List<Commodity> findByName(String name) {
        return commodityDao.findByName(name);
    }

    @Override
    public PageInfo<CommodityVo> findVoByPage(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommodityVo> list = commodityDao.findVoByPage();
        list.forEach(c->{
            c.setPictureUrl(pathConvert.getUrl(c.getPictureUrl()));
        });
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<CommodityVo> findByCategoryId(Integer id) {
        return commodityDao.findByCategoryId(id);
    }

    @Override
    @Transactional
    public void updateStatusByCategoryId(Commodity commodity) {
       commodityDao.updateStatusByCategoryId(commodity);
    }

    @Override
    @Transactional
    public int deleteById(int id) {
        gradientService.deleteByCommodityId(id);
        //commoditySearchService.deleteByCommodityId(id);
        return super.deleteById(id);
    }


}
