package com.jzt.passport.cas;

import org.apache.commons.lang.StringUtils;
import org.jasig.cas.authentication.Credential;
import org.jasig.cas.web.flow.AuthenticationViaFormAction;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.RequestContext;

/**
 * 
 * @author liupiao
 * 2014年11月4日
 */
public class JztAuthenticationViaFormAction extends AuthenticationViaFormAction {
      
     public final String validatorCode(final RequestContext context, final Credential credential, final MessageContext messageContext) throws Exception {  
         String vcode=(String)WebUtils.getHttpServletRequest(context).getSession().getAttribute("vcode");  
         JztCredential upv=(JztCredential)credential;  
         if(StringUtils.isBlank(upv.getVcode()) || StringUtils.isBlank(vcode)){
        	 return ERROR; 
         }
         if(upv.getVcode().equalsIgnoreCase(vcode)){  
             return SUCCESS;  
         }  
         MessageBuilder msgBuilder=new MessageBuilder();  
         msgBuilder.defaultText("验证码有误！");  
         messageContext.addMessage(msgBuilder.error().build());  
         return ERROR;  
     }  

}
