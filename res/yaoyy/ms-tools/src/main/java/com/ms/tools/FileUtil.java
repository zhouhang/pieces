package com.ms.tools;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件工具类
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

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
            throw new RuntimeException("该文件已存在!");
        }
        Files.createParentDirs(file);
        Files.write(ByteStreams.toByteArray(inputStream), file);

        return file;
    }
}

