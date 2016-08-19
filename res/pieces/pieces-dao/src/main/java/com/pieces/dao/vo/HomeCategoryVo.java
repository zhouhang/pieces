package com.pieces.dao.vo;

import com.pieces.dao.model.Ad;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Commodity;

import java.util.List;

/**
 * Created by wangbin on 2016/8/10.
 */
public class HomeCategoryVo{

    private String pictureUrl;

    private String title;

    //橱窗广告
    private Ad showcase;

    private List<Category> breedList;

    private List<CommodityVo> commodityList;


    public List<Category> getBreedList() {
        return breedList;
    }

    public void setBreedList(List<Category> breedList) {
        this.breedList = breedList;
    }

    public List<CommodityVo> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<CommodityVo> commodityList) {
        this.commodityList = commodityList;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Ad getShowcase() {
        return showcase;
    }

    public void setShowcase(Ad showcase) {
        this.showcase = showcase;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
