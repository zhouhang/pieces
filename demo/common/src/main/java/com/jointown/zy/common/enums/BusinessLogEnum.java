package com.jointown.zy.common.enums;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.RangeVo;

/**
 * 
 * 描述： 交易日志枚举<br/>
 * 
 * 日期： 2015年3月16日<br/>
 * 
 * 作者： Mr.songwei<br/>
 *
 * 版本： V1.0<br/>
 */
public enum BusinessLogEnum {
	
	/******************************************************************************************************
	 ***************************************************仓单日志**********************************************
	 *****************************************************************************************************/
	/** 后台审核挂牌：{0} 失败，归还仓单可挂牌数量，由：{1} 变为：{2} */
	WHLIST_UPDATE_LISTING_AUDIT_FAIL("1", "审核挂牌：{0} 失败，归还仓单可挂牌数量，由：{1} 变为：{2}"),
	/** 前台新增挂牌：{0}，仓单可挂牌数量减少，由：{1} 变为：{2} */
	WHLIST_UPDATE_LISTING_ADD("2", "新增挂牌：{0}，仓单可挂牌数量减少，由：{1} 变为：{2}"),
	/** 前台取消挂牌：{0}，归还仓单可挂牌数量，由：{1} 变为：{2} */
	WHLIST_UPDATE_LISTING_CANCEL("3", "取消挂牌：{0}，归还仓单可挂牌数量，由：{1} 变为：{2}"),
	/** 仓单修改，订单：{0} 支付，扣除仓单可挂数量，由：{1} 变为：{2} */
	WHLIST_UPDATE_ORDER_PAID("4", "仓单修改，订单：{0} 支付，挂牌已下架，扣除仓单可挂数量，由：{1} 变为：{2}"),
	/** 仓单修改，订单：仓单修改，订单：{0} 取消，挂牌已下架，归还仓单可挂数量，由：{1} 变为：{2} */
	WHLIST_UPDATE_ORDER_CANCEL("5", "仓单修改，订单：{0} 取消，挂牌已下架，归还仓单可挂数量，由：{1} 变为：{2}"),
	/** 新增仓单 */
	WHLIST_ADD("6", "新增仓单"),
	/** 仓单分割 */
	WHLIST_SPLIT("7", "仓单分割，仓单总量，由：{0} 变为：{1}；分隔出来的子仓单为：{2}，子仓单总量为：{3}"),
	/** 货物出库，仓单总量，由：{1} 变为：{2}；仓单可挂数量，由：{3} 变为：{4} */
	WHLIST_OUT("8", "货物出库，仓单总量，由：{0} 变为：{1}；仓单可挂数量，由：{2} 变为：{3}"),
	/** 修改仓单基本信息、质检信息或图片 */
	WHLIST_UPDATE("9","修改仓单相关信息"),
	/**当交易数量（即冻结数量）与买家购买的数量不一致时，如果挂牌取消了，则将差值返还到可挂数量*/
	WHLIST_UPDATE_FREEZE_LISTING_CANCEL("10", "订单：{0}，交易数量（即冻结数量）与买家购买的数量不一致，挂牌：{1}取消了，归还差值至仓单可挂牌数量，由：{2} 变为：{3}"),
	/******************************************************************************************************
	 ***************************************************挂牌日志**********************************************
	 *****************************************************************************************************/
	/** 挂牌后台审核通过 */
	LISTINGPASS("1", "挂牌后台审核通过"),
	/** 挂牌后台审核不通过 */
	LISTINGNOTPASS("2", "挂牌后台审核不通过"),
	/** 前台挂牌，待后台审核 */
	LISTING("3", "前台挂牌，待后台审核"),
	/** 前台取消挂牌 */
	STOPLISTING("4", "下架挂牌"),
	/** 挂牌商品售罄，已卖完 */
	LISTINGSOLDOUT("5", "挂牌商品售罄，已卖完"),
	/** 修改挂牌信息 */
	LISTINGUPDATE("6", "修改挂牌信息"),
	/** 程序异常 */
	LISTINGERROR("7", "程序异常，未知错误"),
	/**当交易数量（即冻结数量）与买家购买的数量不一致时，如果挂牌未取消，则将差值返还到可摘数量*/
	LISTING_UPDATE_FREEZE("8","订单：{0}，交易数量（即冻结数量）与买家购买的数量不一致，归还差值至挂牌：{1}的可摘数量，由：{2} 变为：{3}"),
	/**订单过期，数量返还挂牌*/
	LISTING_ORDER_OVERTIME("9", "订单过期，数量返还挂牌"),
	/**订单过期，数量返还挂牌*/
	LISTING_ORDER_REFUND("10", "订单退款完成，数量返还挂牌"),
	
