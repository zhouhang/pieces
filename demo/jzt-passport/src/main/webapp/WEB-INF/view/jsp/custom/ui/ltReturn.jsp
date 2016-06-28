<%@ page contentType="text/html; charset=UTF-8"%>  
<% Boolean isRefreshLt = (Boolean)request.getAttribute("isRefreshLt"); 
    // 登录成功
    if(isRefreshLt){%>
    	message = {'lt':'${loginTicket}'};
<%}
%>