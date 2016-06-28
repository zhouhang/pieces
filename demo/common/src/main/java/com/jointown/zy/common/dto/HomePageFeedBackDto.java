package com.jointown.zy.common.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.jointown.zy.common.exception.ErrorRepository;

/**
 * 统一任务(首页反馈信息)Dto
 *
 * @author ff
 *
 * @data 2015年11月02日
 */
public class HomePageFeedBackDto extends BaseDto{

	/** 编号：任务ID */
    private Long homePageId;
    /** 业务点 */
    private String type;
    
    /** 内容  */
    private String content;
    
    
    /** 状态 */
    private String status;
    
    
    /** 开始时间*/
    private String startDate;
    
    /** 结束时间*/
    private String endDate;
    
    /** 备注*/
    private String remark;
    
    /** 手机号码 */
    private String mobile;
    
    //意见反馈者
    private String userName;
    
    //反馈类型
    private String ideaType;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getHomePageId() {
		return homePageId;
	}

	public void setHomePageId(Long homePageId) {
		this.homePageId = homePageId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdeaType() {
		return ideaType;
	}

	public void setIdeaType(String ideaType) {
		this.ideaType = ideaType;
	}



	private static final String MOB_REGX = "^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$|177[0-9]{8}$";
	private static final String REMARK_REGX = "^[\\s|\\S]{0,500}$";
	
	
	public Map<String,Object> validateFiled(){
		Map<String,Object> map = new HashMap<String,Object>(); 
		String msg = "";
		//ie下去掉默认字符
		if(content.equals("请详细填写采购需求，包括采购品种、规格等级、数量等。")
				||content.equals("请详细填写卖货需求，包括药材品种、规格等级、数量等。")
				||content.equals("请写下您的融资需求，包括药材品种、融资金额，融资期限。")
				||content.equals("写下您的融资需求，包括药材品种、融资金额，融资期限，联系电话等，收到后我们会立即回电与您确认，剩下就交给融资小珍吧：）")){
			content = "";
		}
		if(mobile!=null&&mobile.equals("请留下您的电话，以便我们及时回复您")){
			mobile = "";
		}
		 //验证备注】
		if (null == content || "".equals(content)) {
			if(type.equals("1")){
				msg = msg+"请填写采购需求!";
			}else if(type.equals("2")){
				msg = msg+"请填写卖货需求!";
			}else if(type.equals("3") || type.equals("4")){
				msg = msg+"请填写融资需求!";
			}
		}else if(!Pattern.compile(REMARK_REGX).matcher(content).matches()){
			msg = msg+"需求不能多于500个字符!";
        }
		
        //验证手机号
		if((type.equals("1") || type.equals("2") || type.equals("3"))&&msg.equals("")){
			if (null == mobile || "".equals(mobile)) {
				if(msg.equals("")){
					msg = msg+"请填写联系电话!";
				}else{
					msg = msg+"并请填写联系电话!";
				}
			}else if(!Pattern.compile(MOB_REGX).matcher(mobile).matches()) {
				if(msg.equals("")){
					msg = msg+"手机号格式不正确!";
				}else{
					msg = msg+"且手机号格式不正确!";
				}
			}
		}
       
        //验证通过
		if(!msg.equals("")){
			map.put("ok", "error");
			map.put("msg", msg);
		}
		return map;
	}
}