<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>入仓信息管理</title>
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/autocomplete-styles.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.min.js"></script>
</head>
<body>
<#include "home/top.ftl" />  
<#import 'macro.ftl' as tools>
<#include "home/left.ftl" /> 
<div class="wapper">
<!-- pageCenter start -->
    <div class="main">
    	<div class="page-main">
    		<h1 class="title1">入仓信息管理</h1>
            <form action="/infoWarehous" method="POST" id="infoWarehouForm">
                <ul class="store-search">
                    <li>
                      <span>发布人：</span><input name="applyName" value="${page.params.infoWarehousDto.applyName}" class="text-store text-7" type="text" />
                      <span>联系电话：</span><input name="applyMobile" value="${page.params.infoWarehousDto.applyMobile}" class="text-store text-7" type="text" />
                      <span>品种名称：</span><input name="breedName" value="${page.params.infoWarehousDto.breedName}" class="text-store text-7" type="text" />
                      <span>创建时间：</span>
                   	  <input id="wdate1" name="createTimeStart" value="${page.params.infoWarehousDto.createTimeStart}" type="text" class="text text-date" />&nbsp;至&nbsp;
                      <input id="wdate2" name="createTimeEnd" value="${page.params.infoWarehousDto.createTimeEnd}" type="text" class="text text-date" />
                    </li>
                    <li>
                    <span>状态：</span>
                    	<select name="status" class="text-store text-7">
	                        <option value="">全部</option>
	                        <#if statusMap??>
	                   			<#list statusMap?keys as key>
	                    			<option value="${key!'' }" <#if page.params.infoWarehousDto.status == key>selected</#if>>${statusMap[key]!'' }</option>
	                    		</#list>
                   			</#if>
                    	</select> 
	                  <span>信息来源：</span>
	                  <select name="applyResource" class="text-store text-7">
	                       <option value="">全部</option>
	                        <#if sourceMap??>
	                   			<#list sourceMap?keys as key>
	                    			<option value="${key!'' }" <#if page.params.infoWarehousDto.applyResource == key>selected</#if>>${sourceMap[key]!'' }</option>
	                    		</#list>
                   			</#if>
	                  </select>
	                  <span></span><input type="submit" class="btn-blue" id="btn-blue" value="查询" />
	                  <span></span><input type="button" class="btn-hui" id="" value="清空" />
                    </li>
                </ul>
            </form>
            <div class="use-item1" style=" margin-top:20px;">
           		 <@shiro.hasPermission name="入仓信息管理-新增入仓信息">
                   		<span class="btn-add mb10">
							<a href="#">添加入仓信息</a>
						</span>
                  </@shiro.hasPermission>
            	<table id="busiListingTable" class="table-store" width="100%" cellpadding="1" cellspacing="1">
            		<tr>
                        <th width="5%">编号</th>
                        <th width="5%">发布人</th>
                        <th width="7%">联系电话</th>
                        <th width="5%">信息来源</th>
                        <th width="8%">品种名称</th>
                        <th width="5%">入仓数量</th>
                        <th width="8%">仓库类型</th>
                        <th width="5%">预计面积</th>
                        <th width="8%">货物所在地</th>
                        <th width="10%">期望服务</th>
                        <th width="8%">创建时间</th>
                        <th width="8%">更新时间</th>
                        <th width="5%">状态</th>
                        <th width="5%">处理人</th>
                        <th width="8%">处理时间</th>
                        <th width="6%">处理</th>
                    </tr>
                    <#if (page.results?size>0) >
                    	<#list page.results as r>
                    		<tr>
                    			<td>${r.warehouseId!'' }</td>
                    			<td>${r.applyName!'' }</td>  
                    			<td>${r.applyMobile!'' }</td>
                    			<td>
	                    			<#if sourceMap??>
			                   			<#list sourceMap?keys as key>
			                   				<#if r.applyResource == key>
			                   					${sourceMap[key]!'' }
			                   				</#if>
			                    		</#list>
		                   			</#if>
                    			</td>
                    			<td>
                    				<#if (r.breedId=0)>
                    					${r.breedName!'' }
                    					<#else>
                    					${r.breed_Name!''}
                    				</#if>
                    				
                    			</td>
                    			<td>${r.breedAmount!'' }</td>
                    			<td>${r.warehouseType!'' }</td>
                    			<td>${r.warehouseArea!'' }</td>
                    			<td>${r.warehouseAddress!'' }</td>
                    			<td>${r.expectedService!'' }</td>
                    			<td>
                    				<#if (r.createTime)??>
										${r.createTime?string("yyyy-MM-dd")!''}
									</#if>
								</td>
                    			<td>
                    				<#if (r.updateTime)??>
										${r.updateTime?string("yyyy-MM-dd")!''}
									</#if>
								</td>
                    			<td>
                    				<#if statusMap??>
			                   			<#list statusMap?keys as key>
		                   					<#list colorMap?keys as c>
		                   						<#if key == c>
		                   							<#if r.status == key>
			                   							<font color="${colorMap[c]!'' }">${statusMap[key]!'' }</font>
				                   					</#if>
				                   				</#if>
		                    				</#list>
			                    		</#list>
		                   			</#if>
                    			</td>
                    			<td>${r.handler!'' }</td>
                    			<td>
                    				<#if (r.handleTime)??>
										${r.handleTime?string("yyyy-MM-dd")!''}
									</#if>
                    			</td>
                    			<td class="opr-btn" width="216">
                    				<@shiro.hasPermission name="入仓信息管理-查看入仓信息">
										<span id="461-0" class="operate-5" title="审核" onclick="javascript:checkInfoWarehous(${r.warehouseId!''});"></span>
									</@shiro.hasPermission>
								</td>
                    		</tr>
                    	</#list>
                    	<#else>
                    	<tr>
                    		<td colspan="16">没有数据!</td>
                    	</tr>
                    </#if>
				</table>
			</div>
			<@tools.pages page=page form="infoWarehouForm"/>
    	</div>
    </div>
