<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>求购信息管理</title>
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
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">求购信息管理</h1>
            
            <!-- 查询条件 -->
            <form id="conditionForm" action="/bossWxDemand" method="get">
                <ul class="store-search">
                    <li>
                    <span>发布人：　</span><input name="userName" value="${(page.params.wxDemandSearchDto.userName)!''}" class="text-store text-7" type="text" />
                    <span>联系电话：</span><input name="userMobile" value="${(page.params.wxDemandSearchDto.userMobile)!''}" class="text-store text-7" type="text" />
                    <span>品种名称：</span><input name="breedName" value="${(page.params.wxDemandSearchDto.breedName)!''}" class="text-store text-7" type="text" />
                    <span>状态：</span><div id="select-bg"><span><select name="status" value="${(page.params.wxDemandSearchDto.status)!''}">
                    	<option value="">全部</option>
                   		<#if statusMap??>
                        	<#list statusMap?keys as key>
                        		<option value="${key!''}" <#if page.params.wxDemandSearchDto.status == key>selected</#if>>${statusMap[key]!''}</option>
                        	</#list>
                        </#if>
                    </select></span></div>
                    <br/><br/>
                    <span>信息来源：</span><div id="select-bg"><span><select name="applyResource"" value="${(page.params.wxDemandSearchDto.applyResource)!''}">
                    	<option value="">全部</option>
                   		<#if resourceMap??>
                        	<#list resourceMap?keys as key>
                        		<option value="${key!''}" <#if page.params.wxDemandSearchDto.applyResource == key>selected</#if>>${resourceMap[key]!''}</option>
                        	</#list>
                        </#if>
                    </select></span></div>
                    <span>创建时间：</span>
                    <input class="text text-date" id="wdate1" type="text" name="startDate" value="${(page.params.wxDemandSearchDto.startDate)!''}"/> 至 <input class="text text-date" id="wdate2" type="text" name="endDate" value="${(page.params.wxDemandSearchDto.endDate)!''}"/>
                    <span></span><input type="submit" class="btn-blue" id="btn-blue" value="查询" />
                    <span></span><input type="button" class="btn-hui" onclick="$(':input','#conditionForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');" value="清空" />
                    </li>
                </ul>
            </form>
            
            <div class="use-item1" style=" margin-top:20px;">
				<@shiro.hasPermission name="求购信息管理-新增求购信息">
                	<span class="btn-add mb10">
						<a href="#">添加求购信息</a>
					</span>
                </@shiro.hasPermission>
				<!-- 表格 -->
                <table id="wxDemandTable" class="table-store" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="5%">编号</th>        <!-- 1.编号 -->
                        <th width="5%">发布人</th>     <!-- 2.发布人 -->
                        <th width="7%">联系电话</th>   <!-- 3.联系电话 -->
                        <th width="8%">信息来源 </th>   <!-- 4.信息来源 -->
                        <th width="8%">品种名称</th>   <!-- 5.品种名称 -->
                        <th width="5%">规格</th>       <!-- 6.规格 -->
                        <th width="8%">价格</th>       <!-- 7.价格 -->
                        <th width="5%">求购数量</th>   <!-- 8.求购数量 -->
                        <th width="8%">产地 </th>       <!-- 9.产地 -->
                        <th width="5%">创建时间</th>   <!-- 10.创建时间 -->
                        <th width="5%">更新时间</th>   <!-- 11.更新时间 -->
                        <th width="5%">状态</th>       <!-- 12.状态 -->
                        <th width="5%">处理人</th>     <!-- 13.处理人 -->
                        <th width="5%">处理时间</th>   <!-- 14.处理时间 -->
                        <th width="6%">操作</th>       <!-- 15.操作 -->
                    </tr>
                    <#if (page.results?size>0)>
	                    <#list page.results as wxDemand>
	                    	<tr>
        	<!-- 1.编号 -->      <td class="checkDemandId" rel="${(wxDemand.demandId)!''}">${(wxDemand.demandId)!''}</td>                
	        <!-- 2.发布人 -->    <td class="checkUserName">${(wxDemand.userName)!''}</td>
	        <!-- 3.联系电话 -->	<td class="checkUserMobile">${(wxDemand.userMobile)!''}</td>  		      
	        <!-- 4.信息来源 -->  <td class="checkDemandResource" rel="${(wxDemand.applyResource)!''}">                                              
									<#if resourceMap??>
			                   			<#list resourceMap?keys as key>
			                   				<#if wxDemand.applyResource == key>
			                   					${resourceMap[key]!''}
			                   				</#if>
			                    		</#list>
		                   			</#if>
	                    		</td>
	        <!-- 5.品种名称 -->	<td class="checkBreedName">${(wxDemand.breedName)!''}</td>                       
	        <!-- 6.规格 -->	    <td class="checkBreedStandardLevel">${(wxDemand.breedStandardLevel)?default('不限')}</td>
	        <!-- 7.价格 -->	    <td class="checkBreedPrice">${(wxDemand.breedPrice)?default('面议')}</td>          
	        <!-- 8.求购数量-->	<td class="checkQtyUnitQty">${(wxDemand.qtyUnitQty)?default('面议')}</td>           
	        <!-- 9.产地  -->  	<td class="checkBreedPlace">${(wxDemand.breedPlace)?default('不限')}</td>          
	        <!-- 10.创建时间 -->	<td class="checkCreateTime">${(wxDemand.createTime?string("yyyy-MM-dd"))!''}</td>    
	        <!-- 11.更新时间 --> <td class="checkUpdateTime">${(wxDemand.updateTime?string("yyyy-MM-dd"))!''}</td>    
	        <!-- 12.状态 -->     <td class="checkStatus" rel="${(wxDemand.status)!''}">                                                         
	                    			<#if statusMap??>
			                   			<#list statusMap?keys as key>
			                   				<#list colorMap?keys as c>
		                   						<#if key == c>
		                   							<#if wxDemand.status == key>
			                   							<font color="${colorMap[c]!'' }">${statusMap[key]!'' }</font>
				                   					</#if>
				                   				</#if>
		                    				</#list>
			                    		</#list>
		                   			</#if>                                                           
	                    		</td>
	        <!-- 13.处理人 -->	<td class="checkApproverName">${(wxDemand.approverName)!''}</td>                        
	        <!-- 14.处理时间 -->	<td class="checkApproveTime">${(wxDemand.approveTime?string("yyyy-MM-dd"))!''}</td>     
	        <!-- 15.操作 -->     <td class="opr-btn">
									<@shiro.hasPermission name="求购信息管理-查看求购信息">
					                	<span class="operate-5 checkDemandPop" title="查看" onclick="javascript:checkDemand('${(wxDemand.demandId)!''}', '${(wxDemand.applyResource)!''}');"></span>							
					                </@shiro.hasPermission>  
								</td>
	                    	</tr>
	                    </#list>
	                 
	                <#else>
	               			<tr>
	                    		<td colspan="15">没有数据!</td>
	                    	</tr>
                    </#if>
                    
                </table>
            </div>
            
            <!-- 分页 -->
			<@tools.pages page=page form="conditionForm"/>
        </div>
    </div>
    
