<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>中药材电子商务管理系统</title>
    <link rel="stylesheet" type="text/css" 	href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" 	href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" 	href="${RESOURCE_CSS}/js/jzt-boss/ztree/zTreeStyle/zTreeStyle.css" 	type="text/css">
	<link rel="stylesheet" type="text/css" 	href="${RESOURCE_CSS}/css/jquerytab.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/datetimepicker/jquery.datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
<style>
#cont{background:#f2f2f2; padding:10px; border:1px solid #ccc; border-radius:5px;}
.ui-tabs{padding-top:0;}
</style>
</head>
<body>
<#include "home/top.ftl" />  
<div class="wapper">
<#include "home/left.ftl" /> 
<!--分类标签-->
<div class="main">
<div class="demo-classify" >
	<div id="dialog" title="" >
	<h1 class="ui-dialog-title1">新增分类</h1>
		<form id="classForm">
				<ul class="form-dialog form-bk" >
                	<li><label for="tab_title">分类名称:</label><input type="hidden" id="classId" />
				   <input type="text" name="className" id="tab_title" value="" class="ui-widget-content2 ui-corner-all text text-1 " ajaxurl="" nullmsg="请输入分类名称" datatype="*" maxlength="6"/><span id="classNameError" class="Validform_checktip col_999">不大于6个字</span></li>
				<li><label for="tab_content">运营策略:</label>
				<textarea name="operateStrage" id="tab_content" class="text"  maxlength="40" style=" width:279px; height:100px; resize:none;"></textarea><span id="classStrageError" class="Validform_checktip col_999">不大于40个字</span></li><li><span class="col_999 mainadd">主要增加本套分类方法的运营策略，前台哪些频道与模板的调用等</span></li></ul>
            
		</form>
	</div>

	
	<div id="tabs" style="padding-bottom:0;">
		<ul>
			<#list classInfoList as classInfo>
			<li class="onRC ui-state-default ui-corner-top"><a href="javascript:;" style="text-align:center; width:112px;"  onclick="toClassCategory('${classInfo.classId}');" id="${classInfo.classId}" target="down">${classInfo.className}</a></li>
		  </#list>
		  <@shiro.hasPermission name="类目管理-添加分类">
            <li id='button' class="addaction"><span id='add_tab'><img src='${RESOURCE_IMG}/images/jzt-boss/ticon.png'>添加分类</span></li>
           </@shiro.hasPermission> 
		</ul>
		<div id="tabs-1" class="tabscontent" style="margin-top:-20px;">
		</div>
	</div>
</div>
<#if firstClass??><#if firstClass.classId??>
<input id="firstClassId" type="hidden" value="${firstClass.classId}"/>
</#if></#if>
<input id="currentClassId" type="hidden" />
<input id="currentCategoryId" type="hidden" />
</div>
</div>
<!-- End -->


<!-- 右菜单 -->
<div id="rMenu">
    <ul>
      <@shiro.hasPermission name="类目管理-目录分类-新增子目录">
        <li id="m_add" ><i class="icon-4"></i>新增子目录</li>
      </@shiro.hasPermission> 
      <@shiro.hasPermission name="类目管理-目录分类-修改分类">
        <li id="m_mod" ><i class="icon-5"></i>修改分类</li>
      </@shiro.hasPermission>
      <@shiro.hasPermission name="类目管理-目录分类-删除分类"> 
        <li id="m_del" ><i class="icon-6"></i>删除分类</li>
       </@shiro.hasPermission> 
        <!-- <li id="m_unCheck" onclick="checkTreeNode(false);">unCheck节点</li>
        <li id="m_reset" onclick="resetTree();">恢复zTree</li> -->
    </ul>
</div>
<!-- 右菜单 over -->
<!-- 弹层 -->
  <!-- 新增类目 -->
<div class="pop-position" id="addGccount" style="display: none;">
    <div class="close">关闭</div>
    <div class="box">
        <h1 class="title1">新增类目</h1>
        <form id="addTopCategoryForm" action="addCategory" method="post">
            <ul class="form-1 form-bk">
               <input type="hidden" name="classId" id="addClassId" />
               <input type="hidden" name="parentCatId" value="0" />
                <li>
                    <label>类目名称：</label>
                    <input class="text text-1" type="text" maxlength="6" datatype="*1-6"  nullmsg="请输入类目名！" errormsg="名称在6个字以内！" id="addCategoryName" name="catName"  />
                    <span id="topCategoryMsg"></span>
                </li>
                <li class="mt25">
                    <label></label>
                    <input type="button" class="btn-blue" value="添加" id="addTopCategory" />
                </li>
            </ul>
        </form>
    </div>
</div>
  <!-- 新增类目 over  -->
  <!-- 修改分类名称  -->
<div class="pop-position" id="addHccount" style="display: none;">
    <div class="close">关闭</div>
    <div class="box">
        <h1 class="title1">修改分类</h1>
        <form id="updateClassForm">
            <ul class="form-1 form-bk">
                <input type="hidden" name="classId" id="updateClassId" />
                <input type="hidden"  id="defaultUpdateClassName" />
                <li>
                    <label>类目名称：</label>
                    <input class="text text-1" type="text" maxlength="6" datatype="*1-6"  nullmsg="请输入分类名！" errormsg="名称在6个字以内！" id="updateClassName" name="className" />
                    <span id="updateClassNameMsg"></span>
                </li>
                
                <li>
                    <label>运营策略：</label>
                    <textarea name="operateStrage" class="text" style=" width:340px; height:90px;" maxlength="40" id="updateClassStrage" ></textarea>
                    <span id="updateoperateStrageMsg"></span>
                </li>
                <li class="mt25">
                    <label></label>
                    <input type="button" class="btn-blue" value="修改" id="updateClassBtn" />
                </li>
            </ul>
        </form>
    </div>
