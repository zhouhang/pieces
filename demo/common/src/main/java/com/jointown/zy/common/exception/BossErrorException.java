package com.jointown.zy.common.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * BOSS 系统自定义异常
 * @author LiuPiao
 *
 */
public class BossErrorException extends ErrorException {


	public BossErrorException(Exception e) {
		super(e);
	}
	
	public BossErrorException(ErrorMessage e) {
		super(e);
	}
	
	/**
	 * 直接通过错误描述构造异常
	 * @param er
	 */
	public BossErrorException(ErrorRepository er) {
		super(er);
	}
	
	/**
	 * 直接通过错误描述,和自定义的值构造异常
	 * @param er
	 * @param objs
	 */
	public BossErrorException(ErrorRepository er,Object...objs) {
		super(er,objs);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Override
	public ErrorMessage getCustomedErrorMessage(Exception e) {
		if(e instanceof AuthenticationException){
			return new ErrorMessage(ErrorRepository.BOSS_USER_PASS_NOT_MATCH);
		}else{
			return new ErrorMessage(ErrorRepository.ANONYMOUS_ERROR,e.getMessage());
		}
	}
	

}
