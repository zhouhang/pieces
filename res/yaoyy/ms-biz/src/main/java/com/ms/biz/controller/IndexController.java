package com.ms.biz.controller;

import com.ms.dao.model.Article;
import com.ms.dao.model.Commodity;
import com.ms.dao.model.User;
import com.ms.dao.vo.AdVo;
import com.ms.dao.vo.SendSampleVo;
import com.ms.dao.vo.UserVo;
import com.ms.service.*;
import com.ms.service.enums.RedisEnum;
import com.ms.tools.entity.Result;
import com.ms.tools.exception.ControllerException;
import com.ms.tools.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Author: koabs
 * 10/12/16.
 */
@Controller
@RequestMapping()
public class IndexController {

    @Autowired
    private AdService adService;

    @Autowired
    CommodityService commodityService;


    @Autowired
    SendSampleService sendSampleService;

    @Autowired
    UserService userService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategorySearchService categorySearchService;

    @Autowired
    private CommoditySearchService commoditySearchService;

    /**
     * 首页广告
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        // 首页banner 广告
        // 专场广告
        List<AdVo> banners = adService.findByType(1);
        List<AdVo> specials = adService.findByType(2);
        model.put("banners", banners);
        model.put("specials",specials);
        return "index";
    }

    @RequestMapping(value = "apply/sample/{id}", method = RequestMethod.GET)
    public String apply(@PathVariable("id")Integer commdityId,ModelMap model) {

        Commodity commodity=commodityService.findById(commdityId);
        if (commodity==null){
            throw new NotFoundException("找不到该商品");
        }
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if(user!=null){
            model.put("phone", user.getPhone());
        }
        model.put("commodity", commodity);
        return "apply_sample";
    }


    @RequestMapping(value = "apply/sample", method = RequestMethod.POST)
    @ResponseBody
    public Result applySample(SendSampleVo sendSampleVo) {

        if(sendSampleVo.getIntention()==null){
            return Result.error();
        }
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if(user!=null){
            sendSampleVo.setUserId(user.getId());
        }
        sendSampleService.save(sendSampleVo);
        UserVo userInfo=userService.findByPhone(sendSampleVo.getPhone());

        return Result.success().data(userInfo);
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public String article(@PathVariable("id") Integer id, ModelMap modelMap) {
        Article article = articleService.findById(id);
        modelMap.put("article", article);
        return "article";
    }



    @RequestMapping(value = "/assets/test", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public String article() {
        Article article = new Article();
        article.setTitle("111");
        article.setContent("1111C");
        article.setStatus(1);
        articleService.save(article);
        if (true) {
            throw new RuntimeException("失败");
        }
        Article article2 = new Article();
        article2.setTitle("222");
        article2.setContent("222C");
        article2.setStatus(1);
        articleService.create(article2);

        return "article";
    }
   /*
    @RequestMapping(value = "/create/index", method = RequestMethod.GET)
    @ResponseBody
    public Result createIndex(){
        categorySearchService.createAllCategoryDoc();
        commoditySearchService.createAllCommodityDoc();
        return Result.success("创建索引成功");
    }*/




    @RequestMapping(value = "/create/index", method = RequestMethod.GET)
    @ResponseBody
    public Result createIndex(){
        if (true)
        throw new ControllerException("sss");
       return Result.error();
    }


}