</div>
  <!-- 修改分类名称 over  -->
  <!-- 删除框  -->
<div class="de-position" id="addZccount" style="display: none;">
    <div class="close">关闭</div>
    <div class="box1"><h2 class="title2">提示</h2>
        <form>
        	<input type="hidden" name="classId" id="delClassId" />
            <ul class="form-s form-bk">
                <li>
                   <center><p>你确定要删除？</p></center>
                </li>
                <li class="mt25">
                    <center><input type="button" class="btn-blue" value="确定" id="delClassBtn" />
                    <input type="button" class="btn-blue" value="取消" onclick="javascript:closeDiv('addZccount');" /></center>
                </li>
            </ul>
        </form>
    </div>
</div>
<!-- 删除 over  -->
<!--提示框-->
<div class="de-position" id="classMsg" style="display: none;">
    <div class="close">关闭</div>
    <div class="box1"><h2 class="title2">提示</h2>
        <form>
            <ul class="form-s form-bk">
                <li>
                   <center><p id="classMsgInfo"></p></center>
                </li>
                <li class="mt25">
                    <label></label>
                    <center><input type="button" class="btn-blue" value="关闭" id="classMsgBtn"/>
                    </center>
                </li>
            </ul>
        </form>
    </div>
</div>


<!-- pageCenter start add by fanyuna -->

    <div class="">
        <div class="page-main" style="margin-top:100px;">
            <div class="system_box1 fl" style="margin-top:0;">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
           
<!-- ztreejs over -->

  
<div class="fr system_box2 relate">
<div id="cont" class="ui-tabs-panel ui-widget-content ui-corner-bottom"><p><#if firstClass??><#if firstClass.operateStrate??>${firstClassStrage}</#if></#if></p></div>
		<div id="associateBreedFormDiv"></div>
         <div id="associateBreedData"></div>
            
   </div>
 
</div></div>

<!-- 关联品种 -->

<div style="display: none;"  id="addQccount" class="pop-position" >

<div class="close">关闭</div>
    <div class="box">
        <h1 class="title1">关联品种</h1>
       		<form id="queryBreedForm" >
            <ul class="form-1 form-bk">
                <li>
                	<input type="hidden" name="associatePageNo" id="associatePageNo" />
                    <label>辅助查找：</label><input type="hidden" name="catId" id="notAssociateCategory" value=""/><input type="hidden" id="notAssociateClass" name="classId" value=""/>
                    <p><input class="text text-1" type="text" name="name" id="notAssociateName" value="" onkeydown="enter_down(event);" /></p>
                    <p><input type="button" class="btn-blue" value="查询" onclick="javascript:$('#associatePageNo').val(1);AjaxGetData('queryBreedForm','associatePageNo');" />
                </li></ul>
              </form> 
             <div id="searchData">
                
				</div>
				
							
                <ul class="form-1 form-bk">
                <li class="mt25">
                    <label></label>
                    <p><center><input type="button" class="btn-blue" value="关联" onclick="javascript:associateBreed();" />
                    <input type="button" class="btn-blue"  value="关闭" onclick="javascript:closeDiv('addQccount');" /></center></p>
                </li>
            </ul>
</div>
</div>

<!-- 关联品种 over  -->  
<!-- 右菜单 -->
<div id="rMenuTree" class="rMenu">
    <ul>
      <@shiro.hasPermission name="类目管理-子目录-新增子目录">
        <li id="m_add_tree"><i class="icon-4"></i>新增子类目</li>
       </@shiro.hasPermission>
       <@shiro.hasPermission name="类目管理-子目录-修改目录"> 
        <li id="m_mod_tree"><i class="icon-5"></i>修改类目</li>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="类目管理-子目录-删除目录"> 
        <li id="m_del_tree"><i class="icon-6"></i>删除类目</li>
        </@shiro.hasPermission>
    </ul>
</div>
<!-- 右菜单 over -->
<!-- 新增类目 -->
<div class="pop-position" id="addGccountTree" style="display: none;">
    <div class="close">关闭</div>
    <div class="box">
        <h1 class="title1">添加子类目</h1>
        <form id="addChildCatForm" action="addCategory" method="post">
            <ul class="form-1 form-bk">
                
                <li>
                  <label>类目名称：</label>
                  <input class="text text-1 register-text" maxlength="6" datatype="*1-6"  nullmsg="请输入类目名！" errormsg="名称在6个字以内！" id="addCatName" name="catName" type="text" />
                  <span id="addChildCatMsg"></span>
                                    
               </li>
                <input type="hidden" id="parentCatId" name="parentCatId"/>
              	 <input type="hidden" name="classId" id="addChildCategoryClassId" value="${classId}"/>
                <li class="mt25">
                    <label></label>
                    <input type="button" class="btn-blue" value="添加" id="addChildCategory" />
                </li>
            </ul>
        </form>
    </div>
</div>
  <!-- 新增类目 over  -->
<!-- 修改类目  -->
<div class="pop-position" id="addHccountTree" style="display: none;">
    <div class="close">关闭</div>
    <div class="box">
        <h1 class="title1">修改类目</h1>
        <form id="alertCatForm" action="updateCategory" method="post">
            <ul class="form-1 form-bk">
                <li>
                    <label>类目名称：</label>
                    <input class="text text-1 register-text" maxlength="6" datatype="*1-6"  nullmsg="请输入类目名！" errormsg="名称在6个字以内！"  name="catName" id="alertCatName" />
                    <span id="alertCategoryMsg"></span>
                </li>
                <input type="hidden" id="alertCatId" name="catId"/>
                    <input type="hidden" name="classId" id="alertCategoryClassId" value="${classId}"/>
                <li class="mt25">
                    <label></label>
                    <input type="button" class="btn-blue" value="修改" id="alertCategoryBtn" />
                </li>
            </ul>
        </form>
    </div>
