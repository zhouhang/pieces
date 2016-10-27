package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Special;
import com.ms.dao.vo.SpecialVo;
import com.ms.service.SpecialCommodityService;
import com.ms.service.SpecialService;
import com.ms.tools.entity.Result;
import com.ms.tools.utils.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xiao on 2016/10/27.
 */
@Controller
@RequestMapping("special/")
public class SpecialController {

    @Autowired
    SpecialService specialService;

    @Autowired
    SpecialCommodityService specialCommodityService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(SpecialVo specialVo,Integer pageNum,Integer pageSize,
                       ModelMap model){

        PageInfo<SpecialVo> specialVoPageInfo = specialService.findByParams(specialVo,pageNum,pageSize);

        model.put("specialVoPageInfo",specialVoPageInfo);
        model.put("specialVoParams", Reflection.serialize(specialVo));
        model.put("specialVo",specialVo);

        return "special_list";

    }
    @RequestMapping(value="create",method= RequestMethod.GET)
    public String createSpecial(){
        return "special_detail";
    }
    @RequestMapping(value="delete/{id}",method = RequestMethod.POST)
    public Result delete(@PathVariable("id") Integer id){
        specialService.deleteById(id);
        return Result.success().msg("修改成功");
    }

    @RequestMapping(value="save",method = RequestMethod.POST)
    public Result save(SpecialVo specialVo){
        specialService.save(specialVo);
        return Result.success().msg("修改成功");
    }

    @RequestMapping(value="edit/{id}",method = RequestMethod.POST)
    public String edit(@PathVariable("id") Integer id,ModelMap model){
        Special special=specialService.findVoById(id);
        return "special_detail";
    }
}
