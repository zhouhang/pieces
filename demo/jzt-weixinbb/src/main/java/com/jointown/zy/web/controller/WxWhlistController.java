package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.dto.BusiWhlistSearchDto;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.exception.WxErrorException;
import com.jointown.zy.common.model.BusiQualitypic;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiWareHouseService;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.service.WxAdService;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.vo.BusiWareHouseVo;
import com.jointown.zy.common.vo.BusiWhlistVo;
import com.jointown.zy.common.vo.UcUserVo;
import com.jointown.zy.common.vo.WxAdVo;

/**
 * 
 * @ClassName: WxWhlistController
 * @Description: 我的仓单
 * @Author: wangjunhu
 * @Date: 2015年7月20日
 * @Version: 1.0
 */
@Controller
@RequestMapping(value="/wxWhlist")
public class WxWhlistController extends WxUserBaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(WxWhlistController.class);
	
	@Autowired
	private BusiWhlistService busiWhlistService;
	@Autowired
	private BusiWareHouseService busiWareHouseService;
	@Autowired
	private WxAdService wxAdService;
	@Autowired
	private SftpConfigInfo sftpConfigInfo ;
	
	/**
	 * 
	 * @Description: 我的仓单列表查询
	 * @Author: wangjunhu
	 * @Date: 2015年7月22日
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wxWhlistManage", method = RequestMethod.GET)
	public String wxWhlistManage(@ModelAttribute(value="wxWhlistSearchDto") BusiWhlistSearchDto wxWhlistSearchDto, ModelMap model) {
		//验证登陆
		UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
		if (user==null) {
			WxAdVo wxAd = wxAdService.findWxAdsByPostionName("登录页(720*601)");
			model.put("wxAd", wxAd);
			return "/login";
		}
		//我的仓单
		Page<BusiWhlistVo> wxWhlistPage = new Page<BusiWhlistVo>();
		wxWhlistPage.setPageNo(1);
		wxWhlistPage.setPageSize(5);
		Map<String,Object> wxWhlistParams = new HashMap<String,Object>();
		Long userId = user.getUserId();
		wxWhlistParams.put("userId", userId);
		wxWhlistParams.put("busiWhlistSearchDto", wxWhlistSearchDto);
		wxWhlistPage.setParams(wxWhlistParams);
		List<BusiWhlistVo> wxWhlists = busiWhlistService.findBusiWhlistsByCondition(wxWhlistPage);
		/** add by Mr.song 2015.8.28 每次分页记录集要保存到大的page里面，好计算页码，当前页码等 start***********/
		wxWhlistPage.setResults(wxWhlists);
		model.put("page", wxWhlistPage);
		/** add by Mr.song 2015.8.28 每次分页记录集要保存到大的page里面，好计算页码，当前页码等 end***********/
		//仓库列表
		List<BusiWareHouseVo> wxWarehouses = busiWareHouseService.findBusiWareHousesByTree();
		
		model.put("wxWhlistSearchDto", wxWhlistSearchDto);
		/**屏蔽**********************************/
		//model.put("wxWhlists", wxWhlists);
		model.put("wxWarehouses", wxWarehouses);
		model.put("timestamp", getTimestamp()); //生成时间戳，方便资源文件实时无缓存获取
		return "/my-store";
	}
	
	/**
	 * 
	 * @Description: 查看我的仓单详情，ID查询
	 * @Author: wangjunhu
	 * @Date: 2015年7月23日
	 * @param wlId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getWxWhlist", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getWxWhlist(@RequestParam(value="wlId",required=true) String wlId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			if(wlId==null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			//验证信息是否存在
			Long userId = user.getUserId();
			BusiWhlistVo wxWhlist = busiWhlistService.findBusiWhlistById(wlId,userId);
			if(wxWhlist!=null){
				//显示缩略图
				int sftpXXBigWidth = sftpConfigInfo.getSftpXXBigWidth();
				List<BusiQualitypic> piclists = wxWhlist.getPiclist();
				for (BusiQualitypic piclist : piclists) {
					String path = piclist.getPath();
					path = path.substring(0,path.lastIndexOf("."))+"_"+sftpXXBigWidth+path.substring(path.lastIndexOf("."));
					piclist.setPath(path);
				}
				map.put("ok", true);
				map.put("wxWhlist", wxWhlist);
			}else{
				map.put("ok", false);
				map.put("msg", "加载失败！");
			}
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "加载失败！");
			}
			logger.error("WxWhlistController.getWxWhlist："+msg);
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 查询更多的我的仓单列表，分页查询
	 * @Author: wangjunhu
	 * @Date: 2015年7月22日
	 * @param pageNo
	 * @param wxWhlistSearchDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMoreWxWhlists", method = RequestMethod.GET)
	@ResponseBody
	public Page<BusiWhlistVo> getMoreWxWhlists(@RequestParam(value="pageNo",required=true,defaultValue="1") Integer pageNo,@ModelAttribute(value="wxWhlistSearchDto") BusiWhlistSearchDto wxWhlistSearchDto) throws Exception {
		//分页条件查询
		Page<BusiWhlistVo> wxWhlistPage = new Page<BusiWhlistVo>();
		try {
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			if(pageNo==null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			wxWhlistPage.setPageNo(pageNo);
			wxWhlistPage.setPageSize(ConfigConstant.WX_USER_CENTER_PAGE_SIZE);
			Map<String,Object> wxWhlistParams = new HashMap<String,Object>();
			Long userId = user.getUserId();
			wxWhlistParams.put("userId", userId);
			wxWhlistParams.put("busiWhlistSearchDto", wxWhlistSearchDto);
			wxWhlistPage.setParams(wxWhlistParams);
			List<BusiWhlistVo> wxWhlists = busiWhlistService.findBusiWhlistsByCondition(wxWhlistPage);
			wxWhlistPage.setResults(wxWhlists);
		} catch (Exception e) {
			logger.error("WxWhlistController.getMoreWxWhlists error：{}"+e.getMessage());
		}
		return wxWhlistPage;
	}
}
