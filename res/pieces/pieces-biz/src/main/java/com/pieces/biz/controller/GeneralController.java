package com.pieces.biz.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Area;
import com.pieces.service.AreaService;
import com.pieces.tools.bean.FileBo;
import com.pieces.tools.upload.DefaultUploadFile;
import com.pieces.tools.utils.GsonUtil;
import com.pieces.tools.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangbin on 2016/6/27.
 */
@Controller
@RequestMapping(value = "gen")
public class GeneralController {

    @Autowired
    private DefaultUploadFile defaultUploadFile;
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/file/index")
    public String index(){
        return "public/fileUploadTest";
    }

    @RequestMapping(value = "/file/upload")
    public void fileUpload(HttpServletRequest request,
                            HttpServletResponse response,
                            @RequestParam(required=false) MultipartFile file){
        try {
            FileBo fileBo =  defaultUploadFile.uploadFile(file.getOriginalFilename(),file.getInputStream());
            System.out.println(fileBo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 省市区接口
     * @param request
     * @param response
     * @param parentId
     */
    @RequestMapping(value = "/area")
    public void area(HttpServletRequest request,
                     HttpServletResponse response,
                     @RequestParam(required=false) Integer parentId){
        List<Area> areaList = null;
        if(parentId==null){
            areaList = areaService.findByLevel(1);
        }else{
            areaList = areaService.findByParent(parentId);
        }

        String result = GsonUtil.toJsonInclude(areaList,"id","areaname");
        WebUtil.printJson(response,result);
    }


    @RequestMapping(value = "/area/page")
    public void areaPage(HttpServletRequest request,
                     HttpServletResponse response,
                     Integer pageNum,
                     Integer pageSize){
        PageInfo<Area> page = areaService.find(pageNum,pageSize);
        String result = GsonUtil.toJson(page);
        WebUtil.printJson(response,result);
    }

}
