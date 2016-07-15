package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.boss.commons.LogEnum;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVO;
import com.pieces.service.CommodityService;
import com.pieces.service.constant.bean.Result;
import com.pieces.tools.bean.FileBo;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.upload.DefaultUploadFile;
import com.pieces.tools.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * Author: koabs
 * 7/12/16.
 * 商品信息
 */
@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController{

    @Autowired
    CommodityService commodityService;

//    @Autowired
//    private DefaultUploadFile defaultUploadFile;
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Integer pageSize, Integer pageNum, CommodityVO commodityVO , ModelMap model){

        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;

        PageInfo<CommodityVO> pageInfo = commodityService.query(commodityVO,pageNum, pageSize);

        model.put("pageNum", pageNum);
        model.put("pageSize", pageSize);
        model.put("pageInfo", pageInfo);
        return "commodity";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @ResponseBody
    @BizLog(type = "", desc = "保存商品信息")
    public Result save(Commodity commodity) {
        commodityService.saveOrUpdate(commodity);
        return new Result(true).info("商品信息保存成功!");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    @BizLog(type = "商品信息", desc = "根据ID删除商品")
    public Result delete(@PathVariable("id") Integer id) {
        commodityService.deleteById(id);
        return new Result(true).info("新增商品信息成功!");
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    @BizLog(type = "商品信息", desc = "查询商品信息列表")
    public Result queryById(CommodityVO commodity, Integer pageNum, Integer pageSize) {
        return new Result(true).data(commodityService.query(commodity,pageNum, pageSize));
    }

    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    @ResponseBody
    @BizLog(type = "商品信息", desc = "根据ID查询商品信息")
    public Result query(@PathVariable("id")Integer id) {
        return new Result(true).data(commodityService.findById(id));
    }

    /**
     * 上传商品图片信息
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result updateFile(@RequestParam(required = false) MultipartFile file) throws Exception{
//        FileBo fileBo = defaultUploadFile.uploadFile(UUID.randomUUID().toString(), file.getInputStream());

        return new Result(true).data("");
    }

    /**
     * 图片裁剪
     * @param src
     * @param dest
     * @param x
     * @param y
     * @param w
     * @param h
     * @param ext
     * @return
     */
    @RequestMapping(value = "/clipping", method = RequestMethod.GET)
    @ResponseBody
    public Result clipping(String src, String dest, int x, int y, int w, int h, String ext) throws Exception{

        return new Result(true).data(ImageUtil.clipping(src, dest,x,y,w,h,ext));
    }
}
