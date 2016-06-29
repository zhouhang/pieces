package com.jointown.zy.common.exception;

/**
 * 
 * @ClassName: WxErrorException
 * @Description: 微信自定义异常
 * @Author: wangjunhu
 * @Date: 2015年5月19日
 * @Version: 1.0
 */
public class WxErrorException extends ErrorException {

	private static final long serialVersionUID = 1L;

	public WxErrorException(ErrorMessage e) {
		super(e);
	}

	public WxErrorException(Exception e) {
		super(e);
	}

	@Override
	public ErrorMessage getCustomedErrorMessage(Exception e) {
		return null;
	}

	public WxErrorException(ErrorRepository e) {
		super(e);
	}
}
