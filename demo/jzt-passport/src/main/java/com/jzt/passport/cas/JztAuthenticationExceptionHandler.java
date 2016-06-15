package com.jzt.passport.cas;

import java.util.ArrayList;
import java.util.List;




import org.jasig.cas.web.flow.AuthenticationExceptionHandler;
import org.springframework.beans.factory.InitializingBean;

public class JztAuthenticationExceptionHandler extends
		AuthenticationExceptionHandler implements InitializingBean{
	
	@SuppressWarnings("rawtypes")
	private List extendedErrorList;
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws Exception {
		List<Class<? extends Exception>> list = new ArrayList<Class<? extends Exception>>(getErrors());
		//加入自己的自定义异常类
		for(Object error : getExtendedErrorList()){
			list.add((Class<? extends Exception>)Class.forName((String)error));
		}
		setErrors(list);
	}


	@SuppressWarnings("rawtypes")
	public List getExtendedErrorList() {
		return extendedErrorList;
	}


	@SuppressWarnings("rawtypes")
	public void setExtendedErrorList(List extendedErrorList) {
		this.extendedErrorList = extendedErrorList;
	}
	

}
