package com.jointown.zy.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jointown.zy.common.model.EastYaocai;
import com.jointown.zy.common.service.EastYaocaiService;

/**
 * 药材百科Controller
 * 
 * @author wangjunhu
 *
 * @data 2015年3月5日
 */
@Controller(value="wxEastYaocaiController")
public class WxEastYaocaiController extends WxUserBaseController {
	
	@Autowired
	private EastYaocaiService eastYaocaiService;
	
	@RequestMapping(value = "/getEastYaocaiPzs", method = RequestMethod.GET)
	public String getEastYaocaiPzs(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		TreeMap<String,List<EastYaocai>> treeMap = new TreeMap<String,List<EastYaocai>>();
		String jianpinFlags[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		for (String jianpinFlag : jianpinFlags) {
			List<EastYaocai> eastYaocaiList = new ArrayList<EastYaocai>();
			treeMap.put(jianpinFlag, eastYaocaiList);
		}
		List<EastYaocai> eastYaocais = eastYaocaiService.findEastYaocaiPzs();
		for (EastYaocai eastYaocai : eastYaocais) {
			String jianpin = eastYaocai.getJianpin();
			if(jianpin!=null&&!jianpin.isEmpty()){
				String jianpinFlag = String.valueOf(jianpin.charAt(0));
				List<EastYaocai> eastYaocaiList = (List<EastYaocai>) treeMap.get(jianpinFlag);
				if(eastYaocaiList==null){
					eastYaocaiList = new ArrayList<EastYaocai>();
					eastYaocaiList.add(eastYaocai);
					treeMap.put(jianpinFlag, eastYaocaiList);
				}else{
					eastYaocaiList.add(eastYaocai);
				}
			}
		}
		
		model.put("eastYaocaiMap", treeMap);
		return "/eastYaocaiPzs";
	}
}
