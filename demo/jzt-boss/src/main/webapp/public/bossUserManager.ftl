<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>中药材电子商务管理系统</title>
</head>
<body>
	<#include "home/common.ftl"> <#include "home/top.ftl" /> <#import
	'macro.ftl' as tools>
	<div class="wapper">
		<#include "home/left.ftl" />
		<!-- pageCenter start -->
		<div class="main">
			<div class="page-main">
				<h1 class="title1">工作账号管理</h1>
				<form action="/bossUser" method="get" id="conditionForm">
					<ul class="form-search ac-search">
						<li><span>工作帐号：</span><input class="text text-4 mr10"
							id="userCode" name="userCode" value="${page.params.userCode}"
							type="text" /> <span>姓名：</span><input class="text text-4 mr10"
							id="userName" name="userName" value="${page.params.userName}"
							type="text" /> <span>组织：</span><input class="text text-4 mr10"
							id="orgName" name="orgName" value="${page.params.orgName}"
							onFocus="showMenu();" type="text" /> <span>角色：</span><input
							class="text text-4 mr10" id="roleName" name="roleName"
							value="${page.params.roleName}" onFocus="showRoleMenu();"
							type="text" /> <input type="hidden" id="roleId" name="roleId"
							value="${page.params.roleId}" /> <input type="hidden" id="orgId"
							name="orgId" value="${page.params.orgId}" /> <input
							type="button" id="subCForm" class="btn-blue mr10" value="查询" /><input
							type="button" onclick="clearForm('conditionForm')"
							class="btn-hui" value="清空" /></li>
					</ul>
				</form>
				<div class="use-item1 mt25">
					<@shiro.hasPermission name="工作账号管理-添加工作账号"> <span
						class="btn-add mb10"><a href="#">添加工作账号</a> </span>
					</@shiro.hasPermission>
					<table class="table-1" style="width: 100%;" cellpadding="1"
						cellspacing="1">
						<tr>
							<th width="40">ID</th>
							<th width="110">工作账号</th>
							<th width="80">姓名</th>
							<th width="100">手机</th>
							<th>组织</th>
							<th>角色</th>
							<th width="160">添加时间</th>
							<th width="160">修改时间</th>
							<th width="170">操作</th>
						</tr>
						<#if (page.results?size)==0>
						<tr>
							<td colspan="8">没有数据!</td>
						</tr>
						</#if> <#list page.results as bossuser>
						<tr>
							<td>${bossuser.userId!'' }</td>
							<td>${bossuser.userCode!'' }</td>
							<td>${bossuser.userName!'' }</td>
							<td>${bossuser.mobile!'' }</td>
							<td>${bossuser.orgName!'' }</td>
							<td>${bossuser.roleName!'' }</td>
							<td>${bossuser.createTime?string("yyyy-MM-dd HH:mm:ss")!'' }</td>
							<td>${bossuser.updateTime?string("yyyy-MM-dd HH:mm:ss")!'' }</td>
							<td class="opr-btn"><@shiro.hasPermission
								name="工作账号管理-工作账号状态"> <#if (bossuser.status==0)> <span
								class="operate-1 cur"
								onclick="islock('${bossuser.userCode!'' }',${bossuser.status!'' });">
									<div class="tips">已开通</div>
							</span> <#elseif (bossuser.status==3)> <span class="operate-1"
								onclick="islock('${bossuser.userCode!'' }',${bossuser.status!'' });">
									<div class="tips">已锁定</div>
							</span> </#if> </@shiro.hasPermission> <@shiro.hasPermission
								name="工作账号管理-修改工作账号"> <span class="operate-2"
								onclick="showAlertDialog('${bossuser.userCode!'' }');"></span>
								</@shiro.hasPermission> <@shiro.hasPermission
								name="工作账号管理-添加工作账号"> <span class="operate-3"
								onclick="secretReset(${bossuser.userId!''});"></span>
								</@shiro.hasPermission> <@shiro.hasPermission
								name="工作账号管理-添加工作账号"> <span class="operate-4a"></span>
								</@shiro.hasPermission>
							</td>
						</tr>
						</#list>
					</table>
				</div>
				<@tools.pages page=page form="conditionForm"/>
			</div>
		</div>
		<!-- pageCenter over -->
	</div>
	<!-- zTree -->
	<div id="menuContent" class="menuContent" style="display: none; position: absolute;">
		<ul id="orgTree" class="ztree ztree-bg" style="margin-top: 0;"></ul>
	</div>
	<div id="addMenuContent" class="menuContent" title="选择组织" style="display: none; text-align: center;">
		<ul id="addOrgTree" class="ztree" style="margin-top: 0; width: 128px;"></ul>
	</div>
	<div id="alertMenuContent" class="menuContent" title="选择组织" style="display: none; text-align: center;">
		<ul id="alertOrgTree" class="ztree" style="margin-top: 0; width: 128px;"></ul>
	</div>
	<div id="roleMenu" class="menuContent"
		style="display: none; position: absolute;">
		<ul id="roleTree" class="ztree ztree-bg" style="margin-top: 0;"></ul>
	</div>
	<div id="addRoleMenu" class="menuContent" title="选择角色"
		style="display: none; text-align: center;">
		<ul id="addRoleTree" class="ztree"
			style="margin-top: 0; width: 128px;"></ul>
	</div>
	<div id="alertRoleMenu" class="menuContent" title="选择角色"
		style="display: none; text-align: center;">
		<ul id="alertRoleTree" class="ztree"
			style="margin-top: 0; width: 128px;"></ul>
	</div>
	<!-- zTree over -->
	<!-- 弹层  -->
	<!-- 添加工作账号  -->
	<div class="" id="addAccountDialog" title="添加工作账号">
		<div class="box">
			<form id="addAccountForm" action="/bossUser/addBossUserManager"
				method="post">
				<ul class="form-1">
					<li><label><i class="red">*</i> 登录用户名：</label>
						<p>
							<input class="text text-1 register-text" datatype="nameValid"
								ajaxurl="/bossUser/bossUserIsHaved" nullmsg="请输入用户名！"
								errormsg="登录名在2-25位之间！" type="text" id="adduserCode"
								name="userCode" value="" />
						</p>
						<p>
							<span class="Validform_checktip"></span>
						</P></li>
					<li><label><i class="red">*</i> 登录密码：</label>
						<p>
							<input class="text text-1 register-text" datatype="*6-16"
								nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！" id="password"
								name="password" type="password" value="" />
						</p>
						<p>
							<span class="Validform_checktip"></span>
						</P></li>
					<li><label><i class="red">*</i> 确认密码：</label>
						<p>
							<input class="text text-1 register-text" value="" id="repassword"
								datatype="*" recheck="password" nullmsg="请再输入一次密码！"
								errormsg="您两次输入的账号密码不一致！" name="repassword" type="password" />
						</p>
						<p>
							<span class="Validform_checktip"></span>
						</P></li>
					<li><label><i class="red">*</i> 姓 名：</label>
						<p>
							<input class="text text-1 register-text" value="" datatype="*2-6"
								nullmsg="请输入中文姓名！" errormsg="请填写2-6个中文姓名！" id="userName"
								name="userName" type="text" />
						</p>
						<p>
							<span class="Validform_checktip"></span>
						</P></li>
					<li><label><i class="red">*</i> 手 机：</label>
						<p>
							<input class="text text-1 register-text" value="" datatype="mo"
								nullmsg="请输入手机号码！" errormsg="请输入11位数字！" id="mobile"
								name="mobile" type="text" />
						</p>
						<p>
							<span class="Validform_checktip"></span>
						</P></li>
					<li><label><i class="red">*</i> 账号组织：</label>
						<p>
							<input class="text text-1 register-text" value="" datatype="*"
								nullmsg="请选择组织！" type="text" id="addOrgName"
								onclick="showMenu1();" />
						</p>
						<p>
							<span class="Validform_checktip"></span>
						</P> <input type="hidden" id="addOrgId" name="orgId" value="" /></li>
					<li><label>选择角色：</label>
						<p>
							<input class="text text-1" value="" type="text" id="addRoleName"
								onclick="showAddRoleMenu();" />
						</p> <input type="hidden" id="addRoleId" name="roleId" value="" /></li>
				</ul>
			</form>
		</div>
	</div>
	<!-- 添加工作账号 over  -->
	<!-- 修改工作账号  -->
	<div class="" id="alertAccountDialog" title="修改工作账号">
		<div class="box">
			<form id="alertAccountForm" action="/bossUser/alterBossUserManager"
				method="post">
				<ul class="form-1">
					<li><label><i class="red">*</i> 登录用员名：</label>
						<p>
							<span id="alertuserCode"></span>
						</p> <input type="hidden" name="userCode" id="hiddenuserCode" /></li>
					<li><label><i class="red">*</i> 姓 名：</label>
						<p>
							<input class="text text-1 register-text" value="" datatype="*2-6"
								nullmsg="请输入中文姓名！" errormsg="请填写2-6个中文姓名！" id="alertuserName"
								name="userName" type="text" />
						</p>
						<p>
							<span class="Validform_checktip"></span>
						</P></li>
					<li><label><i class="red">*</i> 手 机：</label>
						<p>
							<input class="text text-1 register-text" value="" datatype="mo"
								nullmsg="请输入手机号码！" errormsg="请输入11位数字！" id="alertmobile"
								name="mobile" type="text" />
						</p>
						<p>
							<span class="Validform_checktip"></span>
						</P></li>
					<li><label><i class="red">*</i> 账号组织：</label>
						<p>
							<input class="text text-1 register-text" value="" datatype="*"
								nullmsg="请选择组织！" type="text" id="alertOrgName"
								onclick="showMenu2();" />
						</p>
						<p>
							<span class="Validform_checktip"></span>
						</P> <input type="hidden" id="alertOrgId" name="orgId" value="" /></li>
					<li><label>选择角色：</label>
						<p>
							<input class="text text-1" value="" type="text"
								id="alertRoleName" onclick="showAlertRoleMenu();" />
						</p> <input type="hidden" id="alertRoleId" name="roleId" value="" /></li>
				</ul>
			</form>
		</div>
	</div>
	<!-- 修改工作账号 over  -->
	<!-- 弹层 over -->
	<script>
