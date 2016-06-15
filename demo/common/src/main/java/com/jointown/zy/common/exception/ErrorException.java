package com.jointown.zy.common.exception;


/**
 * 公共异常
 * @author LiuPiao
 *
 */
public abstract class ErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ErrorMessage errorMsg;
	
	private Exception exception;
	
	public ErrorException(ErrorMessage e) {
		this.errorMsg = e;
	}
	
	/**
	 * 直接通过错误描述构造异常
	 * @param er
	 */
	public ErrorException(ErrorRepository er) {
		this.errorMsg = new ErrorMessage(er);
	}
	
	/**
	 * 直接通过错误描述,和自定义的值构造异常
	 * @param er
	 * @param objs
	 */
	public ErrorException(ErrorRepository er,Object...objs) {
		this.errorMsg = new ErrorMessage(er,objs);
	}
	
	public ErrorException(Exception e) {
		this.exception = e;
		this.errorMsg = this.getErrorMessage(e);
		
	}
	
	public ErrorMessage getErrorMessage(){
		return this.errorMsg;
	}
	
	public Exception getException(){
		return this.exception;
	}
	
	
	/**
	 * 客户化异常提取方法，各个子类实现
	 * @param e
	 * @return
	 */
	public abstract ErrorMessage getCustomedErrorMessage(Exception e);
	
	/**
	 * 异常提取方法，将异常参数中的信息转化为errorMessage
	 * @param e
	 * @return
	 */
	public ErrorMessage getErrorMessage(Exception e){
		//异常处理
    	ErrorMessage ee = null;
    	if(ErrorException.class.isInstance(e)){
    		ee = ((ErrorException)e).getErrorMessage();
    	}else if(e.getCause() instanceof ErrorException){
    		ee = ((ErrorException)e.getCause()).getErrorMessage();
    	}else{
    		ee = getCustomedErrorMessage(e);
    	}
    	return ee;
	}
	
	/**
	 * 获取异常信息
	 */
	@Override
	public String getMessage(){
		return getErrorMessage().getMessage();
	}
	
	/**
	 * 获取异常编码
	 * @return
	 */
	public String getCode(){
		return getErrorMessage().getCode();
	}

}