<!-- pageCenter over -->
</div>

<!-- 新增入仓信息弹层 -->
<div id="addInfoWarehousDialog" class="main" style="display:none;">
	 <form id="addInfoWarehousForm" action="/infoWarehous/addInfoWarehous" method="post">
        <ul class="store-add">
               <li>
            	<span class="inp_width1"><i class="red">*</i> 发布人：</span>
            	<input id="applyName" name="applyName" class="text-store text-16" type="text" />
            	<span class="Validform_checktip"></span>
               </li>
               <li>
               	<span class="inp_width1"><i class="red">*</i> 手机号：</span>
               	<input id="applyMobile" name="applyMobile" class="text-store text-16" type="text" />
              	<span class="Validform_checktip"></span>
               </li>
               <li>
               	<span class="inp_width1"><i class="red">*</i> 品种名称：</span>
               	<input id="breedId" name="breedId" type="hidden"  value="" />
               	<input id="autoCompleteBreedName" name="breedName" class="text-store text-16" type="text" />
               	<span class="Validform_checktip"></span>如：三七
               </li>
               <li>
                   <span class="inp_width1"><i class="red">*</i> 入仓数量：</span>
                   <input id="breedAmount" name="breedAmount" class="text-store text-16" type="text" />
                   <span class="Validform_checktip"></span>如：100公斤
               </li>
               <li>
                   <span class="inp_width1"><i class="red">*</i>  仓库类型：</span>
                   <input id="warehouseType" name="warehouseType" class="text-store text-16" type="text"/>
                   <span class="Validform_checktip"></span>如：常温库/阴凉库/冷库
               </li>
               <li>
                   <span class="inp_width1"><i class="red">*</i>  预计面积：</span>
                   <input id="warehouseArea" name="warehouseArea" class="text-store text-16" type="text"/>              
               	   <span class="Validform_checktip"></span>如：30平米
               </li>
               <li>
                    <span class="inp_width1"><i class="red">*</i> 货物所在地：</span>
                    <div id="select-bg">
	                    <span>
		               	 <select id="areaProvince" name="warehouseAddress" class="warehouseAddress" style="*margin-top:-6px;">
		               	 	<option value="" rel="">请选择省份</option>
				            <#if areas??>
			            		<#list areas as area>
									<option value="${area.name!'' }" rel="${area.code!'' }">${area.name!'' }</option>
			            		</#list>
			            	</#if>
		               	 </select>
	           	 		</span>
           	 	 	 </div>
           	 	 	 &nbsp;
	                 <div id="select-bg">
	                    <span>
	                     <select id="areaCity" name="warehouseAddress" class="warehouseAddress" style="*margin-top:-6px;">
		               	 	<option value="" rel="">请选择城市</option>
		               	 </select>
	                    </span>
	                 </div>
	                 &nbsp;
	                 <div id="select-bg">
	                 	<span>
		             		<select id="areaPlace" class="warehouseAddress" name="warehouseAddress" style="*margin-top:-6px;">
			         			<option value="">请选择区/县</option>
			         		</select>
		             	</span>
	                 </div>
	                 <span class="Validform_checktip">如：云南</span>
                </li>
                <li>
                	<span class="inp_width1"><i class="red">*</i> 期望服务：</span>
                	<input name="expectedService" id="expectedService" value="药材质押" type="checkbox"/>&nbsp;药材质押
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	<input name="expectedService" id="expectedService" value="在线销售" type="checkbox"/>&nbsp;在线销售
                	<span class="Validform_checktip"></span>
                </li>

               <li class="clearfix" style="width:100%;margin-top:15px;margin-bottom:25px">
	            	<span class="inp_width1">&nbsp;</span><input type="submit" class="btn-blue" value="提交" />
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" class="btn-hui" value="重置" />
               </li>
        </ul>
	</form>
