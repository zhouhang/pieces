package com.jointown.zy.common.vo;

import com.jointown.zy.common.model.InfoWarehousModel;

/**
 * @ClassName:InfoWarehousModel
 * @author:Calvin.Wangh
 * @date:2015-6-10上午10:03:25
 * @version V1.0
 * @Description:入仓信息管理Vo
 */
public class InfoWarehousVo extends InfoWarehousModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String breed_Name;

	public String getBreed_Name() {
		return breed_Name;
	}

	public void setBreed_Name(String breed_Name) {
		this.breed_Name = breed_Name;
	}

}