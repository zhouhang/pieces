<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head lang="zh">
    <meta charset="UTF-8">
    <title>珍药材_中国领先的中药材现货交易平台</title>
    <meta name="description" content="珍药材54315.com是九州通打造的有质量保障的中药材现货交易平台,为中药材上下游企业提供专业的中药材信息资讯，中药材交易服务，中药材供应链金融服务，中药材质押服务，中药材物流仓储以及中药材质检服务。"/>
	<meta name="keywords" content="珍药材网，中药材市场，中药材价格行情，中药材交易，中药材仓储物流，中药材融资，中药材贷款，中药材金融，中药材采购，中药材供应"/>
    <link rel="stylesheet" type="text/css"  href="html/resources/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="html/resources/css/jzt-user-center/memberCenter.css" />
    <link rel="stylesheet" type="text/css"  href="html/resources/css/common.css" />
    <link rel="stylesheet" type="text/css" href="html/resources/js/Validform/css/style.css" />
</head>
<style>
.topper{width:1100px;}
.foot ul li img{margin-left:-20px;}
</style>
<body>

<!-- 头部  -->
<div class="area-1100">
    <div class="topper sty1">
        <div class="logo clearfix">
        	<a href="${JOINTOWNURL}">聚好药商，卖珍药材！</a>
            <span>登录</span>
        </div>
    </div>
</div>
<!-- 头部 end  -->
<div class="area-1100">
    <div class="login-bg">
        <div class="login-box">
            <h2>会员登录</h2>
            <form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">
			
			
			
			
			
            <!-- <form method="post" action="/login?service=http://localhost:8080/cas&amp;locale=en" id="fm1"> -->
                <ul>
                    <li>
                        <label>会员名/手机号码</label>
                        <p>
                        <form:input cssClass="text login-text user" id="username" datatype="*"  tabindex="1" nullmsg="请输入用户名！" accesskey="${userNameAccessKey}" path="username" autocomplete="off" htmlEscape="true" />
                        </p>
                        <!-- <input type="text" class="text login-text user register-text" datatype="*" nullmsg="请输入用户名！" accesskey="u" value="" name="username" /></p> -->
                        <p></p>
                    </li>
                    <li>
                        <label><span class="fr"><a href="${findPasswordUrl}">忘记密码？</a></span> 登录密码</label>
                        <p>
                        <form:password cssClass="text login-text password"  id="password" datatype="*" tabindex="2" path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" />
                        <!-- <input type="password" name="password" class="text login-text password register-text" datatype="*" nullmsg="请输入密码！" accesskey="p" value="" /> -->
                        </p>
                        <p></p>
                    </li>
                    <li>
                        <label>验证码</label>
                        <p>
                        <input class="text login-text yz" tabindex="3"  id="vcode"  name="vcode"  autocomplete="off"  />
                        <!-- <input name="vcode" type="text" class="text login-text yz register-text" datatype="*" nullmsg="请输入验证码！" accesskey="l" value="" /> -->
                        
							<a id="vcodeA" href="javascript:;" style="width:200px;height:30px; display:inline-block;">
						       <img id="vcodeImg"  width="60" height="30" src="captcha.htm" style="display: block;position: relative;float: left;padding-left: 5px;"/>看不清楚,换一张!
						    </a>
						</p>
						<p></p>
                    </li>
                    <li><div id="errorMsg" style="padding-top:8px; height:12px;color:red;">
                    <form:errors path="*" id="msg" cssClass="red" element="div" htmlEscape="false" /></div>
                    </li>
                    <li class="mt25">
                    <input class="login-btn" id="subbtn" name="button" value="登 录" accesskey="l"  tabindex="4" type="button" />
                    <!-- <input name="submit" type="submit" value="登 录" class="login-btn" />  -->
                    </li>
                    <input type="hidden" name="lt" value="${loginTicket}" />
			      	<input type="hidden" name="execution" value="${flowExecutionKey}" />
			      	<input type="hidden" name="_eventId" value="submit" /><br />
			      	<h2><span class="fr" style="font-size:14px;">还没有账号？ <a style="font-size:18px;" href="${registerUrl}">马上注册&nbsp;&nbsp;</a></span></h2>
                </ul>
            </form:form>
        </div>
    </div>
