package com.jointown.zy.common.exception;

/**
 * 自定义文章异常
 * @author Mr.songwei 
 * @date 2014年11月25日下午15:15:28
 */
public class CmsArticleException extends ErrorException {

	public CmsArticleException(Exception e) {
		super(e);
	}
	
	public CmsArticleException(ErrorMessage e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Override
	public ErrorMessage getCustomedErrorMessage(Exception e) {
		if(e instanceof NullPointerException){
			return new ErrorMessage(ErrorRepository.CMS_NULLPOINTER);
		}else{
			return new ErrorMessage(ErrorRepository.ANONYMOUS_ERROR,e.getMessage());
		}
	}
}
