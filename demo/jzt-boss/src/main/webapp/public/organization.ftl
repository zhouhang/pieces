<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>中药材电子商务管理系统</title>
	<#include "home/common.ftl">
	<style>
		.pop-position{
			width:250px;
			margin-left:-125px;
		}
	</style>
</head>
<body>
<#include "home/top.ftl" />  
<div class="wapper">
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">组织管理</h1>
            <div class="system_box1 fl">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
            <div class="system_box2 fr">
                <div class="head-title">
                	<#if buserlist??>
                		<#if status==0> 
                    		<span class="fr">当前<span class="red">${orgName!'' }</span>组织中共 <span class="red">${(buserlist?size)!'' }</span> 个账号</span>
                    	</#if>
                		<#if status==1> 
                    		<span class="fr">当前<span class="red">${orgName!'' }</span>组织与下级组织中共 <span class="red">${(buserlist?size)!'' }</span> 个账号</span>
                    	</#if>
                    </#if>
                	<#if !buserlist??>
                    	<span class="fr">当前共 <span class="red">0</span> 个账号</span>
                    </#if>
                    <select id="orgSelect" class="text">
                    	<#if !status??>
 	                       <option value="0">本组织账号（账号属于本组织）</option>
 	                       <option value="1">本组织与下级组织所有账号</option>
 	                    <#elseif status==0> 
 	                       <option value="0" selected="selected">本组织账号（账号属于本组织）</option>
 	                       <option value="1">本组织与下级组织所有账号</option>
                    	<#elseif status==1>
 	                       <option value="0">本组织账号（账号属于本组织）</option>
                      	   <option value="1" selected="selected">本组织与下级组织所有账号</option>
                    	</#if>
                    </select>
                </div>
                <table class="table-1" style="width:100%; *width:96%;" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="20%">登录用户名</th>
                        <th width="20%">姓名</th>
                        <th width="30%">手机号码</th>
                        <th width="30%">添加时间</th>
                    </tr>
                    <#if !buserlist??>
                    	<tr>
                    		<td colspan="4">没有数据!</td>
                    	</tr>
                    </#if>
                    <#if buserlist??>
	                	<#list buserlist as bossuser>
	                		<tr>
		                        <td>${bossuser.userCode!'' }</td>
		                        <td>${bossuser.userName!'' }</td>
		                        <td>${bossuser.mobile!'' }</td>
		                        <td>${bossuser.createTime?string("yyyy-MM-dd HH:mm:ss")!'' }</td>
	                    	</tr>
	                	</#list>
                	</#if>
                </table>
            </div>
        </div>
    </div>
<!-- pageCenter over -->
</div>
<!-- 右菜单 -->
<div id="rMenu">
    <ul>
    	<@shiro.hasPermission name="组织管理-新增组织">
        	<li id="m_add" onclick="addTreeNode();"><i class="icon-4"></i>新增组织</li>
        </@shiro.hasPermission>
    	<@shiro.hasPermission name="组织管理-修改组织">
        	<li id="m_del" onclick="alterTreeNode();"><i class="icon-5"></i>修改组织</li>
        </@shiro.hasPermission>
    	<@shiro.hasPermission name="组织管理-删除组织">
        	<li id="m_check" onclick="removeTreeNode();"><i class="icon-6"></i>删除组织</li>
        </@shiro.hasPermission>
    </ul>
</div>
<!-- 右菜单 over -->
<!-- 弹层 -->
  <!-- 新增组织 -->
<div class="" id="addSystemDialog" title="新增组织">
     <div class="box">
        <form id="addSystemForm" action="/organization/addOrganization" method="post">
            <ul class="form-1">
              <li>
                  <label>上级组织：</label>
                  <p><span id="addporgName"></span></p>
              </li>
              <li>
                  <label>组织名称：</label>
                  <p><input class="text text-1 register-text" datatype="*1-20" ajaxurl="/organization/validateOrgName" nullmsg="请输入组织名！" errormsg="组织名称在20个字以内！" id="addorgName" name="orgName" type="text" /></p>
                  <p></p>
              </li>
              <li>
              	  <input type="hidden" id="addorgId" name="orgId"/>
              </li>
	          </ul>
	      </form>
      </div>
</div>
  <!-- 新增组织 over  -->
  <!-- 修改组织  -->
