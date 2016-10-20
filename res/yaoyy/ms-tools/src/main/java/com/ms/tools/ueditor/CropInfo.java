package com.ms.tools.ueditor;

/**
 * Author: koabs
 * 7/19/16.
 * 裁剪后的图片信息
 */
public class CropInfo {

    private String imgUrl;        // your image path (the one we recieved after successfull upload)

    private Double imgInitW;    // your image original width (the one we recieved after upload)
    private Double imgInitH;    // your image original height (the one we recieved after upload)

    private Double imgW;        // your new scaled image width
    private Double imgH;    // your new scaled image height
    private Double imgX1;        // top left corner of the cropped image in relation to scaled image
    private Double imgY1;    // top left corner of the cropped image in relation to scaled image

    private Double cropW;        // cropped image width
    private Double cropH;    // cropped image height

    private Double rotation; // 旋转角度

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Double getImgInitW() {
        return imgInitW;
    }

    public void setImgInitW(Double imgInitW) {
        this.imgInitW = imgInitW;
    }

    public Double getImgInitH() {
        return imgInitH;
    }

    public void setImgInitH(Double imgInitH) {
        this.imgInitH = imgInitH;
    }

    public Double getImgW() {
        return imgW;
    }

    public void setImgW(Double imgW) {
        this.imgW = imgW;
    }

    public Double getImgH() {
        return imgH;
    }

    public void setImgH(Double imgH) {
        this.imgH = imgH;
    }

    public Double getImgX1() {
        return imgX1;
    }

    public void setImgX1(Double imgX1) {
        this.imgX1 = imgX1;
    }

    public Double getImgY1() {
        return imgY1;
    }

    public void setImgY1(Double imgY1) {
        this.imgY1 = imgY1;
    }

    public Double getCropW() {
        return cropW;
    }

    public void setCropW(Double cropW) {
        this.cropW = cropW;
    }

    public Double getCropH() {
        return cropH;
    }

    public void setCropH(Double cropH) {
        this.cropH = cropH;
    }

    public Double getRotation() {
        return rotation;
    }

    public void setRotation(Double rotation) {
        this.rotation = rotation;
    }
}
