package com.jointown.zy.common.dto;

import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.util.TimeUtil;

/**
 * 新增仓单Dto
 * @author wangjunhu
 * 2014-12-25
 */
public class BusiWhlistDto {
	
	//类目ID
	private Long categoryid;
	//品种ID
	private Long breedcode;
	private Long userid;
	private String account;
	private String wlid;
	private String wlrkdate;
	private Integer wlstate;
	private String origin;
	private Double wltotal;
	private String wlunit;
	private String contractnum;
	private String warehouseid;
	private String areaid;
	private String packingway;
	private Integer wlflag;
	
	//仓单model
	private BusiWhlist busiwhlist;

	public BusiWhlistDto() {
		super();
		busiwhlist = new BusiWhlist();
		busiwhlist.setWlFlag(0);
	}

	
	public Long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
		busiwhlist.setCategoryId(categoryid);
	}

	public Long getBreedcode() {
		return breedcode;
	}

	public void setBreedcode(Long breedcode) {
		this.breedcode = breedcode;
		busiwhlist.setBreedCode(breedcode);
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
		busiwhlist.setUserId(userid);
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
		busiwhlist.setAccount(account);
	}

	public String getWlid() {
		return wlid;
	}

	public void setWlid(String wlid) {
		this.wlid = wlid;
		busiwhlist.setWlId(wlid);
	}

	public String getWlrkdate() {
		return wlrkdate;
	}

	public void setWlrkdate(String wlrkdate) {
		this.wlrkdate = wlrkdate;
		if(!wlrkdate.isEmpty()){
			busiwhlist.setWlrkDate(TimeUtil.parseYMDHM(wlrkdate));
		}
	}

	public Integer getWlstate() {
		return wlstate;
	}

	public void setWlstate(Integer wlstate) {
		busiwhlist.setWlState(wlstate);
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
		busiwhlist.setOrigin(origin);
	}

	public Double getWltotal() {
		return wltotal;
	}

	public void setWltotal(Double wltotal) {
		this.wltotal = wltotal;
		busiwhlist.setWlTotal(wltotal);
	}

	public String getWlunit() {
		return wlunit;
	}

	public void setWlunit(String wlunit) {
		this.wlunit = wlunit;
		busiwhlist.setWlUnit(wlunit);
	}

	public String getContractnum() {
		return contractnum;
	}

	public void setContractnum(String contractnum) {
		this.contractnum = contractnum;
		busiwhlist.setContractNum(contractnum);
	}

	public String getWarehouseid() {
		return warehouseid;
	}

	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
		busiwhlist.setWareHouseId(warehouseid);
	}
	
	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
		busiwhlist.setAreaId(areaid);
	}

	public String getPackingway() {
		return packingway;
	}

	public void setPackingway(String packingway) {
		this.packingway = packingway;
		busiwhlist.setPackingWay(packingway);
	}

	public Integer getWlflag() {
		return wlflag;
	}

	public void setWlflag(Integer wlflag) {
		this.wlflag = wlflag;
	}

	public BusiWhlist getBusiwhlist() {
		return busiwhlist;
	}

	public void setBusiwhlist(BusiWhlist busiwhlist) {
		this.busiwhlist = busiwhlist;
	}
}
