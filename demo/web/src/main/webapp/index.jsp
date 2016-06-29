<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<link rel="stylesheet" type="text/css" href="../css/demo.css" />
<title>九州通中药材电商平台</title>
</head>

<body>
<@shiro.guest>
  欢迎游客访问，<a href="/user">去个某个需要登陆才能看的连接1</a><br/>
</@shiro.guest>
<@shiro.user>
欢迎回来
<a href="/user"><@shiro.principal/></a> - <a href="https://passport.jzt.com:8443/logout?service=http://localhost:8080/logout">退出</a>
</@shiro.user>
<hr />

</body>
</html>