</div>
  <!-- 修改类目 over  -->
<!-- 删除提示框  -->
<div class="de-position" id="addZccountTree" style="display: none;">
    <div class="close">关闭</div>
    <div class="box1"><h2 class="title2">提示</h2>
        <form>
            <ul class="form-s form-bk">
                <li>
                   <center><p>您确定要删除此类目吗?</p></center>
                </li>
                <li class="mt25">
                    <center><input type="button" class="btn-blue" value="确定" id="deleteCatBtn" />
                    <input type="button" class="btn-blue" value="取消" id="cancleCatBtn" /></center>
                </li>
                <input type="hidden" id="deleteCatId"/>
                <input type="hidden" id="deleteClassId" value="${classId}" />
            </ul>
        </form>
    </div>
</div>
  <!-- 删除提示框 over  --> 

<!-- 信息提示框  -->
<div class="de-position" id="showCategoryMsg" style="display: none;">
    <div class="close">关闭</div>
    <div class="box1"><h2 class="title2">提示</h2>
        <form>
            <ul class="form-s form-bk">
                <li>
                   <center><p id="categoryMsgInfo"></p></center>
                </li>
                <li class="mt25">
                    <label></label>
                    <center><input type="button" class="btn-blue" value="确定" id="categoryMsgBtn"/>
                     </center>
                     <input type="hidden" id="msgClassId" />
                     <input type="hidden" id="msgcategoryId" />
                     
                </li>
            </ul>
        </form>
    </div>
</div>

  <!-- 信息提示框 over  --> 
<div class="de-position" id="associateBreedMsg" style="display: none;">
    <div class="close">关闭</div>
    <div class="box1"><h2 class="title2">提示</h2>
        <form>
            <ul class="form-s form-bk">
                <li>
                   <center><p id="associateBreedMsgInfo"></p></center>
                </li>
                <li class="mt25">
                    <label></label>
                    <center><input type="button" class="btn-blue" value="确定" id="associateBreedMsgBtn"/>
                     </center>
                     
                </li>
            </ul>
        </form>
    </div>
</div>

 <!-- 删除框  -->
<div class="de-position" id="delCategoryBreedDiv" style="display: none;">
    <div class="close">关闭</div>
    <div class="box1"><h2 class="title2">提示</h2>
        <form>
        	<input type="hidden"  id="breedCategoryId" />
            <ul class="form-s form-bk">
                <li>
                   <center><p>确认要取消该目录下的此品种吗？</p></center>
                </li>
                <li class="mt25">
                    <center><input type="button" class="btn-blue" value="确定" id="delCategoryBreedBtn" />
                    <input type="button" class="btn-blue" value="取消" onclick="javascript:closeDiv('delCategoryBreedDiv');" /></center>
                </li>
            </ul>
        </form>
    </div>
</div>
<!-- 删除 over  -->
<!--end by fanyuna-->
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-ui/jquery.ui.position.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-ui/jquery.ui.core.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-ui/jquery.ui.widget.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-ui/jquery.ui.button.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-ui/jquery.ui.dialog.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jzt-boss/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jzt-boss/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jzt-boss/ztree/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jzt-boss/directory-manage-dyn.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" 	src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript"  src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript">
	//正整数验证
	function fillNumOnly(obj) {
	    var str = obj.value;
	    if ($.trim(str) == "")
	        return;
	    if (/[^0-9]/g.test(str)) {
	        obj.value = str.substring(0, str.length - 1);
	    }
	}
	var vmap = new Array(); //用于存放自定义排序的全局数组 add by Mr.song 2015.4.3
	// map put value 向map里存值 add by Mr.song 2015.4.3
	function putmap(categorysid,key){
		var value=$("#"+key).val();
		if(key!=null && hasmap(key)){
			delete vmap[key];   //删除    
		}
		if(value!='0' &&　value!=''){
			for(var k in vmap){
				if(value== getmap(k)) {  //判断值是否存在  
				  	tips("保存失败，与编号:"+k+" 的排序重复！");
					$("#"+key).val('');  
	              	$("#"+key).addClass("Validform_error");
					delete vmap[key];   //删除    
					return;
				}else{
					$("#"+key).removeClass("Validform_error");
				}
			}	
		}
		if($.trim(value) != "") vmap[key] = value;      //重新赋值
		var t_flag = false;
		if(value!='0' &&　value!=''){
			$.ajax({  
	          async: false,  
	          type: "get",  
	          data:{categorysid:categorysid,cindex:value},  
	          url:"getCategoryManage/validRules",  
	          success: function(data){ 
	             if(data != 0){
	              	tips("保存失败，与编号:"+data+" 的排序重复！");
	              	$("#"+key).val('');
	              	$("#"+key).addClass("Validform_error");
	              	delete vmap[key];   //删除    
	              	t_flag=true;
	              	return;
	             }else{
	             	$("#"+key).removeClass("Validform_error");
	             }
			  },  
			  error: function(){tips("网络异常.");}  
			 });
		}
		if(document.activeElement.id=='saverules' && t_flag ==false){
			$("#saverules").click();
		}
	}
	
	//map get by key
	function getmap(key){
		return vmap[key];
	}
	//map has key
	function hasmap(key){
		if(key in vmap) { //判断是否存在  
		  return true; 
		}  
		return false;
	}
function tips(str){
		bghui();
		Alert({
	            str: str,
	            buttons:[{
	                name:'确定',
	                classname:'btn-blue',
	                ev:{click:function(data){
	                	 disappear();
                         $(".bghui").remove();
                     }
	               }
	            }]
	    });
	};

