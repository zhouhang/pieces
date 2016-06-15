package com.jointown.zy.common.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jointown.zy.common.exception.ErrorMessage;
import com.jointown.zy.common.exception.ErrorRepository;

/**
 * 个人认证Dto
 * @author ldp
 * date 2015年1月8日
 * Verison 0.0.1
 */
public class PersonCertifyDto extends BaseDto {

	private static final long serialVersionUID = -7937459995692145424L;

	/** 个人会员Id*/
	private String userId;
	/** 认证Id*/
	private String certifyId;
	/** 真实姓名*/
	private String name;
	/** 身份证号*/
	private String idCard;
	/** 认证是否通过*/
	private String isPass;
	/** 审核备注*/
	private String checkRemark;
	
	/** 正面图*/
	private String picName;
	private String picPath;
	private Integer picType;
	private String picNameSmall;
	private String picSmallPath;
	private Integer picSmallType;
	private String picNameBig;
	private String picBigPath;
	private Integer picBigType;
	/** 反面图*/
	private String picName1;
	private String picPath1;
	private Integer picType1;
	private String picNameSmall1;
	private String picSmallPath1;
	private Integer picSmallType1;
	private String picNameBig1;
	private String picBigPath1;
	private Integer picBigType1;
	
	public String getCertifyId() {
		return certifyId;
	}
	public void setCertifyId(String certifyId) {
		this.certifyId = certifyId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIsPass() {
		return isPass;
	}
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public Integer getPicType() {
		return picType;
	}
	public void setPicType(Integer picType) {
		this.picType = picType;
	}
	public String getPicNameSmall() {
		return picNameSmall;
	}
	public void setPicNameSmall(String picNameSmall) {
		this.picNameSmall = picNameSmall;
	}
	public String getPicSmallPath() {
		return picSmallPath;
	}
	public void setPicSmallPath(String picSmallPath) {
		this.picSmallPath = picSmallPath;
	}
	public Integer getPicSmallType() {
		return picSmallType;
	}
	public void setPicSmallType(Integer picSmallType) {
		this.picSmallType = picSmallType;
	}
	public String getPicNameBig() {
		return picNameBig;
	}
	public void setPicNameBig(String picNameBig) {
		this.picNameBig = picNameBig;
	}
	public String getPicBigPath() {
		return picBigPath;
	}
	public void setPicBigPath(String picBigPath) {
		this.picBigPath = picBigPath;
	}
	public Integer getPicBigType() {
		return picBigType;
	}
	public void setPicBigType(Integer picBigType) {
		this.picBigType = picBigType;
	}
	public String getPicName1() {
		return picName1;
	}
	public void setPicName1(String picName1) {
		this.picName1 = picName1;
	}
	public String getPicPath1() {
		return picPath1;
	}
	public void setPicPath1(String picPath1) {
		this.picPath1 = picPath1;
	}
	public Integer getPicType1() {
		return picType1;
	}
	public void setPicType1(Integer picType1) {
		this.picType1 = picType1;
	}
	public String getPicNameSmall1() {
		return picNameSmall1;
	}
	public void setPicNameSmall1(String picNameSmall1) {
		this.picNameSmall1 = picNameSmall1;
	}
	public String getPicSmallPath1() {
		return picSmallPath1;
	}
	public void setPicSmallPath1(String picSmallPath1) {
		this.picSmallPath1 = picSmallPath1;
	}
	public Integer getPicSmallType1() {
		return picSmallType1;
	}
	public void setPicSmallType1(Integer picSmallType1) {
		this.picSmallType1 = picSmallType1;
	}
	public String getPicNameBig1() {
		return picNameBig1;
	}
	public void setPicNameBig1(String picNameBig1) {
		this.picNameBig1 = picNameBig1;
	}
	public String getPicBigPath1() {
		return picBigPath1;
	}
	public void setPicBigPath1(String picBigPath1) {
		this.picBigPath1 = picBigPath1;
	}
	public Integer getPicBigType1() {
		return picBigType1;
	}
	public void setPicBigType1(Integer picBigType1) {
		this.picBigType1 = picBigType1;
	}
	public String getCheckRemark() {
		return checkRemark;
	}
	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}
	/**
	 * 验证
	 */
	public List<ErrorMessage> validate(){
		List<ErrorMessage> errorList = new ArrayList<ErrorMessage>();
		if (StringUtils.isEmpty(name)) {
			errorList.add(new ErrorMessage(ErrorRepository.UC_PERSON_CERTIFY_NAME_NOT_NULL));
			return errorList;
		}
		
		if (StringUtils.isEmpty(idCard)) {
			errorList.add(new ErrorMessage(ErrorRepository.UC_PERSON_CERTIFY_IDCARD_NOT_NULL));
			return errorList;
		}
		return null;
	}
}
