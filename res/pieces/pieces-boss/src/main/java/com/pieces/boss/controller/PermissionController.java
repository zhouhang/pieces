package com.pieces.boss.controller;

import com.pieces.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by burgl on 2016/7/9.
 */
@RequestMapping(value = "permission")
@Controller
public class PermissionController extends BaseController{

    @Autowired
    private RoleService roleService;




}
