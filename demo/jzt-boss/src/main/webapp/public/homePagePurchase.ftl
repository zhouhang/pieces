<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <title>中药材电子商务管理系统</title>
    
   	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css"/>
	
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
</head>
<body>
<#include "home/top.ftl" />  
<#import 'macro.ftl' as tools>
<#include "home/left.ftl" /> 
<div class="wapper">
<!-- pageCenter start -->
<div class="main">
	<div class="page-main">
		<h1 class="title1">首页采购信息</h1>
		<form action="/homePagePurchase" method="get" id="homePagePurchaseForm">
			<ul class="store-search ac-search">
				<li><span>采购编号：</span>
					<input name="purchaseId" value="${page.params.purchaseDto.purchaseId!''}" class="text-store text-4 mr10" type="text" /> 
					<span>类型：</span>
					<select class="text-store text-7"name ="type">
						<option value="">--请选择--</option>
						<#list types?keys as key>
							<option value="${key}" <#if page.params.purchaseDto.type == key>selected</#if>>${types[key]}</option>
						</#list>
					</select>
					<input type="submit" id="searchForm" class="btn-blue mr10" value="查询" />
					<input type="button"  class="btn-hui" value="清空" /></li>
			</ul>
		</form>
		
		<div class="use-item1 mt25">
			 <@shiro.hasPermission name="首页采购信息-添加首页采购信息">
				<span class="btn-add mb10">
					<a href="#">添加</a>
				</span>
			 </@shiro.hasPermission>
			<table id="busiListingTable" class="table-store" width="100%"
				cellpadding="1" cellspacing="1">
				<tr>
					<th width="100">采购编号</th>
					<th width="100">采购用户</th>
					<th width="100">类型</th>
					<th width="100">排序</th>
					<th width="150">操作</th>
				</tr>
				<#if (page.results?size>0)> 
					<#list page.results as purchase>
					<tr id="">
						<td>${purchase.purchaseId!''}</td> 
						<td><a href="/getMemberManage/getMemberByUserName?memberName=${purchase.purchaser!''}">${purchase.purchaser!'' }</a></td>
						<td>${types[purchase.type?string]!''}</td>
						<td>${purchase.sortNo!''}</td>
						<td class="opr-btn">
						 <@shiro.hasPermission name="首页采购信息-查看首页采购信息">
						 	<span class="operate-2"	onclick="javascript:showPurchaseInfo(${purchase.homePageId!0});"></span>
				 		 </@shiro.hasPermission>
				 		 <@shiro.hasPermission name="首页采购信息-删除首页采购信息">
				 		 	<span class="operate-4" onclick="javascript:delPurchaseInfo(${purchase.homePageId!0});"></span>
				 		 </@shiro.hasPermission>
						</td>
					</tr>
					</#list> 
				<#else>
				<tr>
					<td colspan="10">没有数据!</td>
				</tr>
				</#if>
			</table>
		</div>
		<@tools.pages page=page form="homePagePurchaseForm"/>
	</div>
</div>
<!-- pageCenter over -->
</div>
<!-- 添加采收信息 弹层 Start -->
<div id="addPurchaseInfoDialog" class="main" style="display:none;">
	 <form id="addPurchaseInfoForm" action="/homePagePurchase/addPurchase" method="post">
        <ul class="form-1">
        		<li><label><i class="red">*</i> 采购编号：</label>
					<p>
						<input class="text-store text-1 register-text"
							datatype="*1-50" ajaxurl="/homePagePurchase/checkPurchaseId"
							nullmsg="请输入采购单号！" type="text"
							id="" name="purchaseId" value="" />
					</p>
				</li>
				
				<li><label><i class="red">*</i> 类型：</label>
					<p>
						<select class="text-store text text-1 register-text" datatype="*" nullmsg="请选择类型！" errormsg="" id="" name="type">
							<option value="">--请选择--</option> 
							<#list types?keys as key>
								<option value="${key}">${types[key]!''}</option> 
							</#list>
						</select>
					</p>
				</li>
				<li><label><i class="red">*</i> 排序：</label>
					<p>
						<input class="text-store text-1 register-text" value="" id="addortno" datatype="n1-2"
							nullmsg="排序不能为空" errormsg="排序输入1-2位数字" name="sortNo" type="text" />
					</p>
				</li>

               <li class="clearfix" style="text-align:center;width:100%;margin-top:15px;margin-bottom:25px">
	            	<span class="inp_width1">&nbsp;</span><input type="submit" class="btn-blue" value="提交" />
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" class="btn-hui" value="重置" />
               </li>
        </ul>
	</form>
