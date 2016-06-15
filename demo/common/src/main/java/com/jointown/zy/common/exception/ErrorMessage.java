package com.jointown.zy.common.exception;


/**
 * 错误信息VO。 主要用作返回前端页面的错误信息。 code表示错误编码，message表示错误信息
 * @author LiuPiao
 * 
 */
public class ErrorMessage {
	private String code;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ErrorMessage(String code,String message){
		this.code = code;
		this.message = message;
	}
	
	public ErrorMessage(ErrorRepository error) {
		this.code = error.name();
		this.message = error.getMessage();
	}
	
	public ErrorMessage(ErrorRepository error,Object...objs) {
		this.code = error.name();
		this.message = error.getMessage(objs);
	}
	
}
