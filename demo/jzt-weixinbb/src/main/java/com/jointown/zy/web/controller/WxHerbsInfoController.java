package com.jointown.zy.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jointown.zy.common.model.BusiQualitypic;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.vo.BusiWhlistVo;

/**
 * 药材信息，用于WMS二维码扫描
 *
 * @author aizhengdong
 * @date 2015年8月31日
 */
@Controller
@RequestMapping(value="/herbsInfo")
public class WxHerbsInfoController extends WxUserBaseController {
	@Autowired
	private BusiWhlistService busiWhlistService;
	
	@Autowired
	private SftpConfigInfo sftpConfigInfo ;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getHerbsInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			String wlId = request.getParameter("wlId");
			
			if(!StringUtils.isBlank(wlId)){
				BusiWhlistVo wxWhlist = busiWhlistService.findBusiWhlistById(wlId, null);
				if(wxWhlist != null){
					int sftpXXBigWidth = sftpConfigInfo.getSftpXXBigWidth();
					List<BusiQualitypic> piclists = wxWhlist.getPiclist();
					for (BusiQualitypic piclist : piclists) {
						String path = piclist.getPath();
						path = path.substring(0,path.lastIndexOf(".")) + "_" + sftpXXBigWidth+path.substring(path.lastIndexOf("."));
						piclist.setPath(path);
					}
				}
				
				model.put("wxWhlist", wxWhlist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "herbs_info";
		
	}

}
