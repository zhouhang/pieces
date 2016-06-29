<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <title>中药材电子商务管理系统</title>
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" >
	
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.min.js"></script>
</head>
<body>
<#include "home/top.ftl" />  
<div class="wapper">
<#include "home/left.ftl" /> 
<#import 'macro.ftl' as tools>
<!-- pageCenter start -->
<div class="main">
	<div class="page-main">
		<h1 class="title1">挂牌查询</h1>
		<form action="/getPlatformListingManage" method="get" id="conditionForm">
			<ul class="store-search ac-search">
				<li><span>挂牌编号：</span>
					<input name="listingid" value="${page.params.homePageListingDto.listingid!''}" class="text-store text-4 mr10" type="text" /> 
					<span>类型：</span>
					<select class="text-store text text-2 register-text mr10" name ="type">
					<option value="">--请选择--</option>
					<#list types?keys as key>
						<option value="${key}" <#if page.params.homePageListingDto.type == key>selected</#if>>${types[key]}</option>
					</#list>
					</select>
					<input type="button" id="subCForm" class="btn-blue mr10" value="查询" />
					<input type="button" onclick="clearForm('conditionForm')" class="btn-hui" value="清空" /></li>
			</ul>
		</form>
		
		<div class="use-item1 mt25">
			<span class="btn-add mb10"><a href="#">添加</a> </span>
			<table id="busiListingTable" class="table-store" width="100%"
				cellpadding="1" cellspacing="1">
				<tr>
					<th width="100">挂牌编号</th>
					<th width="100">挂牌用户</th>
					<th width="100">类型</th>
					<th width="100">排序</th>
					<th >图片地址</th>
					<th width="150">图片描述</th>
					<th width="150">操作</th>
				</tr>
			<#if (page.results?size>0)> 
				<#list page.results as busiListing>
				<tr id="${busiListing.listing_Id!-1}">
					<td>${busiListing.listingid!''}</td> 
					<td><a href="/getMemberManage/getMemberByUserName?memberName=${busiListing.username!''}">${busiListing.username!''}</a></td>
					<td>${types[busiListing.type?string]!''}</td>
					<td>${busiListing.sortno!''}</td>
					<td>${busiListing.picurl!''}</td>
					<#if (busiListing.alt?length > 6)>
                    	<td class="opr-btn"><span class="operate-1 operate-a">${busiListing.alt?substring(0,6)} …<div class="tips tip_store" align="left"><span class="sj"></span>${busiListing.alt!''}</div></span></td>
                    <#else>
                    	<td>${busiListing.alt!''}</td>
                    </#if>
					<td class="opr-btn"><span class="operate-2"
						onclick="showAlertDialog('${busiListing.listing_Id!-1}');"></span>
						<span class="operate-4" onclick="dropListing('${busiListing.listing_Id!-1}');"></span></td>
				</tr>
				</#list> <#else>
				<tr>
					<td colspan="10">没有数据!</td>
				</tr>
				</#if>
			</table>
		</div>
		<@tools.pages page=page form="conditionForm"/>
	</div>
