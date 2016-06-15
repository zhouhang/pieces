<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>九州通中药材电商平台</title>
<script type="text/javascript" src="html/resources/js/jquery-1.11.1.min.js"></script>
</head>

<body>

<input id="json" type="button" value="json视图"/>
<input id="xml" type="button" value="xml视图"/>
<input id="ftl" type="button" value="freemarker默认视图"/>

<div id="show"></div>

<@shiro.guest>
  欢迎游客访问，<a href="/user">去个某个需要登陆才能看的连接</a><br/>
</@shiro.guest>
<@shiro.user>
欢迎回来
<a href="/user"><@shiro.principal/></a> - <a href="https://passport.54315.com/logout?service=http://localhost:8080/logout">退出</a>
</@shiro.user>
<hr />

<script type="text/javascript">

$(function(){  
	 $('#json').click(function(){
		 $.get("/public/lp.json",function(data){
			 alert('获取的json数据是json对象,用例是[返回json对象].[参数对象名].[属性名].\n例如:data.user.userCode是:'+data.user.userCode+'\ndata.user.userName是:'+data.user.userName);
		 });
     });
	 $('#xml').click(function(){
		 $.get("/public/lp.xml",function(data){
		 	 alert("获取的xml数据是xml对象,用例是$([返回xml对象])。find([标签名]得到相应xml标签对象，然后操作).\n例如:$(data).find('userCode').text()是:"+$(data).find('userCode').text()+"\n$(data).find('userName').text()是:"+$(data).find('userName').text());
		 });
     });
	 
	 $('#ftl').click(function(){
	    window.location="/public/lp";
     });
});


</script>

</body>
</html>