/* 条件查询组织树配置 */
var setting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: onClick
		}
	};
function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("orgTree"),
	nodes = zTree.getSelectedNodes(),
	v = "";
	o = "";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		o += nodes[i].id + ",";
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	if (o.length > 0 ) o = o.substring(0, o.length-1);
	$("#orgName").val(v);
	$("#orgId").val(o);
}
function showMenu() {
	var cityObj = $("#orgName");
	var cityOffset = $("#orgName").offset();
	$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}
/* 添加弹出框组织树配置 */
var setting1 = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
/* function onClick1(e, treeId, treeNode) {
	alert("onClick2");
	var zTree = $.fn.zTree.getZTreeObj("addOrgTree"),
	nodes = zTree.getSelectedNodes(),
	v = "";
	o = "";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		o += nodes[i].id + ",";
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	if (o.length > 0 ) o = o.substring(0, o.length-1);
	$("#addOrgName").val(v);
	$("#addOrgId").val(o);
} */
function showMenu1() {
	$("#addMenuContent").dialog("open");
}
/* 修改弹出框组织树配置 */
var setting2 = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
/* function onClick2(e, treeId, treeNode) {
	alert("onClick2");
	var zTree = $.fn.zTree.getZTreeObj("alertOrgTree"),
	nodes = zTree.getSelectedNodes(),
	v = "";
	o = "";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		o += nodes[i].id + ",";
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	if (o.length > 0 ) o = o.substring(0, o.length-1);
	$("#alertOrgName").val(v);
	$("#alertOrgId").val(o);
} */
function showMenu2() {
	$("#alertMenuContent").dialog("open");
}
/* 条件查询角色树配置 */
var rolesetting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: onCheckRole
		}
	};
