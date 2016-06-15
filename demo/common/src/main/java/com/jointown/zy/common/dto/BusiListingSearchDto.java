package com.jointown.zy.common.dto;

/**
 * 挂牌查询Dto
 * @author wangjunhu
 * 2015-1-2
 */
public class BusiListingSearchDto extends BaseDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String wlid;
	private String listingid;
	private String title;
	private String listingtimelimit;
	private String startlistingdate;
	private String startoverduedate;
	private String endoverduedate;
	private String endlistingdate;
	private String listingflag;
	private String userinfo;
	private String username;
	private String usermobile;
	//业务员
	private String salesman;
	//品种
  	private String breedname;
	
	public String getListingid() {
		return listingid;
	}
	public void setListingid(String listingid) {
		this.listingid = listingid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getListingtimelimit() {
		return listingtimelimit;
	}
	public void setListingtimelimit(String listingtimelimit) {
		this.listingtimelimit = listingtimelimit;
	}
	public String getStartlistingdate() {
		return startlistingdate;
	}
	public void setStartlistingdate(String startlistingdate) {
		this.startlistingdate = startlistingdate;
	}
	public String getEndlistingdate() {
		return endlistingdate;
	}
	public void setEndlistingdate(String endlistingdate) {
		this.endlistingdate = endlistingdate;
	}
	public String getListingflag() {
		return listingflag;
	}
	public void setListingflag(String listingflag) {
		this.listingflag = listingflag;
	}
	public String getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsermobile() {
		return usermobile;
	}
	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}
	public String getWlid() {
		return wlid;
	}
	public void setWlid(String wlid) {
		this.wlid = wlid;
	}
	public String getStartoverduedate() {
		return startoverduedate;
	}
	public void setStartoverduedate(String startoverduedate) {
		this.startoverduedate = startoverduedate;
	}
	public String getEndoverduedate() {
		return endoverduedate;
	}
	public void setEndoverduedate(String endoverduedate) {
		this.endoverduedate = endoverduedate;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getBreedname() {
		return breedname;
	}
	public void setBreedname(String breedname) {
		this.breedname = breedname;
	}
}