</div>
<!-- pageCenter over -->
</div>
<!-- 弹层  -->
<!-- 添加  -->
<div id="addPlatformListingDialog" class="main"  title="添加首页挂牌数据" style="display:none;">
		<form id="addPlatformListingForm" action="/getPlatformListingManage/addPlatformListing" method="post">
			<ul class="form-1">
				<li><label><i class="red">*</i> 挂牌编号：</label>
					<p>
						<input class="text-store text-1 register-text"
							datatype="listingidValid"
							ajaxurl="/getPlatformListingManage/listingidIsHaved"
							nullmsg="请输入挂牌编号！" errormsg="挂牌编号是15位数字" type="text"
							id="addListingid" name="listingid" value="" />
					</p>
				</li>
				<li><label><i class="red">*</i> 类型：</label>
					<p>
						<select class="text-store text text-1 register-text" datatype="*"
						onchange="changetype(this.options[this.options.selectedIndex].value,1)"
							nullmsg="请选择类型！" errormsg="" id="addtype" name="type"><option
								value="">--请选择--</option> <#list types?keys as key>
							<option value="${key}">${types[key]!''}</option> </#list>
						</select>
					</p>
				</li>
				<li><label><i class="red">*</i> 排序：</label>
					<p>
						<input class="text-store text-1 register-text" value="" id="addortno" datatype="n1-2"
							nullmsg="排序不能为空" errormsg="排序输入1-2位数字" name="sortno"
							type="text" />
					</p>
				</li>
				<li class="isshowcontent"><label><i class="red">*</i> 图片描述：</label>
					<p>
						<input class="text-store text-1 register-text" value="" datatype="*1-128"
							nullmsg="请输入图片描述！" errormsg="图片描述不能多于128个字符！" id="addalt"
							name="alt" type="text" />
					</p>
				</li>
				<li class="isshowcontent"><label><i class="red">*</i> 图片上传：</label>
					<p class="relative">
					 <span class="storeimg">
                        <img id="addpic" style="cursor:pointer; width:120px; height:120px;" width="120" src="${RESOURCE_IMG}/images/jzt-boss/add.jpg" />
                    	<span class="Validform_checktip"></span>
                    </span>
                    <span class="" style="position:absolute; top:0; left:0;">
						<input type="file" id="addfile" name="upload" class="" style="width:120px; cursor: pointer; height:120px; opacity:0; filter:alpha(opacity:0);"  title="上传新图片" value="" />
						<input type="hidden" id="addpicurl" datatype="*" nullmsg="图片不能为空" name="picurl"/>
					</span>
					</p>
				</li>
			</ul>
		</form>
</div>
<!-- 添加 over  -->
<!-- 修改  -->
<div class="main" id="alterPlatformListingDialog" title="修改首页挂牌数据"  style="display:none;">
		<form id="alterPlatformListingForm" action="/getPlatformListingManage/alterPlatformListing"
			method="post">
			<ul class="form-1">
				<li><label><i class="red">*</i> 挂牌编号：</label>
					<p>
						<span id="alterListingid"></span>
					</p> <input type="hidden" name="listing_Id" id="hiddenlisting_Id" /></li>
				<li><label><i class="red">*</i> 类型：</label>
					<p>
						<select class="text-store text text-1 register-text" datatype="*"
						onchange="changetype(this.options[this.options.selectedIndex].value,2)"
							nullmsg="请选择类型！" 
							errormsg="" id="altertype" name="type"><option
								value="">--请选择--</option> 
							<#list types?keys as key>
								<option value="${key}">${types[key]!''}</option> 
							</#list>
						</select>
					</p>
					</li>
				<li><label><i class="red">*</i> 排序：</label>
					<p>
						<input class="text-store text-1 register-text" value="" id="altersortno"
							datatype="n1-2" nullmsg="排序不能为空" errormsg="排序为一到两位数字" name="sortno"
							type="text" />
					</p>
					</li>
					<li class="isshowcontent1"><label><i class="red">*</i> 图片描述：</label>
					<p>
						<input class="text-store text-1 register-text" value="" datatype="*1-128"
							nullmsg="请输入图片描述！" errormsg="图片描述不能多于128个字符！" id="alteralt"
							name="alt" type="text" />
					</p>
					</li>
					<li class="isshowcontent1"><label><i class="red">*</i> 图片上传：</label>
					<p class="relative">
					<span class="storeimg">
                        <img id="alterpic" style="cursor:pointer; width:120px; height:120px;" width="120" height="120" src="${RESOURCE_IMG}/images/jzt-boss/add.jpg" />
                    </span>
                    <span class="" style="position:absolute; top:0; left:0;">
						<input type="file" id="alterfile" name="upload" class="" style="width:120px; cursor: pointer; height:120px; opacity:0; filter:alpha(opacity:0);"  title="上传新图片" value="" />
						<input type="hidden" id="alterpicurl" datatype="*" nullmsg="图片不能为空" name="picurl" style="width:120px; cursor: pointer; height:120px; opacity:0; filter:alpha(opacity:0);"/>
					</span>
					</p>
					</li>
			</ul>
		</form>
