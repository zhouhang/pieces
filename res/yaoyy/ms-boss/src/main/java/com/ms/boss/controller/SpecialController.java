package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Commodity;
import com.ms.dao.model.Member;
import com.ms.dao.model.Special;
import com.ms.dao.vo.CommodityVo;
import com.ms.dao.vo.SpecialVo;
import com.ms.service.SpecialCommodityService;
import com.ms.service.SpecialService;
import com.ms.service.enums.RedisEnum;
import com.ms.tools.entity.Result;
import org.apache.commons.lang.StringUtils;
import com.ms.tools.utils.Reflection;
//import org.elasticsearch.common.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    HttpSession httpSession;

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
    public String createSpecial(ModelMap model){
        Special special=new Special();
        model.put("special",special);
        return "special_detail";
    }
    @RequestMapping(value="delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id){
        specialService.deleteById(id);
        return Result.success().msg("修改成功");
    }

    @RequestMapping(value="save",method = RequestMethod.POST)
    @ResponseBody
    public Result save(SpecialVo specialVo){
        Member mem= (Member) httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        specialVo.setUpdateMem(mem.getId());
        specialVo.setCreateMem(mem.getId());
        specialService.save(specialVo);
        return Result.success().msg("修改成功");
    }

    @RequestMapping(value="updateStatus",method = RequestMethod.POST)
    @ResponseBody
    public Result updateStatus(SpecialVo specialVo){
        Member mem= (Member) httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        specialVo.setUpdateMem(mem.getId());
        specialVo.setCreateMem(mem.getId());
        specialService.update(specialVo);
        return Result.success().msg("修改成功");
    }


    @RequestMapping(value="edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id,ModelMap model){
        Special special=specialService.findById(id);
        List<CommodityVo> commodities=specialService.findCommodity(id);
        List<Integer> list=new ArrayList<>();
        if (commodities != null && commodities.size() >0){
            commodities.forEach(sc ->{
                list.add(sc.getId());
            });
        }
        model.put("special",special);
        model.put("commodities",commodities);
        model.put("ids", StringUtils.join(list,","));
        return "special_detail";
    }
}
