package com.pieces.service.utils;

import com.pieces.service.vo.CropInfo;
import com.pieces.tools.utils.FileUtil;
import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

/**
 * Author: koabs
 * 7/19/16.
 */
public class ImageUtil {
    public static String cropImg(CropInfo cropInfo) throws IOException {
        String imgPath = cropInfo.getImgUrl();

        String descPath = FileUtil.getFilePathNoExt(imgPath) + "Crop" + FileUtil.getFileExt(imgPath);
        Thumbnails.of(imgPath).forceSize(cropInfo.getImgW().intValue(), cropInfo.getImgH().intValue())
                .sourceRegion(cropInfo.getImgX1().intValue(),cropInfo.getImgY1().intValue(),
                        cropInfo.getCropW().intValue(),cropInfo.getCropH().intValue()).toFile(descPath);

        // 保存缩略图

        return descPath;
    }
}
