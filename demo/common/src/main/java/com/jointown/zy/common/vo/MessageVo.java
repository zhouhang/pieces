package com.jointown.zy.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.jointown.zy.common.exception.ErrorException;
import com.jointown.zy.common.exception.ErrorMessage;

/**
 * 信息VO。主要用作返回前端页面的值对象，错误信息，成功标准等。 常用与JSON或XML格式的转换。
 * ok表示是否成功，errorMessages表示错误列表，obj表示页面要显示的返回值对象
 * @author LiuPiao
 *
 */
public class MessageVo {
	
	/**
	 * 描述是否成功
	 */
	private boolean ok;
	
	/**
	 * 描述错误代码，错误信息
	 */
	private List<ErrorMessage> errorMessages;
	
	/**
	 * 返回值对象	
	 */
	private Object obj;
	

	public boolean isOk() {
		return ok;
	}

	public MessageVo setOk(boolean ok) {
		this.ok = ok;
		return this;
	}

	public List<ErrorMessage> getErrorMessages() {
		return errorMessages;
	}

	public MessageVo setErrorMessages(List<ErrorMessage> errorMessages) {
		this.errorMessages = errorMessages;
		return this;
	}
	
	public MessageVo addError(String errorCode,String errorMecssage){
		if(errorMessages==null){
			errorMessages = new ArrayList<ErrorMessage>();
		}
		errorMessages.add(new ErrorMessage(errorCode, errorMecssage));
		return this;
	}
	
	public MessageVo addError(ErrorMessage error){
		if(errorMessages==null){
			errorMessages = new ArrayList<ErrorMessage>();
		}
		errorMessages.add(error);
		return this;
	}
	
	public MessageVo addError(ErrorException exception){
		if(errorMessages==null){
			errorMessages = new ArrayList<ErrorMessage>();
		}
		errorMessages.add(exception.getErrorMessage());
		return this;
	}

	public Object getObj() {
		return obj;
	}

	public MessageVo setObj(Object obj) {
		this.obj = obj;
		return this;
	}

}
