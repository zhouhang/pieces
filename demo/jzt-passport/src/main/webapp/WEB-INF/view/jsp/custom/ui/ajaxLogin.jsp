<%@ page contentType="text/html; charset=UTF-8"%>  
<%
Boolean isLogin = (Boolean)request.getAttribute("isLogin");
Boolean isPopLogin = (Boolean)request.getAttribute("isPopLogin");
// 弹窗登陆  
if(isPopLogin){
%>
<script>
	location.replace('http://www.54315.com/popValidate?service=http://www.54315.com/popLoginHandler?u=${username}&type=${type}');
</script>
<% 
// 非弹窗ajax
} else{
	//登陆成功
	if(isLogin){
%>
		var u = '${username}';
		if(u){
			location.replace("http://www.54315.com/validate?service="+encodeURIComponent(location.href));
		}
<%
	}
}
%>