</div>
<!-- 新增入仓信息弹层 over -->

<!-- 查看入仓信息  start-->
	<div id="checkInfoWarehousDialog" class="main" style="display:none;">
		 <form id="checkInfoWarehousForm" action="" method="post">
	        <ul class="store-add">
	            <li>
                    <span class="inp_width1">发布人：</span><span id="check_applyName"></span>
                </li>
                
                <li>
                    <span class="inp_width1">手机号：</span><span id="check_applyMobile"></span>
                </li>
                
                <li>
                    <span class="inp_width1">品种名称：</span><span id="check_breed_Name"></span>
                </li>
                
                <li>
                    <span class="inp_width1">入仓数量：</span><span id="check_breedAmount"></span>
                </li>
                
                <li>
                    <span class="inp_width1">仓库类型：</span><span id="check_warehouseType"></span>
                </li>
                
                <li>
                    <span class="inp_width1">预计面积：</span><span id="check_warehouseArea"></span>
                </li>
                
                <li>
                    <span class="inp_width1">货物所在地：</span><span id="check_warehouseAddress"></span>
                </li>
                
                <li>
                    <span class="inp_width1">期望服务：</span><span id="check_expectedService"></span>
                </li>
                
                <li style="width:100%; height:100px;">
                	<span class="inp_width1">备注：</span>
                	<textarea id="check_remarks" name="remarks" class="text-store text-16"></textarea>
                	<span class="Validform_checktip"></span>
                </li>
                
                <li class="clearfix" style="width:100%;margin-top:15px;margin-bottom:25px;">
                	<input id="check_warehouseId" name="warehouseId" type="hidden" value=""/>
                	<span class="inp_width1">&nbsp;</span>
                	<input id="check_infoWarehousValid" type="button" class="btn-blue" value="有效" onclick="checkInfo(1);"/>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	<input id="check_infoWarehousInvalid" type="button" class="btn-hui" value="无效" onclick="checkInfo(2);"/>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	<input id="check_infoWarehousReturn" type="button" class="btn-hui" value="关闭" />
                </li>
	        </ul>
		</form>
	</div>
