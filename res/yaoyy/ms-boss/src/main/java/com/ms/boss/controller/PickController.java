package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Pick;
import com.ms.dao.model.PickTracking;
import com.ms.dao.model.UserDetail;
import com.ms.dao.vo.CommodityVo;
import com.ms.dao.vo.PickCommodityVo;
import com.ms.dao.vo.PickTrackingVo;
import com.ms.dao.vo.PickVo;
import com.ms.service.*;
import com.ms.tools.entity.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao on 2016/10/28.
 */
@Controller
@RequestMapping(value="pick")
public class PickController {

    @Autowired
    private PickService pickService ;

    @Autowired
    private PickCommodityService pickCommodityService;

    @Autowired
    private PickTrackingService pickTrackingService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private CommodityService commodityService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    private String list(PickVo pickVo,Integer pageNum, Integer pageSize, ModelMap model){
        PageInfo<PickVo> pickVoPageInfo = pickService.findByParams(pickVo, pageNum, pageSize);
        model.put("pickVoPageInfo", pickVoPageInfo);
        return "pick_list";
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    private String list(@PathVariable("id") Integer id,  ModelMap model){
        PickVo pickVo=pickService.findVoById(id);
        List<PickCommodityVo> pickCommodityVos=pickCommodityService.findByPickId(id);
        float total=0;

        for(PickCommodityVo vo :pickCommodityVos){
            total+=vo.getTotal();
        }
        pickVo.setTotal(total);

        pickVo.setPickCommodityVoList(pickCommodityVos);
        UserDetail userDetail=userDetailService.findByUserId(pickVo.getUserId());
        if(userDetail==null){
            userDetail=new UserDetail();
        }

        List<PickTrackingVo> pickTrackingVos=pickTrackingService.findByPickId(id);
        model.put("pickVo",pickVo);
        model.put("userDetail",userDetail);
        model.put("pickTrackingVos",pickTrackingVos);
        return "pick_detail";
    }

    @RequestMapping(value="delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    private Result delete(@PathVariable("id") Integer id){
        Pick pick=new Pick();
        pick.setId(id);
        //废弃
        pick.setStatus(-1);
        pickService.update(pick);
        return Result.success().msg("操作成功");
    }





}