</div>
<!-- 添加采收信息 弹层 Start -->

<!-- 修改采购信息弹层  -->
<div id="showPurchaseInfoDialog" class="main" style="display:none;">
	 <form id="showPurchaseInfoForm" action="/homePagePurchase/updatePurchase" method="post">
        <ul class="form-1">
        		<!-- 主键id -->
       			
        		<li><label><i class="red">*</i> 采购编号：</label>
					<p>
						<input type="hidden" id="showHomePageId" name="homePageId" value=""/>
						<input type="hidden" id="purchaseId" name="purchaseId" value=""/>
						<span id="showPurchaseId"></span>
					</p>
				</li>
				
				<li><label><i class="red">*</i> 类型：</label>
					<p>
						<select class="text-store text text-1 register-text" datatype="*" nullmsg="请选择类型！" errormsg="" id="showType" name="type">
							<option value="">--请选择--</option> 
							<#list types?keys as key>
								<option value="${key}">${types[key]!''}</option> 
							</#list>
						</select>
					</p>
				</li>
				<li><label><i class="red">*</i> 排序：</label>
					<p>
						<input class="text-store text-1 register-text" value="" id="showSortNo" datatype="n1-2"
							nullmsg="排序不能为空" errormsg="排序输入1-2位数字" name="sortNo" type="text" />
					</p>
				</li>

               <li class="clearfix" style="text-align:center;width:100%;margin-top:15px;margin-bottom:25px">
	            	<span class="inp_width1">&nbsp;</span><input type="submit" class="btn-blue" value="提交" />
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  type="reset" class="btn-hui" value="重置" />
               </li>
        </ul>
	</form>
</div>
<!-- 修改采购信息弹层 -->
<script>
//////////////////////////////////// 添加弹层设置 ////////////////////////////////////
$('.btn-add a').click(function(){
	//resetDialogForm();
	$("#addPurchaseInfoDialog").dialog({
	 	 title:'添加首页采购数据',
	 	 width : '794',
	     modal: true,
	     autoOpen: true
	});
});

/////////////////////////////////// 添加采购信息表单验证  ///////////////////////////////////
    var addPurchaseInfoForm = $("#addPurchaseInfoForm").Validform({
    	    tiptype:4,
    	    showAllError:true,
    	    ajaxPost:true,
    	    callback:function(data){
    	    	var ok = data.ok;
    	    	var msg = data.msg;
    	    	if(ok){
    	    		$("#addPurchaseInfoDialog").dialog("close");
    	    		tipsfun(msg,function(){
    	    			window.location.reload(true);
    	    		});
    	    	}else{
    	    		if(msg!=undefined){
    	    			tips(msg);
    	    		}else{
    	    			tips('网络繁忙，请稍后再试！');
    	    		}
    	    	}
    	    }
    	});
/////////////////////////////////// 添加采购信息表单验证  ///////////////////////////////////


/////////////////////////////////// 查看修改采购信息表单验证  ///////////////////////////////////
function showPurchaseInfo(id){
	$.ajax({
		async : false,
		cache : false,
		type : "GET",
		data : {"id":id},
		dataType : "json",
		url : "/homePagePurchase/getPurchaseInfo",
		success : function(data){
			var ok = data.ok;
			var msg = data.msg;
			if(ok){
				var obj = data.obj;
				$("#showHomePageId").val(obj.homePageId);
				$("input[id='purchaseId']").val(obj.purchaseId);
				$("#showPurchaseId").text(obj.purchaseId);
				$("#showType").val(obj.type);
				$("#showSortNo").val(obj.sortNo);
				
				//加载dialog弹层
				$("#showPurchaseInfoDialog").dialog({
				 	 title:'修改首页采购信息',
				 	 width : '50%',
				     modal: true,
				     autoOpen: true
				 });	
			}else{
				tips(msg);
			}
		}
	});
}
/////////////////////////////////// 查看修改采购信息表单验证  ///////////////////////////////////


