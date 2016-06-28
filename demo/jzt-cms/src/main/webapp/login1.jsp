<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>九州通中药材电商平台</title>
</head>

<body>

<@shiro.user>Hello guest!</@shiro.user>

<@shiro.hasPermission name="auth:role:add">hello world</@shiro.hasPermission>

<p>我们${haha}</p>  
<p>你好吗？${maplist.name}</p>  
<p>你好？${maplist.age}</p>  
<p>你吗？${maplist.address}</p>  
<p>你？${maplist.ad!}</p>  
  
Welcome ${user!}!
Welcome ${user!'your name'}!
或者
${user?if_exists}

${user?default('your name')}

如果user找不到值，会输出  
Welcome !  
Welcome your name!  
否则freemarker会报错  

</body>
</html>