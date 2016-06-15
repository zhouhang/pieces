<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>中药材电子商务管理系统</title>
	<#include "home/common.ftl">
</head>
<body>
<#include "home/top.ftl" />  
<#import 'macro.ftl' as tools>
<div class="wapper">
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">角色管理</h1>
             <form   id="queryRoleForm" action="bossRole" method="get" >
                <ul class="form-search">
                    <li><span>角色名：</span><input class="text text-2 mr10" type="text" value="${page.params.roleName}" id="roleNamePara" name="roleNamePara"/>
                        <input type="button" class="btn-blue" value="查询"  onclick="javascript:doQueryByPara();" />
                    </li>
                </ul>
            
            <div class="use-item1 mt25">
				 <@shiro.hasPermission name="角色管理-添加角色">
					 <span class="btn-add mb10" ><a href="#">添加角色</a> </span>
				  </@shiro.hasPermission>
				<table class="table-1" style="width:100%; *width:88%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="20%">角色</th>
                        <th width="20%">添加时间</th>
                        <th width="20%">角色权限分配</th>
                        <th width="20%">查看账号</th>
                        <th width="20%">操作</th>
                    </tr>
                     <#if (page.results?size)==0>
                    	<tr>
                    		<td colspan="5">没有数据!</td>
                    	</tr>
                    </#if>
                <#list page.results as role>
                    <tr>
                    	<td align="center"><input type="hidden" id="roleId_${role.roleId}" value="${role.roleId}"/>
                    					   <input type="hidden" id="roleName_${role.roleId}" value="${role.roleName}"/>
                       					 ${role.roleName}</td>
                       	<td align="center">${role.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <td align="center">
                        		<@shiro.hasPermission name="角色管理-角色权限分配">
                        			<a href="javascript:setPermission(${role.roleId});" class="blue">权限分配</a> 
                        		</@shiro.hasPermission>
                        </td>
                        <td align="center"><a href="javascript:getUsers(${role.roleId});" class="blue">查看账号</a></td>
                        <td class="opr-btn">
                        	<@shiro.hasPermission name="角色管理-修改角色">
                        	 	<span class="operate-2" onclick="javascript:updateRoleByID(${role.roleId});" title="修改" > </span>
				  			</@shiro.hasPermission>
                           <@shiro.hasPermission name="角色管理-删除角色">
                            	<span class="operate-4" onclick="javascript:deleteRoleByID(${role.roleId});"  title="删除"></span>
                            </@shiro.hasPermission>
                        </td>
                    </tr>
                 </#list>
                </table>
            </div>
   				 <@tools.pages page=page form="queryRoleForm"/>
            </form>
        </div>
    </div>
<!-- pageCenter over -->

</div>

<!-- 弹层  -->
<!-- 添加角色  -->
<div class="" id="addRole" style="display: none;" title="添加角色">
    <div class="box">
         <form id="AddRoleForm" action="bossRole/addRole" method="post" >
            <ul class="form-1">
                <li>
                    <label>角色：</label>
                     <p><input class="text text-1 register-text" type="text" id="newRoleName"  name="newRoleName" value=""    datatype="*" ajaxurl="bossRole/validateNewRoleName" nullmsg="请输入角色！" errormsg="角色名在6~16位之间！"  /></p>
	                <p></p>
                </li>
            </ul>
        </form>
    </div>
</div>
<!-- 添加角色 over  -->

<!-- 修改角色  -->
<div class="" id="updateRole" style="display: none;" title="修改角色">
    <div class="box">
         <form id="updateRoleForm" action="bossRole/updateRole" method="post" >
            <ul class="form-1 form-bk">
                <li>
                    <label>角色：</label>
                    <p><input class="text text-1 register-text" type="text" id="updateRoleName"  name="updateRoleName"    datatype="*" ajaxurl=""  nullmsg="请输入角色！" errormsg="角色名在6~16位之间！"  />
                    	<input  type="hidden" id="updateRoleId" name="updateRoleId"/>
                    	<input  type="hidden" id="OriRoleName"/>
                    </p>
	                <p></p>
                </li>
            </ul>
        </form>
    </div>
</div>
<!-- 修改角色 over  -->
<!-- 弹层 over -->
<script>
    $(function(){
        //弹层
        var Close = $('.close');
		var html = '<div class="bghui"></div>';

       //关闭弹层
        Close.each(function(){
            $(this).click(function(){
                $(this).parent().hide();
                $('.bghui').remove();
            })
        });
        //增加弹层
        $('.btn-add').on('click',function(){
            $('#addRole').dialog("open");
            $('body').append(html);
        })
        
       // 新增，初始化validform验证 
    	var addform= $("#AddRoleForm").Validform({
    		btnSubmit:"#addRoleSubmit",
    		tiptype:4,
    		ajaxPost:true,
    		showAllError:true,
    		callback:function(data){
    			if(data.status=="yes"){
    				$("#addRole").dialog("close");
    				bghui();
			        Alert({
			            str:'添加成功！',
			            buttons:[{
			                name:'确定',
			                id:'1',
			                classname:'btn-style',
			                ev:{click:function(data){
			                        disappear();
			                        $(".bghui").remove();
			                        //重新刷新页面
							       	doQueryByPara();
			                    }
			                }
			            }]
			        });
    			}
    		}
    	});

        	
		//新增框架验证初始化
		    $("#addRole").dialog({
        	autoOpen : false,
			modal : true,
			width : 794,
			resizable : false,
			buttons :[ 
			    {
			    	text:"确定",
			    	id: "addRoleSubmit",
			    	click: function(){
			    		$("#AddRoleForm").submit();
			    	}
			    }
			  
			],
			close: function(event, ui) { 
				addform.resetForm();
			}
        });
        
        
        
        
          // 修改，初始化validform验证 
    	var updateform= $("#updateRoleForm").Validform({
    		btnSubmit:"#updateRoleSubmit",
    		tiptype:4,
    		ajaxPost:true,
    		showAllError:true,
    		callback:function(data){
    			if(data.status=="yes"){
    				$("#updateRole").dialog("close");
    				bghui();
			        Alert({
			            str:'修改成功！',
			            buttons:[{
			                name:'确定',
			                id:'1',
			                classname:'btn-style',
			                ev:{click:function(data){
			                        disappear();
			                        $(".bghui").remove();
			                        //重新刷新页面
							       	doQueryByPara();
			                    }
			                }
			            }]
			        });
    			}
    		}
    	});

        	
		//修改框架验证初始化
		    $("#updateRole").dialog({
        	autoOpen : false,
			modal : true,
			width : 794,
			resizable : false,
			buttons :[ 
			    {
			    	text:"修改",
			    	id: "updateRoleSubmit",
			    	click: function(){
			    		$("#updateRoleForm").submit();
			    	}
			    }
			  
			],
			close: function(event, ui) { 
				updateform.resetForm();
			}
        });
    });
    	
    
      <!--  使用参数查询  -->
	  function doQueryByPara() {
	 		var roleNamePara = $('#roleNamePara');
       		$('#queryRoleForm').submit();
      }
       <!--  弹出修改角色框  -->
      function updateRoleByID(roleId) {
     		 var html = '<div class="bghui"></div>';
			$('#updateRoleId').val($('#roleId_'+roleId).val());
	 		$('#updateRoleName').val($('#roleName_'+roleId).val());	
	 		var urlname="/bossRole/validateUpdateRoleName?OriRoleName="+$('#roleName_'+roleId).val()+"&roleId="+$('#roleId_'+roleId).val();
	 		$('#updateRoleName').attr("ajaxurl",urlname);
	 		$('#OriRoleName').val($('#roleName_'+roleId).val());	
	 		 $('#updateRole').dialog("open");
	        $('body').append(html);
	   }
      
       <!--  删除按钮  -->
      function deleteRoleByID(roleId) {
	      	bghui();
	        Alert({
	            str:'确定要删除该角色吗？',
	            buttons:[{
	                name:'确定',
	                id:'1',
	                classname:'btn-style',
	                ev:{click:function(data){
	                        disappear();
	                        $(".bghui").remove();
	                      //删除前要判断，是否有子账号
	                		 //需要先对角色进行验证，判断状态
	                		$.post("bossRole/validateRoleDele",{
	                   			"roleId":roleId
	                 		},function(date){
		                   		if(date.indexOf("00")>-1){
		                   				//开始删除
		                   				$.post("bossRole/deleteRoleById",{
		                   					"roleId":roleId
		           	      				},function(){
											bghui();
									        Alert({
									            str:'删除成功！',
									            buttons:[{
									                name:'确定',
									                id:'1',
									                classname:'btn-style',
									                ev:{click:function(data){
									                        disappear();
									                        $(".bghui").remove();
									                        //重新刷新页面
													       	doQueryByPara();
									                    }
									                }
									            }]
									        });
		           			             });
		                   		}else{
		                   			if(date.indexOf("01")>-1){
		                   				alert("错误，角色已经被删除，请重新查询!");
		           	        		}else{
		           	        			alert("该角色下已经有工作账号，不能删除！");
		           	        		}
		                   		}
	                   	   });
	                    }
	                }
	            },{
	            	name:'取消',
	                id:'2',
	                classname:'btn-hui',
	                ev:{click:function(data){
	                        disappear();
	                        $(".bghui").remove();
	                    }
	                }
	            }]
	        });
      }
       <!--  权限分配 -->
      
       function setPermission(roleId) {
      		 var roleNamePara = $('#roleNamePara').val();
			location.href="bossRole/setPermission?roleId="+roleId+"&roleNamePara="+roleNamePara;
		}
      
        <!--  查看账户--> 
       function getUsers(roleId) {
       		var roleName=$('#roleName_'+roleId).val();
			location.href="/bossUser?roleId="+roleId+"&roleName="+roleName;
		}
      
</script>
</body>
</html>