<!-- 查看求购信息  start-->
	<div id="checkDemandDialog" class="main" style="display:none;">
		 <form id="checkDemandForm" action="" method="post">
	        <ul class="store-add">
	            <li>
                    <span class="inp_width1">发布人：</span><span id="check_userName"></span>
                </li>
                
                <li>
                    <span class="inp_width1">联系电话：</span><span id="check_userMobile"></span>
                </li>
                
                <li>
                    <span class="inp_width1">品种名称：</span><span id="check_breedName"></span>
                </li>
                
                <li>
                    <span class="inp_width1">规格：</span><span id="check_breedStandardLevel"></span>
                </li>
                
                <li>
                    <span class="inp_width1">数量：</span><span id="check_qtyUnitQty"></span>
                </li>
                
                <li>
                    <span class="inp_width1">价格：</span><span id="check_breedPrice"></span>
                </li>
                
                <li>
                    <span class="inp_width1">产地：</span><span id="check_breedPlace"></span>
                </li>
                
                <li>
                    <span class="inp_width1">描述：</span><span id="check_depict"></span>
                </li>
                
                <li style="width:100%; height:100px;">
                	<span class="inp_width1">备注：</span><textarea id="check_remarks" class="text-store text-16"></textarea><span></span>
                	<b></b>
                </li>
                
                <li class="clearfix" style="width:100%;margin-top:15px;margin-bottom:25px;">
                	<input id="check_demandId" name="demandId" type="hidden" />
	                <input id="check_applyResource" name="applyResource" type="hidden" />
                	<span class="inp_width1">&nbsp;</span>
                	<input id="check_demandValid" type="button" class="btn-blue" value="有效"/>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	<input id="check_demandInvalid" type="button" class="btn-hui" value="无效"/>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	<input id="check_demandReturn" type="button" class="btn-hui" value="返回" />
                </li>
	        </ul>
		</form>
	</div>
