package com.pieces.tools.utils;


import com.pieces.tools.exception.FileUploadException;
import com.pieces.tools.upload.IUploadConfig;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

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
    public static String saveFileFromTemp(String url, String direct) {
        // 判断url中不包含temp 直接return url
        if (!url.contains("temp/")) {
            url = url.replace(urlPre, absolutePath);
            return url;
        }

        File srcFile = new File(url.replace(urlPre, absolutePath));
        String dest = url.replace(urlPre + "temp/", absolutePath + direct);
        File destFile = new File(dest);
        if (srcFile.exists()) {
            try {
                FileUtils.copyFile(srcFile, destFile);
                if (dest.contains("commodity/")) {
                    // 生成不同规格的缩略图
                    Thumbnails.of(dest).forceSize(90, 90).outputQuality(1.0f).toFile(FileUtil.getFilePathNoExt(dest) + "@90" + FileUtil.getFileExt(dest));
                    Thumbnails.of(dest).forceSize(180, 180).outputQuality(1.0f).toFile(FileUtil.getFilePathNoExt(dest) + "@180" + FileUtil.getFileExt(dest));
                }
            } catch (IOException e) {
                throw new RuntimeException("从临时文件去拷贝文件出错", e);
            }
        } else {
            dest = null;
        }


        return dest;
    }

    /**
     * 从微信服务器保存图片到本地
     * @param token
     * @param mediaId    * @param direct
     * @return
     */
    public static String saveFileFromWechat(String token,String mediaId, String direct) {
        String desc = null;
        Calendar now = Calendar.getInstance();
        int year=now.get(Calendar.YEAR);
        int month=now.get(Calendar.MONTH)+1;
        StringBuffer sb = new StringBuffer();
        sb.append(absolutePath).append(direct).append("/").append(year).append("/").append(month).append("/");
        try {
            String path = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+token+"&media_id="+mediaId;
            URL url  = new URL(path);
            URLConnection connection = url.openConnection();
            String filleName = connection.getHeaderField("Content-disposition").split(";")[1].split("=")[1].replace("\"","");
            InputStream ism=url.openStream();
            FileUtil.save(ism ,sb.toString(),filleName);
            desc = sb.append(filleName).toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return desc;
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
        if (object != null) {
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
        }
        return object;
    }

}

