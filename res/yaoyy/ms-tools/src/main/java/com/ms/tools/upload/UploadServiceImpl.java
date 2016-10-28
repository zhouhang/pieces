package com.ms.tools.upload;

import com.ms.tools.ImageUtil;
import com.ms.tools.entity.FileBo;
import com.ms.tools.ueditor.CropInfo;
import com.ms.tools.ueditor.CropResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

/**
 * Author: koabs
 * 10/20/16.
 */

@Service
public class UploadServiceImpl implements UploadService {

    Logger logger = LoggerFactory.getLogger(UploadService.class);

    @Autowired
    TempUploadFile tempUploadFile;

    @Autowired
    UEditorUploadFile uEditorUploadFile;


    @Override
    public CropResult uploadImage(MultipartFile img) {
        CropResult cropResult = null;
        if(checkImgSize(img))  {
            cropResult = CropResult.error("上传的图片大小不能超过2M");
        } else {
            try {
                FileBo fileBo = tempUploadFile.uploadFile(img.getOriginalFilename(), img.getInputStream());
                FileInputStream fs=new FileInputStream(fileBo.getFile());
                BufferedImage sourceImg = ImageIO.read(fs);
                fs.close();
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
        String basePath = tempUploadFile.getPath();
        String url = tempUploadFile.getUrl();

        String adder = cropInfo.getImgUrl().replace(url, basePath);
        cropInfo.setImgUrl(adder);

        try {
            return CropResult.success(ImageUtil.cropImg(cropInfo).replace(basePath, url));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return CropResult.error("图片裁剪失败");
        }
    }
}
