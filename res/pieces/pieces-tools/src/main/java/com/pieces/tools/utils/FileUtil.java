package com.pieces.tools.utils;


import com.pieces.tools.exception.FileUploadException;
import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * 文件操作工具类
 * Created by wangbin on 2016/6/27.
 */
public class FileUtil {

    private FileUtil(){};

    public static String getFileExt(String fileName){
        int index=fileName.lastIndexOf('.');
        if(index==-1){
            return "";
        }
        String ext=fileName.substring(index);
        return ext;
    }

    public static File save(InputStream inputStream, String path, String fileName)throws IOException{
        File file = new File(path,fileName);
        if(file.exists()){
            throw new FileUploadException("该文件已存在!");
        }
        FileUtils.copyInputStreamToFile(inputStream,file);
        return file;
    }


}

