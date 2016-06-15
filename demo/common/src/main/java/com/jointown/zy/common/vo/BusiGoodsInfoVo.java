package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.BusiQualityItem;
import com.jointown.zy.common.model.BusiQualitypic;

/**
 * 
 * 描述： 挂牌商品信息（包括基本信息、交收信息、质检信息、图片）<br/>
 * 
 * 日期： 2015年1月7日<br/>
 * 
 * 作者： 赵航<br/>
 * 
 * 版本： V1.0<br/>
 */
public class BusiGoodsInfoVo implements Serializable {

	private static final long serialVersionUID = -1193876598798289283L;

	/** 商品ID */
	private String listingid;

	/** 商品标题 */
	private String title;

	/** 商品单价（不开票） */
	private BigDecimal nobillPrice;

	/** 商品单价（开票） */
	private BigDecimal billPrice;

	/** 商品计量单位名称 */
	private String unitname;

	/** 数量 */
	private BigDecimal amount;

	/** 最低起订数量 */
	private BigDecimal minsalesAmount;

	/** 可购数量 */
	private BigDecimal maxsalesAmount;

	/** 可摘数量 用来代替上面的 可购数量 */
	private BigDecimal surpluses;

	/** 开据发票 */
	private String hasbill;

	/** 产地 */
	private String origin;

	/** 包装规格 */
	private String packingway;

	/** 仓单编号 */
	private String wlid;

	/** 所在仓库 */
	private String warehouseName;

	// ############### 质检信息 ###############
	/** 主要性状 */
	private String mainpapacter;

	/** 等级规格 */
	private String grade;

	/** 规格 */
	private String standard;

	/** 等级评定 */
	private String levelEva;

	/** 检品数量 */
	private String checkNumber;

	/** 具体描述 */
	private String detailed;

	/** 水分 */
	private String moisture;

	/** 总灰分 */
	private String ash;

	/** 二氧化硫残留量 */
	private String sulfurDioxideIn;

	/** 含量测定 */
	private String contentCheck;
	//质检项信息
	private List<BusiQualityItem> busiQualityItem;
	//质检项信息分组
	private Map<String, List<BusiQualityItem>> itemMap = new LinkedHashMap<String, List<BusiQualityItem>>();
	// ############### 质检信息 ###############

	/** 图片 */
	private List<BusiQualitypic> goodsPicList;

	/** 详情 */
	private String content;

	// add by fanyuna
	/** 挂牌用户ID */
	private String userId;

	/** 品种ID */
	private String breedId;
	
	/** 品种名称  add by Calvin.wh seo优化 */
	private String breedName;

	/** 挂牌标志 */
	private String listingFlag;
	
	/** 挂牌时间 */
	private Date examintime;

	/**
	 * 取得商品ID
	 * 
	 * @return 商品ID
	 */
	public String getListingid() {
		return listingid;
	}

	/**
	 * 设定商品ID
	 * 
	 * @param listingid
	 *            商品ID
	 */
	public void setListingid(String listingid) {
		this.listingid = listingid;
	}

	/**
	 * 取得商品标题
	 * 
	 * @return 商品标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设定商品标题
	 * 
	 * @param title
	 *            商品标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 取得商品单价（不开票）
	 * 
	 * @return 商品单价（不开票）
	 */
	public BigDecimal getNobillPrice() {
		return nobillPrice;
	}

	/**
	 * 设定商品单价（不开票）
	 * 
	 * @param nobillPrice
	 *            商品单价（不开票）
	 */
	public void setNobillPrice(BigDecimal nobillPrice) {
		this.nobillPrice = nobillPrice;
	}

	/**
	 * 取得商品单价（开票）
	 * 
	 * @return 商品单价（开票）
	 */
	public BigDecimal getBillPrice() {
		return billPrice;
	}

	/**
	 * 设定商品单价（开票）
	 * 
	 * @param billPrice
	 *            商品单价（开票）
	 */
	public void setBillPrice(BigDecimal billPrice) {
		this.billPrice = billPrice;
	}

	/**
	 * 取得商品计量单位名称
	 * 
	 * @return 商品计量单位名称
	 */
	public String getUnitname() {
		return unitname;
	}

	/**
	 * 设定商品计量单位名称
	 * 
	 * @param unitname
	 *            商品计量单位名称
	 */
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	/**
	 * 取得等级规格
	 * 
	 * @return 等级规格
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * 设定等级规格
	 * 
	 * @param grade
	 *            等级规格
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * 取得数量
	 * 
	 * @return 数量
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设定数量
	 * 
	 * @param amount
	 *            数量
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 取得最低起订数量
	 * 
	 * @return 最低起订数量
	 */
	public BigDecimal getMinsalesAmount() {
		return minsalesAmount;
	}

	/**
	 * 设定最低起订数量
	 * 
	 * @param minsalesAmount
	 *            最低起订数量
	 */
	public void setMinsalesAmount(BigDecimal minsalesAmount) {
		this.minsalesAmount = minsalesAmount;
	}

	/**
	 * 取得可购数量
	 * 
	 * @return 可购数量
	 */
	public BigDecimal getMaxsalesAmount() {
		return maxsalesAmount;
	}

