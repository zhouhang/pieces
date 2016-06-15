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
			<h1 class="title1">权限管理</h1>
			<form id="queryPermissionForm" action="#" method="get">
				<ul class="form-search">
					<li><span>权限名称：</span> <input class="text text-2 mr10"
						type="text" value="${page.params.permissionName}"
						id="permissionNamePara" name="permissionNamePara" /> <span>上级名称：</span>
						<input class="text text-2 mr10" type="text"
						value="${page.params.parentName}" id="parentNamePara"
						name="parentNamePara" /> <span>路径：</span> <input
						class="text text-2 mr10" type="text"
						value="${page.params.operationResource}"
						id="operationResourcePara" name="operationResourcePara" /> <input
						type="button" class="btn-blue" value="查询"
						onclick="javascript:doQueryByPara();" /> <input type="button"
						class="btn-hui ml10" value="清空"
						onclick="javascript:doQueryByParaClean();" /></li>
				</ul>
				<div class="use-item1 mt25">
					<@shiro.hasPermission name="权限管理-添加权限"> <span class="btn-add mb10"><a
						href="#">添加权限</a> </span> </@shiro.hasPermission>
					<table class="table-1" style="width:100%; *width:88%;" cellpadding="1" cellspacing="1">
						<tr>
							<th width="120" align="center">权限名称</th>
							<th width="120" align="center">上级</th>
							<th width="150" align="center" style="text-align:center;">路径</th>
							<th width="150" align="center">添加时间</th>
							<th width="150" align="center">排序号</th>
							<th width="150" align="center">路径</th>
							<th width="150" align="center">操作</th>
						</tr>
						<#if (page.results?size)==0>
						<tr>
							<td colspan="6">没有数据!</td>
						</tr>
						</#if> <#list page.results as permission>
						<tr>
							<td align="center"><input type="hidden"
								id="permissionId_${permission.permissionId}"
								value="${permission.permissionId}" /> <input type="hidden"
								id="permissionName_${permission.permissionId}"
								value="${permission.permissionName}" />
								${permission.permissionName}</td>

							<td align="center">${permission.parentName}</td>
							<td align="center">${permission.operationResource}</td>
							<td align="center"><#if permission.createTime??>
								${permission.createTime?string("yyyy-MM-dd HH:mm:ss")} <#else>
								</#if></td>
							<td align="center">${permission.sortNo}</td>
							<td align="center">${permission.path}</td>

							<td class="opr-btn"><@shiro.hasPermission name="权限管理-修改权限">
								<span class="operate-2"
								onclick="showAlertDialog('${permission.permissionId}');"
								title="修改"> </span> </@shiro.hasPermission>

								<@shiro.hasPermission name="权限管理-删除权限"> <span class="operate-4"
								onclick="javascript:deletePermissionByID(${permission.permissionId});"
								title="删除"></span> </@shiro.hasPermission>
							</td>
						</tr>
						</#list>
					</table>
				</div>
				<@tools.pages page=page form="queryPermissionForm"/>
			</form>
		</div>
	</div>

	<!-- pageCenter over -->

</div>
<!-- zTree -->
<div id="addPermissionMenu" class="menuContent" title="选择权限"
	style="display: none; text-align: center;">
	<ul id="addPermissionTree" class="ztree"
		style="margin-top: 0; width: 128px;"></ul>
</div>
<div id="updatePermissionMenu" class="menuContent" title="选择权限"
	style="display: none; text-align: center;">
	<ul id="updatePermissionTree" class="ztree"
		style="margin-top: 0; width: 128px;"></ul>
