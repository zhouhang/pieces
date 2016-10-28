package com.ms.boss.controller;

import com.ms.dao.vo.PickVo;
import com.ms.service.PickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xiao on 2016/10/28.
 */
@Controller
@RequestMapping(value="pick")
public class PickController {

    @Autowired
    private PickService pickService ;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    private String list(PickVo pickVo, ModelMap model){
        return "pick_list";
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    private String list(@PathVariable("id") Integer id,  ModelMap model){
        return "pick_detail";
    }


}
