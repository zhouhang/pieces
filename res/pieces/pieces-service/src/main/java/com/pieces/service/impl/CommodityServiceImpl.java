package com.pieces.service.impl;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import com.github.pagehelper.PageHelper;
import com.pieces.service.utils.ValidUtils;
import com.pieces.tools.log.api.LogAuditing;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.CommodityDao;
import com.pieces.dao.EnquiryCommoditysDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.CategoryVo;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.CategoryService;
import com.pieces.service.CommoditySearchService;
import com.pieces.service.CommodityService;
import com.pieces.service.enums.PathEnum;
import com.pieces.service.utils.ImageUtil;
import com.pieces.service.vo.CropInfo;
import com.pieces.service.vo.CropResult;
import com.pieces.tools.bean.FileBo;
import com.pieces.tools.upload.TempUploadFile;
import com.pieces.tools.upload.UEditorUploadFile;
import com.pieces.tools.utils.FileUtil;

/**
 * Author: koabs
 * 7/12/16.
 */
@Service
public class CommodityServiceImpl  extends AbsCommonService<Commodity> implements CommodityService {

    public final static String param = "pictureUrl";

    Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);

    @Autowired
    CommodityDao commodityDao;

    @Autowired
    private TempUploadFile tempUploadFile;

    @Autowired
    private UEditorUploadFile uEditorUploadFile;

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
    public Integer saveOrUpdate(Commodity commodity) throws IOException {
        // 商品审计日志

//        commodity.setPictureUrl(commodity.getPictureUrl().replace(defaultUploadFile.getUrl(), ""));
        // 把文件从临时目录保存
        commodity.setPictureUrl(FileUtil.saveFileFromTemp(commodity.getPictureUrl(), PathEnum.COMMODITY.getValue()));
        if(commodity.getId()!= null) {
            LogAuditing.audit(commodityDao.findById(commodity.getId()),commodity,"商品","修改商品");
            commodityDao.update(commodity);
        } else {
            commodity.setCreateTime(new Date());
            commodityDao.create(commodity);
            LogAuditing.audit(null,commodity,"商品","新增商品");
        }
        //如果更改商品状态为不显示则删除索引
        if(commodity.getStatus()==0){
            commoditySearchService.deleteByCommodityId(commodity.getId());
        }else{
            commoditySearchService.save(commodity);
        }
        return commodity.getId();
    }


    @Override
    public PageInfo<CommodityVo> query(CommodityVo commodity, int pageNum, int pageSize) {
        return this.findByParam(commodity, pageNum, pageSize);
    }

    @Override
    @Transactional
    public int deleteById(int id) {
        int record =  super.deleteById(id);
        commoditySearchService.deleteByCommodityId(id);
        return record;
    }

    @Override
    public PageInfo<CommodityVo> findVoByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommodityVo> list = commodityDao.findVoByPage();
        list = FileUtil.convertAbsolutePathToUrl(list,param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public CropResult uploadImage(MultipartFile img) {
        CropResult cropResult = null;
        if(checkImgSize(img))  {
            cropResult = CropResult.error("上传的图片大小不能超过2M");
        } else {
            try {
                FileBo fileBo = tempUploadFile.uploadFile(img.getOriginalFilename(), img.getInputStream());
                BufferedImage sourceImg = ImageIO.read(new FileInputStream(fileBo.getFile()));
                cropResult = CropResult.success(fileBo.getUrl(),sourceImg.getWidth(),sourceImg.getHeight());
            } catch (Exception e) {
                logger.error(e.getMessage());
                cropResult = CropResult.error("图片上传失败");
            }
        }
        return cropResult;
    }

    public boolean checkImgSize(MultipartFile img) {
        return (img.getSize()/(1024*1024) >= 2);
    }

    @Override
    public CropResult uploadUeditorImage(MultipartFile img) {
        if(checkImgSize(img))  {
            return  CropResult.error("上传的图片大小不能超过2M");
        }
        try {
            FileBo fileBo = uEditorUploadFile.uploadFile(img.getOriginalFilename(), img.getInputStream());
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(fileBo.getFile()));
            return CropResult.success(fileBo.getUrl(),sourceImg.getWidth(),sourceImg.getHeight());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return CropResult.error("图片上传失败");
        }
    }


    @Override
    public CropResult cropImg(CropInfo cropInfo) {
        String basePath = tempUploadFile.getBasePath();
        String url = tempUploadFile.getUrl();

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
    public CommodityVo findVoById(Integer id) {
        CommodityVo commodity =  (CommodityVo)FileUtil.convertAbsolutePathToUrl(commodityDao.findVoById(id), param);
        return commodity;
    }

    @Override
    public List<CommodityVo> findVoByIds(Set<Integer> ids) {
        return FileUtil.convertAbsolutePathToUrl(commodityDao.findVoByIds(new ArrayList<Integer>(ids))
                ,param);
    }


    @Override
    public List<CommodityVo> findCommodityByBreedId(Integer id) {
    	return FileUtil.convertAbsolutePathToUrl(commodityDao.findCommodityByBreedId(id), param);
    }

    @Override
    public List<CommodityVo> findFactoryByBreedId(String ids) {
        CommodityVo vo = new CommodityVo();
        vo.setBreedIds(ids);
    	return FileUtil.convertAbsolutePathToUrl(commodityDao.findFactoryByBreedId(vo), param);
    }

    @Override
    public List<CommodityVo> findStandardByBreedId(String ids) {
        CommodityVo vo = new CommodityVo();
        vo.setBreedIds(ids);
    	return  FileUtil.convertAbsolutePathToUrl(commodityDao.findStandardByBreedId(vo),param);
    }

    @Override
    public List<CommodityVo> featured(User user, Integer breedId, Integer categoryId) {
//        *  1、用户曾经询价过的品种，取询价次数最多的 5 个，如果不足 5 个，用第 2条规则填补；
//        *  2、当前商品同品种最新发布的前 5 个商品，如果不足 5 个，用第3条规则填补；
//        *  3、当前商品同分类最新发布的前 5 个商品。
        List<CommodityVo> list = new ArrayList<>();
        // TODO: 用户询价商品.
        if (user != null) {
            List<EnquiryCommoditys> commodities = enquiryCommoditysDao.findCommoditysByUser(String.valueOf(user.getId()));

            if (!commodities.isEmpty()){
                String ids = "";
                for (EnquiryCommoditys vo : commodities) {
                    ids += "'" + vo.getId() + "',";
                }

                ids = ids.substring(0, ids.length()-1);
                List<CommodityVo> commodityVOs = this.findByIds(ids);
                list.addAll(commodityVOs);
            }

        }

        if (list.size() < 5) {
            CommodityVo param = new CommodityVo();
            param.setCategoryId(breedId);
            PageInfo<CommodityVo> pageInfo = this.findByParam(param, 1, 5-list.size());
            list.addAll(pageInfo.getList());
        }
        if (list.size() < 5) {
            // 找到的商品数量不足从同分类的商品中找.
            // 根据品种ID 找到所属的类别
            // 根据类别ID 找到下面的品种, 判处当前的品种.找前几条和上面查出来的凑足5条.
                CategoryVo categoryVo = new CategoryVo();
                categoryVo.setParentId(categoryId);
                List<CategoryVo> categoryVos = categoryService.findBreed(categoryVo,1,5).getList();
                String categoryIds = "";
                for (CategoryVo vo : categoryVos) {
                    if(!vo.getId().equals(breedId)){
                        categoryIds += "'" + vo.getId() + "',";
                    }
                }
                categoryIds = categoryIds.substring(0, categoryIds.length()-1);
                CommodityVo commodityVO = new CommodityVo();
                commodityVO.setCategoryIds(categoryIds);
                List<CommodityVo> listc = this.findByParam(commodityVO, 1,5-list.size()).getList();
                // 整合数据
                list.addAll(listc);
                if(list.size() >5) {
                    list = list.subList(0, 5);
                }
        }

        return list;
    }

    @Override
    public List<CommodityVo> findVoByIds(String ids) {
        List<Integer> list = new ArrayList<>();
        for(String id :ids.split(",")){
            list.add(Integer.parseInt(id));
        }
        return FileUtil.convertAbsolutePathToUrl(commodityDao.findVoByIds(list),param);
    }

    @Override
    @Transactional
    public void create(List<Commodity> commodityList) {
        for(Commodity commodity : commodityList){
            commodity.setCreateTime(new Date());
            commodityDao.create(commodity);
        }

    }

	@Override
	public Category findBreedByName(CommodityVo commodityVO) {
        List<CommodityVo> list= commodityDao.findCommodityByName(commodityVO);
		return ValidUtils.listNotBlank(list) ? categoryService.findById(list.get(0).getCategoryId()) : null;
	}

	@Override
	public List<CommodityVo> findCommodityByName(CommodityVo commodityVO) {
		return commodityDao.findCommodityByNameLx(commodityVO);
	}

    @Override
    public List<Commodity> findByName(String name) {
        return commodityDao.findByName(name);
    }

	@Override
	public List<CommodityVo> findDistinctName(CommodityVo commodityVO) {
		return commodityDao.findDistinctName(commodityVO);
	}


    @Override
    public Commodity findById(int id) {
        return (Commodity)FileUtil.convertAbsolutePathToUrl(super.findById(id), param);
    }

    @Override
    public PageInfo<Commodity> find(int pageNum, int pageSize) {
        PageInfo pageInfo = super.find(pageNum, pageSize);
        List<Commodity> list = pageInfo.getList();
        list = FileUtil.convertAbsolutePathToUrl(list, param);
        pageInfo.setList(list);
        return pageInfo;
    }

    private PageInfo<CommodityVo> findByParam(CommodityVo commodity, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommodityVo> list = commodityDao.findByParam(commodity);
        list = FileUtil.convertAbsolutePathToUrl(list, param);
        PageInfo page = new PageInfo(list);
        return page;
    }

    private List<CommodityVo> findByIds(String ids) {
        return FileUtil.convertAbsolutePathToUrl(commodityDao.findByIds(ids),param);
    }


}