<!-- 查看入仓信息 over  -->

<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-autocomplete/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
$(function(){
	//查询条件清空
	$(".btn-hui").click(function(){
		$("#infoWarehouForm input[type='text']").val('');
		
		var selectArr = $("#infoWarehouForm select");
		for (var i = 0; i < selectArr.length; i++) {
			selectArr[i].options[0].selected = true; 
		}
	});
})

/* 
 * 跳转新增页面 
 $(".btn-add").click(function(){
	location.href="/infoWarehous/toAddPage";
}); */
//日期控件
$('#wdate1').click(function(){
	 WdatePicker({
         maxDate:'#F{$dp.$D(\'wdate2\',{d:-1});}',
         readOnly:true
     });
});
$('#wdate2').click(function(){
	WdatePicker({
        minDate:'#F{$dp.$D(\'wdate1\',{d:1});}',
        readOnly:true
    });
});

//弹框
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

// 弹框，带刷新页面功能
function tips(str,submit){
	bghui();
	Alert({
            str: str,
            buttons:[{
                name:'确定',
                classname:'btn-blue',
                ev:{click:function(data){
                	 disappear();
                     $(".bghui").remove();
                     
                     if(submit == true){
                          $('#conditionForm').submit();
                     }
                 }
               }
            }]
    });
};

//弹框，自定义功能
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

//添加入仓信息
$('.btn-add a').click(function(){
	resetDialogForm();
	$("#addInfoWarehousDialog").dialog({
	 	 title:'添加入仓信息',
	 	 width : '45%',
	     modal: true,
	     autoOpen: true
	 });
});

//重置新增入仓信息表单
function resetDialogForm(){
	addInfoWarehousForm.resetForm();
	addInfoWarehousForm.resetStatus();
};

