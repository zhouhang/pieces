<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材-聚好药商，卖珍药材-找回密码</title>
	 <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
 	 <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/common.css" />
 	 <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
	 <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
	 <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>

<!-- 头部  -->
<div class="topper sty1">
  <div class="area-1200">
    <div class="logo clearfix">
   		<a href="${JOINTOWNURL}">聚好药商，卖好药材！</a>
        <span>找回密码</span>
    </div>
  </div>
</div>
<!-- 头部 end  -->
<div class="area-1200">
    <div class="find-pswd">
        
            <ul class="form-1">
            	<li>
            		<label></label>
            		<span id="mobileMsg" style="color: #f00">${mobileMsg}${sendEmail }</span>
            	</li>
                <li>
                    <label>请选择验证方式：</label>
                    <select style="border:1px solid #ccc; width:203px; height:32px; line-height:24px; padding:6px 0; *vertical-align: bottom;" name="verityMethod" id="yz">
	                    <option value="mobileVerity">手机验证</option>
	                    <option value="emailVerity">邮箱验证</option>
                    </select>
                </li>
            </ul>
            <form action="/findBackPwd/mobileVerity" method="post" id="findPwd">
            <ul class="form-1" id="iphone" style="*width:680px;">

                    <li>
                        <label>会员名：</label>
                        <input type="text" value="" id="memberMobName" name="memberMobName" ucmob="memberMobile" class="text-1" ajaxurl="/findBackPwd/memberNameIsExist" datatype="ucmob,da5-25" nullmsg="会员名不能为空！"  errormsg="填写错误，请重新输入！" sucmsg=""/>
                    </li>
                    <li>
                        <label>手机号码：</label>
                        <input type="text" value="" id="memberMobile" name="memberMobile" class="text-1" ajaxurl="/findBackPwd/mobIsExist?memberMobName=" datatype="mo"  nullmsg="手机号码不能为空！" errormsg="请输入正确手机号！"/>
                        
                    </li>
                    <li><label></label>
                    		<input type="button" id="getMobileCode" class="yzm-btn2" value="获取短信验证码" /></li>
                    <li>
                        <label>手机验证码：</label>
                        <input type="text" value="" name="inputMobileCode" class="text-1" ajaxurl="/findBackPwd/verityMobileCode" maxlength="6" datatype="n1-6" nullmsg="验证码不能为空！"  errormsg=""/>
                    </li>
                    <li>
                    <label></label>
                    <input type="submit" value="下一步" id="mobBnt" class="yellow-btn" disabled="disabled" />
                </li>
                </ul>
				</form>
				<!-- 邮箱找回密码，使用新单提交，不与短信找回密码公用一个表单 -->
				<form action="/findBackPwd/emailFindBack" method="post" id="emailFindPwd">
                <ul class="form-1 clearfix" id="email" style="display: none;" style="*width:680px;">
                    <li>
                        <label>会员名：</label><!--   -->
                        <input type="text" value="" name="memberEmailName" class="text-1" ajaxurl="/findBackPwd/memberNameIsExist" datatype="da5-25" nullmsg="会员名不能为空"  errormsg="会员名5-25个字符,只能是数字或字母！" sucmsg=""/>
                    </li>
                    <li>
                    <label></label>
                    <input type="submit" id="emailBnt" value="下一步" class="yellow-btn" />
                </li>
                </ul>
                </form>
        
    </div>
</div>

