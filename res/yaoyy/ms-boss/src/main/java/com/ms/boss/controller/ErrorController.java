package com.ms.boss.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: koabs
 * 10/12/16.
 */
@Controller
class BossErrorController implements ErrorController {

    private static final String ERROR_PATH= "/error";

    @RequestMapping(value = ERROR_PATH)
    public String handleError() {
        return "";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
