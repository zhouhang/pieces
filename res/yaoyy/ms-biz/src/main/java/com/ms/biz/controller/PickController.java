package com.ms.biz.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Commodity;
import com.ms.dao.model.PickCommodity;
import com.ms.dao.model.User;
import com.ms.dao.model.UserDetail;
import com.ms.dao.vo.PickCommodityVo;
import com.ms.dao.vo.PickTrackingVo;
import com.ms.dao.vo.PickVo;
import com.ms.service.CommodityService;
import com.ms.service.PickCommodityService;
import com.ms.service.PickService;
import com.ms.service.PickTrackingService;
import com.ms.service.enums.RedisEnum;
import com.ms.tools.entity.Result;
import com.ms.tools.utils.CookieUtils;
import com.ms.tools.utils.GsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xiao on 2016/11/1.
 */
@Controller
@RequestMapping("pick/")
public class PickController {


    @Autowired
    private PickService pickService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private PickTrackingService pickTrackingService;

    @Autowired
    private PickCommodityService pickCommodityService;

    @Autowired
    private CommodityService commodityService;

    private static final int COOKIE_EXPIRE = 3600;

    private static final String  PICK_COMMODITY_COOKIES="pick_commodity_cookies";


    /**
     * 选货单列表
     * @param model
     * @return
     */
    @RequestMapping(value="list",method= RequestMethod.GET)
    public String list(Integer pageNum, Integer pageSize,ModelMap model){
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        PickVo pickVo=new PickVo();
        pickVo.setUserId(user.getId());
        PageInfo<PickVo> pickVoPageInfo=pickService.findByParams(pickVo,pageNum,pageSize);
        model.put("pickVoPageInfo",pickVoPageInfo);

        return "pick_list";
    }

    /**
     * 选货单详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="detail/{id}",method=RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, ModelMap model){
        PickVo pickVo=pickService.findVoById(id);
        List<PickCommodityVo> pickCommodityVos=pickCommodityService.findByPickId(id);
        float total=0;

        for(PickCommodityVo vo :pickCommodityVos){
            total+=vo.getTotal();
        }
        pickVo.setTotal(total);

        pickVo.setPickCommodityVoList(pickCommodityVos);


        List<PickTrackingVo> pickTrackingVos=pickTrackingService.findByPickId(id);

        model.put("pickVo",pickVo);
        model.put("pickTrackingVos",pickTrackingVos);

        return "pick_detail";
    }

    /**
     * 未提交选货单之前的商品列表
     * @param model
     * @return
     */
    @RequestMapping(value="commodityList",method=RequestMethod.GET)
    public String commodityList(HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {
        List<PickCommodityVo> cookieList=null;

        String cookieValue = CookieUtils.getCookieValue(request, PICK_COMMODITY_COOKIES);
        if(StringUtils.isBlank(cookieValue)){
            cookieList = new ArrayList<>();
        }else{
            cookieList= GsonUtil.jsonToEntity(cookieValue,List.class);
        }
        cookieList.forEach(c->{
                  Commodity commodity=commodityService.findById(c.getCommodityId());
                  c.setName(commodity.getName());
                  c.setOrigin(commodity.getOrigin());
                  c.setPrice(commodity.getPrice());
                  c.setSpec(commodity.getSpec());
                }
        );
        model.put("commodityList",cookieList);

        CookieUtils.setCookie(response, PICK_COMMODITY_COOKIES, GsonUtil.toJson(cookieList),COOKIE_EXPIRE);
        return "pick_commodity";
    }


    /**
     * 未提交选货单 增加商品
     * @param pickCommodityVo
     * @return
     */

    @RequestMapping(value="add",method=RequestMethod.POST)
    @ResponseBody
    public Result addCommodity(HttpServletRequest request, HttpServletResponse response,PickCommodityVo pickCommodityVo) throws Exception {
        List<PickCommodityVo> cookieList=null;
        String cookieValue = CookieUtils.getCookieValue(request, PICK_COMMODITY_COOKIES);
        if(StringUtils.isBlank(cookieValue)){
            cookieList = new ArrayList<>();
        }else{
            cookieList= GsonUtil.jsonToEntity(cookieValue,List.class);
        }
        for(int i=0;i<cookieList.size();i++){
            if(pickCommodityVo.getCommodityId()==cookieList.get(i).getCommodityId()){
                //不用重复添加
                return Result.success().data("添加成功");
            }
        }
        cookieList.add(pickCommodityVo);
        CookieUtils.setCookie(response, PICK_COMMODITY_COOKIES, GsonUtil.toJson(cookieList),COOKIE_EXPIRE);
        return Result.success().data("添加成功");
    }

    /**
     * 未提交选货单 删除商品
     * @param pickCommodityVo
     * @return
     */
    @RequestMapping(value="delete",method=RequestMethod.POST)
    @ResponseBody
    public Result deleteCommodity(HttpServletRequest request, HttpServletResponse response,PickCommodityVo pickCommodityVo) throws Exception {
        List<PickCommodityVo> cookieList=null;
        String cookieValue = CookieUtils.getCookieValue(request, PICK_COMMODITY_COOKIES);
        if(StringUtils.isBlank(cookieValue)){
            cookieList = new ArrayList<>();
        }else{
            cookieList= GsonUtil.jsonToEntity(cookieValue,List.class);
        }
        for(int i=0;i<cookieList.size();i++){
            if(pickCommodityVo.getCommodityId()==cookieList.get(i).getCommodityId()){
                cookieList.remove(i);
            }
        }

        CookieUtils.setCookie(response, PICK_COMMODITY_COOKIES, GsonUtil.toJson(cookieList),COOKIE_EXPIRE);
        return Result.success().data("删除成功");
    }


    /**
     * 提交选货单
     * @param list
     * @param model
     * @return
     */
    @RequestMapping(value="submit",method=RequestMethod.POST)
    @ResponseBody
    public Result deleteCommodity(@RequestBody List<PickCommodity> list, ModelMap model){

        //没注册应该引导输入手机号后台生成用户

        return Result.success().data("提交成功");
    }



}
