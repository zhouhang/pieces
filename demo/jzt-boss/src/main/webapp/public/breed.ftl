<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>中药材电子商务管理系统</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/background.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS}/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
<link rel="stylesheet"
	href="${RESOURCE_CSS}/js/jzt-boss/ztree/zTreeStyle/zTreeStyle.css"
	type="text/css">
<link href="${RESOURCE_CSS}/js/Validform/css/style.css"
	type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS}/js/datetimepicker/jquery.datetimepicker.css" />

<script type="text/javascript"
	src="${RESOURCE_JS}/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript"
	src="${RESOURCE_JS}/js/jzt-boss/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="${RESOURCE_JS}/js/jzt-boss/ztree/jquery.ztree.excheck-3.5.js"></script>

<script type="text/javascript"
	src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript"
	src="${RESOURCE_JS}/js/datetimepicker/jquery.datetimepicker.js"></script>

<script src="${RESOURCE_JS}/js/jquery-ui/jquery.ui.widget.js"></script>
<script src="${RESOURCE_JS}/js/jquery-ui/jquery.ui.tabs.js"></script>
<script src="${RESOURCE_JS}/js/jquery.masterblaster.js"></script>
</head>
<body>
<#include "home/top.ftl" />  
<#import 'macro.ftl' as tools>
<div class="wapper">
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">品种管理</h1>
            <form action="/getBreed/queryBreed" method="post" id="queryBreedForm">
                <ul class="form-search">
                    <li><span>品种名称/别名：</span><input class="text text-4 mr10" name="name" id="name" type="text" value="${page.params.name}" />
                        <span>品种编码：</span><input class="text text-4 mr10"  name="code" id="code" type="text" value="${page.params.code}"/>
                        <span>产地：</span><input class="text text-4 mr10"  name="place" id="place" type="text" value="${page.params.place}"/>
						<@shiro.hasPermission name="品种管理-品种查询">
							<input id="btn-blue" class="btn-blue" type="submit" value="查询">
						</@shiro.hasPermission>

                    </li>
                </ul>
             </form>
            <div class="use-item1 mt25">
            	<@shiro.hasPermission name="品种管理-添加品种">
                	<span class="btn-add mb10"><a href="#">添加品种</a> </span>
                </@shiro.hasPermission>
                <table class="table-1" id="table-1-breed" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                    	<th width="100">品种ID</th>
                        <th width="100">品种名称</th>
                        <th width="100">品种编码</th>
                        <th width="100">别名</th>
                        <th width="100">规格等级</th>
                        <th width="100">数量单位</th>
                        <th width="100">产地</th>
                        <th width="150">操作</th>
                    </tr>
                 <#list page.results as breed>
                    <tr>
                        <td id="id${breed.breedId}">${breed.breedId}</td>
                        <td>${breed.breedName}</td>
                        <td>${breed.breedCode}</td>
                        
                        <td class="opr-btn">
						<span class="operate-1 operate-a">
						<#if breed.breedCname?length gt 5>
                        		${breed.breedCname?substring(0,5)}...
                        		<#else>
                        		${breed.breedCname}
                        </#if>
						<div class="tips tipa" align="left">
						<span class="sj"></span>${breed.breedCname}</div></span></td>
						
                        <td class="opr-btn">
						<span class="operate-1 operate-a">
						<#if breed.breedStandardLevel?length gt 5>
                        		${breed.breedStandardLevel?substring(0,5)}...
                        		<#else>
                        		${breed.breedStandardLevel}
                        </#if>
						<div class="tips tipa" align="left">
						<span class="sj"></span>${breed.breedStandardLevel}</div></span></td>
                        <td>${breed.breedCountUnit}</td>
                        
                        <td class="opr-btn">
						<span class="operate-1 operate-a">
						<#if breed.breedPlace?length gt 5>
                        		${breed.breedPlace?substring(0,5)}...
                        		<#else>
                        		${breed.breedPlace}
                        </#if>
						<div class="tips tipa" align="left">
						<span class="sj"></span>${breed.breedPlace}</div></span></td>
                        <td class="opr-btn">
                        	<@shiro.hasPermission name="品种管理-品种修改">
                            	<span class="operate-2"></span>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="品种管理-品种删除">
                            	<span class="operate-4"></span>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="品种管理-品种查看详情">
                            	<span class="operate-5"></span>
                            </@shiro.hasPermission>
                        </td>
                        
                    </tr>
                </#list>
                </table>
            </div>
           
            <@tools.pages page=page form="queryBreedForm"/>
        </div>
    </div>
