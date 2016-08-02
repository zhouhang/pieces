package com.pieces.service.impl;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import com.pieces.dao.EnquiryCommoditysDao;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.CategoryVo;
import com.pieces.service.CategoryService;
import com.pieces.service.CommoditySearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.CommodityDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVO;
import com.pieces.service.AbsCommonService;
import com.pieces.service.CommodityService;
import com.pieces.service.utils.ImageUtil;
import com.pieces.service.vo.CropInfo;
import com.pieces.service.vo.CropResult;
import com.pieces.tools.bean.FileBo;
import com.pieces.tools.upload.DefaultUploadFile;

/**
 * Author: koabs
 * 7/12/16.
 */
@Service
public class CommodityServiceImpl  extends AbsCommonService<Commodity> implements CommodityService {

    Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);

    @Autowired
    CommodityDao commodityDao;

    @Autowired
    private DefaultUploadFile defaultUploadFile;
    @Autowired
    private CommoditySearchService commoditySearchService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EnquiryCommoditysDao enquiryCommoditysDao;

    @Override
    public ICommonDao<Commodity> getDao() {
        return commodityDao;
    }

    @Override
    @Transactional
    public void saveOrUpdate(Commodity commodity) {
        if(commodity.getId()!= null) {
            commodityDao.update(commodity);
        } else {
            commodity.setCreateTime(new Date());
            commodityDao.create(commodity);
        }
        commoditySearchService.save(commodity);
    }


    @Override
    public PageInfo<CommodityVO> query(CommodityVO commodity, int pageNum, int pageSize) {
        return commodityDao.findByParam(commodity, pageNum, pageSize);
    }
    
    @Override
    public List<Commodity> queryNoPage(CommodityVO commodity) {
        return commodityDao.findByParamNoPage(commodity);
    }

    @Override
    public PageInfo<CommodityVO> findVoByPage(int pageNum, int pageSize) {
        PageInfo<CommodityVO> pageInfo = commodityDao.findVoByPage(pageNum,pageSize);
        return pageInfo;
    }

    @Override
    public CropResult uploadImage(MultipartFile img) {
        if (img.getSize()/(1024*1024) >= 2) {
            return  CropResult.error("上传的图片大小不能超过2M");
        }
        try {
            FileBo fileBo = defaultUploadFile.uploadFile(img.getOriginalFilename(), img.getInputStream());
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(fileBo.getFile()));
            return CropResult.success(fileBo.getPath(),sourceImg.getWidth(),sourceImg.getHeight());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return CropResult.error("图片上传失败");
        }
    }


    @Override
    public CropResult cropImg(CropInfo cropInfo) {
        String basePath = defaultUploadFile.getBasePath();
        String url = defaultUploadFile.getUrl();

        String adder = cropInfo.getImgUrl().replace(url, basePath);
        cropInfo.setImgUrl(adder);

        try {
            return CropResult.success(ImageUtil.cropImg(cropInfo).replace(basePath, url));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return CropResult.error("图片裁剪失败");
        }
    }

    @Override
    public CommodityVO findVoById(Integer id) {
        CommodityVO commodity =  commodityDao.findVoById(id);
        return commodity;
    }

    @Override
    public List<CommodityVO> findVoByIds(Set<Integer> ids) {
        return commodityDao.findVoByIds(ids);
    }


    @Override
    public List<CommodityVO> findCommodityByBreedId(Integer id) {
    	return commodityDao.findCommodityByBreedId(id);
    }

    @Override
    public List<CommodityVO> findFactoryByBreedId(String ids) {
    	return commodityDao.findFactoryByBreedId(ids);
    }

    @Override
    public List<CommodityVO> findStandardByBreedId(String ids) {
    	return commodityDao.findStandardByBreedId(ids);
    }

    @Override
    public List<CommodityVO> featured(User user, Integer breedId, Integer categoryId) {
//        *  1、用户曾经询价过的品种，取询价次数最多的 5 个，如果不足 5 个，用第 2条规则填补；
//        *  2、当前商品同品种最新发布的前 5 个商品，如果不足 5 个，用第3条规则填补；
//        *  3、当前商品同分类最新发布的前 5 个商品。
        List<CommodityVO> list = new ArrayList<>();
        // TODO: 用户询价商品.
        if (user != null) {
            List<EnquiryCommoditys> commodityses = enquiryCommoditysDao.findCommoditysByUser(String.valueOf(user.getId()));

            if (!commodityses.isEmpty()){
                String ids = "";
                for (EnquiryCommoditys vo : commodityses) {
                    ids += "'" + vo.getId() + "',";
                }

                ids = ids.substring(0, ids.length()-1);
                List<CommodityVO> commodityVOs = commodityDao.findByIds(ids);
                list.addAll(commodityVOs);
            }

        }

        if (list.size() < 5) {
            CommodityVO param = new CommodityVO();
            param.setCategoryId(breedId);
            PageInfo<CommodityVO> pageInfo = commodityDao.findByParam(param, 1, 5-list.size());
            list.addAll(pageInfo.getList());
        }
        if (list.size() < 5) {
            // 找到的商品数量不足从同分类的商品中找.
            // 根据品种ID 找到所属的类别
            // 根据类别ID 找到下面的品种, 判处当前的品种.找前几条和上面查出来的凑足5条.
                CategoryVo categoryVo = new CategoryVo();
                categoryVo.setParentId(String.valueOf(categoryId));
                List<CategoryVo> categoryVos = categoryService.findBreed(categoryVo,1,5).getList();
                String categoryIds = "";
                for (CategoryVo vo : categoryVos) {
                    if(!vo.getId().equals(breedId)){
                        categoryIds += "'" + vo.getId() + "',";
                    }
                }
                categoryIds = categoryIds.substring(0, categoryIds.length()-1);
                CommodityVO commodityVO = new CommodityVO();
                commodityVO.setCategoryIds(categoryIds);
                List<CommodityVO> listc = commodityDao.findByParam(commodityVO, 1,5-list.size()).getList();
                // 整合数据
                list.addAll(listc);
                list = list.subList(0, 5);
        }

        return list;
    }
}
