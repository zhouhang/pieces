package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.service.vo.CropInfo;
import com.pieces.service.vo.CropResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by kevin1 on 7/12/16.
 * 商品
 */
public interface CommodityService extends ICommonService<Commodity>{

    /**
     * 保存或者更新商品信息
     * @param commodity
     */
    public void saveOrUpdate(Commodity commodity) throws IOException;
    /**
     * 根据传入的参数查询商品信息
     * @param commodity
     * @return
     */
    public PageInfo<CommodityVo> query(CommodityVo commodity, int pageNum, int pageSize);

    public PageInfo<CommodityVo> findVoByPage(int pageNum, int pageSize);

    public CommodityVo findVoById(Integer id);

    public List<CommodityVo> findVoByIds(Set<Integer> ids);
    /**
     * 上传文件
     * @return
     */
    public CropResult uploadImage(MultipartFile img);

    public CropResult uploadUeditorImage(MultipartFile img);

    public List<CommodityVo> findCommodityByBreedId(Integer id);
    List<CommodityVo> findFactoryByBreedId(String ids);
    List<CommodityVo> findStandardByBreedId(String ids);

    /**
     * 裁剪图片
     * @param crop
     * @return
     */
    public CropResult cropImg(CropInfo crop);

	List<Commodity> queryNoPage(CommodityVo commodity);

    /**
     * 商品推荐
     * 推荐规则:
     *  1、用户曾经询价过的品种，取询价次数最多的 5 个，如果不足 5 个，用第 2条规则填补；
     *  2、当前商品同品种最新发布的前 5 个商品，如果不足 5 个，用第3条规则填补；
     *  3、当前商品同分类最新发布的前 5 个商品。
     * @param user
     * @return
     */
    List<CommodityVo> featured(User user, Integer breedId, Integer categoryId);


    List<CommodityVo> findVoByIds(String ids);


    void create(List<Commodity> commodityList);
    public Category findBreedByName(CommodityVo commodityVO);
    public List<CommodityVo> findCommodityByName(CommodityVo commodityVO);
}
