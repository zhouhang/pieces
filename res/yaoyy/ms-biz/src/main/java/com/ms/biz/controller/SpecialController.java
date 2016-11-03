package com.ms.biz.controller;

import com.ms.dao.model.Commodity;
import com.ms.dao.model.Special;
import com.ms.dao.vo.CommodityVo;
import com.ms.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Author: koabs
 * 10/26/16.
 * 专场
 */
@Controller
@RequestMapping("special")
public class SpecialController {

    @Autowired
    private SpecialService specialService;

    /**
     * 专场详情页
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id")Integer id, ModelMap model) {
        Special vo = specialService.findById(id);
        List<CommodityVo> commodities = specialService.findCommodity(id);
        model.put("special", vo);
        model.put("commodities", commodities);
        // 根据id查询专场信息
        return "special";
    }
}
