package com.pieces.tools.utils;


import com.pieces.tools.exception.FileUploadException;
import com.pieces.tools.upload.IUploadConfig;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 文件操作工具类
 * Created by wangbin on 2016/6/27.
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 绝对路径
     */
    private static String absolutePath;

    /**
     * url 前缀
     */
    private static String urlPre;

    /**
     * 初始化
     */
    static {
        IUploadConfig uploadConfig =  (IUploadConfig)SpringUtil.getBean(IUploadConfig.class);
        absolutePath = uploadConfig.getAbsolutePath();
        urlPre = uploadConfig.getUrlPre();
    }

    private FileUtil() {
    }

    public static String getFileExt(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index == -1) {
            return "";
        }
        String ext = fileName.substring(index);
        return ext;
    }

    public static String getFilePathNoExt(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index == -1) {
            return "";
        }
        String path = fileName.substring(0, index);
        return path;
    }

    public static File save(InputStream inputStream, String path, String fileName) throws IOException {
        File file = new File(path, fileName);
        if (file.exists()) {
            throw new FileUploadException("该文件已存在!");
        }
        FileUtils.copyInputStreamToFile(inputStream, file);
        return file;
    }


    /**
     * 根据传进来的URL 获取绝对路径
     *
     * @param url
     * @return
     */
    public static String getAbsolutePath(String url) {
        return url.replace(urlPre, absolutePath);
    }

    /**
     * 根据绝对路径获取url
     *
     * @param path
     * @return
     */
    public static String getUrl(String path) {
        return path.replace(absolutePath, urlPre);
    }


    /**
     * 根据传回的url 保存临时目录的文件到正式目录并返回绝对路径
     *
     * @param url
     * @param direct
     * @return
     * @throws IOException
     */
    public static String saveFileFromTemp(String url, String direct) throws IOException {
        // 判断url中不包含temp 直接return null
        if (!url.contains("temp/")) {
            return null;
        }

        File srcFile = new File(url.replace(urlPre, absolutePath));
        String dest = url.replace(urlPre + "temp/", absolutePath + direct);
        File destFile = new File(dest);
        if (srcFile.exists()) {
            FileUtils.copyFile(srcFile, destFile);
        } else {
            dest = null;
        }
        return dest;
    }


    /**
     * 将返回的对象list里面的路径属性转换成url
     *
     * @param list
     * @param params
     * @return
     */
    public static List convertAbsolutePathToUrl(List list, String params) {

        try {
            String[] props = params.split(",");

            for (int i = 0; i < props.length; i++) {
                props[i] = props[i].substring(0, 1).toUpperCase() + props[i].substring(1);
            }

            for (Object object : list) {
                for (String prop : props) {
                    Method m = object.getClass().getMethod("get" + prop);
                    String value = (String) m.invoke(object); // 调用getter方法获取属性值
                    if (value != null) {
                        m = object.getClass().getMethod("set" + prop, String.class);
                        m.invoke(object, getUrl(value));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            logger.error("绝对路径转换成url失败!");
        }
        return list;
    }

    public static Object convertAbsolutePathToUrl(Object object, String params) {
        try {
            String[] props = params.split(",");

            for (int i = 0; i < props.length; i++) {
                props[i] = props[i].substring(0, 1).toUpperCase() + props[i].substring(1);
                Method m = object.getClass().getMethod("get" + props[i]);
                String value = (String) m.invoke(object); // 调用getter方法获取属性值
                if (value != null) {
                    m = object.getClass().getMethod("set" + props[i], String.class);
                    m.invoke(object, getUrl(value));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            logger.error("绝对路径转换成url失败!");
        }
        return object;
    }

}

