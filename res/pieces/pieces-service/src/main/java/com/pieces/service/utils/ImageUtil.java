package com.pieces.service.utils;

import com.pieces.service.vo.CropInfo;
import com.pieces.tools.utils.FileUtil;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Author: koabs
 * 7/19/16.
 */
public class ImageUtil {
    public static String cropImg(CropInfo cropInfo) throws IOException {
        String imgPath = cropInfo.getImgUrl();

        // 旋转图片
        String descPath = FileUtil.getFilePathNoExt(imgPath) + "Crop" + FileUtil.getFileExt(imgPath);
        BufferedImage bufferedImage = Thumbnails.of(imgPath).forceSize(cropInfo.getImgW().intValue(), cropInfo.getImgH().intValue())
                .rotate(cropInfo.getRotation())
                .asBufferedImage();

        Thumbnails.of(bufferedImage).sourceRegion(cropInfo.getImgX1().intValue(),cropInfo.getImgY1().intValue(),
                cropInfo.getCropW().intValue(),cropInfo.getCropH().intValue()).scale(1).toFile(descPath);

        // 保存缩略图

        return descPath;
    }
}
