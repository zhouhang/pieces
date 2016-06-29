<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>我的小珍-入驻珍药材</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/js/Validform/css/style.css" />
    <style>
        body{
			background:#f5f5f5;
		}
        .data-box{
			padding-top: 0;
		}
    </style>
    <script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js" type="text/javascript"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/Validform/js/Validform_v5.3.2.js"></script>	
	<script type="text/javascript" src="${RESOURCE_JS}/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${RESOURCE_CSS_WX}/js/legalize_person.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/common.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS_WX}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="clearfix main" id="base">
	<div class="basic-box-head">
		<i class="back" id="back"></i>
    <div align="center" class="inStore-title">基本资料</div>
    </div>
    <div class="info-body">
		<div class="data-box">
        <!--基本资料列表 start-->
        	<ul>
                <li style="margin-top:-3.2em;">
                    <p>登录名<span style="margin-right:1.5em;">${user.userName!''}<i></i></span></p>
                    <p class="move-right" name="pwd">登录密码<span>已设<i class="arrow-right"></i></span></p>
                    <p class="re-phone">手机号码<span>${user.mobile!''}<i class="arrow-right"></i></span></p>
                    <p class="move-right" name="com">公司/姓名<span>${user.companyName!''}<i class="arrow-right"></i></span></p>
                    <p 
                    <#if user.email!''>class="move-right" name="ema"
                    <#else>class="move-right" name="sema"
                    </#if>
                    >邮箱<span><#if user.email??>${user.email!''}<#else>未绑定</#if><i class="arrow-right" ></i></span></p></li>
                <li><p class="move-right" name="cer">实名认证<span>
                	<#if user.certifyStatus==0>
                		未认证
                	<#else>
                		已认证
                	</#if>
                <i class="arrow-right"></i></span></p></li>
                <li><p id="logout">退出<span class="move-right">
                	</span></p></li>
            </ul>
         <!--基本资料列表 end-->
		</div>
	</div>
</div>

<!-- 修改密码弹层 -->
<div class="pop-up clearfix" id="pwd">
	<div class="basic-box-head">
        <i class="back" name="base"></i>
        <div align="center" class="inStore-title">登录密码</div>
    </div>
<div class="password inStore-box info-body">
	<form action="/reset_password" id="rePasswordForm" name="rePasswordForm">
    <ul class="search-report" id="loginBox">
        <li class="code-input">原密码
        	<input style="display:none;" type="password">
            <input class="code-box password-input" type="password" placeholder="请输入原密码" name="oldPassword" ajaxurl="/match_password" datatype="todo,pwd" todo="newPassword" nullmsg="请输入原密码！">
        </li>
        <li class="code-input password-input">新密码
            <input class="code-box password-input" type="password" placeholder="请设置新密码" name="newPassword" datatype="pwd,noteq" nullmsg="请输入新密码！" noteqto="oldPassword" noteqmsg="新密码与原密码不能一致！">
        </li>
        <li class="code-input password-input">确认密码
            <input class="code-box password-input" type="password" placeholder="请再次输入"  name="surePassword" datatype="*" recheck="newPassword" nullmsg="请再输入一次新密码！" errormsg="您两次输入的账号密码不一致！">
        </li>
    </ul>
        <div><span id="rePasswordMsg" style="display:none;margin-left:0.4em;background:none;"></span></div>
    	<div class="code-enter mt20"><input type="button" id="rePasswordBtn" value="确定" class="btn-red1"/></div>
    </form>
</div>
</div>
<!-- 登录密码弹层end -->

<!-- 更改号码弹层 -->
<!--
<div class="pop-up clearfix" id="pho">
	<div class="basic-box-head" >
        <i class="back" name="base"></i>
        <div align="center" class="inStore-title">更改号码</div>
   </div>
<div class="password inStore-box info-body">
	<form action="/reset_mobile" id="reMobileForm" class="reMobileForm" action="post">
	<input style="display:none;" type="text">
    <ul class="login search-report" id="loginBox">
        <li class="code-input">原号码
            <input class="code-box" type="text" placeholder="请输入原手机号码" id="oldMemberMobile" name="oldMobile" ajaxurl="/match_mobile" datatype="todo,m" todo="newMobile" nullmsg="请输入原手机号码！" errormsg="请输入11位正确的手机号码！">
        </li>
        <li class="code-input">
			<input type="text" class="code-box1" disabled="disabled" placeholder="请输入验证码" id="phoneCode" name="mobileVerificationCode"/>
			<input class="yanz" disabled="disabled" value="获取验证码" id="getMobileCode" type="button"/>
        </li>
        </ul>
        <div><span id="reMobileMsg" style="display:none;margin-left:0.4em;background:none;"></span></div>
    	<div class="code-enter mt20"><input type="button" value="确定" id="reMobileBtn" class="btn-red1"/></div>
    </form>
</div>
</div>
-->
<!-- 公司/姓名弹层 -->
<div class="pop-up clearfix" id="com">
	<div class="basic-box-head">
        <i class="back" name="base"></i>
        <div align="center" class="inStore-title">公司/姓名</div>
    </div>
<div class="password inStore-box info-body">
<form id="reCompanyForm" action="/reset_company">
	<input style="display:none;" type="text">
    <ul class="search-report" id="loginBox">
        <li class="code-input">公司/姓名
            <input class="code-box" disabled="disabled" style="color:#666" type="text" value="${user.companyName!''}" name="oldCompanyName"/>
        </li>
        <li class="code-input">新名称
            <input class="code-box" type="text" placeholder="请输入新公司名称/真实姓名" name="newCompanyName" datatype="*2-16,noteq" nullmsg="请输入新公司名或姓名！" errormsg="新公司名或姓名在2~16位之间！" noteqto="oldCompanyName" noteqmsg="新公司名或姓名与原来的不能一致！"/>
        </li>
    </ul>
    <div><span id="reCompanyMsg" style="display:none;margin-left:0.4em;background:none;"></span></div>
    <div class="code-enter mt20">
    <input type="button" value="确定" id="reCompanyBtn" class="btn-red1"  /></div>
    </form>
</div>
</div>
<!-- 公司/姓名弹层end -->

<#if user.email!''>
<!-- 邮箱修改弹层 -->
<div class="pop-up clearfix" id="ema">
	<div class="basic-box-head">
        <i class="back" name="base"></i>
        <div align="center" class="inStore-title">邮箱修改</div>
    </div>
<div class="password inStore-box info-body">
<form action="/reset_email" id="reEmailForm">
    <ul class="search-report" id="loginBox">
    	<input type="hidden" name="oldEmail" value="${(user.email)!''}"/>
        <li class="code-input">登录密码
            <input type="password" class="code-box" id="updateemail_password" todo="oldPassword" name="oldPassword" placeholder="请输入登录密码！" ajaxurl="/match_password" datatype="*6-16" nullmsg="请输入登录密码！" errormsg="密码范围在6~16位之间！"/><span>
        </li>
        <li class="code-input">已验证手机
            <input class="code-box" type="text"  value="${user.mobile!''}" id="memberMobile" readonly="readonly" name="newMobile">
        </li>
        <li class="code-input">
			<input type="text" class="code-box" style="width:50%;" placeholder="请输入验证码" id="phoneCode1" name="mobileVerificationCode"/>
			<input class="yanz" disabled="disabled" value="获取验证码" id="getMobileCode1" type="button"/>
        </li>
        <li class="code-input">新邮箱
        	<input class="code-box" type="text" placeholder="请输入新邮箱" name="newEmail" datatype="e,noteq" nullmsg="请输入新邮箱！" errormsg="输入的邮箱格式不正确！" ajaxurl="/validEmail"/>
    	</li>
    </ul>
    <div><span id="reEmailMsg" style="display:none;margin-left:0.4em;background:none;"></span></div>
    <div class="code-enter mt20"><input type="button" value="确定" id="reEmailBtn" class="btn-red1"  /></div>
</form>
</div>
</div>
<!-- 邮箱修改end -->
<#else>

<!-- 邮箱设置弹层 -->
<div class="pop-up clearfix" id="sema">
	<div class="basic-box-head">
        <i class="back" name="base"></i>
        <div align="center" class="inStore-title">邮箱绑定</div>
    </div>
<div class="password inStore-box info-body">
<form action="/set_email" id="setEmailForm">
    <ul class="search-report" id="loginBox">
        <li class="code-input">已验证手机
            <input class="code-box" type="text"  value="${user.mobile!''}" id="memberMobile" readonly="readonly" name="newMobile"/>
            <input class="code-box" type="hidden"  value="${user.mobile!''}" name="memberMobile"/>
        </li>
        <li class="code-input">
			<input type="text" class="code-box" style="width:50%;" placeholder="请输入验证码" id="phoneCode2" name="mobileVerificationCode"/>
			<input class="yanz" value="获取验证码" id="getMobileCode2" type="button"/>
        </li>
        <li class="code-input">新邮箱
        	<input class="code-box" type="text" placeholder="请输入新邮箱" name="newEmail" datatype="e,noteq" nullmsg="请输入新邮箱！" errormsg="输入的邮箱格式不正确！"/>
    	</li>
    </ul>
    <div><span id="setEmailMsg" style="display:none;margin-left:0.4em;background:none;"></span></div>
    <div class="code-enter mt20"><input type="button" value="确定" id="setEmailBtn" class="btn-red1"  /></div>
