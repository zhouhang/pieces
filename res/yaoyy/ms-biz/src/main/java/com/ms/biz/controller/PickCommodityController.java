package com.ms.biz.controller;

import com.ms.dao.model.User;
import com.ms.dao.vo.PickVo;
import com.ms.dao.vo.UserVo;
import com.ms.service.PickService;
import com.ms.service.UserService;
import com.ms.service.enums.RedisEnum;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xiao on 2016/11/3.
 */
@Controller
@RequestMapping("pickCommodity/")
public class PickCommodityController {
    @Autowired
    private PickService pickService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserService userService;


    /**
     * 未提交选货单之前的商品列表
     * @return
     */
    @RequestMapping(value="list",method= RequestMethod.GET)
    public String commodityList() throws Exception {
        return "pick_commodity";
    }


    /**
     * 提交选货单
     * @param pickVo
     * @return
     */
    @RequestMapping(value="save",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result deleteCommodity(@RequestBody PickVo pickVo){

        //没注册和申请寄样一样处理

        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if(user!=null){
            pickVo.setUserId(user.getId());
        }
        pickService.save(pickVo);

        UserVo userInfo=userService.findByPhone(pickVo.getPhone());



        return Result.success().data(userInfo);
    }
}