</div>
<!-- zTree over -->
<!-- 弹层  -->
<!-- 添加权限 -->
<div class="" id="addPermission" style="display: none;" title="添加权限">
	<div class="box">
		<form id="AddPermissionForm" action="/getPermission/addPermission"
			method="post">
			<ul class="form-1 form-bk">
				<li><label>权限名称：</label>
					<p>
						<input class="text text-1 register-text" type="text"
							id="permissionName" name="permissionName" value="" datatype="*"
							ajaxurl="/getPermission/bossPermissionIsHaved" nullmsg="请输入权限名称！"
							errormsg="权限名在4~8位之间！" />
					</p>
					<p></p></li>
				<li><label>文件路径：</label>
					<p>
						<input class="text text-1 register-text" type="text"
							id="operationResource" name="operationResource" value="" />
					</p>
					<p></p></li>
				<li><label>上级目录：</label>
					<p>
						<input class="text text-1 register-text" type="text"
							id="parentName" name="parentName" value="" datatype="*"
							ajaxurl="" nullmsg="请选择上级目录！" errormsg="显示上级目录！"
							onclick="showPermissionMenuForAdd();" />
					</p>
					<p>
						<input type="hidden" id="parentId" name="parentId" value="" /> <input
							type="hidden" id="permissionType" name="permissionType" value="" />
						<input type="hidden" id="permissionPath" name="permissionPath"
							value="" />
					</p></li>
				<li><label>显示排序：</label>
					<p>
						<input class="text text-1 register-text" type="text"
							id="permissionSortNo" name="permissionSortNo" value=""
							datatype="*" ajaxurl="" nullmsg="请输入排序！" errormsg="显示排序！" />
					</p>
					<p></p></li>
				<li>
					<p>数字越小显示在越前，该排序是在你选择的该上级目录内之间排序</p>
				</li>
			</ul>
		</form>
	</div>
</div>
<!-- 添加权限 over  -->

<!-- 修改权限  -->
<div class="" id="updatePermissionDialog" style="display: none;"
	title="修改权限">
	<div class="box">
		<form id="updatePermissionForm"
			action="/getPermission/updatePermission" method="post">
			<input type="hidden" id="per_Id" />
			<ul class="form-1 form-bk">
				<li><label>权限名称：</label>
					<p>
						<input class="text text-1 register-text" type="text"
							id="alert_Permission_Name" name="permissionName" value=""
							datatype="*"
							ajaxurl="/getPermission/bossPermissionIsHavedForUpdate"
							nullmsg="请输入权限名称！" errormsg="权限名在4~8位之间！" /> <input
							type="hidden" id="alert_Permission_Id" name="permissionId" />

					</p>
					<p></p></li>
				<li><label>文件路径：</label>
					<p>
						<input class="text text-1 register-text" type="text"
							id="alert_OperationResource" name="operationResource" value="" />
					</p>
					<p></p></li>
				<li><label>上级目录：</label>
					<p>
						<input class="text text-1 register-text" type="text"
							id="alert_Parent_Name" name="parentName" value="" datatype="*"
							ajaxurl="" nullmsg="请选择上级目录！"
							onclick="showPermissionMenuForUpdate();" />
					</p>
					<p>
						<input type="hidden" id="parent_Id" name="parentId" value="" /> <input
							type="hidden" id="permission_Type" name="permissionType" value="" />
						<input type="hidden" id="permission_Path" name="permissionPath"
							value="" /> <input type="hidden" id="permission_Path_old"
							name="permissionPathOld" value="" />
						
					</p></li>
				<li><label>显示排序：</label>
					<p>
						<input class="text text-1 register-text" type="text"
							id="alert_Permission_SortNo" name="permissionSortNo" value=""
							datatype="*" ajaxurl="" nullmsg="请输入排序！" errormsg="显示排序！" />
					</p>
					<p></p></li>
			</ul>
		</form>
	</div>
</div>
<!-- 修改权限 over  -->
<!-- 提示框 (确定与取消)-->
<div class="msgdiv" id="classMsg" style="display: none;" title="">

	<ul class="form-s form-bk" style="text-align: center;">
		<li>
			<p id="classMsgInfo" class="msgtxt"></p>
		</li>
		<li class="mt25"><label></label>
			<p>
				<input type="button" class="btn-blue" value="确定" id="classMsgBtn" />
				<input type="button" class="btn-blue" value="取消"
					id="classMsgBtnCancel" />
			</p> <input type="hidden" id="msgClassId" /> <input type="hidden"
			id="msgId" /></li>
	</ul>
</div>
<!-- 提示框 (确定)-->
<div class="msgdiv" id="classMsgEnter" style="display: none;" title="">

	<ul class="form-s form-bk" style="text-align: center;">
		<li>
			<p id="classMsgInfoEnter" class="msgtxt"></p>
		</li>
		<li class="mt25"><label></label>
			<p>
				<input type="button" class="btn-blue" value="确定"
					id="classMsgBtnEnter" />
			</p></li>
	</ul>