/////////////////////////////////// 修改采购信息表单验证  ///////////////////////////////////
    var showPurchaseInfoForm = $("#showPurchaseInfoForm").Validform({
    	    tiptype:4,
    	    showAllError:true,
    	    ajaxPost:true,
    	    callback:function(data){
    	    	var ok = data.ok;
    	    	var msg = data.msg;
    	    	if(ok){
    	    		$("#showPurchaseInfoDialog").dialog("close");
    	    		tipsfun(msg,function(){
    	    			window.location.reload(true);
    	    		});
    	    	}else{
    	    		if(msg!=undefined){
    	    			tips(msg);
    	    		}else{
    	    			tips('网络繁忙，请稍后再试！');
    	    		}
    	    	}
    	    }
    	});
/////////////////////////////////// 修改采购信息表单验证  ///////////////////////////////////

/////////////////////////////////// 删除采购信息表单验证  ///////////////////////////////////

function delPurchaseInfo(id){
	var str = '确定删除吗？';
	bghui();
	Alert({
            str: str,
            buttons:[{
                name:'确定',
                classname:'btn-blue',
                ev:{click:function(data){
                	 disappear();
                     $(".bghui").remove();
                     $.ajax({
                 		async : false,
                 		cache : false,
                 		type : "GET",
                 		data : {"id":id},
                 		dataType : "json",
                 		url : "/homePagePurchase/delPurchaseInfo",
                 		success : function(data){
                 			var ok = data.ok;
                 			var msg = data.msg;
                 			if(ok){
                 				tipsfun(msg,function(){
                 	    			window.location.reload(true);
                 	    		});
                 			}else{
                 				tips(msg);
                 			}
                 		}
                 	});
                 }
               }
            }]
    });
}
/////////////////////////////////// 删除采购信息表单验证  ///////////////////////////////////


/////////////////////////////////// tips信息提示  ///////////////////////////////////
	//tips
	var Height = $('.tips').height() + 18;
	$('.opr-btn .tips').css('top', -Height);
	$('.operate-1').hover(function() {
		$(this).children('.tips').show();
	}, function() {
		$(this).children('.tips').hide();
	});
	
	 $('#Close').click (function(){
        $(this).parents('#picBox').hide();
        $('.bghui').remove();
    });
	 
	function tips(str) {
		bghui();
		Alert({
			str : str,
			buttons : [ {
				name : '确定',
				classname : 'btn-blue',
				ev : {
					click : function(data) {
						disappear();
						$(".bghui").remove();
					}
				}
			} ]
		});
	};
	
	//返回提交表单结果 刷新页面
	//tips 提示信息
	//isSubmit 提交form返回结果
	//form 表单id
	function tips(tips,isSubmit,form){
		bghui();
		Alert({
	            str: tips,
	            buttons:[{
	                name:'确定',
	                classname:'btn-blue',
	                ev:{click:function(data){
	                	 disappear();
	                     $(".bghui").remove();
	                     if(isSubmit == true){
	                          $("'#"+form+"'").submit();
	                     }
	                 }
	               }
	            }]
	    });
	};

	// 弹框，带刷新页面功能
	function tipsfun(str,fun){
		bghui();
		Alert({
	            str: str,
	            buttons:[{
	                name:'确定',
	                classname:'btn-blue',
	                ev:{click:function(data){
	                	 disappear();
	                     $(".bghui").remove();
	                     fun();
	                 }
	               }
	            }]
	    });
	};
/////////////////////////////////// tips信息提示  ///////////////////////////////////
</script>
</body>
</html>