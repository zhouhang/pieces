package com.ms.tools.upload;

import com.ms.tools.FileUtil;
import com.ms.tools.entity.FileBo;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wangbin on 2016/6/27.
 */
public abstract class AbstractUploadFile {

    // 绝对路径
    @Value("${upload.path}")
    public String path;

    // url 前缀
    @Value("${upload.url}")
    public String url;

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

            File file = FileUtil.save(is ,path,fileName);
            is.close();
            FileBo fileBo = new FileBo(file,file.getName(),
                    url + fileName,path+fileName,ext);
            return fileBo;
        } catch (Exception e){
            throw new RuntimeException("文件上传失败!", e);
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

    public String getPath() {
        return path;
    }

    public String getUrl() {
        return url;
    }
}