<!-- 底部  -->
<#include "common/footer.ftl">
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/find-password.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
	function Select(){
	    if($('#yz>option:selected').text() == '手机验证'){
	        $('#iphone').css('display','block');
	        $('#email').css('display','none');
	        $("input[name='memberMobName']").focus();
	    }
	    if($('#yz>option:selected').text() == '邮箱验证'){
	        $('#iphone').css('display','none');
	        $('#email').css('display','block');
	        $('form span').html("");
	        $("input[name='memberEmailName']").focus();
	    }
	}
	Select();
	$('#yz').change(function(){
	    Select();
	})
	
    //聚焦
   /*  $("select[name='verityMethod']").change(function(){
    	var _mobile = $("select[name='verityMethod']").val();
    	if(_mobile=="mobileVerity"){
    		$("input[name='memberMobName']").focus();
    	}else if(_mobile=="emailVerity"){
    		$("input[name='memberEmailName']").focus();
    	}
    });
     */
    //获取短信验证码后，60s可以重新发送 
    var i = 60;
	var time = function(){
		i-=1 ;
		$("#getMobileCode").val('(' + i + ')秒后重新获取');
		$("#getMobileCode").attr("disabled","disabled");
		if(i==0){
			$("#getMobileCode").removeAttr("disabled");
			$("#getMobileCode").val("重新获取验证码");
			i=60;
			return;
		}
		setTimeout(time,1000);
	}
	var num=0;
	
	//获取手机短信校验码（bind为防止重复提交）
	$("#getMobileCode").click(function(){
		//防止重复提交
		//$("#getMobileCode").attr("disabled","disabled");
		var memberMobile = $("#memberMobile").val();
		if(memberMobile==null || memberMobile==""){
			bghui();
			Alert({str:"手机号码不能为空"});
			return;
		}
		var reg = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$|177[0-9]{8}$/;
		if(!reg.test(memberMobile)){
			bghui();
			Alert({str:"需填写有效的11位手机号码"});
			return;
		}
		var memberMobName=$("#memberMobName").val();
		if(memberMobName == null || memberMobName==""){
			bghui();
			Alert({str:"会员名不能为空"});
			return;
		}
		$.post("/findBackPwd/getMobileCodeXgmm",{"memberMobName":memberMobName,"memberMobile":memberMobile},function(data){
			if(data=='n'){
				//alert("发送失败");
			}else{
				time();
			}
		});
	});
	
	//短信找回密码提交
	/* $("#mobBnt").click(function(){
		$("input[name='memberEmailName']").removeAttr("datatype");
		$("#findPwd").attr("action","/findBackPwd/mobileVerity");
		$("#findPwd").submit();
	}); */
	
	//邮件提交
	/* $("#emailBnt").click(function(){
		var _emailVerity = $("#yz").val();
		var _memberNameByEmail = $("input[name='memberEmailName']").val();
		if(_memberNameByEmail==null || _memberNameByEmail==""){
			//alert("会员名不能为空!");
			return;
		}
		var memberNameReg = /^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]{5,25}$/;
		if(!memberNameReg.test(_memberNameByEmail)){
			//alert("会员名至少5个字符,最多25个字符！");
			return;
		}
		
		if(_emailVerity=="emailVerity"){
			$("input[name='memberMobName']").removeAttr("datatype");
			$("input[name='memberMobile']").removeAttr("datatype");
			$("input[name='inputMobileCode']").removeAttr("datatype");
			$("#findPwd").attr("action","/findBackPwd/emailFindBack");
			//alert($("#findPwd").attr("action"));
			$("#findPwd").submit();
			//window.location.href="/findBackPwd/emailFindBack?memberEmailName=" + _memberNameByEmail;
		}
		
	}); */
	
	//短信找回密码
	$(function(){
		$("#findPwd").Validform({
			tiptype:3,
			beforeSubmit:function(curform){//表单提交前再次验证验证码是否正确
				var mobile = $("input[name=inputMobileCode]").val();
				var flag = false;
				$.ajax({
					url:"/findBackPwd/verityMobileCode",
					data:"param=" + mobile,
					async:false,
					success:function(html){
						if(html == 'y'){
              				flag = true;
              			}else{
                  			$("input[name=inputMobileCode]").next("span").addClass("Validform_wrong");
                  			$("input[name=inputMobileCode]").next("span").text("短信验证码错误!");
              				flag = false;
              			}
					}
				});
				return flag;
			}
		});
	})
	
	//会员名和手机号变更时，自动校验
	$.Datatype.ucmob = function(gets,obj,curform,regxp){
		var name = obj.attr('ucmob');
		if($('input[name='+ name +']', curform)[0].value != ''){
			$('input[name='+ name +']', curform)[0].validform_lastval = '';
			//$('input[name='+ name +']', curform).trigger("blur");
		}
		return true;
	}
	
	//邮箱找回密码
	$(function(){
		$("#emailFindPwd").Validform({
			tiptype:4,
			ajaxPost:true,
			callback:function(data){
				if(data.ok){
					location.href="/findBackPwd/sendEmail";
				}else{
					bghui();
					Alert({str:data.errorMessages[0].message});
				}
			}
		});
	})
	
	$(function(){
		$("#memberMobName").change(function(){
			var memberMobName=$('#memberMobName').val();
			var ajaxurl="/findBackPwd/mobIsExist?memberMobName="+memberMobName;
			$("#memberMobile").attr("ajaxurl",ajaxurl);
		});
		$("#memberMobile").change(function(){
			var memberMobName=$('#memberMobName').val();
			var ajaxurl="/findBackPwd/mobIsExist?memberMobName="+memberMobName;
			$("#memberMobile").attr("ajaxurl",ajaxurl);
		});
		
		$("#memberMobName").blur(function(){
			$("#memberMobile").focus();
			$("#memberMobile").trigger("blur");
		});
	});
	
	$(function(){
		var memberMobName=$('#memberMobName').val();
		var ajaxurl="/findBackPwd/mobIsExist?memberMobName="+memberMobName;
		$("#memberMobile").attr("ajaxurl",ajaxurl);
	});
	
	$(function(){
		//防止按钮加载不上的问题
		$("#mobBnt").removeAttr("disabled");
	});
	
</script>
</body>
</html>