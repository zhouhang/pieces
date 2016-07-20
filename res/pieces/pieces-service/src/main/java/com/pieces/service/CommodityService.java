package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVO;
import com.pieces.service.vo.CropInfo;
import com.pieces.service.vo.CropResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * Created by kevin1 on 7/12/16.
 * 商品
 */
public interface CommodityService extends ICommonService<Commodity>{

    /**
     * 保存或者更新商品信息
     * @param commodity
     */
    public void saveOrUpdate(Commodity commodity);
    /**
     * 根据传入的参数查询商品信息
     * @param commodity
     * @return
     */
    public PageInfo<CommodityVO> query(CommodityVO commodity, int pageNum, int pageSize);

    public PageInfo<CommodityVO> findVoByPage(int pageNum, int pageSize);

    public CommodityVO findVoById(Integer id);
    /**
     * 上传文件
     * @return
     */
    public CropResult uploadImage(MultipartFile img);

    public CommodityVO findCommodityByBreedId(Integer id);
    List<CommodityVO> findFactoryByBreedId(Integer id);
    List<CommodityVO> findStandardByBreedId(Integer id);

    /**
     * 裁剪图片
     * @param crop
     * @return
     */
    public CropResult cropImg(CropInfo crop);

}
