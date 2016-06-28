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
            <h1 class="title1">权限分配</h1>
            <form   id="queryPermissionForm" action="setPermission" method="get" >
       <!-- 0级 系统 -->  
            <div class="formbg1">
            	 <input type="hidden" id="roleId" name="roleId" value="${roleId}"/>
            	 <input type="hidden" id="roleNamePara" name="roleNamePara" value="${roleNamePara}"/>
                <select class="text text-2" name="level0Id" id="level0Id" onchange="javascript:changeLevel0();">
                        	<#list Level0Permission as p0>
                        		<option value="${p0.permissionId}" <#if "${p0.permissionId}"="${level0Id}">selected</#if> >${p0.permissionName}</option>
                        	</#list>
               </select>
            </div>
        <!-- 1级 目录 -->  
            <div class="box2">
            	<#if Level1Permission?exists >
                	<ul class="tabs-1" id="tabs">
                </#if>
	                  <#assign   i=0 />
	                  <#list Level1Permission as p1>
								<#if "${i}"="0">
		                    		<li class="cur">${p1.permissionName}</li>
		                    	<#else>
		                    		<li>${p1.permissionName}</li>
		                    	</#if>
								 <#assign i=i+1 />		
	                   </#list> 
               <#if Level1Permission?exists >
            	 </ul>
            	</#if>  
               
          <!-- 2级 菜单，3级按钮 -->     
                <div class="tabcont" id="tabCont"> 
		                 <#assign   j=0 />
		                 <#list Level1Permission as p1>
		                 		<#if "${j}"="0">
		                 			 <div id="selectedDiv" style="display: block;">
		                 		<#else>
		                 			  <div style="display: none;">
		                 		</#if>
		                 			<dl>
		                 				<#list p1.sonList as p2>
		                           			 <dt><input type="checkbox" id="${p2.permissionId}"  value="${p2.permissionId}" 
		                           			 		<#if "${p2.isPermissioned}"="1">checked</#if>  />
		                           			 	 ${p2.permissionName}
		                           			 </dt>
		                           			  <!-- 3级按钮 -->  
		                           			 	<#list p2.sonList as p3>
		                           			 		<dd><input type="checkbox" id="{p3.permissionId}"  value="${p3.permissionId}" 
		                           			 			<#if "${p3.isPermissioned}"="1">checked</#if> />
		                           			 			${p3.permissionName}
		                           			 		</dd>
		                           			 	</#list> 
		                           		</#list> 
		                           </dl>
		                    </div>
		                  <#assign j=j+1 />		
		                </#list>   
                 </div>
                <div class="btn">
                    <input type="button" class="btn-blue" value="保存" onclick="javascript:dosave();" />
                    <input type="button" class="btn-hui2 ml10" value="全选" onclick="javascript:selectAll();" />
                    <input type="button" class="btn-hui ml10" value="反选" onclick="javascript:unSelect();" />
                </div>
            </div>
			  </form>
        </div>
    </div>
<!-- pageCenter over -->
</div>

<!-- 修改角色 over  -->
<!-- 弹层 over -->
<script>
 $(function(){
        var Width = $('.tabs-1>li').width() * $('.tabs-1>li').length+10;
        $('#tabs').width(Width);
        $('#tabs>li').on('click',function(){
            $(this).addClass('cur').siblings().removeClass('cur');
            var j = $(this).parent().next('#tabCont').children('div');
            j.eq($(this).index()).show().siblings().hide();
             //换ID名称,方便后边使用
            j.eq($(this).index()).attr("id","selectedDiv").siblings().attr("id","");
        });
    })

     
     
     function changeLevel0() {
		var level0Id=$('#level0Id').val();
       	$('#queryPermissionForm').submit();
	 }
	 
	  <!--  保存 -->
      	function dosave() {
			var roleId = $('#roleId').val();	
			var level0Id=$('#level0Id').val();
			var roleNamePara=$('#roleNamePara').val();
			
			var permissionIds=[];
			$("input[type='checkbox']").each(function(){ //由于复选框一般选中的是多个,所以可以循环输出
				 if($(this).attr("checked")){
				 		permissionIds.push($(this).val()||'');
				 }
  			 });
  			 //ajax提交
  			 //开始保存
			$.post("saveRolePermission",{
				"roleId":roleId,
				"level0Id":level0Id,
				"permissionIds":permissionIds
			},function(){
						bghui();
				        Alert({
				            str:'权限分配成功！',
				            buttons:[{
				                name:'确定',
				                id:'1',
				                classname:'btn-style',
				                ev:{click:function(data){
				                        disappear();
				                        $(".bghui").remove();
				                        	location.href="/bossRole?roleNamePara="+roleNamePara;
				                    }
				                }
				            }]
				        });
						
             });
		}
		
		  <!--  全选  只控制当前展示的DIV中的内容 -->
      	function selectAll() {
      		$("#selectedDiv input[type='checkbox']").attr("checked",true);
		}
		
		  <!--  反选-->
      	function unSelect() {
      		$("#selectedDiv input[type='checkbox']").attr("checked",false);
		}
		
	
</script>
</body>
</html>