</div>
<!-- 修改 over  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/imgView/jquery.imageView.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/ajaxfileupload.js"></script>
<script>
///////////////////////////////////////////////////// 按钮操作专区 START  /////////////////////////////////////////////////////
//添加首页挂牌信息
$('.btn-add a').click(function(){
   $("#addPlatformListingDialog").dialog({
   		autoOpen : true,
		modal : true,
		width : 794,
		resizable : false,
		buttons :[ 
		    {
		    	text:"确定",
		    	id: "addPlatformListingSubmit",
		    	click: function(){
		    		$("#addPlatformListingForm").submit();
		    	}
		    },
		    {
		    	text:"重置",
		    	id: "addPlatformListingReset",
		    	click: function(){
		    		$("#addpic").attr('src','${RESOURCE_IMG}/images/jzt-boss/add.jpg');
   				$("#addpicurl").val('');
		    		addform.resetForm();
		    	}
		    }
		],
		close: function(event, ui) { 
			addform.resetForm();
		}
   });
   removeCloseClass();
});

//修改首页挂牌信息
function showAlertDialog(listing_Id){
	$("#hiddenlisting_Id").val(listing_Id);
	$.post("/getPlatformListingManage/findPlatformListingById",{
		"listing_Id":listing_Id
	},function(data){
		$("#alterListingid").html(data.listingid);
		$("#altertype").val(data.type);
		$("#altersortno").val(data.sortno);
		if(data.picurl!=null && data.picurl!='') $("#alterpic").attr('src',data.picurl);
		$("#alterpicurl").val(data.picurl);
		$("#alteralt").val(data.alt);
		var s = data.type;
		if(s==1 || s==4){
    		$(".isshowcontent1").hide();
    		alertform.ignore("#alteralt,#alterpicurl");
    	}else{
    		$(".isshowcontent1").show();
    		alertform.unignore("#alteralt,#alterpicurl");
   		 }
	});
     $("#alterPlatformListingDialog").dialog({
    	autoOpen : true,
		modal : true,
		width : 794,
		resizable : false,
    	buttons :[ 
		    {
		    	text:"确定",
		    	id: "alterPlatformListingSubmit",
		    	click: function(){
		    		$("#alterPlatformListingForm").submit();
		    	}
		    },
		    {
		    	text:"取消",
		    	id: "alterPlatformListingReset",
		    	click: function(){
		    		$("#alterPlatformListingDialog").dialog("close");
		    		alertform.resetForm();
		    	}
		    }
		],
		close: function(event, ui) { 
			alertform.resetForm();
		}
	});
	removeCloseClass();    
}

//删除首页挂牌信息
function dropListing(listing_Id){
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
						type : 'GET',
						url : '/getPlatformListingManage/dropHomePageListing?listing_Id='+listing_Id,
						success : function(data) {
							var ok = data;
							if(ok=='y'){
								$('#'+listing_Id).find('td:last').html('');
								tips('操作成功！');
								$("#conditionForm").submit();
							}else{
								tips(data);
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown){
							tips('操作失败！');
						}
					}); 
                 }
               }
            }]
    });
}

//提交表单
$("#subCForm").click(function(){
     $("#conditionForm").submit();
});
///////////////////////////////////////////////////// 按钮操作专区END  /////////////////////////////////////////////////////


//_(:зゝ∠)_ 谁写的 站出来 保证 打不死你  我也不知道这行干嘛的
function removeCloseClass(){
	$('.ui-button-icon-primary').attr('class','ui-button-icon-primary ui-icon-closethick');
} 
//select 切换显示/隐藏部分字段 add by Mr.song 2015.4.16 12:09
   function  changetype(s,type){
   	if(type==1){
    	if(s==1 || s==4){
    		$(".isshowcontent").hide();
    		addform.ignore("#addalt,#addpicurl");
    	}else{
    		$(".isshowcontent").show();
    		addform.unignore("#addalt,#addpicurl");
    	}
    }else if(type==2){
	    if(s==1 || s==4){
    		$(".isshowcontent1").hide();
    		alertform.ignore("#alteralt,#alterpicurl");
    	}else{
    		$(".isshowcontent1").show();
    		alertform.unignore("#alteralt,#alterpicurl");
   		 }
    }
   }
   
   