function showRoleMenu() {
	var cityObj = $("#roleName");
	var cityOffset = $("#roleName").offset();
	$("#roleMenu").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDownRole);
}
function onCheckRole(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("roleTree"),
	nodes = zTree.getSelectedNodes(),
	v = "";
	o = "";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		o += nodes[i].id + ",";
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	if (o.length > 0 ) o = o.substring(0, o.length-1);
	$("#roleName").val(v);
	$("#roleId").val(o);
}
function hideRoleMenu() {
	$("#roleMenu").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDownRole);
}
function onBodyDownRole(event) {
	if (!(event.target.id == "roleName" || event.target.id == "roleMenu" || $(event.target).parents("#roleMenu").length>0)) {
		hideRoleMenu();
	}
}
/* 新增弹出框角色树配置 */
var addRolesetting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		check: {
			enable: true
		},
		callback: {
			beforeClick: beforeClick1
		}
	};
function showAddRoleMenu() {
	$("#addRoleMenu").dialog("open");
}
function beforeClick1(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("addRoleTree");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}
/* 修改弹出框角色树配置 */
var alertRolesetting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		check: {
			enable: true
		},
		callback: {
			beforeClick: beforeClick2
		}
	};
function showAlertRoleMenu() {
	var alertRoleTreeLis = $('ul#alertRoleTree li');
	var alertRoleNames = $('#alertRoleName').val().split(',');	
	$.each(alertRoleNames,function(alertRoleNameIndex,alertRoleName){
		$.each(alertRoleTreeLis,function(alertRoleTreeLisIndex,alertRoleTreeLi){
			var alertRoleTreeSpanName = $(alertRoleTreeLi).find('a span:eq(1)').text();
			var alertRoleTreeSpanCheck = $(alertRoleTreeLi).find('span:eq(1)');
			if(alertRoleName == alertRoleTreeSpanName){
				if($(alertRoleTreeSpanCheck).hasClass('checkbox_false_full')){
					$(alertRoleTreeSpanCheck).click();
				}
			}else{
				var ok = jQuery.inArray(alertRoleTreeSpanName, alertRoleNames); 
				if(ok==-1){
					if($(alertRoleTreeSpanCheck).hasClass('checkbox_true_full')){
						$(alertRoleTreeSpanCheck).click();
					}
				}
			}
		});
	});
	$("#alertRoleMenu").dialog("open");
}
function beforeClick2(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("alertRoleTree");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

	/* 组织树zNodes(json格式) */
	var zNodes;
	$.ajax({
		async : false,
		cache : false,
		type : "POST",
		dataType : "json",
		url : "/organization/getOrganizationTree",// 机构树getOrganizationTree
		success : function(data) { // 请求成功后处理函数。
			zNodes = data; // 把后台封装好的简单Json格式赋给zNodes
		}
	}); 
	/* 角色树zNodes(json格式) */	
	var rzNodes;
	$.ajax({
		async : false,
		cache : false,
		type : "POST",
		dataType : "json",
		url : "/organization/getRoleTree",// 角色树getRoleTree
		success : function(data) { // 请求成功后处理函数。
			rzNodes = data; // 把后台封装好的简单Json格式赋给zNodes
		}
	});
	
    $(function(){
    	/* 初始化树 */
    	$.fn.zTree.init($("#orgTree"), setting, zNodes);
    	$.fn.zTree.init($("#addOrgTree"), setting1, zNodes);
    	$.fn.zTree.init($("#alertOrgTree"), setting2, zNodes);
    	$.fn.zTree.init($("#roleTree"), rolesetting, rzNodes);
    	$.fn.zTree.init($("#addRoleTree"), addRolesetting, rzNodes);
    	$.fn.zTree.init($("#alertRoleTree"), alertRolesetting, rzNodes);
    	/* 初始化validform验证 */
    	var addform= $("#addAccountForm").Validform({
    		btnSubmit:"#addAccountSubmit",
    		btnReset:"#addAccountReset",
    		tiptype:4,
    		showAllError:true,
    		ajaxPost:true,
    		datatype:{
    			"nameValid":/^[\u4E00-\u9FA5\uf900-\ufa2da-zA-Z0-9]{2,25}$/
    		},
    		callback:function(data){
    			if(data.status=="yes"){
    				$("#addAccountDialog").dialog("close");
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
			                        $("#conditionForm").submit();
			                    }
			                }
			            }]
			        });
    			}else if(data.status=="no"){
        			bghui();
        			Alert({str:"添加失败,"+data.msg});
        		}else{
        			bghui();
        			Alert({str:"添加失败"});
        		}
    		}
    	});
    	var alertform= $("#alertAccountForm").Validform({
    		btnSubmit:"#addAccountSubmit",
    		btnReset:"#addAccountReset",
    		tiptype:4,
    		showAllError:true,
    		ajaxPost:true,
    		callback:function(data){
    			if(data.status=="yes"){
    				$("#alertAccountDialog").dialog("close");
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
			                        $("#conditionForm").submit();
			                    }
			                }
			            }]
			        });
    			}
    		}
    	});
        //tips
        $('.operate-1').hover(
                function(){
                    $(this).children('.tips').show();
                },
                function(){
                    $(this).children('.tips').hide();
                }
        );
        $("#addAccountDialog").dialog({
        	autoOpen : false,
			modal : true,
			width : 794,
			resizable : false,
			buttons :[ 
			    {
			    	text:"确定",
			    	id: "addAccountSubmit",
			    	click: function(){
			    		$("#addAccountForm").submit();
			    	}
			    },
			    {
			    	text:"重置",
			    	id: "addAccountReset",
			    	click: function(){
			    		addform.resetForm();
			    		$("#addOrgId").val("");
			    		$("#addRoleId").val("");
			    	}
			    }
			],
			close: function(event, ui) { 
				addform.resetForm();
				$("#addOrgId").val("");
	    		$("#addRoleId").val("");
			}
        });
        $("#alertAccountDialog").dialog({
        	autoOpen : false,
			modal : true,
			width : 794,
			resizable : false,
        	buttons :[ 
  			    {
  			    	text:"确定",
  			    	id: "alertAccountSubmit",
  			    	click: function(){
  			    		$("#alertAccountForm").submit();
  			    	}
  			    },
  			    {
  			    	text:"取消",
  			    	id: "alertAccountReset",
  			    	click: function(){
  			    		$("#alertAccountDialog").dialog("close");
  			    		alertform.resetForm();
  			    		$("#alertOrgId").val("");
			    		$("#alertRoleId").val("");
  			    	}
  			    }
  			],
  			close: function(event, ui) { 
  				alertform.resetForm();
  				$("#alertOrgId").val("");
	    		$("#alertRoleId").val("");
  			}
        });
        $('.btn-add').on('click',function(){
            $('#addAccountDialog').dialog("open");
        });
        $("#subCForm").click(function(){
        	if($("#orgName").val()==""){
        		$("#orgId").val("");
        	}
        	if($("#roleName").val()==""){
        		$("#roleId").val("");
        	}
            $("#conditionForm").submit();
        });
        
        $("#addMenuContent").dialog({
        	autoOpen : false,
			modal : true,
			resizable : false,
			buttons : {
				确定 : function() {
					var zTree = $.fn.zTree.getZTreeObj("addOrgTree"),
					nodes = zTree.getSelectedNodes(),
					v = "";
					o = "";
					nodes.sort(function compare(a,b){return a.id-b.id;});
					for (var i=0, l=nodes.length; i<l; i++) {
						v += nodes[i].name + ",";
						o += nodes[i].id + ",";
					}
					if (v.length > 0 ) v = v.substring(0, v.length-1);
					if (o.length > 0 ) o = o.substring(0, o.length-1);
					$("#addOrgName").val(v);
					$("#addOrgId").val(o);
					$("#addMenuContent").dialog("close");
				},
				取消 : function() {
					$("#addMenuContent").dialog("close");
				}
			}
        });
        $("#alertMenuContent").dialog({
        	autoOpen : false,
			modal : true,
			resizable : false,
			buttons : {
				确定 : function() {
					var zTree = $.fn.zTree.getZTreeObj("alertOrgTree"),
					nodes = zTree.getSelectedNodes(),
					v = "";
					o = "";
					nodes.sort(function compare(a,b){return a.id-b.id;});
					for (var i=0, l=nodes.length; i<l; i++) {
						v += nodes[i].name + ",";
						o += nodes[i].id + ",";
					}
					if (v.length > 0 ) v = v.substring(0, v.length-1);
					if (o.length > 0 ) o = o.substring(0, o.length-1);
					$("#alertOrgName").val(v);
					$("#alertOrgId").val(o);
					$("#alertMenuContent").dialog("close");
				},
				取消 : function() {
					$("#alertMenuContent").dialog("close");
				}
			}
        });
        $("#addRoleMenu").dialog({
        	autoOpen : false,
			modal : true,
			resizable : false,
			buttons : {
				确定 : function() {
					var zTree = $.fn.zTree.getZTreeObj("addRoleTree"),
					nodes = zTree.getCheckedNodes(true),
					v = "";
					o = "";
					nodes.sort(function compare(a,b){return a.id-b.id;});
					for (var i=0, l=nodes.length; i<l; i++) {
						v += nodes[i].name + ",";
						o += nodes[i].id + ",";
					}
					if (v.length > 0 ) v = v.substring(0, v.length-1);
					if (o.length > 0 ) o = o.substring(0, o.length-1);
					$("#addRoleName").val(v);
					$("#addRoleId").val(o);
					$("#addRoleMenu").dialog("close");
				},
				取消 : function() {
					$("#addRoleMenu").dialog("close");
				}
			}
        });
        $("#alertRoleMenu").dialog({
        	autoOpen : false,
			modal : true,
			resizable : false,
			buttons : {
				确定 : function() {
					var zTree = $.fn.zTree.getZTreeObj("alertRoleTree"),
					nodes = zTree.getCheckedNodes(true),
					v = "";
					o = "";
					nodes.sort(function compare(a,b){return a.id-b.id;});
					for (var i=0, l=nodes.length; i<l; i++) {
						v += nodes[i].name + ",";
						o += nodes[i].id + ",";
					}
					if (v.length > 0 ) v = v.substring(0, v.length-1);
					if (o.length > 0 ) o = o.substring(0, o.length-1);
					$("#alertRoleName").val(v);
					$("#alertRoleId").val(o);
					$("#alertRoleMenu").dialog("close");
				},
				取消 : function() {
					$("#alertRoleMenu").dialog("close");
				}
			}
        });
    });
    function showAlertDialog(userCode){
    	$.post("/bossUser/findBossUserByCondition",{
    		"userCode":userCode
    	},function(data){
    		$("#alertuserCode").html(data.userCode);
    		$("#hiddenuserCode").val(data.userCode);
    		$("#alertuserName").val(data.userName);
    		$("#alertmobile").val(data.mobile);
    		$("#alertOrgName").val(data.orgName);
    		$("#alertOrgId").val(data.orgId);
    		$("#alertRoleId").val(data.roleId);
    		$("#alertRoleName").val(data.roleName);
    	});
        $('#alertAccountDialog').dialog("open");
    }
    function islock(userCode,status){
    	if(status==0){
    		bghui();
	        Alert({
	            str:'确定要锁定该会员吗？',
	            buttons:[{
	                name:'确定',
	                id:'1',
	                classname:'btn-style',
	                ev:{click:function(data){
	                        disappear();
	                        $(".bghui").remove();
	                        $.post("/bossUser/changeStatus",{
	                    		"userCode":userCode,
	                    		"status":status
	                    	},function(data){
	                    		if(data=="yes"){
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
	                		                        $("#conditionForm").submit();
	                		                    }
	                		                }
	                		            }]
	                		        });
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
    	}else if(status==3){
    		bghui();
	        Alert({
	            str:'该会员为锁定状态，确定要开通吗？',
	            buttons:[{
	                name:'确定',
	                id:'1',
	                classname:'btn-style',
	                ev:{click:function(data){
	                        disappear();
	                        $(".bghui").remove();
	                        $.post("/bossUser/changeStatus",{
	                    		"userCode":userCode,
	                    		"status":status
	                    	},function(data){
	                    		if(data=="yes"){
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
	                		                        $("#conditionForm").submit();
	                		                    }
	                		                }
	                		            }]
	                		        });
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
    	
    }
    function secretReset(userId){
    	bghui();
        Alert({
            str:'确认重置密码？',
            buttons:[{
                name:'确定',
                id:'1',
                classname:'btn-style',
                ev:{click:function(data){
                        disappear();
                        $(".bghui").remove();
                        $.post("/bossUser/resetBossUserPassWord",{
                    		"userId":userId
                    	},function(data){
                    		if(data=="yes"){
                		        bghui();
                		        Alert({
                		            str:'重置成功！',
                		            buttons:[{
                		                name:'确定',
                		                id:'1',
                		                classname:'btn-style',
                		                ev:{click:function(data){
                		                        disappear();
                		                        $(".bghui").remove();
                		                        $("#conditionForm").submit();
                		                    }
                		                }
                		            }]
                		        });
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
    
    function clearForm(id){
    	var formObj = document.getElementById(id);
    	if(formObj == undefined){
    		return;
    	}
    	for(var i=0; i<formObj.elements.length; i++){
	    	if(formObj.elements[i].type == "text"){
	    		formObj.elements[i].value = "";
	    	}
	    	else if(formObj.elements[i].type == "password"){
	    		formObj.elements[i].value = "";
	    	}
	    	else if(formObj.elements[i].type == "radio"){
	    		formObj.elements[i].checked = false;
	    	}
	    	else if(formObj.elements[i].type == "checkbox"){
	    		formObj.elements[i].checked = false;
	    	}
	    	else if(formObj.elements[i].type == "select-one"){
	    		formObj.elements[i].options[0].selected = true;
	    	}
	    	else if(formObj.elements[i].type == "select-multiple"){
		    	for(var j = 0; j < formObj.elements[i].options.length; j++){
		    		formObj.elements[i].options[j].selected = false;
		    	}
	    	}
	    	else if(formObj.elements[i].type == "file"){
		    	//formObj.elements[i].select();
		    	//document.selection.clear();
		    	// for IE, Opera, Safari, Chrome
		    	var file = formObj.elements[i];
		    	if(file.outerHTML){
		    		file.outerHTML = file.outerHTML;
		    	}else{
		    		file.value = ""; // FF(包括3.5)
		    	}
	    	}
	    	else if(formObj.elements[i].type == "textarea"){
	    		formObj.elements[i].value = "";
	    	}
	    }
    } 
    /* function fromData(){
    	var o=$('#addAccountForm').serializeArray();
        var addData={};
        for(var i=0;i<o.length;i++) {
            if(o[i].value!=null&&o[i].value!=""){
            	alert("name:"+o[i].name+"   value:"+o[i].value)
            	addData[o[i].name]=$.trim(o[i].value);
            }
        }
    } */
</script>
</body>
</html>