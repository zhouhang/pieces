<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>活动管理</title>
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.min.js"></script>
</head>
<body>
<#include "home/top.ftl" />  
<#import 'macro.ftl' as tools>
<div class="wapper">
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">活动管理</h1>
            <form id="conditionForm" action="/bossWxActivity" method="get">
                <ul class="store-search">
                    <li><span>名称：</span><input name="name" value="${(page.params.wxActivitySearchDto.name)!''}" class="text-store text-7" type="text" />
                    <span>类型：</span><select name="type" class="text-store text-7">
                        <option value="">全部</option>
                    	<#if wxActivityTypeMap??>
                   			<#list wxActivityTypeMap?keys as key>
                    			<option value="${key!'' }" <#if page.params.wxActivitySearchDto.type == key>selected</#if>>${wxActivityTypeMap[key]!'' }</option>
                    		</#list>
                   		</#if>	
                    </select>
                    <span>创建时间：</span><input id="wdate1" type="text" class="text-store text-7 mr10 Wdate" name="startWxActivityDate" value="${page.params.wxActivitySearchDto.startWxActivityDate!''}" />至<input id="wdate2" type="text" class="text-store text-7 ml10 Wdate" name="endWxActivityDate" value="${page.params.wxActivitySearchDto.endWxActivityDate!''}" />
                    <span></span><input type="submit" class="btn-blue" id="btn-blue" value="查询" /></li>
                </ul>
            </form>
            <div class="use-item1" style=" margin-top:20px;">
	            <span class="btn-add mb10">
					<a href="javascript:addWxActivity();">添加活动</a>
				</span>
                <table id="wxActivityTable" class="table-store" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="50">编号</th>
                        <th width="100">名称</th>
                        <th width="100">类型</th>
                        <th width="100">简介</th>
                        <th width="100">文章路径</th>
                        <th width="100">图片路径</th>
                        <th width="50">排序号</th>
                        <th width="150">创建时间</th>
                        <th width="150">更新时间</th>
                        <th width="50">创建人</th>
                        <th width="50">修改人</th>
                        <th width="150">操作</th>
                    </tr>
                    <#if (page.results?size>0)>
	                    <#list page.results as wxActivity>
	                    	<tr>
	                    		<td>${wxActivity.activityId!''}</td>
	                    		<td>${wxActivity.name!''}</td>
	                    		<td>
	                    			<#if wxActivity.type == 0>
		                    			活动
									<#elseif wxActivity.type == 1> 
										卖药材介绍
									<#elseif wxActivity.type == 2> 
										中药养生
									</#if>	
	                    		</td>
	                    		<#if (wxActivity.memo?length > 6)>
                    				<td class="opr-btn"><span class="operate-1 operate-a">${wxActivity.memo?substring(0,6)} …<div class="tips tip_store" align="left"><span class="sj"></span>${wxActivity.memo!''}</div></span></td>
                    			<#else>
                    				<td>${wxActivity.memo!''}</td>
                    			</#if>
                    			<#if (wxActivity.url?length > 6)>
                    				<td class="opr-btn"><span class="operate-1 operate-a">${wxActivity.url?substring(0,6)} …<div class="tips tip_store" align="left"><span class="sj"></span>${wxActivity.url!''}</div></span></td>
                    			<#else>
                    				<td>${wxActivity.url!''}</td>
                    			</#if>
                    			<td><a href="javascript:showImg('${wxActivity.name!''}','${wxActivity.picUrl!''}')"><img src=${wxActivity.picUrl!''} /></a></td>
	                    		<td>${wxActivity.sortno!''}</td>
	                    		<td>${(wxActivity.createTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
	                    		<td>${(wxActivity.updateTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
	                    		<td>${wxActivity.creater!''}</td>
	                    		<td>${wxActivity.updater!''}</td>
	                    		<td class="opr-btn">
									<span class="operate-2" title="修改" onclick="javascript:updateWxActivity(${wxActivity.activityId});"> </span>
									<span class="operate-4" title="删除" onclick="javascript:deleteWxActivity(${wxActivity.activityId});"></span>
								</td>
	                    	</tr>
	                    </#list>
	                    <#else>
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
<!-- 更新活动  -->
<div id="wxActivityDialog" title="更新活动" style="display: none;">
	<div class="box">
		<form id="wxActivityForm" action="" method="post">
			<ul class="form-1 temp">
				<li><label><i class="red">*</i> 名称：</label>
					
						<input class="text-store text-1" type="hidden" id="activityId" name="activityId" />
						<input class="text-store text-1" type="text" id="name" name="name" />
				
				</li>
				<li><label><i class="red">*</i> 类型：</label>
					
						<select id="type" name="type" class="text-store">
	                    	<#if wxActivityTypeMap??>
	                   			<#list wxActivityTypeMap?keys as key>
	                    			<option value="${key!'' }">${wxActivityTypeMap[key]!'' }</option>
	                    		</#list>
	                   		</#if>	
	                    </select>
					
				</li>
				<li><label> 简介：</label>
					
						 <textarea id="memo" name="memo" class="text-store" style="width:218px;resize:none;"></textarea>
					
				</li>
				<li><label><i class="red">*</i> 文字路径：</label>
					
						 <input class="text-store text-1" type="text" id="url" name="url" />
					
				</li>
				<li><label><i class="red">*</i> 图片路径：</label>
					
						 <input class="text-store text-1" type="text" id="picUrl" name="picUrl" />
					
				</li>
				<li><label><i class="red">*</i> 排序号：</label>
					
						 <input class="text-store text-1" type="text" id="sortno" name="sortno" />
					
				</li>
				<li class="btn_cen">
                    <input type="submit" class="btn-blue" id="btn-submit" value="提交" />
                </li>
			</ul>
		</form>
	</div>
</div>
<!-- 更新活动 over  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
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
    var wxActivity = $("#wxActivityForm").Validform({
	    tiptype:4,
	    ajaxPost:true,
	    showAllError:true,
	    beforeSubmit:function(curform){
    		var isok = true;
    		var activityId = $("#activityId").val();
			var type = $("#type").val();
	    	var sortno = $("#sortno").val();
	    	$.ajax({
				async : false,
				cache : false,
				type : "POST",
				data : {"activityId":activityId,"type":type,"sortno":sortno},
				dataType : "json",
				url : "/bossWxActivity/findByCondition",
				success : function(data) {
					var ok = data.ok;
					var obj = data.obj;
					if(ok==true){
						tips("当前类型下排序号不唯一！");
						$("#sortno").val("");
						wxActivity.check();
						isok = false;
					}else{
						$('#btn-submit').val('提交中...').attr('disabled','disabled');
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					tips('验证失败！');
					isok = false;
				}
			});
			return isok;
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
    wxActivity.addRule([
	    {
	        ele:"#name",
	        datatype:"*2-200",
	        nullmsg:"请输入名称！",
	        errormsg:"请填写2到16位任意字符！"
	    },
	    {
	        ele:"#type",
	        datatype:"n1-2",
	        nullmsg:"请选择类型！",
	        errormsg:"请选择正确的类型！"
	    },{
	        ele:"#memo",
	        datatype:"*0-500",
	        nullmsg:"请输入简介！",
	        errormsg:"请填写0到500位任意字符！"
	    },
	    {
	        ele:"#url",
	        datatype:"url,*0-255",
			nullmsg:"请输入文字路径！",
	        errormsg:"请填写网址！"
	    },
	    {
	        ele:"#picUrl",
	        datatype:"url,*0-255",
	        nullmsg:"请输入图片路径！",
	        errormsg:"请填写网址！"
	    },
	    {
	        ele:"#sortno",
	        datatype:"n1-5",
	        nullmsg:"请输入排序号！",
	        errormsg:"请填写1到5位数字！"
	    }
	]);
    //添加活动
    function addWxActivity(){
    	formFlag = true;
		resetDialogForm();
    	$(".ui-dialog-title").attr("title","添加活动");
    	$("#wxActivityForm").attr("action","/bossWxActivity/addWxActivity");
    	$("#wxActivityDialog").dialog({
	      	autoOpen : true,
			modal : true,
			width : 600,
			resizable : false
	 	});
	 	$(".ui-dialog-title").text("添加活动");
    };
    //修改活动
    function updateWxActivity(activityId){
    	$.ajax({
				async : false,
				cache : false,
				type : "GET",
				data : {"activityId":activityId},
				dataType : "json",
				url : "/bossWxActivity/getWxActivityById",
				success : function(data) {
					var ok = data.ok;
					if(ok==true){
						var obj = data.obj;
						formFlag = false;
						resetDialogForm();
						//修改弹窗
						$("#wxActivityDialog").attr("title","修改活动");
    					$("#wxActivityForm").attr("action","/bossWxActivity/updateWxActivity");
						//填充数据
						$.each(obj,function(key,value){
					 		$("#"+key).val(value);
					 	});
						//显示弹窗
						$("#wxActivityDialog").dialog({
					      	autoOpen : true,
							modal : true,
							width : 600,
							resizable : false
					 	});
					 	$(".ui-dialog-title").text("修改活动");
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
    //删除活动
    function deleteWxActivity(activityId){
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
							data : {"activityId":activityId},
							dataType : "json",
							url : "/bossWxActivity/deleteWxActivity",
							success : function(data) {
								var ok = data.ok;
								var msg = data.msg;
								if(ok==true){
									var wxActivityTrs = $("#wxActivityTable tr");
									$.each(wxActivityTrs,function(idx,obj){
										var wxActivityTdId = $(obj).find('td:eq(0)').text();
										if(wxActivityTdId==activityId){
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
    	wxActivity.resetForm();
    	wxActivity.resetStatus();
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