/**
*保存自定义排序规则
*/		
function saverules(){
	var x='';
	if(vmap.length == undefined || vmap.length<=0){
		tips('请填写排序数字!');
	}else{
		for(var v in vmap){
			x+=v+":"+getmap(v)+",";
		}
		x = x.substring(0,x.lastIndexOf(","));
	    $.ajax({  
	          async: false,  
	          type: "post",  
	          data:  {vmap:x},  
	          url:"getCategoryManage/saveRules",  
	          success: function(data){ 
	          		if(data ==true) {
	          			tips('保存排序成功！');  
	            		if($("#currentClassId")!=undefined && $("#currentClassId").val()!=null&&$("#currentClassId").val()!=""){
							ajaxGetClassStrage($("#currentClassId").val());
							getCategoryBy($("#currentClassId").val());
						}
	            	}else{
	            		tips('保存排序失败,可能网络异常！');  
	            	}
			  },  
			  error: function(){  
			   	tips('网络异常！');  
			  }  
		});  
	}
}	
	
$(function(){
	//获取第一个分类的tree
		if($("#firstClassId")!=undefined && $("#firstClassId").val()!=null&&$("#firstClassId").val()!=""){
			ajaxGetClassStrage($("#firstClassId").val());
			getCategoryBy($("#firstClassId").val());
			$("#currentClassId").val($("#firstClassId").val());
			}
		
       	});
       	
//表单验证
//$("#classForm").Validform({
//    tiptype:3
//});
//$("#updateClassForm").Validform({
//    tiptype:3
//});
//$("#addTopCategoryForm").Validform({
//    tiptype:3
//});

	function toClassCategory(selectedId){
	  vmap.splice(0,vmap.length);  //每次执行tab切换时清空数组缓存数据  add by Mr.song 2015.4.6
	  
	  ajaxGetClassStrage(selectedId);
	  	$("#currentCategoryId").val("");
		
		getCategoryBy(selectedId);
		$("#currentClassId").val(selectedId);
		
		$("#associateClassId").val(selectedId);
		
	}
function ajaxGetClassStrage(selectedId){
	
var data = {classId:selectedId};  
	    $.ajax({  
                  async: false,  
                  type: "post",  
                  data:data,  
                  dataType: "json",
                  url:"getCategoryManage/getClassStrageBy",  
                  success: function(data){  
	                	  if(data.operateStrate == ""|| data.operateStrate == null){
	                		  $("#cont").css('display','none');
	                	  }else{
	                		  $("#cont").css('display','block');
	                	  }
	                	  $("#cont").html(data.operateStrate); 
					  },  
				error: function(){  
				   tips('网络异常！');  
								  }  
				});  
}
	
//ajax获取类目关联的品种列表
function AjaxGetAssociateBreed(form,currentPageID) {
		if($("#associateBreedPageNo").val()=="1"){
			vmap.splice(0,vmap.length);  //每次执行tab切换时清空数组缓存数据  add by Mr.song 2015.4.6
		}
            $.ajax({
                url: "getCategoryManage/getCategoryBy",
                type: "post",
                data: $("#"+form).serialize(),
                dataType: "json",
                success: function (data) {
                 if(data!=null){
                    var htmlStr = "";
                    htmlStr += "<div class='use-item1 mt25'><@shiro.hasPermission name='类目管理-品种关联'><span class='btn-add mb10' id='abc'><a href='#'>关联品种</a> </span></@shiro.hasPermission>&nbsp;&nbsp;<@shiro.hasPermission name='类目管理-保存排序'><span class='btn-add mb10'><a id='saverules' href='javascript:void(0);' onclick='saverules();'>保存排序</a> </span></@shiro.hasPermission>"
                    htmlStr += "<table class='table-1' width='100%' cellpadding='1' cellspacing='1'>"
                    htmlStr += "<tr>"
                    htmlStr += "<th>编号</th><th>品种名称</th><th>品种编码</th><th>别名</th><th>规格等级</th><th>数量单位</th><th>产地</th><th>上级路径</th><th>排序</th><th width='80'>操作</th>"
                    htmlStr += "</tr>";
                    //设置隐藏域的值
                    $("#associateCategoryId").val(data.params.categoryId);
                	$("#associateBreedName").val(data.params.name);
                	$("#associateBreedCode").val(data.params.code);
                	$("#associateBreedPlace").val(data.params.place);
                	
                    if(data.totalPage==0){
						 $("#associateBreedData").html(htmlStr+"<tr><td colspan='10'>没有数据!</td></tr>"+"</table></div>");
					}
					else{
						for (var i = 0; i < data.results.length; i++) {
							htmlStr += "<tr><td>" + data.results[i].BREED_CATEGORY_ID + "</td>";
							htmlStr += "<td>" + data.results[i].BREED_NAME + "</td>"
											  + "<td>" + data.results[i].BREED_CODE + "</td>"
											  + "<td class='opr-btn'><span class='operate-1 operate-a'>";
											  if(data.results[i].BREED_CNAME.length>5)
											  htmlStr += data.results[i].BREED_CNAME.substring(0,5) + "...";
											  else
											  htmlStr +=data.results[i].BREED_CNAME;
											  htmlStr +="<div class='tips tipa' align='left'><span class='sj'></span>"+data.results[i].BREED_CNAME+"</div></span></td>"
											  + "<td class='opr-btn'><span class='operate-1 operate-a'>";
											  if(data.results[i].BREED_STANDARD_LEVEL.length>5)
											  htmlStr += data.results[i].BREED_STANDARD_LEVEL.substring(0,5) + "...";
											  else
											  htmlStr +=data.results[i].BREED_STANDARD_LEVEL;
											  htmlStr +="<div class='tips tipa' align='left'><span class='sj'></span>"+data.results[i].BREED_STANDARD_LEVEL+"</div></span></td>"
											  + "<td>" + data.results[i].BREED_COUNT_UNIT + "</td>"
											  + "<td class='opr-btn'><span class='operate-1 operate-a'>";
											  if(data.results[i].BREED_PLACE.length>5)
											  htmlStr += data.results[i].BREED_PLACE.substring(0,5) + "...";
											  else
											  htmlStr +=data.results[i].BREED_PLACE;
											  htmlStr +="<div class='tips tipa' align='left'><span class='sj'></span>"+data.results[i].BREED_PLACE+"</div></span></td>"
											  + "<td class='opr-btn'><span class='operate-1 operate-a'>";
											  if(data.results[i].path!=undefined && data.results[i].path!=null && data.results[i].path.length>5)
											  htmlStr += data.results[i].path.substring(0,5) + "...";
											  else if(data.results[i].path==undefined||data.results[i].path==null)
											   htmlStr +="";
											  else
											  htmlStr +=data.results[i].path;
											  htmlStr +="<div class='tips tipa' align='left'><span class='sj'></span>"+data.results[i].path+"</div></span></td>"
											  +"<td><input class='text text-2' maxlength='3' style='width:40px;text-align:center;' id='"+data.results[i].BREED_CATEGORY_ID+"' onkeyup='fillNumOnly(this);' onchange='setTimeout(function(){putmap("+data.results[i].CATEGORY_ID+","+data.results[i].BREED_CATEGORY_ID+");},10);' value='"+data.results[i].CINDEX+"' type='text'/></td><td class='opr-btn'><@shiro.hasPermission name='类目管理-取消关联'><a onclick=deleteBreed('"+data.params.classId+"','"+data.results[i].CATEGORY_ID+"','"+data.results[i].BREED_CATEGORY_ID+"');>"
                                              +"<span class=' operate-1 operate-6'><div class='tips tipb' align='left'><span class='sj'></span>取消与该目录的关联</div></span></a></@shiro.hasPermission></td>";
							htmlStr += "</tr>";
						}
						htmlStr += "</table></div>";
						var info = pageInfo(form,data,currentPageID);
						htmlStr +=info;
						

						$("#associateBreedData").html(htmlStr);
					}
                }
               
                },
                error: function (){
                   tips("error");
                }
            });
        }
