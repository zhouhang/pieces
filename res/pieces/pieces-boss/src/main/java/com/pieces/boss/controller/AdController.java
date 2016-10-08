package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.model.Ad;
import com.pieces.dao.vo.AdVo;
import com.pieces.service.AdService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.CodeEnum;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.Reflection;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 广告管理
 * Created by wangbin on 2016/8/3.
 */
@Controller
@RequestMapping("/ad")
public class AdController extends BaseController{


    @Autowired
    private AdService adService;

    /**
     * 广告管理列表页
     * @param model
     * @return
     */
    @RequiresPermissions(value = "ad:index")
    @RequestMapping("index")
    @BizLog(type = LogConstant.ad, desc = "广告管理列表")
    public String index(Integer pageSize,
                        Integer pageNum,
                        AdVo adVo,
                        ModelMap model){
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        PageInfo<AdVo> adPage= adService.findByParam(adVo,pageNum,pageSize);
        List<CodeEnum> adTypeList = CodeEnum.findByType(CodeEnum.Type.AD);
        String params =  Reflection.serialize(adVo);
        model.put("adPage",adPage);
        model.put("adParams",params);
        model.put("typeList",adTypeList);
        return "ad";
    }

    /**
     * 广告添加
     * @param model
     * @return
     */
    @RequiresPermissions(value = "ad:add")
    @RequestMapping("add")
    @BizLog(type = LogConstant.ad, desc = "广告添加")
    public String add(ModelMap model){
        List<CodeEnum> adTypeList = CodeEnum.findByType(CodeEnum.Type.AD);
        model.put("typeList",adTypeList);
        return "ad-info";
    }

    /**
     * 广告详情页
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "ad:edit")
    @RequestMapping("edit/{id}")
    @BizLog(type = LogConstant.ad, desc = "广告详情")
    public String info(@PathVariable("id") Integer id,
                       ModelMap model){
        List<CodeEnum> adTypeList = CodeEnum.findByType(CodeEnum.Type.AD);
        if(id!=null){
            Ad ad =adService.findById(id);
            model.put("ad",ad);
        }
        model.put("typeList",adTypeList);
        return "ad-info";
    }



    /**
     * 保存广告
     * @param ad
     * @return
     */
    @RequiresPermissions(value = {"ad:add","ad:edit"},logical = Logical.OR)
    @RequestMapping("save")
    @ResponseBody
    @BizLog(type = LogConstant.ad, desc = "广告保存")
    public Result save(Ad ad){
        String message;
        if(ad.getId()==null){
            adService.createAd(ad);
            message="广告添加成功!";
        }else{
            adService.update(ad);
            message="广告修改成功!";
        }
        return new Result(true).info(message).data(ad);
    }


    /**
     * 删除广告
     * @param id
     * @return
     */
    @RequiresPermissions(value = {"ad:add","ad:edit"},logical = Logical.OR)
    @RequestMapping("delete")
    @ResponseBody
    @BizLog(type = LogConstant.ad, desc = "广告删除")
    public Result delete(Integer id){
        adService.deleteById(id);
        return new Result(true).info("删除成功!");
    }





}
