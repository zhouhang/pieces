package com.pieces.biz.controller;

import com.pieces.biz.controller.commons.LogConstant;
import com.pieces.dao.enums.SeoTypeEnum;
import com.pieces.dao.model.Article;
import com.pieces.dao.model.ArticleCategory;
import com.pieces.dao.model.SeoSetting;
import com.pieces.dao.vo.ArticleCategoryVo;
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
@RequestMapping("help")
public class HelpController extends BaseController{
    @Autowired
    ArticleService articleService;

    @Autowired
    private SeoSettingService seoSettingService;

    @RequestMapping("/{id}")
    @BizLog(type = LogConstant.help, desc = "帮助中心页面")
    public String detail(@PathVariable("id") Integer id, ModelMap model) {
        List<ArticleCategoryVo> articleCategorylist = articleService.getCategoryListByModelId(ModelEnum.help.getValue());
        id = id == 0 ? articleCategorylist.get(0).getArticles().get(0).getId() : id;
        Article article = articleService.findArticleById(id);
        model.put("articleCategorylist",articleCategorylist);
        model.put("article",article);
        // 回填帮助中心前台

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



        return "helper";
    }
}