//条件查询此类目及其子类目关联的品种(默认查询第一页数据)
function getCategoryByCondition(form,currentPageID) {
		if($("#associateBreedPageNo").val()=="1"){
			vmap.splice(0,vmap.length);  //每次执行tab切换时清空数组缓存数据  add by Mr.song 2015.4.6
		}
            $.ajax({
                url: "getCategoryManage/getCategoryByCondition",
                type: "post",
                data: $("#"+form).serialize(),
                dataType: "json",
                success: function (data) {
                 if(data!=null){
                    var htmlStr = "";
                    htmlStr += "<div class='use-item1 mt25'><@shiro.hasPermission name='类目管理-品种关联'><span class='btn-add mb10' id='abc'><a href='#'>关联品种</a> </span></@shiro.hasPermission>&nbsp;&nbsp;<@shiro.hasPermission name='类目管理-保存排序'><span class='btn-add mb10'><a id='saverules' href='javascript:void(0);' onclick='saverules();'>保存排序</a> </span></@shiro.hasPermission>"
                    htmlStr += "<table class='table-1' width='100%' cellpadding='1' cellspacing='1'>"
                    htmlStr += "<tr>"
                    htmlStr += "<th>编号</th><th>品种名称</th><th>品种编码</th><th>别名</th><th>规格等级</th><th>数量单位</th><th>产地</th><th>上级路径</th><th>排序</th><th width='80'>操作</th>"
                    htmlStr += "</tr>";
                    //设置隐藏域的值
                    $("#associateCategoryId").val(data.params.categoryId);
                	$("#associateBreedName").val(data.params.name);
                	$("#associateBreedCode").val(data.params.code);
                	$("#associateBreedPlace").val(data.params.place);
                	
                    if(data.totalPage==0){
						 $("#associateBreedData").html(htmlStr+"<tr><td colspan='10'>没有数据!</td></tr>"+"</table></div>");
					}
					else{
						for (var i = 0; i < data.results.length; i++) {
							htmlStr += "<tr><td>" + data.results[i].BREED_CATEGORY_ID + "</td>";
							htmlStr += "<td>" + data.results[i].BREED_NAME + "</td>"
											  + "<td>" + data.results[i].BREED_CODE + "</td>"
											  + "<td class='opr-btn'><span class='operate-1 operate-a'>";
											  if(data.results[i].BREED_CNAME.length>5)
											  htmlStr += data.results[i].BREED_CNAME.substring(0,5) + "...";
											  else
											  htmlStr +=data.results[i].BREED_CNAME;
											  htmlStr +="<div class='tips tipa' align='left'><span class='sj'></span>"+data.results[i].BREED_CNAME+"</div></span></td>"
											  + "<td class='opr-btn'><span class='operate-1 operate-a'>";
											  if(data.results[i].BREED_STANDARD_LEVEL.length>5)
											  htmlStr += data.results[i].BREED_STANDARD_LEVEL.substring(0,5) + "...";
											  else
											  htmlStr +=data.results[i].BREED_STANDARD_LEVEL;
											  htmlStr +="<div class='tips tipa' align='left'><span class='sj'></span>"+data.results[i].BREED_STANDARD_LEVEL+"</div></span></td>"
											  + "<td>" + data.results[i].BREED_COUNT_UNIT + "</td>"
											  + "<td class='opr-btn'><span class='operate-1 operate-a'>";
											  if(data.results[i].BREED_PLACE.length>5)
											  htmlStr += data.results[i].BREED_PLACE.substring(0,5) + "...";
											  else
											  htmlStr +=data.results[i].BREED_PLACE;
											  htmlStr +="<div class='tips tipa' align='left'><span class='sj'></span>"+data.results[i].BREED_PLACE+"</div></span></td>"
											  + "<td class='opr-btn'><span class='operate-1 operate-a'>";
											  if(data.results[i].path!=undefined && data.results[i].path!=null && data.results[i].path.length>5)
											  htmlStr += data.results[i].path.substring(0,5) + "...";
											  else if(data.results[i].path==undefined||data.results[i].path==null)
											   htmlStr +="";
											  else
											  htmlStr +=data.results[i].path;
											  htmlStr +="<div class='tips tipa' align='left'><span class='sj'></span>"+data.results[i].path+"</div></span></td>"
											  +"<td><input class='text text-2' maxlength='3' style='width:40px;text-align:center;' id='"+data.results[i].BREED_CATEGORY_ID+"' onkeyup='fillNumOnly(this);' onchange='setTimeout(function(){putmap("+data.results[i].CATEGORY_ID+","+data.results[i].BREED_CATEGORY_ID+");},10);' value='"+data.results[i].CINDEX+"' type='text'/></td><td class='opr-btn'><@shiro.hasPermission name='类目管理-取消关联'><a onclick=deleteBreed('"+data.params.classId+"','"+data.results[i].CATEGORY_ID+"','"+data.results[i].BREED_CATEGORY_ID+"');>"
                                              +"<span class=' operate-1 operate-6'><div class='tips tipb' align='left'><span class='sj'></span>取消与该目录的关联</div></span></a></@shiro.hasPermission></td>";
							htmlStr += "</tr>";
						}
						htmlStr += "</table></div>";
						var info = pageInfo(form,data,currentPageID);
						htmlStr +=info;
						

						$("#associateBreedData").html(htmlStr);
					}
                }
               
                },
                error: function (){
                   tips("error");
                }
            });
        }
	 
	
		//$("#addChildCatForm").Validform({
		//    tiptype:3
		//});
		//$("#alertCatForm").Validform({
		//    tiptype:3
		//});
		
		//ajax分页 form 查询form的ID,action 请求地址,currentPageID form里当前页控件的ID
		function AjaxGetData(form,currentPageID) {
		var classId = $("#currentClassId").val();
            $.ajax({
                url: "getCategoryManage/getNotAssociateBreedBy",
                type: "post",
                data: $("#"+form).serialize(),
                dataType: "json",
                success: function (data) {
                	$("#notAssociateName").val(data.params.name);
                    var htmlStr = "<form id='associateBreedForm' action='associateCategoryAndBreed' method='post'><input type='hidden' name='catId' value='"+data.params.categoryId+"'/><input type='hidden' name='classId' value='"+classId+"'/>";
                    htmlStr += "<table class='table-1' width='100%' cellpadding='1' cellspacing='1'>"
                    htmlStr += "<tr>"
                    htmlStr += "<th width='50'></th><th width='125'>品种名称</th><th width='135'>品种编码</th><th width='135'>别名</th><th width='135'>规格等级</th><th width='120'>数量单位</th><th width='200'>产地</th>"
                    htmlStr += "</tr>";
                    if(data.totalPage==0){
						 $("#searchData").html(htmlStr+"<tr><td colspan='7'>没有数据!</td></tr>"+"</table></form>");
					}
					else{
						for (var i = 0; i < data.results.length; i++) {
							htmlStr += "<tr>";
							htmlStr += "<td>"
							if(choose_breeds["breedId_" + data.results[i].breedId]){
								htmlStr += "<input name='breedId' value='"+data.results[i].breedId+"' checked type='checkbox'>";
							} else {
								htmlStr += "<input name='breedId' value='"+data.results[i].breedId+"' type='checkbox'>";
							}
							htmlStr += "</td><td>" + data.results[i].breedName + "</td>"
											  + "<td>" + data.results[i].breedCode + "</td>"
											  + "<td class='opr-btn'><span class='operate-1 operate-a'>";
											  if(data.results[i].breedCname && data.results[i].breedCname.length>5)
											  	htmlStr += data.results[i].breedCname.substring(0,5) + "...";
											  else
											  	htmlStr +=data.results[i].breedCname;
											  htmlStr +="<div class='tips tipa' align='left'><span class='sj'></span>"+data.results[i].breedCname+"</div></span></td>"
											  + "<td class='opr-btn'><span class='operate-1 operate-a'>";
											  if(data.results[i].breedStandardLevel && data.results[i].breedStandardLevel.length>5)
											  	htmlStr += data.results[i].breedStandardLevel.substring(0,5) + "...";
											  else
											  	htmlStr +=data.results[i].breedStandardLevel;
											  htmlStr +="<div class='tips tipa' align='left'><span class='sj'></span>"+data.results[i].breedStandardLevel+"</div></span></td>"
											  + "<td>" + data.results[i].breedCountUnit + "</td>"
											  + "<td class='opr-btn'><span class='operate-1 operate-a'>";
											  if(data.results[i].breedPlace && data.results[i].breedPlace.length>5)
											  	htmlStr += data.results[i].breedPlace.substring(0,5) + "...";
											  else
											  	htmlStr +=data.results[i].breedPlace;
											  htmlStr +="<div class='tips tipa' align='left'><span class='sj'></span>"+data.results[i].breedPlace+"</div></span></td>";
											  htmlStr += "</tr>";
						}
						htmlStr += "</table></form>";
						var info = pageInfo(form,data,currentPageID);
						htmlStr +=info;
						$("#searchData").html(htmlStr);
					}
                },
                error: function (){
                    tips("error");
                }
            });
        }
        //分页
        function pageInfo(form,data,currentPageID){
        	var htmlStr = "";
        	htmlStr += "<div class='page-file fr'>";				
						
						htmlStr += "<a href='javascript:void(0);' onclick=GoToFirstPage('"+form+"','"+currentPageID+"') id='aFirstPage' >首    页</a>   ";
						htmlStr += "<a href='javascript:void(0);' onclick=GoToPrePage('"+form+"',"+data.pageNo+",'"+currentPageID+"') id='aPrePage' >上一页</a>   ";
						htmlStr += "<a href='javascript:void(0);' onclick=GoToNextPage('"+form+"',"+data.pageNo+","+data.totalPage+",'"+currentPageID+"') id='aNextPage'>下一页</a>   ";
						htmlStr += "<a href='javascript:void(0);' onclick=GoToEndPage('"+form+"',"+data.totalPage+",'"+currentPageID+"') id='aEndPage' >末    页</a>   ";
						htmlStr += "<input type='text' class='text fy'  /><input type='button'  value='跳转' onclick=GoToAppointPage(this,'"+form+"',"+data.pageNo+","+data.totalPage+",'"+currentPageID+"') /> ";
						htmlStr += "<span>当前：<b>" + data.pageNo + "/"+data.totalPage+"</b>页</span><span>本页<b>"+data.results.length+"</b>条记录</span><span>全部<b>"+data.totalRecord+"</b>条记录</span>";
						htmlStr += "</div>";
						
			return htmlStr;			
		
        }
        
        //首页
        function GoToFirstPage(form,currentPageID) {
            $("#"+currentPageID).val(1);
           if(form =="queryBreedForm")
            AjaxGetData(form,currentPageID);
            else if(form == "getBreedForm")
            AjaxGetAssociateBreed(form,currentPageID);
        }
        //前一页
        function GoToPrePage(form,pageNo,currentPageID) {
            pageNo -= 1;
            pageNo = pageNo > 0 ? pageNo : 1;
            $("#"+currentPageID).val(pageNo);
           if(form =="queryBreedForm")
            AjaxGetData(form,currentPageID);
            else if(form == "getBreedForm")
            AjaxGetAssociateBreed(form,currentPageID);
        }
        //后一页
        function GoToNextPage(form,pageNo,totalPage,currentPageID) {
        	$("#"+currentPageID).val(pageNo + 1);
            if (pageNo + 1 > parseInt(totalPage)) {
                $("#"+currentPageID).val(pageNo);
            }
                if(form =="queryBreedForm")
            AjaxGetData(form,currentPageID);
            else if(form == "getBreedForm")
            AjaxGetAssociateBreed(form,currentPageID);
        }
        //尾页
        function GoToEndPage(form,totalPage,currentPageID) {
            $("#"+currentPageID).val(totalPage);
            if(form =="queryBreedForm")
            AjaxGetData(form,currentPageID);
            else if(form == "getBreedForm")
            AjaxGetAssociateBreed(form,currentPageID);
        }
        //跳转
        function GoToAppointPage(e,form,pageNo,totalPage,currentPageID) {
            var page = $(e).prev().val();
            if(!page){
                	 tips("跳转页面不能为空 !");
                	 return;
             }else{
	            if (isNaN(page)) {
	            	$(e).prev().val("");
	                tips("请输入数字!");
	                return;
		            }
	            else {
	                var tempPageIndex = pageNo;
	                pageNo = parseInt($(e).prev().val());
	                if (pageNo < 1 || pageNo > parseInt(totalPage)) {
	                    pageNo = tempPageIndex;
	                    $(e).prev().val("");
	                    tips("请输入有效的页面范围!");
	                    return;
	                }
	                else {
	                	$("#"+currentPageID).val(pageNo);
	                    if(form =="queryBreedForm")
			            AjaxGetData(form,currentPageID);
			            else if(form == "getBreedForm")
			            AjaxGetAssociateBreed(form,currentPageID);
	                }
	            }
             }
            
        }
        
