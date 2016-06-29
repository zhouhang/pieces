package com.jointown.zy.common.exception;

public class CommonErrorException extends ErrorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommonErrorException(ErrorMessage e) {
		super(e);
	}
	
	/**
	 * 直接通过错误描述构造异常
	 * @param er
	 */
	public CommonErrorException(ErrorRepository er) {
		super(er);
	}
	
	/**
	 * 直接通过错误描述,和自定义的值构造异常
	 * @param er
	 * @param objs
	 */
	public CommonErrorException(ErrorRepository er,Object...objs) {
		super(er,objs);
	}

	@Override
	public ErrorMessage getCustomedErrorMessage(Exception e) {
		// TODO Auto-generated method stub
		return null;
	}

}