<!-- pageCenter over -->
</div>
<!-- 弹层  -->

<!-- 修改品种  -->
<div class="pop-position" id="addBreed"  style="display: none;">
    <div class="close">关闭</div>
    <div class="box">
        <h1 class="title1" id="addBreedTitle">添加品种</h1>
        <form id="from-addBreed">
	        	<ul class="form-1">
	                <li id="p_breedName">
	                    <label>品种名称：</label>
		                    <input class="text text-1" type="text"  id="breedName" 
		                    ajaxurl="" nullmsg="请输入中药品名" datatype="zh1-6" errormsg="不可超过6个汉字"/>
	                    <input type="hidden" name="breedId" id="breedId">
	                </li>
	                <li id="p_breedCode">
	                    <label>品种编码：</label>
	                   	<input class="text text-1" type="text"  id="breedCode" 
	                   	ajaxurl="" nullmsg="请输入中药品名编码" datatype="da1-25" errormsg="数字或英文字母,不可为中文!"/>
	                </li>
	                <li>
	                    <label>常见数量单位：</label>
	                    <div id="select-bg"><span><select name="" id="selectDict"></select></span></div>
	                </li>
	            </ul>
         </form>
        </div>
        <div class="demo">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">常见别名</a></li>
					<li><a href="#tabs-2">常见规格等级</a></li>
			        <li><a href="#tabs-3">常见产地</a></li>
				</ul>
				<div id="tabs-1">
					<input id="tags1" placeholder="请输入"/>
				</div>
				<div id="tabs-2">
					<input id="tags2" placeholder="请输入"/>
				</div>
			    <div id="tabs-3">
					<input id="tags3" placeholder="请输入"/>
				</div>
			</div>
		</div>

        <div class="tabconta">
        	<h1>选择类目</h1>
				<table class="table-a" id="tableTree" width="100%" cellpadding="1" cellspacing="1">
				</table>
		<div class="clear"></div>
		<dl>
			<dt class="mt25">
				<center>
					<input type="button" class="btn-blue" id="btnAddBreed" value="添加" />
					<input type="button" class="btn-hui ml10" value="取消" />
				</center>
			</dt>
		</dl>
    </div>
   </div>
   </div>
<!--修改品种框结束-->



<!-- 品种详情  (开始)-->
<div class="pop-position" id="breedInfo"  style="display: none;">
    <div class="close">关闭</div>
    <div class="box">
        <h1 class="title1">品种详情</h1>
        <form >
	        <ul class="form-1">
	                <li>
	                    <label>品种名称：</label>
	                    <p id="p_breed_name"></p>
	                </li>
	                <li>
	                    <label>品种编码：</label>
	                    <p id="p_breed_code"></p>
	                </li>
	                <li>
	                    <label>常见数量单位：</label>
	                    <p class="bg" id="p_breedCountUnit"></p>
	                </li>
	                <li id="li_breed_cname">
	                    <label>常见别名：</label>
	                </li>
	                <li id="li_breed_level">
	                    <label>常见规格等级：</label>
	                </li>
	                <li id="li_breed_place">
	                    <label>常见产地：</label>
	                </li>
	         </ul>
          </form>
        </div>

        <div class="tabconta"><h1>选择类目</h1>
        <table class="table-a" id ="table_breedInfo" width="100%" cellpadding="1" cellspacing="1">
		</table>
        <div class="clear"></div>
	        <dl>
		        <dt class="mt25">
			        <center>
			        	<input type="button" class="btn-hui ml10" value="关闭" />
			        </center>
		        </dt>
	        </dl>
        </div>
  </div> 
<!--查看品种详情（结束）-->

<!-- 删除框  -->
<div class="de-position" id="deleteBreed" style="display: none;">
    <div class="close">关闭</div>
    <div class="box1"><h2 class="title2">提示</h2>
        <form>
            <ul class="form-s form-bk">
                <li>
                   <p>你确定要删除？</p>
                </li>
                <li class="mt25">
                    <label></label>
                    <p><input type="button" id='qrDelBreed' class="btn-blue" value="确定" />
                    <input type="button" id='cancelDel' class="btn-blue"  value="取消" /> </p>
                </li>
            </ul>
        </form>
    </div>
</div>
<!-- 删除 over  -->    
    
