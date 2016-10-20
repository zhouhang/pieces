package com.ms.tools.ueditor;

/**
 * Author: koabs
 * 7/18/16.
 */
public class CropResult {
    public String status;

    public String url;

    public Integer width;

    public Integer height;

    public String message;

    public static CropResult success(String url, Integer width, Integer height){
        return new CropResult("success", url, width, height);
    }

    public static CropResult success(String url){
        return new CropResult("success", url, 0, 0);
    }

    public static CropResult error(String msg) {
        return new CropResult("error", msg);
    }

    public CropResult(String status, String url, Integer width, Integer height) {
        this.status = status;
        this.url = url;
        this.width = width;
        this.height = height;
    }


    public CropResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
