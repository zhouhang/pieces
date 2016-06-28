<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材-聚好药商，卖珍药材-查看认证详情</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
 	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
 	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/list.css"  />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<#include "common/header.ftl" /> 
<div class="area-1200 clearfix">
	<#include "common/left.ftl" /> 
    <!-- 会员左侧 over -->
    <div class="hy-right fr">
        <div class="border-1 box-tip">
            <p><i class="tip-icon"></i>只有实名认证才可进行交易<br/>
                <span>身份认证：是由珍药材提供的一项身份识别服务，只有完成实名认证才可进行交易，珍药材倡导诚实交易。</span><br/>
                <span>个人会员必须进行个人身份认证，企业需做企业认证，每位会员必须以一个身份参与珍药材的各种行为活动。</span>
            </p>
        </div>
        <div class="hr-red mt10"></div>
        <form>
            <ul class="form-2 mt15">
                <li>
                    <label><i class="red">*</i>企业名称：</label>
                    <span>${ucComCertify.companyName!'' }</span>
                </li>
                <li>
                	<label><i class="red">*</i>营业执照：</label>
                	<#assign status=(ucComCertify.status!'')>
                	<#switch status>
                		<#case 0>
                			认证中
                			<#break>
                		<#case 1>
                			已认证
                			<#break>
                		<#case 2>
                			未认证
                			<#break>
                		<#default>
                			其他
                	</#switch>
                </li>
                <li>
                    <label><i class="red">*</i>法定代表人：</label>
                    <span>${ucComCertify.presidentName!'' }</span>
                </li>
                <li>
                    <label><i class="red">*</i>营业执照注册号：</label>
                    <span>${ucComCertify.licenceCode!'' }</span>
                </li>
                <li>
                    <label><i class="red">*</i>营业执照有效期：</label>
                    <#if (ucComCertify.status==1)>
                    	<span> ******** </span>至 <span> ******** </span>
                    <#else>
                    	<span>${ucComCertify.licenceStartdate?string("yyyy-MM-dd")!''  }</span>至 <span>${ucComCertify.licenceEnddate?string("yyyy-MM-dd")!''  }</span>
                    </#if>
                </li>
                <#if (ucComCertify.status==1)>
                	<#else>
                	<li>
                    	<label><i class="red">*</i>企业证件：</label>
                    	<a href="#" name="see" id="seePicPath" onclick="showPic('${ucComCertify.certifyId }','8')" class="btn-hui1 dis-in-bk">点击查看大图</a>
               		 </li>
	                <#if ucComCertify.orgCode??>
		                <li>
		                    <label>组织机构代码：</label>
		                    <span>${ucComCertify.orgCode!'' }</span>
		                </li>
		               <li>
		                    <label>组织机构代码证：</label>
			                <a href="#" name="see" id="seePicPath1" onclick="showPic('${ucComCertify.certifyId }','11')"  class="btn-hui1 dis-in-bk">点击查看大图</a>
		                </li>  
	                </#if>
                </#if>
            </ul>
        </form>
    </div>

</div>
<div class="popup-box" id="picPath">
    <span class="close" id="Close"></span>
    <img src="" id="showImg"   />
</div>

<!-- 底部  -->
<#include "common/footer.ftl" /> 
<!-- 底部 end  -->

<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/imgView/jquery.imageView.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript">
  $(function () {
	    $('#Close').click (function(){
	        $(this).parents('#picPath').hide();
	        $('.bghui').remove();
	    });
  });
  function showPic(certifyId,type){
	  $('#picPath').show();
      var html = '<div class="bghui"></div>';
      var height = $(document).height();
      $('body').append(html);
      $('.bghui').css('height',height);
	  $('#showImg').attr('src',"/verifyImg?certifyId="+certifyId+"&type="+type);
	  $('#picPath').imageView({width:600, height:400});
      return false;
  }
</script>

</body>
</html>