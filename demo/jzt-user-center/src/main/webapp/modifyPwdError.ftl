<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材-聚好药商，卖珍药材-修改密码</title>
     <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
 	 <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/common.css" />
 	 <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
	 <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
    <style>
        .ui-state-default,.ui-widget-content .ui-state-default, .ui-state-default{
            -webkit-box-border-radius: 3px;
            -moz-box-border-radius: 3px;
            border-radius: 3px;
            color: #fff;
            border: 0 none;
            background: #d84736;
            background: -webkit-gradient(linear, left top, left bottom, from(#d84736), to(#db4932));
            background: -moz-linear-gradient(top,  #d84736,  #db4932);
            filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#d84736', endColorstr='#c7c7c7');
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
            font-size: 14px;
            margin: 10px 5px 50px 5px;
            padding: 3px 10px;
        }
        .ui-widget-content {
            border: 3px solid #e60b0f;
        }
        .ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-icon-only.ui-dialog-titlebar-close
/*.ui-dialog .ui-dialog-titlebar-close*/ {
            background-image:url("${RESOURCE_IMG}/images/jzt-user-center/close-red.png");
        }
        span{
        	color:#f00;
        }
    </style>
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="topper sty1">
  <div class="area-1200">
    <div class="logo clearfix">
   		<a href="${JOINTOWNURL}">聚好药商，卖好药材！</a>
        <span>找回密码</span>
    </div>
  </div>
</div>
<div class="area-1200">
    <div class="find-pswd">
       		<p style="font-size:14px; text-align:center;">${errorMsg }</p>
       		<p style="line-height:35px;" align="center"><a href="https://passport.54315.com/login?service=http://uc.54315.com/cas" style="font-size:14px;">返回登陆页面</a></p>
    </div>
</div>
<!-- 底部  -->
    <#include "common/footer.ftl">
<!-- 底部 end  -->
<!-- 修改成功弹层 -->
<div class="" id="reMemberSuc"  title="提示">
    <div class="box">
        <p class="re-suc">
                密码修改成功，可登录！
        </p>
    </div>
</div>
<!-- 修改成功弹层 -->

<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script>
    //弹层
    $( "#reMemberSuc" ).dialog({
        autoOpen: false,
        width: 485,
        buttons: [
            {
                text: "确定",
                click: function() {
                    $( this ).dialog( "close" );
                    $('.bghui').remove();
                    window.location.href="https://passport.54315.com/login?service=http://uc.54315.com/casuc";
                }
            }

        ]
    });
    // Link to open the dialog
    $( "#reSuc" ).click(function( event ) {
    	var _newPwd = $("input[name='newPassword']").val();
    	var _verityPwd = $("input[name='verityPassword']").val();
    	var _memberName = $("input[name='userName']").val();
    	var _key = $("input[name='key']").val();
    	if(_newPwd==null||_newPwd==''){
    		$("#newPwdMsg").html(" 密码不能为空!");
    		return;
    	}
    	var reg1=/[a-zA-z0-9\\pP]{6,16}/;
			reg2=/^(?:\d*|[a-zA-Z]*|[\x00-\x2f\x3a-\x40\x5b-\x60\x7b-\x7f]*)$/;
    	if(!reg1.test(_newPwd)){
    		$("#newPwdMsg").html("");
			$("#newPwdMsg").html(" 密码为6-16个字符,数字或字母或标点符号的组成!");
			return;
		}
		if(reg2.test(_newPwd)){
			$("#newPwdMsg").html("");
			$("#newPwdMsg").html(" 不能仅包含数字、字母或符号中的一种!");
			return;
		}
    	if(_newPwd != _verityPwd){
    		$("#newPwdMsg").html("");
    		$("#verPwdMsg").html(" 两次输入的密码不一致!");
    		return;
    	}
    	$("#newPwdMsg").html("");
    	$("#verPwdMsg").html("");
    	
    	
	$.post("/findBackPwd/modityPwd", {
			memberName : _memberName,
			newPassword : _newPwd,
			key : _key
		}, function(data) {
			if (data == 1) {
				var html = '<div class="bghui"></div>';
				$('body').append(html);
				$("#reMemberSuc").dialog("open");
				event.preventDefault();
			} else if (data == 0) {
				alert("修改失败");
				return;
			}

		});

	});
</script>
</body>
</html>