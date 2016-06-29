<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>完善联系人信息</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/form.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
</head>
<body>
<div class="area-1100">
<!-- 头部  -->
    <div class="topper sty1 clearfix">
		  <div class="area-1100">
		 	 <div class="logo">
		        <a class="logo_a" href="http://www.54315.com">聚好药商，卖真药材</a>
		        <span>完善联系人信息</span>
		    </div>
		  </div>    
	</div>
<!-- 头部 end  -->
</div>
<div class="complate-wapper pt20">
    <div class="area-1100 border-1 bgfff">
        <h3 class="title"><strong>欢迎您，${user.userName!'' }：</strong>完善以下信息，能使您获得更多的中药材贸易机会和服务</h3>
        <div class="step">
            <span class="step2"></span>
        </div>
    </div>
    <div class="mt5"></div>
    <div class="area-1100 border-1 bgfff">
    <form action="/completeInfoGuide/saveContacterInfo" method="post" id="completeContacterInfoForm">
        <div class="content" id="len">
            <ul id="dynamic_ul_0" class="form">
                <li>
                    <label class="lab"><i class="red">*</i> 姓名：</label>
                    <input id="name" name="ucUserContact[0].name" value="" class="text-sty2 col_333 mr10" datatype="*1-50" nullmsg="请填写您的姓名" errormsg="不可超过1-50个字符"/>
                	<#list sexMap?keys as key> 
                    	<input id="sex" type="radio"  name="ucUserContact[0].sex" value="${key }" <#if key == 1>checked="checked"</#if>/>${sexMap[key]}&nbsp;&nbsp;&nbsp;
              	 	</#list> 
              	 	 &nbsp;&nbsp;&nbsp;<span class="Validform_checktip"></span>
                </li> 
                <li>
                    <label class="lab">部门：</label>
                    <input name="ucUserContact[0].department" value="" class="text-sty2 text-wid210 col_333 mr10" ignore="ignore" datatype="*1-100"  errormsg="不可超过1-100个字符">
                    <span class="dis-in-bk">（未做部门分工，可不填写）</span>
                    <span class="Validform_checktip"></span>
                </li>
                <li>
                    <label class="lab">职务：</label>
                    <input name="ucUserContact[0].job" value="" class="text-sty2 text-wid210 col_333 mr10" ignore="ignore" datatype="*1-100"  errormsg="不可超过1-100个字符">
                    <span class="dis-in-bk">（未做部门分工，可不填写）</span>
                    <span class="Validform_checktip"></span>
                </li>
                <li>
                    <label class="lab">联系座机：</label>
                    <input  name="ucUserContact[0].phone" value="" class="text-sty2 text-wid210 col_333 mr10" ignore="ignore" datatype="n0-12"  errormsg="不可超过12位数字">
                </li>
                <li>
                    <label class="lab"><i class="red">*</i> 联系手机：</label>
                    <input id="mobile" name="ucUserContact[0].mobile" value="" class="text-sty2 text-wid210 col_333 mr10" datatype="mo" nullmsg="请输入手机号码" errormsg="需填写有效的11位手机号码"/>
                    <span class="Validform_checktip" id="mobile"></span>
                </li>
                <li>
                    <label class="lab">电子邮箱：</label>
                    <input name="ucUserContact[0].email" value="" class="text-sty2 text-wid210 col_333 mr10" ignore="ignore" datatype="e" errormsg="邮箱格式不正确!">
                </li>
            </ul>
            
            <div id="append">
                <div class="mt25 save relative">
                    <span class="fr mr80"><a name="addContact" class="add dis-in-bk" href="#" style="color: rgb(0, 136, 204);"> <strong>+</strong> 继续添加联系人</a></span>
                    <div align="center">
                    	<input type="submit" value="保 存" class="btn btn-red-grad big" id="save">
                    	<a href="javascript:void(0);" class="f14 col_blue text-sty-udline dis-in-bk pl30" id="sayGoodBye">下次再说</a>
                    </div>
                </div>
            </div>
        </div>
	</form>
    </div>
