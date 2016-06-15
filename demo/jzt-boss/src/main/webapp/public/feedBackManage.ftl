<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>统一任务管理</title>
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
            <h1 class="title1">统一任务管理</h1>
            
            <!-- 查询条件 -->
            <form id="conditionForm" action="/feedBackManage" method="get">
                <ul class="form-search">
                    <li>
                    <span>业务点：</span>
                    <div id="select-bg" style="width:180px;">
	                    <span style="width:170px;">
		                    <select name="type" style="width:170px;">
		                    	<option value="" <#if (page.params.homePageFeedBackDto.type=='')>selected</#if>>全部</option>
		                        <option value="1" <#if (page.params.homePageFeedBackDto.type=='1')>selected</#if>>快捷入口--免费采购</option>
		                        <option value="2" <#if (page.params.homePageFeedBackDto.type=='2')>selected</#if>>快捷入口--我要卖货</option>
		                        <option value="3" <#if (page.params.homePageFeedBackDto.type=='3')>selected</#if>>快捷入口--我要融资</option>
		                        <option value="4" <#if (page.params.homePageFeedBackDto.type=='4')>selected</#if>>珍药金融--帮我融资</option>
		                        <option value="5" <#if (page.params.homePageFeedBackDto.type=='5')>selected</#if>>页底--立即反馈</option>
		                    </select>
	                    </span>
                    </div>
                    <span>&nbsp;&nbsp;&nbsp;状态：</span>
                    <div id="select-bg" style="width:180px;">
	                    <span style="width:170px;">
		                    <select name="status" style="width:170px;">
		                    	<option value="" <#if (page.params.homePageFeedBackDto.status=='')>selected</#if>>全部</option>
		                        <option value="0" <#if (page.params.homePageFeedBackDto.status=='0')>selected</#if>>未处理</option>
		                        <option value="1" <#if (page.params.homePageFeedBackDto.status=='1')>selected</#if>>有效</option>
		                        <option value="2" <#if (page.params.homePageFeedBackDto.status=='2')>selected</#if>>无效</option>
		                    </select>
	                    </span>
                    </div>
                    <span>&nbsp;&nbsp;&nbsp;联系电话：</span>
                    <input class="text" id="mobile" type="text" name="mobile" value="${(page.params.homePageFeedBackDto.mobile)!''}"/>
                    </li>
                    <li>
                    <span>发布时间：</span>
                    <input class="text text-3 mr10" id="wdate1" type="text" name="startDate" value="${(page.params.homePageFeedBackDto.startDate)!''}"/>
                                       至&nbsp;&nbsp;&nbsp;<input class="text text-3 mr10" id="wdate2" type="text" name="endDate" value="${(page.params.homePageFeedBackDto.endDate)!''}"/>
                    <span></span><input type="submit" class="btn-blue" id="btn-blue" value="查询" />
                    </li>
                </ul>
            </form>
            
            <div class="use-item1" style=" margin-top:20px;">
				<!-- 表格 -->
                <table id="feedBackTable" class="table-store" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="2%">ID</th>        <!-- 1.编号 -->
                        <th width="8%">业务点</th>     <!-- 2.业务点 -->
                        <th width="20%">内容</th>   <!-- 3.内容 -->
                        <th width="5%">联系电话</th>   <!-- 4.联系电话 -->
                        <th width="10%">发布时间</th>   <!-- 5.发布时间 -->
                        <th width="5%">状态</th>       <!-- 6.状态 -->
                        <th width="0%" style="display:none">备注</th>       <!-- 6.备注 -->
                        <th width="10%">处理时间</th>       <!-- 7.处理时间 -->
                        <th width="5%">处理人</th>   <!-- 8.处理人 -->
                        <th width="5%">操作</th>       <!-- 9.操作 -->
                    </tr>
                    <#if (page.results?size>0)>
	                    <#list page.results as feedback>
	                    	<tr>
        	<!-- 1.编号 -->      <td class="checkDemandId" rel="${(feedback.homePageId)!''}">${(feedback.homePageId)!''}</td>                
	        <!-- 2.业务点 -->    <td class="checkUserName">
	        						<#if (feedback.type==1)>快捷入口--免费采购</#if>
	        						<#if (feedback.type==2)>快捷入口--我要卖货</#if>
	        						<#if (feedback.type==3)>快捷入口--我要融资</#if>
	        						<#if (feedback.type==4)>珍药金融--帮我融资</#if>
	        						<#if (feedback.type==5)>页底--立即反馈</#if>
	        				    </td>
	        <!-- 3.内容 -->	   <td class="checkUserMobile">${(feedback.content)!''}</td>  		      
	        <!-- 4.联系电话 -->  <td class="checkUserMobile">${(feedback.mobile)!''}</td>
	        <!-- 5.提交时间 -->  <td class="checkCreateTime">${(feedback.createTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>    
	        <!-- 6.状态 -->     <td class="checkStatus">                                                         
	                    			<#if (feedback.status==0)><span style="color:blue">未处理</span></#if>
	        						<#if (feedback.status==1)><span style="color:green">有效</span></#if>
	        						<#if (feedback.status==2)><span style="color:red">无效</span></#if>
	                    	   </td>
	        <!-- 7.备注 -->  <td class="checkUserMobile" style="display:none">${(feedback.remark)!''}</td>
	        <!-- 8.更新时间 -->  <td class="checkUpdateTime">${(feedback.oprateTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>    
	        <!-- 9.处理人 -->	   <td class="checkApproverName">${(feedback.operator)!''}</td>                        
	        <!-- 10.操作 -->      <td class="opr-btn">
	        						<@shiro.hasPermission name="统一任务管理-统一任务操作">
					                	<span class="operate-5 checkDemandPop" title="操作" onclick="javascript:lookFeedBack(this);"></span>							
									</@shiro.hasPermission>
								</td>
	                    	</tr>
	                    </#list>
	                 
	                <#else>
	               			<tr>
	                    		<td colspan="9">没有数据!</td>
	                    	</tr>
                    </#if>
                    
                </table>
            </div>
            
            <!-- 分页 -->
			<@tools.pages page=page form="conditionForm"/>
        </div>
    </div>

<!-- 操作信息  start-->
	<div id="oprFeedBackDialog" class="main" style="display:none;">
		 <form id="oprFeedBackForm" action="/feedBackManage/operFeedBack">
	        <ul class="store-add">
				<input type="hidden" name="homePageId" id="feedbackId" value="">
	            <li>
	            	<span class="inp_width1"> 业务点：</span>
	            		<span id="type" name="type"/></span>
               		<b></b>
                </li>
                <li>
                	<span class="inp_width1"> 内容：</span>
                		<span id="content" name="content"/></span>
               		<b></b>
                </li>
             
                <li>
                	<span class="inp_width1"> 联系电话：</span>
                		<span id="mobile" name="mobile"/></span>
               		<b></b>
                </li>
                <li>
                	<span class="inp_width1">  提交时间：</span>
                		<span id="createTime" name="createTime"/></span>
                    <b></b>
                </li>
                <li>
                    <span class="inp_width1"><i class="red">*</i>  状态：</span>
                    <div id="select-bg">
	                    <span>
		                    <select name="status" id="status">
		                        <option value="1">有效</option>
		                        <option value="2">无效</option>
		                    </select>
	                    </span>
                    </div>
                    <b></b>
                </li>
                <li>
                    <span class="inp_width1"><i class="red">*</i>  备注：</span>
                    	<textarea id="remark" name="remark" class="text-store text-16"></textarea><span></span>
                    <b></b>
                </li>
                <li class="clearfix" style="width:100%;margin-top:15px;margin-bottom:25px">
	            	<span class="inp_width1">&nbsp;</span><input type="submit" class="btn-blue" value="确定" />
	            	&nbsp;&nbsp;&nbsp;<input id="closeFeedBack" type="reset" class="btn-hui" value="取消" />
                </li>
	        </ul>
		</form>
	</div>
<!-- 操作信息 over  -->
<!-- 查看操作信息  start-->
	<div id="lookFeedBackDialog" class="main" style="display:none;">
	        <ul class="store-add">
	            <li>
	            	<span class="inp_width1"> 业务点：</span>
	            		<span id="type" name="type"/></span>
               		<b></b>
                </li>
                <li>
                	<span class="inp_width1"> 内容：</span>
                		<span id="content" name="content"/></span>
               		<b></b>
                </li>
             
                <li>
                	<span class="inp_width1"> 联系电话：</span>
                		<span id="mobile" name="mobile"/></span>
               		<b></b>
                </li>
                <li>
                	<span class="inp_width1">  提交时间：</span>
                		<span id="createTime" name="createTime"/></span>
                    <b></b>
                </li>
                <li>
                    <span class="inp_width1"><i class="red">*</i>  状态：</span>
                    	<input id="status" name="status" value="" class="text-store text-7" type="text" disabled="disabled"/>
                    <b></b>
                </li>
                <li>
                    <span class="inp_width1"><i class="red">*</i>  备注：</span>
                    	<textarea id="remark" name="remark" class="text-store text-16" disabled="disabled"></textarea><span></span>
                    <b></b>
                </li>
                <li class="clearfix" style="width:100%;margin-top:15px;margin-bottom:25px">
	            	<span class="inp_width1">&nbsp;</span>
	            	&nbsp;&nbsp;&nbsp;<input id="closeLookFeedBack" type="reset" class="btn-hui" value="返回" />
                </li>
	        </ul>
	</div>
<!-- 查看操作信息 over  -->
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
    
    //添加求购信息
	function lookFeedBack(obj){
		var status = $.trim($(obj).parents("tr").children('td').eq(5).text());
		if(status == '未处理'){
			resetFeedBackForm();
			setValues($(obj));
			$("#oprFeedBackDialog").dialog({
				 title:'处理任务',
			 	 width : '45%',
			     modal: true,
			     autoOpen: true
			 });
		}else{
			setValues($(obj));
			$("#lookFeedBackDialog").dialog({
				 title:'查看任务',
			 	 width : '45%',
			     modal: true,
			     autoOpen: true
			 });
		}
		
	};
    
    function setValues(jobj){
    	var par = jobj.parents("tr");
    	var id = $.trim(par.children('td').eq(0).text());
    	var type = $.trim(par.children('td').eq(1).text());
    	var content = $.trim(par.children('td').eq(2).text());
    	var mobile = $.trim(par.children('td').eq(3).text());
    	var createTime = $.trim(par.children('td').eq(4).text());
    	var status = $.trim(par.children('td').eq(5).text());
    	var remark = $.trim(par.children('td').eq(6).text());
    	if(status == '未处理'){
    		$("#oprFeedBackForm").find("#feedbackId").val(id);
        	$("#oprFeedBackForm").find("#type").text(type);
        	$("#oprFeedBackForm").find("#content").text(content);
        	$("#oprFeedBackForm").find("#mobile").text(mobile);
        	$("#oprFeedBackForm").find("#createTime").text(createTime);
        	$("#oprFeedBackForm").find("#status").find("option:contains('"+status+"')").attr("selected",true);
        	$("#oprFeedBackForm").find("#remark").text(remark);
    	}else{
        	$("#lookFeedBackDialog").find("#type").text(type);
        	$("#lookFeedBackDialog").find("#content").text(content);
        	$("#lookFeedBackDialog").find("#mobile").text(mobile);
        	$("#lookFeedBackDialog").find("#createTime").text(createTime);
        	$("#lookFeedBackDialog").find("#status").val(status);
        	$("#lookFeedBackDialog").find("#remark").text(remark);
    	}
    	
    }
	
	//重置新增求购信息表单
    function resetFeedBackForm(){
    	oprFeedBackForm.resetForm();
    	oprFeedBackForm.resetStatus();
    };
    var oprFeedBackForm = $("#oprFeedBackForm").Validform({
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
    oprFeedBackForm.addRule([
		{
	        ele:"#remark",
	        datatype:"*1-500",
	        nullmsg:"请填写备注！",
	        errormsg:"请填写1到500位任意字符！",
	        sucmsg:""
	    }
	]);
    
  //关闭弹层
    $('#closeFeedBack').click(function(){
    	$("#oprFeedBackDialog").dialog("close");
    });
	
    $('#closeLookFeedBack').click(function(){
    	$("#lookFeedBackDialog").dialog("close");
    });
  
</script>
</body>
</html>