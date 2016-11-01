package com.ms.biz.controller;

import com.ms.dao.model.PickCommodity;
import com.ms.service.PickCommodityService;
import com.ms.service.PickService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by xiao on 2016/11/1.
 */
@Controller
@RequestMapping("pick/")
public class PickController {


    @Autowired
    private PickService pickService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private PickCommodityService pickCommodityService;



    @RequestMapping(value="list",method= RequestMethod.GET)
    public String list(ModelMap model){
        return "pick_list";
    }

    @RequestMapping(value="detail/{id}",method=RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, ModelMap model){
        return "pick_detail";
    }

    @RequestMapping(value="commodityList",method=RequestMethod.GET)
    public String commodityList(ModelMap model){
        return "pick_commodity";
    }

    @RequestMapping(value="add",method=RequestMethod.POST)
    @ResponseBody
    public Result addCommodity(Integer id,ModelMap model){
        return Result.success().data("添加成功");
    }

    @RequestMapping(value="delete",method=RequestMethod.POST)
    @ResponseBody
    public Result deleteCommodity(Integer id,ModelMap model){
        return Result.success().data("删除成功");
    }



    @RequestMapping(value="submit",method=RequestMethod.POST)
    @ResponseBody
    public Result deleteCommodity(@RequestBody List<PickCommodity> list, ModelMap model){
        return Result.success().data("提交成功");
    }



}
