package com.pieces.boss.controller;

import com.pieces.service.AdService;
import com.pieces.service.enums.CodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by wangbin on 2016/8/3.
 */
@Controller
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;


    @RequestMapping("index")
    public String index(){

        List<CodeEnum> adTypeList = CodeEnum.findByType(CodeEnum.Type.AD);


        return "ad";
    }



}