<div class="" id="alertSystemDialog" title="修改组织">
    <div class="box">
        <form id="alertSystemForm" action="/organization/updateOrganization" method="post">
            <ul class="form-1">
                <li>
                    <label>上级组织：</label>
                    <p><span id="alterporgName"></span></p>
                </li>
                <li>
                    <label>组织名称：</label>
                    <p><input class="text text-1 register-text" datatype="*1-20" ajaxurl="/organization/validateOrgName" nullmsg="请输入组织名！" errormsg="组织名称在20个字以内！" type="text" id="alterorgName" name="orgName" value="" /></p>
                    <p></p>
                </li>
                <li class="mt25">
                    <input type="hidden" id="alertorgId" name="orgId"/>
                </li>
            </ul>
        </form>
    </div>
</div>
  <!-- 修改组织 over  -->
  <!-- 删除提示框  -->
<div class="pop-position" id="deSystem" style="display: none;">
    <div class="close" id="closedeleteWin">关闭</div>
    <div class="box">
          <form>
            <ul class="form-1">
                <li class="f14">
                    <p align='center'>您确定要删除此组织吗?</p>
                </li>
                <li>
                    <p style="*padding-top:10px;" align="center"><input type="button" class="btn-blue" id="deleteOrgBtn" value="确定" /> <input type="button" class="btn-hui" id="cancleOrgBtn" value="取消" /></p>
          			 
                </li>
                
                <input type="hidden" id="deleteorgId"/>
                
            </ul>
        </form>
    </div>
</div>
  <!-- 删除提示框 over  -->
  <!-- 信息提示框  -->
<div class="pop-position" id="showMsg" style="display: none;width: 400px;">
    <div class="close" id="closedeleteWin">关闭</div>
    <div class="box">
        <form>
           <ul class="form-1">
               <li class="f14">
                   <p align='center'>该组织下有账号或有子组织，不能进行删除!</p>
               </li>
               <li>
                   <p><input type="button" class="btn-blue" id="showMsgBtn" value="确定" /></p>
               </li>
           </ul>
        </form>
    </div>
</div>
  <!-- 信息提示框 over  -->
