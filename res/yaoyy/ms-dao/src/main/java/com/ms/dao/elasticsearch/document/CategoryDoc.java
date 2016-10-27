package com.ms.dao.elasticsearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by xiao on 2016/10/27.
 */
@Document(indexName = "yaoyy", type = "category")
public class CategoryDoc {


    @Id
    private Integer id;
    //商品名称
    private String variety;

    private String title;

    //价格描述
    private String priceDesc;

    //价格单位
    private String unit;

    private Integer sort;

    @Field(type = FieldType.String,index= FieldIndex.not_analyzed)
    private String pictureUrl;

    //上下架状态
    private Integer status;

    private String parentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriceDesc() {
        return priceDesc;
    }

    public void setPriceDesc(String priceDesc) {
        this.priceDesc = priceDesc;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
