package com.pieces.boss.upload;

import com.pieces.tools.upload.IUploadConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author: koabs
 * 8/10/16.
 */
@Component
public class UploadConfig implements IUploadConfig {
    /**
     * 绝对路径
     */
    @Value("${boss.upload.path}")
    public String absolutePath;



    /**
     * url 前缀
     */
    @Value("${boss.upload.url}")
    public String urlPre;

    @Override
    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    @Override
    public String getUrlPre() {
        return urlPre;
    }

    public void setUrlPre(String urlPre) {
    this.urlPre = urlPre;
    }
}
