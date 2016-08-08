package com.pieces.service.enums;

/**
 * Author: koabs
 * 8/5/16.
 * 文章模块划分
 */
public enum ModelEnum {

    help(1), // 帮助中心
    news(2); // 新闻中心

    private ModelEnum(Integer value) {
        this.value = value;
    }

    private Integer value;
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }

    public static String getUrl(String uri, Integer model) {
        String preStr = "";
        switch (model) {
            case 1 :
                preStr = "help";
                break;
            case 2 :
                preStr = "news";
                break;
        }
        return preStr + "-" + uri;
    }
}
