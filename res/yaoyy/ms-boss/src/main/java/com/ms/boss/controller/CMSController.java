package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Article;
import com.ms.dao.vo.ArticleVo;
import com.ms.service.ArticleService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Author: koabs
 * 10/31/16.
 * 内容管理系统
 */
@Controller
@RequestMapping("/cms/")
public class CMSController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private HttpSession httpSession;

    /**
     * @param articleVo
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(ArticleVo articleVo, Integer pageNum, Integer pageSize, ModelMap model) {
        PageInfo<ArticleVo> pageInfo = articleService.findByParams(articleVo, pageNum, pageSize);
        model.put("pageInfo", pageInfo);
        return "article_list";
    }

    /**
     * 添加文章页面
     * @return
     */
    @RequestMapping(value="create",method= RequestMethod.GET)
    public String createArticle(){
        return "article_add";
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @RequestMapping(value="delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id){
        articleService.deleteById(id);
        return Result.success().msg("删除成功");
    }

    /**
     * 保存文章
     * @param article
     * @return
     */
    @RequestMapping(value="save",method = RequestMethod.POST)
    @ResponseBody
    public Result save(Article article){
        articleService.save(article);
        return Result.success().msg("修改成功");
    }

    @RequestMapping(value="enable/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Result enable(@PathVariable("id") Integer id){
        articleService.changeStatus(id, 1);
        return Result.success().msg("修改成功");
    }

    @RequestMapping(value="disable/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Result disable(@PathVariable("id") Integer id){
        articleService.changeStatus(id, 0);
        return Result.success().msg("修改成功");
    }

    @RequestMapping(value="editor/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,ModelMap model){
        Article article = articleService.findById(id);
        model.put("article", article);
        return "article_editor";
    }



}