</div>
<!-- 提示框 over -->
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
            $('#addPermission').dialog("open");
        })
        
       // 新增，初始化validform验证 
    	var addform= $("#AddPermissionForm").Validform({
    		btnSubmit:"#addPermissionSubmit",
    		btnReset:"#addPermissionReset",
    		tiptype:4,
    		ajaxPost:true,
    		showAllError:true,
    		callback:function(data){
    			if(data.status=="yes"){
    				$("#addPermission").dialog("close");
    				bghui();
			        Alert({
			            str:'添加成功！',
			            buttons:[{
			                name:'确定',
			                id:'1',
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
		    $("#addPermission").dialog({
        	autoOpen : false,
			modal : true,
			width : 794,
			resizable : false,
			buttons :[ 
			    {
			    	text:"确定",
			    	id: "addPermissionSubmit",
			    	click: function(){
			    		$("#AddPermissionForm").submit();
			    	}
			    },
			    {
			    	text:"重置",
			    	id:"addPermissionReset",
			    	click: function(){
			    		addform.resetForm();
			    	}
			    }
			],
			close: function(event, ui) { 
				addform.resetForm();
			}
        });
        
        
        
        
          // 修改，初始化validform验证 
    	var updateform= $("#updatePermissionForm").Validform({
    		btnSubmit:"#updatePermissionSubmit",
    		btnReset:"#updatePermissionReset",
    		tiptype:4,
    		ajaxPost:true,
    		showAllError:true,
    		callback:function(data){
    			if(data.status=="yes"){
    				$("#updatePermissionDialog").dialog("close");
    				bghui();
			        Alert({
			            str:'修改成功！',
			            buttons:[{
			                name:'确定',
			                id:'1',
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
		    $("#updatePermissionDialog").dialog({
        	autoOpen : false,
			modal : true,
			width : 794,
			resizable : false,
			buttons :[ 
			    {
			    	text:"修改",
			    	id: "updatePermissionSubmit",
			    	click: function(){
			    		$("#updatePermissionForm").submit();
			    		}
			    },
			    {
  			    	text:"取消",
  			    	id: "updatePermissionReset",
  			    	click: function(){
  			    		$("#updatePermissionDialog").dialog("close");
  			    		updateform.resetForm();
  			    	}
  			    }
			],
			close: function(event, ui) { 
				updateform.resetForm();
			}
        });
        
		    showAlertDialog= function(permissionId){
		    	$("#per_Id").val(permissionId);
		    	$.post("getPermission/findBossPermissionByCondition",{
		    		"permissionId":permissionId
		    	},function(data){
		    		$("#alert_Permission_Name").attr("ajaxurl","/getPermission/bossPermissionIsHavedForUpdate?permissionId="+data.permissionId);
		    		$("#alert_Permission_Id").val(data.permissionId);
		    		$("#alert_Permission_Name").val(data.permissionName);
		    		$("#alert_OperationResource").val(data.operationResource);
		    		$("#alert_Parent_Name").val(data.parentName);
		    		$("#alert_Permission_SortNo").val(data.sortNo);
		    		$("#parent_Id").val(data.parentId);
		    		$("#permission_Type").val(data.type);
		    		$("#permission_Path_old").val(data.path);
		    		if(data.type==0){
		    			Alert({
				            str:'一级目录不允许修改!',
				            buttons:[{
				                name:'确定',
				                id:'1',
				                ev:{click:function(data){
				                        disappear();
				                        $(".bghui").remove();
				                    }
				                }
				            }]
				        });
		    		}else{
				        $('#updatePermissionDialog').dialog("open");
		    		}
		    	});
		    }		
		
		//添加权限zTree树对话框
		    $("#addPermissionMenu").dialog({
	       		autoOpen : false,
				modal : true,
				resizable : false,
				buttons : {
					确定 : function() {
						var zTree = $.fn.zTree.getZTreeObj("addPermissionTree"),
						nodes = zTree.getSelectedNodes(),
						v = "";
						o = "";
						t = "";
						s = "";
						p = "";
						nodes.sort(function compare(a,b){return a.id-b.id;});
						for (var i=0, l=nodes.length; i<l; i++) {
							v += nodes[i].name + ",";
							o += nodes[i].id + ",";
							t += nodes[i].type + ",";
							s += nodes[i].sortno + ",";
							p += nodes[i].path + ",";
						}
						if (v.length > 0 ) v = v.substring(0, v.length-1);
						if (o.length > 0 ) o = o.substring(0, o.length-1);
						if (t.length > 0 ) t = t.substring(0, t.length-1);
						if (s.length > 0 ) s = s.substring(0, s.length-1)+"-";
						if (p.length > 0 ) p = p.substring(0, p.length-1);
						$("#parentName").val(v);
						$("#parentId").val(o);
						$("#permissionType").val(t);
						$("#permissionSortNo").val(s);
						$("#permissionPath").val(p);
						$("#addPermissionMenu").dialog("close");
					},
					取消 : function() {
						$("#addPermissionMenu").dialog("close");
					}
				}
	       });
        
		//修改权限zTree树对话框
		     $("#updatePermissionMenu").dialog({
		          	autoOpen : false,
		   			modal : true,
		   			resizable : false,
		   			buttons : {
		   				确定 : function() {
		   					var zTree = $.fn.zTree.getZTreeObj("updatePermissionTree"),
		   					nodes = zTree.getSelectedNodes(),
		   					v = "";
		   					o = "";
							t = "";
							s = "";
							p = "";
		   					nodes.sort(function compare(a,b){return a.id-b.id;});
		   					for (var i=0, l=nodes.length; i<l; i++) {
								v += nodes[i].name + ",";
								o += nodes[i].id + ",";
								t += nodes[i].type + ",";
								s += nodes[i].sortno + ",";
								p += nodes[i].path + ",";
		   					}
		   					if (v.length > 0 ) v = v.substring(0, v.length-1);
		   					if (o.length > 0 ) o = o.substring(0, o.length-1);
		   					if (t.length > 0 ) t = t.substring(0, t.length-1);
		   					if (p.length > 0 ) p = p.substring(0, p.length-1);
							if (s.length > 0 ) s = s.substring(0, s.length-1)+"-";
							$("#alert_Parent_Name").val(v);
							$("#parent_Id").val(o);
							$("#permission_Type").val(t);
							$("#alert_Permission_SortNo").val(s);
							$("#permission_Path").val(p);
							if($("#alert_Parent_Name").val() == $("#alert_Permission_Name").val())
								{
								$("#alert_Parent_Name").val("");
								Alert({
						            str:'上级名称不能和原名称一样!',
						            buttons:[{
						                name:'确定',
						                id:'1',
						                ev:{click:function(data){
						                        disappear();
						                        $(".bghui").remove();
						                    }
						                }
						            }]
						        });
								}else{
									$("#updatePermissionMenu").dialog("close");
								}
		   					
		   				},
		   				取消 : function() {
		   					$("#updatePermissionMenu").dialog("close");
		   				}
		   			}
		      });		
		
		
		
        
    });
    	
    
      <!--  使用参数查询  -->
	  function doQueryByPara() {
	 		var permissionNamePara = $('#permissionNamePara');
	 		var parentNamePara = $('#parentNamePara');
	 		var operationResourcePara = $('operationResourcePara');
	 		$('#queryPermissionForm').submit();
      }
	  <!--  使用参数清空  -->
	  function doQueryByParaClean() {
		  	$('#permissionNamePara').val("");
		  	$('#parentNamePara').val("");
		  	$('#operationResourcePara').val("");
	 		
    }
       <!--  弹出修改权限框  -->
      function updatePermissionByID(permissionId) {
     		 var html = '<div class="bghui"></div>';
			$('#updatePermissionId').val($('#permissionId_'+permissionId).val());
	 		$('#updatePermissionName').val($('#permissionName_'+permissionId).val());	
	 		var urlname="validateUpdatePermissionName?permissionId="+$('#permissionId_'+permissionId).val();
	 		$('#updatePermissionName').attr("ajaxurl",urlname);
	 		$('#OriPermissionName').val($('#permissionName_'+permissionId).val());	
	 		 $('#updatePermission').dialog("open");
	        $('body').append(html);
	   }
      
	
       <!--  删除按钮  -->
      function deletePermissionByID(permissionId){
    	  bghui();
          Alert({str:'确定要删除该权限吗？',
              buttons:[
                  {
                      name:'确定',
                      id:'1',
                      classname:'btn-style',
                      ev:{click:function(data){
                          disappear();
                          $(".bghui").remove();
                          deletePermissionByID1(permissionId);
                      }}
                  },
                  {
                      name:'取消',
                      id:'2',
                      classname:'btn-hui',
                      ev:{click:function(data){
                          disappear();
                          $(".bghui").remove();
  						  return;
                      }}
                  }
              ]
          });
      }
      function deletePermissionByID1(permissionId) {
    	  /* $("#classMsg").attr('title','权限删除提示');
    	  $("#classMsgInfo").html("确定要删除该权限吗？");
    	  $("#classMsg").dialog(); */
    	  
		  //$("#classMsgBtn").click(function(){
				//删除前要判断，是否有子结点
				 $.post("/getPermission/validatePermissionDeleForNote",{
	       		"permissionId":permissionId
	     		},function(dataNote){
	     			if(dataNote==0){
	     				$.post("/getPermission/validatePermissionDeleForRole",{
	     		       		"permissionId":permissionId
	     		     		},function(dataRole){
	     		     			if(dataRole==0){
	     		     				$.post("/getPermission/deletePermission",{
	     		     					"permissionId":permissionId
	     		     				},function(datadel){
	     		     					if(datadel==1){
	     		     						//$("#classMsg").dialog("close");
	     		     						
	     		     						Alert({
	     							            str:'删除成功！',
	     							            buttons:[{
	     							                name:'确定',
	     							                id:'1',
	     							                ev:{click:function(data){
	     							                	disappear();
	     							                	disappear();
	     						                        $(".bghui").remove();
	     						                        //重新刷新页面
	     										       	doQueryByPara();
	     							                    }
	     							                }
	     							            }]
	     							        });
	     		     						
	     		     					}else{
	     		     						Alert({
	     							            str:'删除失败！',
	     							            buttons:[{
	     							                name:'确定',
	     							                id:'1',
	     							                ev:{click:function(data){
	     							                	disappear();
	     							                	$('#queryPermissionForm').submit();
	     							                    }
	     							                }
	     							            }]
	     							        });
	     		     					}
	     		     				});
	     		     				
	     		     			}else{
	     		     				//$("#classMsg").dialog("close");
	     		     				$("#classMsgEnter").attr('title','权限删除提示');
	     		     				$("#classMsgEnter").dialog();
	     		     		    	$("#classMsgInfoEnter").html("此权限已被使用，请先解除对该权限的使用！");
	     		     		    	$("#classMsgBtnEnter").click(function(){
	     		     					$("#classMsgEnter").dialog("close");
	     		     				});
	     		     			}
	     		     		});	
	     				
	     			}else{
	     				//$("#classMsg").dialog("close");
	     				$("#classMsgEnter").attr('title','权限删除提示');
	     				$("#classMsgEnter").dialog();
	     		    	$("#classMsgInfoEnter").html("要删除的权限下有子权限，请先删除所有子权限！");
	     		    	$("#classMsgBtnEnter").click(function(){
	     					$("#classMsgEnter").dialog("close");
	     				}); 
	     			}
	     		});
			//});
		  
		  /* $("#classMsgBtnCancel").click(function(){
			  $("#classMsg").dialog("close");
		  }); */

    	  
	    	
     		 
	 		
      }
       <!--  权限分配 -->
      
       function setPermission(permissionId) {
			location.href="setPermission?permissionId="+permissionId;
		}
      
        <!--  查看账户--> 
       function getUsers(permissionId) {
       		var roleName=$('#permissionName_'+permissionId).val();
			location.href="getBossUserManager?permissionId="+permissionId+"&permissionName="+permissionName;
		}

       
       
       
       
   	/* 权限树zNodes(json格式) */	
   	var rzNodes;
   	$.ajax({
   		async : false,
   		cache : false,
   		type : "POST",
   		dataType : "json",
   		url : "getPermission/getPermissionTree",// 角色树getPermissionTree
   		success : function(data) { // 请求成功后处理函数。
   			rzNodes = data; // 把后台封装好的简单Json格式赋给zNodes
   		}
   	});
       
       
       /* 添加弹出框权限树配置 */
		var addPermissionsetting = {
				view: {
					dblClickExpand: false
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};
       

       
       function showPermissionMenuForAdd() {
    	$.fn.zTree.init($("#addPermissionTree"), addPermissionsetting, rzNodes);//初始化权限树
       	$("#addPermissionMenu").dialog("open");
       }
       function showPermissionMenuForUpdate() {
      	$.fn.zTree.init($("#updatePermissionTree"), updatePermissionsetting, rzNodes);//初始化权限树
        $("#updatePermissionMenu").dialog("open");
          }       
  
       
       /* 修改弹出框权限树配置 */
       var updatePermissionsetting = {
       		view: {
       			dblClickExpand: false
       		},
       		data: {
       			simpleData: {
       				enable: true
       			}
       		}
       	};
       
       

       
       

       
       
</script>
</body>
</html>