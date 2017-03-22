package com.pieces.dao.enums;

/**
 * Created by xc on 2017/3/15.
 */
public enum SeoTypeEnum {

    BASE(1, "基本设置"),
    COMMODITY_LIST(2, "商品列表页模板"),
    COMMODITY_DETAIL(3, "商品详情页模板"),
    SEARCH_RESULT(4, "搜索结果页模板"),
    ARTICLE_LIST(5, "文章列表页模板"),
    ARTICLE_DETAIL(6, "文章详情页模板");


    private SeoTypeEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    private Integer value;
    private String text;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * 通过ID来查询属性名称
     *
     * @param id
     * @return
     */
    public static String findByValue(Integer id) {
        for (SeoTypeEnum seoTypeEnum : SeoTypeEnum.values()) {
            if (seoTypeEnum.getValue().equals(id)) {
                return seoTypeEnum.getText();
            }
        }
        return null;
    }

}
