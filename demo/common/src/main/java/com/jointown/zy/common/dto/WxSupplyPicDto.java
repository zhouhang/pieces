package com.jointown.zy.common.dto;

import java.util.ArrayList;
import java.util.List;

import com.jointown.zy.common.model.WxSupplyPic;

public class WxSupplyPicDto {

	/** 图片类型 */
	private int type;
	
	/** 图片路径 */
	private String picPath[];
	
	private List<WxSupplyPic> wxSupplyPics;

	public WxSupplyPicDto() {
		super();
		wxSupplyPics = new ArrayList<WxSupplyPic>();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String[] getPicPath() {
		return picPath;
	}

	public void setPicPath(String[] picPath) {
		this.picPath = picPath;
		for (int i = 0; i < picPath.length; i++) {
			WxSupplyPic wxSupplyPic = new WxSupplyPic();
			Short type = (short) (i+1);
			wxSupplyPic.setType(type);
			wxSupplyPic.setPicPath(picPath[i]);
			wxSupplyPics.add(wxSupplyPic);
		}
	}

	public List<WxSupplyPic> getWxSupplyPics() {
		return wxSupplyPics;
	}

}
