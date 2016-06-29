<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>中药材电子商务管理系统</title>
</head>
<body>
<#include "home/common.ftl">
<#include "home/top.ftl" />  
<#import 'macro.ftl' as tools>
<div class="wapper">
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
<div class="main">
	<div class="page-main">
		<h1 class="title1">绑定业务员</h1>
		<form action="/getMemberManage/selectSalesMan" method="get" id="conditionForm">
			<ul class="form-search ac-search">
				<!--会员ID,隐藏域-->
				<input type="hidden" name="memberId" id="memberId" value="${memberId}"/>
				<input type="hidden" name="regStartDate" id="regStartDate" value="${regStartDate}"/>
				<input type="hidden" name="regEndDate" id="regEndDate" value="${regEndDate}"/>
				<input type="hidden" name="ucuserName" id="ucuserName" value="${ucuserName}"/>
				<input type="hidden" name="mobileNo" id="mobileNo" value="${mobileNo}"/>
				<input type="hidden" name="realName" id="realName" value="${realName}"/>
				<input type="hidden" name="salesMan" id="salesMan" value="${salesMan}"/>
				<li> <span>姓名：</span><input class="text text-4 mr10" id="userName" name="userName" value="${page.params.userName}" type="text" /> 
					 <input type="button" id="subCForm" class="btn-blue mr10" value="查询" />
			    </li>
			</ul>
		</form>
		<div class="use-item1 mt25">
			<table class="table-1" style="width: 80%;" cellpadding="1" cellspacing="1">
				<tr>
					<th width="20"></th>
					<th width="70">姓名</th>
					<th width="70">登录用户名</th>
					<th width="150">组织</th>
					<th width="70">手机</th>

				</tr>
				<#if (page.results?size)==0>
				<tr>
					<td colspan="8">没有数据!</td>
				</tr>
				</#if> <#list page.results as bossuser>
				<tr>
					<td><input type="radio" name="userId" value='${bossuser.userId}'/></td>
					<td>${bossuser.userName!'' }</td>
					<td>${bossuser.userCode!'' }</td>
					<td>${bossuser.orgName!'' }</td>
					<td>${bossuser.mobile!'' }</td>
				</tr>
				</#list>
				
			</table>
			<div style="width: 80%;" ><@tools.pages page=page form="conditionForm"/></div>
		</div>
			
	</div>
	<div align="center" style="width: 80%;"  class="clearfix">
		<input type="button" class="btn-blue mr10" value="确定" id="selectConfirm"  />
		<input type="button" class="btn-hui ml10" value="取消" id="selectCancel" onclick="javascript:window.history.go(-1);"  />
	</div>
</div>
<!-- pageCenter over -->

</div>


<script>
	//查询按钮触发查询
	$("#subCForm").click(function(){
	    $("#conditionForm").submit();
	});
	
	//绑定选中的业务员
	$("#selectConfirm").click(function(){
		var _salesManId;//业务员ID
		$("input[type='radio']").each(function(){ 
			 if($(this).attr("checked")){
				 _salesManId=$(this).val();
			 }
		});
		if(_salesManId==null){
			  Alert({
		            str:'请选择一个业务员！'
		        });
			  return;
		}
		var _memberId = $("#memberId").val();
		
		$.ajax({
    		type:"POST",
    		url:"/getMemberManage/bindSalesMan",
    		data:{salesManId:_salesManId,memberId:_memberId},
    		dataType:"json",
    		success:function(data){
    			if(data.status == 'error'){
    				 Alert({
    			            str:'绑定业务员失败！'
    			        });
    			}else{
    				 Alert({
    			            str:'绑定业务员成功！'
    			       });
    				 
    				 var _regStartDate = $("#regStartDate").val();
    					var _regEndDate = $("#regEndDate").val();
    					var _mName = $("#ucuserName").val();
    					var _mobNO = $("#mobileNo").val();
    					var _rname = $("#realName").val();
    					var _salesMan = $("#salesMan").val();
    					window.location.href="/getMemberManage/searchMemberCondition?regStartDate="+ _regStartDate + "&regEndDate="+ _regEndDate + "&userName=" + _mName + "&mobileNo=" + _mobNO + "&realName=" + _rname+ "&salesMan=" + _salesMan;
    			}
    		}
		});
	});
		
</script>
</body>
</html>