</form>
</div>
</div>
<!-- 邮箱设置end -->
</#if>
<!-- 认证详情弹层 -->
<div class="pop-up clearfix" id="cer">
	<div class="basic-box-head" >
    <i class="back" name="base"></i>
    <div align="center" class="inStore-title">实名认证</div>
    </div>
		<div class="data-box info-body">
        <!--基本资料列表 start--> 
        	<ul>
                <li>
                    <#if user.certifyStatus==2||(user.certifyStatus!=2&&companyCertifyStatus==0)>
                    <#else>
	                    <#if user.certifyStatus==0&&ucPersonCertifyStatus==-1>
	                    	<p class="move-right" name="cer_1_1">个人身份认证 <span>未认证<i class="arrow-right"></i></span></p>
	                    <#elseif user.certifyStatus==0&&ucPersonCertifyStatus==0>
	                    	<p class="move-right" name="cer_1_2">个人身份认证 <span>待审核<i class="arrow-right"></i></span></p>
	                    <#elseif user.certifyStatus==0&&ucPersonCertifyStatus==2>
	                    	<p class="move-right" name="cer_1_3">个人身份认证 <span>已驳回<i class="arrow-right"></i></span></p>
	                    <#else>
	                    	<p class="move-right" name="cer_1_4">个人身份认证 <span>已认证<i class="arrow-right"></i></span></p>
	                    </#if>
                    </#if>
                    <#if ucPersonCertifyStatus==0>
                    <#else>
	                    <#if user.certifyStatus!=2&&companyCertifyStatus==-1>
	                    	<p class="move-right" name="cer_2_1">企业身份认证<span>未认证<i class="arrow-right"></i></span></p></li>
	                    <#elseif user.certifyStatus!=2&&companyCertifyStatus==0>
	                    	<p class="move-right" name="cer_2_2">企业身份认证<span>待审核<i class="arrow-right"></i></span></p></li>
	                    <#elseif user.certifyStatus!=2&&companyCertifyStatus==2>
	                    	<p class="move-right" name="cer_2_3">企业身份认证<span>已驳回<i class="arrow-right"></i></span></p></li>
	                    <#elseif user.certifyStatus==2>
	                    	<p class="move-right" name="cer_2_4">企业身份认证<span>已认证<i class="arrow-right"></i></span></p></li>
	                    </#if>
                    </#if>
            </ul>
         <!--基本资料列表 end-->
         <div class="caption">身份认证是由珍药材提供的身份识别服务，只有完成实名认证才可
            进行交易，小珍倡导诚实交易。<br/>
            ● 个人会员必须进行个人身份认证<br/>
            ● 企业需做企业认证<br/>
            ● 会员必须认证后参与珍药材的各种行为活动</div>
		</div>
		</div>
</div>
<!-- 个人未认证弹层end -->

