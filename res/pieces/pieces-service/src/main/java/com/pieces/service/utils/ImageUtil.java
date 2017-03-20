package com.pieces.service.utils;

import com.google.common.collect.FluentIterable;
import com.google.common.io.Files;
import com.pieces.service.vo.CropInfo;
import com.pieces.tools.utils.FileUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

        // 保存缩略图
        Thumbnails.of(bufferedImageR).size(cropInfo.getImgW().intValue(), cropInfo.getImgH().intValue()).watermark(
                Positions.CENTER,
                ImageIO.read(ImageUtil.class.getClassLoader().getResourceAsStream("img/watermark.png")), 1.0f)
                .outputQuality(1.0f).toFile(descPath);
        // 生成不同规格的缩略图
        Thumbnails.of(descPath).forceSize(90,90).outputQuality(1.0f).toFile(FileUtil.getFilePathNoExt(imgPath) + "@90" + FileUtil.getFileExt(imgPath));
        Thumbnails.of(descPath).forceSize(180,180).outputQuality(1.0f).toFile(FileUtil.getFilePathNoExt(imgPath) + "@180" + FileUtil.getFileExt(imgPath));
        Thumbnails.of(descPath).forceSize(360,360).outputQuality(1.0f).toFile(FileUtil.getFilePathNoExt(imgPath) + "@360" + FileUtil.getFileExt(imgPath));

        return descPath;
    }

    /**
     * 批量处理图片生成缩略图 360 不需要
     * @param folderName
     * @throws Exception
     */
    public static void batchProcess(String folderName) throws Exception {
        FluentIterable<File> iterable = Files.fileTreeTraverser().breadthFirstTraversal(new File(folderName));
        for (File f : iterable) {
            if (f.isFile() &&  ImageIO.read(f)!= null) {
                String imgPath = f.getAbsolutePath();
                Thumbnails.of(f).size(90, 90).outputQuality(1.0f).toFile(FileUtil.getFilePathNoExt(imgPath) + "@90" + FileUtil.getFileExt(imgPath));
                Thumbnails.of(f).size(180, 180).outputQuality(1.0f).toFile(FileUtil.getFilePathNoExt(imgPath) + "@180" + FileUtil.getFileExt(imgPath));
                Thumbnails.of(f).size(360, 360).outputQuality(1.0f).toFile(FileUtil.getFilePathNoExt(imgPath) + "@360" + FileUtil.getFileExt(imgPath));
                System.out.println(f.getAbsolutePath());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ImageUtil.batchProcess("/Users/kevin1/Downloads/commodity/");
    }
}
