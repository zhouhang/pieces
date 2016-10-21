package com.ms.boss.controller;

import com.ms.tools.ueditor.CropInfo;
import com.ms.tools.ueditor.CropResult;
import com.ms.tools.ueditor.UEditorResult;
import com.ms.tools.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公共URL访问
 * Created by wangbin on 2016/6/27.
 */
@Controller
@RequestMapping(value = "gen")
public class GeneralController {

    @Autowired
    UploadService uploadService;

    /**
     * 上传商品图片信息
     * @param img
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CropResult updateFile(@RequestParam(required = false) MultipartFile img) throws Exception{
        return uploadService.uploadImage(img);
    }

    /**
     * 图片裁剪
     * @return
     */
    @RequestMapping(value = "/clipping", method = RequestMethod.POST)
    @ResponseBody
    public CropResult clipping(CropInfo cropInfo) throws Exception{
        return uploadService.cropImg(cropInfo);
    }


    /**
     * 富文本编辑器上传图片方法
     * @param upfile
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public UEditorResult updateUEditorFile(@RequestParam(required = false) MultipartFile upfile) throws Exception{
        CropResult cropResult = uploadService.uploadUeditorImage(upfile);
        return UEditorResult.success(upfile.getOriginalFilename(),upfile.getOriginalFilename(),cropResult.getUrl() );
    }


}