<#if user.certifyStatus==2||(user.certifyStatus!=2&&companyCertifyStatus==0)>
<#else>
	<#if user.certifyStatus!=2>
	<!-- 已经进行 企业认证 的个人认证不再处理 -->
		<#if user.certifyStatus==0&&ucPersonCertifyStatus==-1>
		<!-- 未认证逻辑 -->
		<!-- 个人认证1-1弹层  个人认证申请-->
		<div class="pop-up clearfix" id="cer_1_1">
			<div class="basic-box-head">
		    <i class="back" name="cer"></i>
		    <div align="center" class="inStore-title">个人认证</div>
		    </div>
		<div class="password inStore-box info-body">
			<form action="/submitPersonCertify" id="legalizePerson" method="post">
			<input type="hidden" name="isPass" value="0"/>
		    <ul class="search-report" id="loginBox">
		        <li class="code-input">真实姓名
		            <input class="code-box" type="text" placeholder="请输入真实姓名" name="name" value="" nullmsg="请输入您的真实姓名。" datatype="zh2-10" errormsg="请正确输入您正确的真实姓名（仅支持2-10个中文）。">
		        </li>
		        <li class="code-input">身份证号
		            <input class="code-box" type="text" placeholder="请输入身份证号码"  name="idCard" value=""  datatype="sfz" nullmsg="请输入您的身份证号码（由15位、18位数字或字母组成）。" errormsg="请输入15位、18位数字或字母组成的身份证号码。">
		        </li>
		    </ul>
		     <div class="pic-card">
	            <div class="uploading">上传身份证</div>
				<div class="file-bg">
					<img id="img" style="width:7em;height:7em;" src=""/>
					<input style="position:relative;top:-7em" type="file" class="file" id="file" name="file" class="btn-none" onChange="javascript:fileUpload();" value=""/>
					<span style="position:relative;top:-9.2em">身份证正面</span>
					<input type="hidden" id="picName" name="picName" value="" />
			        <input type="hidden" id="picPath" name="picPath" value="" />
			        <input type="hidden" id="picType" name="picType" value="" />
			        <input type="hidden" id="picNameSmall" name="picNameSmall" value="" />
			        <input type="hidden" id="picSmallPath" name="picSmallPath" value="" />
			        <input type="hidden" id="picSmallType" name="picSmallType" value="" />
			        <input type="hidden" id="picNameBig" name="picNameBig" value="" />
			        <input type="hidden" id="picBigPath" name="picBigPath"  datatype="ppath" nullmsg=" " errormsg=" " value="" />
			        <input type="hidden" id="picBigType" name="picBigType" value="" />
				</div>
	            <div class="file-bg">
	            	<img id="img1" style="width:7em;height:7em;" src=""/>
		            <input style="position:relative;top:-7em;" type="file" class="file" id="file1" name="file" class="btn-none" onChange="javascript:fileUpload1();" value=""  />
		            <span style="position:relative;top:-9.2em">身份证反面</span>
		            <input type="hidden" id="picName1" name="picName1" value="" />
			        <input type="hidden" id="picPath1" name="picPath1" value="" />
			        <input type="hidden" id="picType1" name="picType1" value="" />
			        <input type="hidden" id="picNameSmall1" name="picNameSmall1" value="" />
			        <input type="hidden" id="picSmallPath1" name="picSmallPath1" value="" />
			        <input type="hidden" id="picSmallType1" name="picSmallType1" value="" />
			        <input type="hidden" id="picNameBig1" name="picNameBig1" value="" />
			        <input type="hidden" id="picBigPath1" name="picBigPath1" datatype="ppath" nullmsg="" errormsg="" value="" />
			        <input type="hidden" id="picBigType1" name="picBigType1" value="" />
	            </div>
	        </div>
		    <div><span class="ts_error" id="legalizePersonMsg" style="display:none;margin-left:0.4em;background:none;"></span></div>
		    <div class="code-enter mt20">
		    <input type="submit" id="legalizeSubbtn" value="确定提交" class="btn-red1"/></div>
		    </form>
		</div>
		</div>
		<!-- 个人认证1-1弹层end -->
		
		<script type="text/javascript">
			var formObj = $("#legalizePerson");
			$("#legalizePerson").Validform({
			    tiptype:function(msg,o,cssctl){
						if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
							$('#legalizePersonMsg').text(msg);
							$('#legalizePersonMsg').show();
						}
					},
			    ajaxPost:true,
			    dragonfly:true,
			    datatype:{//传入自定义datatype类型，可以是正则，也可以是函数（函数内会传入一个参数）;
					"ppath":function(gets,obj,curform,regxp){
						var picPath = $("#picBigPath").val();
		 				var picPath1 = $("#picBigPath1").val();
		 				if(picPath == ""){
		 					$('#legalizePersonMsg').text('请上传身份证正面照!');
							$('#legalizePersonMsg').show();
		 					return false;
		 				}
		 				if(picPath1 == ""){
		 					$('#legalizePersonMsg').text('请上传身份证正面照!');
							$('#legalizePersonMsg').show();
		 					return false;
		 				}
		 				return true;
					}
				},
			    beforeSubmit:function(formObj){
			    	$("#legalizeSubbtn").val("提交中...");
					$("#legalizeSubbtn").attr("disabled","disabled");
					return true;
				},
			    callback:function(data){
			    	if(data.status=='yes'){
			    		$("#subbtn").val("提交成功");
			    		$('#legalizePersonMsg').text('提交资料成功！我们会在1-3个工作日内为您审核，请耐心等待！');
						$('#legalizePersonMsg').show();
			            location.href = "/info?main=cer";
			    	}else if(data.status == 'code001'){
			    		$('#legalizePersonMsg').text('请上传身份证正面图片！');
						$('#legalizePersonMsg').show();
			    	}else if(data.status == 'code002'){
			    		$('#legalizePersonMsg').text('请上传身份证反面图片！');
						$('#legalizePersonMsg').show();
					//added by biran 20150715 提交前增加对会员认证的校验
			    	}else if(data.status == 'code003'){
			    		$('#legalizePersonMsg').text('您的个人认证已提交，请勿再次提交！');
						$('#legalizePersonMsg').show();
			    	}else if(data.status == 'code004'){
			    		$('#legalizePersonMsg').text('您的企业认证已完成，不能提交个人认证！');
						$('#legalizePersonMsg').show();
			    	}else if(data.status == 'code005'){
			    		$('#legalizePersonMsg').text('您的企业认证已完成，请勿重复提交！');
						$('#legalizePersonMsg').show();
			    	}else if(data.status == 'code006'){
			    		$('#legalizePersonMsg').text('您的认证资料正在审核中，请勿再提交！');
						$('#legalizePersonMsg').show();
				      //added end
			    	}else if(data.status=='double'){
			    		$('#legalizePersonMsg').text('提交失败！不可重复提交！');
						$('#legalizePersonMsg').show();
			    	}else{
			    		$('#legalizePersonMsg').text('提交资料失败！请仔细核对资料正确性！');
						$('#legalizePersonMsg').show();
			    	}
			    	$("#legalizeSubbtn").val("确定提交");
					$("#legalizeSubbtn").removeAttr("disabled");
			    }
			});
			
			//图片上传
			function fileUpload(){
				 //ajax请求后台
				 var pic = $("#file").val();
				 if(!/.(gif|jpg|jpeg|bmp|png|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
					$('#legalizePersonMsg').text("请选择图片!");
					$('#legalizePersonMsg').show();
					
					return;
				 }
				 $.ajaxFileUpload({
					url:'/uploadImg?type=0', 
					secureuri:false,
					fileElementId:'file',
					dataType: 'json',
					success: function (data, status)
					{
						if(data.status.code==0){
							$('#legalizePersonMsg').text('上传成功!');
							$('#legalizePersonMsg').show();
							
							$("#picName").val(data.con[0].filename);
							$("#picPath").val(data.con[0].path);
							$("#picType").val(data.con[0].type);
							$("#picNameSmall").val(data.con[1].filename);
							$("#picSmallPath").val(data.con[1].path);
							$("#picSmallType").val(data.con[1].type);
							$("#picNameBig").val(data.con[2].filename);
							$("#picBigPath").val(data.con[2].path);
							$("#picBigType").val(data.con[2].type);
							$("#img").attr("src",data.con[2].path);
						}else if(data.status.code==1){
							$('#legalizePersonMsg').text('图片超过规定大小!');
							$('#legalizePersonMsg').show();
							
							return false;
						}else{
							$('#legalizePersonMsg').text('上传失败!');
							$('#legalizePersonMsg').show();
							
							return false;
						}
					},
					error: function (data, status, e)
					{
						$('#legalizePersonMsg').text('上传失败!');
						$('#legalizePersonMsg').show();
					}
			     })
			 }
			
			function fileUpload1(){
				 //$(this).parents('li').children('span:last').addClass('waiting');
				 //ajax请求后台
				 var pic = $("#file1").val();
				 if(!/.(gif|jpg|jpeg|bmp|png|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
					 $('#legalizePersonMsg').text("请选择图片!");
					$('#legalizePersonMsg').show();
					
					return false;
				 }
				 $.ajaxFileUpload({
					url:'/uploadImg?type=3', 
					secureuri:false,
					fileElementId:'file1',
					dataType: 'json',
					success: function (data, status)
					{
						if(data.status.code==0){
							$('#legalizePersonMsg').text('上传成功!');
							$('#legalizePersonMsg').show();
							
							$("#picName1").val(data.con[0].filename);
							$("#picPath1").val(data.con[0].path);
							$("#picType1").val(data.con[0].type);
							$("#picNameSmall1").val(data.con[1].filename);
							$("#picSmallPath1").val(data.con[1].path);
							$("#picSmallType1").val(data.con[1].type);
							$("#picNameBig1").val(data.con[2].filename);
							$("#picBigPath1").val(data.con[2].path);
							$("#picBigType1").val(data.con[2].type);
							$("#img1").attr("src",data.con[2].path);
						}else if(data.status.code==1){
							$('#legalizePersonMsg').text('图片超过规定大小!');
							$('#legalizePersonMsg').show();
							
							return false;
						}else{
							$('#legalizePersonMsg').text('上传失败!');
							$('#legalizePersonMsg').show();
							
							return false;
						}
					},
					error: function (data, status, e)
					{
						$('#legalizePersonMsg').text('上传失败!');
						$('#legalizePersonMsg').show();
					}
			      })
			  }
		</script>
		
		<#elseif user.certifyStatus==0&&ucPersonCertifyStatus==0>
		 <!-- 待审核逻辑 -->
		<!-- 个人认证1-2弹层  待审核提示-->
		<div class="pop-up clearfix" id="cer_1_2">
			<div class="basic-box-head">
			    <i class="back" name="cer"></i>
			    <div align="center" class="inStore-title">个人认证信息</div>
		    </div>
		<div class="info-body">
			<div class="ad">
				<img src="${RESOURCE_IMG_WX}/images/bh_ad.png" />
			</div>
			<div class="check-box">
		       <ul>
		       	<li style="border:0;">
		        		<p class="bg move-right" name="cer_1_5">个人身份认证<b></b><span style="color:#efad62; margin-left:1.5em;">待审核<i class="bh-right"></i></span></p>
		        	</li>
		            <li class="xz_check">小珍会在1-3个工作日内为您审核！</li>
		            <li>
		            	<a href="tel:4001054315"><p>客服电话<span>400-10-54315</span></p><i class="arrow-right"></i></a>
		           		<a href="http://webchat.b.qq.com/webchat.htm?sid=218808P8z8p8q8x8Q808z"><p>客服 QQ<span>400-10-54315</span></p><i class="arrow-right"></i></a>
		           	</li>
				</ul>
			</div>
		</div>
		</div>
		<!-- 个人认证1-2弹层end -->
		
		<!-- 个人认证1-5弹层 个人信息查看 -->
		<div class="pop-up clearfix" id="cer_1_5">
			<div class="basic-box-head">
		    <i class="back" name="cer"></i>
		    <div align="center" class="inStore-title">个人认证信息</div>
		    </div>
		    <div class="info-body">
		    <div class="rank-box">
		        	<ul>
		                <li>
		                    <p>真实姓名 <span>${ucPersonCertify.name!''}</span></p>
		                    <p>身份证号<span>${ucPersonCertify.idCard!''}</span></p></li>
		            </ul>
			</div>
				<div class="pic-card">
		            <div class="uploading">上传身份证</div>
					<div class="file-bg">
						<img id="img" style="width:7em;height:7em;" 
						<#if (picPath??)>
							src="${picPath}"
						</#if>
						/>
						<input type="file" class="file" style="position:relative;top:-7em" disabled="disabled"/><span style="position:relative;top:-9.2em">身份证正面</span></div>
							<input type="hidden" id="picName" name="picName" value="" />
							<input type="hidden" id="picPath" name="picPath" value="" />
			                <input type="hidden" id="picType" name="picType" value="" />
			                <input type="hidden" id="picNameSmall" name="picNameSmall" value="" />
			                <input type="hidden" id="picSmallPath" name="picSmallPath" value="" />
			                <input type="hidden" id="picSmallType" name="picSmallType" value="" />
			                <input type="hidden" id="picNameBig" name="picNameBig" value="" />
			                <input type="hidden" id="picBigPath" name="picBigPath" value="" />
			                <input type="hidden" id="picBigType" name="picBigType" value="" />
		            <div class="file-bg">
		            	<img id="img" style="width:7em;height:7em;" 
		            	<#if (picPath1??)>
		            		src="${picPath1}"
		            	</#if>
		            	/>
		            	<input type="file" class="file" style="position:relative;top:-7em" disabled="disabled"/><span style="position:relative;top:-9.2em">身份证反面</span></div>
		            		<input type="hidden" id="picName1" name="picName1" value="" />
		                    <input type="hidden" id="picPath1" name="picPath1" value="" />
		                    <input type="hidden" id="picType1" name="picType1" value="" />
		                    <input type="hidden" id="picNameSmall1" name="picNameSmall1" value="" />
		                    <input type="hidden" id="picSmallPath1" name="picSmallPath1" value="" />
		                    <input type="hidden" id="picSmallType1" name="picSmallType1" value="" />
		                    <input type="hidden" id="picNameBig1" name="picNameBig1" value="" />
		                    <input type="hidden" id="picBigPath1" name="picBigPath1" value="" /><!-- datatype="ppath" nullmsg=" " errormsg=" "   -->
		                    <input type="hidden" id="picBigType1" name="picBigType1" value="" />
					</div>
			</div>
		</div>
		<!-- 个人认证1-5弹层end -->
		
		<#elseif user.certifyStatus==0&&ucPersonCertifyStatus==2>
		 <!-- 申请驳回逻辑 -->
		<!-- 个人认证1-3弹层  申请驳回提示页-->
		<div class="pop-up clearfix" id="cer_1_3">
			<div class="basic-box-head">
		    <i class="back" name="cer"></i>
		    <div align="center" class="inStore-title">个人认证信息</div>
		    </div>
		<div class="info-body">
			<div class="ad">
				<img src="${RESOURCE_IMG_WX}/images/gr_ad.png" />
			</div>
			<div class="check-box">
		       <ul>
		       	<li style=" border:0;">
		        		<p class="bg move-right" name="cer_1_6">个人身份认证<b></b><span style="color:#efad62; margin-left:1.5em;">已驳回<i class="bh-right"></i></span></p>
		        	</li>
		            <li class="bh_check">您提交的资料审核未通过，请重新提交资料！</li>
	                <li class="write">
	                <em>请您填写真实信息进行认证，珍药材将对您的信息进行严格审核。</em>
	                <em>身份认证：是由珍药材提供的一项身份识别服务，只有完成实名认证才可进行交易，珍药材倡导诚实交易。</em>
	                <em>个人会员必须进行个人身份认证，企业需做企业认证，每位会员必须以一个身份参与珍药材的各种行为活动。</em>  
	                </li></ul>                                
	              	<ul> 
		            <li>
		            	<a href="tel:4001054315"><p>客服电话<span>400-10-54315</span></p><i class="arrow-right"></i></a>
		           		<a href="http://webchat.b.qq.com/webchat.htm?sid=218808P8z8p8q8x8Q808z"><p>客服 QQ<span>400-10-54315</span></p><i class="arrow-right"></i></a>
		           	</li>
		       </ul>
			</div>
		</div>
		</div>
		<!-- 个人认证1-3弹层end -->
		
		<!-- 个人认证1-6弹层  申请驳回重新提交页-->
		<div class="pop-up clearfix" id="cer_1_6">
			<div class="basic-box-head">
		    <i class="back" name="cer"></i>
		    <div align="center" class="inStore-title">个人认证信息</div>
		    </div>
		<div class="info-body">
		    <form action="/submitPersonCertify" id="legalizePerson" method="post">
		    <input type="hidden" name="certifyId" value="${ucPersonCertify.certifyId!'' }"/>
	        <input type="hidden" name="isPass" value="2"/>
		    <div class="rank-box">
		        	<ul>
		                <li>
		                    <p>真实姓名 <span><input class="code-box" type="text" placeholder="请输入真实姓名" name="name" value="${ucPersonCertify.name!'' }" nullmsg="请输入您的真实姓名。" datatype="zh2-10" errormsg="请正确输入您正确的真实姓名（仅支持2-10个中文）。"></span></p>
		                    <p>身份证号<span><input class="code-box" type="text" placeholder="请输入身份证号码"  name="idCard" value="${ucPersonCertify.idCard!'' }"  datatype="sfz" nullmsg="请输入您的身份证号码（由15位、18位数字或字母组成）。" errormsg="请输入15位、18位数字或字母组成的身份证号码。"></span></p></li>
		            </ul>
			</div>
		          <div class="pic-card">	
		            <div class="uploading">上传身份证</div>
					<div class="file-bg">
						<img id="img" style="width:7em;height:7em;" 
						<#if (picPath??)>
							src="${picPath}"
						</#if>
						/>
						<input type="file" id="file" name="file" class="file" onChange="javascript:fileUpload();" style="position:relative;top:-7em;"/><span style="position:relative;top:-9.2em">身份证正面</span></div>
							<input type="hidden" id="picName" name="picName" value="" />
							<input type="hidden" id="picPath" name="picPath" value="" />
			                <input type="hidden" id="picType" name="picType" value="" />
			                <input type="hidden" id="picNameSmall" name="picNameSmall" value="" />
			                <input type="hidden" id="picSmallPath" name="picSmallPath" value="" />
			                <input type="hidden" id="picSmallType" name="picSmallType" value="" />
			                <input type="hidden" id="picNameBig" name="picNameBig" value="" />
			                <input type="hidden" id="picBigPath" name="picBigPath" value="" />
			                <input type="hidden" id="picBigType" name="picBigType" value="" />
			<div class="file-bg">
						<img id="img1" style="width:7em;height:7em;" 
		            	<#if (picPath1??)>
		            		src="${picPath1}"
		            	</#if>
		            	/>
		            	<input type="file" id="file1" name="file" class="file" onChange="javascript:fileUpload1();" style="position:relative;top:-7em;"/><span style="position:relative;top:-9.2em">身份证反面</span></div>
		            		<input type="hidden" id="picName1" name="picName1" value="" />
		                    <input type="hidden" id="picPath1" name="picPath1" value="" />
		                    <input type="hidden" id="picType1" name="picType1" value="" />
		                    <input type="hidden" id="picNameSmall1" name="picNameSmall1" value="" />
		                    <input type="hidden" id="picSmallPath1" name="picSmallPath1" value="" />
		                    <input type="hidden" id="picSmallType1" name="picSmallType1" value="" />
		                    <input type="hidden" id="picNameBig1" name="picNameBig1" value="" />
		                    <input type="hidden" id="picBigPath1" name="picBigPath1" value="" /><!-- datatype="ppath" nullmsg=" " errormsg=" "   -->
		                    <input type="hidden" id="picBigType1" name="picBigType1" value="" />
			</div>
		    <div><span class="ts_error" id="legalizePersonMsg" style=""></span></div>
		   	<div class="code-enter mt20"><input type="submit" value="重新提交" class="btn-red1"/></div>
		   	</form>
		    <div class="code-enter mt20"><input type="button" value="企业身份认证" class="btn-white move-right" name="<#if user.certifyStatus!=2&&companyCertifyStatus==-1>cer_2_1<#elseif user.certifyStatus!=2&&companyCertifyStatus==0>cer_2_2<#elseif user.certifyStatus!=2&&companyCertifyStatus==2>cer_2_3<#else>cer_2_1</#if>"/></div>
		</div>
		</div>
		<!-- 个人认证1-6弹层end -->
		<script type="text/javascript">
	
		$("#legalizePerson").Validform({
		    tiptype:function(msg,o,cssctl){
						if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
							$('#legalizePersonMsg').text(msg);
							$('#legalizePersonMsg').show();
						}
					},
		    ajaxPost:true,
		    dragonfly:true,
		    beforeSubmit:function(formObj){
		    	$("#legalizeSubbtn").val("提交中...");
				$("#legalizeSubbtn").attr("disabled","disabled");
				return true;
			},
		    callback:function(data){
		    	if(data.status=='yes'){
		    		$('#legalizePersonMsg').text('重新提交资料成功！我们会在1-3个工作日内为您审核，请耐心等待！');
					$('#legalizePersonMsg').show();
		    		$("#legalizeSubbtn").val("重新提交成功");
					location.href = "/info?main=cer";
		    	//added by biran 20150715 提交前增加对会员认证的校验
		    	}else if(data.status == 'code001'){
					$('#legalizePersonMsg').text('请上传身份证正面图片！');
					$('#legalizePersonMsg').show();
				}else if(data.status == 'code002'){
					$('#legalizePersonMsg').text('请上传身份证反面图片！');
					$('#legalizePersonMsg').show();
				//added by biran 20150715 提交前增加对会员认证的校验
				}else if(data.status == 'code003'){
					$('#legalizePersonMsg').text('您的个人认证已提交，请勿再次提交！');
					$('#legalizePersonMsg').show();
				}else if(data.status == 'code004'){
					$('#legalizePersonMsg').text('您的企业认证已完成，不能提交个人认证！');
					$('#legalizePersonMsg').show();
				}else if(data.status == 'code005'){
					$('#legalizePersonMsg').text('您的企业认证已完成，请勿重复提交！');
					$('#legalizePersonMsg').show();
				}else if(data.status == 'code006'){
					$('#legalizePersonMsg').text('您的认证资料正在审核中，请勿再提交！');
					$('#legalizePersonMsg').show();
					  //added end
				}else if(data.status=='double'){
					$('#legalizePersonMsg').text('提交失败！不可重复提交！');
					$('#legalizePersonMsg').show();
				}else{
					$('#legalizePersonMsg').text('提交资料失败！请仔细核对资料正确性！');
					$('#legalizePersonMsg').show();
				}
				$("#legalizeSubbtn").val("重新提交");
				$("#legalizeSubbtn").removeAttr("disabled");
			}
		});
		
		//图片上传
			function fileUpload(){
				 //$(this).parents('li').children('span:last').addClass('waiting');
				 //ajax请求后台
				 var pic = $("#file").val();
				 if(!/.(gif|jpg|jpeg|bmp|png|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
					$('#legalizePersonMsg').text("请选择图片!");
					$('#legalizePersonMsg').show();
					
					return;
				 }
				 $.ajaxFileUpload({
					url:'/uploadImg?type=0', 
					secureuri:false,
					fileElementId:'file',
					dataType: 'json',
					success: function (data, status)
					{
						if(data.status.code==0){
							$('#legalizePersonMsg').text('上传成功!');
							$('#legalizePersonMsg').show();
							
							$("#picName").val(data.con[0].filename);
							$("#picPath").val(data.con[0].path);
							$("#picType").val(data.con[0].type);
							$("#picNameSmall").val(data.con[1].filename);
							$("#picSmallPath").val(data.con[1].path);
							$("#picSmallType").val(data.con[1].type);
							$("#picNameBig").val(data.con[2].filename);
							$("#picBigPath").val(data.con[2].path);
							$("#picBigType").val(data.con[2].type);
							$("#img").attr("src",data.con[2].path);
						}else if(data.status.code==1){
							$('#legalizePersonMsg').text('图片超过规定大小!');
							$('#legalizePersonMsg').show();
							
							return false;
						}else{
							$('#legalizePersonMsg').text('上传失败!');
							
							$('#legalizePersonMsg').show();
							
							return false;
						}
					},
					error: function (data, status, e)
					{
						$('#legalizePersonMsg').text('上传失败!');
						
						$('#legalizePersonMsg').show();
					}
			     })
			 }
			
			function fileUpload1(){
				 //$(this).parents('li').children('span:last').addClass('waiting');
				 //ajax请求后台
				 var pic = $("#file1").val();
				 if(!/.(gif|jpg|jpeg|bmp|png|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
					 $('#legalizePersonMsg').text("请选择图片!");
					
					$('#legalizePersonMsg').show();
					
					return false;
				 }
				 $.ajaxFileUpload({
					url:'/uploadImg?type=3', 
					secureuri:false,
					fileElementId:'file1',
					dataType: 'json',
					success: function (data, status)
					{
						if(data.status.code==0){
							$('#legalizePersonMsg').text('上传成功!');
							$('#legalizePersonMsg').show();
							
							$("#picName1").val(data.con[0].filename);
							$("#picPath1").val(data.con[0].path);
							$("#picType1").val(data.con[0].type);
							$("#picNameSmall1").val(data.con[1].filename);
							$("#picSmallPath1").val(data.con[1].path);
							$("#picSmallType1").val(data.con[1].type);
							$("#picNameBig1").val(data.con[2].filename);
							$("#picBigPath1").val(data.con[2].path);
							$("#picBigType1").val(data.con[2].type);
							$("#img1").attr("src",data.con[2].path);
						}else if(data.status.code==1){
							$('#legalizePersonMsg').text('图片超过规定大小!');
							$('#legalizePersonMsg').show();
							
							return false;
						}else{
							$('#legalizePersonMsg').text('上传失败!');
							$('#legalizePersonMsg').show();
							
							return false;
						}
					},
					error: function (data, status, e)
					{
						$('#legalizePersonMsg').text('上传失败!');
						$('#legalizePersonMsg').show();
					}
			      })
			  }
	</script>
		
		<#else>
		<!-- 个人已认证逻辑 -->
		<!-- 个人认证1-4弹层 个人已认证信息页 -->
		<div class="pop-up clearfix" id="cer_1_4">
			<div class="basic-box-head">
		    <i class="back" name="cer"></i>
		    <div align="center" class="inStore-title">个人认证信息</div>
		    </div>
		<div class="info-body">
		    <div class="rank-box">
		        	<ul>
		                <li>
		                    <p>真实姓名 <span>${user.userName!''}</span></p>
		                    <p>身份证号<span>${ucPersonCertify.idCard!''}</span></p></li>
		            </ul>
			</div>
			<div class="code-enter mt20"><input type="button" value="升级企业认证" class="btn-red1 move-right" name="<#if user.certifyStatus!=2&&companyCertifyStatus==-1>cer_2_1<#elseif user.certifyStatus!=2&&companyCertifyStatus==0>cer_2_2<#elseif user.certifyStatus!=2&&companyCertifyStatus==2>cer_2_3<#else>cer_2_1</#if>"/></div>
		</div>
		</div>
		<!-- 个人认证1-4弹层end -->
		</#if>
	</#if>
</#if>

<#if ucPersonCertifyStatus==0>
<#else>
	<#if user.certifyStatus!=2&&companyCertifyStatus==-1>
	<!-- 企业认证弹层 2-1-->
	<div class="pop-up clearfix" id="cer_2_1">
		<div class="basic-box-head" >
	    <i class="back" name="cer"></i>
	    <div align="center" class="inStore-title">企业身份认证</div>
	    </div>
	<div class="info-body">
	    <form action="/addCompanyCertify" id="legalizeCompany" method="post">
			<div class="password inStore-box">
	            <ul class="search-report" id="loginBox">
	                <li class="code-input">企业名称
	                    <input class="code-box" type="text" placeholder="请输入企业名全称" name="companyName" ajaxurl="/companyNameIsHaved" id="companyName" datatype="s4-60" nullmsg="请输入企业名称。" errormsg="请输入正确的企业名称4-60个字。"/>
	                </li>
	                <li class="code-input">法人名称
	                    <input class="code-box" type="text" placeholder="请输入法人名称"  name="presidentName" id="presidentName" datatype="zh2-10" nullmsg="请输入法人姓名。"  errormsg="请正确输入您正确的法人姓名（仅支持2-10个中文）。"/>
	                </li>
	                <li class="code-input">营业执照号
	                    <input class="code-box" type="text" placeholder="请输入营业执照注册号" name="licenceCode" id="licenceCode" datatype="*"  nullmsg="请输入营业执照上的注册号。" errormsg="请输入正确的营业执照注册号。" />
	                </li>
	                <li class="code-input">机构代码
	                    <input class="code-box" type="text" placeholder="请输入组织机构代码编号" name="orgCode" id="orgCode" />
	                </li>
	                <li class="code-input">有效期
		                <input name="licenceStartdate" placeholder="开始日期" id="licenceStartdate" class="rl" type="text" nullmsg="请输入有效日期。" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'licenceEnddate\')}',dateFmt:'yyyy/MM/dd'})" />—
		                <input name="licenceEnddate" placeholder="结束日期" id="licenceEnddate" class="rl" type="text" datatype="vt" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'licenceStartdate\')}',dateFmt:'yyyy/MM/dd'})"/>
	                </li>
	            </ul>
			</div>
	           <div class="pic-card">
				<div class="file-bg">
					<img id="img2" style="width:7em;height:7em;" src=""/>
					<input type="file" class="file" id="file2" name="file" onchange="fileUpload2();" style="position:relative;top:-7em;"/><span style="position:relative;top:-9.2em;">上传营业执照</span>
	            	<input type="hidden" id="picName2" name="picName" value="" />
	                <input type="hidden" id="picPath2" name="picPath" value="" />
	                <input type="hidden" id="picType2" name="picType" value="" />
	                <input type="hidden" id="picNameSmall2" name="picNameSmall" value="" />
	                <input type="hidden" id="picSmallPath2" name="picSmallPath" value="" />
	                <input type="hidden" id="picSmallType2" name="picSmallType" value="" />
	                <input type="hidden" id="picNameBig2" name="picNameBig" value="" />
	                <input type="hidden" id="picBigPath2" name="picBigPath" value="" />
	                <input type="hidden" id="picBigType2" name="picBigType" value="" />
				</div>
	            <div class="file-bg">
		            <img id="img3" style="width:7em;height:7em;" src=""/>
		            <input type="file" class="file" id="file3" name="file" onchange="fileUpload3();" style="position:relative;top:-7em;"/><span style="position:relative;top:-9.2em;">上传组织机构代码证</span>
	            	<input type="hidden" id="picName3" name="picName1" value="" />
	                <input type="hidden" id="picPath3" name="picPath1" value="" />
	                <input type="hidden" id="picType3" name="picType1" value="" />
	                <input type="hidden" id="picNameSmall3" name="picNameSmall1" value="" />
	                <input type="hidden" id="picSmallPath3" name="picSmallPath1" value="" />
	                <input type="hidden" id="picSmallType3" name="picSmallType1" value="" />
	                <input type="hidden" id="picNameBig3" name="picNameBig1" value="" />
	                <input type="hidden" id="picBigPath3" name="picBigPath1" value="" />
	                <input type="hidden" id="picBigType3" name="picBigType1" value="" />
	            </div>
	          </div>
	    <div><span class="ts_error" id="legalizeCompanyMsg" style=""></span></div>
	    <div class="code-enter mt20"><input type="submit" value="确定提交" id="legalizeCompanySubBtn" class="btn-red1"/></div>
	    </form>
	    
	</div>
	</div>
	<script type="text/javascript">
	  $(function () {
	      $("#legalizeCompany").Validform({
			    tiptype:function(msg,o,cssctl){
						if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
							$('#legalizeCompanyMsg').text(msg);
							$('#legalizeCompanyMsg').show();
						}
					},
			    ajaxPost:true,
			    dragonfly:true,
			    datatype:{
	     			"vt":function(gets,obj,curform,regxp){
	     				var start = $("#licenceStartdate").val();
	     				var end = $("#licenceEnddate").val();
	     				if(gets==""){
	     					return false;
	     				}
	     				if(start==""){
	     					$('#legalizeCompanyMsg').text('开始时间不能为空!');
							$('#legalizeCompanyMsg').show();
	     					return false;
	     				}
	     				return true;
	     			}
			    },
			    beforeSubmit:function(formObj){
			    	$("#legalizeCompanySubBtn").val("提交中...");
					$("#legalizeCompanySubBtn").attr("disabled","disabled");
					return true;
				},
				callback:function(data){
					if(data.status=="yes"){
					    $('#legalizeCompanyMsg').text('提交资料成功！我们会在1-3个工作日内为您审核，请耐心等待!');
						$('#legalizeCompanyMsg').show();
						location.replace('/info');
				    //added by biran 20150715 提交前增加对会员认证的校验
			    	}else if(data.status == 'code003'){
						$('#legalizeCompanyMsg').text('您的个人认证已提交，请勿再次提交！');
						$('#legalizeCompanyMsg').show();
			    	}else if(data.status == 'code004'){
			    		$('#legalizeCompanyMsg').text('您的企业认证已完成，不能提交个人认证！');
						$('#legalizeCompanyMsg').show();
			    	}else if(data.status == 'code005'){
			    		$('#legalizeCompanyMsg').text('您的企业认证已完成，请勿重复提交！');
						$('#legalizeCompanyMsg').show();
			    	}else if(data.status == 'code006'){
			    		$('#legalizeCompanyMsg').text('您的认证资料正在审核中，请勿再提交！');
						$('#legalizeCompanyMsg').show();
				    //add end
					}else if(data.status=='double'){
			    		$('#legalizeCompanyMsg').text('提交失败！不可重复提交！');
						$('#legalizeCompanyMsg').show();
		    		}else{
						$('#legalizeCompanyMsg').text('提交失败！请刷新后重新提交！');
						$('#legalizeCompanyMsg').show();
					}
				}
		  });
	});
	function fileUpload2(){
		//ajax请求后台
		var pic = $("#file2").val();
		if(!/.(gif|jpg|jpeg|png|bmp|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
	        $('#legalizeCompanyMsg').text('请选择图片!');
			$('#legalizeCompanyMsg').show();
			return false;
		}
		$("#img2").val(pic);
		$.ajaxFileUpload({
			url:'/uploadImg?type=6',
			secureuri:false,
			fileElementId:'file2',
			dataType: 'json',
			success: function (data, status)
			{
				if(data.status.code==0){
					$('#legalizeCompanyMsg').text('上传成功!');
					$('#legalizeCompanyMsg').show();
					
					$("#picName2").val(data.con[0].filename);
					$("#picPath2").val(data.con[0].path);
					$("#picType2").val(data.con[0].type);
					$("#picNameSmall2").val(data.con[1].filename);
					$("#picSmallPath2").val(data.con[1].path);
					$("#picSmallType2").val(data.con[1].type);
					$("#picNameBig2").val(data.con[2].filename);
					$("#picBigPath2").val(data.con[2].path);
					$("#picBigType2").val(data.con[2].type);
					
					$("#img2").attr("src",data.con[1].path);
				}else if(data.status.code==1){
			        $('#legalizeCompanyMsg').text('图片超过规定大小!');
					$('#legalizeCompanyMsg').show();
				}else{
			        $('#legalizeCompanyMsg').text('上传失败!');
					$('#legalizeCompanyMsg').show();
					return false;
				}
			},
			error: function (data, status, e)
			{
				$('#legalizeCompanyMsg').text('上传失败!');
				$('#legalizeCompanyMsg').show();
			}
	      });
	}
	function fileUpload3(){
			 //ajax请求后台
			 var pic = $("#file3").val();
			 if(!/.(gif|jpg|jpeg|png|bmp|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
		        $('#legalizeCompanyMsg').text('请选择图片!');
				$('#legalizeCompanyMsg').show();
				return false;
			 }
			 $("#img3").val(pic);
			 $.ajaxFileUpload({
				url:'/uploadImg?type=9', 
				secureuri:false,
				fileElementId:'file3',
				dataType: 'json',
				success: function (data, status)
				{
					if(data.status.code==0){
						$('#legalizeCompanyMsg').text('上传成功!');
						$('#legalizeCompanyMsg').show();
						
						$("#picName3").val(data.con[0].filename);
						$("#picPath3").val(data.con[0].path);
						$("#picType3").val(data.con[0].type);
						$("#picNameSmall3").val(data.con[1].filename);
						$("#picSmallPath3").val(data.con[1].path);
						$("#picSmallType3").val(data.con[1].type);
						$("#picNameBig3").val(data.con[2].filename);
						$("#picBigPath3").val(data.con[2].path);
						$("#picBigType3").val(data.con[2].type);
						
						$("#img3").attr("src",data.con[1].path);
					}else if(data.status.code==1){
						$('#legalizeCompanyMsg').text('图片超过规定大小!');
						$('#legalizeCompanyMsg').show();
						return false;
					}else{
						$('#legalizeCompanyMsg').text('上传失败!');
						$('#legalizeCompanyMsg').show();
						return false;
					}
				},
				error: function (data, status, e)
				{
						$('#legalizeCompanyMsg').text('上传失败!');
						$('#legalizeCompanyMsg').show();
				}
		      });
	}
	</script>
	
	<!-- 企业未认证2-1弹层end -->
	<#elseif user.certifyStatus!=2&&companyCertifyStatus==0>
	<!-- 企业待审核2-2弹层 -->
	
	<div class="pop-up clearfix" id="cer_2_2">
		<div class="basic-box-head">
		    <i class="back" name="cer"></i>
		    <div align="center" class="inStore-title">企业认证信息</div>
		</div>
	<div class="info-body">
		<div class="ad"><img src="${RESOURCE_IMG_WX}/images/bh_ad.png" /></div>
		<div class="check-box">
			<ul><li style="border:0;">
		                    <p class="bg move-right" name="cer_2_6" >企业身份认证<b></b><span style="color:#efad62; margin-left:1.5em;">待审核<i class="bh-right"></i></span></p></li>
		                <li class="xz_check">小珍会在1-3个工作日内为您审核！</li>      
		                <li>
		                    <a href="tel:4001054315"><p>客服电话<span>400-10-54315</span></p><i class="arrow-right"></i></a>
		           			<a href="http://webchat.b.qq.com/webchat.htm?sid=218808P8z8p8q8x8Q808z"><p>客服 QQ<span>400-10-54315</span></p><i class="arrow-right"></i></a>
		           		</li>
		            </ul>
		</div>
	</div>
	</div>
	<!-- 企业认证待审核2-2弹层end -->
	<!-- 企业认证信息弹层2-6 -->
	<div class="pop-up clearfix" id="cer_2_6">
		<div class="basic-box-head" >
		    <i class="back" name="cer"></i>
		    <div align="center" class="inStore-title">企业身份认证</div>
	    </div>
	<div class="info-body">
	        <div class="rank-box">
	        	<ul>
	                <li>
	                    <p>企业名称 <span>${companyCertify.companyName!''}</span></p>
	                    <p>法人名<span>${companyCertify.presidentName!''}</span></p>
	                    <p>营业执照号<span>${companyCertify.licenceCode!''}</span></p>
	                    <p>机构代码<span>${companyCertify.orgCode!''}</span></p>
	                    <p>机构代码<span>${companyCertify.licenceStartdate?string("yyyy/MM/dd")!''}-${companyCertify.licenceEnddate?string("yyyy/MM/dd")!''}</span></p>
	                </li>
	            </ul>
		</div>
	
	        	<div class="pic-card">          
					<div class="file-bg"><img id="img2" style="width:7em;height:7em;" src="${picPath2!''}"/>
						<input type="file" class="file" id="file2" disabled="disabled" name="file" onchange="fileUpload2();" style="position:relative;top:-7em;"/><span style="position:relative;top:-9.2em;">上传营业执照</span></div>
		        	<div class="file-bg"><img id="img3" style="width:7em;height:7em;" src="${picPath3!''}"/>
						<input type="file" class="file" id="file3" disabled="disabled" name="file" onchange="fileUpload3();" style="position:relative;top:-7em;"/><span style="position:relative;top:-9.2em;">上传组织机构代码证</span></div>       
	          	</div>
	</div>
	</div>
	<!-- 企业认证信息弹层end -->
	<#elseif user.certifyStatus!=2&&companyCertifyStatus==2>
	<!-- 企业认证已驳回2-3弹层 -->
	<div class="pop-up clearfix" id="cer_2_3">
		<div class="basic-box-head" >
		    <i class="back" name="cer"></i>
		    <div align="center" class="inStore-title">企业身份认证</div>
	    </div>
	<div class="info-body">
		<div class="ad"><img src="${RESOURCE_IMG_WX}/images/bh_ad3.png" /></div>
		<div class="check-box">
	        	<ul><li style="border:0;">
	                    <p class="bg move-right" name="cer_2_5">企业身份认证<b></b><span style="color:#ff1400; margin-left:1.5em;">已驳回<i class="bh-right" name="cer_2_5"></i></span></p></li>
	                <li class="bh_check">您提交的资料审核未通过，请重新提交资料！</li>
	                <li class="write">
	                <em>请您填写真实信息进行认证，珍药材将对您的信息进行严格审核。</em>
	                <em>身份认证：是由珍药材提供的一项身份识别服务，只有完成实名认证才可进行交易，珍药材倡导诚实交易。</em>
	                <em>个人会员必须进行个人身份认证，企业需做企业认证，每位会员必须以一个身份参与珍药材的各种行为活动。</em>  
	                </li></ul>                                
	              <ul> <li>
	                    <a href="tel:4001054315"><p>客服电话<span>400-10-54315</span></p><i class="arrow-right"></i></a>
		           		<a href="http://webchat.b.qq.com/webchat.htm?sid=218808P8z8p8q8x8Q808z"><p>客服 QQ<span>400-10-54315</span></p><i class="arrow-right"></i></a>
		           	</li>
	            </ul>
			</div>
	</div>
	</div>
	<!-- 企业认证已驳回2-3弹层 -->
	<!-- 企业身份认证弹层 -->
	<div class="pop-up clearfix" id="cer_2_5">
		<div class="basic-box-head" >
	    <i class="back" name="cer"></i>
	    <div align="center" class="inStore-title">企业身份认证</div>
	    </div>
	<div class="info-body">
	    <form id="legalizeCompany" method="post" action="/addCompanyCertify">
	    <input type="hidden" id="certifyId" name="certifyId" value="${companyCertify.certifyId!'' }" />
			<div class="password inStore-box">
	            <ul class="search-report" id="loginBox">
	                <li class="code-input">企业名称
	                    <input class="code-box" type="text" value="${companyCertify.companyName!''}" placeholder="请输入企业名全称" name="companyName" ajaxurl="companyNameIsHaved" id="companyName" datatype="s4-60" nullmsg="请输入企业名称。" errormsg="请输入正确的企业名称4-60个字。"/>
	                </li>
	                <li class="code-input">法人名称
	                    <input class="code-box" type="text" value="${companyCertify.presidentName!''}" placeholder="请输入法人名称" name="presidentName" id="presidentName" datatype="zh2-10" nullmsg="请输入法人姓名。"  errormsg="请正确输入您正确的法人姓名（仅支持2-10个中文）。"/>
	                </li>
	                <li class="code-input">营业执照号
	                    <input class="code-box" type="text" value="${companyCertify.licenceCode!''}" placeholder="请输入营业执照注册号" name="licenceCode" id="licenceCode" datatype="*"  nullmsg="请输入营业执照上的注册号。" errormsg="请输入正确的营业执照注册号。" />
	                </li>
	                <li class="code-input">机构代码
	                    <input class="code-box" type="text" value="${companyCertify.orgCode!''}" placeholder="请输入组织机构代码编号" name="orgCode" id="orgCode" />
	                </li>
	                <li class="code-input">有效期 <input name="licenceStartdate" placeholder="请选择开始日期" id="licenceStartdate" value="${companyCertify.licenceStartdate?string("yyyy/MM/dd")!''}" class="rl" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'licenceEnddate\')}',dateFmt:'yyyy/MM/dd'})" /> —
	                <input name="licenceEnddate" placeholder="请选择结束日期" id="licenceEnddate" value="${companyCertify.licenceEnddate?string("yyyy/MM/dd")!''}" class="rl" type="text" datatype="vt" nullmsg="请输入有效日期。" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'licenceStartdate\')}',dateFmt:'yyyy/MM/dd'})"/>
	                </li>
	            </ul>
			</div>
	           <div class="pic-card">
				<div class="file-bg">
					<img id="img2" style="width:7em;height:7em;" src="${picPath2!''}"/>
					<input type="file" class="file" id="file2" name="file" onchange="fileUpload2();" style="position:relative;top:-7em;"/><span style="position:relative;top:-9.2em;">上传营业执照</span>
	            	<input type="hidden" id="picName2" name="picName" value="${companyCertifyDto.picName!'' }" />
	                <input type="hidden" id="picPath2" name="picPath" value="${companyCertifyDto.picPath!'' }" />
	                <input type="hidden" id="picType2" name="picType" value="${companyCertifyDto.picType!'' }" />
	                <input type="hidden" id="picNameSmall2" name="picNameSmall" value="${companyCertifyDto.picNameSmall!'' }" />
	                <input type="hidden" id="picSmallPath2" name="picSmallPath" value="${companyCertifyDto.picSmallPath!'' }" />
	                <input type="hidden" id="picSmallType2" name="picSmallType" value="${companyCertifyDto.picSmallType!'' }" />
	                <input type="hidden" id="picNameBig2" name="picNameBig" value="${companyCertifyDto.picNameBig!'' }" />
	                <input type="hidden" id="picBigPath2" name="picBigPath" value="${companyCertifyDto.picBigPath!'' }" />
	                <input type="hidden" id="picBigType2" name="picBigType" value="${companyCertifyDto.picBigType!'' }" />
				</div>
	            <div class="file-bg">
		            <img id="img3" style="width:7em;height:7em;" src="${picPath3!''}"/>
		            <input type="file" class="file" id="file3" name="file" onchange="fileUpload3();" style="position:relative;top:-7em;"/><span style="position:relative;top:-9.2em;">上传组织机构代码证</span>
	            	<input type="hidden" id="picName3" name="picName1" value="${companyCertifyDto.picName1!'' }" />
	                <input type="hidden" id="picPath3" name="picPath1" value="${companyCertifyDto.picPath1!'' }" />
	                <input type="hidden" id="picType3" name="picType1" value="${companyCertifyDto.picType1!'' }" />
	                <input type="hidden" id="picNameSmall3" name="picNameSmall1" value="${companyCertifyDto.picNameSmall1!'' }" />
	                <input type="hidden" id="picSmallPath3" name="picSmallPath1" value="${companyCertifyDto.picSmallPath1!'' }" />
	                <input type="hidden" id="picSmallType3" name="picSmallType1" value="${companyCertifyDto.picSmallType1!'' }" />
	                <input type="hidden" id="picNameBig3" name="picNameBig1" value="${companyCertifyDto.picNameBig1!'' }" />
	                <input type="hidden" id="picBigPath3" name="picBigPath1" value="${companyCertifyDto.picBigPath1!'' }" />
	                <input type="hidden" id="picBigType3" name="picBigType1" value="${companyCertifyDto.picBigType1!'' }" />
	            </div>
	          </div>  
	    <div><span class="ts_error" id="legalizeCompanyMsg" style=""></span></div>
	    <div class="code-enter mt20"><input type="submit" value="确定提交" id="legalizeCompanySubBtn" class="btn-red1"/></div>
	    </form>
	</div>
	</div>
	<script type="text/javascript">
	  $(function () {
	      $("#legalizeCompany").Validform({
			    tiptype:function(msg,o,cssctl){
						if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
							$('#legalizeCompanyMsg').text(msg);
							$('#legalizeCompanyMsg').show();
						}
					},
			    ajaxPost:true,
			    dragonfly:true,
			    datatype:{
	     			"vt":function(gets,obj,curform,regxp){
	     				var start = $("#licenceStartdate").val();
	     				var end = $("#licenceEnddate").val();
	     				if(gets==""){
	     					return false; 
	     				}
	     				if(start==""){
	     					$('#legalizeCompanyMsg').text('开始时间不能为空!');
							$('#legalizeCompanyMsg').show();
	     					return "false";
	     				}
	     				return true;
	     			}
			    },
			    beforeSubmit:function(formObj){
			    	$("#legalizeCompanySubBtn").val("提交中...");
					$("#legalizeCompanySubBtn").attr("disabled","disabled");
					return true;
				},
				callback:function(data){
					if(data.status=="yes"){
					    $('#legalizeCompanyMsg').text('提交资料成功！我们会在1-3个工作日内为您审核，请耐心等待!');
						$('#legalizeCompanyMsg').show();
						$("#legalizeSubbtn").val("重新提交成功");
						location.href = "/info?main=cer";
				    //added by biran 20150715 提交前增加对会员认证的校验
			    	}else if(data.status == 'code003'){
						$('#legalizeCompanyMsg').text('您的个人认证已提交，请勿再次提交！');
						$('#legalizeCompanyMsg').show();
			    	}else if(data.status == 'code004'){
			    		$('#legalizeCompanyMsg').text('您的企业认证已完成，不能提交个人认证！');
						$('#legalizeCompanyMsg').show();
			    	}else if(data.status == 'code005'){
			    		$('#legalizeCompanyMsg').text('您的企业认证已完成，请勿重复提交！');
						$('#legalizeCompanyMsg').show();
			    	}else if(data.status == 'code006'){
			    		$('#legalizeCompanyMsg').text('您的认证资料正在审核中，请勿再提交！');
						$('#legalizeCompanyMsg').show();
				    //add end
					}else if(data.status=='double'){
			    		$('#legalizeCompanyMsg').text('提交失败！不可重复提交！');
						$('#legalizeCompanyMsg').show();
		    		}else{
						$('#legalizeCompanyMsg').text('提交失败！请刷新后重新提交！');
						$('#legalizeCompanyMsg').show();
					}
					$("#legalizeCompanySubBtn").val("确认，提交");
					$("#legalizeCompanySubBtn").removeAttr("disabled");
				}
		  });
	});
	function fileUpload2(){
		//ajax请求后台
		var pic = $("#file2").val();
		if(!/.(gif|jpg|jpeg|png|bmp|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
	        $('#legalizeCompanyMsg').text('请选择图片!');
			$('#legalizeCompanyMsg').show();
			return false;
		}
		$("#img2").val(pic);
		$.ajaxFileUpload({
			url:'/uploadImg?type=6',
			secureuri:false,
			fileElementId:'file2',
			dataType: 'json',
			success: function (data, status)
			{
				if(data.status.code==0){
					$('#legalizeCompanyMsg').text('上传成功!');
					$('#legalizeCompanyMsg').show();
					
					$("#picName2").val(data.con[0].filename);
					$("#picPath2").val(data.con[0].path);
					$("#picType2").val(data.con[0].type);
					$("#picNameSmall2").val(data.con[1].filename);
					$("#picSmallPath2").val(data.con[1].path);
					$("#picSmallType2").val(data.con[1].type);
					$("#picNameBig2").val(data.con[2].filename);
					$("#picBigPath2").val(data.con[2].path);
					$("#picBigType2").val(data.con[2].type);
					
					$("#img2").attr("src",data.con[1].path);
				}else if(data.status.code==1){
			        $('#legalizeCompanyMsg').text('图片超过规定大小!');
					$('#legalizeCompanyMsg').show();
				}else{
			        $('#legalizeCompanyMsg').text('上传失败!');
					$('#legalizeCompanyMsg').show();
					return false;
				}
			},
			error: function (data, status, e)
			{
				$('#legalizeCompanyMsg').text('上传失败!');
				$('#legalizeCompanyMsg').show();
			}
	      });
	}
	function fileUpload3(){
			 //ajax请求后台
			 var pic = $("#file3").val();
			 if(!/.(gif|jpg|jpeg|png|bmp|GIF|JPG|JPEG|BMP|PNG)$/.test(pic)){
		        $('#legalizeCompanyMsg').text('请选择图片!');
				$('#legalizeCompanyMsg').show();
				return false;
			 }
			 $("#img3").val(pic);
			 $.ajaxFileUpload({
				url:'/uploadImg?type=9', 
				secureuri:false,
				fileElementId:'file3',
				dataType: 'json',
				success: function (data, status)
				{
					if(data.status.code==0){
						$('#legalizeCompanyMsg').text('上传成功!');
						$('#legalizeCompanyMsg').show();
						
						$("#picName3").val(data.con[0].filename);
						$("#picPath3").val(data.con[0].path);
						$("#picType3").val(data.con[0].type);
						$("#picNameSmall3").val(data.con[1].filename);
						$("#picSmallPath3").val(data.con[1].path);
						$("#picSmallType3").val(data.con[1].type);
						$("#picNameBig3").val(data.con[2].filename);
						$("#picBigPath3").val(data.con[2].path);
						$("#picBigType3").val(data.con[2].type);
						
						$("#img3").attr("src",data.con[1].path);
					}else if(data.status.code==1){
						$('#legalizeCompanyMsg').text('图片超过规定大小!');
						$('#legalizeCompanyMsg').show();
						return false;
					}else{
						$('#legalizeCompanyMsg').text('上传失败!');
						$('#legalizeCompanyMsg').show();
						return false;
					}
				},
				error: function (data, status, e)
				{
						$('#legalizeCompanyMsg').text('上传失败!');
						$('#legalizeCompanyMsg').show();
				}
		      });
	}
	</script>
	<!-- 企业身份认证弹层end -->
	<#else>
	<!-- 企业认证信息弹层 -->
	<div class="pop-up clearfix" id="cer_2_4">
		<div class="basic-box-head">
	    <i class="back" name="cer"></i>
	    <div align="center" class="inStore-title">企业身份认证</div>
	    </div>
	<div class="info-body">
	        <div class="rank-box">
	        	<ul>
	                <li>
	                    <p>企业名称 <span>${companyCertify.companyName!''}</span></p>
	                    <p>法人名称<span>${companyCertify.presidentName!''}</span></p>
	                    <p>营业执照号<span>${companyCertify.licenceCode!''}</span></p>
	                    <#if (companyCertify.orgCode??)>
							<p>机构代码<span>${companyCertify.orgCode!''}</span></p>
	                    </#if>
	                    <!--
							<p>营业执照有效期<span>${companyCertify.licenceStartdate?string("yyyy/MM/dd")!''}-${companyCertify.licenceEnddate?string("yyyy/MM/dd")!''}</span></p>
	                	-->
	                </li>
	            </ul>
		</div>
	</div>
	</div> 
	<!-- 企业认证信息弹层end -->
	</#if>
</#if>
<script type="text/javascript">
$(function(){
	var main = "${main}";
	if(main){
		var target = main;
		$('.main').hide();
		$('.main').attr('style','');
		var Height = $(document).height();
		$('.main').height(Height);
		$('.main').attr('class','pop-up-two');
		$('#'+target).attr('class','pop-up main');
		$('#'+target).css('position','relative');
		$('#'+target).show();
		$('#'+target).animate({left:0},100);
	}
});
</script>
<script type="text/javascript">
    $(function(){
    	//登出
        $('#logout').click(function(){
        	location.replace('/logout');
        });
        
        //登出
        $('#back').click(function(){
        	location.replace('/myzyc');
        });
    
		//按下改变密码输入框的type
		$('.password-input').keyup(function() {
			if ($(this).next().hasClass('cur')) {
				$(this).attr('type', 'text');
			} else {
				$(this).attr('type', 'password');
			}
		});

        //添行加
        $('#add').on('click',function(){
            var string = '<tr><td><input  value="" class="text" /></td><td><input  value="" class="text" /></td><td><input  value="" class="text" /></td><td><input  value="" class="text" /></td><td><i class="remove"></i></td></tr>';
            $('.inStore-box2 table tr:last').before(string);
            return false;
        });
        $('.remove').on('click',function(){
            $(this).parents('tr').remove();
        })
		//上传、删除照片
        $('.pic-upfile ul li i').each(function(){
            $(this).on('click',function(){
                $(this).parent('li').remove();
            })
        });

        //
        $('.select').each(function(){
           $(this).children('a:last').css('border','none');
        });
		
		//厂地分类层
		var Height = $(document).height();
		$('#base').attr('style','left:0');
		$('.info-body').height(Height-4.2*16);
		$('.info-body').css('overflow-x','hidden');
		$('.info-body').css('overflow-y','auto');
		
		//从右像左滑
		$('.move-right').click(function(){
			var target = $(this).attr('name');
			$('.main').hide();
			$('.main').attr('style','');
			var Height = $(document).height();
			$('.main').height(Height);
			$('.main').attr('class','pop-up-two');
			$('#'+target).attr('class','pop-up main');
			$('#'+target).css('position','relative');
			$('#'+target).show();
			$('#'+target).animate({left:0},100);
		});
		
		//从左向右滑的
		$('.back').click(function(){
			var target = $(this).attr('name');
			$('.main').hide();
			$('.main').attr('style','');
			var Height = $(document).height();
			$('.main').height(Height);
			$('.main').attr('class','pop-up');
			$('#'+target).attr('class','pop-up-two main');
			$('#'+target).css('position','relative');
			$('#'+target).show();
			$('#'+target).animate({left:0},100);
		});
		
		//修改密码
	    validSubmit('rePassword');
	    
	    //修改手机号
	    validSubmit('reMobile', function(formName, objval){
	    	clearTimeout(t);
	    	$("#getMobileCode").removeAttr("disabled");
			$("#getMobileCode").val("获取验证码");
	    });
	    
	    //修改公司名称
	    validSubmit('reCompany', function(formName, objval){
	    });
	    
	    //设置邮箱
	    validSubmit('setEmail', function(formName, objval){
	    });
	    
	    //修改邮箱
	    validSubmit('reEmail', function(formName, objval){
	    });
	    
	    //获取手机短信校验码
		$("#getMobileCode").click(function(){
			var memberMobile = $("#reMobileForm input[name=newMobile]").val();
			var oldMemberMobile = $("#reMobileForm input[name=oldMobile]").val();
			if(memberMobile==null || memberMobile==""){
				$("#reMobileMsg").text("请输入新手机号码！");
				$("#reMobileMsg").show();
				return;
			}
			var reg = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}|177[0-9]{8}$/;
			if(!reg.test(memberMobile)){
				$("#reMobileMsg").text("请输入11位正确的手机号码！");
				$("#reMobileMsg").show();
				return;
			}
			if(memberMobile == oldMemberMobile){
				$("#reMobileMsg").text("新手机号码与原手机号码不能一致！");
				$("#reMobileMsg").show();
				return;
			}
			
			time(120,"getMobileCode");
			$.post(
				"/getMobileCode",
				{memberMobile:memberMobile},
		     	function(data){
		     	    	if(data=='eorr'){
		     	    		$('#codeErorr').css("display","list-item");
		     				$("#codeErorr .error").text("120秒内只能获取一次验证码");
		     	    			reflag = true;
		     	    	}
		     	    	if(data=='y'){
		     	    		time(120);
		     	    	}
		     	    });
		});
		
		//修改邮箱获取手机短信校验码
		$("#getMobileCode1").click(function(){
			var memberMobile = $("#reEmailForm input[name=newMobile]").val();
			var updateemail_password = $("#updateemail_password").val();
			$.post(
				"/getMobileCode",
				{memberMobile:memberMobile},
		     	function(data){
		     	    if(data=='eorr'){
			     		$("#reEmailMsg").text("120秒内只能获取一次验证码");
		     	     	reflag = true;
		     	    }
		     	    if(data=='y'){
		     	    	time(120,"getMobileCode1");
		     		}
		     	}
		     );
		});
		
		//设置邮箱获取手机短信校验码
		$("#getMobileCode2").click(function(){
			var memberMobile = $("#reEmailForm input[name=newMobile]").val();
			var updateemail_password = $("#updateemail_password").val();
			$.post(
				"/getMobileCode",
				{memberMobile:memberMobile},
		     	function(data){
		     	    if(data=='eorr'){
			     		$("#setEmailMsg").text("120秒内只能获取一次验证码");
		     	     	reflag = true;
		     	    }
		     	    if(data=='y'){
		     	    	time(120,"getMobileCode2");
		     		}
		     	}
		     );
		});
		
		//获取手机短信校验码(支付密码)
		$("#getCode").click(function(){
			time(120,"getCode");
			$.post("/getCode");
		});
    });
    //ready end
    
	//上传、删除身份证照片
    $('.pic-card ul li i').each(function(){
        $(this).on('click',function(){
            $(this).parent('li').remove();
        })
    });

    //
    $('.select').each(function(){
       $(this).children('a:last').css('border','none');
    });
    
    //修改前后的内容不能一致的check(Validform的扩展属性)
	$.Datatype.noteq = function(gets,obj,curform,regxp){
		var value = $.trim(gets);
		var name = obj.attr('noteqto');
		var value1 = $.trim($('input[name='+ name +']', curform).val());
		if(value === value1){
			return obj.attr('noteqmsg');
		} else {
				if(name=='oldMobile'){
					$('#phoneCode').removeAttr('disabled');
					$('#getMobileCode').removeAttr('disabled');
					$('#phoneCode').attr('placeholder','验证时请不要修改手机号');
				}
			return true;
		}
	}
	
	$.Datatype.todo = function(gets,obj,curform,regxp){
		var name = obj.attr('todo');
		if($('input[name='+ name +']', curform)[0].value != ''){
			$('input[name='+ name +']', curform)[0].validform_lastval = '';
			$('input[name='+ name +']', curform).trigger("blur");
		}
		return true;
	}
    
    $.Datatype.pwd = function(gets,obj,curform,regxp){
		var reg1=/^[a-zA-Z0-9\\pP]{6,16}$/;
		reg2=/^(?:\d*|[a-zA-Z]*|[\x00-\x2f\x3a-\x40\x5b-\x60\x7b-\x7f]*)$/;
		var _newPwd = $.trim(gets)
		if(!reg1.test(_newPwd)){
			return "密码为6-16个字符!";
		}
		return true;
	}
	
	function validSubmit(formName, fn){
		var form = "#" + formName + "Form";
		var msgBox = "#"+ formName + "Msg";
		var validForm = $(form).Validform({
	    	btnSubmit:"#" + formName + "Btn",
	    	btnReset:"#" + formName + "ResetBtn",
	        tiptype:function(msg,o,cssctl){
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					//if(msg.trim()!=''){
						$(msgBox).text(msg);
						$(msgBox).show();
						if(o.obj.attr('id')=='updateemail_password' && msg==' '){
							$('#phoneCode1').removeAttr('disabled');
							$('#getMobileCode1').removeAttr('disabled');
							$('#phoneCode1').attr('placeholder','验证时请不要修改手机号');
						}
					//}
				}
			},
			dragonfly:true,
			ignoreHidden:true,
	        showAllError:true,
	        ajaxPost:true,
	        callback:function(data){
	        	if('y' == data.status){
	        		//请求成功
	        		$(msgBox).text(data.info);
	        		$(msgBox).attr('class','Validform_checktip');
	        		location.replace('/info?msg='+data.info);
	        	} else if('n' == data.status){
	        		//请求失败
	        		$(msgBox).text(data.info);
	        	} else {
	        		$(msgBox).text("未知错误，修改失败！");
	        	}
	        	$(msgBox).show();
	        }
	    });
		
		return validForm;
	}
	
	//获取短信验证码后，120s可以重新发送 
	var t;
	function time(i,id){
		i-=1 ;
		$("#"+id).val('(' +i+ ')秒后再获取');
		$("#"+id).attr("disabled","disabled");
		if(i==0){
			$("#"+id).removeAttr("disabled");
			$("#"+id).val("重新获取验证码");
			return;
		}
		t = setTimeout("time("+ i +",'"+id+"')", 1000);
	}
	
    $(function(){
		//点眼睛ICON图标修改密码输入框中的type
        $('#loginBox .eye').on('click',function(){
            $(this).toggleClass('cur');
            if($(this).hasClass('cur')){
                $(this).prev().attr('type','text');
            }else{
                $(this).prev().attr('type','password');
                $(this).removeClass('cur');
            }
        });

		//点击提交按钮显示错误提示信息
        $('#Register').click(function(){
            $('#msg').removeAttr('hidden');
        })

        //touch事件替换CLICK事件
        $('input[type=button]').touchStart(function () {
            $(this).addClass('hover');
        });
        $('input[type=button]').touchMove(function () {
            $(this).addClass('hover');
        });
        $('input[type=button]').touchEnd(function () {
            $(this).removeClass('hover');
        });
        $('input[type=button]').tapOrClick(function () {
            $(this).removeClass('hover');
        });
    });
</script>
<script>
	 $('.re-phone').click(function(){
		location.href= "/authenticateMobile/authenticateIdentity?optType=0";
	})
</script>
<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>
</body>
</html>