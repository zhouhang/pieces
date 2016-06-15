<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>供应信息管理</title>
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/autocomplete-styles.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
	<style>
		.main .store-left li {
			width: 100%;
		}
		.main .store-right img {
		    height: 100px;
		    width: 100px;
		}
		.store-right li p {
		    width: 110px;
		    cursor: pointer;
		}
	</style>
</head>
<body>
<#include "home/top.ftl" />  
<#import 'macro.ftl' as tools>
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">供应信息管理</h1>
            <!-- 查询条件 -->
            <form id="conditionForm" action="/bossWxSupply" method="get">
                <ul class="store-search">
                    <li><span>发布人：</span><input name="userName" value="${(page.params.wxSupplySearchDto.userName)!''}" class="text-store text-7" type="text" />
                    <span>联系电话：</span><input name="userMobile" value="${(page.params.wxSupplySearchDto.userMobile)!''}" class="text-store text-7" type="text" />
                    <span>品种名称：</span><input name="breedName" value="${(page.params.wxSupplySearchDto.breedName)!''}" class="text-store text-7" type="text" />
                    <span>状态：</span><div id="select-bg"><span><select name="status" value="${(page.params.wxSupplySearchDto.status)!''}">
                    	<option value="">全部</option>
                    	<#if statusMap??>
	               			<#list statusMap?keys as key>
	                			<option value="${key!'' }" <#if page.params.wxSupplySearchDto.status == key>selected</#if>>${statusMap[key]!'' }</option>
	                		</#list>
	               		</#if>
                    </select></span></div>
                    <br/><br/>
					<span>信息来源：</span><div id="select-bg"><span><select name="sypplyResource" value="${(page.params.wxSupplySearchDto.sypplyResource)!''}">
                    	<option value="">全部</option>
                    	<#if sypplyResourcesMap??>
	               			<#list sypplyResourcesMap?keys as key>
	                			<option value="${key!'' }" <#if page.params.wxSupplySearchDto.sypplyResource == key>selected</#if>>${sypplyResourcesMap[key]!'' }</option>
	                		</#list>
	               		</#if>
                    </select></span></div>
                    <span>创建时间：</span>
                    <input id="wdate1" type="text" class="text text-date" name="startDate" value="${(page.params.wxSupplySearchDto.startDate)!''}" />
                    —
                    <input id="wdate2" type="text" class="text text-date" name="endDate" value="${(page.params.wxSupplySearchDto.endDate)!''}" />
                    <span></span><input type="submit" class="btn-blue" id="btn-blue" value="查询" />
                    <span></span><input type="button" class="btn-hui" onclick="$(':input','#conditionForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');" value="清空" />
                    </li>
                </ul>
            </form>
            <div class="use-item1" style=" margin-top:20px;">
            	<@shiro.hasPermission name="供应信息管理-新增供应信息">
                	<span class="btn-add mb10">
						<a href="#">添加供应信息</a>
					</span>
                </@shiro.hasPermission>
				<!-- 表格 -->
                <table id="wxSupplyTable" class="table-store" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="5%">编号</th>        <!-- 1.编号 -->
                        <th width="5%">发布人</th>     <!-- 2.发布人 -->
                        <th width="7%">联系电话</th>     <!-- 3.联系电话 -->
                        <th width="7%">信息来源</th>     <!-- 4.信息来源 -->
                        <th width="7%">品种名称</th>   <!-- 5.品种名称 -->
                        <th width="5%">规格</th>       <!-- 3.规格 -->
                        <th width="7%">价格</th>       <!-- 7.价格 -->
                        <th width="5%">库存</th>       <!-- 8.库存 -->
                        <th width="7%">产地</th>       <!-- 9.产地 -->
                        <th width="7%">货物所在地</th> <!-- 10.货物所在地 -->
                        <th width="7%">创建时间</th>   <!-- 11.创建时间 -->
                        <th width="7%">更新时间</th>   <!-- 12.更新时间 -->
                        <th width="5%">状态</th>       <!-- 13.状态 -->
                        <th width="5%">处理人</th>     <!-- 14.处理人 -->
                        <th width="7%">处理时间</th>   <!-- 15.处理时间 -->
                        <th width="5%">操作</th>       <!-- 16.操作 -->
                    </tr>
                    <#if (page.results?size>0)>
	                    <#list page.results as wxSupply>
	                    	<tr>
	                    		<td class="checkSupplyId" rel="${(wxSupply.supplyId)!''}">${(wxSupply.supplyId)!''}</td>      <!-- 1.编号 -->
	                    		<td class="checkUserName">
	                    		<!--alter by biran 20150702 删除超链接
	                    		 	<#if wxSupply.sypplyResource == 0>
		           						<a href="/getMemberManage/getMemberByUserName?memberName=${(wxSupply.userName)!''}">${(wxSupply.userName)!''}</a>
								    <#else>
										${(wxSupply.userName)!''}
									</#if>
									-->
									${(wxSupply.userName)!''}
	                    		</td>  																   						  <!-- 2.发布人 -->
	                    		<td class="checkUserMobile">${(wxSupply.userMobile)!''}</td>		   						  <!-- 3.联系电话 -->
	                    		<td class="checkSypplyResource" rel="${(wxSupply.sypplyResource)!''}">
	                    			<#if wxSupply.sypplyResource == 0>			 					   						  <!-- 4.信息来源 -->	
		                    			微信
									<#elseif wxSupply.sypplyResource == 1>
										东方中药材
									<#elseif wxSupply.sypplyResource == 2>
										客服
								    <#else>
										异常
									</#if>
	                    		</td>		 
	                    		<td class="checkBreedName">${(wxSupply.breedName)!''}</td>                       			  <!-- 5.品种名称 -->
	                    		<td class="checkBreedStandardLevel">${(wxSupply.breedStandardLevel)!''}</td>     			  <!-- 6.规格 -->
	                    		<td class="checkPriceUnitPrice">${(wxSupply.priceUnitPrice)!''}</td>   			 			  <!-- 7.价格 -->
	                    		<td class="checkQtyUnitQty">${(wxSupply.qtyUnitQty)!''}</td>   <!-- 8.库存 -->
	                    		<td class="checkBreedPlace">${(wxSupply.breedPlace)!''}</td>                                  <!-- 9.产地 -->
	                    		<td class="checkAreaName">${(wxSupply.areaName)!''}</td>                                      <!-- 10.货物所在地 -->
	                    		<td class="checkCreateTime">${(wxSupply.createTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>    <!-- 11.创建时间 -->
	                    		<td class="checkUpdateTime">${(wxSupply.updateTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>    <!-- 12.更新时间 -->
	                    		<td class="checkStatus" rel="${(wxSupply.status)!''}">                                                                	  <!-- 13.状态 -->
	                    			<#if statusMap??>
			                   			<#list statusMap?keys as key>
			                   				<#list colorMap?keys as c>
		                   						<#if key == c>
		                   							<#if wxSupply.status == key>
			                   							<font color="${colorMap[c]!'' }">${statusMap[key]!'' }</font>
				                   					</#if>
				                   				</#if>
		                    				</#list>
			                    		</#list>
		                   			</#if>
	                    		</td>
	                    		<td class="checkApproverName">${(wxSupply.approverName)!''}</td>                                <!-- 14.处理人 -->
	                    		<td class="checkApproveTime">${(wxSupply.approveTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>    <!-- 15.处理时间 -->
	                    		<td class="opr-btn">
	                    			<@shiro.hasPermission name="供应信息管理-查看供应信息">
					                	<span class="operate-5 checkWxSupplyPop" title="审核"></span>							<!-- 16.操作 -->
					                </@shiro.hasPermission>                             							
		                        	<#if (wxSupply.picVoList??)&&(wxSupply.picVoList?size>0)>
										<#list wxSupply.picVoList as pic>
											<input type="hidden" title="${RESOURCE_IMG_UPLOAD}/${(pic.originalPath)!''}" value="${RESOURCE_IMG_UPLOAD}/${(pic.thumbnailPath)!''}" />
										</#list>
									</#if>
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
            <!-- 分页 -->
			<@tools.pages page=page form="conditionForm"/>
        </div>
    </div>
<!-- pageCenter over -->
<!-- 弹层  -->
<!-- 新增供应信息  start-->
	<div id="addWxSupplyDialog" class="main" style="display:none;">
		<div class="page-main" style="margin-left:15px;">
			<form id="addWxSupplyForm" action="/bossWxSupply/addWxSupply" method="post">
	        	<div class="store-left">
			        <ul class="store-add">
			            <li>
		                    <span class="inp_width1"><i class="red">*</i>发布人：</span><input id="userName" name="userName" class="text-store text-16" type="text" /><span class="Validform_checktip"></span>
		                </li>
		                <li>
		                    <span class="inp_width1"><i class="red">*</i>联系电话：</span><input id="userMobile" name="userMobile" class="text-store text-16" type="text" /><span class="Validform_checktip"></span>
		                </li>
		                <li>
		                    <span class="inp_width1"><i class="red">*</i>品种名称：</span><input id="breedName" name="breedName" class="text-store text-16" type="text" /><span class="Validform_checktip">如：三七</span>
		                </li>
		                <li>
		                    <span class="inp_width1"><i class="red">*</i>货物所在地：</span>
		                    <input id="areaCode" name="areaCode" type="hidden" />
		                    <div id="select-bg">
			                    <span>
				               	 <select id="areaProvince" class="warehousePlace" style="*margin-top:-6px;">
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
			                     <select id="areaCity" class="warehousePlace" style="*margin-top:-6px;">
				               	 	<option value="" rel="">请选择城市</option>
				               	 </select>
			                    </span>
			                 </div>
			                 &nbsp;
			                 <div id="select-bg">
			                 	<span>
				             		<select id="areaPlace" class="warehousePlace" name="areaName" style="*margin-top:-6px;">
					         			<option value="">请选择区/县</option>
					         		</select>
				             	</span>
			                 </div>
			                 <span class="Validform_checktip">如：云南</span>
		                </li>
		                <li>
		                    <span class="inp_width1">规格：</span><input id="breedStandardLevel" name="breedStandardLevel" class="text-store text-16" type="text" /><span class="Validform_checktip">如：120头</span>
		                </li>
		                <li>
		                    <span class="inp_width1">价格：</span><input id="breedPrice" name="price" class="text-store text-16" type="text" />&nbsp;元&nbsp;/&nbsp;
		                    <div id="select-bg">
			                    <span>
				                    <select id="breedPriceUnit" name="priceUnit" style="*margin-top:-6px;">
				                    	<option value=""></option>
				                    	<#if dicts??>
						            		<#list dicts as dict>
						            			<option value="${dict.dictValue!''}">${dict.dictValue!''}</option>
						            		</#list>
						            	</#if>
				                    </select>
			                    </span>
	                    	</div>
	                    	<span class="Validform_checktip">如：100元/公斤</span>
		                </li>
		                <li>
		                    <span class="inp_width1">库存：</span><input id="breedAmount" name="qty" class="text-store text-16" type="text" />&nbsp;
		                    <div id="select-bg">
		                    	<span>
				                    <select id="breedAmountUnit" name="qtyUnit" style="*margin-top:-6px;">
				                    	<option value=""></option>
				                    	<#if dicts??>
						            		<#list dicts as dict>
						            			<option value="${dict.dictValue!''}">${dict.dictValue!''}</option>
						            		</#list>
						            	</#if>
			                    	</select>
	                    		</span>
	                    	</div>
	                    	<span class="Validform_checktip">如：120公斤</span>
		                </li>
		                <li>
		                    <span class="inp_width1">产地：</span><input id="breedPlace" name="breedPlace" class="text-store text-16" type="text" /><span class="Validform_checktip">如：云南</span>
		                </li>
		                <li class="clearfix" style="margin:25px 0px;">
			            	<span class="inp_width1">&nbsp;</span><input id="addWxSupplyButton" type="submit" class="btn-blue" value="添加" />
			            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" class="btn-hui" value="重置" />
		                </li>
			        </ul>
	        	</div>
		        <div class="store-right">
		            <ul class="form-2">
		            	<li id="addPic" class="relative">
		                    <span style="display:block;">
		                        <img width="50" src="${RESOURCE_IMG}/images/jzt-boss/add.jpg" />
		                    </span>
		                    <p>上传图片（可批量）</p>
		                     <span style="position:absolute; top:0; left:0;">
								<input type="file" id="uploadPic" name="file" style="width:100px; height:100px; cursor: pointer; opacity:0; filter:alpha(opacity:0);" title="上传新图片" accept="image/*" multiple="multiple" />
							</span>
		            	</li>
		            </ul>            
				</div>
			</form>
		</div>
	</div>
<!-- 新增供应信息 over  -->
<!-- 查看供应信息  start-->
	<div id="checkWxSupplyDialog" class="main" style="display:none;">
		<div class="page-main" style="margin-left:15px;">
	        <div class="store-left">
				 <form id="checkWxSupplyForm" action="" method="post">
			        <ul class="store-add">
			            <li>
		                    <span class="inp_width1">发布人：</span><span id="checkUserName"></span>
		                </li>
		                <li>
		                    <span class="inp_width1">联系电话：</span><span id="checkUserMobile"></span>
		                </li>
		                <li>
		                    <span class="inp_width1">品种名称：</span><span id="checkBreedName"></span>
		                </li>
		                <li>
		                    <span class="inp_width1">规格：</span><span id="checkBreedStandardLevel"></span>
		                </li>
		                <li>
		                    <span class="inp_width1">价格：</span><span id="checkPriceUnitPrice"></span>
		                </li>
		                <li>
		                    <span class="inp_width1">库存：</span><span id="checkQtyUnitQty"></span>
		                </li>
		                <li>
		                    <span class="inp_width1">产地：</span><span id="checkBreedPlace"></span>
		                </li>
		                <li>
		                    <span class="inp_width1">货物所在地：</span><span id="checkAreaName"></span>
		                </li>
		                <li style="height:100px;">
		                    <span class="inp_width1">备注：</span><textarea id="checkRemark" name="remark" class="text-store text-16"></textarea><span></span>
		                </li>
		                <li class="clearfix" style="margin:25px 0px;">
		                	<input id="checkSupplyId" name="supplyId" type="hidden" />
		                	<input id="checkSypplyResource" name="sypplyResource" type="hidden" />
			            	<span class="inp_width1">&nbsp;</span><input id="checkWxSupplyValid" type="button" class="btn-blue" value="有效" />
			            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="checkWxSupplyInvalid" type="button" class="btn-hui" value="无效" />
			            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="checkWxSupplyReturn" type="button" class="btn-hui" value="返回" />
		                </li>
			        </ul>
				</form>
	        </div>
	        <div class="store-right">
	            <ul class="form-2 gallery"></ul>            
			</div>
		</div>	
	</div>
<!-- 查看供应信息 over  -->

<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jQuery-File-Upload/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jQuery-File-Upload/js/jquery.fileupload-process.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jQuery-File-Upload/js/jquery.fileupload-validate.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-autocomplete/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
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
    //验证新增微信供应信息表单
    var addWxSupplyForm = $("#addWxSupplyForm").Validform({
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
			//验证图片上传状态
	    	var loadedPicImgsNum = $('#addPic').prevAll('li').find('img.loaded').length;
	    	if(loadedPicImgsNum>0){
	    		tips('请等待图片上传完成再添加！');
	    		return false;
	    	}
	    	//验证是否上传图片
	    	var loadedPicImgsNum = $('#addPic').prevAll('li').find('input:hidden').length;
	    	if(loadedPicImgsNum==0){
	    		tips('最少上传1张图片！');
	    		return false;
	    	}else if(loadedPicImgsNum>6){
	    		tips('最多上传6张图片！');
	    		return false;
	    	}
	    	$('#addWxSupplyButton').val('添加中').attr('disabled','disabled');
	    },
	    callback:function(data){
	    	$('#addWxSupplyButton').val('添加').removeAttr('disabled');
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
	addWxSupplyForm.addRule([
		{
	        ele:"#userName",
	        datatype:"*1-50",
	        nullmsg:"请填写发布人！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#userMobile",
	        datatype:"m",
	        nullmsg:"请填写联系电话！",
	        errormsg:"请填写正确的电话号码！",
	        sucmsg:""
	    },
	    {
	        ele:"#breedName",
	        ajaxurl:"/bossWxSupply/checkBreedName",
	        datatype:"*1-50",
	        nullmsg:"请填写品种名称！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#areaPlace",
	        datatype:"*1-50",
	        nullmsg:"请选择货物所在地！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#breedStandardLevel",
	        ignore:"ignore", 
	        datatype:"*1-50",
	        nullmsg:"请填写规格！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#breedPrice",
	        ignore:"ignore", 
	        datatype:"n1-10",
	        nullmsg:"请填写价格！",
	        errormsg:"请填写1到10位数字！",
	        sucmsg:""
	    },
	    {
	        ele:"#breedPriceUnit",
	        ignore:"ignore", 
	        datatype:"*1-2",
	        nullmsg:"请选择单位！",
	        errormsg:"请填写1到2位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#breedAmount",
	        ignore:"ignore", 
	        datatype:"n1-20",
	        nullmsg:"请填写库存！",
	        errormsg:"请填写1到20位数字！",
	        sucmsg:""
	    },
	    {
	        ele:"#breedAmountUnit",
	        ignore:"ignore", 
	        datatype:"*1-2",
	        nullmsg:"请选择单位！",
	        errormsg:"请填写1到2位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#breedPlace",
	        ignore:"ignore", 
	        datatype:"*1-50",
	        nullmsg:"请填写产地！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    }
	]);
	//重置新增微信供应信息表单
    function resetDialogForm(){
    	addWxSupplyForm.resetForm();
    	addWxSupplyForm.resetStatus();
    	$('#addPic').prevAll('li').remove();
    };
	//微信供应信息上传图片
	$('#uploadPic').fileupload({
		url: '/bossWxSupply/uploadPic',
		autoUpload: true,
		singleFileUploads: false,
        dataType: 'json',
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png|bmp)$/i,
        maxFileSize: 5000000,
        messages: {
            acceptFileTypes: '不支持的文件类型！',
            maxFileSize: '不支持大小超过5M的图片！'
        }
    }).on('fileuploadsubmit', function (e, data) {
       	var filesLength = data.files.length;
       	var picImgsLength = $('#addPic').prevAll('li').find('img').length;
       	//tips(filesLength+"/"+picImgsLength);
		if(filesLength+picImgsLength>6 || picImgsLength>6){
			tips("最多添加6张图片！");
			return false;
		}
		$.each(data.files, function (index,file) {
           $('#addPic').before('<li><span style="display:block;"><img class="loaded" width="50" src="${RESOURCE_IMG}/images/loading.gif" /></span><p class="delPic"><span>点击移除图片</span></p></li>');
        });
        picImgsLength = $('#addPic').prevAll('li').find('img').length;
       	if(picImgsLength==6){
			$('#addPic').hide();
		}
    }).on('fileuploadprocessalways', function (e, data) {
        if(data.files.error){
        	tips(data.files[0].error);
		}
    }).on('fileuploaddone', function (e, data) {
    	//tips(JSON.stringify(data.result))
    	var picImgs = $('#addPic').prevAll('li').find('img.loaded');
    	var ok = data.result.ok;
    	if(ok){
    		var picPaths = data.result.obj;
    		$.each(picPaths, function (index,picPath) {
    			picPath = '${RESOURCE_IMG_UPLOAD}/'+picPath;
    			//加载图片
	        	var img = new Image();
		    	img.onload = function(){
		    		$(picImgs[index]).removeClass('loaded').attr('src',picPath).after('<input type="hidden" name="picPath" value="'+picPath+'" />');
		    	};
		    	img.onerror = function(){
		    		tips('图片加载失败！');
		    	};	
		    	img.src = picPath;
	        });
		}
		var msg = data.result.msg;
		if(msg!=undefined){
			tips(msg);
		}
    }).on('fileuploadfail', function (e, data) {
        tips('操作失败！');
    });
    //移除微信供应信息图片
    $('#addWxSupplyDialog .store-right ul').on('click','li .delPic',function(){
    	$(this).parent('li').remove();
    	$('#addPic').show();
    });
    //新增微信供应信息
	$('.btn-add a').click(function(){
		resetDialogForm();
		$('#addWxSupplyDialog').dialog({
		 	 title:'添加供应信息',
		 	 width : '1200',
		     modal: true,
		     autoOpen: true
		 });
	});
	//品种名称联想
	$('#breedName').autocomplete({ 
		zIndex:10001, 
		deferRequestBy:200,//keyUp之后发起请求的间隔时间  Default: 0
		serviceUrl: '/bossWxSupply/getBreedNames',//ajax后台请求地址
		onSearchComplete: function (query, suggestions) {
			if($.isArray(suggestions) && $(suggestions).size()==0){
				//alert(JSON.stringify(suggestions));
				//tips('请先在品种管理中添加该品种！');
			}
		},
		onSelect: function (suggestion) {
			//alert(JSON.stringify(suggestion));
			addWxSupplyForm.check(false,'#breedName');
		},
		onSearchError: function (query, jqXHR, textStatus, errorThrown) {
			tips('网络繁忙，请稍后再试！');
		}
    }); 
    //区域级联
    $('select.warehousePlace').change(function(){
    	var id = $(this).attr('id');
    	var code = $(this).find('option:selected').attr('rel');
    	if(code==''){
    		return;
    	}
    	switch(id)
		{
			case 'areaProvince':
			  	$.ajax({
					async : true,
					cache : true,
					type : "GET",
					data : {'code':code},
					dataType : "json",
					url : "/bossWxSupply/getAreasByCode",
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
						tips('操作失败！');
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
					url : "/bossWxSupply/getAreasByCode",
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
						tips('操作失败！');
					}
				});
				break;
			case 'areaPlace':
				$('#areaCode').val(code);
				break;
			default:
			 	break;
		}
    });
	//关闭查看供应信息窗口
    $('#checkWxSupplyReturn').click(function(){
    	$('#checkWxSupplyDialog').dialog('close');
    });
	//查看供应信息
	$('.checkWxSupplyPop').click(function(){
		var checkWxSupplyRows = $(this).parent('td').siblings('td');
		var checkWxSupplyPics =  $(this).parent('td').find('input:hidden');
		$.ajax({
			async : true,
			cache : true,
			type : "GET",
			dataType : "json",
			url : "/bossWxSupply/getWxSupply",
			success : function(data) {
				var ok = data.ok;
				if(ok){
					//重置窗口
					$('#checkRemark').val('').parent('li').show();
					$('#checkWxSupplyValid').show();
					$('#checkWxSupplyInvalid').show();
					//加载详情
					$(checkWxSupplyRows).each(function(checkWxSupplyRowIndex,checkWxSupplyRow){
						var checkWxSupplyRowFlag = $(checkWxSupplyRow).attr('class');
						var checkWxSupplyRowText = $(checkWxSupplyRow).text();
						var checkWxSupplyRowRel = $(checkWxSupplyRow).attr('rel');
						
						if(checkWxSupplyRowRel!=undefined){
							$('#'+checkWxSupplyRowFlag).val(checkWxSupplyRowRel);
						}else{
							$('#'+checkWxSupplyRowFlag).text(checkWxSupplyRowText);
						}
						
						if(checkWxSupplyRowFlag == 'checkStatus'){
							if(checkWxSupplyRowRel != 0){
								$('#checkRemark').parent('li').hide();
								$('#checkWxSupplyValid').hide();
								$('#checkWxSupplyInvalid').hide();
							}
						}
					});
					var checkSypplyResource = $('#checkSypplyResource').val();
					if(checkSypplyResource != 0){
						$('#checkRemark').parent('li').hide();
					}
					//加载图片
					$('#checkWxSupplyDialog .gallery').empty();
					$(checkWxSupplyPics).each(function(checkWxSupplyPicIndex,checkWxSupplyPic){
						var checkWxSupplyOriginalPic = $(checkWxSupplyPic).attr('title');
						var checkWxSupplyThumbnailPic = $(checkWxSupplyPic).val();
						$('#checkWxSupplyDialog .gallery').append('<li><span style="display:block;"><img width="50" rel="'+checkWxSupplyOriginalPic+'" src="'+checkWxSupplyThumbnailPic+'" /></span><p><span>点击查看大图</span></p></li>');
					});
					//加载窗口
					$("#checkWxSupplyDialog").dialog({
					 	 title:'供应信息',
					 	 width : '1200',
					     modal: true,
					     autoOpen: true
					 });
				}else{
					tips('网络繁忙，请稍后再试！');
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				tips('操作失败！');
			}
		});
	});
	//查看供应信息大图
    $('#checkWxSupplyDialog .gallery').on('click','li p',function(){
    	var img = new Image();
    	img.onload = function(){
    		var imgWidth = img.width;
    		var imgHeight = img.height;
    		if(imgWidth>800||imgHeight>600){
    			imgWidth=800;
    			imgHeight=600;
    		}else{
    			imgWidth=imgWidth+50;
    			imgHeight=imgHeight+100;
    		}
    		$('<div style="text-align:center;"><img src='+img.src+' /></div>').dialog({
    			width : imgWidth,
    			height : imgHeight,
				modal : true,
				autoOpen : true
		 	});
    	};
    	img.onerror = function(){
    		tips('图片加载失败！');
    	};	
    	img.src = $(this).prev('span').find('img').attr('rel');
    });
    //验证微信查看供应信息表单
    var checkWxSupplyForm = $("#checkWxSupplyForm").Validform({
	    tiptype:function(msg,o,cssctl){
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				var objtip=$(o.obj).next('span');
				cssctl(objtip,o.type);
				objtip.text(msg);
			}	
		},
	    ajaxPost:true,
	    showAllError:true,
	    beforeSubmit:function(curform){
	    	//验证参数是否正确
			var supplyId = $('#checkSupplyId').val();
			var sypplyResource = $('#checkSypplyResource').val();
			if(supplyId == '' || sypplyResource == ''){
				tips('供应信息参数错误！');
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
	checkWxSupplyForm.addRule([
		{
	        ele:"#checkRemark",
	        ignore:"ignore", 
	        datatype:"*1-500",
	        nullmsg:"请填写备注！",
	        errormsg:"请填写0到500位任意字符！",
	        sucmsg:""
	    }
	]);
	//信息有效
	$('#checkWxSupplyValid').click(function(){
		$('#checkWxSupplyForm').attr('action','/bossWxSupply/checkWxSupplyValid');
		$('#checkWxSupplyForm').submit();
	});
	//信息无效
	$('#checkWxSupplyInvalid').click(function(){
		$('#checkWxSupplyForm').attr('action','/bossWxSupply/checkWxSupplyInvalid');
		$('#checkWxSupplyForm').submit();
	});
</script>
</body>
</html>