<!-- 提示框（给用户友好的提示）开始 -->
<div class="de-position" id="tipsInfo" style="display: none;">
    <div class="close">关闭</div>
    <div class="box1"><h2 class="title2">提示</h2>
        <form>
            <ul class="form-s form-bk">
                <li>
                   <p id="tipsInfo_p"></p>
                </li>
                <li class="mt25">
                    <label></label>
                    <p><input type="button" id="btn_tipsInfo" class="btn-blue" value="确定" />
                </li>
            </ul>
        </form>
    </div>
</div>
<!-- 提示框（给用户友好的提示）结束 -->   
     
     
 <!-- 提示框（添加品种成功之后）开始 -->
<div class="de-position" id="tipsAddBreedInfo" style="display: none;">
    <div class="box1"><h2 class="title2">提示</h2>
        <form>
            <ul class="form-s form-bk">
                <li>
                   <p>添加成功</p>
                </li>
                <li class="mt25">
                    <label></label>
                    <p><input type="button" id="btn_addBreedInfo" class="btn-blue" value="确定" />
                </li>
            </ul>
        </form>
    </div>
</div>
<!-- 提示框（添加品种成功之后）结束 --> 
<script>
    $(function(){
    
		var Height = $('.tips').height()+18;
		$('.opr-btn .tips').css('top',-Height);
		//浮层
		var html = '<div class="bghui"></div>';
		 //点击弹出框的关闭按钮，删除浮层，关闭窗口
		
		//表单验证(初始化)
		var addBreedForm = $("#from-addBreed").Validform({
		    tiptype:3
		});
		
		var Close = $('.close');
        Close.each(function(){
            $(this).click(function(){
                $(this).parent().hide();
                $('.bghui').remove();
                addBreedForm.resetForm();
            })
        });
        
		//点击添加breed(返回页面，用ajax返回下拉框的值和树的结构（按照要求返回树的结构）)
		//树的setting设置
		var setting = {
			view: {
	            dblClickExpand: false
	        },
			check: {
					enable: true,
					chkStyle: "checkbox",
					chkboxType: { "Y": "", "N": "" }
				},
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0
				}
			}
		};
		
		//品种详情(点击查看品种详情)
        var breedInfo = $('.operate-5');
        breedInfo.each(function(){
            $(this).click(function(){
                $('#breedInfo').show();
                var height = $(document).height();
		        $('body').append(html);
		        $('.bghui').css('height',height);
                //获取这一行的id
                var breedId=$(this).parent().parent().children().first().html();
                //ajax提交请求（返回数据显示在页面上）
                $.ajax({
                	 url: "/getBreed/queryBreedInfoById",
					 type: 'post', 
					 data: {"breedId":breedId},
					 dataType:"json",
					 success:function(data){
					 	//成功后返回json格式的字符串，并且解析为js对象显示在前台页面
						//1.品种名称    
						var breedName = data.breedName;
						$("#p_breed_name").html(breedName);
						//2.品种编码
						var breedCode=data.breedCode;
						$("#p_breed_code").html(breedCode);
						//3.常见计量单位
						var breedCountUnit =data.breedCountUnit; 
						$("#p_breedCountUnit").html(breedCountUnit);
						//4.常见别名
						var listBreedCname=data.listBreedCname;
						var liBreedCname =$("#li_breed_cname");
						liBreedCname.empty();
						liBreedCname.append($("<label>").html("常见别名："));
						for(var i=0;i<listBreedCname.length;i++){
							var p =$("<p>");
							p.attr({"class": "bg" });
							p.html(listBreedCname[i].attrValue);
							liBreedCname.append(p);
						}
						//5.常见规格等级
						var listBreedLevel= data.listBreedLevel;
						var liBreedLevel =$("#li_breed_level");
						liBreedLevel.empty();
						liBreedLevel.append($("<label>").html("常见规格等级："));
						for(var i=0;i<listBreedLevel.length;i++){
							var p =$("<p>");
							p.attr({"class": "bg" });
							p.html(listBreedLevel[i].attrValue);
							liBreedLevel.append(p);
						}
						//6.常见产地
						var listBreedPlace =data.listBreedPlace;
						var liBreedPlace =$("#li_breed_place");
						liBreedPlace.empty();
						liBreedPlace.append($("<label>").html("常见产地："));
						for(var i=0;i<listBreedPlace.length;i++){
							var p =$("<p>");
							p.attr({"class": "bg" });
							p.html(listBreedPlace[i].attrValue);
							liBreedPlace.append(p);
						}
						//7.所有的目录树
						var listTree = data.listTree;
						//table_breedInfo
						//成功后返回json格式的listTree，解析listTree成树显示在页面上
					    //7.1.清空table中的树的数据
					    $('#table_breedInfo').empty();
					    //7.2.将listTree中的数据存放在table中
					    var listTree=data.listTree;
					    //7.3.进行循环生成table中的数据
					     for(var i=0;i<listTree.length;i++){
					     	//7.3.1获取了classInfo和categorys
					     	var classInfo = listTree[i].classInfo;
					     	var zNodes = listTree[i].categorys;
					     	//7.3.2封装一个table下面的td
					     	var ul=$("<ul>");
					     	var ulId= "treeDemo"+i;
					     	ul.attr({ id: ulId, "class": "ztree" });
					     	var div=$("<div>");
					     	div.attr({"class": "zTreeDemoBackground left" });
					     	//ul放在div里面
					     	div.append(ul);
					     	var td=$("<td>");
					     	//在td表格里面添加h1和div
					     	td.append("<h1>"+classInfo+"</h1>");
					     	td.append(div);
					     	//需要的时候初始化一个tr
					     	if(i==0||i%3==0){
					     		var tr= $("<tr>");
					     	}
					     	tr.append(td);
					     	$('#table_breedInfo').append(tr);
							var zNodes2 = jQuery.parseJSON(zNodes);
					     	$.fn.zTree.init($("#"+ulId), setting, zNodes2);
			           }
			           
					 }
                });
            });
        });
       
		//tipa
		var Height = $('.tips').height()+18;
		$('.opr-btn .tips').css('top',-Height);
        $('.operate-1').hover(
                function(){
                    $(this).children('.tips').show();
                },
                function(){
                    $(this).children('.tips').hide();
                }
        );
		
		
		$('.btn-add').on('click',function(){
            $('#addBreed').show();
            $('#breedCode').removeAttr('disabled');
            var height = $(document).height();
        	$('body').append(html);
        	$('.bghui').css('height',height);
            
            $("#addBreedTitle").html("添加品种");
            $("#btnAddBreed").attr("value","添加");
            //清空表格的数据
			cleanAddBreedData();
			cleanTagData();
            //通过Ajax查询出
            $.ajax({
			 url: "/getBreed/getDictInfoAndTreeInfo",
			 type: 'post', 
			 data: {"DICT_TYPE":"weight_unit"},
			 dataType:"json",
			 success:function(data){
                //成功后返回json格式的字符串，并且解析为js对象显示在前台页面
				 //清空select中的数据
				 $('#selectDict').empty();
				 
			     //返回json数据  
			     //将返回的json数据展现在页面上
			     var listLimtDict=data.listLimtDict;
			     for(var i=0;i<listLimtDict.length;i++){
			     	var value=listLimtDict[i].DICT_CODE;
			     	var innerText=listLimtDict[i].DICT_VALUE;
			     	var option = $("<option>").val(value).text(innerText);
			     	$('#selectDict').append(option);
			     }
			    $("#selectDict option[value='1']").attr("selected","true");
			     //成功后返回json格式的listTree，解析listTree成树显示在页面上
			     //1.清空table中的树的数据
			     $('#tableTree').empty();
			     //2.将listTree中的数据存放在table中
			     var listTree=data.listTree;
			     //3.进行循环生成table中的数据
			     for(var i=0;i<listTree.length;i++){
			     	//3.1获取了classInfo和categorys
			     	var classInfo = listTree[i].classInfo;
			     	var zNodes = listTree[i].categorys;
			     	//3.2封装一个table下面的td
			     	var ul=$("<ul>");
			     	var ulId= "treeDemo"+i;
			     	ul.attr({ id: ulId, "class": "ztree" });
			     	var div=$("<div>");
			     	div.attr({"class": "zTreeDemoBackground left" });
			     	//ul放在div里面
			     	div.append(ul);
			     	var td=$("<td>");
			     	//在td表格里面添加h1和div
			     	td.append("<h1>"+classInfo+"</h1>");
			     	td.append(div);
			     	//需要的时候初始化一个tr
			     	if(i==0||i%3==0){
			     		var tr= $("<tr>");
			     	}
			     	tr.append(td);
			     	$('#tableTree').append(tr);
					var zNodes2 = jQuery.parseJSON(zNodes);
			     	$.fn.zTree.init($("#"+ulId), setting, zNodes2);
			     	}
            	},
             error:function(data){
               	alert("操作失败");
                }
			});
        });
        //点击提示框的确定按钮
       	$("#btn_tipsInfo").click(function(){
        	$("#tipsInfo").hide();
        });
        
        //点击添加成功后的确定按钮
        $("#btn_addBreedInfo").click(function(){
        	var name =$("#name").val();
        	var code =$("#code").val();
        	var place =$("#place").val();
        	//跳转刷新页面
        	location.href="/getBreed/queryBreed?name="+name+"&code="+code+"&place="+place;
        });
		//点击添加页面的添加数据
		$("#btnAddBreed").click(function(){
			/***********用来判断是新增还是修改（有id表示修改，没有id是新增）**************/
			var breedId =$("#breedId").val();
			//1.获取页面传递的数据
			//1.1产品名称
			var breedName= $("#breedName").val();
			//1.2产品编码
			var breedCode=$("#breedCode").val();
			//1.3常见数量单位
			var selectDict=$("#selectDict").val();
			//1.4获取标签数据
			var tagData=getTagData();
			var BREED_CNAME=tagData["BREED_CNAME"];
			var BREED_STANDARD_LEVEL=tagData["BREED_STANDARD_LEVEL"];
			var BREED_PLACE=tagData["BREED_PLACE"];
			//1.5获取类目数量（选择分类）
			var noteIds=getXzlmData();
			//前端校验
			var flag=checkBreedCommit();
			//1.6将数据进行封装，进行异步提交
			//if(!breedId && typeof(breedId)!="undefined" && breedId!=0){
			if(flag){
				if(breedId==""){
				/***********************添加breed*******************************************/
					$.ajax({
					 url: "/getBreed/addBreed",
					 type:'post', 
					 data: {
					 	"breedName":breedName,
					 	"breedCode":breedCode,
					 	"selectDict":selectDict,
					 	"BREED_CNAME":BREED_CNAME,
					 	"BREED_STANDARD_LEVEL":BREED_STANDARD_LEVEL,
					 	"BREED_PLACE":BREED_PLACE,
					 	"noteIds":noteIds
					 	},
					 dataType:"json",
					 success:function(data){
	                    //成功后返回json格式的字符串，并且解析为js对象显示在前台页面
					     if(data.flag){
					     	$("#addBreed").hide();
					     	//$('.bghui').remove();
							$("#tipsAddBreedInfo").show();
					     }else{
					     	$("#tipsInfo_p").html("添加失败");
							$("#tipsInfo").show();
					     }    
	                	},
	                 error:function(data){
	                   		$("#tipsInfo_p").html("添加失败");
							$("#tipsInfo").show();
	                    }
					});
				}else{
					$.ajax({
					 url: "/getBreed/modifyBreed",
					 type:'post', 
					 data: {
					 	"breedId":breedId,
					 	"breedName":breedName,
					 	"breedCode":breedCode,
					 	"selectDict":selectDict,
					 	"BREED_CNAME":BREED_CNAME,
					 	"BREED_STANDARD_LEVEL":BREED_STANDARD_LEVEL,
					 	"BREED_PLACE":BREED_PLACE,
					 	"noteIds":noteIds
					 	},
					 dataType:"json",
					 success:function(data){
	                    //成功后返回json格式的字符串，并且解析为js对象显示在前台页面
					     if(data.flag){
					     	$("#addBreed").hide();
					     	$('.bghui').remove();
					     	$("#tipsInfo_p").html("修改成功");
							$("#tipsInfo").show();
					     }else{
					     	$("#tipsInfo_p").html("修改失败");
							$("#tipsInfo").show();
					     }    
	                	},
	                 error:function(data){
	                   	$("#tipsInfo_p").html("修改失败");
						$("#tipsInfo").show();
	                    }
					});
				}
			}
    });
 /****************************************************************************************/
 /******************************************************************************************/   		 
		//点击修改breed
		var reBreed = $('.operate-2');
        reBreed.each(function(){
            $(this).click(function(){
                $('#addBreed').show();
                $('#breedCode').attr('disabled','disabled');
                var height = $(document).height();
        		$('body').append(html);
        		$('.bghui').css('height',height);
                $("#addBreedTitle").html("修改品种");
                $("#btnAddBreed").attr("value","修改");
                cleanAddBreedData();
                //获取这一行的id
                var breedId=$(this).parent().parent().children().first().html();
                
                //ajax提交请求（返回数据显示在页面上）
                $.ajax({
                	 url: "/getBreed/modifyBreedQueryById",
					 type: 'post', 
					 data: {"breedId":breedId},
					 dataType:"json",
					 success:function(data){
					 	//成功后返回json格式的字符串，并且解析为js对象显示在前台页面
					 	/****品种ID（很重要）*****/
					 	$("#breedId").val(data.breedId);
						//1.品种名称    
						var breedName = data.breedName;
						$("#breedName").val(breedName);
						//2.品种编码
						var breedCode=data.breedCode;
						$("#breedCode").val(breedCode);
						//3.常见计量单位
						//清空select中的数据
						$('#selectDict').empty();
						var breedCountUnit=data.breedCountUnit; 
					     //将返回的json数据展现在页面上
					     var listLimtDict=data.listLimtDict;
					     for(var i=0;i<listLimtDict.length;i++){
					     	var value=listLimtDict[i].DICT_CODE;
					     	var innerText=listLimtDict[i].DICT_VALUE;
					     	var option = $("<option>").val(value).text(innerText);
					     	$('#selectDict').append(option);
					     }
					     $('#selectDict').val(breedCountUnit); 
					     //qq标签操作
					     //清空qq标签
					     cleanTagData();
						//4.常见别名
						var listBreedCname=data.listBreedCname;
						var $tags1 = $("#tags1");
						for(var i=0;i<listBreedCname.length;i++){
							$tags1.masterblaster( "push", listBreedCname[i].attrValue );
						}
						
						//5.常见规格等级
						var listBreedLevel= data.listBreedLevel;
						var $tags2 =$("#tags2");
						$tags2.masterblaster("remove")
						for(var i=0;i<listBreedLevel.length;i++){
							$tags2.masterblaster( "push", listBreedLevel[i].attrValue );
						}
						
						
						//5.常见规格等级
						var listBreedPlace= data.listBreedPlace;
						var $tags3 =$("#tags3");
						for(var i=0;i<listBreedPlace.length;i++){
							$tags3.masterblaster( "push", listBreedPlace[i].attrValue );
						}
					
						
						//成功后返回json格式的listTree，解析listTree成树显示在页面上
					    //7.1.清空table中的树的数据
					    $('#tableTree').empty();
					    //7.2.将listTree中的数据存放在table中
					    var listTree=data.listTree;
					    //7.3.进行循环生成table中的数据
					     for(var i=0;i<listTree.length;i++){
					     	//7.3.1获取了classInfo和categorys
					     	var classInfo = listTree[i].classInfo;
					     	var zNodes = listTree[i].categorys;
					     	//7.3.2封装一个table下面的td
					     	var ul=$("<ul>");
					     	var ulId= "breedTreeInfo"+i;
					     	ul.attr({ id: ulId, "class": "ztree" });
					     	var div=$("<div>");
					     	div.attr({"class": "zTreeDemoBackground left" });
					     	//ul放在div里面
					     	div.append(ul);
					     	var td=$("<td>");
					     	//在td表格里面添加h1和div
					     	td.append("<h1>"+classInfo+"</h1>");
					     	td.append(div);
					     	//需要的时候初始化一个tr
					     	if(i==0||i%3==0){
					     		var tr= $("<tr>");
					     	}
					     	tr.append(td);
					     	$('#tableTree').append(tr);
							var zNodes2 = jQuery.parseJSON(zNodes);
					     	$.fn.zTree.init($("#"+ulId), setting, zNodes2);
			           }
			         }
                });
            });
        });
       
       
       //删除弹层
       //弹出确认删除的窗口
        var reBreed = $('.operate-4');
        var Close = $('.close');
        //遍历关闭窗口
        reBreed.each(function(){
            $(this).click(function(){
                $('#deleteBreed').show();
                var height = $(document).height();
        		$('body').append(html);
        		$('.bghui').css('height',height);
                //获取这一行的id
                var id=$(this).parent().parent().children().first().html();
                //点击删除的确认按钮
                 $('#qrDelBreed').click(function(){
                 	//执行ajax方法进行异步删除
                 	$.ajax({
					 url: "/getBreed/deleteBreedById",
					 type: 'post', 
					 data: {"id":id},
					 dataType:"json",
					 success:function(data){
	                    //成功后返回json格式的字符串，并且解析为js对象显示在前台页面
						     if(data.flag){
						     	$("#id"+id).parent().remove();	
						     	$('#deleteBreed').hide();
						     	$('.bghui').remove();
						     	$("#tipsInfo_p").html("删除成功");
								$("#tipsInfo").show();
						     }else{
						     	$("#tipsInfo_p").html("删除失败");
								$("#tipsInfo").show();
						     }            
	                	},
	                 error:function(data){
	                   	$("#tipsInfo_p").html("删除失败");
						$("#tipsInfo").show();
	                    }
					});
        		 });
				
            });
        });
        //点击关闭
        Close.each(function(){
            $(this).click(function(){
                $(this).parent().hide();
                $('.bghui').remove();
            })
        });
        /***********************点击取消按钮（开始）*******************************************/
        //点击删除框中的取消按钮
        $('#cancelDel').click(function(){
        	$('#deleteBreed').hide();
        	$('.bghui').remove();
        });
       
       //点击(添加breed中的取消)取消按钮(关闭窗口)
        $('.btn-hui').click(function(){
        	$('#addBreed').hide();
        	$('#breedInfo').hide();
        	$('.bghui').remove();
        });
         /**********************点击取消按钮（结束）********************************************/
       
    });
    
	$( "#tabs" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	$( "#tabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
	$("#tags1").masterblaster({
	 animate:true
	});
	$("#tags2").masterblaster({
	 animate:true
	});
	$("#tags3").masterblaster({
	 animate:true
	});
	
	
	/**************************标签（开始）*************************************/
	 function getTagData(){
        	//将标签里面的数据拼接成字符串，用逗号分隔(tabs1开始)
        	var tabs1=$('#tabs-1 ul li');
        	var len=tabs1.length;
        	var BREED_CNAME='';
        	tabs1.each(function(i){
        		if(i==(len-1)){
        			BREED_CNAME+=$(this).data('tag');
        			//表示最后一个	
        		}else{
        			BREED_CNAME+=$(this).data('tag')+',';
        		}
        		//或者是向像下面
        		//alert($(this).attr('data-tag'));
        	});
        	//(tabs1结束)
        	
        	//将标签里面的数据拼接成字符串，用逗号分隔(tabs2开始)
        	var tabs2=$('#tabs-2 ul li');
        	var tabs2len=tabs2.length;
        	var BREED_STANDARD_LEVEL='';
        	tabs2.each(function(i){
        		if(i==(tabs2len-1)){
        			BREED_STANDARD_LEVEL+=$(this).data('tag');
        			//表示最后一个	
        		}else{
        			BREED_STANDARD_LEVEL+=$(this).data('tag')+',';
        		}
        	});
        	//(tabs2结束)
        	
        	//将标签里面的数据拼接成字符串，用逗号分隔(tabs3开始)
        	var tabs3=$('#tabs-3 ul li');
        	var tabs3len=tabs3.length;
        	var BREED_PLACE='';
        	tabs3.each(function(i){
        		if(i==(tabs3len-1)){
        			BREED_PLACE+=$(this).data('tag');
        			//表示最后一个	
        		}else{
        			BREED_PLACE+=$(this).data('tag')+',';
        		}
        	});
        	//(tabs3结束)
        	var tagData={"BREED_CNAME":BREED_CNAME,
        			"BREED_STANDARD_LEVEL":BREED_STANDARD_LEVEL,
        			"BREED_PLACE":BREED_PLACE};
        	return tagData;
	}
	 /***************************标签（结束）********************************************/
	//清空弹出的添加页面和修改页面的数据
	function cleanAddBreedData(){
		$('#breedCode').val("");
		$('#breedName').val("");
		$('#breedId').val("");
	}
	//清空qq标签里面的数据
	function cleanTagData(){
		var tabs1=$('#tabs-1 ul li');
		tabs1.remove();
		var tabs2=$('#tabs-2 ul li');
		tabs2.remove();
		var tabs3=$('#tabs-3 ul li');
		tabs3.remove();
	}
	//获取选择类目的分类的数据
	function getXzlmData(){
		//1.获取所有的tree
		var trees=$("#tableTree .ztree");
		//2.循环trees获取选中的节点
		var nodeIds='';
		for(var i=0;i<trees.length;i++){
			//trees[i]取出来的不是jquery对象，必须包一层才是jquery对象
			var tree = $(trees[i]);
			var id=tree.attr('id');
			var treeObj = $.fn.zTree.getZTreeObj(id);
			//这个事多选框应该用getCheckedNodes,有些方式是用getSelectedNodes
			var nodes =treeObj.getCheckedNodes();
			if(nodes.length>0){
					for(var j=0;j<nodes.length;j++){
					var id = nodes[j].id;
					nodeIds+=id;
					nodeIds+=",";
				}
			}
		}
		//去掉最后一个,
		var  dataNoteIds=nodeIds.substring(0,nodeIds.length-1)
		return dataNoteIds;
	}
	
	//添加框提交后进行js验证
	function checkBreedCommit(){
		var reBreedname =new RegExp("^[\u4e00-\u9fa5]{1,6}$");
		var reBreedcode =new RegExp("^[A-Za-z0-9]+$");
		var breedName = $("#breedName").val();
		$("#p_breedName span").remove();
		$("#p_breedCode span").remove();
		var flag=true;
		var resultMsg="";
		if(breedName==""){
			resultMsg="品种名称不能为空";
			flag=false;
			var $span=$("<span>");
			$span.attr({"class":"Validform_checktip col_999"});
			$span.html(resultMsg);
			$("#p_breedName").append($span);
			return flag;
			
		}
		//验证没有通过
		var resultBreedName=reBreedname.test(breedName);
		if(!resultBreedName){
			resultMsg="品种名称不可超过6个汉字";
			flag=false;
			var $span=$("<span>");
			$span.attr({"class":"Validform_checktip col_999"});
			$span.html(resultMsg);
			$("#p_breedName").append($span);
			return flag;
		}
		var breedCode=$("#breedCode").val();
		if(breedCode==""){
			resultMsg="品种编码不能为空";
			flag=false;
			var $span=$("<span>");
			$span.attr({"class":"Validform_checktip col_999"});
			$span.html(resultMsg);
			$("#p_breedCode").append($span);
			return flag;
		}
		//正则表达式验证breedCode
		var resultBreedCode = reBreedcode.test(breedCode)
		if(!resultBreedCode){
			resultMsg="不可为中文，只可以为字母和数字";
			flag=false;
			var $span=$("<span>");
			$span.attr({"class":"Validform_checktip col_999"});
			$span.html(resultMsg);
			$("#p_breedCode").append($span);
			return flag;
		}
		//add by fanyuna 验证唯一性
		var breedId =$("#breedId").val();
		if(breedId==null||breedId==""){
			var breedCodeOk = validBreedCode(breedCode); 
			var breedNameOk = validBreedName(breedName);
			if((!breedCodeOk)||(!breedNameOk)){
				flag = false				
				return flag;
			}
		}
		var tagData=getTagData();
		var BREED_CNAME =tagData.BREED_CNAME;
		var BREED_STANDARD_LEVEL =tagData.BREED_STANDARD_LEVEL;
		var BREED_PLACE =tagData.BREED_PLACE;
		if(BREED_CNAME==""){
			resultMsg="常见别名不能为空";
			flag=false;
			$("#tipsInfo_p").html(resultMsg);
			$("#tipsInfo").show();
			return flag;
		}
		if(BREED_STANDARD_LEVEL==""){
			resultMsg="常见等级规格不能为空";
			flag=false;
			$("#tipsInfo_p").html(resultMsg);
			$("#tipsInfo").show();
			return flag;
		}
		if(BREED_PLACE==""){
			resultMsg="常见产地不能为空";
			flag=false;
			$("#tipsInfo_p").html(resultMsg);
			$("#tipsInfo").show();
			return flag;
		}
		return flag;
	}
	
	function validBreedCode(breedCode){
	var f=true;
	 $.ajax({
			 type: "POST",
			 url: "/getBreed/breedCodeIsHaved",
			 data:   "param="+breedCode,
			 async: false,
			 success: function(msg){
			 	if(msg!="y"){
					var $span=$("<span>");
					$span.attr({"class":"Validform_checktip col_999"});
					$span.attr({"style":"color:#F00"});
					$span.text("该品种编码已存在,请重新输入！");
					$("#p_breedCode").append($span);
					f = false; 
				}
			  } 
			  
			}); 
			return f;
	}
	
	//验证品种名称唯一性
	function validBreedName(breedName){
			var f=true;
			 $.ajax({
					 type: "POST",
					 url: "/getBreed/breedNameIsHaved",
					 data:   "param="+breedName,
					 async: false,
					 success: function(msg){
					 	if(msg!="y"){
							var $span=$("<span>");
							$span.attr({"class":"Validform_checktip col_999"});
							$span.attr({"style":"color:#F00"});
							$span.text("该品种名称已存在,请重新输入！");
							$("#p_breedName").append($span);
							f = false; 
						}
					  } 
			  
			}); 
			return f;
	}
	
</script>
</body>
</html>