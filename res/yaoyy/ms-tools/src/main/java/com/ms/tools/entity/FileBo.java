package com.ms.tools.entity;

import java.io.File;

/**
 * Created by wangbin on 2016/6/27.
 */
public class FileBo {
    private File file;
    private String name;
    private String path;
    private String ext;
    private String url;

    public FileBo(File file, String name, String url, String path, String ext) {
        this.file = file;
        this.name = name;
        this.path = path;
        this.ext = ext;
        this.url = url;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