</div>
<!-- 底部  -->
<#include "common/smart_footer.ftl" /> 
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_CSS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_CSS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_CSS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
    $(function(){
    	var dynamic_index = 1 ;
        //添加联系人
        $('a[name=addContact]').on('click',function(){
            var contactBox = '<ul id="dynamic_ul_'+dynamic_index+'" class="form">'+
                    '<li>'+
                    '<label class="lab"><i class="red">*</i> 姓名：</label>'+
                    '<input id="userName" name="ucUserContact['+dynamic_index+'].name" class="text-sty2 col_333 mr10" value="" datatype="*1-50" nullmsg="请填写您的姓名" errormsg="不可超过1-50个字符"/>&nbsp;&nbsp;&nbsp;'+
                    '<#list sexMap?keys as key>'+ 
                		'<input type="radio" name="ucUserContact['+dynamic_index+'].sex" value="${key}" <#if key == 1>checked="checked"</#if>/>${sexMap[key]}&nbsp;&nbsp;&nbsp;'+
          	 		'</#list>'+
                    '&nbsp;&nbsp;&nbsp;<span class="Validform_checktip" id="name-msg"></span>'+
                    '</li>'+
                    '<li>'+
                    '<label class="lab">部门：</label>'+
                    '<input name="ucUserContact['+dynamic_index+'].department" class="text-sty2 text-wid210 col_333 mr10" value="" />'+
                    '<span class="dis-in-bk">（未做部门分工，可不填写）</span>'+
                    '</li>'+
                    '<li>'+
                    '<label class="lab">职务：</label>'+
                    '<input name="ucUserContact['+dynamic_index+'].job" class="text-sty2 text-wid210 col_333 mr10" value="" />'+
                    '<span class="dis-in-bk">（未做部门分工，可不填写）</span>'+
                    '</li>'+
                    '<li>'+
                    '<label class="lab">联系座机：</label>'+
                    '<input name="ucUserContact['+dynamic_index+'].phone" class="text-sty2 text-wid210 col_333 mr10" value="" />'+
                    '</li>'+
                    '<li>'+
                    '<label class="lab"><i class="red">*</i> 联系手机：</label>'+
                    '<input id="mobilePhone" name="ucUserContact['+dynamic_index+'].mobile" class="text-sty2 text-wid210 col_333 mr10" value="" datatype="mo" nullmsg="请输入手机号码" errormsg="需填写有效的11位手机号码"/>'+
                    ' <span class="Validform_checktip" id="mobile"></span>'+
                    '</li>'+
                    '<li>'+
                    '<label class="lab">电子邮箱：</label>'+
                    '<input name="ucUserContact['+dynamic_index+'].email" class="text-sty2 text-wid210 col_333 mr10" value="" ignore="ignore" datatype="e" errormsg="邮箱格式不正确!"/>'+
                    '</li>'+
                    '<li class="mt20"><div align="center"><span class="blue f14 text-sty-udline hand" id="removeContact">删 除</span></div> </li>'+
                    '</ul>';
            var len = $(this).parents('div[id=len]').children('ul').length;
            if(len<=4){
                $(this).parents('div[id=len]').children('ul:last').after(contactBox);
                len++;
                $(this).css('color','#0088cc');
            }else{
                $(this).css('color','#666');
            }
            dynamic_index++;
            return false;
        });
        
        /////删除
        $('body').delegate('span[id=removeContact]','click',function(){
            $('[name=addContact]').css('color','#0088cc');
            if($(this).parents('div[id=len]').children('ul').length>1){
                $(this).parents('.form').remove();
            }
        });
    });
    
    function remove(){
        $('.alert').remove();
        setTimeout('remove()', 5000);
    }
    remove();
///////////////////////////////////////////////////////////////// 表单验证 //////////////////////////////////////////////////////////
 $("#completeContacterInfoForm").Validform({
		tiptype:4,
		dragonfly:true,
		showAllError:true,
		ajaxPost:true,
		callback:function(data){
			if(data.status){
				 var Alert = '<span class="alert"><i></i>保存成功！</span>';
		         $('input[id=save]').parent('div').append(Alert);
		         
		         location.href="http://uc.54315.com";
			}else{
				bghui();//遮罩层
				Alert({str:'操作失败'});
			} 
		}
	});
	
	$("#sayGoodBye").click(function(){
		location.href="/completeInfoGuide/inFuture?type=contacter";
	});
	
	$("div.logo").click(function(){
		location.href="http://www.54315.com";
	});
	
</script>
</body>
</html>