package com.pieces.service.vo;

/**
 * Author: koabs
 * 7/19/16.
 * 裁剪后的图片信息
 */
public class CropInfo {

    private String imgUrl;        // your image path (the one we recieved after successfull upload)

    private Integer imgInitW;    // your image original width (the one we recieved after upload)
    private Integer imgInitH;    // your image original height (the one we recieved after upload)

    private Integer imgW;        // your new scaled image width
    private Integer imgH;    // your new scaled image height
    private Integer imgX1;        // top left corner of the cropped image in relation to scaled image
    private Integer imgY1;    // top left corner of the cropped image in relation to scaled image

    private Integer cropW;        // cropped image width
    private Integer cropH;    // cropped image height

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getImgInitW() {
        return imgInitW;
    }

    public void setImgInitW(Integer imgInitW) {
        this.imgInitW = imgInitW;
    }

    public Integer getImgInitH() {
        return imgInitH;
    }

    public void setImgInitH(Integer imgInitH) {
        this.imgInitH = imgInitH;
    }

    public Integer getImgW() {
        return imgW;
    }

    public void setImgW(Integer imgW) {
        this.imgW = imgW;
    }

    public Integer getImgH() {
        return imgH;
    }

    public void setImgH(Integer imgH) {
        this.imgH = imgH;
    }

    public Integer getImgX1() {
        return imgX1;
    }

    public void setImgX1(Integer imgX1) {
        this.imgX1 = imgX1;
    }

    public Integer getImgY1() {
        return imgY1;
    }

    public void setImgY1(Integer imgY1) {
        this.imgY1 = imgY1;
    }

    public Integer getCropW() {
        return cropW;
    }

    public void setCropW(Integer cropW) {
        this.cropW = cropW;
    }

    public Integer getCropH() {
        return cropH;
    }

    public void setCropH(Integer cropH) {
        this.cropH = cropH;
    }
}
