<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>会员管理</title>
	<#include "home/common.ftl">
</head>
<body>
<#include "home/top.ftl" />  
<div class="wapper">
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">会员管理</h1>
            <form id="dataForm">
                <ul class="form-1">
                    <li><label class="mb10"><strong>基本资料</strong></label></li>
                    <li>
                        <label>会员名：</label>
                        <p>${ucUser.userName }</p>
                    </li>
                    <li>
                        <label>公司/真实姓名：</label>
                        <p>${ucUser.companyName }</p>
                    </li>
                    <li>
                        <label>手机：</label>
                        <p>${ucUser.mobile }</p>
                    </li>
                    <li>
                        <label>邮箱：</label>
                        <p>${ucUser.email } </p>
                    </li>
					 <li>
					 	<label></label>
                        <input type="button" class="btn-hui" value="返回" onclick="javascript:window.history.go(-1);"/>
                    </li>
                </ul>
            </form>
        </div>
    </div>
<!-- pageCenter over -->
</div>
</body>
</html>