package com.pieces.tools.upload;


import com.pieces.tools.bean.FileBo;
import com.pieces.tools.exception.FileUploadException;
import com.pieces.tools.utils.FileUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by wangbin on 2016/6/27.
 */
public abstract class AbstractUploadFile {

    @Autowired
    IUploadConfig uploadConfig;

    private String basePath;

    private String url;

    /**
     * 上传文件
     * @param originalFileName
     * @param inputStream
     * @return
     */
    public FileBo uploadFile(String originalFileName, InputStream inputStream){
        try {
            String ext = FileUtil.getFileExt(originalFileName);
            if(!checkFileSuffix(ext)){
                throw new FileUploadException("文件类型错误!");
            }
            //自定义文件名
            String fileName = customImageName(ext);

            //图片加水印
            InputStream is = addWatermark(inputStream, ext);

            File file = FileUtil.save(is ,getBasePath(),fileName);
            FileBo fileBo = new FileBo(file,file.getName(),
                    getUrl() + fileName,getBasePath()+fileName,ext);
            return fileBo;
        }
        catch (FileUploadException fe){
            throw fe;
        }
        catch (Exception e){
            throw new FileUploadException("文件上传失败!");
        }
    }

    /**
     * 自定义文件名
     * @param fileName
     * @return
     */
    public abstract String customImageName(String fileName);


    public abstract InputStream addWatermark(InputStream inputStream, String ext) throws IOException;

    /**
     * 判断文件后缀
     * @param ext
     * @return
     */
    public  Boolean checkFileSuffix(String ext){
        return true;
    }


    public String getBasePath() {
        return uploadConfig.getAbsolutePath();
    }

    public String getUrl() {
        return uploadConfig.getUrlPre();
    }
}
