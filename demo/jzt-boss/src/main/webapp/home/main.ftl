<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>中药材电子商务管理系统</title>
	<#include "home/common.ftl">
</head>
<body>
<#include "home/top.ftl" />  
<div class="wapper">
<!-- <#include "home/left.ftl" /> -->
<!-- 左侧菜单  start -->
<div class="nav">
    <ul class="nav" style="display: block;">
	    <#list rules as rule>
	    	<#if rule_index==0>
	          <li class="cur"><a href="#"><i class="${rule.className!'' }"></i>${rule.permissionName!'' }</a>
	          <ul class="sub-nav">
	        <#else>
               <li class=""><a href="#"><i class="${rule.className!'' }"></i>${rule.permissionName!'' }</a>
              <ul class="sub-nav">
             </#if>
	             <#list rule.sonList as r1>
	            	<li>${r1.permissionName!'' }
	            	<ul class="sub-navs">
	                     	<#list r1.sonList as r12>
	                     		<#if r12.path==ThirdMenu>
			             	<li class="hover"><a href="${(r12.operationResource)!'javascript:void(0)' }?golableMenuPath=${(r12.path)!''}">${(r12.permissionName)!'' }</a></li>
			             <#else>
			             	<li><a href="${(r12.operationResource)!'javascript:void(0)' }?golableMenuPath=${(r12.path)!''}">${(r12.permissionName)!'' }</a></li>
			             </#if>
	                         	
	                     	</#list>
	                     </ul>
	                 </li>
	              </#list>
	            </ul>
	            <div class="pop-sub-nav">
	                <span></span>
	                <#if (rule.sonList?exists)>
	                 <#if (rule.sonList?size!=0)>
	                  <p>
	                   <#list rule.sonList as r11>
	                       <a href="${(r11.operationResource)!'javascript:void(0)' }" target="right">${(r11.permissionName)!'' }</a>
	                   </#list>
	                  </p>
	                 </#if>
	                </#if>
	            </div>
	        </li>
	      </#list>
    </ul>
</div>
<!-- 左侧菜单  end   -->

<div class="main">
    <div class="page-main">
        <h1 class="title1 wel">欢迎进入 <span>Welcome to here!</span></h1>
        <p class="welcome">这不该是一场赛跑，它是一次旅行，我们要学会好好欣赏每一段的风景。<br/>
            静下心来，做好当前的每一件事，为梦想起航！
        </p>
    </div>
</div>
</div>
<script type="text/javascript" language="javascript">
	var iframeHeight;//iframe当前高度
	var iframeTimer;//iframe定时器
	//iframe自适应高度
	function resizeIframe(iframeId){
	    var iframe = document.getElementById(iframeId);
	    $(iframe).removeAttr('style');
	    try{
	        var bHeight = iframe.contentWindow.document.body.scrollHeight;
	        var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
	        var height = Math.max(bHeight, dHeight);
	        $(iframe).height(height+50);
	        if(iframeHeight==height){
	        	clearInterval(iframeTimer);
	        }
	        iframeHeight = height;
	        //alert(bHeight+"/"+dHeight);
	    }catch (ex){
	    	//alert(ex);
	    }
	}
$(function(){
    $('.nav li .sub-nav:first').css('display','block');
    $('#logout').click(function(){
    	  window.location = "/logout";
    });
    $('#editSelf').click(function(){
    	$(".sub-nav li a[href='getBossUserOrgAndRoleInfo']").click();
    	window.frames["right"].document.location.href='/getBossUserOrgAndRoleInfo';
    });
   			
    //加载右边iframe
    (function(){
   		var Height = $(document).height()+$(document).scrollTop();
   	    var HeightRight = Height - $('div.top').height();
   	    $('#right').attr('height',HeightRight+100); 
   	    //调整iframe高度到合适
   	 	//adjustHeight("right");
   	 	
   	 	//调整高度
   	 	function adjustHeight(iframeId){
   			$("#"+iframeId).load(function(){
   				iframeTimer = window.setInterval(function(){
					resizeIframe(iframeId);
				},200);
   			});
   	 	}	    
 	})();
});
</script>
</body>
</html>
