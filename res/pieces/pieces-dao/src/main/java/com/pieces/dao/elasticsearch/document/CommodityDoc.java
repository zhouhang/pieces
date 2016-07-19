package com.pieces.dao.elasticsearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;

/**
 * 商品索引文档
 * Created by wangbin on 2016/7/14.
 */
@Document(indexName = "pieces", type = "commodity")
public class CommodityDoc {

    @Id
    private Integer id;
    //商品名称
    private String name;
    //生产厂家
    private String factory;
    //外观描述
    @Field(index= FieldIndex.not_analyzed)
    private String exterior;

    //品种名称
    private String categoryName;

    //切制规格
    private String spec;
    //原药产地
    private String originOf;
    //执行标准
    @Field(index=FieldIndex.not_analyzed)
    private String executiveStandard;
    //图片URL
    @Field(index=FieldIndex.not_analyzed)
    private String pictureUrl;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getOriginOf() {
        return originOf;
    }

    public void setOriginOf(String originOf) {
        this.originOf = originOf;
    }

    public String getExecutiveStandard() {
        return executiveStandard;
    }

    public void setExecutiveStandard(String executiveStandard) {
        this.executiveStandard = executiveStandard;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
