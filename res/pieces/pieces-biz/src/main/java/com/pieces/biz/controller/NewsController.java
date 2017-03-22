package com.pieces.biz.controller;

import com.github.inspektr.audit.annotation.Audit;
import com.github.pagehelper.PageInfo;
import com.pieces.biz.controller.commons.LogConstant;
import com.pieces.dao.enums.SeoTypeEnum;
import com.pieces.dao.model.Article;
import com.pieces.dao.model.ArticleCategory;
import com.pieces.dao.model.SeoSetting;
import com.pieces.dao.vo.ArticleVo;
import com.pieces.service.ArticleService;
import com.pieces.service.SeoSettingService;
import com.pieces.service.enums.ModelEnum;
import com.pieces.tools.log.annotation.BizLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Author: koabs
 * 8/8/16.
 */
@Controller
@RequestMapping("news")
public class NewsController extends BaseController {

    @Autowired
    ArticleService articleService;

    @Autowired
    private SeoSettingService seoSettingService;



    @RequestMapping("/{id}")
    @BizLog(type = LogConstant.logistics, desc = "新闻中心")
    public String detail(@PathVariable("id") Integer id, ModelMap model) {
        ArticleVo vo = new ArticleVo();
        vo.setModel(ModelEnum.news.getValue());
        List<ArticleVo> articleList = articleService.queryArticle(vo,1,5).getList();
        id = id == 0 ? articleList.get(0).getId() : id;
        Article article = articleService.findArticleById(id);
        // 回填前台显示
        model.put("articleList",articleList);
        model.put("article",article);
        //seo信息
        SeoSetting base=seoSettingService.findByType(SeoTypeEnum.BASE.getValue());
        SeoSetting commditySetting=seoSettingService.findByType(SeoTypeEnum.ARTICLE_DETAIL.getValue());
        String title=commditySetting.getTitle();
        if(title!=null){
            title=title.replace("{文章标题}",article.getTitle());
            ArticleCategory articleCategory=articleService.getCategoryById(article.getCategoryId());
            title=title.replace("{分类名称}",articleCategory.getName());
            title=title.replace("{通用标题}",base.getTitle()==null?"":base.getTitle());
        }
        String description=commditySetting.getIntro();
        if(description!=null){
            description=description.replace("{文章描述}",article.getIntro()==null?"":article.getIntro());
            description=description.replace("{通用描述}",base.getIntro()==null?"":base.getIntro());
        }



        String keyWords=commditySetting.getKeyWord();
        if(keyWords!=null){
            keyWords=keyWords.replace("{文章关键字}",article.getKeyWord()==null?"":article.getKeyWord());
            keyWords=keyWords.replace("{通用关键字}",base.getKeyWord()==null?"":base.getKeyWord());
        }



        model.put("title",title);
        model.put("description",description);
        model.put("keyWords",keyWords);


        return "article";
    }



}