//省市三级联动
$('select.warehouseAddress').change(function(){
	var id = $(this).attr('id');
	var code = $(this).find('option:selected').attr('rel');
	if(code==''){
		return;
	}
	switch(id){
		case 'areaProvince':
		  	$.ajax({
				async : true,
				cache : true,
				type : "GET",
				data : {'code':code},
				dataType : "json",
				url : "/infoWarehous/getAreasByCode",
				success : function(data) {
					var ok = data.ok;
					if(ok){
						$('#areaCity option:first').nextAll().remove();
						var areas = data.obj;
						$.each(areas,function(index,area){
							$('#areaCity').append('<option value="'+area.name+'" rel="'+area.code+'">'+area.name+'</option>');
						});
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert('操作失败！');
				}
			});
			break;
		case 'areaCity':
		  	$.ajax({
				async : true,
				cache : true,
				type : "GET",
				data : {'code':code},
				dataType : "json",
				url : "/infoWarehous/getAreasByCode",
				success : function(data) {
					var ok = data.ok;
					if(ok){
						$('#areaPlace option:first').nextAll().remove();
						var areas = data.obj;
						$.each(areas,function(index,area){
							$('#areaPlace').append('<option value="'+area.name+'" rel="'+area.code+'">'+area.name+'</option>');
						});
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert('操作失败！');
				}
			});
			break;
		default:
		 	break;
	}
});


$('#autoCompleteBreedName').autocomplete({  
	zIndex:10001, 
	deferRequestBy:200,//keyUp之后发起请求的间隔时间  Default: 0
	serviceUrl: '/infoWarehous/getTypeName',//ajax后台请求地址
	onSelect: function (suggestion) {
		//下拉层选择触发事件 回写breedId
		var url = "/infoWarehous/getTypeName";
		$.ajax({
			type : "POST",
			url : url,
			data : {query:suggestion.value},
			dataType : "json",
			success : function(map){
				var data = map.list[0];
				$("input[name='breedId']").val(data.breedId);
				addInfoWarehousForm.check(false, "#autoCompleteBreedName");
			}
		});
	}
}); 

////////////////////////////////////////////////////////// 入仓信息审核处理 ////////////////////////////////////////////////////
//查看入仓信息
function checkInfoWarehous(warehousId){
	$.ajax({
			async : false,
			cache : false,
			type : "GET",
			data : {"warehousId":warehousId},
			dataType : "json",
			url : "/infoWarehous/getInfoWarehous",
			success : function(data) {
				var ok = data.ok;
				if(ok==true){
					var obj = data.obj;
					
					//重置窗口
					$('#check_remarks').val('').parent('li').show();
					$('#check_infoWarehousValid').show();
					$('#check_infoWarehousInvalid').show();
					
					//填充数据
					$.each(obj,function(key,value){
						if(key == 'warehouseId'){
							$('#check_warehouseId').val(value);
						}else if(key == 'remarks'){
							$("#check_"+key).val(value);
						}else{
							$("#check_"+key).text(value);
						}
				 		
				 		if(key == 'status'){
							if(value != 0){
								//$('#check_remarks').parent('li').hide();
								$('#check_infoWarehousValid').hide();
								$('#check_infoWarehousInvalid').hide();
							}
						}
				 	});
					//加载窗口
					$("#checkInfoWarehousDialog").dialog({
					 	 title:'入仓信息',
					 	 width : '45%',
					     modal: true,
					     autoOpen: true
					 });	
					 
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

//关闭查看入仓信息弹层
$('#check_infoWarehousReturn').click(function(){
	$("#checkInfoWarehousDialog").dialog("close");
});

//审核有效 无效记录处理
function checkInfo(id){
	var warehouseId = $("#check_warehouseId").val();
	var remarks = $("#check_remarks").val();
	var status = id;
	var url = "/infoWarehous/checkInfoWarehous";
	$.ajax({
		type : "post",
		url : url,
		data : {warehouseId:warehouseId,remarks:remarks,status:status},
		dataType : "json",
		success : function(data){
			if(data.ok){
				bghui();
				Alert({
	    			str : '操作成功！',
	    			buttons:[{
	    				name:'确定',
	    				id:'1',
	    				classname:'btn-style',
	    				ev:{click:function(data){
	    					$('#check_infoWarehousValid').hide();
							$('#check_infoWarehousInvalid').hide();
	    					disappear();
	    					$(".bghui").remove();
	                        location.href = "/infoWarehous";
	    				}
	    			}
	    			}]
	    		});
			}else{
				bghui();
				Alert({
	    			str : '操作失败！',
	    			buttons:[{
	    				name:'确定',
	    				id:'1',
	    				classname:'btn-style',
	    				ev:{click:function(data){
	    					$(".bghui").remove();
	    					$('#check_infoWarehousValid').hide();
							$('#check_infoWarehousInvalid').hide();
	    					disappear();
	                        location.href = "/infoWarehous";
	    				}
	    			}
	    			}] 
	    		});
			}
		}
	});
}


///////////////////////////////////////////////////// 表单验证 ///////////////////////////////////////////////////
	//验证添加求购信息表单
    var addInfoWarehousForm = $("#addInfoWarehousForm").Validform({
    	    tiptype:function(msg,o,cssctl){
    			//msg：提示信息;
    			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
    			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
    			if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
    				var objtip=$(o.obj).siblings('span.Validform_checktip');
    				if(o.obj.is("select")){
    					objtip=$(o.obj).parent().parent().next('span.Validform_checktip');
    				}
    				cssctl(objtip,o.type);
    				objtip.text(msg);
    			}	
    		},
    	    ajaxPost:true,
    	    showAllError:true,
    	    ajaxurl:{
    	        success:function(data,obj){
    	            //data是返回的json数据;
    	            //obj是当前正做实时验证表单元素的jquery对象;
    	            //注意：5.3版中，实时验证的返回数据须是含有status值的json数据！
    	            //跟callback里的ajax返回数据格式统一，建议不再返回字符串"y"或"n"。目前这两种格式的数据都兼容。
    	            var ok = data.ok;
    	            if(ok==undefined){
    	            	$(obj).next('span.Validform_checktip').text('网络繁忙，请稍后再试！');
    	            }
    	        },
    	        error:function(data,obj){
    	            //data是{ status:**, statusText:**, readyState:**, responseText:** };
    	            //obj是当前表单的jquery对象;
    	            var readyState = data.readyState;
    	            if(readyState!=0){
    	            	$(obj).next('span.Validform_checktip').text('网络繁忙，请稍后再试！');
    	            }
    	        }
    	    },
			beforeSubmit:function(curform){
				var warehouseAddress = $('#addInfoWarehousForm select[name=warehouseAddress]:eq(0)');
		        if(warehouseAddress.val() == ''){
		        	tips('请选择货物所在地！');
					return false; 
		        }
    	    },
    	    callback:function(data){
    	    	var ok = data.ok;
    	    	var msg = data.msg;
    	    	if(ok){
    	    		tipsfun(msg,function(){window.location.reload(true);});
    	    	}else{
    	    		if(msg!=undefined){
    	    			tips(msg);
    	    		}else{
    	    			tips('网络繁忙，请稍后再试！');
    	    		}
    	    	}
    	    }
    	});
    addInfoWarehousForm.addRule([
		{
	        ele:"#applyName",
	        datatype:"*1-50",
	        nullmsg:"请填写发布人！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#applyMobile",
	        datatype:"m",
	        nullmsg:"请填写联系电话！",
	        errormsg:"请填写正确的电话号码！",
	        sucmsg:""
	    },
	    {
	        ele:"#autoCompleteBreedName",
	        ajaxurl:"/infoWarehous/checkBreedName",
	        datatype:"*1-50",
	        nullmsg:"请填写品种名称！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#breedAmount",
	        datatype:"*1-50",
	        nullmsg:"请填写入仓数量！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#warehouseType",
	        datatype:"*1-50",
	        nullmsg:"请填写仓库类型！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#warehouseArea",
	        datatype:"*1-50",
	        nullmsg:"请填写预计面积！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#expectedService",
	        datatype:"*",
	        nullmsg:"请勾选一种期望服务！",
	        errormsg:"请勾选一种期望服务！",
	        sucmsg:""
	    }
	    
	]);
    
    
  	//审核入仓信息信息表单
    var checkInfoWarehousForm   = $("#checkInfoWarehousForm").Validform({
	    tiptype:function(msg,o,cssctl){
	    	if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				var objtip=$(o.obj).siblings('span.Validform_checktip');
				if(o.obj.is("select")){
					objtip=$(o.obj).parent().parent().next('span.Validform_checktip');
				}
				cssctl(objtip,o.type);
				objtip.text(msg);
			}	
		},
	    ajaxPost:true,
	    showAllError:true,
	    beforeSubmit:function(curform){
	    	//验证参数是否正确
			var warehousId = $('#check_warehouseId').val();
			if(warehousId == ''){
				tips('入仓信息参数不能为空！');
				return false;
			}
	    },
	    callback:function(data){
	    	var ok = data.ok;
	    	var msg = data.msg;
	    	if(ok){
	    		tipsfun(msg,function(){window.location.reload(true);});
	    	}else{
	    		if(msg!=undefined){
	    			tips(msg);
	    		}else{
	    			tips('网络繁忙，请稍后再试！');
	    		}
	    	}
	    }
	});
    checkInfoWarehousForm.addRule([
	{
        ele:"#check_remarks",
        datatype:"*0-500",
        ignore:"ignore",
        nullmsg:"请填写备注！",
        errormsg:"请填写1到500位任意字符！",
        sucmsg:""
    }
	]);
</script>
</body>
</html>