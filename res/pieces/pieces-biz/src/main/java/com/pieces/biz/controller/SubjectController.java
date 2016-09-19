package com.pieces.biz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangbin on 2016/9/19.
 */
@Controller
public class SubjectController extends BaseController{


    @RequestMapping(value = "subject/{id}")
    public String index(@PathVariable("id") Integer id){

        return "subject/subject_"+id;
    }


}