var choose_breeds;
$(function(){
	$(document).on('click','input[name="breedId"]', function(){
		var value = $(this).val();
		if($(this).prop("checked")){
			choose_breeds["breedId_" + value] = value;
		} else {
			delete choose_breeds["breedId_" + value];
		}
	});
	
	//关联品种弹
	$("#associateBreedData").delegate('#abc', 'click', function() {
		choose_breeds = new Object();
		$("#searchData").html("");
		$('#associatePageNo').val(1);
		$('#notAssociateName').val("")
		AjaxGetData("queryBreedForm","associatePageNo");
    	$('#addQccount').show();
        $('body').append(maskDiv);
		$('.bghui').css('height',$(document).height()-20);
    });
    
})
		
 var isClose = false;
function associateBreed(){
	var boo = false;
	var ary = new Array();
	for(var key in choose_breeds){
		var flag = false;
		$("#associateBreedForm :checked").each(function(){
			if($(this).val() == choose_breeds[key]){
				flag = true;
			}
		});
		if(!flag){
			ary.push(choose_breeds[key]);
		}
	}
	
	if(($("#associateBreedForm :checked").size() + ary.length) > 0){
		var paramStr = $('#associateBreedForm').serialize();
		for(var i=0 ; i < ary.length ; i++){
			paramStr += "&breedId=" + ary[i];
		}
		$.ajax({
            cache: true,
            type: "POST",
            url:"getCategoryManage/associateCategoryAndBreed",
            data:paramStr,// 你的formid
            async: false,
            error: function(request) {
                tips("Connection error");
            },
            success: function(data) {
                if(data){
                	closeDiv('addQccount');
                	$("#associateBreedMsgInfo").html("关联成功！");
					$("#associateBreedMsg").show();
                	isClose = true;
                	//$("#addQccount",window.parent.document).hide();
		          //  window.parent.location.reload();
                }
            }
        });
		
	}else{
		//alert("请选择关联品种！");
		$("#associateBreedMsgInfo").html("请选择关联品种！");
		$("#associateBreedMsg").show();
		return false;
	}
}  

