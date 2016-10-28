package com.ms.tools.upload;

import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Author: koabs
 * 10/21/16.
 * 1. 绝对路径转换成Url 路径
 * 2. 把临时文件保存到 正式目录
 */
@Service
public class PathConvert {

   private Logger logger = LoggerFactory.getLogger(PathConvert.class);

    // 绝对路径
    @Value("${upload.path}")
    public String path;

    // url 前缀
    @Value("${upload.url}")
    public String url;

    /**
     * 根据绝对路径获取url
     *
     * @param path
     * @return
     */
    public String getUrl(String path) {
        if (path != null) {
            path = path.replace(this.path, url);
        }
        return path;
    }

    /**
     * 根据传回的url 保存临时目录的文件到正式目录并返回绝对路径
     *
     * @param url
     * @param direct
     * @return
     * @throws IOException
     */
    public String saveFileFromTemp(String url, String direct) {
        if (url == null) {
            return null;
        }

        // 判断url中不包含temp 直接return null
        if (!url.contains("temp/")) {
            url = url.replace(this.url, path);
            return url;
        }

        File srcFile = new File(url.replace(this.url, path));
        String dest = url.replace(this.url + "temp/", path + direct);
        File destFile = new File(dest);
        if (srcFile.exists()) {
            try {
                Files.createParentDirs(destFile);
                Files.move(srcFile, destFile);
            } catch (IOException e) {
                throw new RuntimeException("从临时文件去拷贝文件出错", e);
            }
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
    public List convertAbsolutePathToUrl(List list, String params) {

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

    public Object convertAbsolutePathToUrl(Object object, String params) {
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
