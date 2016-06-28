<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>中药材电子商务管理系统</title>
	<#include "home/common.ftl">
</head>
<body>
<#include "home/top.ftl" />  
<div class="wapper">
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">修改本账号密码</h1>
            <form id="dataForm" action="/bossUser/getBossUserOrgAndRoleInfo/modifyBossUserPassword" method="post">
                <ul class="form-1">
                    <li>
                        <label>登录账号：</label>
                        <p>${(bossUserOrgRole.userCode)!'' }</p>
                    </li>
                    <li>
                        <label>组织：</label>
                        <p>${(bossUserOrgRole.orgName)!'' }</p>
                    </li>
                    <li>
                        <label>角色：</label>
                        <p>${(bossUserOrgRole.roleName)!'' }</p>
                    </li>
                    <li>
                        <label>原登录密码：</label>
                        <p style="*margin-left: -170px;"><input class="text text-1" id="oldPassword" name="oldPassword" type="password" datatype="todo,*6-16" todo="newPassword" nullmsg="原密码不能为空！" errormsg="密码范围在6~16位之间！" /> </p>
                        <p></p>
                    </li>
                    <li>
                        <label>新登录密码：</label>
                        <p style="*margin-left: -170px;"><input class="text text-1" id="newPassword" name="newPassword" type="password" noteqto="oldPassword" noteqmsg="新密码与原密码不能一致！" datatype="*6-16,noteq" nullmsg="新密码不能为空！" errormsg="密码范围在6~16位之间！"/></p>
                    	<p></p>
                    </li>
                    <li>
                        <label>确认新密码：</label>
                        <p style="*margin-left: -170px;"><input class="text text-1" id="configPassword" name="configPassword" type="password" datatype="*" recheck="newPassword" errormsg="您两次输入的账号密码不一致！"/></p>
                    	<p></p>
                    </li>
                    <li>
                    	<label></label>
                    	<p style="color:#f00 ; font-size:12px;">${errorMsg !'' } ${modifyMsg !''}</p>
                    </li>
                    <li class="mt25">
                        <label></label>
                        <p style="*margin-left: -170px;"><input class="btn-blue" value="修改" type="submit" /><input class="btn-hui ml10" value="重置" type="reset" /></p>
                    </li>
                </ul>
            </form>
        </div>
    </div>
<!-- pageCenter over -->
</div>
<script type="text/javascript">
	$(function(){
		$("#dataForm").Validform({
        	tiptype:2
        });
		
		

	})
	
	$.Datatype.noteq = function(gets,obj,curform,regxp){
        	var value = $.trim(gets);
        	var name = obj.attr('noteqto');
        	var value1 = $.trim($('input[name='+ name +']', curform).val());
        	if(value === value1){
        		return obj.attr('noteqmsg');
        	} else {
        		return true;
        	}
        }
		
		$.Datatype.todo = function(gets,obj,curform,regxp){
			var name = obj.attr('todo');
			if($('input[name='+ name +']', curform)[0].value != ''){
				$('input[name='+ name +']', curform)[0].validform_lastval = '';
				$('input[name='+ name +']', curform).trigger("blur");
			}
			return true;
		}
</script>
</body>
</html>