$("#associateBreedMsgBtn").click(function(){
	$("#associateBreedMsg").hide();
	if(isClose){
		isClose = false;
		//$("#down",window.parent.document).attr("src","getCategoryBy?classId="+$("#currentClassId").val()+"&categoryId="+$("#currentCategoryId").val()).ready();
		var categoryId = $("#currentCategoryId").val();
		var classId = $("#currentClassId").val();
		$("#associateCategoryId").val(categoryId);
		$("#associateClassId").val(classId);
		AjaxGetAssociateBreed("getBreedForm","associateBreedPageNo");
		$("#notAssociateClass").val(classId);
        $("#notAssociateCategory").val(categoryId);
        //ajax获取未关联的品种
        //AjaxGetData("queryBreedForm","associatePageNo");
		
	}
});

function deleteBreed(classId,categoryId,breedCategoryId){
	$("#breedCategoryId").val(breedCategoryId);
	$('#delCategoryBreedDiv').show();
	$('body').append(maskDiv);
	$('.bghui').css('height',$(document).height()-20);
	
	
} 

$("#delCategoryBreedBtn").click(function(){
	closeDiv('delCategoryBreedDiv');
	var breedCategoryId=$("#breedCategoryId").val();
	$.ajax({
		 type: "POST",
		 url: "getCategoryManage/deleteBreedByCategory",
		 data:   "breedCategoryId="+breedCategoryId,
		success: function(data){
			if(data){
				$("#associateBreedMsgInfo").html("取消关联成功！");
				$("#associateBreedMsg").show();
                isClose = true;
			}
			else{
			  $("#associateBreedMsgInfo").html("取消关联失败！");
				$("#associateBreedMsg").show();
                isClose = false;
				 }
				} 
			}); 
});

