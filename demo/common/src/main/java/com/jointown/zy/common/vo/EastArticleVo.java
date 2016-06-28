/**
 * @author guoyb
 * 2015年3月3日 上午9:05:20
 */
package com.jointown.zy.common.vo;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 抓取的东方中药材网的 article(文章信息表)
 * 
 * @author guoyb 2015年3月3日 上午9:05:20
 */
public class EastArticleVo{

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
	
	// 摘要
	private String ac_abstract;
	
	// 点击次数
	private Integer tip;
	
	public void setAc_abstract(String ac_abstract) {
		this.ac_abstract = ac_abstract;
	}

	public String getAc_abstract() {
		return ac_abstract;
	}

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
		String value="";
		/**
		 * add by Mr.song 2015.9.23 
		 * 过滤所有以"<"开头以">"的所有标签   start
		 */
		if(StringUtils.isNotEmpty(cont)){
			Pattern pattern = Pattern.compile("<[^>]+>");   
	        Matcher matcher = pattern.matcher(cont);   
	        StringBuffer sb = new StringBuffer();   
	        boolean result1 = matcher.find();   
	        while (result1) {   
	            matcher.appendReplacement(sb, "");   
	            result1 = matcher.find();   
	        }   
	        matcher.appendTail(sb);   
	        value =  sb.toString().replace("&nbsp;", "").trim(); 
		}else{
			value = cont;
		}
		/**
		 * add by Mr.song 2015.9.23 
		 * 过滤所有以"<"开头以">"的所有标签   end
		 */
		this.cont = value;
		value =""; //add by Mr.song 2015.9.23  消除对象引用
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

	public Integer getTip() {
		return tip;
	}

	public void setTip(Integer tip) {
		this.tip = tip;
	}
}