</div>
<!-- 底部  -->
<div class="foot">
    <div class="area-1200">
        <ul class="clearfix">
            <li class="style-1">
                <strong class="f14">新手指南</strong>
                <a href="help.54315.com/view-1770909150-4890870192.html"  target="_blank">了解珍药材</a>
                <a href="help.54315.com/view-826324128-134455933.html"  target="_blank">注册及实名认证</a>
                <a href="help.54315.com/view-1222808050-4277461942.html"  target="_blank">采购商入门</a>
                <a href="help.54315.com/view-2011458294-7859114524.html"  target="_blank">供应商入门</a>
            </li>
            <li class="style-1">
                <strong class="f14">交易服务</strong>
                <a href="help.54315.com/view-1970233306-7837069404.html" target="_blank">挂牌销售</a>
                <a href="help.54315.com/view-1717367380-9370413524.html" target="_blank">药材采购</a>
                <a href="help.54315.com/view-1763402417-5532681146.html" target="_blank">交易保障</a>
                <a href="help.54315.com/view-1112212469-1417544386.html" target="_blank">如何付款</a>
                <a href="help.54315.com/view-1074337588-1881602956.html" target="_blank">药材交收</a>
                <a href="help.54315.com/view-1442508962-9836334809.html" target="_blank">货款结算</a>
            </li>
            <li class="style-1">
                <strong class="f14">金融服务</strong>
                <a href="help.54315.com/view-165069101-570170504.html"  target="_blank">药材质押</a>
                <a href="help.54315.com/view-90284155-1702282056.html"  target="_blank">应收保理</a>
                <a href="help.54315.com/view-362846536-471937280.html"  target="_blank">授信采购</a>
            </li>
            <li class="style-1">
                <strong class="f14">仓储物流</strong>
                <a href="help.54315.com/view-1154327359-8543844178.html" target="_blank">仓库介绍</a>
                <a href="help.54315.com/view-1990279866-6239033474.html" target="_blank">入库流程</a>
                <a href="help.54315.com/view-1148978752-4550043483.html" target="_blank">保管安全</a>
                <a href="help.54315.com/view-1291479146-6465076424.html" target="_blank">远程查看</a>
            </li>
            <li class="style-1">
                <strong class="f14">委托服务</strong>
                <a href="help.54315.com/view-296408919-7787049031.html" target="_blank">委托采购</a>
                <a href="help.54315.com/view-1463152138-1723459540.html" target="_blank">委托销售</a>
                <a href="help.54315.com/view-443979987-1583418185.html" target="_blank">委托养护</a>
                <a href="help.54315.com/view-1393195069-8959880481.html" target="_blank">委托配送</a>
                <a href="help.54315.com/view-1585124319-3648243659.html" target="_blank">全外包服务</a>
            </li>
            <li class="style-1">
                <strong class="f14">价格行情</strong>
                <a href="http://www.zyczyc.com/info/WenZhang.aspx?lmid=2&key=十日谈"  target="_blank">专家十日谈</a>
                <a href="http://www.zyczyc.com/info/MarkNews.aspx"  target="_blank">行情报道</a>
                <a href="http://www.zyczyc.com/info/JiaGe.aspx"  target="_blank">今日价格</a>
                <a href="http://www.zyczyc.com/info/ChanDiInfo.aspx"  target="_blank">产地动态</a>
                <a href="http://www.zyczyc.com/BBS/LunTan.aspx"  target="_blank">药商论坛</a>
            </li>
            <li class="style-2"><a href="http://www.54315.com/feedBackManage/ideaResp" target="_blank">立即反馈</a> </li>
        </ul>

        <div class="hr"></div>
        <div align="center" class="mt15">
             <p class="links"><a href="http://www.jztey.com/" target="_blank">九州通医药集团</a>
                <a href="http://www.jztzygs.com/zycygs/" target="_blank">九州天润中药产业公司</a>
                <a href="http://www.yyjzt.com/" target="_blank">药品在线采购平台</a>
                <a href="http://www.ehaoyao.com/" target="_blank">好药师网上药店</a>
                <a href="http://www.qumaiyao.com/" target="_blank">去买药网</a>
                 <a href="http://www.jzsyg.com" target="_blank">九州上医馆</a>
                <a class="bn" href="http://www.50yc.com/" target="_blank" >物联云仓</a>
            </p>
            <p class="links"><a href="help.54315.com/view-4152142030-1649064836.html" target="_blank">关于珍药材</a>
            	<a href="help.54315.com/view-561630814-1170162866.html" target="_blank">珍药材动态</a>
                <a href="help.54315.com/view-4152142030-998555506.html" target="_blank">合作伙伴</a>
                <a href="help.54315.com/view-4152142030-969783539.html" target="_blank">经营证书</a>
                <a href="help.54315.com/view-4152142030-3504604999.html" target="_blank">法律声明</a>
                <a href="help.54315.com/view-4152142030-548339172.html" target="_blank">联系我们</a>
                <a href="help.54315.com/view-4152142030-8088297484.html" target="_blank">诚聘英才</a>
               <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254793674'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1254793674' type='text/javascript'%3E%3C/script%3E"));</script>
            </p>
            <p class="links mt10">
			                增值电信业务经营许可证：<span>鄂B2-20150052</span> | 互联网药品信息服务资格证：<a class="bn pn" href="
			http://help.54315.com/view-4152142030-969783539.html
			" target="_blank">(鄂)-经营性-2015-0019</a><br/>
			                Copyright© 2014-2016 ，珍药材版权所有 鄂ICP备14019602号-2

            </p>
        </div>
    </div>
</div>
<!-- 底部 end  -->
<script type="text/javascript" src="/html/resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/html/resources/js/public.js"></script>
<script type="text/javascript" src="/html/resources/js/Validform/js/Validform_v5.3.2.js"></script>
<script>
    $(function(){
    	
    	$('a[title="站长统计"]').addClass('bn');
        
    	/* $("#fm1").Validform({
            tiptype:2,
            showAllError:true
        }); */
        
     	// 绑定键盘按下事件  
    	$(document).keydown(function(e) {  
    		// 回车键事件  
    	    if(e.which == 13) {  
    	    	$('.login-btn').click();
    	    }    	  
    	});  	
    	//验证码事件绑定
		$('#vcodeA').click(function() {
			$('#vcodeImg').attr('src','/captcha.htm?t='+new Date().getTime());
		});
        
        $("#subbtn").click(function(){
        	$("#errorMsg").html("");
        	var _userName = $("#username").val();
        	var _pwd = $("#password").val();
        	var _vcode = $("#vcode").val();
        	if(_userName==null || _userName==''){
        		$("#errorMsg").html("会员名不能为空!");
        		return;
        	}
        	if(_pwd==null || _pwd==''){
        		$("#errorMsg").html("密码不能为空!");
        		return;
        	}
        	if(_vcode==null || _vcode==''){
        		$("#errorMsg").html("验证码不能为空!");
        		return;
        	}
        	$("#username").val($("#username").val().replace(/(^\s*)|(\s*$)/g, ""));
        	$("#fm1").submit();
        });
    })
    
    
</script>
</body>
</html>