<!-- 查看求购信息 over  -->

<!-- 添加求购信息  start-->
	<div id="addDemandDialog" class="main" style="display:none;">
		 <form id="addDemandForm" action="/bossWxDemand/addDemand" method="post">
	        <ul class="store-add">
	            <li>
	            	<span class="inp_width1"><i class="red">*</i> 发布人：</span><input id="userName" name="userName" class="text-store text-16" type="text" />
               		<b></b>
                </li>
 
                <li>
                	<span class="inp_width1"><i class="red">*</i> 联系电话：</span><input id="userMobile" name="userMobile" class="text-store text-16" type="text" />
               		<b></b>
                </li>
             
                <li>
                	<span class="inp_width1"><i class="red">*</i> 品种名称：</span>
                	<input id="breedId" name="breedId" type="hidden" />
                	<input id="breedName" name="breedName" class="text-store text-16" type="text" />
                	<span>如：三七</span>
                	<b></b>
                </li>
               
                <li>
                	<span class="inp_width1">  规格：</span><input id="breedStandardLevel" name="breedStandardLevel" class="text-store text-16" type="text" />
                    <span>如：120头</span>
                    <b></b>
                </li>
             
                <li>
                    <span class="inp_width1">  数量：</span><input id="qtyUnitQty" name="qtyUnitQty" class="text-store text-16" type="text" />
                    <span>如：100公斤</span>
                    <b></b>
                </li>
      
                <li>
                    <span class="inp_width1">  价格：</span><input id="breedPrice" name="breedPrice" class="text-store text-16" type="text"/>
                    <span>如：120元/公斤</span>
                    <b></b>
                </li>
           
                <li>
                    <span class="inp_width1">  产地：</span><input id="breedPlace" name="breedPlace" class="text-store text-16" type="text"/>              
                	<span>如：云南</span>
                	<b></b>
                </li>
          
                <li style="width:100%; height:100px;">
                	<span class="inp_width1">  描述：</span><textarea id="depict" name="depict" class="text-store text-16"></textarea><span></span>
                	<b></b>
                </li>

                <li class="clearfix" style="width:100%;margin-top:15px;margin-bottom:25px">
	            	<span class="inp_width1">&nbsp;</span><input type="submit" class="btn-blue" value="提交" />
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" class="btn-hui" value="重置" />
                </li>
	        </ul>
		</form>
	</div>
<!-- 添加供应信息 over  -->

