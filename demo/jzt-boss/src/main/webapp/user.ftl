<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表</title>
  </head>
  <body>
    <h1>欢迎[<@shiro.principal/>]--<a href="/user/add">添加用户</a>---<a href="https://passport.54315.com/logout?service=http://localhost:8080/logout">退出登录</a>    </h1>
    <h2>权限列表</h2>
    <@shiro.authenticated>用户已经登录显示此内容</br></@shiro.authenticated>
    <@shiro.hasRole name="manager">manager角色登录显示此内容</br></@shiro.hasRole>
    <@shiro.hasRole name="admin">admin角色登录显示此内容</br></@shiro.hasRole>
    <@shiro.hasRole name="user">user角色登录显示此内容</br></@shiro.hasRole>
    
    <@shiro.hasAnyRoles name="manager,admin">**manager or admin 角色用户登录显示此内容**</br></@shiro.hasAnyRoles>
    <@shiro.principal/>-显示当前登录用户名</br>
    <@shiro.hasPermission name="add">add权限用户显示此内容</br></@shiro.hasPermission>
    <@shiro.hasPermission name="user:query">query权限用户显示此内容<@shiro.principal/></br></@shiro.hasPermission>
    <@shiro.lacksPermission name="user:del"> 不具有user:del权限的用户显示此内容 </br></@shiro.lacksPermission>
  </body>
</html>