<!-- 弹层 over -->
<script type="text/javascript">
    var setting = {
        view: {
            dblClickExpand: false
        },
        data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: 0
			}
		},	
        callback: {
            onRightClick: OnRightClick,
            onClick: zTreeOnClick
        }
    };

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
    
	function zTreeOnClick(event, treeId, treeNode){
		var status = $('#orgSelect option:selected') .val();
		window.location.replace("/organization/findBossUsersByOrgId?orgId="+treeNode.id+"&status="+status);
	}
	
    function OnRightClick(event, treeId, treeNode) {
        if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
            zTree.cancelSelectedNode();
            showRMenu("root", event.clientX, event.clientY);
        } else if (treeNode && !treeNode.noR) {
            zTree.selectNode(treeNode);
            showRMenu("node", event.clientX, event.clientY);
        }
    }

    function showRMenu(type, x, y) {
        $("#rMenu ul").show();
        if (type=="root") {
            $("#m_del").hide();
            $("#m_check").hide();
            $("#m_unCheck").hide();
        } else {
            $("#m_del").show();
            $("#m_check").show();
            $("#m_unCheck").show();
        }
        rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

        $("body").bind("mousedown", onBodyMouseDown);
    }
    function hideRMenu() {
        if (rMenu) rMenu.css({"visibility": "hidden"});
        $("body").unbind("mousedown", onBodyMouseDown);
    }
    function onBodyMouseDown(event){
        if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
            rMenu.css({"visibility" : "hidden"});
        }
    }
    var addCount = 1;
    function addTreeNode() {
        hideRMenu();
        var id = zTree.getSelectedNodes()[0].id
        $("#addorgId").val(id);
        $.post("/organization/getOrgNameById",{
        	"id":id
        },function(date){
        	$("#addporgName").html(date[0]);
        });
        $("#addSystemDialog").dialog("open");
		
    }
    function removeTreeNode() {
        hideRMenu();
        var id = zTree.getSelectedNodes()[0].id;
        $("#deleteorgId").val(id);
        var html = '<div class="bghui"></div>';
        $('body').append(html);
		$("#deSystem").show(); 
		
    }
    function alterTreeNode() {
        hideRMenu();
        var id = zTree.getSelectedNodes()[0].id
        $("#alertorgId").val(id);
        $.post("/organization/getOrgNameById",{
        	"id":id
        },function(date){
        	$("#alterporgName").html(date[0]);
        	$("#alterorgName").val(date[1]);
        });
        $("#alertSystemDialog").dialog("open");
    }
    function resetTree() {
        hideRMenu();
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    }

    var zTree, rMenu;
    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        zTree = $.fn.zTree.getZTreeObj("treeDemo");
        rMenu = $("#rMenu");
        var addSystemForm= $("#addSystemForm").Validform({
    		btnSubmit:"#addSystemSubmit",
    		btnReset:"#addSystemReset",
    		tiptype:4,
    		showAllError:true,
    		ajaxPost:true,
			callback:function(data){
				if(data.status=="yes"){
					$("#addSystemDialog").dialog("close");
			        bghui();
			        Alert({
			            str:'添加成功！',
			            buttons:[{
			                name:'确定',
			                id:'1',
			                ev:{click:function(data){
			                        disappear();
			                        $(".bghui").remove();
							        window.location.replace("/organization");
			                    }
			                }
			            }]
			        });
				}
			}
    	});
    	var alertSystemform= $("#alertSystemForm").Validform({
    		btnSubmit:"#alertSystemSubmit",
    		btnReset:"#alertSystemReset",
    		tiptype:4,
    		showAllError:true,
    		ajaxPost:true,
			callback:function(data){
				if(data.status=="yes"){
					$("#alertSystemDialog").dialog("close");
			        bghui();
			        Alert({
			            str:'修改成功！',
			            buttons:[{
			                name:'确定',
			                id:'1',
			                ev:{click:function(data){
			                        disappear();
			                        $(".bghui").remove();
							        window.location.replace("/organization");
			                    }
			                }
			            }]
			        });
				}
			}
    	});
        $("#addSystemDialog").dialog({
        	autoOpen : false,
			modal : true,
			width : 794,
			resizable : false,
        	buttons :[ 
  			    {
  			    	text:"确定",
  			    	id: "addSystemSubmit",
  			    	click: function(){
  			    		$("#addSystemForm").submit();
  			    	}
  			    },
  			    {
  			    	text:"重置",
  			    	id: "addSystemReset",
  			    	click: function(){
  			    		addSystemForm.resetForm();
  			    	}
  			    }
  			],
  			close: function(event, ui) { 
  				addSystemForm.resetForm();
  			}
        });
        $("#alertSystemDialog").dialog({
        	autoOpen : false,
			modal : true,
			width : 794,
			resizable : false,
        	buttons :[ 
  			    {
  			    	text:"确定",
  			    	id: "alertSystemSubmit",
  			    	click: function(){
  			    		$("#alertSystemForm").submit();
  			    	}
  			    },
  			    {
  			    	text:"重置",
  			    	id: "alertSystemReset",
  			    	click: function(){
  			    		alertSystemform.resetForm();
  			    	}
  			    }
  			],
  			close: function(event, ui) { 
  				alertSystemform.resetForm();
  			}
        });
        $("#closedeleteWin").click(function(){
        	$("#deSystem").hide();
        	$('.bghui').remove();
        });
        $("#cancleOrgBtn").click(function(){
        	$("#deSystem").hide();
        	$('.bghui').remove();
        });
        $("#showMsgBtn").click(function(){
        	$("#showMsg").hide();
        	$('.bghui').remove();
        });
        $(".close").click(function(){
        	this.hide();
        	$('.bghui').remove();
        });
        $("#deleteOrgBtn").click(function(){
        	var orgId = $("#deleteorgId").val();
        	$.post("/organization/validateOrgChildren",{
        		"orgId":orgId
      		},function(date){
        		if(date){
        			$.post("/organization/deleteOrganization",{
                		"orgId":orgId
              		},function(data){
              			if(data=="yes"){
              				$(".bghui").remove();
              				$("#deSystem").hide();
        			        bghui();
        			        Alert({
        			            str:'删除成功！',
        			            buttons:[{
        			                name:'确定',
        			                id:'1',
        			                ev:{click:function(data){
        			                        disappear();
        			                        $(".bghui").remove();
        							        window.location.replace("/organization");
        			                    }
        			                }
        			            }]
        			        });
        				}
                	});
        		}else{
        			$("#deSystem").hide();
        			$("#showMsg").show();
        		}
        	});
        });
    });
    //-->
</script>
</body>
</html>