	/******************************************************************************************************
	 ***************************************************订单日志**********************************************
	 *****************************************************************************************************/
	/** 卖家下单 */
	ORDER_CREATED("1","买家下单"),
	/** 买家支付保证金 */
	ORDER_DEPOSIT_PAID("2","买家支付保证金"),
	/** wms系统冻结货物 */
	ORDER_GOODS_PREPARED("3","WMS系统备货"),
	/** 买家支付尾款 */
	ORDER_BALANCE_PAID("4","买家支付尾款"),
	/** wms系统分割仓单 */
	ORDER_FINISHED("5","订单完成"),
	/** 订单过期取消 */
	ORDER_CANCELED("6","订单过期取消"),
	/** 卖家修改订单单价 */
	ORDER_UPDATE_PRICE("7","卖家修改订单单价"),
	/** 后台完成赔付 */
	ORDER_REIMBURSE_PAY("8","后台完成赔付"),
	/** 后台驳回申请 */
	ORDER_REIMBURSE_REJECT("9","后台驳回申请"),
	/** 买家申请退款 */
	ORDER_APPLY_REIMBURSE("10","买家申请退款"),
	/** 关闭订单 */
	ORDER_COLOSE("11","关闭订单"),
	/** 删除订单*/
	ORDER_DELETE("12","删除订单"),
	/** 订单延期*/
	ORDER_EXPIRETIME("13","订单延期"),
	/** 订单保证金修改*/
	ORDER_DEPOSIT_UPDATE("14","订单保证金修改"),
	//add by fanyuna 2015.09.07 订单已分割 、未付尾款
	/**  订单已分割 、未付尾款 */
	ORDER_SPLIT("15","订单已分割，未付尾款"),
	/** 转为账期订单*/
	ORDER_PAYMENTDAYS("16","转为账期订单"),
	/** 账期订单即将到期通知*/
	TERM_ORDER_OVERTIME_WARN_ADVANCE("17","账期订单即将到期通知"),
	/** 账期订单即将到期通知*/
	TERM_ORDER_OVERTIME_WARN("18","账期订单到期通知"),
	
	
	
	/******************************************************************************************************
	 ***************************************************采购交易日志**********************************************
	 *****************************************************************************************************/
	
	/** 创建采购单 */
	PURCHASE_CREATE("10","创建采购单"),
	/** 修改采购单 */
	PURCHASE_UPDATE("20","修改采购单"),
	/** 删除采购单 */
	PURCHASE_DELETE("30","删除采购单"),
	/** 审核通过 */
	PURCHASE_AUDIT_PASSED("40","审核通过"),
	/** 达成交易 */
	PURCHASE_DEAL_SUCCESS("50","达成交易"),
	/** 审核不通过 */
	PURCHASE_AUDIT_UNPASSED("-10","审核不通过"),
	/** 结束采购 */
	PURCHASE_FINISHED("-20","结束采购");
	
	
	private String code;
	private String codeName;
	private BusinessLogEnum(){}
	private BusinessLogEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BusinessLogEnum item : BusinessLogEnum.values()) {
			map.put(item.getCode(), item.getCodeName());
		}
		return map;
	}
	
	/**
	 * 取得code
	 * @return code
	 */
	public String getCode() {
	    return code;
	}
	/**
	 * 设定code
	 * @param code code
	 */
	public void setCode(String code) {
	    this.code = code;
	}
	/**
	 * 取得codeName
	 * @return codeName
	 */
	public String getCodeName() {
	    return codeName;
	}
	/**
	 * 设定codeName
	 * @param codeName codeName
	 */
	public void setCodeName(String codeName) {
	    this.codeName = codeName;
	}
	
	/**
	 * 获取{0}占位符替换后的message
	 * @param objs
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getMessage(Object...objs){
		String result = codeName;
		int index = 0;
		if(objs!=null&&objs.length>0){
			for(Object o:objs){
				if(o instanceof String
						|| o instanceof Number){
					result = result.replaceAll("\\{"+index+"\\}", String.valueOf(o));
				}else if(o instanceof Date){
					result = result.replaceAll("\\{"+index+"\\}", TimeUtil.getYMDHMS((Date)o));
				}else if(o instanceof Calendar){
					result = result.replaceAll("\\{"+index+"\\}", TimeUtil.getYMDHMS(((Calendar)o).getTime()));
				}else if(o instanceof RangeVo){
					result = result.replaceAll("\\{"+index+"\\}", String.valueOf(((RangeVo)o).getStart()))
							.replaceAll("\\{"+(index+1)+"\\}", String.valueOf(((RangeVo)o).getEnd()));
					index++;
				}else{
					result = result.replaceAll("\\{"+index+"\\}", String.valueOf(o));
				}
				index++;
			}
		}
		return result;
	}
}
