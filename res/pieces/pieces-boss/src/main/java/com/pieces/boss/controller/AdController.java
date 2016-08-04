package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Ad;
import com.pieces.dao.vo.AdVo;
import com.pieces.service.AdService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.CodeEnum;
import com.pieces.tools.utils.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 广告管理
 * Created by wangbin on 2016/8/3.
 */
@Controller
@RequestMapping("/ad")
public class AdController {


    @Autowired
    private AdService adService;

    /**
     * 光谷管理列表页
     * @param model
     * @return
     */
    @RequestMapping("index")
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
     * 保存广告
     * @param ad
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public Result save(Ad ad){
        String message;
        if(ad.getId()==null){
            adService.create(ad);
            message="广告添加成功!";
        }else{
            adService.update(ad);
            message="广告修改成功!";
        }
        return new Result(true).info(message);
    }







}
