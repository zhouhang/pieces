package com.jointown.zy.common.dto;

import java.io.Serializable;

import com.jointown.zy.common.util.BeanUtil;
/**
 * 
 * @ClassName: WmsWlSplitDto
 * @Description: 仓单分割成功接口dto
 * @Author: fanyuna
 * @Date: 2015年4月15日
 * @Version: 1.0
 */
public class WmsWlSplitDto implements Serializable {

	//原仓单信息
	private WmsOldWlInfo oldWlInfo;
	
	//新仓单信息
	private WmsNewWlInfo newWlInfo;

	public WmsOldWlInfo getOldWlInfo() {
		return oldWlInfo;
	}

	public void setOldWlInfo(WmsOldWlInfo oldWlInfo) {
		this.oldWlInfo = oldWlInfo;
	}

	public WmsNewWlInfo getNewWlInfo() {
		return newWlInfo;
	}

	public void setNewWlInfo(WmsNewWlInfo newWlInfo) {
		this.newWlInfo = newWlInfo;
	}

	public static void main(String[] args) {
		String json="{\"oldWlInfo\":{\"wlId\":\"001\",\"remainCount\":20,\"contractNum\":\"0001\"},\"newWlInfo\":{\"wlId\":\"002\",\"actualCount\":200,\"username\":\"张三\",\"pWlId\":\"001\",\"orderId\":\"ZYC2015020510022\",\"contractNum\":10001}}";
		WmsWlSplitDto dto = BeanUtil.jsonToObject(json, WmsWlSplitDto.class);
		System.out.println(dto.getNewWlInfo().getContractNum()+"**"+dto.getOldWlInfo().getRemainCount());
	}
	
	
}
