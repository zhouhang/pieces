<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>广告管理</title>
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS}/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.min.js"></script>
</head>
<body>
<#include "home/top.ftl" />  
<#import 'macro.ftl' as tools>
<#include "home/left.ftl" /> 
	<!-- pageCenter start -->
	<div class="main">
		<div class="page-main">
			<h1 class="title1">广告管理</h1>
			<form id="conditionForm" action="/bossWxAd" method="get">
				<ul class="store-search">
					<li>
					<span>类型：</span><select
						name="adType" class="text-store text-7">
							<option value="">全部</option> 
							<#if wxAdTypeMap??> 
							<#list wxAdTypeMap?keys as key>
							<option value="${key!'' }"<#if page.params.wxAdSearchDto.adType == key>selected</#if>>${wxAdTypeMap[key]!'' }</option> 
							</#list> 
							</#if>
					</select>
					
					
					
					<span>位置名称：</span><input name="adPostionName"
						value="${(page.params.wxAdSearchDto.adPostionName)!''}"
						class="text-store text-7" type="text" />  
					
					<span>标题：</span><input name="adTitle"
						value="${(page.params.wxAdSearchDto.adTitle)!''}"
						class="text-store text-7" type="text" />  

					<span>内容：</span><input name="adMemo"
						value="${(page.params.wxAdSearchDto.adMemo)!''}"
						class="text-store text-7" type="text" />
					</li>
					<li>
					<span>键接地址：</span><input name="adUrl"
						value="${(page.params.wxAdSearchDto.adUrl)!''}"
						class="text-store text-7" type="text" />  
					<span>创建时间：</span><input id="wdate1" type="text"
						class="text-store text-7 mr10 Wdate" name="startWxAdDate"
						value="${(page.params.wxAdSearchDto.startWxAdDate)!''}" />
						至<input id="wdate2" type="text"
						class="text-store text-7 ml10 Wdate" name="endWxAdDate"
						value="${(page.params.wxAdSearchDto.endWxAdDate)!''}" /> <span></span>

						<input type="submit" class="btn-blue" id="btn-blue" value="查询" />

					</li>
				</ul>
			</form>
			<div class="use-item1" style="margin-top: 20px;">
				<span class="btn-add mb10"> <a href="javascript:addWxAd();">添加广告</a>
				</span>
				<table id="wxAdTable" class="table-store" width="100%"
					cellpadding="1" cellspacing="1">
					<tr>
						<th width="50">编号</th>
						<th width="100">类型</th>
						<th width="100">位置名称</th>
						<th width="100">标题</th>
						<th width="100">内容</th>
						<th width="100">链接地址</th>
						<th width="150">创建时间</th>
						<th width="150">更新时间</th>
						<th width="50">创建人</th>
						<th width="50">修改人</th>
						<th width="150">操作</th>
					</tr>
					<#if (page.results?size>0)> <#list page.results as wxAd>
					<tr>
						<td>${wxAd.adId!''}</td>
						<td><#if wxAd.adType == 0> 文字 <#elseif wxAd.adType == 1> 图片
							<#elseif wxAd.adType == 2> 动画 <#elseif wxAd.adType == 3> 声音
							<#elseif wxAd.adType == 4> 视频 </#if></td>
						<td>${wxAd.adPostionName!''}</td>
						<td>${wxAd.adTitle!''}</td> <#if (wxAd.adMemo?length > 6)>
						<td class="opr-btn"><span class="operate-1 operate-a">${wxAd.adMemo?substring(0,6)}…
								<div class="tips tip_store" align="left">
									<span class="sj"></span>${wxAd.adMemo!''}</div>
						</span></td> <#else>
						<td>${wxAd.adMemo!''}</td> </#if> <#if (wxAd.adUrl?length > 6)>
						<td class="opr-btn"><span class="operate-1 operate-a"><a
								href="${wxAd.adUrl!''}" target="_blank">${wxAd.adUrl?substring(0,6)}…</a>
								<div class="tips tip_store" align="left">
									<span class="sj"></span><a href="${wxAd.adUrl!''}" target="_blank">${wxAd.adUrl!''}</a>
								</div></span></td> <#else>
						<td><a href="${wxAd.adUrl!''}" target="_blank">${wxAd.adUrl!''}</a></td> </#if>
						<!-- <td><a
							href="javascript:showImg('${wxAd.name!''}','${wxAd.picUrl!''}')"><img
								src=${wxAd.picUrl!''} /></a></td> -->
						<td>${(wxAd.createTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
						<td>${(wxAd.updateTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
						<td>${wxAd.creater!''}</td>
						<td>${wxAd.updater!''}</td>
						<td class="opr-btn"><span class="operate-2" title="修改"
							onclick="javascript:updateWxAd(${wxAd.adId});"> </span> <span
							class="operate-4" title="删除"
							onclick="javascript:deleteWxAd(${wxAd.adId});"></span></td>
					</tr>
					</#list> <#else>
					<tr>
						<td colspan="12">没有数据!</td>
					</tr>
					</#if>
				</table>
			</div>
			<@tools.pages page=page form="conditionForm"/>
		</div>
	</div>
	<!-- pageCenter over -->
	<!-- 弹层  -->
	<!-- 更新广告  -->
<style>
	#wxAdForm ul li{
		white-space: nowrap;
	}
</style>
	<div id="wxAdDialog" title="更新广告" style="display: none;">
		<div class="box">
			<form id="wxAdForm" action="" method="post">
				<ul class="form-1">
					<li><label><i class="red">*</i> 类型：</label>
						<p>
						<input class="text-store text-1" type="hidden" id="adId"
								name="adId" /> 
							<select id="adType" name="adType" class="text-store text-1">
								<#if wxAdTypeMap??> <#list wxAdTypeMap?keys as key>
								<option value="${key!'' }">${wxAdTypeMap[key]!'' }</option>
								</#list>
								</#if>
							</select>
						</p></li>				
					<li><label><i class="red">*</i> 位置名称：</label>
						<p>
						   <input class="text-store text-1" type="text" id="adPostionName" name="adPostionName" />
						</p></li>				
					<li><label><i class="red">*</i> 标题：</label>
						<p>
						<input class="text-store text-1" type="text"
								id="adTitle" name="adTitle" />
						</p></li>
					<li><label><i class="red">*</i> 内容：</label>
						<p>
						<input class="text-store text-1" type="text"
								id="adMemo" name="adMemo" />
						</p></li>
						
					<li><label><i class="red">*</i> 链接地址：</label>
						<p>
							<input class="text-store text-1" type="text"
								id="adUrl" name="adUrl" />
						</p></li>
					<li class="btn_cen"><input type="submit" class="btn-blue"
						id="btn-submit" value="提交" /></li>
				</ul>
			</form>
		</div>
	</div>
	<!-- 更新广告 over  -->
	<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
	<script type="text/javascript"
		src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
	<script type="text/javascript"
		src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css"
		href="${RESOURCE_CSS}/js/Validform/css/style.css" />
	<script type="text/javascript"
		src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
	<!-- 图片上传插件start -->
	<script type="text/javascript"
		src="${RESOURCE_JS}/js/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="${RESOURCE_JS}/js/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
	<script type="text/javascript"
		src="${RESOURCE_JS}/js/jQuery-File-Upload/js/jquery.fileupload.js"></script>
	<!-- 图片上传插件end -->
	<script>
	//日期控件
	$('#wdate1').click(function(){
		WdatePicker({
			startDate:'%y/%M/%d',
			dateFmt:'yyyy/MM/dd',
			maxDate:'#F{$dp.$D(\'wdate2\',{d:-1});}',
			readOnly:true
		});
	});
	$('#wdate2').click(function(){
		WdatePicker({
			startDate:'%y/%M/%d',
			dateFmt:'yyyy/MM/dd',
			minDate:'#F{$dp.$D(\'wdate1\',{d:1});}',
			readOnly:true
		});
	});
	
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
    var formFlag = true;//表单类型（true：添加，flase：修改）
    var wxAd = $("#wxAdForm").Validform({
	    tiptype:4,
	    ajaxPost:true,
	    showAllError:true,
	    beforeSubmit:function(curform){
						$('#btn-submit').val('提交中...').attr('disabled','disabled');
	    },
	    callback:function(data){
	    	$('#btn-submit').val('提交').removeAttr('disabled');
	    	var status = data.status;
	    	var str = data.info;
	    	if(status){
	    		bghui();
				Alert({
			            str: str,
			            buttons:[{
			                name:'确定',
			                classname:'btn-blue',
			                ev:{click:function(data){
			                	 disappear();
		                         $(".bghui").remove();
		                         $("#conditionForm").submit();
		                     }
			               }
			            }]
			    });
	    	}else{
	    		tips(str);
	    	}
	    }
	});
    wxAd.addRule([
	    {
	        ele:"#adType",
	        nullmsg:"请选择类型！"
	    },
	    {
	        ele:"#adPostionName",
	        datatype:"*1-200",
	        nullmsg:"请输入位置名称！",
	        errormsg:"请填写小于200位的任意字符！"
	    },
	    {
	        ele:"#adTitle",
	        datatype:"*1-200",
	        nullmsg:"请输入标题！",
	        errormsg:"请填写0到200位任意字符！"
	    },
	    {
	        ele:"#adMemo",
	        datatype:"*1-1000",
			nullmsg:"请输入内容！",
	        errormsg:"请填写0到1000位任意字符！"
	    },
	    {
	        ele:"#adUrl",
	        datatype:"url,*0-255",
	        nullmsg:"请输入网址！",
	        errormsg:"请填写正确的网址！"
	    }
	]);
    //添加广告
    function addWxAd(){
    	formFlag = true;
		resetDialogForm();
    	$(".ui-dialog-title").attr("title","添加广告");
    	$("#wxAdForm").attr("action","/bossWxAd/addWxAd");
    	$("#wxAdDialog").dialog({
	      	autoOpen : true,
			modal : true,
			width : 600,
			resizable : false
	 	});
	 	$(".ui-dialog-title").text("添加广告");
    };
    //修改广告
    function updateWxAd(adId){
    	$.ajax({
				async : false,
				cache : false,
				type : "GET",
				data : {"adId":adId},
				dataType : "json",
				url : "/bossWxAd/getWxAdById",
				success : function(data) {
					var ok = data.ok;
					if(ok==true){
						var obj = data.obj;
						formFlag = false;
						resetDialogForm();
						//修改弹窗
						$("#wxAdDialog").attr("title","修改广告");
    					$("#wxAdForm").attr("action","/bossWxAd/updateWxAd");
						//填充数据
						$.each(obj,function(key,value){
					 		$("#"+key).val(value);
					 	});
						//显示弹窗
						$("#wxAdDialog").dialog({
					      	autoOpen : true,
							modal : true,
							width : 600,
							resizable : false
					 	});
					 	$(".ui-dialog-title").text("修改广告");
					}else{
						var msg = data.msg;
						tips(msg);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					tips('操作失败！');
				}
			});
    };
    //删除广告
    function deleteWxAd(adId){
    	bghui();
		Alert({
	            str: '确定删除吗？',
	            buttons:[{
	                name:'确定',
	                classname:'btn-blue',
	                ev:{click:function(data){
	                	 disappear();
                         $(".bghui").remove();
                         $.ajax({
							async : false,
							cache : false,
							type : "POST",
							data : {"adId":adId},
							dataType : "json",
							url : "/bossWxAd/deleteWxAd",
							success : function(data) {
								var ok = data.ok;
								var msg = data.msg;
								if(ok==true){
									var wxAdTrs = $("#wxAdTable tr");
									$.each(wxAdTrs,function(idx,obj){
										var wxAdTdId = $(obj).find('td:eq(0)').text();
										if(wxAdTdId==adId){
											$(obj).remove();
											return;
										}
									});
								}
								tips(msg);
							},
							error : function(XMLHttpRequest, textStatus, errorThrown){
								tips('操作失败！');
							}
						});
                     }
	               }
	            }]
	    });
    };
    //重置弹窗表单
    function resetDialogForm(){
    	wxAd.resetForm();
    	wxAd.resetStatus();
    	var formCheckTips = $("span.Validform_checktip")
    	$.each(formCheckTips,function(idx,obj){
			$(obj).text("");
		});
    };
    //查看图片
    function showImg(title,imgUrl){
    	var img = new Image();
    	img.src = imgUrl;
    	//alert(img.width+"/"+img.height);
    	var imgWidth = img.width;
    	if(imgWidth>0){
    		$(img).dialog({
		      	autoOpen : true,
				modal : true,
				width : imgWidth+25,
				title:title,
				resizable : false
		 	});
    	}else{
    		tips('图片加载失败！');
    	}
    };
</script>
</body>
</html>