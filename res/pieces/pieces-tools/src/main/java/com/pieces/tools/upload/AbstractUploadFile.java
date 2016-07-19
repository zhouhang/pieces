package com.pieces.tools.upload;


import com.pieces.tools.bean.FileBo;
import com.pieces.tools.exception.FileUploadException;
import com.pieces.tools.utils.FileUtil;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.InputStream;

/**
 * Created by wangbin on 2016/6/27.
 */
public abstract class AbstractUploadFile {

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
            String fileName = customImageName(originalFileName);
            File file = FileUtil.save(inputStream,getBasePath(),fileName);
            FileBo fileBo = new FileBo(file,file.getName(),url+fileName,ext);
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

    /**
     * 判断文件后缀
     * @param ext
     * @return
     */
    public  Boolean checkFileSuffix(String ext){
        return true;
    }


    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
