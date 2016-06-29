package com.jzt.passport.cas;

import java.util.Map;

import org.jasig.cas.authentication.UsernamePasswordCredential;

public class JztCredential extends UsernamePasswordCredential {

	private static final long serialVersionUID = 1L;

	private Map<String,Object> param;
    private String vcode;          
  
    public String getVcode() {  
        return vcode;  
    }  
  
    public void setVcode(String vcode) {  
        this.vcode = vcode;  
    }  
  
    public Map<String, Object> getParam() {
        return param;
    }
 
    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

}
