package com.pieces.tools.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Author: koabs
 * 7/14/16.
 * 文件裁剪和相关操作工具类
 */
public class ImageUtil {


    /**
     * @param src 源文件路径
     * @param dest 裁剪后的文件路径
     * @param x
     * @param y
     * @param w
     * @param h
     * @param ext 后缀名
     * @return
     */
    public static String clipping(String src, String dest, int x, int y, int w, int h, String ext) throws Exception {

        Iterator iterator = ImageIO.getImageReadersByFormatName(ext);
        ImageReader reader = (ImageReader) iterator.next();
        InputStream in = new FileInputStream(src);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        Rectangle rect = new Rectangle(x, y, w, h);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ImageIO.write(bi, ext, new File(dest));


        return dest;
    }
}