	/**
	 * 设定可购数量
	 * 
	 * @param maxsalesAmount
	 *            可购数量
	 */
	public void setMaxsalesAmount(BigDecimal maxsalesAmount) {
		this.maxsalesAmount = maxsalesAmount;
	}

	/**
	 * 取得开据发票
	 * 
	 * @return 开据发票
	 */
	public String getHasbill() {
		return hasbill;
	}

	/**
	 * 设定开据发票
	 * 
	 * @param hasbill
	 *            开据发票
	 */
	public void setHasbill(String hasbill) {
		this.hasbill = hasbill;
	}

	/**
	 * 取得产地
	 * 
	 * @return 产地
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * 设定产地
	 * 
	 * @param origin
	 *            产地
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * 取得包装规格
	 * 
	 * @return 包装规格
	 */
	public String getPackingway() {
		return packingway;
	}

	/**
	 * 设定包装规格
	 * 
	 * @param packingway
	 *            包装规格
	 */
	public void setPackingway(String packingway) {
		this.packingway = packingway;
	}

	/**
	 * 取得仓单编号
	 * 
	 * @return 仓单编号
	 */
	public String getWlid() {
		return wlid;
	}

	/**
	 * 设定仓单编号
	 * 
	 * @param wlid
	 *            仓单编号
	 */
	public void setWlid(String wlid) {
		this.wlid = wlid;
	}

	/**
	 * 取得所在仓库
	 * 
	 * @return 所在仓库
	 */
	public String getWarehouseName() {
		return warehouseName;
	}

	/**
	 * 设定所在仓库
	 * 
	 * @param warehouseName
	 *            所在仓库
	 */
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	/**
	 * 取得主要性状
	 * 
	 * @return 主要性状
	 */
	public String getMainpapacter() {
		return mainpapacter;
	}

	/**
	 * 设定主要性状
	 * 
	 * @param mainpapacter
	 *            主要性状
	 */
	public void setMainpapacter(String mainpapacter) {
		this.mainpapacter = mainpapacter;
	}

	/**
	 * 取得具体描述
	 * 
	 * @return 具体描述
	 */
	public String getDetailed() {
		return detailed;
	}

	/**
	 * 设定具体描述
	 * 
	 * @param detailed
	 *            具体描述
	 */
	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}

	/**
	 * 取得水分
	 * 
	 * @return 水分
	 */
	public String getMoisture() {
		return moisture;
	}

	/**
	 * 设定水分
	 * 
	 * @param moisture
	 *            水分
	 */
	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}

	/**
	 * 取得总灰分
	 * 
	 * @return 总灰分
	 */
	public String getAsh() {
		return ash;
	}

	/**
	 * 设定总灰分
	 * 
	 * @param ash
	 *            总灰分
	 */
	public void setAsh(String ash) {
		this.ash = ash;
	}

	/**
	 * 取得二氧化硫残留量
	 * 
	 * @return 二氧化硫残留量
	 */
	public String getSulfurDioxideIn() {
		return sulfurDioxideIn;
	}

	/**
	 * 设定二氧化硫残留量
	 * 
	 * @param sulfurDioxideIn
	 *            二氧化硫残留量
	 */
	public void setSulfurDioxideIn(String sulfurDioxideIn) {
		this.sulfurDioxideIn = sulfurDioxideIn;
	}

	/**
	 * 取得含量测定
	 * 
	 * @return 含量测定
	 */
	public String getContentCheck() {
		return contentCheck;
	}

	/**
	 * 设定含量测定
	 * 
	 * @param contentCheck
	 *            含量测定
	 */
	public void setContentCheck(String contentCheck) {
		this.contentCheck = contentCheck;
	}

	/**
	 * 取得图片
	 * 
	 * @return 图片
	 */
	public List<BusiQualitypic> getGoodsPicList() {
		return goodsPicList;
	}

	/**
	 * 设定图片
	 * 
	 * @param goodsPicList
	 *            图片
	 */
	public void setGoodsPicList(List<BusiQualitypic> goodsPicList) {
		this.goodsPicList = goodsPicList;
	}

	/**
	 * 取得详情
	 * 
	 * @return 详情
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设定详情
	 * 
	 * @param content
	 *            详情
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBreedId() {
		return breedId;
	}

	public void setBreedId(String breedId) {
		this.breedId = breedId;
	}

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}

	public String getListingFlag() {
		return listingFlag;
	}

	public void setListingFlag(String listingFlag) {
		this.listingFlag = listingFlag;
	}

	public BigDecimal getSurpluses() {
		return surpluses;
	}

	public void setSurpluses(BigDecimal surpluses) {
		this.surpluses = surpluses;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getLevelEva() {
		return levelEva;
	}

	public void setLevelEva(String levelEva) {
		this.levelEva = levelEva;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}


	public List<BusiQualityItem> getBusiQualityItem() {
		return busiQualityItem;
	}

	public void setBusiQualityItem(List<BusiQualityItem> busiQualityItem) {
		this.busiQualityItem = busiQualityItem;
	}

	public Map<String, List<BusiQualityItem>> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<String, List<BusiQualityItem>> itemMap) {
		this.itemMap = itemMap;
	}

	/**
	 * @return the examintime
	 */
	public Date getExamintime() {
		return examintime;
	}

	/**
	 * @param examintime the examintime to set
	 */
	public void setExamintime(Date examintime) {
		this.examintime = examintime;
	}

}