function closeDiv(id){
	$("#"+id).hide();
    $('.bghui').remove();
}
function enter_down(event){
	if(event.keyCode==13){
		stopDefault(event);
	}
}

 function stopDefault(e) {  
        //如果提供了事件对象，则这是一个非IE浏览器   
        if(e && e.preventDefault) {  
        　　//阻止默认浏览器动作(W3C)  
        　　e.preventDefault();  
        } else {  
        　　//IE中阻止函数器默认动作的方式   
        　　window.event.returnValue = false;   
        }  
        return false;  
    }  

//document.onkeydown = function() {
 
//        if((event.keyCode==13)||(event.keyCode==32))
//        {
//        event.keyCode=0;
//        event.returnValue=false;
//        }
//        }

function getCategoryBy(classId){
	
	var setting = {
			edit: {
				drag: {
					autoExpandTrigger: true,
					prev: dropPrev,
					inner: false,
					next: dropNext,
					//设置不能复制
					isCopy: false
				},
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false
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
				beforeDrag: beforeDrag,
				beforeDrop: beforeDrop,
				beforeDragOpen: beforeDragOpen,
				onRightClick: OnRightClickTree,
				onClick: zTreeOnClick,
				onDrag: onDrag,
				onDrop: onDrop,
				onExpand: onExpand
							
			}
		};
		
        $.ajax({
			async : false,
			cache : false,
			type : "POST",
			dataType : "json",
			url : "getCategoryManage/getCategoryTreeByClass?classId="+classId,// 类目树
			success : function(data) { // 请求成功后处理函数。
			  
				zNodes = data; // 把后台封装好的简单Json格式赋给zNodes
			if(data !="" && data.length>0){
				//update 2015.01.11
				var htmlStr="";
				htmlStr += "<form id='getBreedForm' action='getCategoryBy' method='post'><ul class='form-search'>"
                    htmlStr += "<input type='hidden' name='pageNo' id='associateBreedPageNo' /><input type='hidden' name='classId' id='associateClassId' value='"+classId+"' /><input type='hidden' name='categoryId' id='associateCategoryId'  />"
                    htmlStr += "<li><span>&nbsp;&nbsp;&nbsp;&nbsp;品名名称/别名：</span><input class='text text-2 mr10' type='text' name='name' id='associateBreedName' />"
                    htmlStr += "<span>品种编码：</span><input class='text text-2 mr10' type='text' name='code' id='associateBreedCode' />"
                    htmlStr += "<span>产地：</span><input class='text text-2 mr10' type='text' name='place' id='associateBreedPlace' /></li>"
                    htmlStr += "<@shiro.hasPermission name='类目管理-子目录-关联品种查询'><li><div align='center'><input type='button' class='btn-blue' value='查询' onclick=javascript:getCategoryByCondition('getBreedForm','associateBreedPageNo'); /></div></li></@shiro.hasPermission></ul></form>";
					$("#associateBreedFormDiv").html(htmlStr);
					//点击分类 未点击类目时categoryId为""
					var categoryId = $("#currentCategoryId").val();
					if(categoryId != null &&categoryId !="")
					   $("#associateCategoryId").val(categoryId);
					$("#associateClassId").val(classId);
					AjaxGetAssociateBreed("getBreedForm","associateBreedPageNo");
					
					if(categoryId != null &&categoryId !=""){
						$("#notAssociateClass").val(classId);
	                    $("#notAssociateCategory").val(categoryId);
	                  //ajax获取未关联的品种
	                  //  AjaxGetData("queryBreedForm","associatePageNo");
					}
					else{
					//获取该分类下的第一个类目
					$.ajax({
						async : false,
						cache : false,
						type : "POST",
						url : "getCategoryManage/getFirstCategoryByClass?classId="+classId,// 类目树
						success : function(data) {
							if(data != null){
								$("#currentCategoryId").val(data.id);//设置页面上的隐藏域-当前类目ID
								$("#notAssociateClass").val(classId);
			                    $("#notAssociateCategory").val(data.id);
			                    //ajax获取未关联的品种
			                    //AjaxGetData("queryBreedForm","associatePageNo");
							}
						}
					});
					}
				
			  }else{
				  $("#associateBreedFormDiv").html("");
				  $("#associateBreedData").html("");
				  
			  }
			}
		}); 
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		
	}
</script>