<!-- pageCenter over -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-autocomplete/jquery.autocomplete.js"></script>
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

	
	// 弹框
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

    //查看求购信息
    function checkDemand(demandId, applyResource){
    	$.ajax({
				async : false,
				cache : false,
				type : "GET",
				data : {"demandId":demandId, "applyResource":applyResource},
				dataType : "json",
				url : "/bossWxDemand/getDemand",
				success : function(data) {
					var ok = data.ok;
					if(ok==true){
						var obj = data.obj;
						
						//重置窗口
						$('#check_remarks').val('').parent('li').show();
						$('#check_demandValid').show();
						$('#check_demandInvalid').show();
						
						//填充数据
						$.each(obj,function(key,value){
							if(key == 'demandId'){
								$('#check_demandId').val(value);
							}else if(key == 'applyResource'){
								$('#check_applyResource').val(value);
							}else if(key == 'remarks'){
								$("#check_"+key).val(value);
							}else{
								$("#check_"+key).text(value);
							}
					 		
					 		if(key == 'status'){
								if(value != 0){
									//$('#check_remarks').parent('li').hide();
									$('#check_demandValid').hide();
									$('#check_demandInvalid').hide();
								}
							}
					 	});
						
						//加载窗口
						$("#checkDemandDialog").dialog({
						 	 title:'求购信息',
						 	 width : '40%',
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
	
	//关闭查看供应信息弹窗
    $('#check_demandReturn').click(function(){
    	$("#checkDemandDialog").dialog("close");
    });
	
	//信息有效
	$('#check_demandValid').click(function(){
		$('#checkDemandForm').attr('action','/bossWxDemand/checkDemandValid');
		$('#checkDemandForm').submit();
	});
	
	//信息无效
	$('#check_demandInvalid').click(function(){
		$('#checkDemandForm').attr('action','/bossWxDemand/checkDemandInvalid');
		$('#checkDemandForm').submit();
	});
    
    //添加求购信息
	$('.btn-add a').click(function(){
		resetDialogForm();
		$("#addDemandDialog").dialog({
		 	 title:'添加求购信息',
		 	 width : '45%',
		     modal: true,
		     autoOpen: true
		 });
	});
	
	//重置新增求购信息表单
    function resetDialogForm(){
    	addDemandForm.resetForm();
    	addDemandForm.resetStatus();
    };
    
    //验证查看求购信息表单
    var checkDemandForm = $("#checkDemandForm").Validform({
	    tiptype:function(msg,o,cssctl){
			if(!o.obj.is("form")){
				var objtip=$(o.obj).parent('li').children('b');
				cssctl(objtip,o.type);
				objtip.text(msg);
			}	
		},
	    ajaxPost:true,
	    showAllError:true,
	    beforeSubmit:function(curform){
	    	//验证参数是否正确
			var demandId = $('#check_demandId').val();
			var applyResource = $('#check_applyResource').val();
			if(demandId == '' || applyResource == ''){
				tips('求购信息参数错误！');
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
	checkDemandForm.addRule([
	{
        ele:"#check_remarks",
        datatype:"*0-500",
        ignore:"ignore",
        nullmsg:"请填写备注！",
        errormsg:"请填写1到500位任意字符！",
        sucmsg:""
    }
	]);
	
    //验证添加求购信息表单
    var addDemandForm = $("#addDemandForm").Validform({
	    tiptype:function(msg,o,cssctl){
			if(!o.obj.is("form")){
				var objtip=$(o.obj).parent('li').children('b');
				cssctl(objtip,o.type);
				objtip.text(msg);
			}	
		},
	    ajaxPost:true,
	    showAllError:true,
	    ajaxurl:{
	        success:function(data,obj){
	            var ok = data.ok;
	            if(ok==undefined){
	            	var objtip=$(obj).parent('li').children('b');
					objtip.text('网络繁忙，请稍后再试！');
	            }
	        },
	        error:function(data,obj){
	        	var readyState = data.readyState;
	            if(readyState != 0){
    	        	var objtip=$(obj).parent('li').children('b');
					objtip.text('网络繁忙，请稍后再试！');
	            }
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
	addDemandForm.addRule([
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
	        ajaxurl:"/bossWxDemand/checkBreedName",
	        datatype:"*1-50",
	        nullmsg:"请填写品种名称！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#breedStandardLevel",
	        datatype:"*0-50",
	        ignore:"ignore",
	        nullmsg:"请填写规格！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#qtyUnitQty",
	        datatype:"*0-50",
	        ignore:"ignore",
	        nullmsg:"请填写数量！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#breedPrice",
	        datatype:"*0-50",
	        ignore:"ignore",
	        nullmsg:"请填写价格！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#breedPlace",
	        datatype:"*0-50",
	        ignore:"ignore",
	        nullmsg:"请填写产地！",
	        errormsg:"请填写1到50位任意字符！",
	        sucmsg:""
	    },
	    {
	        ele:"#depict",
	        datatype:"*0-500",
	        ignore:"ignore",
	        nullmsg:"请填写描述！",
	        errormsg:"请填写1到500位任意字符！",
	        sucmsg:""
	    }
	    
	]);
	
	
	//品种名称联想
	$('#breedName').autocomplete({  
		zIndex:10001, 
		deferRequestBy:200,//keyUp之后发起请求的间隔时间  Default: 0
		serviceUrl: '/bossWxDemand/getBreedNames',//ajax后台请求地址
		onSearchComplete: function (query) {//请求成功之后的回调
		
		},
		onSelect: function (suggestion) {
			//下拉层选择触发事件
    		addDemandForm.check(false, "#breedName");
   		}
    }); 
    
	
</script>
</body>
</html>