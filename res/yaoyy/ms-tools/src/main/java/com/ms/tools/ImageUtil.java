package com.ms.tools;

import com.ms.tools.ueditor.CropInfo;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * Author: koabs
 * 7/19/16.
 */
public class ImageUtil {
    public static String cropImg(CropInfo cropInfo) throws Exception {
        String imgPath = cropInfo.getImgUrl();

        // 旋转图片
        String descPath = FileUtil.getFilePathNoExt(imgPath) + "Crop" + FileUtil.getFileExt(imgPath);
        BufferedImage bufferedImage = Thumbnails.of(imgPath).forceSize(cropInfo.getImgW().intValue(), cropInfo.getImgH().intValue())
                .rotate(cropInfo.getRotation())
                .asBufferedImage();

        // 加水印
        BufferedImage bufferedImageR = Thumbnails.of(bufferedImage).sourceRegion(cropInfo.getImgX1().intValue(),cropInfo.getImgY1().intValue(),
                cropInfo.getCropW().intValue(),cropInfo.getCropH().intValue()).scale(1).asBufferedImage();

        // 加水印
        InputStream input = ImageUtil.class.getClassLoader().getResourceAsStream("img/watermark.png");
        if (input != null) {
            Thumbnails.of(bufferedImageR).size(cropInfo.getImgW().intValue(), cropInfo.getImgH().intValue()).watermark(
                    Positions.CENTER,
                    ImageIO.read(input), 1.0f)
                    .outputQuality(1.0f).toFile(descPath);
        } else {
            Thumbnails.of(bufferedImageR)
                    .outputQuality(1.0f).scale(1).toFile(descPath);
        }

        return descPath;
    }
}
