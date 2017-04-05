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
    Integer saveOrUpdate(Commodity commodity) throws IOException;
    /**
     * 根据传入的参数查询商品信息
     * @param commodity
     * @return
     */
    PageInfo<CommodityVo> query(CommodityVo commodity, int pageNum, int pageSize);

    PageInfo<CommodityVo> findVoByPage(int pageNum, int pageSize);

    CommodityVo findVoById(Integer id);

    List<CommodityVo> findVoByIds(Set<Integer> ids);
    /**
     * 上传文件
     * @return
     */
    CropResult uploadImage(MultipartFile img);

    CropResult uploadUeditorImage(MultipartFile img);

    List<CommodityVo> findCommodityByBreedId(Integer id);
    List<CommodityVo> queryCommodityByBreedId(Integer id);
    List<CommodityVo> findFactoryByBreedId(String ids);
    List<CommodityVo> findStandardByBreedId(String ids);

    /**
     * 裁剪图片
     * @param crop
     * @return
     */
    public CropResult cropImg(CropInfo crop);

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
    Category findBreedByName(CommodityVo commodityVO);

    List<CommodityVo> findCommodityByName(CommodityVo commodityVO);

    List<Commodity> findByName(String name);
    List<CommodityVo> findDistinctName(CommodityVo commodityVO);

    Integer batchUpdate(List<Commodity> list);

    PageInfo<CommodityVo> searchForOrder(Integer userId, String name,Integer pageNum, Integer pageSize);

    List<CommodityVo> searchOrderByIds(Integer userId,String ids);

}
