/**
 * @author guoyb
 * 2015年3月3日 上午9:05:20
 */
package com.jointown.zy.common.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * 抓取的东方中药材网的 article(文章信息表)
 * 
 * @author guoyb 2015年3月3日 上午9:05:20
 */
@SuppressWarnings("serial")
public class EastArticle implements Serializable {

	//id
	private Integer acid;
	
	// 文章栏目：1-品种分析，2-市场动态，4-政策法规，7-产地快迅，8-大盘分析，9-研究报告，
	// 10-财经信息，11-行业新闻，12-投资理念，15-看图说话，18-手机专用
	private Integer lmid;

	// 药材名称
	private String ycnam;

	// 文章标题
	private String title;

	// 文章内容
	private String cont;
	
	// 文章作者
	private String writer;
	
	// 创建时间
	private Date dtm;
	
	// 免费：0-免费
	private String free;
	
	// 店铺id
	private Integer shopid;
	
	// 点击次数
	private Integer tip;

	public Integer getAcid() {
		return acid;
	}

	public void setAcid(Integer acid) {
		this.acid = acid;
	}

	public Integer getLmid() {
		return lmid;
	}

	public void setLmid(Integer lmid) {
		this.lmid = lmid;
	}

	public String getYcnam() {
		return ycnam;
	}

	public void setYcnam(String ycnam) {
		this.ycnam = ycnam;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getDtm() {
		return dtm;
	}

	public void setDtm(Date dtm) {
		this.dtm = dtm;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public Integer getTip() {
		return tip;
	}

	public void setTip(Integer tip) {
		this.tip = tip;
	}
}