///////////////////////////////////////////////////// 异步上传图片专区 START /////////////////////////////////////////////////////
	    
		//add上传图片
		$('#addfile').live('change',function(){
			var fileId = $(this).attr('id');
			var pic = $(this).val();
			if(!/.(gif|jpg|jpeg|png|bmp)$/.test(pic.toLowerCase())){
				tips("请选择图片!");
				return false;
			}
			var storeimg = $('#'+fileId).parent().parent().find(".storeimg img");
			$(storeimg).attr('src','${RESOURCE_IMG}/images/loading.gif');
			$.ajaxFileUpload(
				{
					url:'/getPlatformListingManage/uploadpic', 
					secureuri:false,
					fileElementId:fileId,
					dataType: 'json',
					success: function (data, status)
					{
						
						if(data.status.code==0){
							var imgSrc = data.con.path+data.con.dateDir+"/"+data.con.filename;
							$("#addpicurl").val(imgSrc);
							$(storeimg).attr('src', imgSrc);
						}else{
							tips("上传失败！");
							return false;
						}
					},
					error: function (data, status, e)
					{
						tips('操作失败！');
					}
			  });
		});
		//alter上传图片
		$("#alterfile").live('change',function(){
			var fileId = $(this).attr('id');
			var pic = $(this).val();
			if(!/.(gif|jpg|jpeg|png|bmp)$/.test(pic.toLowerCase())){
				tips("请选择图片!");
				return false;
			}
			var storeimg = $('#'+fileId).parent().parent().find(".storeimg img");
			$(storeimg).attr('src','${RESOURCE_IMG}/images/loading.gif');
			$.ajaxFileUpload(
				{
					url:'/getPlatformListingManage/uploadpic', 
					secureuri:false,
					fileElementId:fileId,
					dataType: 'json',
					success: function (data, status)
					{
						if(data.status.code==0){
							var imgSrc = data.con.path+data.con.dateDir+"/"+data.con.filename;
							$("#alterpicurl").val(imgSrc);
							$(storeimg).attr('src', imgSrc);
						}else{
							tips("上传失败！");
							return false;
						}
					},
					error: function (data, status, e)
					{
						tips('操作失败！');
					}
			  });
		});
///////////////////////////////////////////////////// 异步上传图片专区 END  /////////////////////////////////////////////////////
    
///////////////////////////////////////////////////// 表单验证START  /////////////////////////////////////////////////////
	/* 初始化validform验证 */
	var addform= $("#addPlatformListingForm").Validform({
		btnSubmit:"#addPlatformListingSubmit",
		btnReset:"#addPlatformListingReset",
		tiptype:4,
		showAllError:true,
		ajaxPost:true,
		datatype:{
			"listingidValid":/^[0-9]{15}$/
		},
		callback:function(data){
			if(data.status=="yes"){
				$("#addPlatformListingDialog").dialog("close");
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
			}
		}
	});
	var alertform= $("#alterPlatformListingForm").Validform({
		btnSubmit:"#addPlatformListingSubmit",
		btnReset:"#addPlatformListingReset",
		tiptype:4,
		showAllError:true,
		ajaxPost:true,
		callback:function(data){
			if(data.status=="yes"){
				$("#alterPlatformListingDialog").dialog("close");
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
///////////////////////////////////////////////////// 表单验证END  /////////////////////////////////////////////////////

///////////////////////////////////////////////////// 提示START  /////////////////////////////////////////////////////
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
///////////////////////////////////////////////////// 提示START  /////////////////////////////////////////////////////

	$('#1').live('click',function(){
		disappear();
		$(".bghui").remove();
		$("#conditionForm").submit();
	})
	
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
</script>
</body>
</html>